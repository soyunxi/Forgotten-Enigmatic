package org.yunxi.forgottenenigmatic.handlers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"UnstableApiUsage", "removal"})
public class CurioHandler {

    public static List<ItemStack> getAllCurios(Player player) {
        ArrayList<ItemStack> curiosList = new ArrayList<>();
        CuriosApi.getCuriosHelper().getCuriosHandler(player).ifPresent(handler -> {
            for (Map.Entry<String, ICurioStacksHandler> entry : handler.getCurios().entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stacksHandler.getStacks().getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        curiosList.add(stack);
                    }
                }
            }
        });
        return curiosList;
    }

}