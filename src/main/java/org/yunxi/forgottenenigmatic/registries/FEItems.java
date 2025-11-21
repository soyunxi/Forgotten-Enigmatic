package org.yunxi.forgottenenigmatic.registries;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.yunxi.forgottenenigmatic.Forgottenenigmatic;
import org.yunxi.forgottenenigmatic.common.TextItem;

public class FEItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Forgottenenigmatic.MODID);

    public static final RegistryObject<Item> Text = ITEMS.register("text", () -> new TextItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
