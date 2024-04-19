package util.database;

import Database.Problem;
import util.file.FileHelper;

import java.io.IOException;
import java.nio.file.Path;

public class DBHelper {

    private static final Path problemPath = FileHelper.getRootPath().resolve("data").resolve("problem");

    public static InnerProblem getInnerProblem(int index) throws IOException {
        Path filePath = problemPath.resolve(index + ".json");
        Problem problem = new Problem(filePath);
        return new InnerProblem(problem);
    }

    public static String[] getNameOfAllProblem() throws IOException {
        int maxIndex = FileHelper.countJsonFiles();
        String[] ss = new String[maxIndex];
        for (int i = 0; i < maxIndex; i++) {
            InnerProblem innerProblem = getInnerProblem(i + 1);
            String s = innerProblem.getIndex() + "." + innerProblem.getName();
            ss[i] = s;
        }
        return ss;
    }
}