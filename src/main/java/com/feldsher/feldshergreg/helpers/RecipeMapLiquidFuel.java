package com.feldsher.feldshergreg.helpers;

import gregtech.api.util.GT_Recipe;
import gregtech.api.enums.Materials;

import java.util.Collection;


public class RecipeMapLiquidFuel extends GT_Recipe.GT_Recipe_Map_Fuel {
     public RecipeMapLiquidFuel(Collection<GT_Recipe> aRecipeList, String aUnlocalizedName, String aLocalName, String aNEIName, String aNEIGUIPath,
                             int aUsualInputCount, int aUsualOutputCount, int aMinimalInputItems, int aMinimalInputFluids, int aAmperage,
                             String aNEISpecialValuePre, int aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI,
                             boolean aNEIAllowed) {
        super(aRecipeList, aUnlocalizedName, aLocalName, aNEIName, aNEIGUIPath, aUsualInputCount, aUsualOutputCount, aMinimalInputItems, aMinimalInputFluids,
              aAmperage, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed);
    }

    public GT_Recipe addLiquidFuel(Materials M, int burn) {
        return super.addFuel(M.getCells(1), null, burn);
    }
}
