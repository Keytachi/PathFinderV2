package com.rpgfoundation.Character;
import com.rpgfoundation.Control.BattleEngine;
import com.rpgfoundation.Control.IO;
import com.rpgfoundation.Gear.Armor;
import com.rpgfoundation.Gear.Weapon;
import com.rpgfoundation.JobType.JobType;
import com.rpgfoundation.JobType.Paladin;
import com.rpgfoundation.JobType.Warrior;
import com.rpgfoundation.Secondary.Attribute;
import com.rpgfoundation.Secondary.Inventory;
import com.rpgfoundation.Secondary.Modify.SpellEffect;
import com.rpgfoundation.Secondary.Spell;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Brandon on 1/1/2017.
 * Done
 */
public class Person{

    private int level;
    private int resource;
    private int current_Resource;
    private int health;
    private int current_Health;
    private int baseArmor;
    private String name;


    public static enum PersonStatus{
        ALIVE,
        DEAD,
        SLEEP,
        CONFUSE,
        FLEE,
        STUN;
    }
    public static enum Side{
        PLAYER,
        ENEMY;

        private static final Side[] VALUES = values();
        private static final int SIZE = VALUES.length;
        private static final Random RANDOM = new Random();

        public static Side getRandomSide()
        {
            return VALUES[RANDOM.nextInt(SIZE)];
        }
    }
    public static enum ClassRole{
        PALADIN,
        WARRIOR;

        private static final ClassRole[] VALUES = values();
        private static final int SIZE = VALUES.length;
        private static final Random RANDOM = new Random();

        public static ClassRole getRandomRole()
        {
            return VALUES[RANDOM.nextInt(SIZE)];
        }
    }
    public enum ResourceType{
        MANA,
        RAGE,
        ENERGY;
    }

    private ArrayList<Spell> spells = new ArrayList<>();
    private ArrayList<SpellEffect> buffSystem = new ArrayList<>();
    private ArrayList<Inventory> bag = new ArrayList<>();

    protected PersonStatus status;
    protected Side team;
    protected ClassRole specialty;
    protected ResourceType resourceType;
    protected String title;

    private JobType jobType;
    private Attribute attribute;
    private Weapon weapon;
    private Armor armor;
    private Spell spell;

    public Person(String name,int level,Weapon weapon,Side team, ClassRole specialty) {
        this.name = name;
        this.level = level;
        this.baseArmor = 0;
        this.status = PersonStatus.ALIVE;
        this.team = team;
        this.specialty = specialty;
        this.weapon = weapon;
        switch(specialty)
        {
            case PALADIN:
                File paladinSpell = new File("src/bin/xml/PaladinSpellList.xml");
                this.resource = 100;
                this.current_Resource = 100;
                this.resourceType = ResourceType.MANA;
                this.jobType = new Paladin();
                this.spells = IO.readFile(paladinSpell, spells);
                break;
            case WARRIOR:
                File warriorSpell = new File("src/bin/xml/WarriorSpellList.xml");
                this.resource = 100;
                this.current_Resource = 0;
                this.resourceType = ResourceType.RAGE;
                this.jobType = new Warrior();
                this.spells = IO.readFile(warriorSpell, spells);
                break;
        }
        this.attribute = statsCombine();
        this.health = 100 + attribute.getConstitution()*10;
        this.current_Health = 100 + attribute.getConstitution()*10;
        BattleEngine.characterList.add(this);
    }

    public String getName(){
        return name;
    }
    public int getLevel() {
        return level;
    }
    public int getResource() {
        return resource;
    }
    public int getCurrent_Resource() {
        return current_Resource;
    }
    public int getHealth() {
        return health;
    }
    public int getCurrent_Health() {
        return current_Health;
    }

    public PersonStatus getStatus() {
        return status;
    }
    public Side getTeam() {
        return team;
    }
    public ClassRole getSpecialty() {
        return specialty;
    }
    public ResourceType getResourceType(){
        return resourceType;
    }


    public Attribute getAttribute() {
        return attribute;
    }
    public JobType getJobType() {
        return jobType;
    }
    public ArrayList<Spell> getSpell(){ return spells;}
    public ArrayList<SpellEffect> getBuffSystem(){
        return buffSystem;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
    public void setCurrent_Resource(int current_Resource) {
        this.current_Resource = current_Resource;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setCurrent_Health(int current_Health) {
        this.current_Health = current_Health;
    }
    public void setStatus(PersonStatus status) {
        this.status = status;
    }
    public void setBuffSystem(SpellEffect spell)
    {
        this.buffSystem.add(spell);
    }

    @Override
    public String toString(){
        return title;
    }


    public Attribute statsCombine() {
            return new Attribute(
                    jobType.getAttribute().getStrength() + weapon.getAttribute().getStrength(),
                    jobType.getAttribute().getIntellect() + weapon.getAttribute().getIntellect(),
                    jobType.getAttribute().getDexterity() + weapon.getAttribute().getDexterity(),
                    jobType.getAttribute().getConstitution() + weapon.getAttribute().getConstitution(),
                    jobType.getAttribute().getWisdom() + weapon.getAttribute().getWisdom(),
                    jobType.getAttribute().getResistance() + weapon.getAttribute().getResistance());
    }
    public boolean isStatus(PersonStatus status) {
        return getStatus() == status;
    }

    public boolean isTeam(Side side) {
        return getTeam() == side;
    }
}
