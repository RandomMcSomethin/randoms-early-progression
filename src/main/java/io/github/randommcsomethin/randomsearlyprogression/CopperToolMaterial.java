package io.github.randommcsomethin.randomsearlyprogression;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;

public class CopperToolMaterial implements ToolMaterial {
    public static final CopperToolMaterial INSTANCE = new CopperToolMaterial();
    @Override
    public int getDurability() {
        return ToolMaterials.STONE.getDurability();
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return ToolMaterials.STONE.getMiningSpeedMultiplier();
    }

    @Override
    public float getAttackDamage() {
        return ToolMaterials.STONE.getAttackDamage();
    }

    @Override
    public int getMiningLevel() {
        return ToolMaterials.STONE.getMiningLevel();
    }

    @Override
    public int getEnchantability() {
        return ToolMaterials.STONE.getEnchantability();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.COPPER_INGOT);
    }
}
