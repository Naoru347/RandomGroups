
/* 
 * This program (v. 0.5) takes a list of student names from the user
 * Saves those names into a CSV file for later use
 * Randomizes that list of names,
 * And creates pairs for a group activity
 * 
 * Current interactivity also allows a use to select file or manual input
 * NOTE: file input coming in a future version
 */
import java.util.*;
import java.io.FileWriter;

public class randomGroups {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>(Arrays.asList());

        // method call to inputSelect
        inputSelect();
        // tryCatch for collecting data from user to populate arrayList and write to CSV
        // file.
        try {
            FileWriter save = new FileWriter("save_list.csv");
            while (true) {
                String studentName = input.nextLine();
                // control statement to exit data input
                if ("stop".equalsIgnoreCase(studentName)) {
                    break;
                }
                if ("y".equalsIgnoreCase(studentName)) {
                    // here you'll call the method to read from a file and populate the array list
                    // that way
                    System.out.println("Please enter file name, including the file type (e.g., .CSV).");
                    // method call to file reader listImport
                    break;
                } else if ("n".equalsIgnoreCase(studentName)) {
                    System.out.println("At present, only .CSV files are accepted." +
                            "\nPlease reformat your file and try again.");
                    System.exit(0);
                }
                // adds student name to the arrayList and to the CSV file, appending a comma
                list.add(studentName);
                save.write(studentName + ",");
            }
            // summarizes the student list and closes the file and inputStream
            System.out.println("Entered Students: " + list + "\nSaved to file: save_list.csv.");
            input.close();
            save.close();
        } catch (Exception e) {
        }
        // runs shuffle method 100x
        int s = 0;
        while (s < 100) {
            shuffle(list);
            s++;
        }
        // prints dummy 'processing' message, pauses 5000ms, formats output
        System.out.println("\nShuffling students.");
        Thread.sleep(5000);
        System.out.println("\nBelow are your group assignments:");
        System.out.print("\n\t" + "Group" + "\t\t" + "Members");

        // pulls student information from shuffled arrayList 2 at a time and prints it
        // for the user
        int i = 0;
        grouping: while (i < list.size()) {
            int groupNumber = i / 2 + 1;
            System.out.print("\n\t" + "Group " + groupNumber + ":\t" + list.get(i) + " & " + list.get(i + 1));
            i += 2;
            // adds a third person to the final group if list.size == odd
            if (i == list.size() - 1) {
                System.out.print(" & " + list.get(i));
                break;
            } else {
                continue grouping;
            }
        }
    }

    // method to shuffle the passed arrayList
    public static void shuffle(List<?> list) {
        Random random = new Random();
        Object[] arr = list.toArray();

        // Sets iteration to == list.size and decrements by 1 each time.
        for (int i = list.size(); i > 1; i--) {
            // pulls from a random index location, places it in a new location in a holding
            // array
            int j = random.nextInt(i);
            Object tmp = arr[i - 1];
            arr[i - 1] = arr[j];
            arr[j] = tmp;
        }

        // ListIterator method to take items in holding array and pass them back to the
        // main method
        ListIterator it = list.listIterator();
        for (int i = 0; i < arr.length; i++) {
            it.next();
            it.set(arr[i]);
        }
    }

    // put a method here that trys to have the user select an input method FROMfile
    // or MANUAL
    public static void inputSelect() throws InterruptedException {
        System.out.println("Enter 'F' to load class information from the CSV file, " +
                "\n Enter 'M' to manually enter your class information: ");

        String methodSelection = input.nextLine();
        String choice = methodSelection.toLowerCase();
        switch (choice) {
            case "f":
                System.out.println("Is file the file a CSV (comma separate values) file?" +
                        "\nEnter Y for yes or N for no");
                break;
            case "m":
                System.out.println("Enter a student's name and then press enter to provide additional names." +
                        " When finished, type 'stop'");
                break;
            default:
                System.out.println("Invalid selection." + "\t\tExiting program.");
                Thread.sleep(3000);
                System.exit(0);
        }
    }
    /**
     * Here you'll put your file import method listImport(). It'll need to import
     * data from the
     * CSV file, toss it into the
     * arrayList and then pass that information back to the main method to create
     * the groups and spit out information.
     */
}