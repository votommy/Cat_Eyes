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
				"Toggle Cat Eyes", //the keybinding's name
				InputUtil.Type.KEYSYM, //KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_V, // The keycode of the key
				"Cat Eyes" //the keybinding's category.
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			Runnable runnable = () -> {
				while (client.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
					try {
						Thread.sleep(5000);
					}
					catch (InterruptedException ex) {
						System.out.println(ex);
					}

					if (client.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
						client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 420));
					}
				}
			};

			Runnable cureBlindness = () -> {
				while (client.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
					if (client.player.hasStatusEffect(StatusEffects.DARKNESS)) {
						client.player.removeStatusEffect(StatusEffects.DARKNESS);
					}
					if (client.player.hasStatusEffect(StatusEffects.BLINDNESS)) {
						client.player.removeStatusEffect(StatusEffects.BLINDNESS);
					}
				}
			};

			while (toggleBTN.wasPressed()) {
				if (client.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
					client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
				} else {
					client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 420));

					if (client.player.hasStatusEffect(StatusEffects.DARKNESS)) {
						client.player.removeStatusEffect(StatusEffects.DARKNESS);
					}
					if (client.player.hasStatusEffect(StatusEffects.BLINDNESS)) {
						client.player.removeStatusEffect(StatusEffects.BLINDNESS);
					}

					Thread thread = new Thread(runnable);
					thread.start();

					Thread thread2 = new Thread(cureBlindness);
					thread2.start();
				}
			}
		});
	}
}