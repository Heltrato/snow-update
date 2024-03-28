package net.technic.snow_update;

import com.mojang.logging.LogUtils;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.technic.snow_update.Items.SnowUpdateItemProperties;
import net.technic.snow_update.entity.client.IceChunkEntityRenderer;
import net.technic.snow_update.entity.client.JuvenileYetiRenderer;
import net.technic.snow_update.entity.client.SnowBallRenderer;
import net.technic.snow_update.entity.client.TitanYetiRenderer;
import net.technic.snow_update.registry.SnowBlockRegistry;
import net.technic.snow_update.registry.SnowEffectsRegistry;
import net.technic.snow_update.registry.SnowEntityRegistry;
import net.technic.snow_update.registry.SnowFeaturesRegistry;
import net.technic.snow_update.registry.SnowItemsRegistry;
import net.technic.snow_update.registry.SnowMemoryModulesRegistry;
import net.technic.snow_update.registry.SnowSoundsRegistry;

import org.slf4j.Logger;

@Mod(SnowUpdate.MOD_ID)
public class SnowUpdate
{
    public static final String MOD_ID = "snow_update";
    
    private static final Logger LOGGER = LogUtils.getLogger();

    public SnowUpdate()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        SnowBlockRegistry.register(modEventBus);
        SnowEntityRegistry.register(modEventBus);
        SnowItemsRegistry.register(modEventBus);
        SnowSoundsRegistry.register(modEventBus);
        SnowEffectsRegistry.register(modEventBus);
        SnowMemoryModulesRegistry.register(modEventBus);
        SnowFeaturesRegistry.register(modEventBus);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(()-> {
                EntityRenderers.register(SnowEntityRegistry.JUVENILE_YETI.get(), JuvenileYetiRenderer::new);
                EntityRenderers.register(SnowEntityRegistry.SNOW_BALL.get(), SnowBallRenderer::new);
                EntityRenderers.register(SnowEntityRegistry.TITAN_YETI.get(), TitanYetiRenderer::new);
                EntityRenderers.register(SnowEntityRegistry.ICE_CHUNK.get(), IceChunkEntityRenderer::new);
                SnowUpdateItemProperties.addItemProperties();
            });
        }
    }
}
