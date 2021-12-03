package com.tangosix.cateyes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.lwjgl.glfw.GLFW;

public class CatEyes implements ModInitializer {

	private static KeyBinding toggleBTN;

	@Override
	public void onInitialize() {

		toggleBTN = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"Toggle Cat Vision", //the keybinding's name
				InputUtil.Type.KEYSYM, //KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_V, "key.categories.misc" //the keybinding's category.
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (toggleBTN.wasPressed()) {
				if (client.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
					client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
				} else {
					client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 118900));
				}
			}
		});
	}

}