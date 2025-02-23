package ru.blatfan.blatium.compact.mekanism;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.blatium.Blatium;
import ru.blatfan.blatium.item.BlatItem;

import java.util.function.Supplier;

public class BlatiumMek {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Blatium.MODID);
    
    public static final RegistryObject<Item> BLATIUM_CLUMP = registerMek("blatium_clump", BlatItem::blatium);
    public static final RegistryObject<Item> BLATIUM_SHARD = registerMek("blatium_shard", BlatItem::blatium);
    public static final RegistryObject<Item> BLATIUM_CRYSTAL = registerMek("blatium_crystal", BlatItem::blatium);
    public static final RegistryObject<Item> BLATIUM_DIRTY = registerMek("blatium_dirty", BlatItem::blatium);
    
    public static final RegistryObject<Item> NLIUM_CLUMP = registerMek("nlium_clump", BlatItem::nlium);
    public static final RegistryObject<Item> NLIUM_SHARD = registerMek("nlium_shard", BlatItem::nlium);
    public static final RegistryObject<Item> NLIUM_CRYSTAL = registerMek("nlium_crystal", BlatItem::nlium);
    public static final RegistryObject<Item> NLIUM_DIRTY = registerMek("nlium_dirty", BlatItem::nlium);
    
    private static RegistryObject<Item> registerMek(String id, Supplier<Item> itemSupplier){
        return REGISTRY.register(id, itemSupplier);
    }
    
    public BlatiumMek(IEventBus modEventBus){
        REGISTRY.register(modEventBus);
        BlatiumSlurries.SLURRIES.register(modEventBus);
    }
}