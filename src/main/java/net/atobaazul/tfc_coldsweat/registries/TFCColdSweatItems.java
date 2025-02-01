package net.atobaazul.tfc_coldsweat.registries;

import net.dries007.tfc.common.TFCCreativeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

import static net.atobaazul.tfc_coldsweat.TFCColdSweat.MODID;


@SuppressWarnings("unused")
public final class TFCColdSweatItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MODID);

    public static final RegistryObject<Item> PREPARED_CURSED_HIDE = register("cursed_prepared_hide", () -> new Item(new Item.Properties().stacksTo(32)));
    public static final RegistryObject<Item> SCRAPED_CURSED_HIDE = register("cursed_scraped_hide", () -> new Item(new Item.Properties().stacksTo(32)));
    public static final RegistryObject<Item> SOAKED_CURSED_HIDE = register("cursed_soaked_hide", () -> new Item(new Item.Properties().stacksTo(32)));

    private static RegistryObject<Item> register(String name) {
        return register(name, () -> new Item(new Item.Properties()));
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}