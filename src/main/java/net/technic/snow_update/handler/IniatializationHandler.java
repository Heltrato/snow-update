package net.technic.snow_update.handler;

import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.technic.snow_update.worldgen.utils.LevelUtils;

public class IniatializationHandler {
    public static void onServerAboutToStart(ServerAboutToStartEvent pEvent) {
        LevelUtils.initializeOnServerStart(pEvent.getServer());
    }
}
