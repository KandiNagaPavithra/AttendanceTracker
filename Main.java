import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AttendanceManager manager = new AttendanceManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Attendance Tracker ---");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. View Attendance Report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number: ");
                    String roll = sc.nextLine();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    manager.addStudent(roll, name);
                    break;
                case 2:
                    System.out.print("Enter Date (e.g., 2025-06-22): ");
                    String date = sc.nextLine();
                    System.out.print("Enter Roll Number to mark present: ");
                    String r = sc.nextLine();
                    sc.nextLine();
                    manager.markAttendance(date, r);
                    break;
                case 3:
                    System.out.print("Enter Date (e.g., 2025-06-22): ");
                    String d = sc.nextLine();
                    manager.showAttendanceReport(d);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
