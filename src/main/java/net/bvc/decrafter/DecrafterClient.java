package net.bvc.decrafter;

import net.bvc.decrafter.screen.DecraftingScreen;
import net.bvc.decrafter.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class DecrafterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HandledScreens.register(ModScreenHandlers.DECRAFTING_SCREEN_HANDLER, DecraftingScreen::new);

    }
}
