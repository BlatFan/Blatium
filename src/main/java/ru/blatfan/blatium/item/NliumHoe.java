package ru.blatfan.blatium.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import ru.blatfan.blatium.ServerConfig;
import ru.blatfan.blatium.init.BlatiumToolTiers;

import java.util.List;
import java.util.UUID;

public class NliumHoe extends ShovelItem {
    private final ServerConfig.MaterialSetConfig config = ServerConfig.NLIUM_CONFIG;
    public NliumHoe() {
        super(BlatiumToolTiers.NLIUM_TIER, 0, 0, new Properties().fireResistant().rarity(Rarity.EPIC));
    }
    
    private Multimap<Attribute, AttributeModifier> makeAttributeMap() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = UUID.randomUUID();
        double attackDamage = this.config.hoeDamage().get();
        double attackSpeed = this.config.hoeSpeed().get();
        if (attackDamage != 0) {
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Attack damage", attackDamage, AttributeModifier.Operation.ADDITION));
        }
        if (attackSpeed != 0) {
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "Attack speed", attackSpeed, AttributeModifier.Operation.ADDITION));
        }
        
        return builder.build();
    }
    
    private static Multimap<Attribute, AttributeModifier> attribites = null;
    
    public static void reload(){
        attribites=null;
    }
    
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        if(attribites==null) attribites=makeAttributeMap();
        if(equipmentSlot==EquipmentSlot.MAINHAND) return attribites;
        return super.getDefaultAttributeModifiers(equipmentSlot);
    }
    
    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return true;
    }
    
    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.empty());
        p_41423_.add(Component.translatable("tooltip.blatium.unbreakable").withStyle(ChatFormatting.LIGHT_PURPLE));
        p_41423_.add(Component.empty());
        
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }
}
