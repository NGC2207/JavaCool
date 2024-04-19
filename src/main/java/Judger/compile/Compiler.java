package Judger.compile;

import Judger.CMD;

import java.nio.file.Path;
import java.util.logging.Logger;

public class Compiler {
    private static final Logger logger=Logger.getLogger("Compiler");
    public static String compile(Path path){
        logger.info("Compiling "+path.toString());
        String[] compileCMD={"javac",path.toString()};
        return CMD.execute(compileCMD);
    }
}
