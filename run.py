import os

if __name__ == "__main__":
    classpath = ""
    for dirpath, _, filenames in os.walk("C:\\Users\\aon91\\Desktop\\kotlinTest\\Passwd\\lib"):
        for f in filenames:
            classpath += str(os.path.abspath(os.path.join(dirpath, f)) + ";")
    os.popen("kotlinc .\\src\\* -cp \"" + classpath + "\" -include-runtime -d run.jar")
    os.popen("java -cp \"run.jar;" + classpath + "\" pass.Main")