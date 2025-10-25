import java.util.*;

public class AttendanceManager {

    private Map<String, List<String>> attendanceRecord = new HashMap<>();
    private List<Student> studentList = new ArrayList<>();

    // ✅ Add a student
    public void addStudent(String roll, String name) {
        studentList.add(new Student(roll, name));
        System.out.println("Student added successfully.");
    }

    // ✅ New Method: Mark attendance for selected students after selecting a date once
    public void markAttendanceInteractive(String date, Scanner sc) {

        if (!attendanceRecord.containsKey(date)) {
            attendanceRecord.put(date, new ArrayList<>());
        }

        while (true) {
            System.out.println("\n--- Students List ---");
            for (int i = 0; i < studentList.size(); i++) {
                System.out.println((i + 1) + ". " + studentList.get(i));
            }

            System.out.print("Select student number to mark attendance: ");
            int choice = sc.nextInt() - 1;
            sc.nextLine(); // Consume leftover newline

            if (choice < 0 || choice >= studentList.size()) {
                System.out.println("Invalid student number. Try again.");
                continue;
            }

            Student student = studentList.get(choice);
            System.out.print("Is " + student.getName() + " present? (y/n): ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("y")) {
                attendanceRecord.get(date).add(student.getRollNumber());
                System.out.println("Marked Present ✅");
            } else {
                System.out.println("Marked Absent ❌");
            }

            System.out.print("Do you want to continue marking attendance? (y/n): ");
            String more = sc.nextLine().trim().toLowerCase();
            if (!more.equals("y")) break;
        }

        System.out.println("✔ Attendance updated for date: " + date);
    }

    // ✅ Show attendance report on a particular date
    public void showAttendanceReport(String date) {
        System.out.println("\nAttendance Report for " + date + ":");
        List<String> presentRolls = attendanceRecord.getOrDefault(date, new ArrayList<>());

        for (Student s : studentList) {
            if (presentRolls.contains(s.getRollNumber())) {
                System.out.println(s + " - Present");
            } else {
                System.out.println(s + " - Absent");
            }
        }
    }
}
