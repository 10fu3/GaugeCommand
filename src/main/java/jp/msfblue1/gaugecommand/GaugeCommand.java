package jp.msfblue1.gaugecommand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class GaugeCommand extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("起動しました");
        getCommand("gauge").setExecutor(new Commands());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
