package ru.blatfan.blatium.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.blatium.Blatium;

public class BlatBlockItem extends BlockItem {
    public BlatBlockItem(Block block, boolean blatium){
        super(block, new Properties().fireResistant().rarity(blatium ? Blatium.RARITY_BLATIUM : Blatium.RARITY_NLIUM));
    }
    public BlatBlockItem(RegistryObject<Block> blockRegistryObject, boolean blatium){
        this(blockRegistryObject.get(), blatium);
    }
}
