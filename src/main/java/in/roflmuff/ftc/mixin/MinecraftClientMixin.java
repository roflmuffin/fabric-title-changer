package in.roflmuff.ftc.mixin;

import in.roflmuff.ftc.config.FabricTitleChangerConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.ModStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
@Environment(EnvType.CLIENT)
public class MinecraftClientMixin
{
	@Inject(at = @At(value = "RETURN"), method = "getWindowTitle", cancellable = true)
	private void getWindowTitle(final CallbackInfoReturnable<String> info)
	{
		MinecraftClient client = (MinecraftClient) (Object) this;

		if (FabricTitleChangerConfig.overrideFullTitle != null)
		{

			String returnVal = FabricTitleChangerConfig.overrideFullTitle;
			if (client.getSession() != null)
			{
				returnVal = returnVal.replaceAll("%username%", client.getSession().getUsername());
			}

			info.setReturnValue(returnVal);
			return;
		}

		StringBuilder stringBuilder = new StringBuilder();
		if (FabricTitleChangerConfig.overrideMinecraftTitle != null)
		{
			stringBuilder.append(FabricTitleChangerConfig.overrideMinecraftTitle);
		}
		else
		{
			stringBuilder.append("Minecraft");
		}

		if (ModStatus.check("vanilla", ClientBrandRetriever::getClientModName, "Client", MinecraftClient.class).isModded() && FabricTitleChangerConfig.includeAsterisk)
		{
			stringBuilder.append("*");
		}

		if (FabricTitleChangerConfig.includeVersionNumber)
		{
			stringBuilder.append(" ");
			stringBuilder.append(SharedConstants.getGameVersion().getName());
		}

		if (FabricTitleChangerConfig.includeNetworkStatus)
		{
			ClientPlayNetworkHandler clientPlayNetworkHandler = client.getNetworkHandler();
			if (clientPlayNetworkHandler != null && clientPlayNetworkHandler.getConnection().isOpen())
			{
				stringBuilder.append(" - ");
				if (client.getServer() != null && !client.getServer().isRemote())
				{
					stringBuilder.append(I18n.translate("title.singleplayer", new Object[0]));
				}
				else if (client.isConnectedToRealms())
				{
					stringBuilder.append(I18n.translate("title.multiplayer.realms", new Object[0]));
				}
				else if (client.getServer() == null && (client.getCurrentServerEntry() == null || !client.getCurrentServerEntry().isLocal()))
				{
					stringBuilder.append(I18n.translate("title.multiplayer.other", new Object[0]));
				}
				else
				{
					stringBuilder.append(I18n.translate("title.multiplayer.lan", new Object[0]));
				}
			}
		}

		info.setReturnValue(stringBuilder.toString());
	}
}