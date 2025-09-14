package net.atobaazul.tfc_coldsweat;

import net.neoforged.fml.loading.moddiscovery.ModFileInfo;
import net.neoforged.fml.loading.FMLLoader;

public class CompatManager {
    public static final ModFileInfo FIRMALIFE_ENABLED = FMLLoader.getLoadingModList().getModFileById("firmalife");
    public static final ModFileInfo CASTIRONGRILL_ENABLED = FMLLoader.getLoadingModList().getModFileById("castirongrill");
    public static final ModFileInfo TFC_ENABLED = FMLLoader.getLoadingModList().getModFileById("tfc");
    public static final ModFileInfo ALEKISHIPS_ENABLED = FMLLoader.getLoadingModList().getModFileById("alekiships");
}


