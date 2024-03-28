package net.technic.snow_update.datagen;

import java.util.List;
import java.util.Set;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.technic.snow_update.datagen.loot.SnowUpdateBlockLootTable;

public class SnowUpdateLootTableProvider {
    public static LootTableProvider create(PackOutput pOutput) {
        return new LootTableProvider(pOutput, Set.of(), List.of(new LootTableProvider.SubProviderEntry(SnowUpdateBlockLootTable::new, LootContextParamSets.BLOCK)));
    }
}
