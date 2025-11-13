import java.io.*;
import java.util.*;

public class AttendanceManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, List<String>> attendanceRecord = new HashMap<>();
    private List<Student> studentList = new ArrayList<>();

    // ✅ Permanent save path (creates a real folder under your home directory)
    private static final String DATA_FOLDER = System.getProperty("user.home") + File.separator + "AttendanceTrackerData";
    private static final String FILE_PATH = DATA_FOLDER + File.separator + "attendance_data.ser";

    public AttendanceManager() {}

    // Add a new student
    public void addStudent(String roll, String name) {
        studentList.add(new Student(roll, name));
        System.out.println("✅ Student added: " + name);
    }

    // Mark attendance for all students for a specific date
    public void markAttendanceForAll(String date, Scanner sc) {
        if (!attendanceRecord.containsKey(date)) {
            attendanceRecord.put(date, new ArrayList<>());
        }

        System.out.println("\nMark attendance for date: " + date);
        for (Student s : studentList) {
            System.out.print("Is " + s.getName() + " (Roll " + s.getRollNumber() + ") present? (y/n): ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                attendanceRecord.get(date).add(s.getRollNumber());
            }
        }
        System.out.println("✅ Attendance recorded for " + date);
    }

    // Show attendance report for a given date
    public void showAttendanceReport(String date) {
        System.out.println("\n📅 Attendance Report on " + date + ":");
        List<String> presentRolls = attendanceRecord.getOrDefault(date, new ArrayList<>());

        if (studentList.isEmpty()) {
            System.out.println("No students available!");
            return;
        }

        for (Student s : studentList) {
            if (presentRolls.contains(s.getRollNumber())) {
                System.out.println(s + " - Present");
            } else {
                System.out.println(s + " - Absent");
            }
        }
    }

    // Show all recorded dates
    public void showAllDates() {
        if (attendanceRecord.isEmpty()) {
            System.out.println("\nNo attendance records found.");
            return;
        }

        System.out.println("\n📚 All Recorded Dates:");
        for (String date : attendanceRecord.keySet()) {
            System.out.println("- " + date);
        }
    }

    // ✅ Attendance summary (present/absent counts)
    public void showAttendanceSummary() {
        System.out.println("\n===== Attendance Summary =====");
        int totalDays = attendanceRecord.size();

        if (totalDays == 0) {
            System.out.println("No attendance data available yet.");
            return;
        }

        for (Student s : studentList) {
            int presentCount = 0;
            for (List<String> rolls : attendanceRecord.values()) {
                if (rolls.contains(s.getRollNumber())) {
                    presentCount++;
                }
            }
            int absentCount = totalDays - presentCount;
            System.out.println(s + " -> Present: " + presentCount + " | Absent: " + absentCount);
        }
    }

    // ✅ Search attendance for a specific student (by roll)
    public void searchAttendanceByRoll(String roll) {
        System.out.println("\nSearching attendance for Roll: " + roll);
        if (attendanceRecord.isEmpty()) {
            System.out.println("No attendance records found.");
            return;
        }

        for (Map.Entry<String, List<String>> entry : attendanceRecord.entrySet()) {
            String date = entry.getKey();
            List<String> presentRolls = entry.getValue();
            String status = presentRolls.contains(roll) ? "Present" : "Absent";
            System.out.println(date + " → " + status);
        }
    }

    // ✅ NEW FEATURE: Export all attendance data in readable format
    public void exportReadableReport() {
        try {
            File dir = new File(DATA_FOLDER);
            if (!dir.exists()) dir.mkdirs();

            String exportPath = DATA_FOLDER + File.separator + "attendance_report.txt";
            try (PrintWriter writer = new PrintWriter(new FileWriter(exportPath))) {
                writer.println("Date, Roll Number, Name, Status");

                for (String date : attendanceRecord.keySet()) {
                    List<String> presentRolls = attendanceRecord.get(date);
                    for (Student s : studentList) {
                        String status = presentRolls.contains(s.getRollNumber()) ? "Present" : "Absent";
                        writer.println(date + ", " + s.getRollNumber() + ", " + s.getName() + ", " + status);
                    }
                }
            }
            System.out.println("📝 Readable report exported successfully at:\n" + exportPath);
        } catch (IOException e) {
            System.out.println("❌ Error exporting report: " + e.getMessage());
        }
    }

    // ✅ Save data (binary format)
    public void saveData() {
        try {
            File dir = new File(DATA_FOLDER);
            if (!dir.exists()) dir.mkdirs();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                oos.writeObject(this);
            }

            System.out.println("💾 Data saved successfully at: " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
    }

    // ✅ Load data (binary format)
    public static AttendanceManager loadData() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No saved data found. Starting fresh...");
            return new AttendanceManager();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            System.out.println("📂 Data loaded successfully from: " + FILE_PATH);
            return (AttendanceManager) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Error loading data: " + e.getMessage());
            return new AttendanceManager();
        }
    }
}
