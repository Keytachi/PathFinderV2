package com.rpgfoundation.Secondary;


import com.rpgfoundation.Gear.Weapon;
import com.rpgfoundation.Secondary.Modify.InventoryItem;

import java.util.ArrayList;

/**
 * Created by Brandon on 1/28/2017.
 */
public class Inventory{

    private ArrayList<InventoryItem> bag;

    public Inventory()
    {
        this.max_BagSpace = 20;
        this.current_BagUsage = 0;
        this.bag = new ArrayList<>();
    }

    private int max_BagSpace;
    private int current_BagUsage;

    public void addItem(InventoryItem o)
    {
        if (!(getCurrent_BagUsage() >= getMax_BagSpace()))
            {
                System.out.println("Inventory added: " + o);
                setCurrent_BagUsage(getCurrent_BagUsage() + 1);
                bag.add(o);
            }
        else
                System.out.println("Bag is full");
    }

    public int getCurrent_BagUsage() {
        return current_BagUsage;
    }
    public void setCurrent_BagUsage(int current_BagUsage) {
        this.current_BagUsage = current_BagUsage;
    }
    public int getMax_BagSpace() {
        return max_BagSpace;
    }
    public ArrayList<InventoryItem> getBag()
    {
        return bag;
    }
}
