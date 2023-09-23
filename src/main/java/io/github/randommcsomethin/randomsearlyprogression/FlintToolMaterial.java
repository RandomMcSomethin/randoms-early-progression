package io.github.randommcsomethin.randomsearlyprogression;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;

public class FlintToolMaterial implements ToolMaterial {
    public static final FlintToolMaterial INSTANCE = new FlintToolMaterial();

    @Override
    public int getDurability() {
        return ToolMaterials.WOOD.getDurability();
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return ToolMaterials.WOOD.getMiningSpeedMultiplier();
    }

    @Override
    public float getAttackDamage() {
        return ToolMaterials.WOOD.getAttackDamage();
    }

    @Override
    public int getMiningLevel() {
        return ToolMaterials.WOOD.getMiningLevel();
    }

    @Override
    public int getEnchantability() {
        return ToolMaterials.WOOD.getEnchantability();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.FLINT);
    }
}
