package com.feldsher.feldshergreg.tileentities;

import java.util.Arrays;

import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtech.api.util.GT_Recipe;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;

import com.feldsher.feldshergreg.Config;
import com.feldsher.feldshergreg.loaders.RecipeLoader;
import com.feldsher.feldshergreg.helpers.GTCustomIcon;


public class GT_MetaTileEntity_GalvanicGenerator extends GT_MetaTileEntity_BasicGenerator {
    
    public GT_MetaTileEntity_GalvanicGenerator(int aID, String aName, String aNameRegional, int aTier, ITexture... aTextures) {
        super(aID, aName, aNameRegional, aTier, new String[]{}, aTextures);
    }

    public GT_MetaTileEntity_GalvanicGenerator(String aName, int aTier, String[] aDescription, ITexture[][][] aTextures) {
        super(aName, aTier, aDescription, aTextures);
    }

    @Override
    public int getPollution() {
        return 0;
    }

    @Override
    public GT_Recipe.GT_Recipe_Map getRecipes() {
        return RecipeLoader.galvanicMapFuel;
    }

    @Override
    public int getEfficiency() {
        return 100 - 5 * this.mTier;
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new GT_MetaTileEntity_GalvanicGenerator(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    @Override
    public boolean isOutputFacing(byte aSide) {
        return aSide == this.getBaseMetaTileEntity().getFrontFacing();
    }

    public String[] getDescription() {
        String[] oldDescription = super.getDescription();
        String[] newDescription = Arrays.copyOf(oldDescription, oldDescription.length + 1);
        newDescription[oldDescription.length] = Config.copyright;
        return newDescription;
    }

    public ITexture[] getFront(byte aColor) {
        return new ITexture[]{super.getFront(aColor)[0], getTextureFromPath("OVERLAY_FRONT"), Textures.BlockIcons.OVERLAYS_ENERGY_OUT[this.mTier]};
    }

    public ITexture[] getBack(byte aColor) {
        return new ITexture[]{super.getBack(aColor)[0], getTextureFromPath("OVERLAY_BACK")};
    }

    public ITexture[] getBottom(byte aColor) {
        return new ITexture[]{super.getBottom(aColor)[0], getTextureFromPath("OVERLAY_BOTTOM")};
    }

    public ITexture[] getTop(byte aColor) {
        return new ITexture[]{super.getTop(aColor)[0], getTextureFromPath("OVERLAY_TOP")};
    }

    public ITexture[] getSides(byte aColor) {
        return new ITexture[]{super.getSides(aColor)[0], getTextureFromPath("OVERLAY_SIDE")};
    }

    public ITexture[] getFrontActive(byte aColor) {
        return new ITexture[]{super.getFrontActive(aColor)[0], getTextureFromPath("OVERLAY_FRONT_ACTIVE"),
                             Textures.BlockIcons.OVERLAYS_ENERGY_OUT[this.mTier]};
    }

    public ITexture[] getBackActive(byte aColor) {
        return new ITexture[]{super.getBackActive(aColor)[0], getTextureFromPath("OVERLAY_BACK_ACTIVE")};
    }

    public ITexture[] getBottomActive(byte aColor) {
        return new ITexture[]{super.getBottomActive(aColor)[0], getTextureFromPath("OVERLAY_BOTTOM_ACTIVE")};
    }

    public ITexture[] getTopActive(byte aColor) {
        return new ITexture[]{super.getTopActive(aColor)[0], getTextureFromPath("OVERLAY_TOP_ACTIVE")};
    }

    public ITexture[] getSidesActive(byte aColor) {
        return new ITexture[]{super.getSidesActive(aColor)[0], getTextureFromPath("OVERLAY_SIDE_ACTIVE")};
    }

    private ITexture getTextureFromPath(String path) {
        return new GT_RenderedTexture(new GTCustomIcon("galvanic_generator/" + path));
    }
}
