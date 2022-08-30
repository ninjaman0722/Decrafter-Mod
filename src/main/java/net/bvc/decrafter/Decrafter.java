package net.bvc.decrafter;

import net.bvc.decrafter.block.ModBlocks;
import net.bvc.decrafter.block.entity.ModBlockEntities;
import net.bvc.decrafter.item.ModItems;
import net.bvc.decrafter.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Decrafter implements ModInitializer {
	public static final String MOD_ID = "decrafter";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerAllScreenHandlers();
	}
}
