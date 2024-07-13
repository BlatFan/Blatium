package ru.blatfan.blatium.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class BlatItem extends Item {
    public BlatItem(){
        super(new Item.Properties().fireResistant().rarity(Rarity.EPIC));
    }
}
