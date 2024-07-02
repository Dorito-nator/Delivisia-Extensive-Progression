package net.Rockbudy.dermod.block.entity;

import net.Rockbudy.dermod.DERMod;
import net.Rockbudy.dermod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntitys {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DERMod.MODID);

    public static final RegistryObject<BlockEntityType<TrancendenceAnvilBlockEntity>> TRANSENDANCE_ANVIL_BE =
            BLOCK_ENTITIES.register("transendance_anvil_be", ()->
                    BlockEntityType.Builder.of(TrancendenceAnvilBlockEntity::new,
                            ModBlocks.TRANCENDENCE_ANVIL.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
