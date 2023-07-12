package com.feldsher.feldshergreg.tileentities;

import java.util.Arrays;

import gregtech.api.metatileentity.implementations.GT_MetaPipeEntity_Cable;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.metatileentity.IMetaTileEntityCable;
import gregtech.api.interfaces.ITexture;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.enums.Textures;
import gregtech.api.enums.TextureSet;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Dyes;

import com.feldsher.feldshergreg.Config;

public class FeldsherGregCable extends GT_MetaPipeEntity_Cable implements IMetaTileEntityCable {
    private int color;
    private int id;
    private String name, nameRegional;

    public FeldsherGregCable(int id, String name, String nameRegional, float thickNess, long cableLossPerMeter, long amperage, long voltage, boolean insulated, boolean canShock, TextureSet iconSet, int color) {
        super(id, name, nameRegional, thickNess, Materials.Iron, cableLossPerMeter, amperage, voltage, insulated, canShock);
        this.color = color;
    }

    public FeldsherGregCable(String name, float thickNess, long cableLossPerMeter, long amperage, long voltage, boolean insulated, boolean canShock, int color) {
		super(name, thickNess, Materials.Iron, cableLossPerMeter, amperage, voltage, insulated, canShock);
        this.color = color;
	}

    @Override
    public IMetaTileEntity newMetaEntity(final IGregTechTileEntity aTileEntity) {
        return new FeldsherGregCable(this.mName, this.mThickNess, this.mCableLossPerMeter, this.mAmperage, this.mVoltage, this.mInsulated, this.mCanShock, this.color);
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aConnections, byte aColorIndex, boolean aConnected, boolean aRedstone) {
        short[] rgba = new short[]{
            (short)((color & 0xFF0000) >> 16),
            (short)((color & 0xFF00) >> 8),
            (short)((color & 0xFF)),
            0
        };
        
        if (!mInsulated)
            return new ITexture[]{new GT_RenderedTexture(mMaterial.mIconSet.mTextures[TextureSet.INDEX_wire], Dyes.getModulation(aColorIndex, rgba) )};
        if (aConnected) {
            float tThickNess = getThickNess();
            if (tThickNess < 0.124F)
                return new ITexture[]{new GT_RenderedTexture(Textures.BlockIcons.INSULATION_FULL, Dyes.getModulation(aColorIndex, Dyes.CABLE_INSULATION.mRGBa))};
            if (tThickNess < 0.374F) // 0.375 x1
                return new ITexture[]{new GT_RenderedTexture(mMaterial.mIconSet.mTextures[TextureSet.INDEX_wire], rgba), new GT_RenderedTexture(Textures.BlockIcons.INSULATION_TINY, Dyes.getModulation(aColorIndex, Dyes.CABLE_INSULATION.mRGBa))};
            if (tThickNess < 0.499F) // 0.500 x2
                return new ITexture[]{new GT_RenderedTexture(mMaterial.mIconSet.mTextures[TextureSet.INDEX_wire], rgba), new GT_RenderedTexture(Textures.BlockIcons.INSULATION_SMALL, Dyes.getModulation(aColorIndex, Dyes.CABLE_INSULATION.mRGBa))};
            if (tThickNess < 0.624F) // 0.625 x4
                return new ITexture[]{new GT_RenderedTexture(mMaterial.mIconSet.mTextures[TextureSet.INDEX_wire], rgba), new GT_RenderedTexture(Textures.BlockIcons.INSULATION_MEDIUM, Dyes.getModulation(aColorIndex, Dyes.CABLE_INSULATION.mRGBa))};
            if (tThickNess < 0.749F) // 0.750 x8
                return new ITexture[]{new GT_RenderedTexture(mMaterial.mIconSet.mTextures[TextureSet.INDEX_wire], rgba), new GT_RenderedTexture(Textures.BlockIcons.INSULATION_MEDIUM_PLUS, Dyes.getModulation(aColorIndex, Dyes.CABLE_INSULATION.mRGBa))};
            if (tThickNess < 0.874F) // 0.825 x12
                return new ITexture[]{new GT_RenderedTexture(mMaterial.mIconSet.mTextures[TextureSet.INDEX_wire], rgba), new GT_RenderedTexture(Textures.BlockIcons.INSULATION_LARGE, Dyes.getModulation(aColorIndex, Dyes.CABLE_INSULATION.mRGBa))};
            return new ITexture[]{new GT_RenderedTexture(mMaterial.mIconSet.mTextures[TextureSet.INDEX_wire], rgba), new GT_RenderedTexture(Textures.BlockIcons.INSULATION_HUGE, Dyes.getModulation(aColorIndex, Dyes.CABLE_INSULATION.mRGBa))};
        }
        return new ITexture[]{new GT_RenderedTexture(Textures.BlockIcons.INSULATION_FULL, Dyes.getModulation(aColorIndex, Dyes.CABLE_INSULATION.mRGBa))};
    }

    @Override
    public String[] getDescription() {
        String[] oldDescription = super.getDescription();
        String[] newDescription = Arrays.copyOf(oldDescription, oldDescription.length + 1);
        newDescription[oldDescription.length] = Config.copyright;
        return newDescription;
    }
}