package com.rpgfoundation.Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.rpgfoundation.Character.Person;
import com.rpgfoundation.Secondary.Modify.SpellEffect;
import com.rpgfoundation.Secondary.Spell;

/**
 * Created by Brandon on 1/2/2017.
 */
public class BattleEngine {

    public static ArrayList<Person> characterList = new ArrayList<Person>();
    private static boolean gameOver = false;

    public static void descendingOrder() {
        Collections.sort(characterList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.valueOf(o2.getAttribute().getDexterity()).compareTo(o1.getAttribute().getDexterity());
            }
        });
    }   //Function to sort it descending the arrayList via dexterity value

    public static boolean getGameOver(){
        return gameOver == true;
    }

    private static void setGameOver(boolean state){
        gameOver = state;
    }

    public static void turnEnd(Person player) {
        if(!player.getBuffSystem().isEmpty())
        {
            for(int i = 0; i < player.getBuffSystem().size(); i++)
            {
                buffAfter(player.getBuffSystem().get(i), characterList.get(i));
            }
        }
        if (player.getHealth() <= 0) {
            player.setStatus(Person.PersonStatus.DEAD);
        }
        if(checker(player.getTeam()))
        {
            System.out.println(player.getTeam() + " wins!");
            System.exit(0);
        }

    }

    public static void updateAfter() {
     //Function is to detect if there are any characters that has flee.
     //If character did flee, it will remove from the list.
        for (int i = 0; i < characterList.size(); i++) {
            if (characterList.get(i).isStatus(Person.PersonStatus.FLEE)) {
                characterList.remove(characterList.get(i));
            }
        }
    }

    public static boolean checker(Person.Side side) {
    //This function will be use to check to see if the game is over or not.
    //This will depend if there are any more players on an opposing team.
        int opposing = 0;

        for (int i = 0; i < characterList.size(); i++) {
            if(!characterList.get(i).isTeam(side) && !characterList.get(i).isStatus(Person.PersonStatus.DEAD)) {
                opposing++;
            }
        }
        setGameOver(opposing == 0);
        return getGameOver();
    }


    public static void characterTurn() {
    //Display the order that the special sorting function via dexterity value.
        for (int i = 0; i < characterList.size(); i++) {
            if (characterList.get(i).isStatus(Person.PersonStatus.DEAD) ||
                    characterList.get(i).isStatus(Person.PersonStatus.STUN)) {
                turnEnd(characterList.get(i));
            }
            IO.actionChoice(characterList.get(i));
            turnEnd(characterList.get(i));
        }
        updateAfter();
    }

    /**
     * This section is for spells/buff system.
     * Happens when casting/after turn/spell ending.
     */
    public static void applySpell(Person caster, Person target, Spell spell) {
        for(SpellEffect effect : spell.getEffects())
            switch(effect.getMechanic())
            {
                case DAMAGE:
                    target.setCurrent_Health(target.getCurrent_Health()
                            - caster.getAttribute().getStrength()*effect.getDamageModifier());
                    IO.damageReport(caster,target,
                            caster.getAttribute().getStrength()*effect.getDamageModifier());
                    break;
                case HEAL:
                    target.setCurrent_Health(target.getCurrent_Health() + caster.getAttribute().getIntellect()
                    *effect.getDamageModifier());
                    if(target.getCurrent_Health() > target.getHealth())
                        target.setCurrent_Health(target.getHealth());
                    break;
                case BURN:
                case CURSE:
                    target.setBuffSystem(effect);
                    effect.setDamageOverTime(caster.getAttribute().getIntellect() * effect.getDamageModifier());
                    break;
                case SLEEP:
                    target.setStatus(Person.PersonStatus.SLEEP);
                    target.setBuffSystem(effect);
                    break;
                case STUN:
                    target.setStatus(Person.PersonStatus.STUN);
                    target.setBuffSystem(effect);
                    break;
                case INCREASESTATS:
                case DECREASESTATS:
                    target.setBuffSystem(effect);
                    break;
                default:
            }
    }
    public static void buffAfter(SpellEffect buff, Person player) {
        switch(buff.getMechanic())
        {
            case BURN:
            case CURSE:
                player.setCurrent_Health(player.getCurrent_Health()-buff.getDamageOverTime());
                spellEnding(player,buff);
                break;
            default:
                spellEnding(player,buff);
                break;
        }

    }
    public static void spellEnding(Person player,SpellEffect spell) {
        int turnLeft = spell.getDuration();
        turnLeft--;
        spell.setDuration(turnLeft);
        if(turnLeft == 0) {
            player.getBuffSystem().remove(spell);
            if (!player.isStatus(Person.PersonStatus.ALIVE)) {
                player.setStatus(Person.PersonStatus.ALIVE);
            }
        }
    }

    // TODO: Need to create a function which the player can use their potions to restore a % of health or resources in
    // TODO: a turn.
    // TODO: Need to create a function which the player can equip and unequip weapon/armor base on a turn.

}
