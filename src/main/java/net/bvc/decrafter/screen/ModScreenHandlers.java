package net.bvc.decrafter.screen;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static ScreenHandlerType<DecraftingScreenHandler> DECRAFTING_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        DECRAFTING_SCREEN_HANDLER = new ScreenHandlerType<>(DecraftingScreenHandler::new);
    }
}
