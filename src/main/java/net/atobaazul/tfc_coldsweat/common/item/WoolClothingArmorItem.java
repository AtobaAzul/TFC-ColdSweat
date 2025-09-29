package net.atobaazul.tfc_coldsweat.common.item;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

//Not really used but I'm keeping it in case I want to do anything custom.
public class WoolClothingArmorItem extends ArmorItem {
    public WoolClothingArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }
}
