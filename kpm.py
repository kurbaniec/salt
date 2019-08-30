import os
import sys
import subprocess


def msg():
    print("\tUse argument 'build' to compile project")
    print("\tUse argument 'run' to start program")


if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Invalid arguments count!")
        msg()
        sys.exit(0)
    else:
        arg = sys.argv[1]
        if arg == "build" or arg == "run":
            classpath = ""
            for dirpath, _, filenames in os.walk("C:\\Users\\aon91\\Desktop\\kotlinTest\\Passwd\\lib"):
                for f in filenames:
                    classpath += str(os.path.abspath(os.path.join(dirpath, f)) + ";")
            if arg == "build":
                compile = "kotlinc .\\src\\* -cp \"" + classpath + "\" -include-runtime -d run.jar"
                p = subprocess.Popen(compile, shell=True)
                (output, err) = p.communicate()
                p_status = p.wait()
                print("Build...Done")
            else:
                run = "java -cp \"run.jar;" + classpath + "\" pass.Main"
                p = subprocess.Popen(run, shell=True)
                try:
                    (output, err) = p.communicate()
                except KeyboardInterrupt:
                    print("Stopping...")
                    sys.exit(0)
                p_status = p.wait()
                print("Command output: " + str(output))
        else:
            print("Invalid argument!")
            msg()
            sys.exit(0)
