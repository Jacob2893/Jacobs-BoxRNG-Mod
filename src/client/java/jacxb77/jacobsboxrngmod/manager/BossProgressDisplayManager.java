package jacxb77.jacobsboxrngmod.manager;

import jacxb77.jacobsboxrngmod.model.BossProgressTextModel;
import jacxb77.jacobsboxrngmod.type.ProgressColors;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import org.joml.Matrix4f;

import java.util.List;

public class BossProgressDisplayManager {

    // Objects for each boss hologram
    private static BossProgressTextModel zombie = new BossProgressTextModel("Zombie", 0, 250, 0xFFFFFF, false);
    private static BossProgressTextModel spider = new BossProgressTextModel("Spider", 0, 500, 0xFFFFFF, false);
    private static BossProgressTextModel slime = new BossProgressTextModel("Slime", 0, 750, 0xFFFFFF, false);
    private static BossProgressTextModel amalgamation = new BossProgressTextModel("Amalgamation", 0, 1000, 0xFFFFFF, false);
    private static BossProgressTextModel lavaCrawler = new BossProgressTextModel("Lava Crawler", 0, 1500, 0xFFFFFF, false);
    private static BossProgressTextModel depthWalker = new BossProgressTextModel("Depth Walker", 0, 2500, 0xFFFFFF, false);

    public static void renderArmorStandNames(MatrixStack matrices, List<String> armorStandNames) {
        MinecraftClient client = MinecraftClient.getInstance();
        // Ensure player exists
        if (client.player == null) return;

        // VertexConsumers and Matrix for rendering text
        VertexConsumerProvider.Immediate vertexConsumers = client.getBufferBuilders().getEntityVertexConsumers();
        Matrix4f matrix = matrices.peek().getPositionMatrix();

        // Variables to check if a boss hologram is in range
        boolean z = false;
        boolean sp = false;
        boolean sl = false;
        boolean a = false;
        boolean lc = false;
        boolean dw = false;

        // Render each boss's progress as text on screen
        for (String name : armorStandNames) {
            String values = name;
            BossProgressTextModel bossProgressText = null;

            if(name.contains("Zombies")) {
                z = true;
                bossProgressText = zombie;
                bossProgressText.setInPlayersRange(true);
                values = values.replaceFirst("Zombies: ", "");
            }
            if(name.contains("Spiders")) {
                sp = true;
                bossProgressText = spider;
                values = values.replaceFirst("Spiders: ", "");
            }
            if(name.contains("Slimes")) {
                sl = true;
                bossProgressText = slime;
                values = values.replaceFirst("Slimes: ", "");
            }
            if(name.contains("Amalgamations")) {
                a = true;
                bossProgressText = amalgamation;
                values = values.replaceFirst("Amalgamations: ", "");
            }
            if(name.contains("Lava Crawlers")) {
                lc = true;
                bossProgressText = lavaCrawler;
                values = values.replaceFirst("Lava Crawlers: ", "");
            }
            if(name.contains("Depth Walkers")) {
                dw = true;
                bossProgressText = depthWalker;
                values = values.replaceFirst("Depth Walkers: ", "");
            }

            String[] valueArray = values.split("/");
            int progress = Integer.parseInt(valueArray[0]);
            int total = Integer.parseInt(valueArray[1]);
            int color = ProgressColors.WHITE.color;

            // Determine each line's color based on progress to spawning the boss
            if((float) progress / total < 0.5 ) {
                color = ProgressColors.GREEN.color;
            } else if((float) progress / total < 0.75) {
                color = ProgressColors.YELLOW.color;
            } else if((float) progress / total < 0.9) {
                color = ProgressColors.ORANGE.color;
            }
            if((float) progress / total >= 0.9 && total - progress > 10) {
                color = ProgressColors.RED.color;

                // Plays a bell sound if the boss's spawn progress is exactly 90%
                if((float) progress / total == 0.9) {
                    client.player.playSound(SoundEvents.BLOCK_BELL_USE, 2f, 1f);
                }
            }
            if(total - progress <= 10) {
                color = ProgressColors.DARK_RED.color;
                // Plays a bell sound if the boss will spawn after exactly 10 mobs are killed
                if(total - progress == 10) {
                    client.player.playSound(SoundEvents.BLOCK_BELL_USE, 2f, 1f);
                }
            }

            // Assign values to objects for each boss if not null
            if(bossProgressText != null) {
                bossProgressText.setProgress(progress);
                bossProgressText.setTotal(total);
                bossProgressText.setColor(color);
                bossProgressText.setInPlayersRange(true);
            }
        }

        // Set a boss progress line as Out Of Range if not in player's render range
        if(!z) zombie.setInPlayersRange(false);
        if(!sp) spider.setInPlayersRange(false);
        if(!sl) slime.setInPlayersRange(false);
        if(!a) amalgamation.setInPlayersRange(false);
        if(!lc) lavaCrawler.setInPlayersRange(false);
        if(!dw) depthWalker.setInPlayersRange(false);

        // Render progress for each boss as text on the players screen
        client.textRenderer.draw(zombie.getBossProgressText(), 2, client.getWindow().getHeight() / 4, zombie.getColor(), true, matrix, (VertexConsumerProvider) vertexConsumers, TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
        client.textRenderer.draw(spider.getBossProgressText(), 2, client.getWindow().getHeight() / 4 + 10, spider.getColor(), true, matrix, (VertexConsumerProvider) vertexConsumers, TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
        client.textRenderer.draw(slime.getBossProgressText(), 2, client.getWindow().getHeight() / 4 + 20, slime.getColor(), true, matrix, (VertexConsumerProvider) vertexConsumers, TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
        client.textRenderer.draw(amalgamation.getBossProgressText(), 2, client.getWindow().getHeight() / 4 + 30, amalgamation.getColor(), true, matrix, (VertexConsumerProvider) vertexConsumers, TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
        client.textRenderer.draw(lavaCrawler.getBossProgressText(), 2, client.getWindow().getHeight() / 4 + 40, lavaCrawler.getColor(), true, matrix, (VertexConsumerProvider) vertexConsumers, TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
        client.textRenderer.draw(depthWalker.getBossProgressText(), 2, client.getWindow().getHeight() / 4 + 50, depthWalker.getColor(), true, matrix, (VertexConsumerProvider) vertexConsumers, TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);

        vertexConsumers.draw();
    }
}
