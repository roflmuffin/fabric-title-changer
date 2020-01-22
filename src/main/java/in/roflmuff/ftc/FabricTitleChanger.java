package in.roflmuff.ftc;

import in.roflmuff.ftc.config.Configuration;
import in.roflmuff.ftc.config.FabricTitleChangerConfig;
import net.fabricmc.api.ModInitializer;

public class FabricTitleChanger implements ModInitializer {
	@Override
	public void onInitialize() {
		new Configuration(FabricTitleChangerConfig.class, "fabric-title-changer");
	}
}
