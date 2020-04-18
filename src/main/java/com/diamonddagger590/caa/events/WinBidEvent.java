package com.diamonddagger590.caa.events;

import com.diamonddagger590.caa.actionbar.ActionBar;
import com.diamonddagger590.caa.datastorage.AnnouncerLimiter;
import com.diamonddagger590.caa.main.CrazyAuctionsAnnouncer;
import com.diamonddagger590.caa.util.Methods;
import me.badbones69.crazyauctions.api.ShopType;
import me.badbones69.crazyauctions.api.events.AuctionWinBidEvent;
import me.badbones69.crazyenchantments.api.CrazyEnchantments;
import me.badbones69.crazyenchantments.api.objects.CEBook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class WinBidEvent implements Listener{
	
	@EventHandler
	public void winBid(AuctionWinBidEvent e) {
		Player p = e.getPlayer();
		ItemStack item = e.getItem();
		long bid = e.getBid();
		String itemType = Methods.convertName(item.getType(), item.getDurability());
		if(Bukkit.getPluginManager().isPluginEnabled("CrazyEnchantments") && CrazyEnchantments.getInstance().isEnchantmentBook(item)) {
			CEBook book = CrazyEnchantments.getInstance().getCEBook(item);
			String power = Methods.convertToNumeral(book.getEnchantment().getPower(item));
			itemType = book.getEnchantment().getName() + " " + power;
		}
		String serverMessage = Methods.color(CrazyAuctionsAnnouncer.getPluginPrefix() + CrazyAuctionsAnnouncer.getConfigFile().getString("Messages.AuctionWinBid"));
		String displayName;
		if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()){
			displayName = item.getItemMeta().getDisplayName();
		}
		else{
			displayName = Methods.convertName(item.getType());
		}
		serverMessage = Methods.translateMessage(serverMessage, p, bid, item.getAmount(), itemType, ShopType.BID.getName(), displayName);
		String discordMessage = CrazyAuctionsAnnouncer.getConfigFile().getString("Discord.Messages.AuctionWinBid");
		discordMessage = Methods.translateMessage(discordMessage, p, bid, item.getAmount(), itemType, ShopType.BID.getName(), displayName);
		String displayType = CrazyAuctionsAnnouncer.getConfigFile().getString("Settings.MessageSendTo");
		if((!CrazyAuctionsAnnouncer.getConfigFile().getBoolean("Settings.UseAnnouncementLimit")) || AnnouncerLimiter.canAnnounce()) {
			if(displayType.equalsIgnoreCase("both") || displayType.equalsIgnoreCase("server")) {
				if(CrazyAuctionsAnnouncer.getConfigFile().getBoolean("Server.EventEnabler.AuctionWinBid")) {
					String displayTypeServer = CrazyAuctionsAnnouncer.getConfigFile().getString("Server.DisplayType.AuctionWinBid");
					for(Player play : Bukkit.getOnlinePlayers()) {
						if(displayTypeServer.equalsIgnoreCase("message")) {
							play.sendMessage(serverMessage);
						}
						else if(displayTypeServer.equalsIgnoreCase("subtitle")){
							ActionBar.sendTitle(play, "", serverMessage, CrazyAuctionsAnnouncer.getConfigFile().getInt("Server.TitleConfig.AuctionWinBid.FadeInTime") * 20,
								CrazyAuctionsAnnouncer.getConfigFile().getInt("Server.TitleConfig.AuctionWinBid.StayTime") * 20,
								CrazyAuctionsAnnouncer.getConfigFile().getInt("Server.TitleConfig.AuctionWinBid.FadeOutTime") * 20);
						}
						else if(displayTypeServer.equalsIgnoreCase("title")) {
							ActionBar.sendTitle(play, "", serverMessage, CrazyAuctionsAnnouncer.getConfigFile().getInt("Server.TitleConfig.AuctionWinBid.FadeInTime") * 20,
								CrazyAuctionsAnnouncer.getConfigFile().getInt("Server.TitleConfig.AuctionWinBid.StayTime") * 20,
								CrazyAuctionsAnnouncer.getConfigFile().getInt("Server.TitleConfig.AuctionWinBid.FadeOutTime") * 20);
						}
						else if(displayTypeServer.equalsIgnoreCase("action_bar")) {
							ActionBar.sendActionBar(play, serverMessage);
						}
					}
				}
			}
			if(displayType.equalsIgnoreCase("both") || displayType.equalsIgnoreCase("discord")) {
				String channel = CrazyAuctionsAnnouncer.getConfigFile().getString("Discord.Channels.AuctionWinBidServer");
				if(CrazyAuctionsAnnouncer.getConfigFile().getBoolean("Discord.EventEnabler.AuctionWinBid")) {
					Methods.sendDiscordMessage(discordMessage, channel);
                }
			}
		}
	}

}
