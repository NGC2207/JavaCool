package util.backend;

import Judger.compile.Compiler;
import util.database.DBHelper;
import util.database.InnerProblem;
import util.file.FileHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class BEHelper {
    private static final Path answerPath=FileHelper.getRootPath().resolve("answer");

    public static InnerProblem getInnerProblem(int index) throws IOException {
        int maxIndex= FileHelper.countJsonFiles();
        if(index<=0||index>maxIndex){
            throw new IOException("index error:index<=0||index>maxIndex");
        }
        return DBHelper.getInnerProblem(index);
    }

    public static String[] getNameOfAllProblem() throws IOException {
        return DBHelper.getNameOfAllProblem();
    }

    public static String compile(int index,String code){
        try{
            Path folderPath=answerPath.resolve(Integer.toString(index));
            System.out.println(folderPath);
            FileHelper.createFolder(folderPath);
            FileHelper.createFile(folderPath,code,"java");
            Path compilePath=folderPath.resolve("Main.java");
            return Compiler.compile(compilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
