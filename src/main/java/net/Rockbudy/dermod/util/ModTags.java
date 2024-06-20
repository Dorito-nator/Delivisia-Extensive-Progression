package net.Rockbudy.dermod.util;

import net.Rockbudy.dermod.DERMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(DERMod.MODID, name));
        }
    }

    public static class items{


        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(DERMod.MODID, name));
        }
    }
}
