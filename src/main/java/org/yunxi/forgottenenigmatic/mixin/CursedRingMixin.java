package org.yunxi.forgottenenigmatic.mixin;

import com.aizistral.enigmaticlegacy.items.CursedRing;
import com.aizistral.enigmaticlegacy.items.generic.ItemBaseCurio;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

@SuppressWarnings({"UnstableApiUsage", "removal"})
@Mixin(value = CursedRing.class, remap = false)
public class CursedRingMixin extends ItemBaseCurio {
    @Inject(method = "getAttributeModifiers", at = @At("RETURN"), cancellable = true)
    public void getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack, CallbackInfoReturnable<Multimap<Attribute, AttributeModifier>> cir) {
        Multimap<Attribute, AttributeModifier> returnValue = cir.getReturnValue();
        CuriosApi.getCuriosHelper().addSlotModifier(returnValue, "etching", UUID.fromString("d469eb4b-64fc-4747-8d78-44ce1993a8a9"), 7.0, AttributeModifier.Operation.ADDITION);
        CuriosApi.getCuriosHelper().addSlotModifier(returnValue, "charm", UUID.fromString("d469eb4b-64fc-4747-8d78-44ce1993a8a6"), 2.0, AttributeModifier.Operation.ADDITION);
    }

    /**
     * @author
     * @reason
     */
    /*@Overwrite
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }*/
}
