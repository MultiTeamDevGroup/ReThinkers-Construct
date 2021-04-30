package multiteam.rethinkers.main.items;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.rethinkers.main.Registration;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> ESSENCE_BERRY = Registration.ITEMS.register("essence_berry", () -> new EssenceBerryItem(new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> IRON_OREBERRY = Registration.ITEMS.register("iron_oreberry", () -> new Item(new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> GOLD_OREBERRY = Registration.ITEMS.register("gold_oreberry", () -> new Item(new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> COPPER_OREBERRY = Registration.ITEMS.register("copper_oreberry", () -> new Item(new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> TIN_OREBERRY = Registration.ITEMS.register("tin_oreberry", () -> new Item(new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ALUMINIUM_OREBERRY = Registration.ITEMS.register("aluminium_oreberry", () -> new Item(new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON)));


    public static void register() {

    }

}
