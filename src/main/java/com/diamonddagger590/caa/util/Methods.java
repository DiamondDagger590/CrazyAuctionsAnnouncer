package com.diamonddagger590.caa.util;

import com.diamonddagger590.caa.discordsrv.DiscordSRVManager;
import com.diamonddagger590.caa.main.CrazyAuctionsAnnouncer;
import com.diamonddagger590.caa.minecord.MineCordHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Methods {

  public static void sendDiscordMessage(String discordMessage, String channel){
    if(Bukkit.getPluginManager().isPluginEnabled("MineCordBot")){
      MineCordHandler.sendMessage(channel, discordMessage);
    }
		/*
		else if(Bukkit.getPluginManager().isPluginEnabled("UChat") || Bukkit.getPluginManager().isPluginEnabled("UltimateChat")){
			UChatHandler.sendMessage(channel, discordMessage);
		}*/
    else if(Bukkit.getPluginManager().isPluginEnabled("DiscordSRV")){
      DiscordSRVManager.sendMessage(channel, discordMessage);
    }
  }

  public static String translateMessage(String message, Player p, long bid, int amount, String itemType, String aucType, String displayName){
    if(displayName == null){
      displayName = "";
    }
    if(!displayName.equalsIgnoreCase("") && CrazyAuctionsAnnouncer.getConfigFile().contains("Settings.BannedDisplayNameWords")){
      for(String s : CrazyAuctionsAnnouncer.getConfigFile().getStringList("Settings.BannedDisplayNameWords")){
        if(message.contains(s)){
          message = message.replace(s, "***");
        }
      }
    }
    return message.replace("%Player%", p.getDisplayName()).replace("%Money%", Long.toString(bid)).replace("%Amount%", Integer.toString(amount)).replace("%Item%", itemType)
            .replace("%AuctionType%", aucType).replace("%DisplayName%", displayName);
  }

  public static String convertName(Material m){
    String[] chars = m.toString().split("");
    String itemType = "";
    boolean first = true;
    boolean capNext = false;
    for(String s : chars){
      if(first){
        itemType += s;
        first = false;
        continue;
      }
      else{
        if(s.equals("_")){
          itemType += " ";
          capNext = true;
          continue;
        }
        if(capNext){
          s = s.toUpperCase();
          capNext = false;
        }
        else{
          s = s.toLowerCase();
        }
        itemType += s;
        continue;
      }
    }
    return itemType;
  }

  public static String color(String msg){
    return ChatColor.translateAlternateColorCodes('&', msg);
  }

  public static String convertSpecialName(final Material m, final short data){
    if(m.equals(Material.ACACIA_DOOR_ITEM)){
      return Material.ACACIA_DOOR.toString();
    }
    if(m.equals(Material.BIRCH_DOOR_ITEM)){
      return Material.BIRCH_DOOR.toString();
    }
    if(m.equals(Material.DARK_OAK_DOOR_ITEM)){
      return Material.DARK_OAK_DOOR.toString();
    }
    if(m.equals(Material.JUNGLE_DOOR_ITEM)){
      return Material.JUNGLE_DOOR.toString();
    }
    if(m.equals(Material.WOODEN_DOOR) || m.equals(Material.WOOD_DOOR)){
      return "OAK_DOOR";
    }
    if(m.equals(Material.SPRUCE_DOOR_ITEM)){
      return Material.SPRUCE_DOOR.toString();
    }
    if(m.equals(Material.COOKED_FISH)){
      if(data == 0){
        return m.toString();
      }
      if(data == 1){
        return "COOKED_SALMON";
      }
    }
    else if(m.equals(Material.RAW_FISH)){
      if(data == 0){
        return m.toString();
      }
      if(data == 1){
        return "RAW_SALMON";
      }
      if(data == 2){
        return "CLOWNFISH";
      }
      if(data == 3){
        return "PUFFERFISH";
      }
    }
    else if(m.equals(Material.INK_SACK)){
      if(data == 0){
        return m.toString();
      }
      if(data == 1){
        return "ROSE_RED_DYE";
      }
      if(data == 2){
        return "CACTUS_GREEN_DYE";
      }
      if(data == 3){
        return "COCOA_BEANS";
      }
      if(data == 4){
        return "LAPIS_LAZULI";
      }
      if(data == 5){
        return "PURPLE_DYE";
      }
      if(data == 6){
        return "CYAN_DYE";
      }
      if(data == 7){
        return "LIGHT_GRAY_DYE";
      }
      if(data == 8){
        return "GRAY_DYE";
      }
      if(data == 9){
        return "PINK_DYE";
      }
      if(data == 10){
        return "LIME_DYE";
      }
      if(data == 11){
        return "DANDELION_YELLOW_DYE";
      }
      if(data == 12){
        return "LIGHT_BLUE_DYE";
      }
      if(data == 13){
        return "MAGENTA_DYE";
      }
      if(data == 14){
        return "ORANGE_DYE";
      }
      if(data == 15){
        return "BONE_MEAL";
      }
    }
    else if(m.equals(Material.STONE)){
      if(data == 0){
        return m.toString();
      }
      if(data == 1){
        return "GRANITE";
      }
      if(data == 2){
        return "POLISHED_GRANITE";
      }
      if(data == 3){
        return "DIORITE";
      }
      if(data == 4){
        return "POLISHED_DIORITE";
      }
      if(data == 5){
        return "ANDESITE";
      }
      if(data == 6){
        return "POLISHED_ANDESITE";
      }
    }
    else if(m.equals(Material.DIRT)){
      if(data == 0){
        return m.toString();
      }
      if(data == 1){
        return "COARSE_DIRT";
      }
      if(data == 2){
        return "PODZOL";
      }
    }
    else if(m.equals(Material.SAND)){
      if(data == 0){
        return m.toString();
      }
      if(data == 1){
        return "RED_SAND";
      }
    }
    else if(m.equals(Material.WOOD)){
      if(data == 0){
        return "OAK_WOOD_PLANKS";
      }
      if(data == 1){
        return "SPRUCE_WOOD_PLANKS";
      }
      if(data == 2){
        return "BIRCH_WOOD_PLANKS";
      }
      if(data == 3){
        return "JUNGLE_WOOD_PLANKS";
      }
      if(data == 4){
        return "ACACIA_WOOD_PLANKS";
      }
      if(data == 5){
        return "DARK_OAK_WOOD_PLANKS";
      }
    }
    else if(m.equals(Material.WOOD_STEP)){
      if(data == 0){
        return "OAK_WOOD_SLAB";
      }
      if(data == 1){
        return "SPRUCE_WOOD_SLAB";
      }
      if(data == 2){
        return "BIRCH_WOOD_SLAB";
      }
      if(data == 3){
        return "JUNGLE_WOOD_SLAB";
      }
      if(data == 4){
        return "ACACIA_WOOD_SLAB";
      }
      if(data == 5){
        return "DARK_OAK_WOOD_SLAB";
      }
    }
    else if(m.equals(Material.QUARTZ_BLOCK)){
      if(data == 0){
        return "BLOCK_OF_QUARTZ";
      }
      if(data == 1){
        return "CHISLED_QUARTZ_BLOCK";
      }
      if(data == 2){
        return "PILLAR_QUARTZ_BLOCK";
      }
    }
    else if(m.equals(Material.SAPLING)){
      if(data == 0){
        return "OAK_SAPLING";
      }
      if(data == 1){
        return "SPRUCE_SAPLING";
      }
      if(data == 2){
        return "BIRCH_SAPLING";
      }
      if(data == 3){
        return "JUNGLE_SAPLING";
      }
      if(data == 4){
        return "ACACIA_SAPLING";
      }
      if(data == 5){
        return "DARK_OAK_SAPLING";
      }
    }
    else if(m.equals(Material.LOG)){
      if(data == 0){
        return "OAK_WOOD";
      }
      if(data == 1){
        return "SPRUCE_WOOD";
      }
      if(data == 2){
        return "BIRCH_WOOD";
      }
      if(data == 3){
        return "JUNGLE_WOOD";
      }
    }
    else if(m.equals(Material.LOG_2)){
      if(data == 0){
        return "ACACIA_WOOD";
      }
      if(data == 1){
        return "DARK_OAK_WOOD";
      }
    }
    else if(m.equals(Material.LEAVES)){
      if(data == 0){
        return "OAK_LEAVES";
      }
      if(data == 1){
        return "SPRUCE_LEAVES";
      }
      if(data == 2){
        return "BIRCH_LEAVES";
      }
      if(data == 3){
        return "JUNGLE_LEAVES";
      }
    }
    else if(m.equals(Material.LEAVES_2)){
      if(data == 0){
        return "ACACIA_LEAVES";
      }
      if(data == 1){
        return "DARK_OAK_LEAVES";
      }
    }
    else if(m.equals(Material.LONG_GRASS)){
      if(data == 2){
        return "FERN";
      }
    }
    else if(m.equals(Material.RED_ROSE)){
      if(data == 0){
        return "POPPY";
      }
      if(data == 1){
        return "BLUE_ORCHID";
      }
      if(data == 2){
        return "ALLIUM";
      }
      if(data == 3){
        return "AZURE_BLUET";
      }
      if(data == 4){
        return "RED_TULIP";
      }
      if(data == 5){
        return "ORANGE_TULIP";
      }
      if(data == 6){
        return "WHITE_TULIP";
      }
      if(data == 7){
        return "PINK_TULIP";
      }
      if(data == 8){
        return "OXEYE_DAISY";
      }
    }
    else if(m.equals(Material.STEP)){
      if(data == 0){
        return "STONE_SLAB";
      }
      if(data == 1){
        return "SANDSTONE_SLAB";
      }
      if(data == 2){
        return "COBBLESTONE_SLAB";
      }
      if(data == 3){
        return "BRICK_SLAB";
      }
      if(data == 4){
        return "STONE_BRICKS_SLAB";
      }
      if(data == 5){
        return "NETHER_BRICK_SLAB";
      }
      if(data == 6){
        return "QUARTZ_SLAB";
      }
    }
    else if(m.equals(Material.SMOOTH_BRICK)){
      if(data == 0){
        return "STONE_BRICKS";
      }
      if(data == 1){
        return "MOSSY_STONE_BRICKS";
      }
      if(data == 2){
        return "CRACKED_STONE_BRICKS";
      }
      if(data == 3){
        return "CHISELED_STONE_BRICKS";
      }
    }
    else if(m.equals(Material.SANDSTONE)){
      if(data == 0){
        return m.toString();
      }
      if(data == 1){
        return "CHISELED_SANDSTONE";
      }
      if(data == 2){
        return "SMOOTH_SANDSTONE";
      }
    }
    else{
      if(m.equals(Material.CARPET) || m.equals(Material.WOOL) || m.equals(Material.STAINED_GLASS) || m.equals(Material.STAINED_GLASS_PANE) || (getMinecraftVersion().contains("1.12") && (m.equals(Material.CONCRETE) || m.equals(m.equals(Material.CONCRETE_POWDER))))){
        final String mat = m.toString();
        String color = null;
        if(data == 0){
          color = "WHITE_";
        }
        else if(data == 1){
          color = "ORANGE_";
        }
        else if(data == 2){
          color = "MAGENTA_";
        }
        else if(data == 3){
          color = "LIGHT_BLUE_";
        }
        else if(data == 4){
          color = "YELLOW_";
        }
        else if(data == 5){
          color = "LIME_";
        }
        else if(data == 6){
          color = "PINK_";
        }
        else if(data == 7){
          color = "GRAY_";
        }
        else if(data == 8){
          color = "LIGHT_GRAY_";
        }
        else if(data == 9){
          color = "CYAN_";
        }
        else if(data == 10){
          color = "PURPLE_";
        }
        else if(data == 11){
          color = "BLUE_";
        }
        else if(data == 12){
          color = "BROWN_";
        }
        else if(data == 13){
          color = "GREEN_";
        }
        else if(data == 14){
          color = "RED_";
        }
        else if(data == 15){
          color = "BLACK_";
        }
        return color + mat;
      }
      if(m.equals(Material.PRISMARINE)){
        if(data == 0){
          return m.toString();
        }
        if(data == 1){
          return "PRISMARINE_BRICKS";
        }
        if(data == 2){
          return "DARK_PRISMARINE";
        }
      }
      else if(m.equals(Material.DOUBLE_PLANT)){
        if(data == 0){
          return "SUNFLOWER";
        }
        if(data == 1){
          return "LILAC";
        }
        if(data == 2){
          return "TALL_GRASS";
        }
        if(data == 3){
          return "LARGE_FERN";
        }
        if(data == 4){
          return "ROSE_BUSH";
        }
        if(data == 5){
          return "PEONY";
        }
      }
      else if(m.equals(Material.RED_SANDSTONE)){
        if(data == 0){
          return m.toString();
        }
        if(data == 1){
          return "CHISELED_RED_SANDSTONE";
        }
        if(data == 2){
          return "SMOOTH_RED_SANDSTONE";
        }
      }
    }
    return m.toString();
  }

  private static String getMinecraftVersion(){
    return Bukkit.getServer().getVersion();
  }

  public static String convertName(final Material m, final short data){
    final String[] chars = convertSpecialName(m, data).split("");
    String itemType = "";
    boolean first = true;
    boolean capNext = false;
    String[] array;
    for(int length = (array = chars).length, i = 0; i < length; ++i){
      String s = array[i];
      if(first){
        itemType = itemType + s;
        first = false;
      }
      else if(s.equals("_")){
        itemType = itemType + " ";
        capNext = true;
      }
      else{
        if(capNext){
          s = s.toUpperCase();
          capNext = false;
        }
        else{
          s = s.toLowerCase();
        }
        itemType = itemType + s;
      }
    }
    return itemType;
  }

}
