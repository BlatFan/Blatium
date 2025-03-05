package ru.blatfan.blatium.compat.mekanism;

import mekanism.api.chemical.slurry.Slurry;
import mekanism.common.registration.impl.SlurryRegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlatiumSlurries {

    public static final SlurryRegistry SLURRIES = new SlurryRegistry();

    public static final Map<BlatiumResource, SlurryRegistryObject<Slurry, Slurry>> PROCESSED_RESOURCES = new LinkedHashMap<>();

    static {
        for (BlatiumResource resource : BlatiumResource.values()) {
            PROCESSED_RESOURCES.put(resource, SLURRIES.register(resource));
        }
    }
}
