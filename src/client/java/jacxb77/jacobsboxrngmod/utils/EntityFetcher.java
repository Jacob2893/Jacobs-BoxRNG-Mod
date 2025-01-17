package jacxb77.jacobsboxrngmod.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class EntityFetcher {

    public static List<Entity> getArmorStands(PlayerEntity playerEntity) {
        ClientWorld world = MinecraftClient.getInstance().world;
        // Return empty list if world is not loaded
        if(world == null) {
            return List.of();
        }

        // Get all loaded in entities and filter out the ones that are not armor stands
        List<Entity> armorStands = new ArrayList<>();
        Iterable<Entity> entities = world.getEntities();
        entities.forEach(entity -> {
            if(entity.getType().equals(EntityType.ARMOR_STAND)) {
                armorStands.add(entity);
            }
        });

        return armorStands;
    }

    // Returns boss hologram text as ex. "Zombies: 120/250"
    public static List<String> getFilteredArmorStandNames() {
        List<String> names = new ArrayList<>();

        // Filter out armor stands and only return the ones which are boss holograms
        for (Entity armorStand : getArmorStands(MinecraftClient.getInstance().player)) {
            if(!armorStand.getName().getString().contains("Zombies Killed: ")
                    && !armorStand.getName().getString().contains("Spiders Killed: ")
                    && !armorStand.getName().getString().contains("Slimes Killed: ")
                    && !armorStand.getName().getString().contains("Amalgamations Killed: ")
                    && !armorStand.getName().getString().contains("Lava Crawlers Killed: ")
                    && !armorStand.getName().getString().contains("Depth Walkers Killed: ")) continue;
            if (armorStand.getName().getString().contains("Killed:")) {
                names.add(armorStand.getName().getString().replaceAll(" Killed", ""));
            }
        }

        return names;
    }
}
