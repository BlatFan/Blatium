package ru.blatfan.blatium.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import ru.blatfan.blatium.Blatium;
import ru.blatfan.blatium.ServerConfig;
import ru.blatfan.blatium.init.BlatiumArmorMaterial;

import java.util.List;
import java.util.Locale;

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
        if(ServerConfig.FULL_SET.get())tooltips.add(Component.translatable("tooltip.blatium.full_set."+material.getName())
            .withStyle(style -> style.withColor(material==BlatiumArmorMaterial.BLATIUM ? Blatium.COLOR_BLATIUM : Blatium.COLOR_NLIUM)));
        
        if(getType()==Type.HELMET && !ServerConfig.HELMET.get()) return;
        if(getType()==Type.CHESTPLATE && !ServerConfig.CHESTPLATE.get()) return;
        if(getType()==Type.LEGGINGS && !ServerConfig.LEGGINGS.get()) return;
        if(getType()==Type.BOOTS && !ServerConfig.BOOTS.get()) return;
        
        tooltips.add(Component.empty());
        tooltips.add(Component.translatable("tooltip.blatium."+getType().toString().toLowerCase()).withStyle(style -> style.withColor(material==BlatiumArmorMaterial.BLATIUM ? Blatium.COLOR_BLATIUM : Blatium.COLOR_NLIUM)));
        
        super.appendHoverText(itemStack, level, tooltips, tooltipFlag);
    }
    
    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return String.format(Locale.ROOT, "%s:textures/models/armor/%s_layer_%d%s.png", Blatium.MODID, material.getName(), slot == EquipmentSlot.LEGS ? 2 : 1, type == null ? "" : String.format(Locale.ROOT, "_%s", type));
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