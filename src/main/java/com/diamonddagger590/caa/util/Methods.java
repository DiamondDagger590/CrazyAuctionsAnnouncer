package com.diamonddagger590.caa.util;

import com.diamonddagger590.caa.discordsrv.DiscordSRVManager;
import com.diamonddagger590.caa.main.CrazyAuctionsAnnouncer;
import com.diamonddagger590.caa.minecord.MineCordHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Methods {
	
	public static void sendDiscordMessage(String discordMessage, String channel) {
		if(Bukkit.getPluginManager().isPluginEnabled("MineCordBot")) {
			MineCordHandler.sendMessage(channel, discordMessage);
		}
		/*
		else if(Bukkit.getPluginManager().isPluginEnabled("UChat") || Bukkit.getPluginManager().isPluginEnabled("UltimateChat")){
			UChatHandler.sendMessage(channel, discordMessage);
		}*/
		else if(Bukkit.getPluginManager().isPluginEnabled("DiscordSRV")) {
			DiscordSRVManager.sendMessage(channel, discordMessage);
		}
	}
	
	public static String translateMessage(String message, Player p, long bid, int amount, String itemType, String aucType, String displayName) {
		if(displayName == null) {
			displayName = "";
		}
		if(!displayName.equalsIgnoreCase("") && CrazyAuctionsAnnouncer.getConfigFile().contains("Settings.BannedDisplayNameWords")) {
			for(String s : CrazyAuctionsAnnouncer.getConfigFile().getStringList("Settings.BannedDisplayNameWords")) {
				if(message.contains(s)) {
					message = message.replace("s", "***");
				}
			}
		}
		return message.replace("%Player%", p.getDisplayName()).replace("%Money%", Long.toString(bid)).replace("%Amount%", Integer.toString(amount)).replace("%Item%", itemType)
		.replace("%AuctionType%", aucType).replace("%DisplayName%", displayName);
	}
	
	public static String convertName(Material m) {
		String[] chars = m.toString().split("");
		String itemType = "";
		boolean first = true;
		boolean capNext = false;
		for(String s : chars) {
			if(first) {
				itemType += s;
				first = false;
			}else {
				if(s.equals("_")) {
					itemType += " ";
					capNext = true;
					continue;
				}
				if(capNext) {
					s = s.toUpperCase();
					capNext = false;
				}else {
					s = s.toLowerCase();
				}
				itemType += s;
			}
		}
		return itemType;
	}
	
	public static String convertName(final Material m, final short data) {
		//Server is 1.13+
		if(Version.getCurrentVersion().isNewer(Version.v1_12_R1)) {
			return convertName(m);
		}else {
			final String[] chars = convertSpecialName(m, data).split("");
			String itemType = "";
			boolean first = true;
			boolean capNext = false;
			String[] array;
			for(int length = (array = chars).length, i = 0; i < length; ++i) {
				String s = array[i];
				if(first) {
					itemType = itemType + s;
					first = false;
				}else if(s.equals("_")) {
					itemType = itemType + " ";
					capNext = true;
				}else {
					if(capNext) {
						s = s.toUpperCase();
						capNext = false;
					}else {
						s = s.toLowerCase();
					}
					itemType += s;
				}
			}
			return itemType;
		}
	}
	
	public static String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static String convertSpecialName(final Material m, final short data) {
		if(m.equals(Material.matchMaterial("ACACIA_DOOR_ITEM"))) {
			return Material.ACACIA_DOOR.toString();
		}
		if(m.equals(Material.matchMaterial("BIRCH_DOOR_ITEM"))) {
			return Material.BIRCH_DOOR.toString();
		}
		if(m.equals(Material.matchMaterial("DARK_OAK_DOOR_ITEM"))) {
			return Material.DARK_OAK_DOOR.toString();
		}
		if(m.equals(Material.matchMaterial("JUNGLE_DOOR_ITEM"))) {
			return Material.JUNGLE_DOOR.toString();
		}
		if(m.equals(Material.matchMaterial("WOODEN_DOOR")) || m.equals(Material.matchMaterial("WOOD_DOOR"))) {
			return "OAK_DOOR";
		}
		if(m.equals(Material.matchMaterial("SPRUCE_DOOR_ITEM"))) {
			return Material.SPRUCE_DOOR.toString();
		}
		if(m.equals(Material.matchMaterial("COOKED_FISH"))) {
			switch(data) {
				case 0:
					return m.toString();
				case 1:
					return "COOKED_SALMON";
			}
		}else if(m.equals(Material.matchMaterial("RAW_FISH"))) {
			switch(data) {
				case 0:
					return m.toString();
				case 1:
					return "RAW_SALMON";
				case 2:
					return "CLOWNFISH";
				case 3:
					return "PUFFERFISH";
			}
		}else if(m.equals(Material.matchMaterial("INK_SACK"))) {
			switch(data) {
				case 0:
					return m.toString();
				case 1:
					return "ROSE_RED_DYE";
				case 2:
					return "CACTUS_GREEN_DYE";
				case 3:
					return "COCOA_BEANS";
				case 4:
					return "LAPIS_LAZULI";
				case 5:
					return "PURPLE_DYE";
				case 6:
					return "CYAN_DYE";
				case 7:
					return "LIGHT_GRAY_DYE";
				case 8:
					return "GRAY_DYE";
				case 9:
					return "PINK_DYE";
				case 10:
					return "LIME_DYE";
				case 11:
					return "DANDELION_YELLOW_DYE";
				case 12:
					return "LIGHT_BLUE_DYE";
				case 13:
					return "MAGENTA_DYE";
				case 14:
					return "ORANGE_DYE";
				case 15:
					return "BONE_MEAL";
			}
		}else if(m.equals(Material.STONE)) {
			switch(data) {
				case 0:
					return m.toString();
				case 1:
					return "GRANITE";
				case 2:
					return "POLISHED_GRANITE";
				case 3:
					return "DIORITE";
				case 4:
					return "POLISHED_DIORITE";
				case 5:
					return "ANDESITE";
				case 6:
					return "POLISHED_ANDESITE";
			}
		}else if(m.equals(Material.DIRT)) {
			switch(data) {
				case 0:
					return m.toString();
				case 1:
					return "COARSE_DIRT";
				case 2:
					return "PODZOL";
			}
		}else if(m.equals(Material.SAND)) {
			switch(data) {
				case 0:
					return m.toString();
				case 1:
					return "RED_SAND";
			}
		}else if(m.equals(Material.matchMaterial("WOOD"))) {
			switch(data) {
				case 0:
					return "OAK_WOOD_PLANKS";
				case 1:
					return "SPRUCE_WOOD_PLANKS";
				case 2:
					return "BIRCH_WOOD_PLANKS";
				case 3:
					return "JUNGLE_WOOD_PLANKS";
				case 4:
					return "ACACIA_WOOD_PLANKS";
				case 5:
					return "DARK_OAK_WOOD_PLANKS";
			}
		}else if(m.equals(Material.matchMaterial("WOOD_STEP"))) {
			switch(data) {
				case 0:
					return "OAK_WOOD_SLAB";
				case 1:
					return "SPRUCE_WOOD_SLAB";
				case 2:
					return "BIRCH_WOOD_SLAB";
				case 3:
					return "JUNGLE_WOOD_SLAB";
				case 4:
					return "ACACIA_WOOD_SLAB";
				case 5:
					return "DARK_OAK_WOOD_SLAB";
			}
		}else if(m.equals(Material.QUARTZ_BLOCK)) {
			switch(data) {
				case 0:
					return "BLOCK_OF_QUARTZ";
				case 1:
					return "CHISLED_QUARTZ_BLOCK";
				case 2:
					return "PILLAR_QUARTZ_BLOCK";
			}
		}else if(m.equals(Material.matchMaterial("SAPLING"))) {
			switch(data) {
				case 0:
					return "OAK_SAPLING";
				case 1:
					return "SPRUCE_SAPLING";
				case 2:
					return "BIRCH_SAPLING";
				case 3:
					return "JUNGLE_SAPLING";
				case 4:
					return "ACACIA_SAPLING";
				case 5:
					return "DARK_OAK_SAPLING";
			}
		}else if(m.equals(Material.matchMaterial("LOG"))) {
			switch(data) {
				case 0:
					return "OAK_WOOD";
				case 1:
					return "SPRUCE_WOOD";
				case 2:
					return "BIRCH_WOOD";
				case 3:
					return "JUNGLE_WOOD";
			}
		}else if(m.equals(Material.matchMaterial("LOG_2"))) {
			switch(data) {
				case 0:
					return "ACACIA_WOOD";
				case 1:
					return "DARK_OAK_WOOD";
			}
		}else if(m.equals(Material.matchMaterial("LEAVES"))) {
			switch(data) {
				case 0:
					return "OAK_LEAVES";
				case 1:
					return "SPRUCE_LEAVES";
				case 2:
					return "BIRCH_LEAVES";
				case 3:
					return "JUNGLE_LEAVES";
			}
		}else if(m.equals(Material.matchMaterial("LEAVES_2"))) {
			switch(data) {
				case 0:
					return "ACACIA_LEAVES";
				case 1:
					return "DARK_OAK_LEAVES";
			}
		}else if(m.equals(Material.matchMaterial("LONG_GRASS"))) {
			if(data == 2) {
				return "FERN";
			}
		}else if(m.equals(Material.matchMaterial("RED_ROSE"))) {
			switch(data) {
				case 0:
					return "POPPY";
				case 1:
					return "BLUE_ORCHID";
				case 2:
					return "ALLIUM";
				case 3:
					return "AZURE_BLUET";
				case 4:
					return "RED_TULIP";
				case 5:
					return "ORANGE_TULIP";
				case 6:
					return "WHITE_TULIP";
				case 7:
					return "PINK_TULIP";
				case 8:
					return "OXEYE_DAISY";
			}
		}else if(m.equals(Material.matchMaterial("STEP"))) {
			switch(data) {
				case 0:
					return "STONE_SLAB";
				case 1:
					return "SANDSTONE_SLAB";
				case 2:
					return "COBBLESTONE_SLAB";
				case 3:
					return "BRICK_SLAB";
				case 4:
					return "STONE_BRICKS_SLAB";
				case 5:
					return "NETHER_BRICK_SLAB";
				case 6:
					return "QUARTZ_SLAB";
			}
		}else if(m.equals(Material.matchMaterial("SMOOTH_BRICK"))) {
			switch(data) {
				case 0:
					return "STONE_BRICKS";
				case 1:
					return "MOSSY_STONE_BRICKS";
				case 2:
					return "CRACKED_STONE_BRICKS";
				case 3:
					return "CHISELED_STONE_BRICKS";
			}
		}else if(m.equals(Material.SANDSTONE)) {
			switch(data) {
				case 0:
					return m.toString();
				case 1:
					return "CHISELED_SANDSTONE";
				case 2:
					return "SMOOTH_SANDSTONE";
			}
		}else {
			if(m.equals(Material.matchMaterial("CARPET")) || m.equals(Material.matchMaterial("WOOL")) || m.equals(Material.matchMaterial("STAINED_GLASS")) || m.equals(Material.matchMaterial("STAINED_GLASS_PANE")) || (Version.getCurrentVersion().isSame(Version.v1_12_R1) && (m.equals(Material.matchMaterial("CONCRETE")) || m.equals(Material.matchMaterial("CONCRETE_POWDER"))))) {
				final String mat = m.toString();
				String color = null;
				switch(data) {
					case 0:
						return "WHITE_" + mat;
					case 1:
						return "ORANGE_" + mat;
					case 2:
						return "MAGENTA_" + mat;
					case 3:
						return "LIGHT_BLUE_" + mat;
					case 4:
						return "YELLOW_" + mat;
					case 5:
						return "LIME_" + mat;
					case 6:
						return "PINK_" + mat;
					case 7:
						return "GRAY_" + mat;
					case 8:
						return "LIGHT_GRAY_" + mat;
					case 9:
						return "CYAN_" + mat;
					case 10:
						return "PURPLE_" + mat;
					case 11:
						return "BLUE_" + mat;
					case 12:
						return "BROWN_" + mat;
					case 13:
						return "GREEN_" + mat;
					case 14:
						return "RED_" + mat;
					case 15:
						return "BLACK_" + mat;
				}
			}
			if(m.equals(Material.PRISMARINE)) {
				switch(data) {
					case 0:
						return m.toString();
					case 1:
						return "PRISMARINE_BRICKS";
					case 2:
						return "DARK_PRISMARINE";
				}
			}else if(m.equals(Material.matchMaterial("DOUBLE_PLANT"))) {
				switch(data) {
					case 0:
						return "SUNFLOWER";
					case 1:
						return "LILAC";
					case 2:
						return "TALL_GRASS";
					case 3:
						return "LARGE_FERN";
					case 4:
						return "ROSE_BUSH";
					case 5:
						return "PEONY";
				}
			}else if(m.equals(Material.RED_SANDSTONE)) {
				switch(data) {
					case 0:
						return m.toString();
					case 1:
						return "CHISELED_RED_SANDSTONE";
					case 2:
						return "SMOOTH_RED_SANDSTONE";
				}
			}
		}
		return m.toString();
	}
	
}
