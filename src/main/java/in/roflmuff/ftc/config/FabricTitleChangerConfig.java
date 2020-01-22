package in.roflmuff.ftc.config;

public class FabricTitleChangerConfig {
    @Config(config="settings", category = "windowTitle", key = "includeAsterisk", comment = "Include the asterisk for modded versions")
    public static boolean includeAsterisk = true;

    @Config(config="settings", category = "windowTitle", key = "includeVersionNumber", comment = "Include the version number (1.15.2, etc)")
    public static boolean includeVersionNumber = true;

    @Config(config="settings", category = "windowTitle", key = "includeNetworkStatus", comment = "Include the network status (Singleplayer, Multiplayer, LAN, etc.)")
    public static boolean includeNetworkStatus = true;

    @Config(config="settings", category = "windowTitle", key = "overrideMinecraftTitle", comment = "Set to change the phrase \"Minecraft\" in the title.")
    public static String overrideMinecraftTitle = null;

    @Config(config="settings", category = "windowTitle", key = "overrideFullTitle", comment = "Set to ignore other settings and define a full pre-defined name.")
    public static String overrideFullTitle = null;
}
