import java.util.*;

public class AttendanceManager {
    private Map<String, List<String>> attendanceRecord = new HashMap<>();
    private List<Student> studentList = new ArrayList<>();

    public void addStudent(String roll, String name) {
        studentList.add(new Student(roll, name));
        System.out.println("Student added.");
    }

    public void markAttendance(String date, String roll) {
        if (!attendanceRecord.containsKey(date)) {
            attendanceRecord.put(date, new ArrayList<>());
        }
        attendanceRecord.get(date).add(roll);
        System.out.println("Attendance marked for roll number: " + roll);
    }

    public void showAttendanceReport(String date) {
        System.out.println("Attendance on " + date + ":");
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
