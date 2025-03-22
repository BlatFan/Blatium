package ru.blatfan.blatium;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import ru.blatfan.blatium.init.BlatiumArmorMaterials;
import ru.blatfan.blatium.item.*;

import java.util.List;

@Mod.EventBusSubscriber(modid = Blatium.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ServerConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    static ForgeConfigSpec SPEC;

    public static final MaterialSetConfig BLATIUM_CONFIG;
    public static final MaterialSetConfig NLIUM_CONFIG;
    public static final ForgeConfigSpec.BooleanValue HELMET, CHESTPLATE, LEGGINGS, BOOTS, FULL_SET;

    static {
        BUILDER.push("Materials Config");
        BUILDER.comment("Changing armor and tools values requires game restart");
        
        BLATIUM_CONFIG = defineConfig("blatium",
            List.of(400, 550, 500, 400),
            25,
            25,
            750,
            1.4f,
            25,
            1.2f,
            10f,
            800,
            1f,
            10,
            1f,
            10,
            4f
        );
        NLIUM_CONFIG = defineConfig("nlium",
            List.of(800, 1100, 1000, 800),
            50,
            50,
            1500,
            1.4f,
            50,
            1.2f,
            20f,
            1600,
            1f,
            20,
            1f,
            20,
            4f
        );
        BUILDER.pop();
        BUILDER.push("Armor Damage Immune");
        
        HELMET = BUILDER.define("helmet", true);
        CHESTPLATE = BUILDER.define("chestplate", true);
        LEGGINGS = BUILDER.define("leggings", true);
        BOOTS = BUILDER.define("boots", true);
        FULL_SET = BUILDER.define("full_set", true);
        
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
    
    private static MaterialSetConfig defineConfig(String name, List<Integer> defenseValues, int toughness, double knockbackResistance,
                                                  int swordDamage, float swordSpeed,
                                                  int pickaxeDamage, float pickaxeSpeed,float pickaxeDestroyTime,
                                                  int axeDamage, float axeSpeed,
                                                  int shovelDamage, float shovelSpeed,
                                                  int hoeDamage, float hoeSpeed
    
    ) {
        ServerConfig.BUILDER.push(name);
        var config = new MaterialSetConfig(
            ServerConfig.BUILDER.defineList("armor.values", () -> defenseValues, (x) -> true),
            ServerConfig.BUILDER.defineInRange("armor.toughness", toughness, 0, Integer.MAX_VALUE),
            ServerConfig.BUILDER.defineInRange("armor.knockbackResistance", knockbackResistance, 0, Integer.MAX_VALUE),
            ServerConfig.BUILDER.defineInRange("sword.damage", swordDamage, 0, Integer.MAX_VALUE),
            ServerConfig.BUILDER.defineInRange("pickaxe.damage", pickaxeDamage, 0, Integer.MAX_VALUE),
            ServerConfig.BUILDER.defineInRange("pickaxe.destroyTime", pickaxeDestroyTime, 0f, Integer.MAX_VALUE),
            ServerConfig.BUILDER.defineInRange("axe.damage", axeDamage, 0, Integer.MAX_VALUE),
            ServerConfig.BUILDER.defineInRange("hoe.damage", hoeDamage, 0, Integer.MAX_VALUE),
            ServerConfig.BUILDER.defineInRange("shovel.damage", shovelDamage, 0, Integer.MAX_VALUE),
            ServerConfig.BUILDER.defineInRange("sword.speed", swordSpeed, 0f, Integer.MAX_VALUE),
            ServerConfig.BUILDER.defineInRange("pickaxe.speed", pickaxeSpeed, 0f, Integer.MAX_VALUE),
            ServerConfig.BUILDER.defineInRange("axe.speed", axeSpeed, 0f, Integer.MAX_VALUE),
            ServerConfig.BUILDER.defineInRange("shovel.speed", shovelSpeed, 0f, Integer.MAX_VALUE),
            ServerConfig.BUILDER.defineInRange("hoe.speed", hoeSpeed, 0f, Integer.MAX_VALUE));
        ServerConfig.BUILDER.pop();
        return config;
    }

    public record MaterialSetConfig(
            ForgeConfigSpec.ConfigValue<List<? extends Integer>> defenseValues,
            ForgeConfigSpec.IntValue toughness,
            ForgeConfigSpec.ConfigValue<? extends Double> knockbackResistance,
            ForgeConfigSpec.IntValue swordDamage,
            ForgeConfigSpec.IntValue pickaxeDamage,
            ForgeConfigSpec.DoubleValue pickaxeDestroyTime,
            ForgeConfigSpec.IntValue axeDamage,
            ForgeConfigSpec.IntValue shovelDamage,
            ForgeConfigSpec.IntValue hoeDamage,
            ForgeConfigSpec.DoubleValue swordSpeed,
            ForgeConfigSpec.DoubleValue pickaxeSpeed,
            ForgeConfigSpec.DoubleValue axeSpeed,
            ForgeConfigSpec.DoubleValue shovelSpeed,
            ForgeConfigSpec.DoubleValue hoeSpeed
    ) {
        public double getDefenseFor(EquipmentSlot slot) {
            if (defenseValues.get().size() != 4) {
                return defenseValues.getDefault().get(slot.getIndex());
            } else {
                return defenseValues.get().get(slot.getIndex());
            }
        }
    }

    @SubscribeEvent
    public static void onReload(ModConfigEvent.Reloading event) {
        for (BlatiumArmorMaterials value : BlatiumArmorMaterials.values()) {
            value.reload();
        }
        BlatiumSword.reload();
        BlatiumPickaxe.reload();
        BlatiumAxe.reload();
        BlatiumShovel.reload();
        BlatiumHoe.reload();
        
        NliumSword.reload();
        NliumPickaxe.reload();
        NliumAxe.reload();
        NliumShovel.reload();
        NliumHoe.reload();
    }
}
