package ru.blatfan.blatium.compact.mekanism;

import mekanism.common.resource.BlockResourceInfo;
import mekanism.common.resource.IResource;
import mekanism.common.resource.ResourceType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public enum BlatiumResource implements IResource {
    BLATIUM("blatium", 13718243, ItemTags.create(new ResourceLocation("forge", "ores/blatium"))),
    NLIUM("nlium", 2547336,ItemTags.create(new ResourceLocation("forge", "ores/nlium")));
    
    private final String name;
    private final int tint;
    private final Supplier<TagKey<Item>> oreTag;
    private final boolean isVanilla;
    private final BlockResourceInfo resourceBlockInfo;
    private final BlockResourceInfo rawResourceBlockInfo;
    
    BlatiumResource(String name, int color, TagKey<Item> oreTag) {
        this(name, color, () -> oreTag,true, null,null);
    }
    BlatiumResource(String name, int tint, Supplier<TagKey<Item>> oreTag, boolean isVanilla, BlockResourceInfo resourceBlockInfo, BlockResourceInfo rawResourceBlockInfo) {
        this.name = name;
        this.tint = tint;
        this.oreTag = oreTag;
        this.isVanilla = isVanilla;
        this.resourceBlockInfo = resourceBlockInfo;
        this.rawResourceBlockInfo = rawResourceBlockInfo;
    }

    @Override
    public String getRegistrySuffix() {
        return name;
    }

    public int getTint() {
        return tint;
    }

    public TagKey<Item> getOreTag() {
        return oreTag.get();
    }

    public boolean has(ResourceType type) {
        return type != ResourceType.ENRICHED && (!isVanilla || !type.isVanilla());
    }

    public boolean isVanilla() {
        return isVanilla;
    }

    @Nullable
    public BlockResourceInfo getResourceBlockInfo() {
        return resourceBlockInfo;
    }

    @Nullable
    public BlockResourceInfo getRawResourceBlockInfo() {
        return rawResourceBlockInfo;
    }
}
