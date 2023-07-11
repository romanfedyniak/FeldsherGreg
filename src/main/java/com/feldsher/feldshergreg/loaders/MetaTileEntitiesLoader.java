package com.feldsher.feldshergreg.loaders;

import com.feldsher.feldshergreg.tileentities.*;
import com.feldsher.feldshergreg.FeldsherGregsItems;

public final class MetaTileEntitiesLoader {
    public static void load() {
        FeldsherGregsItems.quarry1 = new GT_MetaTileEntity_Quarry1(15001, "multimachine.quarry1", "Quarry I").getStackForm(1);
        FeldsherGregsItems.quarry2 = new GT_MetaTileEntity_Quarry2(15002, "multimachine.quarry2", "Quarry II").getStackForm(1);
        FeldsherGregsItems.quarry3 = new GT_MetaTileEntity_Quarry3(15003, "multimachine.quarry3", "Quarry III").getStackForm(1);
        FeldsherGregsItems.quarry4 = new GT_MetaTileEntity_Quarry4(15004, "multimachine.quarry4", "Quarry IV").getStackForm(1);
    }
}
