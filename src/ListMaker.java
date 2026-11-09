import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> myList = new ArrayList<>();
        boolean done = false;
        String cmd;

        do {
            displayList(myList);
            cmd = SafeInput.getRegExString(in,
                    "Enter a command: A - Add, D - Delete, I - Insert, P - Print, Q - Quit",
                    "[AaDdIiPpQq]").toUpperCase();

            switch (cmd) {
                case "A":
                    addItem(in, myList);
                    break;
                case "D":
                    deleteItem(in, myList);
                    break;
                case "I":
                    insertItem(in, myList);
                    break;
                case "P":
                    printList(myList);
                    break;
                case "Q":
                    done = quit(in);
                    break;
            }
        } while (!done);
    }

    // Shows the current list above the menu so the user can see what they're doing
    private static void displayList(ArrayList<String> list) {
        System.out.println("\n--- Current List ---");
        if (list.isEmpty()) {
            System.out.println("(empty)");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
        System.out.println("--------------------");
    }

    // P - display the list
    private static void printList(ArrayList<String> list) {
        displayList(list);
    }

    // A - add to the end of the list
    private static void addItem(Scanner in, ArrayList<String> list) {
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to add");
        list.add(item);
    }

    // D - delete the item the user selects by its displayed number
    private static void deleteItem(Scanner in, ArrayList<String> list) {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }
        int num = SafeInput.getRangedInt(in, "Enter the number of the item to delete", 1, list.size());
        list.remove(num - 1); // convert 1-based to 0-based
    }

    // I - insert at the location the user selects
    private static void insertItem(Scanner in, ArrayList<String> list) {
        int num = SafeInput.getRangedInt(in, "Enter the location to insert at", 1, list.size() + 1);
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to insert");
        list.add(num - 1, item); // convert 1-based to 0-based
    }

    // Q - confirm before quitting; returns true if the user wants to exit
    private static boolean quit(Scanner in) {
        return SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
    }
}