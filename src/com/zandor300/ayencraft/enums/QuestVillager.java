package com.zandor300.ayencraft.enums;

import com.zandor300.ayencraft.AyenCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Zandor300.
 * Date: 31-12-2014
 * Time: 13:12
 * PC: ZANDOR-PC
 */
public class QuestVillager implements Listener {

	private static ArrayList<QuestVillager> allVillagers = new ArrayList<QuestVillager>();
	private HashMap<String, Integer> playerLine = new HashMap<String, Integer>();
	private final ArrayList<String> gaveItem = new ArrayList<String>();
	private final ArrayList<String> tookItem = new ArrayList<String>();

	private final String name;
	private final String fancyName;
	private ArrayList<String> story = new ArrayList<String>();
	private final Location location;

	private final CustomItem giveItem;
	private final int giveItemAmount;
	private final String giveItemAcceptLine;

	private final CustomItem takeItem;
	private final int takeItemAmount;
	private final String takeItemDenyLine;

	private final boolean multiTradable;

	private LivingEntity entity;

	public QuestVillager(String name, String fancyName, ArrayList<String> story, Location location, CustomItem giveItem, int giveItemAmount, String giveItemAcceptLine,
						 CustomItem takeItem, int takeItemAmount, String takeItemDenyLine, boolean multiTradable) {
		this.name = name;
		this.fancyName = fancyName;
		this.story = story;
		this.location = location;

		this.giveItem = giveItem;
		this.giveItemAmount = giveItemAmount;
		this.giveItemAcceptLine = giveItemAcceptLine;

		this.takeItem = takeItem;
		this.takeItemAmount = takeItemAmount;
		this.takeItemDenyLine = takeItemDenyLine;

		this.multiTradable = multiTradable;

		allVillagers.add(this);

		Bukkit.getPluginManager().registerEvents(this, AyenCraft.getPlugin());
	}

	public static QuestVillager getQuestVillager(String name) {
		for(QuestVillager villager : allVillagers)
			if(villager.getName().equalsIgnoreCase(name))
				return villager;
		return null;
	}

	public static QuestVillager getFancyQuestVillager(String fancyName) {
		for(QuestVillager villager : allVillagers)
			if(villager.getFancyName().equalsIgnoreCase(fancyName))
				return villager;
		return null;
	}

	public static ArrayList<QuestVillager> getAllVillagers() {
		return allVillagers;
	}

	public String getName() {
		return name;
	}

	public String getFancyName() {
		return fancyName;
	}

	public ArrayList<String> getStory() {
		return story;
	}

	public CustomItem getGiveItem() {
		return giveItem;
	}

	public int getGiveItemAmount() {
		return giveItemAmount;
	}

	public String getGiveItemAcceptLine() {
		return giveItemAcceptLine;
	}

	public CustomItem getTakeItem() {
		return takeItem;
	}

	public int getTakeItemAmount() {
		return takeItemAmount;
	}

	public String getTakeItemDenyLine() {
		return takeItemDenyLine;
	}

	public boolean isMultiTradable() {
		return multiTradable;
	}

	public LivingEntity getEntity() {
		return entity;
	}

	public void respawn() {
		despawn();
		spawn();
	}

	public void despawn() {
		if(entity != null){
			entity.remove();
			entity = null;
		}
	}

	public void spawn() {
		despawn();
		location.getChunk().load();
		entity = location.getWorld().spawnCreature(location, CreatureType.VILLAGER);
		entity.setCustomNameVisible(true);
		entity.setCustomName(fancyName + ChatColor.DARK_GREEN + " [Quest]");
		entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 257));
	}

	@EventHandler
	private void onEntityDamage(EntityDamageEvent event) {
		if(event.getEntity().getUniqueId().equals(entity.getUniqueId()))
			event.setCancelled(true);
	}
}
