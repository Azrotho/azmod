package fr.azrotho.azmod.item;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum ModToolMaterial implements ToolMaterial {

    ENDER(4, 2048, 12.0F, 4.0F, 30, Ingredient.ofItems(Items.ENDER_PEARL));

    private final int miningLevel;
    private final int durability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Ingredient repairIngredient;


    ModToolMaterial(int miningLevel, int durability, float miningSpeed, float attackDamage, int enchantability, Ingredient repairIngredient) {
        this.miningLevel = miningLevel;
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }

    
}
