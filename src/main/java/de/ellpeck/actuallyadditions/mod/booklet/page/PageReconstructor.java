/*
 * This file ("PageReconstructor.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.booklet.page;

import de.ellpeck.actuallyadditions.api.booklet.internal.GuiBookletBase;
import de.ellpeck.actuallyadditions.api.recipe.LensConversionRecipe;
import de.ellpeck.actuallyadditions.mod.booklet.gui.GuiBooklet;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.config.GuiUtils;

import java.util.List;

public class PageReconstructor extends BookletPage{

    private final LensConversionRecipe recipe;

    public PageReconstructor(int localizationKey, LensConversionRecipe recipe){
        super(localizationKey);
        this.recipe = recipe;
    }

    @Override
    public void drawScreenPre(GuiBookletBase gui, int startX, int startY, int mouseX, int mouseY, float partialTicks){
        super.drawScreenPre(gui, startX, startY, mouseX, mouseY, partialTicks);

        gui.mc.getTextureManager().bindTexture(GuiBooklet.RES_LOC_GADGETS);
        GuiUtils.drawTexturedModalRect(startX+30, startY+10, 80, 146, 68, 48, 0);

        gui.renderScaledAsciiString("(Atomic Reconstructor Recipe)", startX+12, startY+63, 0, false, 0.65F);

        PageTextOnly.renderTextToPage(gui, this, startX+6, startY+88);
    }

    @Override
    public void initGui(GuiBookletBase gui, int startX, int startY){
        super.initGui(gui, startX, startY);

        if(this.recipe != null){
            gui.addOrModifyItemRenderer(this.recipe.inputStack, startX+30+1, startY+10+13, 1F, true);
            gui.addOrModifyItemRenderer(this.recipe.outputStack, startX+30+47, startY+10+13, 1F, false);
        }
    }

    @Override
    public void getItemStacksForPage(List<ItemStack> list){
        super.getItemStacksForPage(list);

        if(this.recipe != null){
            list.add(this.recipe.outputStack);
        }
    }

    @Override
    public int getSortingPriority(){
        return 20;
    }
}
