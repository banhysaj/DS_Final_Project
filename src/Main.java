import java.util.Scanner;
import java.util.*;

class GameInventory {
    public static void main(String[] args) {
        maincall();

    }


    // Helper method to check if the player has an Upgrade Potion in their inventory
    private static boolean hasUpgradePotion(Inventory inventory) {
        List<Item> items = inventory.getItems();
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase("Upgrade Potion") && item.getType().equalsIgnoreCase("Potion")) {
                return true;
            }
        }
        return false;
    }

    public static void maincall() {
        Inventory inventory = new Inventory(20, 1000);
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have " + inventory.getCoins() + " coins.");

        while (true) {
            System.out.println("1. Add item to inventory");
            System.out.println("2. Remove item from inventory");
            System.out.println("3. Display inventory");
            System.out.println("4. Sort inventory by name");
            System.out.println("5. Sort inventory by type");
            System.out.println("6. Filter inventory by type");
            System.out.println("7. Filter inventory by value");
            System.out.println("8. Upgrade item");
            System.out.println("9. I want to earn some money");
            System.out.println("10. Quit\n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = scanner.next();
                    System.out.print("Enter item type: ");
                    scanner.nextLine();
                    String type = scanner.nextLine();
                    System.out.print("Enter item stats: \n");
                    int[] stats = new int[3];
                    System.out.print("Attack: ");
                    stats[0] = scanner.nextInt();
                    System.out.print("Defense: ");
                    stats[1] = scanner.nextInt();
                    System.out.print("Durability: ");
                    stats[2] = scanner.nextInt();
                    System.out.println("Enter item value: ");
                    Double value = scanner.nextDouble();

                    Item item = new Item(name, type, stats, value);
                    boolean added = inventory.addItem(item);

                    if (added) {
                        System.out.println("Item added to inventory.");
                    } else {
                        System.out.println("Inventory is full. Item not added.");
                    }
                    break;

                case 2:
                    System.out.print("Enter item name to remove: ");
                    String itemName = scanner.next();
                    Item itemToRemove = inventory.getItemByName(itemName);

                    if (itemToRemove != null) {
                        boolean removed = inventory.removeItem(itemToRemove);

                        if (removed) {
                            System.out.println("Item removed from inventory.");
                        } else {
                            System.out.println("Failed to remove item from inventory.");
                        }
                    } else {
                        System.out.println("Item not found in inventory.");
                    }
                    break;

                case 3:
                    System.out.println("Inventory:");
                    for (Item inventoryItem : inventory.getItems()) {
                        System.out.print(inventoryItem.getName() + " (" +
                                inventoryItem.getType() + ") - Stats: [ ");
                        String[] statLabels = {"Attack", "Defense", "Durability"}; // array of labels
                        for (int i = 0; i < inventoryItem.getStats().length; i++) {
                            System.out.print(statLabels[i] + ": " + inventoryItem.getStats()[i] + " ");
                        }
                        System.out.println("] - Value: " + inventoryItem.getValue()+ "\n");
                    }
                    System.out.println("Coins: " + inventory.getCoins());
                    break;

                case 4:
                    inventory.sortByName();
                    System.out.println("Inventory sorted by name.");
                    break;

                case 5:
                    inventory.sortByType();
                    System.out.println("Inventory sorted by type.");
                    break;

                case 6:
                    System.out.print("Enter item type to filter: ");
                    String filterType = scanner.next();
                    System.out.println("Filtered inventory:");
                    for (Item filteredItem : inventory.filterByType(filterType)) {
                        System.out.println(filteredItem.getName() + " (" +
                                filteredItem.getType() + ") - Stats: " + filteredItem.getStats());
                    }
                    break;

                case 7:
                    System.out.print("Enter item value to filter: ");
                    double filterValue = scanner.nextDouble();
                    System.out.println("Filtered inventory:");
                    for (Item filteredItem : inventory.filterByValue(filterValue)) {
                        System.out.println(filteredItem.getName() + " (" +
                                filteredItem.getType() + ") - Stats: " + filteredItem.getStats());
                    }
                    break;

                case 8:
                    System.out.print("Enter the name of the item you wish to upgrade: ");
                    String itemToUpgradeName = scanner.next();
                    Item itemToUpgrade = inventory.getItemByName(itemToUpgradeName);

                    if (itemToUpgrade != null) {
                        // Check if player has enough coins inventory
                        if (inventory.getCoins() >= 100) {
                            inventory.addToUpgradeQueue(itemToUpgrade);
                            inventory.processUpgradeQueue();
                             inventory.setCoins(inventory.getCoins()-100); // Deduct the cost of upgrade from player's coins
                            System.out.println("Item upgraded successfully.");
                        } else {
                            System.out.println("Insufficient coins.");
                        }
                    } else {
                        System.out.println("Item not found in inventory.");
                    }
                    break;

                case 9:
                    QuizGame();
                    break;


                case 10:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }


                }
              }
        public static void QuizGame(){
            Inventory inventory = new Inventory(20, 1000);
            Scanner scanner1 = new Scanner(System.in);
            Queue<Integer> consecutiveAnswers = new LinkedList<>();
            int counter = 0;
            while (counter < 4) {
                Random random = new Random();
                switch (random.nextInt(2)+1) {
                    case 1:
                        int n = random.nextInt(10) + 1;
                        System.out.println("What is the " + (n+1) + " number in the Fibonacci sequence?");
                        int guess = scanner1.nextInt();
                        int answer = Inventory.fibQ(n);
                        if (guess == answer) {
                            System.out.println("Correct!!");
                            inventory.setCoins((inventory.getCoins()) + 500);
                            consecutiveAnswers.offer(1);

                        } else {
                            System.out.println("Hah loser, WRONG!!");
                            consecutiveAnswers.clear();
                        }
                        break;

                    case 2:
                        int a = random.nextInt(20) + 1;
                        int b = random.nextInt(20) + 1;
                        System.out.println("What is " + a + "*" + b + "?");
                        int guess1 = scanner1.nextInt();
                        int answer1 = a * b;
                        if (guess1 == answer1) {
                            System.out.println("Correct!!");
                            inventory.setCoins(inventory.getCoins() + 500);
                            consecutiveAnswers.offer(1);

                        } else {
                            System.out.println("Hah loser, WRONG!!");
                            consecutiveAnswers.clear();
                        }
                        break;

                    case 3:
                        System.out.println("If the earth is flat, will asians eat my cat? (Please choose a number for the answer)");
                        System.out.println("1. Yes lol");
                        System.out.println("2. No");
                        System.out.println("3. That is a racial stereotype you should be ashamed");
                        int guess2 = scanner1.nextInt();
                        if (guess2 == 1) {
                            System.out.println("Correct!!");
                            inventory.setCoins(inventory.getCoins() + 500);
                            consecutiveAnswers.offer(1);

                        } else {
                            System.out.println("Hah loser, WRONG!!");
                            consecutiveAnswers.clear();
                        }
                        break;
                }
                if(consecutiveAnswers.size() >= 3){
                    System.out.println("You got 3 questions right in a row");
                    inventory.setCoins(inventory.getCoins() + 750);
                    System.out.println("You now have: " + inventory.getCoins() + " coins.");
                    consecutiveAnswers.clear();
                }
                counter++;
            }
        }
}





