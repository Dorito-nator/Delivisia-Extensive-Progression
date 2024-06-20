package net.Rockbudy.dermod.items;

import net.Rockbudy.dermod.DERMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DERMod.MODID);

    //Materials
    public static final RegistryObject<Item> TESLA_ALLOY = ITEMS.register("teslaalloy",
            ()-> new Item(new Item.Properties()));


    public static  void register(IEventBus eventBus){ITEMS.register(eventBus);}
}
