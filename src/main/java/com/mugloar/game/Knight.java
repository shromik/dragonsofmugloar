
package com.mugloar.game;

public class Knight {
    private String name;
    private Integer attack;
    private Integer armor;
    private Integer agility;
    private Integer endurance;

    public String getName() {
        return name;
    }

    public Knight setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAttack() {
        return attack;
    }

    public Knight setAttack(Integer attack) {
        this.attack = attack;
        return this;
    }

    public Integer getArmor() {
        return armor;
    }

    public Knight setArmor(Integer armor) {
        this.armor = armor;
        return this;
    }

    public Integer getAgility() {
        return agility;
    }


    public Knight setAgility(Integer agility) {
        this.agility = agility;
        return this;
    }

    public Integer getEndurance() {
        return endurance;
    }

    public Knight setEndurance(Integer endurance) {
        this.endurance = endurance;
        return this;
    }

}
