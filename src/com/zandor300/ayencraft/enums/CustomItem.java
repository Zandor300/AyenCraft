package com.zandor300.ayencraft.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Zandor300.
 * Date: 31-12-2014
 * Time: 13:15
 * PC: ZANDOR-PC
 */
public class CustomItem {

	private static ArrayList<CustomItem> allItems = new ArrayList<CustomItem>();

	private final String name;
	private final ItemStack itemStack;

	public CustomItem(String name, String fancyName, int itemId, int data, String[] lore) {
		this.name = name;
		ItemStack itemStack1 = new ItemStack(Material.getMaterial(itemId), 1, (byte) data);
		ItemMeta itemMeta = itemStack1.getItemMeta();
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', fancyName));
		ArrayList<String> lore1 = new ArrayList<String>();
		for(String string : lore)
			lore1.add(ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', string));
		itemMeta.setLore(lore1);
		itemStack1.setItemMeta(itemMeta);
		this.itemStack = itemStack1;

		allItems.add(this);
	}

	public static CustomItem getItem(String name) {
		for(CustomItem item : allItems)
			if(item.getName().equalsIgnoreCase(name))
				return item;
		return null;
	}

	public static CustomItem getFancyItem(String fancyName) {
		for(CustomItem item : allItems)
			if(item.getFancyName().equalsIgnoreCase(fancyName))
				return item;
		return null;
	}

	public static ArrayList<CustomItem> getAllItems() {
		return allItems;
	}

	public String getName() {
		return name;
	}

	public String getFancyName() {
		return itemStack.getItemMeta().getDisplayName();
	}

	public Material getType() {
		return itemStack.getType();
	}

	public ItemStack getItemStack() {
		return itemStack;
	}

	public void give(Player player, int amount) {
		for(int i = amount; i < amount; i++)
			player.getInventory().addItem(itemStack);
	}

	public void set(Player player, int slot, int amount) {
		if(amount > 64)
			amount = 64;
		for(int i = amount; i < amount; i++)
			player.getInventory().setItem(slot, itemStack);
	}
}
