package util.database;

import Database.Problem;

import java.util.Arrays;

public class InnerProblem {
    private final int index;
    private final String name;
    private final String description;
    private final String difficulty;
    private final String[] example;
    private final String[] tip;
    private final int timeLimit;
    private final int memoryLimit;

    InnerProblem(Problem problem){
        this.index=problem.getIndex();
        this.name =problem.getName();
        this.description=problem.getDescription();
        this.difficulty=problem.getDifficulty();
        this.example=problem.getExample();
        this.tip=problem.getTip();
        this.timeLimit=problem.getTimeLimit();
        this.memoryLimit=problem.getMemoryLimit();
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

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    //    @Override
//    public String toString() {
//        StringBuilder sb=new StringBuilder();
//        return "InnerProblem{" +"\n"+
//                "index=" + index +"\n"+
//                ", name='" + name + "\n" +
//                ", description='" + description + "\n" +
//                ", difficulty='" + difficulty + "\n" +
//                ", example=" + Arrays.toString(example)+"\n" +
//                ", tip=" + Arrays.toString(tip) +"\n"+
//                ", timeLimit=" + timeLimit +"\n"+
//                ", memoryLimit=" + memoryLimit +"\n"+
//                '}';
//    }
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(index).append(". ").append(name).append("\n");
//        sb.append(difficulty).append("\n");
//        sb.append(description).append("\n\n");
//
//        for (int i = 0; i < example.length; i++) {
//            sb.append("示例 ").append(i + 1).append(":\n");
//            sb.append(example[i]).append("\n");
//        }
//
//        sb.append("\n提示：\n");
//        for (String tipItem : tip) {
//            sb.append(tipItem).append("\n");
//        }
//
//        sb.append("时间限制：").append(timeLimit).append("\n");
//        sb.append("内存限制：").append(memoryLimit).append("\n");
//        return sb.toString();
//    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(index).append(". ").append(name).append("\n\n");
        sb.append(difficulty).append("\n");
        sb.append(description).append("\n\n");

        for (int i = 0; i < example.length; i++) {
            sb.append("Example ").append(i + 1).append(":\n");
            sb.append(example[i]).append("\n\n");
        }

        sb.append("Tips:\n");
        for (String tipItem : tip) {
            sb.append(tipItem).append("\n");
        }

        sb.append("\n");

        sb.append("Time Limit: ").append(timeLimit).append("\n\n");
        sb.append("Memory Limit: ").append(memoryLimit).append("\n");
        return sb.toString();
    }
}
