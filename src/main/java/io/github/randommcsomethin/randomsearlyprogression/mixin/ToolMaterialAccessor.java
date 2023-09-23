package io.github.randommcsomethin.randomsearlyprogression.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ToolItem.class)
public interface ToolMaterialAccessor {
    @Accessor @Mutable
    void setMaterial(final ToolMaterial material);
}
