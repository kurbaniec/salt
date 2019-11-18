package pass.salt.code.loader
import java.io.File
import pass.salt.code.modules.SaltProcessor
import pass.salt.code.container.Container
import pass.salt.code.exceptions.ExceptionsTools
import pass.salt.code.exceptions.MainPackageNotFoundException
import pass.salt.code.loader.config.Config
import java.io.FileOutputStream
import java.lang.Exception
import java.nio.file.Paths
import java.util.jar.JarFile
import java.util.logging.Logger


class Loader {
    val log = Logger.getLogger("SaltLogger")
    val config: Config
    val container: Container
    val singleModules = mutableListOf<SaltProcessor>()
    val classModules = mutableListOf<SaltProcessor>()

    init {
        config = Config()
        container = Container()
        container.addElement("config", config)
        load()
    }

    /**
     * Look for annotations in "out/.../pack" (compile output) and process them.
     * Also adds all classes to the "container".
     */
    private fun load(): Loader {
        val pair = getLocation()
        val pack = pair.first
        val location = pair.second
        log.fine("Location of compiled classes to scan: " + location.absolutePath)
        log.fine("Project path located")

        // Module System -> Single Instance
        log.fine("Loading module System (Single)")
        singleModules.add(SaltProcessor.module(module = "SaltSecurity", config = config, container = container))
        singleModules.add(SaltProcessor.module(module = "SaltThreadPool", config = config, container = container))
        singleModules.add(SaltProcessor.module(module = "PepperServer", config = config, container = container))
        singleModules.add(SaltProcessor.module(module = "MongoInit", config = config, container = container))
        for (mod in singleModules) {
            mod.process()
        }

        // Module System -> Process Annotations through all Classes
        // Order of modules is important!
        log.fine("Loading module System (Classes)")
        classModules.add(SaltProcessor.module("ComponentScan", config, container))
        classModules.add(SaltProcessor.module("MongoScan", config, container))
        classModules.add(SaltProcessor.module("AutowiredScan", config, container))
        classModules.add(SaltProcessor.module("MappingScan", config, container))
        classModules.add(SaltProcessor.module("SecurityScan", config, container))
        for (mod in classModules) {
            location.walk().forEach {
                // TODO do something about KT classes
                // TODO what do do with salt path?
                if (it.toString().endsWith(".class") &&
                        !((it.toString().endsWith("Kt.class")) || it.toString().endsWith("$1.class") ||
                                it.toString().substring(location.absolutePath.length).contains("salt") ||
                                it.toString().contains("$"))) {

                    val className = getClassName(it.toString(), pack)

                    // Module System -> Process Annotations through all Classes
                    mod.process(className)
                }
            }
        }
        // Delete temporary files if created
        deleteDirectory(File("tmpout"))

        log.fine("SaltApplication fully loaded")
        return this
    }

    fun shutdown() {
        for (mod in classModules) {
            mod.shutdown()
        }
        for (mod in singleModules) {
            mod.shutdown()
        }
    }

    private fun getClassName(name: String, pack: String): String {
        var ret = name.replace(".class", "")
        ret = ret.substring(ret.lastIndexOf(File.separator + pack + File.separator)+1, ret.length)
        ret = ret.replace(File.separator, ".")
        return ret
    }

    /**
     * [pack] is the name of the main package.
     */
    private fun getLocation(): Pair<String, File> {
        val path = if (System.getProperty("user.dir") != null) {
            System.getProperty("user.dir")
        } else Paths.get(".").toAbsolutePath().normalize().toString()

        log.fine("Application detected following program path: $path")

        if (File("$path/run.jar").exists()) {
            val jar = JarFile("$path/run.jar")
            val tmpOut = File("tmpout")
            deleteDirectory(tmpOut)
            tmpOut.mkdir()
            val outPath = "$path/tmpout"
            val entries = jar.entries()
            while (entries.hasMoreElements()) {
                val entry = entries.nextElement()
                if (!entry.name.contains("META-INF") && !entry.name.contains("kotlin")) {
                    var file = File(outPath, entry.name)
                    if (!file.exists()) {
                        file.parentFile.mkdirs()
                        file = File(outPath, entry.name)
                    }
                    if (entry.isDirectory) {
                        continue
                    }
                    val inp = jar.getInputStream(entry)
                    val out = FileOutputStream(file)
                    while (inp.available() > 0) {
                        out.write(inp.read())
                    }
                    out.close()
                    inp.close()
                }
            }
            val dir = File(outPath).listFiles(File::isDirectory)
            when {
                dir == null -> throw MainPackageNotFoundException("No main package found in jar")
                dir.size > 1 -> throw MainPackageNotFoundException("More than one main package found!\nRefactor your project so you have exactly one main package")
                else -> return Pair(dir.first().name, dir.first())
            }
        }
        else {
            log.info("No 'jar.run' found, using /out for file scan...")
            // TODO safety check
            val pack: String
            try {
                pack = File("$path/src/main/kotlin").list().get(0)!!
            } catch (ex: Exception) {
                log.warning(ExceptionsTools.exceptionToString(ex))
                throw MainPackageNotFoundException("Main package not found under /src/")
            }
            try {
                //val check = File("$path/out")
                File("$path/build").walk().forEach {
                    if (it.toString().endsWith(pack)) {
                        return Pair(pack, it)
                    }
                }
            } catch (ex: Exception) {
                log.warning(ExceptionsTools.exceptionToString(ex))
                throw MainPackageNotFoundException("No compiled classes found in /build/")

            }
            throw MainPackageNotFoundException("Something went wrong, check your project structure\nTry to use or use not the buidled 'run.jar'")
        }
    }

    /**
     * Deletes a directory, if it exists.
     */
    private fun deleteDirectory(directory: File): Boolean {
        if (directory.exists()) {
            val files = directory.listFiles()
            if (null != files) {
                for (i in files.indices) {
                    if (files[i].isDirectory) {
                        deleteDirectory(files[i])
                    } else {
                        files[i].delete()
                    }
                }
            }
        }
        return directory.delete()
    }
}