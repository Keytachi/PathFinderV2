package com.rpgfoundation.Secondary;


import com.rpgfoundation.Secondary.Modify.InventoryItem;

import java.util.ArrayList;

/**
 * Created by Brandon on 1/28/2017.
 */
public class Inventory{

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
        if (!(getCurrent_BagUsage() >= getMax_BagSpace()))
            {
                System.out.println("Inventory added: " + o + " x" + o.getCount());
                if(o.stack() == InventoryItem.Stack.YES && ifExist(o) )
                {
                    InventoryItem a = null;
                    for(int j = 0; j < getBagContent().size(); j++)
                    {
                        if(o.getName() == getBagContent().get(j).getName())
                        {
                            a = getBagContent().get(j);
                        }
                    }
                    a.setCount(a.getCount()+ o.getCount());
                }
                else {
                    setCurrent_BagUsage(getCurrent_BagUsage() + 1);
                    bagContent.add(o);
                }
            }
        else
                System.out.println("Bag is full");
    }

    public void removeItem(InventoryItem o)
    {
        if(ifExist(o))
        {
            bagContent.remove(o);
            System.out.println("You have removed " + o + " from inventory");
        }
        else{
            System.out.println(o + " is not in inventory");
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

    public boolean ifExist(InventoryItem o)
    {
        for(int i = 0; i < getBagContent().size(); i++)
            if(o.getName() == getBagContent().get(i).getName())
                return true;
        return false;
    }
}
