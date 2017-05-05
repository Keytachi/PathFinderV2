package com.rpgfoundation.Character;

import com.rpgfoundation.Inventory.Weapon;

/**
 * Created by Brandon on 1/1/2017.
 */
public class Human extends Person {

    public Human(String name, int level, Weapon weapon, Side team, ClassRole specialty) {
        super(name, level, weapon, team, specialty);
    }
}
