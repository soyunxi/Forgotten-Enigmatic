package org.yunxi.forgottenenigmatic.mixin;


import com.aizistral.enigmaticlegacy.handlers.EnigmaticEventHandler;
import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import com.aizistral.enigmaticlegacy.registries.EnigmaticItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = EnigmaticEventHandler.class, remap = false)
public class EnigmaticEventHandlerMixin {
    @Inject(method = "onEntitySpawn", at = @At("HEAD"), cancellable = true, remap = false)
    private void onEntitySpawn(MobSpawnEvent.FinalizeSpawn event, CallbackInfo ci) {
        ci.cancel();
    }

    /*
    /summon minecraft:vindicator ~ ~ ~ {Health:1000f, NoAI:1b}
    */

    @ModifyVariable(method = "onEntityHurt", at = @At(value = "STORE", opcode = Opcodes.ISTORE))
    private boolean onEntityHurt(boolean bypass) {
        if (Minecraft.getInstance().player != null) {
            return SuperpositionHandler.hasCurio(Minecraft.getInstance().player, EnigmaticItems.DESOLATION_RING.asItem());
        }
        return false;
    }



}
