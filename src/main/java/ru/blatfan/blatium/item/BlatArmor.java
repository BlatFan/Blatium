package ru.blatfan.blatium.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import ru.blatfan.blatium.Blatium;
import ru.blatfan.blatium.init.BlatiumArmorMaterial;

import java.util.List;

public class BlatArmor extends ArmorItem {
    private final BlatiumArmorMaterial material;
    
    public BlatArmor(BlatiumArmorMaterial armorMaterial, ArmorItem.Type type) {
        super(armorMaterial, type, new Item.Properties().fireResistant().rarity(armorMaterial==BlatiumArmorMaterial.BLATIUM ? Blatium.RARITY_BLATIUM : Blatium.RARITY_NLIUM));
        this.material=armorMaterial;
    }
    
    @Override
    public Component getName(ItemStack p_41458_) {
        return super.getName(p_41458_).copy().withStyle(style -> style.withColor(material==BlatiumArmorMaterial.BLATIUM ? Blatium.COLOR_BLATIUM : Blatium.COLOR_NLIUM));
    }
    
    @Override
    public int getMaxDamage(ItemStack stack) {
        return -1;
    }
    
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltips, TooltipFlag tooltipFlag) {
        tooltips.add(Component.empty());
        tooltips.add(Component.translatable("tooltip.blatium.unbreakable").withStyle(style -> style.withColor(material==BlatiumArmorMaterial.BLATIUM ? Blatium.COLOR_BLATIUM : Blatium.COLOR_NLIUM)));
        tooltips.add(Component.empty());
        tooltips.add(Component.translatable("tooltip.blatium."+getType().toString().toLowerCase()).withStyle(style -> style.withColor(material==BlatiumArmorMaterial.BLATIUM ? Blatium.COLOR_BLATIUM : Blatium.COLOR_NLIUM)));
        tooltips.add(Component.empty());
        
        super.appendHoverText(itemStack, level, tooltips, tooltipFlag);
    }
    
    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }
    
    @Override
    public boolean canBeDepleted() {
        return false;
    }
    
    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return true;
    }
    
    @Override
    public BlatiumArmorMaterial getMaterial() {
        return this.material;
    }
    
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        if (pEquipmentSlot == this.type.getSlot()) {
            return this.material.getSlotToAttributeMap().get(pEquipmentSlot);
        } else {
            return ImmutableMultimap.of();
        }
    }
}