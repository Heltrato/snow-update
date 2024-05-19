package net.technic.snow_update.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.VegetationPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DripstoneClusterConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.worldgen.IceStalactiteFeature;
import net.technic.snow_update.worldgen.features.SnowLayerFeature;

public class SnowFeaturesRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.Keys.FEATURES, SnowUpdate.MOD_ID);
    

    public static final RegistryObject<IceStalactiteFeature> ICE_STALACTITE_FEATURE = FEATURES.register("ice_stalactite", 
    ()-> new IceStalactiteFeature(DripstoneClusterConfiguration.CODEC));
    
    public static final RegistryObject<SnowLayerFeature> SNOW_LAYER_FEATURE = FEATURES.register("snow_layer_feature", 
    ()-> new SnowLayerFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<VegetationPatchFeature> ICE_PATCH_FLOOR_FEATURE = FEATURES.register("ice_patch_floor_feature", 
    ()-> new VegetationPatchFeature(VegetationPatchConfiguration.CODEC));

    public static final RegistryObject<VegetationPatchFeature> ICE_PATCH_CEILING_FEATURE = FEATURES.register("ice_patch_ceiling_feature",
    ()-> new VegetationPatchFeature(VegetationPatchConfiguration.CODEC));

    public static final RegistryObject<VegetationPatchFeature> SNOW_PATCH_FLOOR_FEATURE = FEATURES.register("snow_patch_floor_feature",
    ()-> new VegetationPatchFeature(VegetationPatchConfiguration.CODEC));

    public static final RegistryObject<VegetationPatchFeature> SNOW_PATCH_CEILING_FEATURE = FEATURES.register("snow_patch_ceiling_feature",
    ()-> new VegetationPatchFeature(VegetationPatchConfiguration.CODEC));

    public static final RegistryObject<VegetationPatchFeature> POWDER_SNOW_PATCH_FLOOR_FEATURE = FEATURES.register("powder_snow_patch_floor_feature",
    ()-> new VegetationPatchFeature(VegetationPatchConfiguration.CODEC));

    public static final RegistryObject<VegetationPatchFeature> POWDER_SNOW_PATCH_CEILING_FEATURE = FEATURES.register("powder_snow_patch_ceiling_feature",
    ()-> new VegetationPatchFeature(VegetationPatchConfiguration.CODEC));

    public static final RegistryObject<VegetationPatchFeature> KORISTONE_PATCH_FLOOR_FEATURE = FEATURES.register("koristone_patch_floor_feature",
    ()-> new VegetationPatchFeature(VegetationPatchConfiguration.CODEC));

    public static final RegistryObject<VegetationPatchFeature> KORISTONE_PATCH_CEILING_FEATURE = FEATURES.register("koristone_patch_ceiling_feature",
    ()-> new VegetationPatchFeature(VegetationPatchConfiguration.CODEC));

    public static final RegistryObject<VegetationPatchFeature> FRIGIDITE_PATCH_FLOOR_FEATURE = FEATURES.register("frigidite_patch_floor_feature",
    ()-> new VegetationPatchFeature(VegetationPatchConfiguration.CODEC));

    public static final RegistryObject<VegetationPatchFeature> FRIGIDITE_PATCH_CEILING_FEATURE = FEATURES.register("frigidite_patch_ceiling_feature",
    ()-> new VegetationPatchFeature(VegetationPatchConfiguration.CODEC));


    public static void register(IEventBus pBus){
        FEATURES.register(pBus);
    }

    
}
