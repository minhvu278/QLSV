import java.io.FileWriter;

public class Example {
    public static void main(String args[]) {
        try {
            FileWriter fw = new FileWriter("/home/vu/IdeaProjects/QLSV/src/Data.txt");
            fw.write("Welcome to java.");
            fw.write("Welcome to java1.");
            fw.write("Welcome to java2.");
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Success...");
    }
}
