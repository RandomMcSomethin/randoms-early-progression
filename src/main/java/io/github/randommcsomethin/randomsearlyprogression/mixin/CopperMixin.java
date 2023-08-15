package io.github.randommcsomethin.randomsearlyprogression.mixin;

import com.mojang.datafixers.types.templates.Tag;
import io.github.randommcsomethin.randomsearlyprogression.RandomsEarlyProgression;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Mainly used for copper ore, but could also be repurposed for letting flint mine other things
@Mixin(PlayerEntity.class)
public abstract class CopperMixin extends LivingEntity {

    @Shadow @Final private PlayerInventory inventory;

    protected CopperMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "canHarvest(Lnet/minecraft/block/BlockState;)Z", cancellable = true)
    private void canHarvest(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        boolean usingFlintTool = this.inventory.getMainHandStack().isIn(RandomsEarlyProgression.FLINT_TOOLS);
        if (usingFlintTool && canHarvestBlock(this.inventory.getMainHandStack().getItem(), state) && state.isIn(RandomsEarlyProgression.NEEDS_FLINT_TOOL)) {
            cir.setReturnValue(true);
        }
    }

    private boolean canHarvestBlock(Item tool, BlockState state) {
        if (tool instanceof PickaxeItem && state.isIn(BlockTags.PICKAXE_MINEABLE)) {
            return true;
        } else if (tool instanceof AxeItem && state.isIn(BlockTags.AXE_MINEABLE)) {
            return true;
        } else if (tool instanceof ShovelItem && state.isIn(BlockTags.SHOVEL_MINEABLE)) {
            return true;
        } else return tool instanceof HoeItem && state.isIn(BlockTags.HOE_MINEABLE);
    }

}
