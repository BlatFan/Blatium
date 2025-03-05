package ru.blatfan.blatium;

import net.minecraft.util.FastColor;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ru.blatfan.blatium.compat.CreateCompat;
import ru.blatfan.blatium.compat.mekanism.MekanismCompat;
import ru.blatfan.blatium.init.BlatiumBlocks;
import ru.blatfan.blatium.init.BlatiumItems;
import ru.blatfan.blatium.init.BlatiumTab;

import java.awt.*;

@Mod(Blatium.MODID)
public class Blatium {
    public static final String MODID = "blatium";
    
    public static int COLOR_BLATIUM = getColor(new Color(156, 39, 176));
    public static int COLOR_NLIUM = getColor(new Color(63, 81, 181));
    public static Rarity RARITY_BLATIUM = Rarity.create("blatium", style -> style.withColor(COLOR_BLATIUM));
    public static Rarity RARITY_NLIUM = Rarity.create("nlium", style -> style.withColor(COLOR_NLIUM));
    
    private static int getColor(Color color) {
        return FastColor.ARGB32.color(color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue());
    }
    
    public Blatium() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ServerConfig.SPEC, "blatfan/blatium-common.toml");
        
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        BlatiumItems.REGISTRY.register(modEventBus);
        BlatiumBlocks.REGISTRY.register(modEventBus);
        
        if(ModList.get().isLoaded("mekanism")) new MekanismCompat(modEventBus);
        if(ModList.get().isLoaded("create")) new CreateCompat(modEventBus);
        
        BlatiumTab.REGISTRY.register(modEventBus);
    }
}
