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
    public static final RegistryObject<Item> EYE_OF_THE_VOID = ITEMS.register("eyeofthevoid",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MIRACLE_PRISM = ITEMS.register("miracleprism",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> THE_EQUILIBRIUM = ITEMS.register("theequilibrium",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HEART_OF_BALANCE = ITEMS.register("heartofbalance",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOLTEN_NETHERITE = ITEMS.register("moltennetherite",
            ()-> new Item(new Item.Properties()));



    public static  void register(IEventBus eventBus){ITEMS.register(eventBus);}
}
