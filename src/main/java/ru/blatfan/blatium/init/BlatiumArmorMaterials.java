package ru.blatfan.blatium.init;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import ru.blatfan.blatium.ServerConfig;

import java.util.EnumMap;
import java.util.UUID;
import java.util.function.Supplier;

public enum BlatiumArmorMaterials implements ArmorMaterial {
   BLATIUM("blatium", ServerConfig.BLATIUM_CONFIG, 85, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(ItemTags.create(new ResourceLocation("forge:ingots/blatium")))),
   NLIUM("nlium", ServerConfig.NLIUM_CONFIG, 125, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(ItemTags.create(new ResourceLocation("forge:ingots/nlium"))));
   
   private final String name;
   private final int enchantmentValue;
   private final SoundEvent sound;
   private final Ingredient repairIngredient;
   private final ServerConfig.MaterialSetConfig config;
   
   BlatiumArmorMaterials(String pName, ServerConfig.MaterialSetConfig config, int pEnchantmentValue, SoundEvent pSound, Supplier<Ingredient> pRepairIngredient) {
      this.name = pName;
       this.enchantmentValue = pEnchantmentValue;
      this.sound = pSound;
      this.repairIngredient = pRepairIngredient.get();
      this.config = config;
      slotToAttributeMap = null;
   }
   
   private EnumMap<EquipmentSlot, Multimap<Attribute, AttributeModifier>> slotToAttributeMap;
   
   public EnumMap<EquipmentSlot, Multimap<Attribute, AttributeModifier>> getSlotToAttributeMap() {
      if (slotToAttributeMap == null) {
         slotToAttributeMap = makeSlotToAttributeMap();
      }
      return slotToAttributeMap;
   }
   
   private EnumMap<EquipmentSlot, Multimap<Attribute, AttributeModifier>> makeSlotToAttributeMap() {
      return Util.make(new EnumMap<>(EquipmentSlot.class), (map) -> {
         map.put(EquipmentSlot.FEET, makeAttributeMap(ArmorItem.Type.BOOTS));
         map.put(EquipmentSlot.LEGS, makeAttributeMap(ArmorItem.Type.LEGGINGS));
         map.put(EquipmentSlot.CHEST, makeAttributeMap(ArmorItem.Type.CHESTPLATE));
         map.put(EquipmentSlot.HEAD, makeAttributeMap(ArmorItem.Type.HELMET));
      });
   }
   
   public void reload() {
      this.slotToAttributeMap = null;
   }
   
   private Multimap<Attribute, AttributeModifier> makeAttributeMap(ArmorItem.Type slot) {
      ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
      UUID uuid = ArmorItem.ARMOR_MODIFIER_UUID_PER_TYPE.get(slot);
      double defense = this.config.getDefenseFor(slot.getSlot());
      double toughness = this.config.toughness().get();
      double knockbackResistance = this.config.knockbackResistance().get();
      if (defense != 0) {
         builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", defense, AttributeModifier.Operation.ADDITION));
      }
      if (toughness != 0) {
         builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", toughness, AttributeModifier.Operation.ADDITION));
      }
      if (knockbackResistance != 0) {
         builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor  knockback resistance", knockbackResistance, AttributeModifier.Operation.ADDITION));
      }
      
      return builder.build();
   }
   
   public int getDurabilityForType(ArmorItem.Type p_266745_) {
      return -1;
   }
   
   public int getDefenseForType(ArmorItem.Type type) {
      return -1;
   }
   
   public int getEnchantmentValue() {
      return this.enchantmentValue;
   }
   
   public SoundEvent getEquipSound() {
      return this.sound;
   }
   
   @Override
   public Ingredient getRepairIngredient() {
      return repairIngredient;
   }
   
   public String getName() {
      return this.name;
   }
   
   public float getToughness() {
      return -1;
   }
   
   public float getKnockbackResistance() {
      return -1;
   }
}
