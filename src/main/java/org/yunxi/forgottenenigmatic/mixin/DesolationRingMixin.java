package org.yunxi.forgottenenigmatic.mixin;

import com.aizistral.enigmaticlegacy.api.items.IEldritch;
import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import com.aizistral.enigmaticlegacy.helpers.ItemLoreHelper;
import com.aizistral.enigmaticlegacy.items.CursedScroll;
import com.aizistral.enigmaticlegacy.items.DesolationRing;
import com.aizistral.enigmaticlegacy.items.generic.ItemBaseCurio;
import com.aizistral.enigmaticlegacy.registries.EnigmaticItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

@Mixin(DesolationRing.class)
public class DesolationRingMixin extends ItemBaseCurio implements IEldritch {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag p_41424_) {
        ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.void");
        if (Screen.hasShiftDown()) {
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.theTwist4");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.theTwist5");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.void");

            ItemLoreHelper.addLocalizedString(list, "tooltip.forgottenenigmatic.eternallyBound2");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.void");

            ItemLoreHelper.addLocalizedString(list, "tooltip.forgottenenigmatic.desolationRing1");
            ItemLoreHelper.addLocalizedString(list, "tooltip.forgottenenigmatic.desolationRing2");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.void");

            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.cursed_scroll1", ChatFormatting.GOLD, new Object[]{CursedScroll.damageBoost.getValue().asPercentage() * 1.5f + "%"});
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.cursed_scroll2", ChatFormatting.GOLD, new Object[]{CursedScroll.miningBoost.getValue().asPercentage() * 1.5f + "%"});
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.cursed_scroll3", ChatFormatting.GOLD, new Object[]{CursedScroll.regenBoost.getValue().asPercentage() * 1.5f + "%"});
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.void");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.cursed_scroll4");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.cursed_scroll5");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.void");
            ItemLoreHelper.indicateWorthyOnesOnly(list);
        } else {
            ItemLoreHelper.addLocalizedString(list, "tooltip.forgottenenigmatic.eternallyBound1");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.void");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.eternallyBound1");
            if (Minecraft.getInstance().player != null && SuperpositionHandler.canUnequipBoundRelics(Minecraft.getInstance().player)) {
                ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.eternallyBound2_creative");
            } else {
                ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.eternallyBound2");
            }
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.void");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.holdShift");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.void");
        }

        if (Minecraft.getInstance().player != null && SuperpositionHandler.getCurioStack(Minecraft.getInstance().player, this) == stack) {
            int curses = SuperpositionHandler.getCurseAmount(Minecraft.getInstance().player);
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.void");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.cursed_scroll6");
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.cursed_scroll7", ChatFormatting.GOLD, new Object[]{CursedScroll.damageBoost.getValue().asPercentage() * curses * 1.5f + "%"});
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.cursed_scroll8", ChatFormatting.GOLD, new Object[]{CursedScroll.miningBoost.getValue().asPercentage() * curses * 1.5f + "%"});
            ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.cursed_scroll9", ChatFormatting.GOLD, new Object[]{CursedScroll.regenBoost.getValue().asPercentage() * curses * 1.5f + "%"});
        }

        ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.void");
        ItemLoreHelper.indicateCursedOnesOnly(list);
        super.appendHoverText(stack, p_41422_, list, p_41424_);
    }

    public boolean canEquip(SlotContext context, ItemStack stack) {
        LivingEntity entity = context.entity();
        if (entity instanceof Player player) {
            if (SuperpositionHandler.isTheWorthyOne(player)) {
                return !SuperpositionHandler.hasCurio(player, EnigmaticItems.DESOLATION_RING) && !SuperpositionHandler.hasCurio(player, EnigmaticItems.CURSED_SCROLL);
            }
        }
        return false;
    }

    public void curioTick(SlotContext context, ItemStack stack) {
        LivingEntity entity = context.entity();
        if (entity instanceof Player player) {
            AABB boundingBoxAroundEntity = SuperpositionHandler.getBoundingBoxAroundEntity(player, 32.0);
            Level level = player.level();
            List<LivingEntity> entitiesOfClass = level.getEntitiesOfClass(LivingEntity.class, boundingBoxAroundEntity, this::isFriendly);
            for (LivingEntity ofClass : entitiesOfClass) {
                if (!(ofClass instanceof Player)) {
                    ofClass.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 4));
                }
            }
        }
        super.curioTick(context, stack);
    }

    boolean isFriendly(Entity entity) {
        if (!(entity instanceof LivingEntity living)) return false;
        if (ModList.get().isLoaded("dummmmmmy")) {
            if (ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).toString().equals("dummmmmmy:target_dummy")) return false;
        }
        if (entity instanceof Animal || entity instanceof AmbientCreature || entity instanceof WaterAnimal) {
            return true;
        }
        if (entity.getType() == EntityType.VILLAGER || entity.getType() == EntityType.WANDERING_TRADER) {
            return true;
        }
        if (entity instanceof Mob mob) {
            boolean hasAttackGoal = mob.goalSelector.getAvailableGoals().stream()
                    .anyMatch(goal -> goal.getGoal() instanceof MeleeAttackGoal || goal.getGoal() instanceof NearestAttackableTargetGoal<?>);

            if (!hasAttackGoal) {
                return true;
            }
        }

        return false;
    }

}
