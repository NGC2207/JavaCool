package Judger.run;

import Judger.CMD;

import java.nio.file.Path;
import java.util.logging.Logger;

public class Runner {
    private static final Logger logger = Logger.getLogger("Runner");

    public static String run(Path path) {
        logger.info("Running " + path.toString());
        String[] runCMD = {"java", "-cp",path.toString(), "Main"};
        return CMD.execute(runCMD);
    }
}
