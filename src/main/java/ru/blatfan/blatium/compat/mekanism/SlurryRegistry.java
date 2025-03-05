package ru.blatfan.blatium.compat.mekanism;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import mekanism.common.registration.WrappedDeferredRegister;
import mekanism.common.registration.impl.SlurryRegistryObject;
import ru.blatfan.blatium.Blatium;

import java.util.function.UnaryOperator;

public class SlurryRegistry extends WrappedDeferredRegister<Slurry> {
    public SlurryRegistry() {
        super(Blatium.MODID, MekanismAPI.SLURRY_REGISTRY_NAME);
    }

    public SlurryRegistryObject<Slurry, Slurry> register(BlatiumResource resource) {
        return register(resource.getRegistrySuffix(), builder -> builder.tint(resource.getTint()).ore(resource.getOreTag()));
    }

    public SlurryRegistryObject<Slurry, Slurry> register(String baseName, UnaryOperator<SlurryBuilder> builderModifier) {
        return new SlurryRegistryObject<>(internal.register("dirty_" + baseName, () -> new Slurry(builderModifier.apply(SlurryBuilder.dirty()))),
                internal.register("clean_" + baseName, () -> new Slurry(builderModifier.apply(SlurryBuilder.clean()))));
    }
}