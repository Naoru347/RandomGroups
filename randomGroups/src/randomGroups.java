
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

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
                    System.out.println("Entered Students: " + list + "\nSaved to file: save_list.csv.");
                    break;
                }
                if ("y".equalsIgnoreCase(studentName)) {
                    System.out.println("Please enter file name with the CSV extension.");
                    list = listImport();
                    System.out.println("Imported students are: " + list);
                    break;
                } else if ("n".equalsIgnoreCase(studentName)) {
                    System.out.println("Files must be in CSV format." +
                            "\nExiting Program.");
                    System.exit(-1);
                }
                // adds student name to the arrayList and to the CSV file, appending a comma
                list.add(studentName);
                save.write(studentName + ",");
            }
            // summarizes the student list and closes the file and inputStream
            input.close();
            save.close();
        } catch (Exception e) {
        }
        // runs shuffle method 100x

        for (int s = 0; s < 100; s++) {
            shuffle(list);

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

    // A method to read user-supplied data from a CSV file into the ArrayList for
    // shuffling
    public static List<String> listImport() throws IOException {

        List<String> list = new ArrayList<>(Arrays.asList());
        String fileName = input.nextLine();
        Scanner fileReader = new Scanner(new File(fileName));
        fileReader.useDelimiter(",");

        while (fileReader.hasNext()) {
            String studentFileName = fileReader.next();
            list.add(studentFileName);

        }
        return list;

    }

}
