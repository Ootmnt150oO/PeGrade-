package cn.nk.yuziouo;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;

import java.io.File;

public class Grade {
    private Config config;
    private String name;
    private ConfigSection configSection = new ConfigSection(){{
        put("等級",0);
        put("經驗",0);
        put("血量",20);
    }};
    private Loader plugin;

    public Grade(String name, Loader loader) {
        this.plugin = loader;
        this.name = name;
        this.config = new Config(new File(this.plugin.getDataFolder(), "players") + "/" + this.name + ".yml",
                2,
                this.configSection
        );
    }

    public Config grade(String r){
        File name = new File(this.plugin.getDataFolder()+"/players/" + r + ".yml");
        return new Config(name);
    }

    public void addGrade(int grade){
        this.config.set("等級",this.config.getInt("等級") + grade);
        this.config.save();
    }
    public void setGrade(int grade){
        this.config.set("等級",grade);
        this.config.save();
    }
    public int getGrade(){
        return this.config.getInt("等級");
    }
    public void addExp(Player player,int exp){
        this.config.set("經驗",this.config.getInt("經驗") + exp);
        this.config.save();
        if (this.config.getInt("經驗")>=maxExp()){
            upgrade();
            player.setMaxHealth(setmh());
            player.sendMessage("恭喜你，升级啦"+ "§r\n" +"你的等级+1" + "§r\n" + "你的血量+" + this.config.getInt("等級")+1*5);
        }
    }
    public void setExp(int exp){
        this.config.set("經驗",exp);
        this.config.save();
    }
    public int getExp(){
        return this.config.getInt("經驗");
    }
    public int maxExp(){
        return this.config.getInt("等級")*20+20;
    }
    public void upgrade(){
        this.config.set("等級",this.config.getInt("等級") + 1);
        this.config.set("經驗",0);
        this.config.set("血量",this.config.getInt("血量")+this.config.getInt("等級")+1*5);
        this.config.save();
    }
    public int getmh(){
        return this.config.getInt("血量");
    }
    public int setmh() {
        return this.config.getInt("血量")+this.config.getInt("等級")+1*5;
    }
}

