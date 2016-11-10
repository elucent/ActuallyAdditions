/*
 * This file ("BookletPageAA.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.booklet.page;

import de.ellpeck.actuallyadditions.api.booklet.IBookletChapter;
import de.ellpeck.actuallyadditions.api.booklet.IBookletPage;
import de.ellpeck.actuallyadditions.api.booklet.internal.IPageGui;
import de.ellpeck.actuallyadditions.mod.util.ModUtil;
import de.ellpeck.actuallyadditions.mod.util.StringUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookletPage implements IBookletPage{

    protected IBookletChapter chapter;

    protected List<FluidStack> fluidsForPage = new ArrayList<FluidStack>();
    protected List<ItemStack> itemsForPage = new ArrayList<ItemStack>();

    protected boolean hasNoText;
    protected final HashMap<String, String> textReplacements = new HashMap<String, String>();
    protected final int localizationKey;

    public BookletPage(int localizationKey){
        this.localizationKey = localizationKey;
    }

    @Override
    public ItemStack[] getItemStacksForPage(){
        return this.itemsForPage.toArray(new ItemStack[this.itemsForPage.size()]);
    }

    @Override
    public FluidStack[] getFluidStacksForPage(){
        return this.fluidsForPage.toArray(new FluidStack[this.fluidsForPage.size()]);
    }

    @Override
    public IBookletChapter getChapter(){
        return this.chapter;
    }

    @Override
    public void setChapter(IBookletChapter chapter){
        this.chapter = chapter;
    }

    @Override
    public IPageGui createGui(){
        return null;
    }

    @Override
    public String getInfoText(){
        if(this.hasNoText){
            return null;
        }

        String base = StringUtil.localize("booklet."+ModUtil.MOD_ID+".chapter."+this.chapter.getIdentifier()+".text."+this.localizationKey);
        base = base.replaceAll("<imp>", TextFormatting.DARK_GREEN+"");
        base = base.replaceAll("<item>", TextFormatting.BLUE+"");
        base = base.replaceAll("<r>", TextFormatting.BLACK+"");
        base = base.replaceAll("<n>", "\n");
        base = base.replaceAll("<i>", TextFormatting.ITALIC+"");
        base = base.replaceAll("<tifisgrin>", TextFormatting.DARK_RED+""+TextFormatting.UNDERLINE); //This is fucking important so go read it now

        for(Map.Entry<String, String> entry : this.textReplacements.entrySet()){
            base = base.replaceAll(entry.getKey(), entry.getValue());
        }
        return base;
    }

    public BookletPage addFluidToPage(Fluid fluid){
        this.fluidsForPage.add(new FluidStack(fluid, 1));
        return this;
    }

    public BookletPage addItemToPage(ItemStack stack){
        this.itemsForPage.add(stack);
        return this;
    }
}