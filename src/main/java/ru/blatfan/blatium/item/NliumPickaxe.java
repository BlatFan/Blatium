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
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import ru.blatfan.blatium.Blatium;
import ru.blatfan.blatium.ServerConfig;
import ru.blatfan.blatium.init.BlatiumToolTiers;

import java.util.List;
import java.util.UUID;

public class NliumPickaxe extends PickaxeItem {
    private float destroySpeed;
    
    private final ServerConfig.MaterialSetConfig config = ServerConfig.NLIUM_CONFIG;
    public NliumPickaxe() {
        super(BlatiumToolTiers.NLIUM_TIER, 0, 0, new Properties().fireResistant().rarity(Blatium.RARITY_NLIUM));
    }
    
    @Override
    public Component getName(ItemStack p_41458_) {
        return super.getName(p_41458_).copy().withStyle(style -> style.withColor(Blatium.COLOR_NLIUM));
    }
    
    private Multimap<Attribute, AttributeModifier> makeAttributeMap() {
        destroySpeed=config.pickaxeDestroyTime().get();
        
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = UUID.randomUUID();
        double attackDamage = this.config.pickaxeDamage().get();
        double attackSpeed = this.config.pickaxeSpeed().get();
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
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack stack) {
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
        p_41423_.add(Component.translatable("tooltip.blatium.unbreakable").withStyle(style -> style.withColor(Blatium.COLOR_BLATIUM)));
        p_41423_.add(Component.empty());
        
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }
    
    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        return destroySpeed;
    }
}
