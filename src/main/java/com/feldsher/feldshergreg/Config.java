package com.feldsher.feldshergreg;

import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraft.util.EnumChatFormatting;

public class Config {

    private static class Defaults {
        public static final String greeting = "Hello World";
        public static final String copyright = "Added by " + EnumChatFormatting.DARK_GREEN+"FeldsherGreg";
    }

    private static class Categories {
        public static final String general = "general";
    }

    public static String greeting = Defaults.greeting;
    public static String copyright = Defaults.copyright;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);
        configuration.load();

        Property greetingProperty =
                configuration.get(Categories.general, "greeting", Defaults.greeting, "How shall I greet?");
        greeting = greetingProperty.getString();

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
