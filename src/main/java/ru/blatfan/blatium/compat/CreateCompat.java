package ru.blatfan.blatium.compat;

import com.simibubi.create.content.decoration.encasing.CasingBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.blatium.Blatium;
import ru.blatfan.blatium.item.BlatBlockItem;
import ru.blatfan.blatium.item.BlatItem;

import java.util.function.Supplier;

public class CreateCompat {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Blatium.MODID);
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, Blatium.MODID);
    
    public static final RegistryObject<Item> CRUSHED_RAW_BLATIUM = ITEM.register("crushed_raw_blatium", BlatItem::blatium);
    public static final RegistryObject<Item> CRUSHED_RAW_NLIUM   = ITEM.register("crushed_raw_nlium", BlatItem::nlium);
    public static final RegistryObject<Block> POWER_OBSIDIAN     = register("power_obsidian", ()->new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).strength(3, 1200)));
    public static final RegistryObject<Block> CREATIVE_CASING    = register("creative_casing", ()->new CasingBlock(BlockBehaviour.Properties.copy(POWER_OBSIDIAN.get())));
    
    public static RegistryObject<Block> register(String id, Supplier<Block> sup){
        RegistryObject<Block> reg = BLOCK.register(id, sup);
        ITEM.register(id, () -> new BlatBlockItem(reg.get(), true));
        return reg;
    }
    
    public CreateCompat(IEventBus modEventBus){
        ITEM.register(modEventBus);
        BLOCK.register(modEventBus);
    }
}
