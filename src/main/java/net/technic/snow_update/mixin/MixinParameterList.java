package net.technic.snow_update.mixin;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import com.mojang.datafixers.util.Pair;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.technic.snow_update.worldgen.IExtendedParameterList;
import net.technic.snow_update.worldgen.api.Region;
import net.technic.snow_update.worldgen.api.RegionType;
import net.technic.snow_update.worldgen.api.Regions;
import net.technic.snow_update.worldgen.area.Area;
import net.technic.snow_update.worldgen.noise.LayeredNoiseUtil;

@Mixin(Climate.ParameterList.class)
public abstract class MixinParameterList<T> implements IExtendedParameterList<T> {
    @Shadow
    @Final
    private List<Pair<Climate.ParameterPoint, T>> values;

    @Shadow
    public abstract T findValue(Climate.TargetPoint target);

    @Unique
    private boolean initialized = false;
    @Unique
    private boolean treesPopulated = false;
    @Unique
    private Area uniqueness;
    @SuppressWarnings("rawtypes")
    @Unique
    private Climate.RTree[] uniqueTrees;

    @Override
    public void initializeForTerraBlender(RegistryAccess registryAccess, RegionType regionType, long seed)
    {
        // We don't want to initialize multiple times
        if (this.initialized)
            return;

        this.uniqueness = LayeredNoiseUtil.uniqueness(registryAccess, regionType, seed);
        this.uniqueTrees = new Climate.RTree[Regions.getCount(regionType)];

        Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registries.BIOME);

        for (Region region : Regions.get(regionType))
        {
            int regionIndex = Regions.getIndex(regionType, region.getName());

            // Use the existing values for index 0, rather than those from the region. This is for datapack support.
            if (regionIndex == 0)
            {
                this.uniqueTrees[0] = Climate.RTree.create(this.values);
            }
            else
            {
                List<Pair<Climate.ParameterPoint, Holder<Biome>>> pairs = new ArrayList<>();
                region.addBiomes(biomeRegistry, pair -> pairs.add(pair.mapSecond(biomeRegistry::getHolderOrThrow)));

                // We can't create an RTree if there are no values present.
                if (!pairs.isEmpty())
                    this.uniqueTrees[regionIndex] = Climate.RTree.create(pairs);
            }
        }

        this.treesPopulated = true;

        // Mark as initialized
        this.initialized = true;
    }

    @Override
    public int getUniqueness(int x, int y, int z)
    {
        return this.uniqueness.get(x, z);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Climate.RTree getTree(int uniqueness)
    {
        return this.uniqueTrees[uniqueness];
    }

    @Override
    public int getTreeCount()
    {
        return this.uniqueTrees.length;
    }

    @Override
    public boolean isInitialized()
    {
        return this.initialized && this.treesPopulated;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findValuePositional(Climate.TargetPoint target, int x, int y, int z)
    {
        // Fallback on findValue if we are uninitialized (may be the case for non-TerraBlender dimensions)
        if (!this.initialized)
            return this.findValue(target);

        if (!this.treesPopulated)
            throw new RuntimeException("Attempted to call findValuePositional whilst trees remain unpopulated!");

        int uniqueness = this.getUniqueness(x, y, z);
        Holder<Biome> biome = (Holder<Biome>)this.getTree(uniqueness).search(target, Climate.RTree.Node::distance);

        if (biome.is(Region.DEFERRED_PLACEHOLDER))
            return (T)this.uniqueTrees[0].search(target, Climate.RTree.Node::distance);
        else
            return (T)biome;
    }
}
