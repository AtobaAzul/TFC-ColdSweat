package net.atobaazul.tfc_coldsweat;

import net.atobaazul.tfc_coldsweat.config.TFCColdSweatConfig;
import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.*;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import net.minecraft.resources.ResourceLocation;

@Mod(TFCColdSweat.MOD_ID)
public final class TFCColdSweat {

	public static final Logger LOG = LogUtils.getLogger();
	public static final String MOD_ID = "tfc_coldsweat";

	public TFCColdSweat(final ModContainer modContainer, final IEventBus modBus, final Dist dist) {
		// I likely won't even use these but better have these handy in case I do need to.
		modContainer.registerConfig(Type.COMMON, TFCColdSweatConfig.COMMON.spec());
		modContainer.registerConfig(Type.CLIENT, TFCColdSweatConfig.CLIENT.spec());
		modContainer.registerConfig(Type.SERVER, TFCColdSweatConfig.SERVER.spec());
		modContainer.registerConfig(Type.STARTUP, TFCColdSweatConfig.STARTUP.spec());

		modBus.register(TFCColdSweat.class);

		TFCColdSweatForgeEvents.init(NeoForge.EVENT_BUS);
	}


	/**
	 * Shorthand for {@code ResourceLocation.fromNamespaceAndPath(MOD_ID, path)}
	 */
	public static ResourceLocation location(final String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	/**
	 * Helper for creating modid prepended lang keys
	 */
	public static String lang(final String langKey) {
		return MOD_ID + "." + langKey;
	}
}