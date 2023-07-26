package com.feldsher.feldshergreg.loaders;

import net.minecraft.item.ItemStack;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TextureSet;
import gregtech.api.enums.GT_Values;
import gregtech.api.metatileentity.implementations.GT_MetaPipeEntity_Cable;

import com.feldsher.feldshergreg.tileentities.*;
import com.feldsher.feldshergreg.FeldsherGregsItems;

public final class MetaTileEntitiesLoader {
    public static void load() {
        loadMetaTileEntity();
        loadWire();
    }

    private static void loadMetaTileEntity() {
        FeldsherGregsItems.quarry1 = new GT_MetaTileEntity_Quarry1(15001, "multimachine.quarry1", "Quarry I").getStackForm(1);
        FeldsherGregsItems.quarry2 = new GT_MetaTileEntity_Quarry2(15002, "multimachine.quarry2", "Quarry II").getStackForm(1);
        FeldsherGregsItems.quarry3 = new GT_MetaTileEntity_Quarry3(15003, "multimachine.quarry3", "Quarry III").getStackForm(1);
        FeldsherGregsItems.quarry4 = new GT_MetaTileEntity_Quarry4(15004, "multimachine.quarry4", "Quarry IV").getStackForm(1);

        FeldsherGregsItems.galvanicGenerator1 = new GT_MetaTileEntity_GalvanicGenerator(15005, "galvanicgenerator1", "Galvanic Generator LV", 1).getStackForm(1);
        FeldsherGregsItems.galvanicGenerator2 = new GT_MetaTileEntity_GalvanicGenerator(15006, "galvanicgenerator2", "Galvanic Generator MV", 2).getStackForm(1);
        FeldsherGregsItems.galvanicGenerator3 = new GT_MetaTileEntity_GalvanicGenerator(15007, "galvanicgenerator3", "Galvanic Generator HV", 3).getStackForm(1);

        FeldsherGregsItems.multiMultiFurnace = new MultiMultiFurnace(15008, "multimachine.multimultifurnace", "Multi Multi Smelter").getStackForm(1);
    }

    private static void loadWire() {
        FeldsherGregsItems.superconductorBaseLVWire = makeSuperconductorBase("Superconductor Base LV", 15200, 16L, 1L, GT_Values.V[1], TextureSet.SET_METALLIC, 0xE1B454);
        ItemStack[][] superconductorLV = makeWire("Superconductor LV", 15300, 0, 0L, 1L, GT_Values.V[1], false, true, TextureSet.SET_METALLIC, 0xE1B454);
        FeldsherGregsItems.superconductorLVWire = superconductorLV[0];

        FeldsherGregsItems.superconductorBaseMVWire = makeSuperconductorBase("Superconductor Base MV", 15201, 64L, 1L, GT_Values.V[2], TextureSet.SET_METALLIC, 0x331900);
        ItemStack[][] superconductorMV = makeWire("Superconductor MV", 15306, 0, 0L, 1L, GT_Values.V[2], false, true, TextureSet.SET_METALLIC, 0x331900);
        FeldsherGregsItems.superconductorMVWire = superconductorMV[0];

        FeldsherGregsItems.superconductorBaseHVWire = makeSuperconductorBase("Superconductor Base HV", 15202, 256L, 2L, GT_Values.V[3], TextureSet.SET_METALLIC, 0x555555);
        ItemStack[][] superconductorHV = makeWire("Superconductor HV", 15312, 0, 0L, 2L, GT_Values.V[3], false, true, TextureSet.SET_METALLIC, 0x555555);
        FeldsherGregsItems.superconductorHVWire = superconductorHV[0];

        FeldsherGregsItems.superconductorBaseEVWire = makeSuperconductorBase("Superconductor Base EV", 15203, 1024L, 3L, GT_Values.V[4], TextureSet.SET_METALLIC, 0x281E5D);
        ItemStack[][] superconductorEV = makeWire("Superconductor EV", 15318, 0, 0L, 3L, GT_Values.V[4], false, true, TextureSet.SET_METALLIC, 0x281E5D);
        FeldsherGregsItems.superconductorEVWire = superconductorEV[0];

        FeldsherGregsItems.superconductorBaseIVWire = makeSuperconductorBase("Superconductor Base IV", 15204, 4096L, 4L, GT_Values.V[5], TextureSet.SET_METALLIC, 0x9e9764);
        ItemStack[][] superconductorIV = makeWire("Superconductor IV", 15324, 0, 0L, 4L, GT_Values.V[5], false, true, TextureSet.SET_METALLIC, 0x9e9764);
        FeldsherGregsItems.superconductorIVWire = superconductorIV[0];
    }

    private static ItemStack makeSuperconductorBase(String name, int id, long loss, long amperage, long voltage, TextureSet iconSet, int color) {
        return new FeldsherGregCable(id + 0, "wire." + name.toLowerCase() + ".01", "1x " + name + " Wire", 0.125F, loss, amperage, voltage, false, true, iconSet, color).getStackForm(1);
    }

    private static ItemStack[][] makeWire(String name, int startID, long lossInsulated, long loss, long amperage, long voltage, boolean insulatable, boolean autoInsulated, TextureSet iconSet, int color) {
        ItemStack[][] wires = new ItemStack[2][];
        wires[0] = new ItemStack[]{
            new FeldsherGregCable(startID + 0, "wire." + name.toLowerCase() + ".01", "1x " + name + " Wire", 0.125F, loss, 1L * amperage, voltage, false, !autoInsulated, iconSet, color).getStackForm(1),
            new FeldsherGregCable(startID + 1, "wire." + name.toLowerCase() + ".02", "2x " + name + " Wire", 0.25F, loss, 2L * amperage, voltage, false, !autoInsulated, iconSet, color).getStackForm(1),
            new FeldsherGregCable(startID + 2, "wire." + name.toLowerCase() + ".04", "4x " + name + " Wire", 0.375F, loss, 4L * amperage, voltage, false, !autoInsulated, iconSet, color).getStackForm(1),
            new FeldsherGregCable(startID + 3, "wire." + name.toLowerCase() + ".08", "8x " + name + " Wire", 0.5F, loss, 8L * amperage, voltage, false, !autoInsulated, iconSet, color).getStackForm(1),
            new FeldsherGregCable(startID + 4, "wire." + name.toLowerCase() + ".12", "12x " + name + " Wire", 0.675F, loss, 12L * amperage, voltage, false, !autoInsulated, iconSet, color).getStackForm(1),
            new FeldsherGregCable(startID + 5, "wire." + name.toLowerCase() + ".16", "16x " + name + " Wire", 0.75F, loss, 16L * amperage, voltage, false, !autoInsulated, iconSet, color).getStackForm(1)
        };
        if (insulatable) {
            wires[1] = new ItemStack[] {
                new FeldsherGregCable(startID + 6, "cable." + name.toLowerCase() + ".01", "1x " + name + " Cable", 0.25F, lossInsulated, 1L * amperage, voltage, true, false, iconSet, color).getStackForm(1),
                new FeldsherGregCable(startID + 7, "cable." + name.toLowerCase() + ".02", "2x " + name + " Cable", 0.375F, lossInsulated, 2L * amperage, voltage, true, false, iconSet, color).getStackForm(1),
                new FeldsherGregCable(startID + 8, "cable." + name.toLowerCase() + ".04", "4x " + name + " Cable", 0.5F, lossInsulated, 4L * amperage, voltage, true, false, iconSet, color).getStackForm(1),
                new FeldsherGregCable(startID + 9, "cable." + name.toLowerCase() + ".08", "8x " + name + " Cable", 0.625F, lossInsulated, 8L * amperage, voltage, true, false, iconSet, color).getStackForm(1),
                new FeldsherGregCable(startID + 10, "cable." + name.toLowerCase() + ".12", "12x " + name + " Cable", 0.75F, lossInsulated, 12L * amperage, voltage, true, false, iconSet, color).getStackForm(1)
            };
        }

        return wires;
    }
}
