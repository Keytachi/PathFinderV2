package com.rpgfoundation.Secondary.Modify;

import com.rpgfoundation.Secondary.Spell;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Brandon on 1/5/2017.
 */
@XmlRootElement
public class SpellEffect {
    public enum Mechanic{
        DAMAGE, HEAL, CURSE, BURN, SLEEP, STUN, INCREASESTATS, DECREASESTATS,
    }

    public enum TargetType{
        SELF,PARTY,ENEMYPARTY,TARGET;
    }


    private int duration;
    private int coolDown;
    private int damageModifier;
    private int damageOverTime;

    protected Mechanic effect;
    protected TargetType type;

    public SpellEffect(int duration, int coolDown, int damageModifier, Mechanic effect, TargetType type) {
        this.duration = duration;
        this.coolDown = coolDown;
        this.damageModifier = damageModifier;
        this.effect = effect;
        this.type = type;
    }

    public SpellEffect(){

    }

    @XmlAttribute
    public Mechanic getMechanic() {
        return effect;
    }
    @XmlAttribute
    public TargetType getType() {
        return type;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }
    public void setDamageModifier(int damageModifier){
        this.damageModifier = damageModifier;
    }
    public void setEffect(Mechanic effect) {
        this.effect = effect;
    }


    public int getDuration() {
        return duration;
    }
    public int getCoolDown() {
        return coolDown;
    }
    public int getDamageModifier(){
        return damageModifier;
    }
    public int getDamageOverTime(){
        return damageOverTime;
    }

    public void setType(TargetType type) {
        this.type = type;
    }
    public void setDamageOverTime(int damageOverTime){
        this.damageOverTime = damageOverTime;
    }
}
