package com.rpgfoundation.Secondary;

import com.rpgfoundation.Character.Person;
import com.rpgfoundation.Control.IO;
import com.rpgfoundation.Secondary.Modify.SpellEffect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Brandon on 1/5/2017.
 */

@XmlRootElement(name = "Spell")
@XmlAccessorType(XmlAccessType.FIELD)
public class Spell extends SpellEffect{

    private String name;
    private String description;
    private int manaCost;
    private List<SpellEffect> effects;
    private SpellEffect spellEffect;

    public Spell(String name, String description,int manaCost,List<SpellEffect> effects) {

        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.effects = effects;
    }

    public Spell()
    {

    }

    @Override
    public String toString() {
        return name;
    }
    //Getter functions for  the spell class.
    public String getName() {
        return name;
    }
    public int getManaCost(){
        return manaCost;
    }
    public String getDescription() {
        return description;
    }
    //Getter function to access the SpellEffect class
    public List<SpellEffect> getEffects(){
        return effects;
    }
    public SpellEffect getSpellEffect(){
        return spellEffect;
    }


    public void cast(Person caster, Person target) {
        caster.setCurrent_Resource(caster.getCurrent_Resource() - getManaCost());
        applySpell(caster, target);
    }
    public void applySpell(Person caster, Person target) {
        for(SpellEffect effect : getEffects())
        switch(effect.getEffect())
        {
            case DAMAGE:
                target.setCurrent_Health(target.getCurrent_Health() - caster.getAttribute().getStrength()*effect.getDamageModifier());
                IO.damageReport(caster,target,caster.getAttribute().getStrength()*effect.getDamageModifier());
                damageMechanic(caster,target,this);
                break;
            case HEAL:
                healMechanic(caster,target,this);
                break;
            case CURSE, BURN:
                target.setBuffSystem(this);
                setDamageOverTime((int)caster.getAttribute().getIntellect()*getDamageModifier());
                break;
            case SLEEP:
                target.setStatus(Person.PersonStatus.SLEEP);
                target.setBuffSystem(this);
                break;
            case STUN:
                target.setStatus(Person.PersonStatus.STUN);
                target.setBuffSystem(this);
                break;
            case INCREASESTATS:
                break;
            case DECREASESTATS:
                break;
            default:
        }
    }
    public void buffAfter(Person player) {
        //TODO: Apply the effect of the mechanic of the spell to lower its duration and damaging the player.
        switch(getEffect())
        {
            case BURN, CURSE:
                player.setCurrent_Health(player.getCurrent_Health()-getDamageOverTime());
            default:
                spellEnding(player,this);
                break;
        }

    }

    //Doing damage mechanic
    public void damageMechanic(Person caster, Person target, SpellEffect effect){


    }

    public void spellEnding(Person player,Spell spell)
    {
        int turnLeft = getDuration();
        turnLeft--;
        if(turnLeft == 0) {
            player.getBuffSystem().remove(this);
            if (!player.isStatus(Person.PersonStatus.ALIVE)) {
                player.setStatus(Person.PersonStatus.ALIVE);
            }
        }

    }
    public void healMechanic(Person caster, Person target, SpellEffect effect) {
        if(target.getCurrent_Health() == target.getHealth())
        {
            target.setCurrent_Health(target.getHealth());
            System.out.println(target.getName() + " is at full health");
        }
        target.setCurrent_Health(target.getCurrent_Health() + caster.getAttribute().getIntellect() * effect.getDamageModifier());
        if(target.getCurrent_Health() > target.getHealth())
        {
            target.setCurrent_Health(target.getHealth());
        }
        IO.damageReport(caster,target,caster.getAttribute().getIntellect()*effect.getDamageModifier());
    }
}
