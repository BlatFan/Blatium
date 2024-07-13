package ru.blatfan.blatium.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import ru.blatfan.blatium.Blatium;

import java.util.List;

public class BlatiumToolTiers {
    public static final TagKey<Block> BLATIUM_TOOL_TAG = BlockTags.create(new ResourceLocation("mineable/pickaxe"));
    public static final TagKey<Block> NLIUM_TOOL_TAG = BlockTags.create(new ResourceLocation("mineable/pickaxe"));
    public static final TagKey<Block> BLATIUM_TIER_TAG = BlockTags.create(new ResourceLocation("forge", "needs_blatium_tool"));
    public static final TagKey<Block> NLIUM_TIER_TAG = BlockTags.create(new ResourceLocation("forge", "needs_nlium_tool"));


    public static final Tier BLATIUM_TIER = TierSortingRegistry.registerTier(
            new ForgeTier(10, -1, 10, 110.0F, 85, BLATIUM_TIER_TAG, () -> Ingredient.of(BlatiumItems.BLATIUM_INGOT.get())),
            new ResourceLocation(Blatium.MODID,"blatium"),
            List.of(Tiers.NETHERITE), List.of());
    public static final Tier NLIUM_TIER = TierSortingRegistry.registerTier(
            new ForgeTier(15, -1, 20, 110.0F, 85, NLIUM_TIER_TAG, () -> Ingredient.of(BlatiumItems.NLIUM_INGOT.get())),
            new ResourceLocation(Blatium.MODID,"nlium"),
            List.of(BlatiumToolTiers.BLATIUM_TIER), List.of());

}
