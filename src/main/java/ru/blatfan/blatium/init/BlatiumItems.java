package ru.blatfan.blatium.init;

import net.minecraft.world.item.*;
import net.minecraftforge.registries.*;
import ru.blatfan.blatium.Blatium;
import ru.blatfan.blatium.item.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlatiumItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Blatium.MODID);
    
    public static Map<String, RegistryObject<Item>> BLOCKS = new HashMap<>();
    
    public static List<RegistryObject<Item>> NORMAL = new ArrayList<>();
    public static List<RegistryObject<Item>> MEKANISM = new ArrayList<>();
    
    public static final RegistryObject<Item> BLATIUM_CLUMP = registerMek("blatium_clump", BlatItem::new);
    public static final RegistryObject<Item> BLATIUM_SHARD = registerMek("blatium_shard", BlatItem::new);
    public static final RegistryObject<Item> BLATIUM_CRYSTAL = registerMek("blatium_crystal", BlatItem::new);
    public static final RegistryObject<Item> BLATIUM_DIRTY = registerMek("blatium_dirty", BlatItem::new);
    public static final RegistryObject<Item> BLATIUM_DUST = register("blatium_dust", BlatItem::new);
    public static final RegistryObject<Item> BLATIUM_GEAR = register("blatium_gear", BlatItem::new);
    public static final RegistryObject<Item> BLATIUM_INGOT = register("blatium_ingot", BlatItem::new);
    public static final RegistryObject<Item> BLATIUM_NUGGET = register("blatium_nugget", BlatItem::new);
    public static final RegistryObject<Item> BLATIUM_PLATE = register("blatium_plate", BlatItem::new);
    public static final RegistryObject<Item> BLATIUM_ROD = register("blatium_rod", BlatItem::new);
    public static final RegistryObject<Item> RAW_BLATIUM = register("raw_blatium", BlatItem::new);
    public static final RegistryObject<Item> BLATIUM_UPGRADE_SMITHING_TEMPLATE = register("blatium_upgrade_smithing_template", BlatItem::new);
    
    public static final RegistryObject<Item> BLATIUM_SWORD = register("blatium_sword", BlatiumSword::new);
    public static final RegistryObject<Item> BLATIUM_PICKAXE = register("blatium_pickaxe", BlatiumPickaxe::new);
    public static final RegistryObject<Item> BLATIUM_AXE = register("blatium_axe", BlatiumAxe::new);
    public static final RegistryObject<Item> BLATIUM_SHOVEL = register("blatium_shovel", BlatiumShovel::new);
    public static final RegistryObject<Item> BLATIUM_HOE = register("blatium_hoe", BlatiumHoe::new);
    
    public static final RegistryObject<Item> BLATIUM_HELMET = register("blatium_helmet", () -> new BlatArmor(BlatiumArmorMaterial.BLATIUM, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> BLATIUM_CHESTPLATE = register("blatium_chestplate", () -> new BlatArmor(BlatiumArmorMaterial.BLATIUM, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> BLATIUM_LEGGINGS = register("blatium_leggings", () -> new BlatArmor(BlatiumArmorMaterial.BLATIUM, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> BLATIUM_BOOTS = register("blatium_boots", () -> new BlatArmor(BlatiumArmorMaterial.BLATIUM, ArmorItem.Type.BOOTS));
    
    public static final RegistryObject<Item> NLIUM_CLUMP = registerMek("nlium_clump", BlatItem::new);
    public static final RegistryObject<Item> NLIUM_SHARD = registerMek("nlium_shard", BlatItem::new);
    public static final RegistryObject<Item> NLIUM_CRYSTAL = registerMek("nlium_crystal", BlatItem::new);
    public static final RegistryObject<Item> NLIUM_DIRTY = registerMek("nlium_dirty", BlatItem::new);
    public static final RegistryObject<Item> NLIUM_DUST = register("nlium_dust", BlatItem::new);
    public static final RegistryObject<Item> NLIUM_GEAR = register("nlium_gear", BlatItem::new);
    public static final RegistryObject<Item> NLIUM_INGOT = register("nlium_ingot", BlatItem::new);
    public static final RegistryObject<Item> NLIUM_NUGGET = register("nlium_nugget", BlatItem::new);
    public static final RegistryObject<Item> NLIUM_PLATE = register("nlium_plate", BlatItem::new);
    public static final RegistryObject<Item> NLIUM_ROD = register("nlium_rod", BlatItem::new);
    public static final RegistryObject<Item> RAW_NLIUM = register("raw_nlium", BlatItem::new);
    public static final RegistryObject<Item> NLIUM_UPGRADE_SMITHING_TEMPLATE = register("nlium_upgrade_smithing_template", BlatItem::new);
    
    public static final RegistryObject<Item> NLIUM_SWORD = register("nlium_sword", NliumSword::new);
    public static final RegistryObject<Item> NLIUM_PICKAXE = register("nlium_pickaxe", NliumPickaxe::new);
    public static final RegistryObject<Item> NLIUM_AXE = register("nlium_axe", NliumAxe::new);
    public static final RegistryObject<Item> NLIUM_SHOVEL = register("nlium_shovel", NliumShovel::new);
    public static final RegistryObject<Item> NLIUM_HOE = register("nlium_hoe", NliumHoe::new);
    
    public static final RegistryObject<Item> NLIUM_HELMET = register("nlium_helmet", () -> new BlatArmor(BlatiumArmorMaterial.NLIUM, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> NLIUM_CHESTPLATE = register("nlium_chestplate", () -> new BlatArmor(BlatiumArmorMaterial.NLIUM, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> NLIUM_LEGGINGS = register("nlium_leggings", () -> new BlatArmor(BlatiumArmorMaterial.NLIUM, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> NLIUM_BOOTS = register("nlium_boots", () -> new BlatArmor(BlatiumArmorMaterial.NLIUM, ArmorItem.Type.BOOTS));
    
    public static RegistryObject<Item> register(String id, Supplier<Item> itemSupplier){
        RegistryObject<Item> reg = REGISTRY.register(id, itemSupplier);
        NORMAL.add(reg);
        return reg;
    }
    public static RegistryObject<Item> registerMek(String id, Supplier<Item> itemSupplier){
        RegistryObject<Item> reg = REGISTRY.register(id, itemSupplier);
        MEKANISM.add(reg);
        return reg;
    }
}
