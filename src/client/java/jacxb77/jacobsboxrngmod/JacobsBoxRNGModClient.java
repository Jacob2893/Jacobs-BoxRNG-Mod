package jacxb77.jacobsboxrngmod;

import jacxb77.jacobsboxrngmod.manager.BossProgressDisplayManager;
import jacxb77.jacobsboxrngmod.utils.EntityFetcher;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.text.Text;

import java.util.List;

public class JacobsBoxRNGModClient implements ClientModInitializer {
	private static List<String> armorStandNames = List.of(); // Holds the names of filtered armor stands
	private static int tickCounter = 0; // Counter for 20-tick updates

	@Override
	public void onInitializeClient() {
		// Registers tick end event and looks for boss hologrrams every 1 second (20 ticks)
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			// Ensure the world is loaded
			if (client.world != null) {
				tickCounter++;
				if (tickCounter >= 20) {
					tickCounter = 0;
					armorStandNames = EntityFetcher.getFilteredArmorStandNames();
				}
			}
		});

		// Register hud
		HudRenderCallback.EVENT.register((drawContext, tickDelta) -> BossProgressDisplayManager.renderArmorStandNames(drawContext.getMatrices(), armorStandNames));

		// Register the config command
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("jacobsmod").executes(context -> {
			context.getSource().sendFeedback(Text.literal("Config/GUI Command will be added soon!"));

			return 1;
		})));
	}
}