package com.rpgfoundation.Secondary;

import com.rpgfoundation.Character.Person;
import com.rpgfoundation.Control.BattleEngine;
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
public class Spell{

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

    public Spell(){}

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
        BattleEngine.applySpell(caster, target, this);
    }
}
