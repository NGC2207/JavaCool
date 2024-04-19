package Database;

import javax.json.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Problem {
    private final int index;
    private final String name;
    private final String description;
    private final String difficulty;
    private final String[] example;
    private final String[] tip;
    private final int timeLimit;
    private final int memoryLimit;
    private final String[] givenParameter;
    private final String[] expectedAnswer;

    public Problem(Path path) throws IOException {
        try (JsonReader reader = Json.createReader(Files.newInputStream(path))) {
            JsonObject jsonObject = reader.read().asJsonObject();
            this.index = jsonObject.getInt("index");
            this.name = jsonObject.getString("name");
            this.description=jsonObject.getString("description");
            this.difficulty=jsonObject.getString("difficulty");
            this.example=toArray(jsonObject.getJsonArray("example"));
            this.tip=toArray(jsonObject.getJsonArray("tip"));
            this.givenParameter = toArray(jsonObject.getJsonArray("givenParameter"));
            this.expectedAnswer = toArray(jsonObject.getJsonArray("expectedAnswer"));
            this.timeLimit = jsonObject.getInt("timeLimit");
            this.memoryLimit = jsonObject.getInt("memoryLimit");
        } catch (IOException e) {
            throw new IOException("Problem with reading JSON from " + path, e);
        }
    }

    private String[] toArray(JsonArray jsonArray) {
        return jsonArray.getValuesAs(JsonString.class).stream()
                .map(JsonString::getString)
                .map(s -> s.replace("\\n", "\n")) // replace escaped newlines
                .map(this::replaceWithSuperscript) // replace "^" with superscript
                .toArray(String[]::new);
    }

    private String replaceWithSuperscript(String s) {
        String[][] replacements = {{"^0", "⁰"}, {"^1", "¹"}, {"^2", "²"}, {"^3", "³"}, {"^4", "⁴"}, {"^5", "⁵"}, {"^6", "⁶"}, {"^7", "⁷"}, {"^8", "⁸"}, {"^9", "⁹"}};
        for (String[] replacement : replacements) {
            s = s.replace(replacement[0], replacement[1]);
        }
        return s;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String[] getExample() {
        return example;
    }

    public String[] getTip() {
        return tip;
    }

    public String[] getGivenParameter() {
        return givenParameter;
    }

    public String[] getExpectedAnswer() {
        return expectedAnswer;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }
}
