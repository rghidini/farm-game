package com.farm.model;

public class Vegetable {
    private String name;
    private int cost;
    private int weeksToGrow;
    private int sellPrice;
    private int weeksGrown;

    public Vegetable(String name, int cost, int weeksToGrow, int sellPrice) {
        this.name = name;
        this.cost = cost;
        this.weeksToGrow = weeksToGrow;
        this.sellPrice = sellPrice;
        this.weeksGrown = 0;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getWeeksToGrow() {
        return weeksToGrow;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getWeeksGrown() {
        return weeksGrown;
    }

    public void setWeeksGrown(int weeksGrown) {
        this.weeksGrown = weeksGrown;
    }

    public boolean isFullyGrown() {
        return weeksGrown >= weeksToGrow;
    }

    public void decreaseWeeksToGrow() {
        if (weeksGrown < weeksToGrow) {
            weeksGrown++;
        }
    }

    @Override
    public String toString() {
        return String.format("%s (cost: %d, weeks to grow: %d, sell price: %d)",
                name, cost, weeksToGrow, sellPrice);
    }
}
