package ru.blatfan.blatium.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.*;
import ru.blatfan.blatium.Blatium;
import ru.blatfan.blatium.compact.mekanism.BlatiumMek;

public class BlatiumTab {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Blatium.MODID);
    
    public static final RegistryObject<CreativeModeTab> TAB = REGISTRY.register("blatium", () -> CreativeModeTab
        .builder()
        .title(Component.translatable("itemGroup.blatium.blatium").withStyle(style -> style.withColor(Blatium.COLOR_BLATIUM)))
        .icon(() -> BlatiumItems.BLATIUM_INGOT.get().getDefaultInstance())
        .displayItems((parameters, output) -> {
            for(RegistryObject<Item> item : BlatiumItems.REGISTRY.getEntries())
                output.accept(item.get());
            for(RegistryObject<Block> block : BlatiumBlocks.REGISTRY.getEntries())
                output.accept(block.get());
            
            if(ModList.get().isLoaded("mekanism"))
                for(RegistryObject<Item> item : BlatiumMek.REGISTRY.getEntries())
                    output.accept(item.get());
        })
        .build()
    );
}
