package cn.nk.yuziouo;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.plugin.PluginBase;


public class Loader  extends PluginBase implements Listener {
    @Override
    public void onEnable(){
        getServer().getLogger().alert("Plugin Enable");
        getServer().getPluginManager().registerEvents(this,this);
    }
    @Override
    public void onDisable(){
        getServer().getLogger().alert("Plugin Disable");
    }

    public Grade getPlayer(String name) {
        return new Grade(name,this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player =event.getPlayer();
        String name = event.getPlayer().getName();
        Grade g = new Grade(name,this);
        if (!player.hasPlayedBefore()){
            g.grade(name);
            player.sendMessage("你的等级:" + g.getGrade() + "§r\n" + "你的經驗:" + g.getExp() + "§r\n" + "你的血量:" + g.getmh());
        }else {
            player.sendMessage("你的等级:" + g.getGrade() + "§r\n" + "你的經驗:" + g.getExp() + "§r\n" + "你的血量:" + g.getmh());
            player.setMaxHealth(g.getmh());
            player.setHealth(g.getmh());
        }

    }
}
