package ru.blatfan.blatium.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import ru.blatfan.blatium.Blatium;

public class BlatBlockItem extends BlockItem {
    private final boolean blatium;
    public BlatBlockItem(Block block, boolean blatium){
        super(block, new Properties().fireResistant().rarity(blatium ? Blatium.RARITY_BLATIUM : Blatium.RARITY_NLIUM));
        this.blatium = blatium;
    }
    public BlatBlockItem(RegistryObject<Block> blockRegistryObject, boolean blatium){
        this(blockRegistryObject.get(), blatium);
    }
    
    @Override
    public Component getName(ItemStack p_41458_) {
        return super.getName(p_41458_).copy().withStyle(style -> style.withColor(blatium ? Blatium.COLOR_BLATIUM : Blatium.COLOR_NLIUM));
    }
}
