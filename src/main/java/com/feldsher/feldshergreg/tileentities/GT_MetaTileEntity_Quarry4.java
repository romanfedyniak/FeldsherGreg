package com.feldsher.feldshergreg.tileentities;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;

public class GT_MetaTileEntity_Quarry4 extends GT_MetaTileEntity_QuarryBase {
    public GT_MetaTileEntity_Quarry4(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public GT_MetaTileEntity_Quarry4(String aName) {
        super(aName);
    }

    @Override
    public String[] getDescription() {
        return getDescriptionInternal("IV");
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Quarry4(mName);
    }

    @Override
    protected ItemList getCasingBlockItem() {
        return ItemList.Casing_MiningOsmiridium;
    }

    @Override
    protected Materials getFrameMaterial() {
        return Materials.Osmiridium;
    }

    @Override
    protected int getCasingTextureIndex() {
        return 62;
    }

    @Override
    protected int getRadius() {
        return 9 * 16;
    }

    @Override
    protected int getMinTier() {
        return 5;
    }

    @Override
    protected int getBaseProgressTime() {
        return 32;
    }
}
