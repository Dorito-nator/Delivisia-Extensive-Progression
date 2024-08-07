package net.Rockbudy.dermod;

import net.Rockbudy.dermod.block.ModBlocks;
import net.Rockbudy.dermod.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DERCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DERMod.MODID);

    public static final RegistryObject<CreativeModeTab> DER_TAB = CREATIVE_MODE_TABS.register("der_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.TESLA_ALLOY.get()))
                    .title(Component.translatable("creativetab.der_tab"))
                    .displayItems((pParameters, pOutput)->{
                        pOutput.accept(ModItems.TESLA_ALLOY.get());
                        pOutput.accept(ModItems.EYE_OF_THE_VOID.get());
                        pOutput.accept(ModItems.MIRACLE_PRISM.get());
                        pOutput.accept(ModItems.THE_EQUILIBRIUM.get());
                        pOutput.accept(ModItems.HEART_OF_BALANCE.get());
                        pOutput.accept(ModItems.MOLTEN_NETHERITE.get());
                        pOutput.accept(ModBlocks.TRANCENDENCE_ANVIL.get());
                    })
                    .build());

    public static void register (IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
