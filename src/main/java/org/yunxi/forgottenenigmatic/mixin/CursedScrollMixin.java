package org.yunxi.forgottenenigmatic.mixin;


import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import com.aizistral.enigmaticlegacy.items.CursedScroll;
import com.aizistral.enigmaticlegacy.registries.EnigmaticItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import top.theillusivec4.curios.api.SlotContext;

@Mixin(value = CursedScroll.class, remap = false)
public class CursedScrollMixin {
    @Inject(method = "canEquip", at = @At(value = "HEAD"), cancellable = true, remap = false)
    public void canEquip(SlotContext context, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (SuperpositionHandler.hasCurio(context.entity(), EnigmaticItems.DESOLATION_RING)) cir.setReturnValue(false);
    }
}
