package net.technic.snow_update.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DripstoneClusterConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.worldgen.IceStalactiteFeature;

public class SnowFeaturesRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, SnowUpdate.MOD_ID);

    public static final RegistryObject<IceStalactiteFeature> ICE_STALACTITE_FEATURE = FEATURES.register("ice_stalactite_feature", 
    ()-> new IceStalactiteFeature(DripstoneClusterConfiguration.CODEC));


    public void register(IEventBus pBus){
        FEATURES.register(pBus);
    }
}
