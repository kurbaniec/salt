import os
import sys
import subprocess
import platform

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
            ptf = platform.system()
            classpath = ""
            current = os.getcwd()
            dlm = ";"
            esc = "\""
            if ptf != "Windows":
                esc = ""
            if ptf != "Windows":
                dlm = ":"
            for dirpath, _, filenames in os.walk(current + os.sep + "lib"):
                for f in filenames:
                    classpath += str(os.path.abspath(os.path.join(dirpath, f)) + dlm)
            if arg == "build":
                # src = os.path.normpath("./src/") + "*"
                src = "src" + os.sep + "*"
                cmp = "kotlinc " + src + " -cp " + esc + classpath + esc + " -include-runtime -d run.jar"
                p = subprocess.Popen(cmp, shell=True)
                (output, err) = p.communicate()
                p_status = p.wait()
                print("Build...Done")
            else:
                run = "java -cp " + esc + "run.jar" + dlm + classpath + esc + " pass.Main"
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
