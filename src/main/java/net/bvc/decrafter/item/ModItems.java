package net.bvc.decrafter.item;

import net.bvc.decrafter.Decrafter;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item ENDERVEIRS = registerItem("enderveirs",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Decrafter.MOD_ID, name), item );
    }
    public static void registerModItems() {
        Decrafter.LOGGER.debug("Registering Mod Items for " + Decrafter.MOD_ID);
    }

}
