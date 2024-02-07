package me.oringo.oringoclient.qolfeatures.module.impl.render;

import me.oringo.oringoclient.OringoClient;
import me.oringo.oringoclient.events.GuiChatEvent;
import me.oringo.oringoclient.qolfeatures.module.Module;
import me.oringo.oringoclient.qolfeatures.module.settings.impl.BooleanSetting;
import me.oringo.oringoclient.qolfeatures.module.settings.impl.NumberSetting;
import me.oringo.oringoclient.ui.hud.impl.ItemLoreDisplay;
import me.oringo.oringoclient.utils.RenderUtils;
import me.oringo.oringoclient.utils.SkyblockUtils;
import me.oringo.oringoclient.utils.font.Fonts;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LoreDisplay extends Module {
    public NumberSetting scale = new NumberSetting("Scale", 1, 0.5, 2, 0.1);
    public NumberSetting opacity = new NumberSetting("Opacity", 150, 0, 255, 1);
    public BooleanSetting customFont = new BooleanSetting("Custom Font", false);

    public NumberSetting x;
    public NumberSetting y;

    public ItemLoreDisplay itemLoreDisplay = new ItemLoreDisplay();

    public LoreDisplay()
    {
        super("Lore Display", Category.RENDER);

        this.x = new NumberSetting("X1234", 0.0, -100000.0, 100000.0, 1.0E-5, a -> true);
        this.y = new NumberSetting("Y1234", 100.0, -100000.0, 100000.0, 1.0E-5, a -> true);

        addSettings(
                x,
                y,
                scale,
                opacity,
                customFont
        );

        itemLoreDisplay.setPosition(this.x.getValue(), this.y.getValue());
        itemLoreDisplay.setHidden(true);
    }

    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post event) {
        if (this.isToggled() && event.type.equals(RenderGameOverlayEvent.ElementType.HOTBAR) && OringoClient.mc.thePlayer != null) {
            render(itemLoreDisplay.getX(), itemLoreDisplay.getY());
        }
    }

    @SubscribeEvent
    public void onChatEvent(final GuiChatEvent event) {
        if (!this.isToggled()) {
            return;
        }
        final ItemLoreDisplay component = itemLoreDisplay;
        if (event instanceof GuiChatEvent.MouseClicked) {
            if (component.isHovered(event.mouseX, event.mouseY)) {
                component.startDragging();
            }
        }
        else if (event instanceof GuiChatEvent.MouseReleased) {
            component.stopDragging();
        }
        else if (event instanceof GuiChatEvent.Closed) {
            component.stopDragging();
        }
        else if (event instanceof GuiChatEvent.DrawChatEvent) {}
    }

    public void render(double x, double y)
    {
        itemLoreDisplay.drawScreen();

        ItemStack item = OringoClient.mc.thePlayer.getHeldItem();
        if (item == null)
        {
            itemLoreDisplay.setHidden(true);
            itemLoreDisplay.setSize(0, 0);
            return;
        }

        itemLoreDisplay.setHidden(false);

        List<String> lore = new ArrayList<>();

        lore.add(SkyblockUtils.getDisplayName(item));
        if (item.getTagCompound() == null) return;
        if (!item.getTagCompound().hasKey("display")) return;
        NBTTagCompound c = item.getTagCompound().getCompoundTag("display");
        if (!c.hasKey("Lore"))
            return;
        NBTTagList compound = c.getTagList("Lore", 8);
        double longest = OringoClient.mc.fontRendererObj.getStringWidth(lore.get(0));

        if (customFont.isEnabled())
            longest = Fonts.robotoMedium.getStringWidth(lore.get(0));

        for (int i = 0; i < compound.tagCount(); i++)
        {
            String str = compound.get(i).toString();
            str = str.substring(1, str.length()-1);
            if (customFont.isEnabled() && Fonts.robotoMedium.getStringWidth(str) > longest)
                longest = Fonts.robotoMedium.getStringWidth(str);
            else if (OringoClient.mc.fontRendererObj.getStringWidth(str) > longest)
                longest = OringoClient.mc.fontRendererObj.getStringWidth(str);
            lore.add(str);
        }

        int height = (lore.size()*10)+4;
        if (customFont.isEnabled())
            height = (lore.size()*(Fonts.robotoMedium.getHeight()+2))+4;

        GlStateManager.pushMatrix();
        GlStateManager.scale(scale.getValue(), scale.getValue(), scale.getValue());

        itemLoreDisplay.setSize(longest, height);

        RenderUtils.drawBorderedRoundedRect((float) itemLoreDisplay.getX(), (float) itemLoreDisplay.getY(), (float) longest + 5, height, 5, 2, new Color(103, 103, 103, (int) opacity.getValue()).getRGB(), OringoClient.clickGui.getColor().getRGB());
        int yy = 2;
        for (String str : lore)
        {
            if (customFont.isEnabled())
            {
                Fonts.robotoMedium.drawStringWithShadow(str, itemLoreDisplay.getX() + 2, yy + itemLoreDisplay.getY(), Color.white.getRGB());
                yy += Fonts.robotoMedium.getHeight() + 2;
            }
            else
            {
                OringoClient.mc.fontRendererObj.drawString(str, (int) (itemLoreDisplay.getX() + 2), (int) (yy + itemLoreDisplay.getY()), Color.white.getRGB());
                yy += 10;
            }

        }
        GlStateManager.popMatrix();

        this.x.setValue(itemLoreDisplay.getX());
        this.y.setValue(itemLoreDisplay.getX() + itemLoreDisplay.getHeight());
    }
}
