package ru.blatfan.blatium.events;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.blatfan.blatium.ServerConfig;
import ru.blatfan.blatium.init.BlatiumArmorMaterials;
import ru.blatfan.blatium.item.BlatArmor;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ArmorEvents {
	@SubscribeEvent
	public static void onPlayerFall(LivingFallEvent event) {
		Item boots = event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem();
		if(boots instanceof BlatArmor && ServerConfig.BOOTS.get())
			event.setCanceled(true);
	}



	@SubscribeEvent
	public static void onEntityHurt(LivingAttackEvent event) {
		if (!event.getEntity().getCommandSenderWorld().isClientSide) {
			Item helmet = event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem();
			Item chestplate = event.getEntity().getItemBySlot(EquipmentSlot.CHEST).getItem();
			Item leggings = event.getEntity().getItemBySlot(EquipmentSlot.LEGS).getItem();
			Item boots = event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem();
			
			if (helmet instanceof BlatArmor) {
				if (event.getSource().is(DamageTypes.FLY_INTO_WALL) && ServerConfig.HELMET.get()) {
					event.setCanceled(true);
				}
				if (event.getSource().is(DamageTypes.DROWN) && ServerConfig.HELMET.get()) {
					event.getEntity().setAirSupply(event.getEntity().getMaxAirSupply());
					event.setCanceled(true);
				}
			}
			if (chestplate instanceof BlatArmor) {
				if (((event.getSource().is(DamageTypes.IN_FIRE)) || (event.getSource().is(DamageTypes.ON_FIRE)) || (event.getSource().is(DamageTypes.LAVA)) || (event.getSource().is(DamageTypes.HOT_FLOOR)))
					&& ServerConfig.CHESTPLATE.get()) {
					event.getEntity().clearFire();
					event.setCanceled(true);
				}
			}
			if (leggings instanceof BlatArmor) {
				if (event.getSource().is(DamageTypes.EXPLOSION) && ServerConfig.LEGGINGS.get()) {
					event.setCanceled(true);
				}
			}
			if(event.isCanceled()) return;
			if(helmet instanceof BlatArmor h &&
			chestplate instanceof BlatArmor c &&
			leggings instanceof BlatArmor l &&
			boots instanceof BlatArmor b && ServerConfig.FULL_SET.get()){
				if(h.getMaterial()== BlatiumArmorMaterials.BLATIUM &&
					c.getMaterial()== BlatiumArmorMaterials.BLATIUM &&
					l.getMaterial()== BlatiumArmorMaterials.BLATIUM &&
					b.getMaterial()== BlatiumArmorMaterials.BLATIUM) {
					if (Math.random()>0.75)
						event.setCanceled(true);
				}
				if(h.getMaterial()== BlatiumArmorMaterials.NLIUM &&
					c.getMaterial()== BlatiumArmorMaterials.NLIUM &&
					l.getMaterial()== BlatiumArmorMaterials.NLIUM &&
					b.getMaterial()== BlatiumArmorMaterials.NLIUM) {
					if (Math.random()>0.25)
						event.setCanceled(true);
				}
			}
		}

	}
}

