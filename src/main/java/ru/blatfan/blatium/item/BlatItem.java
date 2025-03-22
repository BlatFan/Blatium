package ru.blatfan.blatium.item;

import net.minecraft.world.item.Item;
import ru.blatfan.blatium.Blatium;

public class BlatItem extends Item {
    protected BlatItem(boolean blatium){
        super(new Item.Properties().fireResistant().rarity(blatium ? Blatium.RARITY_BLATIUM : Blatium.RARITY_NLIUM));
    }
    
    public static BlatItem blatium(){
        return new BlatItem(true);
    }
    
    public static BlatItem nlium(){
        return new BlatItem(false);
    }
}
