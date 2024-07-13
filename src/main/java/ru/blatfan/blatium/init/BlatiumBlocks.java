package ru.blatfan.blatium.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.blatium.Blatium;

import java.util.function.Supplier;

public class BlatiumBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Blatium.MODID);
    
    public static final RegistryObject<Block> BLATIUM_BLOCK = register("blatium_block", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK).lightLevel((state) -> { return 0;}).strength(2,1500.0f)));
    public static final RegistryObject<Block> BLATIUM_ORE = register("blatium_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((state) -> { return 15;}).strength(2,1500.0f)));
    public static final RegistryObject<Block> DEEPSLATE_BLATIUM_ORE = register("deepslate_blatium_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).lightLevel((state) -> { return 15;}).strength(3,1500.0f)));
    
    public static final RegistryObject<Block> NLIUM_BLOCK = register("nlium_block", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK).lightLevel((state) -> { return 0;}).strength(2,1500.0f)));
    public static final RegistryObject<Block> NLIUM_ORE = register("nlium_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().sound(SoundType.STONE).lightLevel((state) -> { return 15;}).strength(3,1500.0f)));
    
    public static RegistryObject<Block> register(String id, Supplier<Block> sup){
        RegistryObject<Block> reg = REGISTRY.register(id, sup);
        
        BlatiumItems.BLOCKS.put(id, BlatiumItems.REGISTRY.register(id, () -> new BlockItem(reg.get(), new Item.Properties().fireResistant().rarity(Rarity.EPIC))));
        
        return reg;
    }
}
