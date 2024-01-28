package com.feldsher.feldshergreg.tileentities;

import static gregtech.api.enums.GT_Values.VN;

import java.util.ArrayList;
import java.util.List;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.gui.GT_GUIContainer_MultiMachine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.objects.ItemData;
import gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_DrillerBase;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkPosition;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.IFluidBlock;
import scala.actors.threadpool.Arrays;
import net.minecraft.block.BlockLiquid;

import com.feldsher.feldshergreg.Config;


public abstract class GT_MetaTileEntity_QuarryBase extends GT_MetaTileEntity_DrillerBase {

    private static final ItemStack miningPipeTip = GT_ModHandler.getIC2Item("miningPipeTip", 0);
    private static final Block miningPipeTipBlock = GT_Utility.getBlockFromStack(miningPipeTip);
    private int mLastXOff = 0, mLastZOff = 0;
    private int blocks = 1;

    public GT_MetaTileEntity_QuarryBase(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public GT_MetaTileEntity_QuarryBase(String aName) {
        super(aName);
    }

    @Override
    public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_GUIContainer_MultiMachine(aPlayerInventory, aBaseMetaTileEntity, getLocalName(), "OreDrillingPlant.png");
    }

    @Override
    protected boolean workingDownward(ItemStack aStack, int xDrill, int yDrill, int zDrill, int xPipe, int zPipe, int yHead, int oldYHead) {
        if (yDrill == yHead)
        {
            switch (tryLowerPipe()) {
            case 2: mMaxProgresstime = 0; return false; 
            case 3: workState = STATE_UPWARD; return true;
            case 1: workState = STATE_AT_BOTTOM; return true;
            case 0: return true;
            }
        }
        
        int radius = getRadius();
        if (mLastXOff == 0 && mLastZOff == 0) {
			mLastXOff = -radius;
			mLastZOff = -radius;
        }

        int state = -1;
        for (int b = 0; b < this.blocks; b++)
        {
        	int new_state = working(xDrill, zDrill, yHead);
	        switch (new_state) {
	        case 2: return (state == 1 ? true : false);
	        case 1: state = new_state;
	        }
	        
	        if (new_state == 0) {
	        	state = -1;
	        	break;
        	}
        }
        if (state == 1) return true;

        switch (tryLowerPipe()) {
        case 2: mMaxProgresstime = 0; return false;
        case 3: workState = STATE_UPWARD; return true;
        case 1: workState = STATE_AT_BOTTOM; return true;
        }

        mLastXOff = -radius;
        mLastZOff = -radius;
        return true;
    }
    
    private int working(int xDrill, int zDrill, int yHead) {
        int radius = getRadius();
    	for (int i = mLastXOff; i <= radius; i++) {
            for (int j = (i == mLastXOff ? mLastZOff : -radius); j <= radius; j++) {
                int aX = xDrill + i;
                int aY = yHead;
                int aZ = zDrill + j;
                Block block = getBaseMetaTileEntity().getBlock(aX, aY, aZ);

                if (isHarvestable(block, aX, aY, aZ)) {
                    if (tryBreak(block, aX, aY, aZ)) {
                        mLastXOff = i;
                        mLastZOff = j;
                        return 1;
                    }
                    return 2;
                }
                else if (canDrain(block, aX, aY, aZ))
                {
                    pumpBlock(block, aX, aY, aZ);
                    return 1;
                }
            }
        }
    	return 0;
    }

    @Override
    protected boolean checkHatches(){
    	return !mMaintenanceHatches.isEmpty() && !mInputHatches.isEmpty() 
                && !mOutputBusses.isEmpty() && !mEnergyHatches.isEmpty();
    }
    
    @Override
    protected void setElectricityStats() {
        this.mEfficiency = getCurrentEfficiency(null);
        this.mEfficiencyIncrease = 10000;
        int tier = Math.max(1, GT_Utility.getTier(getMaxInputVoltage()));
        this.mEUt = -3 * (1 << (tier << 1));
        double progresstime = (double)(workState == STATE_DOWNWARD ? getBaseProgressTime() : 80) / (double)(1 << tier);
        this.blocks = (int)(1.0 / progresstime);
        this.mMaxProgresstime = Math.max(1, (int)progresstime);
    }

	@SuppressWarnings("unchecked")
	private boolean tryBreak(final Block block, int aX, int aY, int aZ)
    {
        if (tryConsumeDrillingFluid() == false) return false;

        int blockMeta = getBaseMetaTileEntity().getMetaID(aX, aY, aZ);
        List<ItemStack> outputItems = 
            block.getDrops(getBaseMetaTileEntity().getWorld(), aX, aY, aZ, blockMeta, 1);
        if (mOutputItems != null)
        	outputItems.addAll(Arrays.asList(mOutputItems));
        mOutputItems = outputItems.toArray(new ItemStack[outputItems.size()]); 
        
        setAir(aX, aY, aZ);
        return true;
    }

    private boolean canDrain(final Block block, int aX, int aY, int aZ)
    {
        if (!GT_Utility.eraseBlockByFakePlayer(getFakePlayer(getBaseMetaTileEntity()), aX, aY, aZ, true)) return false;

        if (block instanceof IFluidBlock) {
            if (((IFluidBlock)block).canDrain(getBaseMetaTileEntity().getWorld(), aX, aY, aZ)) {
                return true;
            }
            else {
                setAir(aX, aY, aZ);
                return false;
            }
        }
        else if (block == Blocks.water || block == Blocks.flowing_water || block == Blocks.lava || block == Blocks.flowing_water) {
            if (getBaseMetaTileEntity().getMetaID(aX, aY, aZ) == 0) {
                return true;
            }
            else {
                setAir(aX, aY, aZ);
                return false;
            }
        }
        return false;
    }

    private boolean isHarvestable(final Block block, int aX, int aY, int aZ) {
        if (block instanceof IFluidBlock
            || block instanceof BlockFluidClassic
            || block instanceof BlockStaticLiquid 
            || block instanceof BlockDynamicLiquid
            || block == Blocks.air
            || block == miningPipeTipBlock
            || block.getBlockHardness(getBaseMetaTileEntity().getWorld(), aX, aY, aZ) < 0.0F)
            return false;
        return GT_Utility.eraseBlockByFakePlayer(getFakePlayer(getBaseMetaTileEntity()), aX, aY, aZ, true);
    }

    public void pumpBlock(final Block block, int aX, int aY, int aZ) {
        if(block == Blocks.water){
            setAir(aX, aY, aZ);
            addOutput(GT_ModHandler.getWater(1000L));
        }
        else if(block == Blocks.lava){
            setAir(aX, aY, aZ);
            addOutput(GT_ModHandler.getLava(1000L));
        }
        else if(block instanceof IFluidBlock){
            FluidStack fStack = ((IFluidBlock)block).drain(getBaseMetaTileEntity().getWorld(), aX, aY, aZ, false);
            setAir(aX, aY, aZ);
            addOutput(fStack);
        }
    }

    private void setAir(int aX, int aY, int aZ) {
        getBaseMetaTileEntity().getWorld().setBlock(aX, aY, aZ, Blocks.air, 0, 2);
    }

    private boolean tryConsumeDrillingFluid() {
        if (!depleteInput(new FluidStack(ItemList.sDrillingFluid, 50))) {
        	mMaxProgresstime = 0;
        	return false;
        }
        return true;
    }

    protected abstract int getRadius();

    protected abstract int getBaseProgressTime();

    protected String[] getDescriptionInternal(String tierSuffix) {
        String casings = getCasingBlockItem().get(0).getDisplayName();
        return new String[]{
                "Controller Block for the Quarry " + (tierSuffix != null ? tierSuffix : ""),
                "Size(WxHxD): 3x7x3, Controller (Front middle bottom)",
                "3x1x3 Base of " + casings,
                "1x3x1 " + casings + " pillar (Center of base)",
                "1x3x1 " + getFrameMaterial().mName + " Frame Boxes (Each pillar side and on top)",
                "1x Input Hatch for drilling fluid (Any bottom layer casing)",
                "1x Input Bus for mining pipes (Any bottom layer casing; not necessary)",
                "1x Output Bus (Any bottom layer casing)",
                "1x Maintenance Hatch (Any bottom layer casing)",
                "1x " + VN[getMinTier()] + "+ Energy Hatch (Any bottom layer casing)",
                "Radius is " + getRadius() + " blocks",
                Config.copyright};
    }
}
