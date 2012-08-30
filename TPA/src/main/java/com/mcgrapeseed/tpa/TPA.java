package com.mcgrapeseed.tpa;

import org.bukkit.plugin.java.JavaPlugin;

public class TPA extends JavaPlugin{
	
	public void onEnable(){
		getCommand("tpa").setExecutor(new TPACommand(this));
	}

}
