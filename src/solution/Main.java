package solution;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static solution.Constant.*;

public class Main {
    public static void main(String[] args) {
        // Create empty list student
        List<Student> students = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // Print menu
        // Switch case to choice.
        int choice;
        while (true) {
            printMenu();
            System.out.print("Please enter your choice: ");
            choice = sc.nextInt();
            readRedundantChar(sc);

            switch (choice) {
                case PRINT_LIST:
                    printList(students);
                    printWaitMessage(sc);
                    break;
                case INSERT_STUDENT:
                    insertStudent(students, sc);
                    printWaitMessage(sc);
                    break;
                case REMOVE_STUDENT:
                    students = removeStudent(students, sc);
                    break;
                case UPDATE_STUDENT:
                    updateStudent(students, sc);
                    break;
                case FIND_STUDENT:
                    findStudent(students, sc);
                case LOAD_STUDENT:
                    loadStudents(students);
                    break;
                case SAVE_STUDENT:
                    saveStudents(students);
                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Wrong choice. Please enter again!");
            }
        }
    }

    private static void findStudent(List<Student> students, Scanner sc) {
        System.out.println("** FIND STUDENTS **");

        System.out.println("1. Find by id");
        System.out.println("2. Find by name");
        System.out.println("3. Find by math score");

        System.out.print("- Enter find mode: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case FIND_MODE_ID:
                findById(students, sc);
                break;
            case FIND_MODE_NAME:
                findByNameStudent(students, sc);
                break;
            case FIND_MODE_MATH_SCORE:
                findByScore(students, sc);
                break;
        }
    }

    private static void findByScore(List<Student> students, Scanner sc) {
        System.out.print("- Enter student match score");
        int mathScore = sc.nextInt();

        List<Student> foundList = new ArrayList<>();
        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getMath() == mathScore) {
                found = true;
                foundList.add(students.get(i));
            }
        }
        if (!found) {
            System.out.println("Not found student with name: " + mathScore);
        }

        printList(foundList);
    }

    private static void findByNameStudent(List<Student> students, Scanner sc) {
        System.out.print("- Enter student name: ");
        String search = sc.nextLine();

        List<Student> foundList = new ArrayList<>();
        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            if (Objects.equals(students.get(i).getName(), search)) {
                found = true;
                foundList.add(students.get(i));
            }

            if (!found) {
                System.out.println("Not found student with name: " + search);
            }

            printList(foundList);
        }
    }

    private static void findById(List<Student> students, Scanner sc) {
        System.out.print("- Enter id want to find: ");
        int findId = sc.nextInt();
        sc.nextLine();

        List<Student> foundList = new ArrayList<>();

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == findId) {
                found = true;
                foundList.add(students.get(i));
            }
        }
        if (!found) {
            System.out.println("Not found student with id " + findId);
            return;
        }


        printList(foundList);
    }

    private static void loadStudents(List<Student> students) {
        System.out.println("** LOADING STUDENTS **");
        try {
            String currentPath = Paths.get("").toAbsolutePath().toString();
            Path path = Paths.get(currentPath + "/src/students.txt");
            List<String> lines = Files.readAllLines(path);


            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                if (line.startsWith("#")) {
                    System.out.println(">> Ignore line " + i + " due to start with #");
                    continue;
                }

                String[] raw = line.split(",");
                if (raw.length != 3) {
                    System.out.println("- Line " + i + " wrong format");
                }

                int id = Integer.parseInt(raw[0]);
                String name = raw[1];
                int math = Integer.parseInt(raw[2]);

                Student newStudent = new Student(id, name, math);

                students.add(newStudent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveStudents(List<Student> students) {
        System.out.println("** Saving students.... **");

        try {
            String currentPath = Paths.get("").toAbsolutePath().toString();
            FileWriter fw = new FileWriter(currentPath + "/src/students.txt");

            StringBuilder res = new StringBuilder();
            for (Student s : students) {
                res.append(String.format("%d,%s,%d\n", s.getId(), s.getName(), s.getMath()));
            }

            fw.write(res.toString());
            fw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void updateStudent(List<Student> students, Scanner sc) {
        System.out.println("** Update student **");

        System.out.print("- Enter id of student to update: ");
        int idUpdate = sc.nextInt();
        readRedundantChar(sc);

        System.out.print("- Enter new name: ");
        String newName = sc.nextLine();

        System.out.print("- Enter new math score: ");
        int newMath = sc.nextInt();
        readRedundantChar(sc);

        // Find & update
        boolean found = false;
        for (Student s : students) {
            if (s.getId() == idUpdate) {
                s.setName(newName);
                s.setMath(newMath);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Not found student with id: " + idUpdate);
            return;
        }

        System.out.println("Successfully update student with id: " + idUpdate);
    }

    private static void readRedundantChar(Scanner sc) {
        sc.nextLine();
    }

    private static List<Student> removeStudent(List<Student> students, Scanner sc) {
        System.out.println("** Remove student **");
        System.out.println("1. Remove by id");
        System.out.println("2. Remove by name");
        System.out.println("3. Remove by math score");

        System.out.print("- Enter remove mode: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case REMOVE_MODE_ID:
                removeById(students, sc);
                return students;
            case REMOVE_MODE_NAME:
                return removeByName(students, sc);
            case REMOVE_MODE_MATH_SCORE:
                removeByScore(students, sc);
                break;
        }

        return students;
    }

    private static void removeByScore(List<Student> students, Scanner sc) {
        System.out.print("- Enter student math score: ");

        int mathScore = sc.nextInt();
        sc.nextLine();

        int pos = 0;
        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getMath() == mathScore) {
                pos = i;
                found = true;
            }
        }

        if (!found) {
            System.out.println("Not found student with id " + mathScore);
            return;
        }

        students.remove(pos);
        System.out.println("Remove student " + mathScore + " success");

    }

    private static List<Student> removeByName(List<Student> students, Scanner sc) {
        System.out.print("- Enter student name: ");
        String search = sc.nextLine();
        search = search.toLowerCase();

        List<Student> newStudents = new ArrayList<>();
        boolean found = false;
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            String studentName = s.getName().toLowerCase();

            if (studentName.contains(search)) {
                found = true;
                continue;
            }

            newStudents.add(s);
        }

        if (!found) {
            System.out.println("Not found student with name: " + search);
            return students;
        }

        System.out.println("Successfully remove " + (students.size() - newStudents.size()) + " with name " + search);
        return newStudents;
    }

    private static void removeById(List<Student> students, Scanner sc) {
        System.out.print("- Enter id want to remove: ");
        int removeId = sc.nextInt();
        sc.nextLine();

        int pos = 0;
        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == removeId) {
                pos = i;
                found = true;
            }
        }

        if (!found) {
            System.out.println("Not found student with id " + removeId);
            return;
        }

        students.remove(pos);
        System.out.println("Remove student " + removeId + " success");
    }

    private static void insertStudent(List<Student> students, Scanner sc) {
        System.out.println("** Enter student information **");
        System.out.print("- Enter name: ");
        String name = sc.nextLine();
        System.out.print("- Enter math: ");
        int math = sc.nextInt();
        readRedundantChar(sc);

        // Find largest id
        int largestId = 0;
        for (Student s : students) {
            if (s.getId() > largestId) {
                largestId = s.getId();
            }
        }

        int newId = largestId + 1;
        Student newStudent = new Student(newId, name, math);
        students.add(newStudent);

        System.out.println("Added " + name + " to student list successfully");
    }

    private static void printList(List<Student> students) {
        System.out.println("** Student list **");
        StringBuilder res = new StringBuilder();
        res.append(String.format(STUDENT_FORMAT,
                "ID",
                "Name",
                "Math"
        ));
        for (Student student : students) {
            res.append(String.format(STUDENT_FORMAT,
                    student.getId(),
                    student.getName(),
                    student.getMath()
            ));
        }

        System.out.println(res);
        System.out.println("- Total: " + students.size());
        System.out.println("");

    }

    private static void printMenu() {
        System.out.println("** MENU **");
        System.out.println("1. Print list student");
        System.out.println("2. Insert student");
        System.out.println("3. Remove student");
        System.out.println("4. Find student");
        System.out.println("5. Load student list");
        System.out.println("6. Save student to disk");
        System.out.println("7. Update student");
        System.out.println("8. Exit");
    }

    private static void printWaitMessage(Scanner sc) {
        System.out.println("Press any key to continue");
        sc.nextLine();
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}
