package com.feldsher.feldshergreg.tileentities;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;

public class GT_MetaTileEntity_Quarry1 extends GT_MetaTileEntity_QuarryBase {
    public GT_MetaTileEntity_Quarry1(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public GT_MetaTileEntity_Quarry1(String aName) {
        super(aName);
    }

    @Override
    public String[] getDescription() {
        return getDescriptionInternal("I");
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Quarry1(mName);
    }

    @Override
    protected ItemList getCasingBlockItem() {
        return ItemList.Casing_SolidSteel;
    }

    @Override
    protected Materials getFrameMaterial() {
        return Materials.Steel;
    }

    @Override
    protected int getCasingTextureIndex() {
        return 16;
    }

    @Override
    protected int getRadius() {
        return 3 * 16;
    }

    @Override
    protected int getMinTier() {
        return 2;
    }

    @Override
    protected int getBaseProgressTime() {
        return 800;
    }
}
