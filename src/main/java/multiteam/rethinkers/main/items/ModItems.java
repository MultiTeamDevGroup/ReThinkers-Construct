package multiteam.rethinkers.main.items;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.rethinkers.main.Registration;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> ESSENCE_BERRY = Registration.ITEMS.register("essence_berry", () -> new Item(new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON)));


    public static void register() {

    }

}
