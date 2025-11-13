import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AttendanceManager manager = AttendanceManager.loadData();

        while (true) {
            System.out.println("\n===== Attendance Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Show Attendance Report for a Date");
            System.out.println("4. Show All Recorded Dates");
            System.out.println("5. Attendance Summary");
            System.out.println("6. Search Student by Roll Number");
            System.out.println("7. Save Data and Exit");
            System.out.println("8. Export Readable Report");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number: ");
                    String roll = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    manager.addStudent(roll, name);
                    break;

                case 2:
                    System.out.print("Enter Date (e.g., 2025-11-05): ");
                    String date = sc.nextLine();
                    manager.markAttendanceForAll(date, sc);
                    break;

                case 3:
                    System.out.print("Enter Date (e.g., 2025-11-05): ");
                    String d = sc.nextLine();
                    manager.showAttendanceReport(d);
                    break;

                case 4:
                    manager.showAllDates();
                    break;

                case 5:
                    manager.showAttendanceSummary();
                    break;

                case 6:
                    System.out.print("Enter Roll Number to Search: ");
                    String r = sc.nextLine();
                    manager.searchAttendanceByRoll(r);
                    break;

                case 7:
                    manager.saveData();
                    System.out.println("Exiting... Goodbye!");
                    return;

                case 8:
                    manager.exportReadableReport();
                    break;

                default:
                    System.out.println("Invalid choice! Please enter between 1–8.");
            }
        }
    }
}
