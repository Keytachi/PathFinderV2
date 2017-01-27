package com.rpgfoundation;

import com.rpgfoundation.Character.Human;
import com.rpgfoundation.Character.Person;
import com.rpgfoundation.Control.BattleEngine;
import com.rpgfoundation.Gear.Weapon;
import com.rpgfoundation.Secondary.Attribute;
import com.rpgfoundation.Secondary.Modify.SpellEffect;
import com.rpgfoundation.Secondary.Modify.SpellHolder;
import com.rpgfoundation.Secondary.Spell;



public class Main {

    public static void main(String[] args)
    {
        /*ArrayList<SpellEffect> testingEffect = new ArrayList<>();
        ArrayList<SpellEffect> testingEffect2 = new ArrayList<>();

        SpellHolder spells = new SpellHolder();
        spells.setSpellSet(new ArrayList<Spell>());


        testingEffect.add(new SpellEffect(0,0,5,SpellEffect.Mechanic.DAMAGE, SpellEffect.TargetType.TARGET));
        testingEffect.add(new SpellEffect(1,0,0,SpellEffect.Mechanic.STUN, SpellEffect.TargetType.TARGET));
        testingEffect2.add(new SpellEffect(0,0,6, SpellEffect.Mechanic.DAMAGE, SpellEffect.TargetType.TARGET));
        Spell testingList = new Spell("Charge", "Running in to the enemy gaining rage and stunning the enemy.", -10, testingEffect);
        Spell testingList2 = new Spell("Rend", "Striking the enemy and cause a bleed on the target",20, testingEffect2);

        spells.getSpellSet().add(testingList);
        spells.getSpellSet().add(testingList2);

        IO.createFile(spells);*/
        game();
    }

    public static void game()
    {
        createPlayer();
        do {
            BattleEngine.descendingOrder();
            BattleEngine.characterTurn();
        }while(BattleEngine.checker() != false);

    }

    public static void createPlayer()   //Generating random character for testing purpose
    {
        Weapon testWep = new Weapon(2,5,2.6, new Attribute(0,0,0,0,0,0));
        //Test Weapon
        int npcGenerate = 3; //(int)(Math.random()*10)+2;
        // From the range of 2-12 since it can randomly only do 1 character at a time.
        Person[] characterNumber = new Person[npcGenerate];
        //Creating an object array using the Character class.

        for(int i = 0; i < npcGenerate; i++)
        {
            characterNumber[i] = new Human("NPC " + (i + 1), 1,testWep, Person.Side.getRandomSide(),
                    Person.ClassRole.getRandomRole());
        }
    }
}
