package com.feldsher.feldshergreg;

import com.feldsher.feldshergreg.tileentities.*;
import cpw.mods.fml.common.event.*;

public class CommonProxy {

    public static GT_MetaTileEntity_Quarry1 quarry1;
    public static GT_MetaTileEntity_Quarry2 quarry2;
    public static GT_MetaTileEntity_Quarry3 quarry3;
    public static GT_MetaTileEntity_Quarry4 quarry4;

    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    public void preInit(FMLPreInitializationEvent event) {
        Config.syncronizeConfiguration(event.getSuggestedConfigurationFile());

        FeldsherGreg.info(Config.greeting);
        FeldsherGreg.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION + " and group name " + Tags.GROUPNAME);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes."
    public void init(FMLInitializationEvent event) {
        quarry1 = new GT_MetaTileEntity_Quarry1(15001, "multimachine.quarry1", "Quarry I");
        quarry2 = new GT_MetaTileEntity_Quarry2(15002, "multimachine.quarry2", "Quarry II");
        quarry3 = new GT_MetaTileEntity_Quarry3(15003, "multimachine.quarry3", "Quarry III");
        quarry4 = new GT_MetaTileEntity_Quarry4(15004, "multimachine.quarry4", "Quarry IV");
    }

    // postInit "Handle interaction with other mods, complete your setup based on this."
    public void postInit(FMLPostInitializationEvent event) {}

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {}

    // register server commands in this event handler
    public void serverStarting(FMLServerStartingEvent event) {}

    public void serverStarted(FMLServerStartedEvent event) {}

    public void serverStopping(FMLServerStoppingEvent event) {}

    public void serverStopped(FMLServerStoppedEvent event) {}
}
