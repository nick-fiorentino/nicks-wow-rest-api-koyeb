package com.nickfiorentino.model.extrapojoitemclasses;

import java.util.List;

public class Weapon {

    private Damage damage;
    private AttackSpeed attackSpeed;
    private Dps dps;
    private List<AdditionalDamage> additionalDamage;

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public AttackSpeed getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(AttackSpeed attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public Dps getDps() {
        return dps;
    }

    public void setDps(Dps dps) {
        this.dps = dps;
    }

    public List<AdditionalDamage> getAdditionalDamage() {
        return additionalDamage;
    }

    public void setAdditionalDamage(List<AdditionalDamage> additionalDamage) {
        this.additionalDamage = additionalDamage;
    }

}
