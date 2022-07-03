
/*
 * This program (v. 0.2) takes a list of student names from the user
 * Randomizes that list of names,
 * And creates pairs for a group activity
 */
import java.util.*;

public class randomGroups {
    public static void shuffle(List<?> list) {
        Random random = new Random();
        Object[] arr = list.toArray();

        for (int i = list.size(); i > 1; i--) {
            int j = random.nextInt(i);
            Object tmp = arr[i - 1];
            arr[i - 1] = arr[j];
            arr[j] = tmp;
        }

        ListIterator it = list.listIterator();
        for (int i = 0; i < arr.length; i++) {
            it.next();
            it.set(arr[i]);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>(Arrays.asList());
        Scanner input = new Scanner(System.in);

        System.out.println(
                "Enter a student's name and then press enter to provide additional names. When finished, type 'stop'");

        while (true) {
            String studentName = input.nextLine();
            if ("stop".equalsIgnoreCase(studentName)) {
                break;
            }
            list.add(studentName);
        }

        System.out.println("Entered Students: " + list);
        input.close();

        int s = 0;
        while (s < 10) {
            shuffle(list);
            s++;
        }
        System.out.println("Shuffling students.");
        Thread.sleep(5000);
        System.out.println("Below are your group assignments for the team presentations");
        System.out.print("\n\t" + "Group" + "\t\t" + "Members");

        int i = 0;
        grouping: while (i < list.size()) {
            int groupNumber = i / 2 + 1;
            System.out.print("\n\t" + "Group " + groupNumber + ":\t" + list.get(i) + " & " + list.get(i + 1));
            i += 2;
            if (i == list.size() - 1) {
                System.out.print(" & " + list.get(i));
                break;
            } else {
                continue grouping;
            }
        }
    }
}