package solution;

public class Student {
    private int id;
    private String name;
    private int math;

    public Student(int id, String name, int math) {
        this.id = id;
        this.name = name;
        this.math = math;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }
}
