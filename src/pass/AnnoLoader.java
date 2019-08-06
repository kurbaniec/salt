package pass;

import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
public class AnnoLoader {
    public void load() throws IOException {
        for (URL url: getRootUrls ()) {
            File f = new File (url.getPath());
            if (f.isDirectory()) {
                visitFile (f);
            }
            else {
                visitJar (url);
            }
        }
    }
    public List<URL> getRootUrls () {
        List<URL> result = new ArrayList<>();

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        while (cl != null) {
            if (cl instanceof URLClassLoader) {
                URL[] urls = ((URLClassLoader) cl).getURLs();
                result.addAll (Arrays.asList (urls));
            }
            cl = cl.getParent();
        }
        return result;
    }



    void visitFile (File f) throws IOException {
        if (f.isDirectory ()) {
            final File[] children = f.listFiles ();
            if (children != null) {
                for (File child: children) {
                    visitFile (child);
                }
            }
        }
        else if (f.getName ().endsWith (".class")) {
            try (FileInputStream in = new FileInputStream (f)) {
               handleClass(in);
            }
        }
    }

    void visitJar (URL url) throws IOException {
        try (InputStream urlIn = url.openStream ();
             JarInputStream jarIn = new JarInputStream (urlIn)) {
            JarEntry entry;
            while ((entry = jarIn.getNextJarEntry ()) != null) {
                if (entry.getName ().endsWith (".class")) {
                    handleClass(jarIn);
                }
            }
        }
    }

    void handleClass (InputStream in) throws IOException {
        MyClassVisitor cv = new MyClassVisitor ();
        new ClassReader(in).accept (cv, 0);

        if (cv.hasAnnotation) {
            System.out.println("has annotation");
        }
        if (cv.hasSuffix && !cv.hasAnnotation) {
            System.out.println ("service without annotation: " + cv.className);
        }
        if (cv.hasAnnotation && !cv.hasSuffix) {
            System.out.println ("wrong name: " + cv.className);
        }
    }

    class MyClassVisitor extends ClassVisitor {
        public boolean hasSuffix;
        public boolean hasAnnotation;
        public String className;

        MyClassVisitor () {
            super (Opcodes.ASM5);
        }

        /**
        @Override public void visit (int version,
                                     int access, String name, String signature,
                                     String superName, String[] interfaces) {
            className = name.replace('/', '.');
            hasSuffix = name.endsWith("Service");
        }*/
    /**
        @Override public AnnotationVisitor visitAnnotation (
                String desc, boolean visible) {
            if(desc.contains("Get")) {
                System.out.println("found it");
            }
            if (desc.equals ("Lorg/springframework/stereotype/Service;")) {
                hasAnnotation = true;
            }
            return null;
        }
    }
}
*/