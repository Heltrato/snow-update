package net.technic.snow_update.entity.client;

import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.technic.snow_update.SnowUpdate;
import net.technic.snow_update.entity.SnowUpdateBoats;
import net.technic.snow_update.entity.SnowUpdateChestBoats;

public class SnowUpdateBoatRenderer extends BoatRenderer{
    private final Map<SnowUpdateBoats.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public SnowUpdateBoatRenderer(Context pContext, boolean pChestBoat) {
        super(pContext, pChestBoat);
        this.boatResources = Stream.of(SnowUpdateBoats.Type.values()).collect(ImmutableMap.toImmutableMap((pType) -> pType, 
        (pType) -> Pair.of(new ResourceLocation(SnowUpdate.MOD_ID, getTexture(pType, pChestBoat)), this.createBoatModel(pContext, pType, pChestBoat))));
    }

    private static String getTexture(SnowUpdateBoats.Type pType, boolean pChestBoat) {
        return pChestBoat ? "textures/entity/chest_boat/" + pType.getName() + ".png" : "textures/entity/boat/" + pType.getName() + ".png";
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context pContext, SnowUpdateBoats.Type pType, boolean pChestBoat) {
        ModelLayerLocation modellayerlocation = pChestBoat ? SnowUpdateBoatRenderer.createChestBoatModelName(pType) : SnowUpdateBoatRenderer.createBoatModelName(pType);
        ModelPart modelpart = pContext.bakeLayer(modellayerlocation);
        return pChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    public static ModelLayerLocation createBoatModelName(SnowUpdateBoats.Type pType) {
        return createLocation("boat/" + pType.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(SnowUpdateBoats.Type pType) {
        return createLocation("chest_boat/" + pType.getName(), "main");
    }

    private static ModelLayerLocation createLocation(String pPath, String pModel) {
        return new ModelLayerLocation(new ResourceLocation(SnowUpdate.MOD_ID, pPath), pModel);
    }

    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        if(boat instanceof SnowUpdateBoats modBoat) {
            return this.boatResources.get(modBoat.getModVariant());
        } else if(boat instanceof SnowUpdateChestBoats modChestBoatEntity) {
            return this.boatResources.get(modChestBoatEntity.getModVariant());
        } else {
            return null;
        }
    }

}
