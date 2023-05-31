

//Here we have implemented the Item class, which has Name,Type, Stats, and Value of item.
//Stats has been declared as a 1D array since it will take as input values for ATK, DEF and DUR
//Just a simple class with getters and setters
public class Item {
    private String name;
    private String type;
    private int[] stats;

    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int[] getStats() {
        return stats;
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Item(String name, String type, int[] stats, Double value) {
        this.name = name;
        this.type = type;
        this.stats = stats;
        this.value=value;
    }


}