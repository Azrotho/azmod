package fr.azrotho.azmod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.azrotho.azmod.item.ModItems;
import fr.azrotho.azmod.item.ModItemsGroups;

public class AzMod implements ModInitializer {
	public static final String MODID = "azmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing AzMod...");
		ModItemsGroups.registerModItemsGroups();
		ModItems.registerModItems();
	}
}