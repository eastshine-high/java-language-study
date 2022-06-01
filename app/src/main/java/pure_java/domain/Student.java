package pure_java.domain;

public class Student {
    private String name;
    private int score;
    private int grade;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
        this.grade = 0; //default
    }

    public Student(String name, int score, int grade) {
        this.name = name;
        this.score = score;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getGrade() {
        return grade;
    }
}
