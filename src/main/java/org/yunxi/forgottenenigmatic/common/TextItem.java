package org.yunxi.forgottenenigmatic.common;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.yunxi.forgottenenigmatic.handlers.CurioHandler;

public class TextItem extends Item {
    public TextItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        CurioHandler.getAllCurios(pPlayer).forEach(item -> {
            pPlayer.displayClientMessage(item.getDisplayName(), false);
        });
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
