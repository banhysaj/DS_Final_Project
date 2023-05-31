//Here we have all the main methods

import java.util.*;
public class Inventory {
    private List<Item> items; //List of items
    private int capacity; //Inventory Capacity (The bigger the better)
    private Queue<Item> upgradeQueue; //We declared a queue as we will need it later

    private int coins;

    public Inventory(int capacity, int coins) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
        this.upgradeQueue = new LinkedList<>();
        this.coins = coins;
    }

    public boolean addItem(Item item) {
        if (items.size() < capacity) {
            items.add(item);
            return true;
        }
        return false;
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCoins(){
        return coins;
    }
    public void setCoins(int coins){
        this.coins=coins;
    }

    public boolean isFull() {
        return items.size() == capacity;
    }

    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {  //equalsIgnoreCase compares 2 strings, no matter the lower or uppercase letters
                return item;
            }
        }
        return null;
    }

    public void sortByName() {
        Collections.sort(items, Comparator.comparing(Item::getName)); //We sort all items based on the name
    }

    public void sortByType() {
        Collections.sort(items, Comparator.comparing(Item::getType)); //Based by Type
    }

    public List<Item> filterByType(String type) {  //Here we have a method that filters the items based on the type
        List<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getType().equalsIgnoreCase(type)) { //If we find the type entered by user anywhehere in the inventory
                filteredItems.add(item);                 // we simply add it to FilteredItems and then return FilteredItems
            }
        }
        return filteredItems;
    }
    public List<Item> filterByValue(Double value){        //Same logic here
        List<Item> filteredItems = new ArrayList<>();
        for(Item item: items){
            if(item.getValue().equals(value)){
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

    public void addToUpgradeQueue(Item item) {     //With this method we can add items to the queue and they will stay there until theyre processed
        if (upgradeQueue.size() < 5) {             //We can only have 5 items in the queue at the same time
            upgradeQueue.add(item);
            System.out.println("Added " + item.getName() + " to upgrade queue.");
        } else {
            System.out.println("Upgrade queue is full. Item not added.");
        }
    }


    public void upgrade(Item item) {        //The Upgrade method increases the stats of the item by 10%
        int[] stats = item.getStats();
        for (int i = 0; i < stats.length; i++) {
            stats[i] *= 1.1;
        }
        item.setStats(stats);

        Double value = item.getValue();       // Increases the value by 12%
        value *= 1.2;
        item.setValue(value);
    }
    public void processUpgradeQueue() {        //The method process upgrade queue will now upgrade any items we had added into the queue
        int queueSize = upgradeQueue.size();
        if (queueSize == 0) {
            System.out.println("Upgrade queue is empty.");
        } else {
            System.out.println("Processing upgrade queue...");
            String[] statLabels = {"Attack", "Defense", "Durability"};
            for (int i = 0; i < queueSize; i++) {
                Item item = upgradeQueue.poll(); //This method will remove the head (1st item) of the queue
                upgrade(item);
                System.out.println("Upgraded " + item.getName() + ". New stats: " + Arrays.toString(item.getStats()));
            }
        }
    }

    private void mergeSort(List<Item> itemList, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(itemList, left, mid);
            mergeSort(itemList, mid + 1, right);

            merge(itemList, left, mid, right);
        }
    }
    private void merge(List<Item> itemList, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        List<Item> leftList = new ArrayList<>();
        List<Item> rightList = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            leftList.add(itemList.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightList.add(itemList.get(mid + 1 + j));
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftList.get(i).getName().compareTo(rightList.get(j).getName()) <= 0) {
                itemList.set(k, leftList.get(i));
                i++;
            } else {
                itemList.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            itemList.set(k, leftList.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            itemList.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
    public static int fibQ(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int prev = 0;
        int current = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + current;
            prev = current;
            current = next;
        }
        return current;
    }
}
