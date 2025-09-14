package net.atobaazul.tfc_coldsweat;

import net.neoforged.bus.api.*;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public final class TFCColdSweatForgeEvents {

	public static void init(final IEventBus eventBus) {
		// Register all static @SubscribeEvent annotated event methods
		eventBus.register(TFCColdSweatForgeEvents.class);
	}
}