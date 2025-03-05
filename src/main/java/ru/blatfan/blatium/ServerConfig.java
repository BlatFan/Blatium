package ru.blatfan.blatium;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import ru.blatfan.blatium.init.BlatiumArmorMaterial;
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
            25,
            10f,
            800,
            10,
            10
        );
        NLIUM_CONFIG = defineConfig("nlium",
                List.of(800, 1100, 1000, 800),
                50,
                50,
                1500,
            50,
            20f,
                1600,
            20,
            20
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

    private static MaterialSetConfig defineConfig(String name,
                                                  List<Integer> defenseValues, int toughness, double knockbackResistance,
                                                  int swordDamage,
                                                  int pickaxeDamage, float pickaxeDestroyTime,
                                                  int axeDamage,
                                                  int shovelDamage,
                                                  int hoeDamage
    
    ) {
        ServerConfig.BUILDER.push(name);
        var config = new MaterialSetConfig(
                ServerConfig.BUILDER.defineList("armor.values", () -> defenseValues, (x) -> true),
                ServerConfig.BUILDER.define("armor.toughness", toughness),
                ServerConfig.BUILDER.define("armor.knockbackResistance", knockbackResistance),
                ServerConfig.BUILDER.define("sword.damage", swordDamage),
                ServerConfig.BUILDER.define("pickaxe.damage", pickaxeDamage),
                ServerConfig.BUILDER.define("pickaxe.destroyTime", pickaxeDestroyTime),
                ServerConfig.BUILDER.define("axe.damage", axeDamage),
                ServerConfig.BUILDER.define("hoe.damage", hoeDamage),
                ServerConfig.BUILDER.define("shovel.damage", shovelDamage),
                ServerConfig.BUILDER.define("sword.speed", (float) 1.4),
                ServerConfig.BUILDER.define("pickaxe.speed", (float) 1.2),
                ServerConfig.BUILDER.define("axe.speed", (float) 1.0),
                ServerConfig.BUILDER.define("shovel.speed", (float) 1.0),
                ServerConfig.BUILDER.define("hoe.speed", (float) 4.0));
        ServerConfig.BUILDER.pop();
        return config;
    }

    public record MaterialSetConfig(
            ForgeConfigSpec.ConfigValue<List<? extends Integer>> defenseValues,
            ForgeConfigSpec.ConfigValue<? extends Integer> toughness,
            ForgeConfigSpec.ConfigValue<? extends Double> knockbackResistance,
            ForgeConfigSpec.ConfigValue<? extends Integer> swordDamage,
            ForgeConfigSpec.ConfigValue<? extends Integer> pickaxeDamage,
            ForgeConfigSpec.ConfigValue<? extends Float> pickaxeDestroyTime,
            ForgeConfigSpec.ConfigValue<? extends Integer> axeDamage,
            ForgeConfigSpec.ConfigValue<? extends Integer> shovelDamage,
            ForgeConfigSpec.ConfigValue<? extends Integer> hoeDamage,
            ForgeConfigSpec.ConfigValue<? extends Float> swordSpeed,
            ForgeConfigSpec.ConfigValue<? extends Float> pickaxeSpeed,
            ForgeConfigSpec.ConfigValue<? extends Float> axeSpeed,
            ForgeConfigSpec.ConfigValue<? extends Float> shovelSpeed,
            ForgeConfigSpec.ConfigValue<? extends Float> hoeSpeed
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
        for (BlatiumArmorMaterial value : BlatiumArmorMaterial.values()) {
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
