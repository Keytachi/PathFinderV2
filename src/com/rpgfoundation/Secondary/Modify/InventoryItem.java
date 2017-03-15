package com.rpgfoundation.Secondary.Modify;

/**
 * Created by Brandon on 2/22/2017.
 */
public class InventoryItem {
    public enum Stackable{
        YES,
        NO
    }
    public int itemWeight;
    public Stackable itemStack;

    public InventoryItem(int itemWeight, Stackable itemStack) {
        this.itemWeight = itemWeight;
        this.itemStack = itemStack;
    }
}
