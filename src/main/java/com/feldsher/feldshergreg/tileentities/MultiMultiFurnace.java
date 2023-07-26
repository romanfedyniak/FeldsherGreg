package com.feldsher.feldshergreg.tileentities;

import java.util.Arrays;
import java.util.ArrayList;

import gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_MultiFurnace;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.util.GT_Utility;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumChatFormatting;

import com.feldsher.feldshergreg.Config;

public class MultiMultiFurnace extends GT_MetaTileEntity_MultiFurnace {
    private int level = 0;
    private int costDiscount = 1;

    public MultiMultiFurnace(int id, String name, String nameRegional) {
        super(id, name, nameRegional);
    }

    public MultiMultiFurnace(String name) {
        super(name);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity tileEntity) {
        return new MultiMultiFurnace("multimultifurnace");
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity baseMetaTileEntity, ItemStack stack){
        this.level = 0;
        this.costDiscount = 1;

        if (super.checkMachine(baseMetaTileEntity, stack) == false) {
            return false;
        }

        int xDir = ForgeDirection.getOrientation(baseMetaTileEntity.getBackFacing()).offsetX;
        int zDir = ForgeDirection.getOrientation(baseMetaTileEntity.getBackFacing()).offsetZ;
        byte usedMeta = baseMetaTileEntity.getMetaIDOffset(xDir + 1, 1, zDir);
        switch (usedMeta) {
            case 0:
                this.level = 1;
                this.costDiscount = 1;
                break;
            case 1:
                this.level = 2;
                this.costDiscount = 1;
                break;
            case 2:
                this.level = 4;
                this.costDiscount = 1;
                break;
            case 3:
                this.level = 8;
                this.costDiscount = 1;
                break;
            case 4:
                this.level = 16;
                this.costDiscount = 2;
                break;
            case 5:
                this.level = 16;
                this.costDiscount = 4;
                break;
            case 6:
                this.level = 16;
                this.costDiscount = 8;
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean checkRecipe(ItemStack aStack) {
        ArrayList<ItemStack> tInputList = getStoredInputs();
        if (!tInputList.isEmpty()) {
            int tTier = (byte) Math.max(1, GT_Utility.getTier(getMaxInputVoltage()));

            int tMaxParallel = 8 * this.level;
            int tCurrentParallel = 0;
            ArrayList<ItemStack> smeltedOutputs = new ArrayList<ItemStack>();
            ArrayList<Integer> outputStackSizes = new ArrayList<Integer>();
            for (ItemStack item : tInputList) {
                ItemStack smeltedOutput = GT_OreDictUnificator.get(FurnaceRecipes.smelting().getSmeltingResult(item));
                if (smeltedOutput != null) {
                    smeltedOutputs.add(smeltedOutput);
                    if (item.stackSize <= (tMaxParallel - tCurrentParallel)) {
                        tCurrentParallel += item.stackSize;
                        outputStackSizes.add(smeltedOutput.stackSize * item.stackSize);
                        item.stackSize = 0;
                    } else {
                        int remainingStackSize = tCurrentParallel + item.stackSize - tMaxParallel;
                        outputStackSizes.add(smeltedOutput.stackSize * (item.stackSize - remainingStackSize));
                        item.stackSize = remainingStackSize;
                        break;
                    }
                }

                if (tCurrentParallel == tMaxParallel) {
                    break;
                }
            }

            this.mOutputItems = new ItemStack[smeltedOutputs.size()];
            for (int i = 0; i < this.mOutputItems.length; i++) {
                ItemStack tNewStack = smeltedOutputs.get(i);
                tNewStack.stackSize = outputStackSizes.get(i);
                this.mOutputItems[i] = tNewStack;
            }

            if (this.mOutputItems.length > 0) {
                this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
                this.mEfficiencyIncrease = 10000;
                this.mEUt = (-4 * (1 << tTier - 1) * (1 << tTier - 1) * this.level / this.costDiscount);
                this.mMaxProgresstime = Math.max(1, 512 / (1 << tTier - 1));
            }
            updateSlots();
            return true;
        }
        return false;
    }

    private static int divisionUp (int a, int b) {
        return a / b + ((a % b == 0) ? 0 : 1);
    }

    @Override
    public String[] getDescription() {
        String[] oldDescription = super.getDescription();
        String[] newDescription = Arrays.copyOf(oldDescription, oldDescription.length + 2);
        newDescription[oldDescription.length - 1] = EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "True multithreading!";
        newDescription[oldDescription.length] = Config.copyright;
        return newDescription;
    }
}
