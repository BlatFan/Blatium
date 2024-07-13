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

    public static MaterialSetConfig BLATIUM_CONFIG;
    public static MaterialSetConfig NLIUM_CONFIG;

    static {
        BUILDER.push("Materials Config");
        BUILDER.comment("Changing armor and tools values requires game restart");

       BLATIUM_CONFIG = defineConfig(BUILDER, "blatium",
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
        NLIUM_CONFIG = defineConfig(BUILDER, "nlium",
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
        SPEC = BUILDER.build();
    }

    private static MaterialSetConfig defineConfig(ForgeConfigSpec.Builder builder, String name, List<Integer> defenseValues, int toughness, double knockbackResistance,
                                                  int swordDamage, float swordSpeed,
                                                  int pickaxeDamage, float pickaxeSpeed,float pickaxeDestroyTime,
                                                  int axeDamage, float axeSpeed,
                                                  int shovelDamage, float shovelSpeed,
                                                  int hoeDamage, float hoeSpeed
                                                  ) {
        builder.push(name);
        var config = new MaterialSetConfig(
                builder.defineList("armor.values", () -> defenseValues, (x) -> true),
                builder.define("armor.toughness", toughness),
                builder.define("armor.knockbackResistance", knockbackResistance),
                builder.define("sword.damage", swordDamage),
                builder.define("pickaxe.damage", pickaxeDamage),
                builder.define("pickaxe.destroyTime", pickaxeDestroyTime),
                builder.define("axe.damage", axeDamage),
                builder.define("hoe.damage", hoeDamage),
                builder.define("shovel.damage", shovelDamage),
                builder.define("sword.speed", swordSpeed),
                builder.define("pickaxe.speed", pickaxeSpeed),
                builder.define("axe.speed", axeSpeed),
                builder.define("shovel.speed", shovelSpeed),
                builder.define("hoe.speed", hoeSpeed));
        builder.pop();
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
