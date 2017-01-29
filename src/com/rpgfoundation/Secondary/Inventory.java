package com.rpgfoundation.Secondary;

/**
 * Created by Brandon on 1/28/2017.
 */
public class Inventory{

    private final int max_BagSpace = 20;
    private int current_BagUsage;
    private int max_BagWeight;
    private int current_BagWeight;


    public int getCurrent_BagUsage() {
        return current_BagUsage;
    }
    public void setCurrent_BagUsage(int current_BagUsage) {
        this.current_BagUsage = current_BagUsage;
    }
    public int getCurrent_BagWeight() {
        return current_BagWeight;
    }
    public void setCurrent_BagWeight(int current_BagWeight) {
        this.current_BagWeight = current_BagWeight;
    }

    public int getMax_BagSpace() {
        return max_BagSpace;
    }

    public int getMax_BagWeight() {
        return max_BagWeight;
    }

    public void setMax_BagWeight(int max_bagWeight)
    {
        this.max_BagWeight = max_bagWeight;
    }
}
