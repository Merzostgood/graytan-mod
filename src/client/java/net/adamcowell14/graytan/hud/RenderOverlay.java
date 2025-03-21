package net.adamcowell14.graytan.hud;

import net.adamcowell14.graytan.GraytanClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;

public class RenderOverlay {
    public static void RenderGameOverlayEvent(DrawContext context, RenderTickCounter delta) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (GraytanClient.CONFIG.anti_tp) {
            Text text = Text.of("Graytan active");
            context.drawTextWithShadow(client.textRenderer, text.asOrderedText(), (MinecraftClient.getInstance().getWindow().getScaledWidth()) - 10 - client.textRenderer.getWidth(text), 10, 0xffffff);
        }
    }
}
