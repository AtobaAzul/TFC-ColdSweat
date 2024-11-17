package net.atobaazul.tfc_coldsweat;

import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.moddiscovery.ModFileInfo;

public class CompatManager {
    public static final ModFileInfo FIRMALIFE_ENABLED = FMLLoader.getLoadingModList().getModFileById("firmalife");
    public static final ModFileInfo CASTIRONGRILL_ENABLED = FMLLoader.getLoadingModList().getModFileById("castirongrill");
    public static final ModFileInfo TFC_ENABLED =  FMLLoader.getLoadingModList().getModFileById("tfc");

}
