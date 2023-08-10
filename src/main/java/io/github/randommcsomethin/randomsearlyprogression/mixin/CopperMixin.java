package io.github.randommcsomethin.randomsearlyprogression.mixin;

import io.github.randommcsomethin.randomsearlyprogression.ExampleMod;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
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
        boolean usingFlintTool = this.inventory.getMainHandStack().isIn(ExampleMod.FLINT_TOOLS);
        if (usingFlintTool && state.isIn(ExampleMod.NEEDS_FLINT_TOOL)) {
            cir.setReturnValue(true);
        }
    }

}
