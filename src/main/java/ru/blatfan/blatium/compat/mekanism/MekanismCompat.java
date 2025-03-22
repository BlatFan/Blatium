package ru.blatfan.blatium.compat.mekanism;

import mekanism.api.chemical.infuse.InfuseType;
import mekanism.common.registration.impl.InfuseTypeDeferredRegister;
import mekanism.common.registration.impl.InfuseTypeRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.blatium.Blatium;
import ru.blatfan.blatium.item.BlatItem;

import java.util.function.Supplier;

public class MekanismCompat {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Blatium.MODID);
    
    public static final RegistryObject<Item> ENRICHED_BLATIUM = registerMek("enriched_blatium", BlatItem::blatium);
    public static final RegistryObject<Item> BLATIUM_CLUMP = registerMek("blatium_clump", BlatItem::blatium);
    public static final RegistryObject<Item> BLATIUM_SHARD = registerMek("blatium_shard", BlatItem::blatium);
    public static final RegistryObject<Item> BLATIUM_CRYSTAL = registerMek("blatium_crystal", BlatItem::blatium);
    public static final RegistryObject<Item> BLATIUM_DIRTY = registerMek("blatium_dirty", BlatItem::blatium);
    
    public static final RegistryObject<Item> ENRICHED_NLIUM = registerMek("enriched_nlium", BlatItem::nlium);
    public static final RegistryObject<Item> NLIUM_CLUMP = registerMek("nlium_clump", BlatItem::nlium);
    public static final RegistryObject<Item> NLIUM_SHARD = registerMek("nlium_shard", BlatItem::nlium);
    public static final RegistryObject<Item> NLIUM_CRYSTAL = registerMek("nlium_crystal", BlatItem::nlium);
    public static final RegistryObject<Item> NLIUM_DIRTY = registerMek("nlium_dirty", BlatItem::nlium);
    
    public static final RegistryObject<Item> CREATIVE_ALLOY = registerMek("creative_alloy", BlatItem::blatium);
    public static final RegistryObject<Item> CREATIVE_CONTROL_CIRCUIT = registerMek("creative_control_circuit", BlatItem::blatium);
    
    private static RegistryObject<Item> registerMek(String id, Supplier<Item> itemSupplier){
        return REGISTRY.register(id, itemSupplier);
    }
    
    public static final InfuseTypeDeferredRegister INFUSE_TYPES = new InfuseTypeDeferredRegister(Blatium.MODID);
    public static final InfuseTypeRegistryObject<InfuseType> BLATIUM = INFUSE_TYPES.register("blatium", Blatium.COLOR_BLATIUM);
    public static final InfuseTypeRegistryObject<InfuseType> NLIUM = INFUSE_TYPES.register("nlium", Blatium.COLOR_NLIUM);
    
    public static void init(IEventBus modEventBus){
        REGISTRY.register(modEventBus);
        BlatiumSlurries.SLURRIES.register(modEventBus);
        INFUSE_TYPES.register(modEventBus);
    }
}