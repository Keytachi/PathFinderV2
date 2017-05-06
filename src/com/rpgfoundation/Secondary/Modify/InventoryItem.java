package com.rpgfoundation.Secondary.Modify;

/**
 * Created by Brandon on 2/22/2017.
 */
public class InventoryItem implements Cloneable {
    public enum Stack {
        YES,
        NO
    }
    public int itemWeight;
    public Stack itemStack;
    public int count;
    public String name;

    public InventoryItem(int itemWeight, Stack itemStack, int count, String name) {
        this.itemWeight = itemWeight;
        this.itemStack = itemStack;
        this.count = count;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Stack getItemStackstack() {return itemStack;}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object clone()
    {
        try {
            return super.clone();
        }catch (CloneNotSupportedException e)
        {
            throw new Error("Something went wrong");
        }
    }

    public boolean isStack(Stack stack)
    {
        return getItemStackstack() == stack;
    }
}
