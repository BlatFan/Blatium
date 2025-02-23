package ru.blatfan.blatium.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import ru.blatfan.blatium.Blatium;

public class BlatItem extends Item {
    private final boolean blatium;
    protected BlatItem(boolean blatium){
        super(new Item.Properties().fireResistant().rarity(blatium ? Blatium.RARITY_BLATIUM : Blatium.RARITY_NLIUM));
        this.blatium = blatium;
    }
    
    @Override
    public Component getName(ItemStack p_41458_) {
        return super.getName(p_41458_).copy().withStyle(style -> style.withColor(blatium ? Blatium.COLOR_BLATIUM : Blatium.COLOR_NLIUM));
    }
    
    public static BlatItem blatium(){
        return new BlatItem(true);
    }
    
    public static BlatItem nlium(){
        return new BlatItem(false);
    }
}
