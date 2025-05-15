package org.yunxi.forgottenenigmatic.handlers;


import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import com.aizistral.enigmaticlegacy.items.CursedScroll;
import com.aizistral.enigmaticlegacy.items.ForbiddenFruit;
import com.aizistral.enigmaticlegacy.registries.EnigmaticItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.yunxi.forgottenenigmatic.Forgottenenigmatic;

@Mod.EventBusSubscriber(modid = Forgottenenigmatic.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgottenenigmaticEventHandler {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingHurt(LivingHurtEvent event) {
        Entity sourceEntity = event.getSource().getEntity();
        if (sourceEntity instanceof Player player) {
            float damageBoost = 0.0F;
            if (SuperpositionHandler.hasCurio(player, EnigmaticItems.DESOLATION_RING)) {
                damageBoost += event.getAmount() * CursedScroll.damageBoost.getValue().asModifier() * 1.5f * (float)SuperpositionHandler.getCurseAmount(player);
            }
            event.setAmount(event.getAmount() + damageBoost);
        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void miningStuff(PlayerEvent.BreakSpeed event) {
        float correctedSpeed = event.getOriginalSpeed();
        float miningBoost = 1.0F;
        if (SuperpositionHandler.hasCurio(event.getEntity(), EnigmaticItems.DESOLATION_RING)) {
            miningBoost += CursedScroll.miningBoost.getValue().asModifier() * 1.5f * (float)SuperpositionHandler.getCurseAmount(event.getEntity());
        }
        correctedSpeed *= miningBoost;
        correctedSpeed -= event.getOriginalSpeed();
        event.setNewSpeed(event.getNewSpeed() + correctedSpeed);
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onLivingHeal(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (SuperpositionHandler.hasCurio(player, EnigmaticItems.DESOLATION_RING)) {
                event.setAmount(event.getAmount() + event.getAmount() * CursedScroll.regenBoost.getValue().asModifier() * (float)SuperpositionHandler.getCurseAmount(player));
            }
        }
    }

}
