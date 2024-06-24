package fr.azrotho.azmod.item;

import fr.azrotho.azmod.AzMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemsGroups {
    public static final ItemGroup AZMOD_GROUP = Registry.register(
        Registries.ITEM_GROUP,
        new Identifier(AzMod.MODID, "azmod_items"),
        FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.SAPPHIRE)).entries((displayContext, entries) -> {
                entries.add(ModItems.SAPPHIRE);
                entries.add(ModItems.ENDER_SWORD);
                entries.add(ModItems.DIAMOND_MAGNET);
            })
            .displayName(Text.translatable("itemGroup.azmod_items"))
            .build()
    );

    public static void registerModItemsGroups() {
        AzMod.LOGGER.info("Registering AzMod items groups...");

    }
    
}
