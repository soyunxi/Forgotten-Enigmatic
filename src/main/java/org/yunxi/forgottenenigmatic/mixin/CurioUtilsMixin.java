package org.yunxi.forgottenenigmatic.mixin;

import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CurioUtils.class, remap = false)
public class CurioUtilsMixin {
    @Inject(method = "isCsOn", at = @At("RETURN"), cancellable = true)
    private static void isCsOn(Player player, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() || SuperpositionHandler.isTheCursedOne(player));
    }
}
