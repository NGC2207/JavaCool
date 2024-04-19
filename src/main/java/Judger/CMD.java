package Judger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Logger;

public class CMD {
    private final static Logger logger=Logger.getLogger("CMD");
    public static String execute(String[] cmd){
        StringBuilder result=new StringBuilder();
        int exitCode;
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                 BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String s;
                while ((s = stdInput.readLine()) != null) {
                    result.append(s).append("\n");
                }
                while ((s = stdError.readLine()) != null) {
                    result.append(s).append("\n");
                }
                exitCode = process.waitFor();
            }
        }catch (Exception e){
            throw new RuntimeException("CMD: "+ Arrays.toString(cmd) +"error: "+e.getMessage(),e);
        }
        if(exitCode!=0){
            logger.warning("CMD: "+ Arrays.toString(cmd) +" exit code: "+exitCode);
        }
        return result.toString();
    }
}
