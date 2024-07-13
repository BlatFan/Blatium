package ru.blatfan.blatium.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.*;
import ru.blatfan.blatium.Blatium;

public class BlatiumTab {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Blatium.MODID);
    
    public static final RegistryObject<CreativeModeTab> TAB = REGISTRY.register("blatium", () -> CreativeModeTab
        .builder()
        .title(Component.translatable("itemGroup.blatium.blatium"))
        .icon(() -> BlatiumItems.BLATIUM_INGOT.get().getDefaultInstance())
        .displayItems((parameters, output) -> {
            for(RegistryObject<Item> item : BlatiumItems.NORMAL)
                output.accept(item.get());
            for(RegistryObject<Block> block : BlatiumBlocks.REGISTRY.getEntries())
                output.accept(block.get());
            
            if(Blatium.MEKANISM)
                for (RegistryObject<Item> item : BlatiumItems.MEKANISM)
                    output.accept(item.get());
        })
        .build()
    );
}
