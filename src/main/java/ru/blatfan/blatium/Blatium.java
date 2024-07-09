package ru.blatfan.blatium;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ru.blatfan.blatium.compact.mekanism.BlatiumSlurries;
import ru.blatfan.blatium.init.BlatiumBlocks;
import ru.blatfan.blatium.init.BlatiumItems;
import ru.blatfan.blatium.init.BlatiumTab;

@Mod(Blatium.MODID)
public class Blatium {
    public static final String MODID = "blatium";
    
    public static boolean MEKANISM = false;
    
    public Blatium() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ServerConfig.SPEC, "blatfan/blatium-common.toml");
        
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        BlatiumItems.REGISTRY.register(modEventBus);
        BlatiumBlocks.REGISTRY.register(modEventBus);
        
        MEKANISM = ModList.get().isLoaded("mekanism");
        
        if(MEKANISM)
            BlatiumSlurries.SLURRIES.register(modEventBus);
        
        BlatiumTab.REGISTRY.register(modEventBus);
    }
}
