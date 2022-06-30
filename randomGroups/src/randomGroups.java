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
        List<String> list = new ArrayList<String>(
                Arrays.asList("Bandar", "Nada", "Cookie", "Tomo", "Eun Hye", "Gemma"));
        int s = 0;
        while (s < 10) {
            shuffle(list);
            s++;
        }
        System.out.println("Shuffling students.");
        Thread.sleep(5000);
        System.out.println("Below are your group assignments for the team presentations");
        System.out.println("\n\t" + "Group" + "\t\t" + "Members");

        int i = 0;
        while (i < list.size()) {
            int groupNumber = i / 2 + 1;
            System.out.println("\t" + "Group " + groupNumber + ":\t" + list.get(i) + " & " + list.get(i + 1));
            i += 2;
        }
    }
}