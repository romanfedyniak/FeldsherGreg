package com.feldsher.feldshergreg.helpers;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;

import com.feldsher.feldshergreg.Tags;


public class GTCustomIcon extends Textures.BlockIcons.CustomIcon {
    public GTCustomIcon(String iconName) {
        super(iconName);
    }

    @Override
    public void run() {
        mIcon = GregTech_API.sBlockIcons.registerIcon(Tags.MODID + ":" + mIconName);
    }
}
