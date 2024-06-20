package net.Rockbudy.dermod.datagen;

import net.Rockbudy.dermod.DERMod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, DERMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
