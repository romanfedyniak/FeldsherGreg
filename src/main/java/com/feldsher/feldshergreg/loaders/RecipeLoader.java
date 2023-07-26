package com.feldsher.feldshergreg.loaders;

import java.util.HashSet;

import ic2.core.Ic2Items;
import net.minecraft.item.ItemStack;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import static gregtech.api.enums.GT_Values.RA;

import com.feldsher.feldshergreg.FeldsherGregsItems;
import com.feldsher.feldshergreg.helpers.RecipeMapLiquidFuel;


public final class RecipeLoader {
    public static final RecipeMapLiquidFuel galvanicMapFuel = new RecipeMapLiquidFuel(
        new HashSet<>(2),
        "fg.recipe.galvanicfuel",
        "Galvanic Fuel",
        null,
        "gregtech:textures/gui/basicmachines/Default",
        1, 1, 0, 0, 1,
        "Fuel Value: ", 1000, " EU", true, true
    );

    public static void load() {
        loadWorkbench();
        loadMixer();
        loadBlast();
        loadWiremill();
        loadWire();
        loadAssembler();
        loadFuel();
    }

    // Workbench recipes
    private static void loadWorkbench() {
        long bits = GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE | GT_ModHandler.RecipeBits.BUFFERED;
        GT_ModHandler.addCraftingRecipe(FeldsherGregsItems.quarry1, bits, new Object[]{"WWW", "EME", "CPC", 'M', Ic2Items.advminer, 'W', OrePrefixes.frameGt.get(Materials.Titanium), 'E', OrePrefixes.circuit.get(Materials.Data), 'C', ItemList.Electric_Motor_EV, 'P', ItemList.Electric_Pump_EV});
        GT_ModHandler.addCraftingRecipe(FeldsherGregsItems.quarry2, bits, new Object[]{"WWW", "EME", "CPC", 'M', FeldsherGregsItems.quarry1, 'W', OrePrefixes.frameGt.get(Materials.TungstenSteel), 'E', OrePrefixes.circuit.get(Materials.Elite), 'C', ItemList.Electric_Motor_IV, 'P', ItemList.Electric_Pump_IV});
        GT_ModHandler.addCraftingRecipe(FeldsherGregsItems.quarry3, bits, new Object[]{"WWW", "EME", "CPC", 'M', FeldsherGregsItems.quarry2, 'W', OrePrefixes.frameGt.get(Materials.Osmiridium), 'E', OrePrefixes.circuit.get(Materials.Master), 'C', ItemList.Electric_Motor_LuV, 'P', ItemList.Electric_Pump_LuV});
        GT_ModHandler.addCraftingRecipe(FeldsherGregsItems.quarry4, bits, new Object[]{"WWW", "EME", "CPC", 'M', FeldsherGregsItems.quarry3, 'W', OrePrefixes.frameGt.get(Materials.Tritanium), 'E', OrePrefixes.circuit.get(Materials.Ultimate), 'C', ItemList.Electric_Motor_ZPM, 'P', ItemList.Electric_Pump_ZPM});

        GT_ModHandler.addCraftingRecipe(FeldsherGregsItems.galvanicGenerator1, bits, new Object[]{"PCP", "HMH", "UWU", 'P', OrePrefixes.pipeMedium.get(Materials.Bronze), 'C', OrePrefixes.circuit.get(Materials.Basic), 'H', ItemList.Battery_Hull_LV, 'M', ItemList.Hull_LV, 'U', ItemList.Electric_Pump_LV, 'W', OrePrefixes.cableGt01.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(FeldsherGregsItems.galvanicGenerator2, bits, new Object[]{"PCP", "HMH", "UWU", 'P', OrePrefixes.pipeMedium.get(Materials.Steel), 'C', OrePrefixes.circuit.get(Materials.Good), 'H', ItemList.Battery_Hull_MV, 'M', ItemList.Hull_MV, 'U', ItemList.Electric_Pump_MV, 'W', OrePrefixes.cableGt01.get(Materials.Copper)});
        GT_ModHandler.addCraftingRecipe(FeldsherGregsItems.galvanicGenerator3, bits, new Object[]{"PCP", "HMH", "UWU", 'P', OrePrefixes.pipeMedium.get(Materials.StainlessSteel), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'H', ItemList.Battery_Hull_HV, 'M', ItemList.Hull_HV, 'U', ItemList.Electric_Pump_HV, 'W', OrePrefixes.cableGt01.get(Materials.Gold)});

        GT_ModHandler.addShapelessCraftingRecipe(FeldsherGregsItems.multiMultiFurnace, new Object[]{ItemList.Machine_Multi_Furnace.get(1L)});
        GT_ModHandler.addShapelessCraftingRecipe(ItemList.Machine_Multi_Furnace.get(1L), new Object[]{FeldsherGregsItems.multiMultiFurnace});
    }

    // Mixer recipes
    private static void loadMixer() {
        RA.addMixerRecipe(Materials.Manganese.getDust(1), Materials.Phosphorus.getDust(1), null, null, null, null,
            getSimpleStack(FeldsherGregsItems.dustSuperconductorBaseLV, 2),
            20 * 20, 30);

        RA.addMixerRecipe(Materials.Magnesium.getDust(1), Materials.Boron.getDust(2), null, null, null, null,
            getSimpleStack(FeldsherGregsItems.dustSuperconductorBaseMV, 3),
            20 * 20, 30);

        RA.addMixerRecipe(Materials.Barium.getDust(2), Materials.Calcium.getDust(2), Materials.Copper.getDust(3), null, Materials.Mercury.getFluid(1000), null,
            getSimpleStack(FeldsherGregsItems.dustSuperconductorBaseHV, 8),
            20 * 20, 30);

        RA.addMixerRecipe(Materials.Cerium.getDust(2), Materials.Copper.getDust(2), Materials.Silicon.getDust(2), null, null, null,
            getSimpleStack(FeldsherGregsItems.dustSuperconductorBaseEV, 6),
            20 * 20, 30);

        RA.addMixerRecipe(Materials.Lanthanum.getDust(2), Materials.Copper.getDust(1), null, null, null, null,
            getSimpleStack(FeldsherGregsItems.dustSuperconductorBaseIV, 3),
            20 * 20, 30);
        
    }

    // Blast furnace recipes
    private static void loadBlast() {
        RA.addBlastRecipe(FeldsherGregsItems.dustSuperconductorBaseLV, null,
            FeldsherGregsItems.ingotSuperconductorBaseLV, null,
            150 * 20, 120, 1200);

        RA.addBlastRecipe(FeldsherGregsItems.dustSuperconductorBaseMV, null,
            FeldsherGregsItems.ingotSuperconductorBaseMV, null,
            150 * 20, 120, 2500);

        RA.addBlastRecipe(FeldsherGregsItems.dustSuperconductorBaseHV, null, Materials.Oxygen.getGas(8000L),
            null, FeldsherGregsItems.ingotSuperconductorBaseHV, null,
            150 * 20, 120, 3200);

        RA.addBlastRecipe(FeldsherGregsItems.dustSuperconductorBaseEV, null,
            FeldsherGregsItems.ingotSuperconductorBaseEV, null,
            150 * 20, 320, 4500);

        RA.addBlastRecipe(FeldsherGregsItems.dustSuperconductorBaseIV, null, Materials.Oxygen.getGas(4000L),
            null, FeldsherGregsItems.ingotSuperconductorBaseIV, null,
            150 * 20, 500, 5400);
    }

    // Wiremill recipes
    private static void loadWiremill() {
        RA.addWiremillRecipe(FeldsherGregsItems.ingotSuperconductorBaseLV,
            getSimpleStack(FeldsherGregsItems.superconductorBaseLVWire, 2),
            5 * 20, 5);

        RA.addWiremillRecipe(FeldsherGregsItems.ingotSuperconductorBaseMV,
            getSimpleStack(FeldsherGregsItems.superconductorBaseMVWire, 2),
            5 * 20, 5);

        RA.addWiremillRecipe(FeldsherGregsItems.ingotSuperconductorBaseHV,
            getSimpleStack(FeldsherGregsItems.superconductorBaseHVWire, 2),
            5 * 20, 5);

        RA.addWiremillRecipe(FeldsherGregsItems.ingotSuperconductorBaseEV,
            getSimpleStack(FeldsherGregsItems.superconductorBaseEVWire, 2),
            5 * 20, 5);

        RA.addWiremillRecipe(FeldsherGregsItems.ingotSuperconductorBaseIV,
            getSimpleStack(FeldsherGregsItems.superconductorBaseIVWire, 2),
            5 * 20, 5);
    }

    // Assembler recipes
    private static void loadAssembler() {
        RA.addAssemblerRecipe(
            new ItemStack[]{
                getSimpleStack(FeldsherGregsItems.superconductorBaseLVWire, 3),
                ItemList.Electric_Pump_LV.get(1L),
                GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Materials.Steel, 1)},
            Materials.Nitrogen.getGas(1000L),
            getSimpleStack(FeldsherGregsItems.superconductorLVWire[0], 3),
            20 * 20, 30);

        RA.addAssemblerRecipe(
            new ItemStack[]{
                getSimpleStack(FeldsherGregsItems.superconductorBaseMVWire, 3),
                ItemList.Electric_Pump_LV.get(1L),
                GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Materials.Steel, 1)},
            Materials.Hydrogen.getGas(1000L),
            getSimpleStack(FeldsherGregsItems.superconductorMVWire[0], 3),
            20 * 20, 30);

        RA.addAssemblerRecipe(
            new ItemStack[]{
                getSimpleStack(FeldsherGregsItems.superconductorBaseHVWire, 3),
                ItemList.Electric_Pump_LV.get(1L),
                GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Materials.StainlessSteel, 1)},
            Materials.Nitrogen.getGas(1000L),
            getSimpleStack(FeldsherGregsItems.superconductorHVWire[0], 3),
            20 * 20, 30);

        RA.addAssemblerRecipe(
            new ItemStack[]{
                getSimpleStack(FeldsherGregsItems.superconductorBaseEVWire, 3),
                ItemList.Electric_Pump_MV.get(1L),
                GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Materials.StainlessSteel, 1)},
            Materials.Helium.getGas(1000L),
            getSimpleStack(FeldsherGregsItems.superconductorEVWire[0], 3),
            20 * 20, 30);

        RA.addAssemblerRecipe(
            new ItemStack[]{
                getSimpleStack(FeldsherGregsItems.superconductorBaseIVWire, 3),
                ItemList.Electric_Pump_HV.get(1L),
                GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Materials.Titanium, 1)},
            Materials.Helium.getGas(1000L),
            getSimpleStack(FeldsherGregsItems.superconductorIVWire[0], 3),
            20 * 20, 30);
    }

    // Wire recipes
    private static void loadWire() {
        addWireRecipes(FeldsherGregsItems.superconductorLVWire);
        addWireRecipes(FeldsherGregsItems.superconductorMVWire);
        addWireRecipes(FeldsherGregsItems.superconductorHVWire);
        addWireRecipes(FeldsherGregsItems.superconductorEVWire);
        addWireRecipes(FeldsherGregsItems.superconductorIVWire);
    }

    // Fuels
    private static void loadFuel() {
        // Galvanic generator
        galvanicMapFuel.addLiquidFuel(Materials.SulfuricAcid, 18);
        galvanicMapFuel.addLiquidFuel(Materials.Mercury, 32);
    }

    // Utils //

    // - Add recipes for wire 2x, 4x, 8x, etc
    private static void addWireRecipes(ItemStack[] wire) {
        long bits = GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE | GT_ModHandler.RecipeBits.BUFFERED;
        // x1
        GT_ModHandler.addShapelessCraftingRecipe(getSimpleStack(wire[0], 2), new Object[]{wire[1]});
        GT_ModHandler.addShapelessCraftingRecipe(getSimpleStack(wire[0], 4), new Object[]{wire[2]});
        GT_ModHandler.addShapelessCraftingRecipe(getSimpleStack(wire[0], 8), new Object[]{wire[3]});
        GT_ModHandler.addShapelessCraftingRecipe(getSimpleStack(wire[0], 12), new Object[]{wire[4]});
        GT_ModHandler.addShapelessCraftingRecipe(getSimpleStack(wire[0], 16), new Object[]{wire[5]});

        // x2
        GT_ModHandler.addShapelessCraftingRecipe(wire[1], new Object[]{wire[0], wire[0]});
        RA.addAssemblerRecipe(getSimpleStack(wire[0], 2), ItemList.Circuit_Integrated.getWithDamage(0L, 2L, new Object[0]), wire[1], 150, 8);

        // x4
        GT_ModHandler.addShapelessCraftingRecipe(wire[2], new Object[]{wire[0], wire[0], wire[0], wire[0]});
        GT_ModHandler.addShapelessCraftingRecipe(wire[2], new Object[]{wire[1], wire[1]});
        RA.addAssemblerRecipe(getSimpleStack(wire[0], 4), ItemList.Circuit_Integrated.getWithDamage(0L, 4L, new Object[0]), wire[2], 200, 8);

        // x8
        GT_ModHandler.addShapelessCraftingRecipe(wire[3], new Object[]{wire[0], wire[0], wire[0], wire[0], wire[0], wire[0], wire[0], wire[0]});
        GT_ModHandler.addShapelessCraftingRecipe(wire[3], new Object[]{wire[1], wire[1], wire[1], wire[1]});
        GT_ModHandler.addShapelessCraftingRecipe(wire[3], new Object[]{wire[2], wire[2]});
        RA.addAssemblerRecipe(getSimpleStack(wire[0], 8), ItemList.Circuit_Integrated.getWithDamage(0L, 8L, new Object[0]), wire[3], 300, 8);

        // x12
        GT_ModHandler.addShapelessCraftingRecipe(wire[4], new Object[]{wire[3], wire[2]});
        RA.addAssemblerRecipe(getSimpleStack(wire[0], 12), ItemList.Circuit_Integrated.getWithDamage(0L, 12L, new Object[0]), wire[4], 400, 8);

        // x16
        GT_ModHandler.addShapelessCraftingRecipe(wire[5], new Object[]{wire[3], wire[3]});
        GT_ModHandler.addShapelessCraftingRecipe(wire[5], new Object[]{wire[4], wire[2]});
        RA.addAssemblerRecipe(getSimpleStack(wire[0], 16), ItemList.Circuit_Integrated.getWithDamage(0L, 16L, new Object[0]), wire[5], 500, 8);
    }

    private static ItemStack getSimpleStack(ItemStack item, int i) {
        ItemStack newItem = item.copy();
        newItem.stackSize = i;
        return newItem;
    }
}
