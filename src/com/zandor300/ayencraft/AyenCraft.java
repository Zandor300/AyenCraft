package com.zandor300.ayencraft;

import com.zandor300.core.utilities.Chat;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Zandor300.
 * Date: 31-12-2014
 * Time: 13:03
 * PC: ZANDOR-PC
 */
public class AyenCraft extends JavaPlugin{

	private static AyenCraft plugin;
	public static AyenCraft getPlugin() {
		return plugin;
	}
	private static Chat chat = new Chat("AyenCraft", ChatColor.DARK_GREEN);

	@Override
	public void onEnable() {
		chat.sendConsoleMessage("Setting plugin up...");

		plugin = this;

		chat.sendConsoleMessage("AyenCraft enabled.");
	}

	@Override
	public void onDisable() {
		chat.sendConsoleMessage("Cleaning server up...");

		chat.sendConsoleMessage("Everything is snining again!");
		chat.sendConsoleMessage("AyenCraft disabled.");
	}
}
