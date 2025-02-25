import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Класс Student
class Student {
    private String name;
    private int age;
    private String gender;

    // Обычный конструктор
    public Student() {
        this.name = "Unknown";
        this.age = 0;
        this.gender = "Unknown";
    }

    // Конструктор с параметрами
    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // Билдер (статический внутренний класс)
    public static class Builder {
        private String name;
        private int age;
        private String gender;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Student build() {
            return new Student(name, age, gender);
        }
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Student " +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'';
    }
}

// Основной класс программы
public class Main {
    public static void main(String[] args) {
        // Создаем список студентов
        List<Student> students = new ArrayList<>();
        students.add(new Student("Dasha", 20, "Female"));
        students.add(new Student("Ivan", 22, "Male"));
        students.add(new Student("Sasha", 21, "Male"));
        students.add(new Student("Diana", 23, "Female"));

        // Записываем студентов в файл
        String fileName = "students.txt";
        writeStudentsToFile(students, fileName);

        // Считываем студентов из файла и выводим только Male
        List<Student> readStudents = readStudentsFromFile(fileName);
        String targetGender = "Male"; // Можно изменить на "Female" для вывода другого пола
        printStudentsByGender(readStudents, targetGender);
    }

    // Метод для записи списка студентов в файл
    private static void writeStudentsToFile(List<Student> students, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getAge() + "," + student.getGender());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для чтения списка студентов из файла
    private static List<Student> readStudentsFromFile(String fileName) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                String gender = parts[2];
                students.add(new Student(name, age, gender));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Метод для вывода студентов определенного пола
    private static void printStudentsByGender(List<Student> students, String gender) {
        for (Student student : students) {
            if (student.getGender().equals(gender)) {
                System.out.println(student);
            }
        }
    }
}