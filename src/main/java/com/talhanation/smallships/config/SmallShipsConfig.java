package com.talhanation.smallships.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;
import java.util.ArrayList;

@Mod.EventBusSubscriber
public class SmallShipsConfig {


    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec CONFIG;

    public static ForgeConfigSpec.IntValue VERSION;
    public static final int NEW_VERSION = 4;

    public static ForgeConfigSpec.BooleanValue PlaySwimmSound;
    public static ForgeConfigSpec.BooleanValue WaterMobFlee;
    public static ForgeConfigSpec.DoubleValue GalleySpeedFactor;
    public static ForgeConfigSpec.DoubleValue GalleyTurnFactor;
    public static ForgeConfigSpec.DoubleValue WarGalleySpeedFactor;
    public static ForgeConfigSpec.DoubleValue WarGalleyTurnFactor;
    public static ForgeConfigSpec.DoubleValue CogSpeedFactor;
    public static ForgeConfigSpec.DoubleValue CogTurnFactor;
    public static ForgeConfigSpec.DoubleValue DrakkarSpeedFactor;
    public static ForgeConfigSpec.DoubleValue DrakkarTurnFactor;
    public static ForgeConfigSpec.DoubleValue DrakkarIceBreakSpeed;
    public static ForgeConfigSpec.DoubleValue RowBoatSpeedFactor;
    public static ForgeConfigSpec.DoubleValue RowBoatTurnFactor;
    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> PassengerBlackList;

    static {
        VERSION = BUILDER.comment("\n" +"##Version, do not change!##")
                .defineInRange("Version", 0, 0, Integer.MAX_VALUE);

        BUILDER.comment("Small Ships Config:").push("Ships");

        GalleySpeedFactor = BUILDER.comment("\n" +"----Galley Speed Factor.----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "default: 1.3")
                .worldRestart()
                .defineInRange("GalleySpeedFactor", 1.3, 0.0, 2.0);

        GalleyTurnFactor = BUILDER.comment("\n" +"----Galley Turn Factor.----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "default: 0.5")
                .worldRestart()
                .defineInRange("GalleyTurnFactor", 0.5, 0.0, 1.0);

        CogSpeedFactor = BUILDER.comment("\n" +"----Cog Speed Factor.----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "default: 1.25" )
                .worldRestart()
                .defineInRange("CogSpeedFactor", 1.25, 0.0, 2.0);

        CogTurnFactor = BUILDER.comment("\n" +"----Cog Turn Factor.----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "default: 0.1")
                .worldRestart()
                .defineInRange("CogTurnFactor", 0.1, 0.0, 1.0);

        WarGalleySpeedFactor = BUILDER.comment("\n" +"----War Galley Speed Factor.----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "default: 1.15")
                .worldRestart()
                .defineInRange("WarGalleySpeedFactor", 1.15, 0.0, 2.0);

        WarGalleyTurnFactor = BUILDER.comment("\n" +"----War Galley Turn Factor.----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "default: 0.4")
                .worldRestart()
                .defineInRange("WarGalleyTurnFactor", 0.4, 0.0, 1.0);

        DrakkarSpeedFactor = BUILDER.comment("\n" +"----Drakkar Speed Factor.----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "default: 1.25")
                .worldRestart()
                .defineInRange("DrakkarSpeedFactor", 1.25, 0.0, 2.0);

        DrakkarTurnFactor = BUILDER.comment("\n" +"----Drakkar Turn Factor.----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "default: 0.3")
                .worldRestart()
                .defineInRange("DrakkarTurnFactor", 0.3, 0.0, 1.0);

        DrakkarIceBreakSpeed = BUILDER.comment("\n" +"----Drakkar Ice Break Speed.----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "higher values = slower break speed"  + "\n" +
                "\t" + "default: 2.0")
                .worldRestart()
                .defineInRange("DrakkarIceBreakSpeed", 2.0, 0.0, 100);

        RowBoatSpeedFactor = BUILDER.comment("\n" +"----Row Boat Speed Factor.----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "default: 1.0")
                .worldRestart()
                .defineInRange("DrakkarSpeedFactor", 1.0, 0.0, 2.0);

        RowBoatTurnFactor = BUILDER.comment("\n" +"----Row Boat Turn Factor.----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "default: 0.8")
                .worldRestart()
                .defineInRange("DrakkarTurnFactor", 0.8, 0.0, 1.0);

        PlaySwimmSound = BUILDER.comment("\n" + "----Should Ships Make Swimming sounds?----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "default: true")
                .define("PlaySwimmSound", true);

        WaterMobFlee = BUILDER.comment("\n" + "----Should Ships Make WaterMobs flee?----" + "\n" +
                "\t" + "(takes effect after restart)" + "\n" +
                "\t" + "default: true")
                .define("WaterMobFlee", true);



        CONFIG = BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        configData.load();
        spec.setConfig(configData);
        if (VERSION.get() != NEW_VERSION) {
            configData = CommentedFileConfig.builder(path)
                    .sync()
                    .autosave()
                    .writingMode(WritingMode.REPLACE)
                    .build();
            spec.setConfig(configData);
            VERSION.set(NEW_VERSION);
            configData.save();
        }
    }
}
