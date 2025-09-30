package net.atobaazul.tfc_coldsweat.datagen.providers;

import net.atobaazul.tfc_coldsweat.common.TFCColdSweatItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static net.atobaazul.tfc_coldsweat.TFCColdSweat.MOD_ID;

public class TFCColdSweatLangProvider extends LanguageProvider {
    public TFCColdSweatLangProvider(PackOutput output) {
        super(output, MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.addItem(TFCColdSweatItems.BURLAP_CHESTPLATE, "Burlap Shirt");
        this.addItem(TFCColdSweatItems.BURLAP_HELMET, "Burlap Hat");
        this.addItem(TFCColdSweatItems.BURLAP_LEGGINGS, "Burlap Pants");

        this.addItem(TFCColdSweatItems.WOOL_CHESTPLATE, "Wool Shirt");
        this.addItem(TFCColdSweatItems.WOOL_HELMET, "Wool Hat");
        this.addItem(TFCColdSweatItems.WOOL_LEGGINGS, "Wool Pants");

        this.addItem(TFCColdSweatItems.SILK_CHESTPLATE, "Silk Shirt");
        this.addItem(TFCColdSweatItems.SILK_HELMET, "Silk Hat");
        this.addItem(TFCColdSweatItems.SILK_LEGGINGS, "Silk Shorts");
    }
}
