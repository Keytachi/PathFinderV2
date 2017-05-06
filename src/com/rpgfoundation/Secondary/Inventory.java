package com.rpgfoundation.Secondary;


import com.rpgfoundation.Control.IO;
import com.rpgfoundation.Secondary.Modify.InventoryItem;

import java.util.ArrayList;

/**
 * Created by Brandon on 1/28/2017.
 */
public class Inventory{

    //A container for the content of the inventory items
    private ArrayList<InventoryItem> bagContent = new ArrayList<>();


    private int max_BagSpace;
    private int current_BagUsage;

    public Inventory()
    {
        this.max_BagSpace = 20;
        this.current_BagUsage = 0;
    }

    public void addItem(InventoryItem o)
    {
        InventoryItem a = ifExist(o);
        if (!(getCurrent_BagUsage() >= getMax_BagSpace()))
            {
                if (o.isStack(InventoryItem.Stack.YES) && a != null)
                {
                    a.setCount(a.getCount() + o.getCount());
                }
                else
                {
                    a = (InventoryItem) o.clone();
                    setCurrent_BagUsage(getCurrent_BagUsage() + 1);
                    bagContent.add(a);
                    IO.successAdd(a);
                }
            }
        else
                IO.failAdd(o);
    }

    public void removeItem(InventoryItem o)
    {
        InventoryItem a = ifExist(o);
        if(a != null)
        {
            bagContent.remove(a);
            System.out.println("You have removed " + a + " from inventory");
        }
        else{
            System.out.println(a + " is not in inventory");
        }

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
    public ArrayList<InventoryItem> getBagContent()
    {
        return bagContent;
    }

    public InventoryItem ifExist(InventoryItem o)
    {
        InventoryItem a;
        for(int i = 0; i < getBagContent().size(); i++) {
            a = getBagContent().get(i);
                if (o.getName() == a.getName())
                    return a;
            }
        return null;
    }
}
