package net.adamcowell14.graytan.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KeyBind {
    private String translationKey;
    private short modifier;

    public static KeyBind of(ModifierKeyCode modifierKeyCode) {
        return new KeyBind(modifierKeyCode.getKeyCode().getTranslationKey(), modifierKeyCode.getModifier().getValue());
    }

    public ModifierKeyCode toModifierKeyCode() {
        return ModifierKeyCode.of(InputUtil.fromTranslationKey(translationKey), Modifier.of(modifier));
    }

    public String toString() {
        return this.getLocalizedName().getString();
    }

    public Text getLocalizedName() {
        Text base = InputUtil.fromTranslationKey(translationKey).getLocalizedText();
        Modifier modifier = Modifier.of(this.modifier);

        if (modifier.hasShift()) {
            base = Text.translatable("modifier.cloth-config.shift", base);
        }

        if (modifier.hasControl()) {
            base = Text.translatable("modifier.cloth-config.ctrl", base);
        }

        if (modifier.hasAlt()) {
            base = Text.translatable("modifier.cloth-config.alt", base);
        }

        return base;
    }
}