package com.rpgfoundation.Inventory;

import com.rpgfoundation.Secondary.Inventory;
import com.rpgfoundation.Secondary.Modify.InventoryItem;

/**
 * Created by GamingRig on 5/4/2017.
 */
public class Consumable extends InventoryItem {

    public enum TypesofPotion{
        HEALTH,
        MANA,
    }
    private String name;
    private TypesofPotion potions;
    private int count;

    public Consumable(int itemweight, Stack stack, int count, String name, TypesofPotion potions)
    {
        super(itemweight, stack, count, name);
        this.potions = potions;
    }

    public String getName() {
        return name;
    }
    public TypesofPotion getPotions() {
        return potions;
    }
}
