package fr.azrotho.azmod.item;

import fr.azrotho.azmod.AzMod;
import fr.azrotho.azmod.item.custom.DiamondMagnetItem;
import fr.azrotho.azmod.item.custom.EnderSwordItem;
import fr.azrotho.azmod.item.custom.GrapplingHookItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SAPPHIRE = registerItem("sapphire", new Item(new Item.Settings()));
    public static final Item ENDER_SWORD = registerItem("ender_sword", new EnderSwordItem(ModToolMaterial.ENDER, 8, 1.6f, new Item.Settings().maxDamage(1000)));
    public static final Item DIAMOND_MAGNET = registerItem("diamond_magnet", new DiamondMagnetItem(new Item.Settings().maxDamage(500)));
    public static final Item GRAPPLING_HOOK = registerItem("grappling_hook", new GrapplingHookItem(new Item.Settings().maxDamage(64)));
    public static final Item HOOK = registerItem("hook", new Item(new Item.Settings()));


    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AzMod.MODID, name), item);
    }

    public static void registerModItems() {
        AzMod.LOGGER.info("Registering AzMod items...");
    }
}
