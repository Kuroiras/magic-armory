package com.arsatecs.magicarmory.init;

import com.arsatecs.magicarmory.MagicArmory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MagicArmory.MODID);

    public static final RegistryObject<Block> RAINBOW_BLOCK = register(
            "rainbow_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.METAL).friction(0.5f).destroyTime(3)),
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC));

    public static final RegistryObject<Block> BLOODY_WOOD = register(
            "bloody_wood",
            ()-> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(4f)),
            new Item.Properties().tab(CreativeModeTab.TAB_MISC));

    public static final RegistryObject<Block> CREEPER_BLOCK = register(
            "creeper_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.DECORATION).destroyTime(2)),
            new Item.Properties().tab(CreativeModeTab.TAB_MISC));





    private static <T extends Block> RegistryObject<T> register(
            String name,
            Supplier<T> supplier,
            Item.Properties properties) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
        return block;
    }

}
