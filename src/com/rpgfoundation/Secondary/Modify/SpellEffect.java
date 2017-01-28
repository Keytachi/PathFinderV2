package com.rpgfoundation.Secondary.Modify;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

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

    protected Mechanic mechanic;
    protected TargetType type;

    public SpellEffect(int duration, int coolDown, int damageModifier, Mechanic mechanic, TargetType type) {
        this.duration = duration;
        this.coolDown = coolDown;
        this.damageModifier = damageModifier;
        this.mechanic = mechanic;
        this.type = type;
    }

    public SpellEffect(){

    }

    @XmlAttribute
    public Mechanic getMechanic() {
        return mechanic;
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
    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
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
    public void setDamageOverTime(int damageOverTime){
        this.damageOverTime = damageOverTime;
    }
}
