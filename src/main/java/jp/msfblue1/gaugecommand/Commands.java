package jp.msfblue1.gaugecommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Created by msfblue1 on 2018/03/28.
 */
public class Commands implements CommandExecutor{

    public final String PREFIX = ChatColor.DARK_GRAY+"["+ChatColor.AQUA+"GaugeCommand"+ChatColor.DARK_GRAY+"] ";

    public void putsERROR(CommandSender p , String mes){
        p.sendMessage(PREFIX+ChatColor.RED+mes);
        p.sendMessage(PREFIX+ChatColor.YELLOW+"=======  ヘルプ  =======");
        p.sendMessage(PREFIX+ChatColor.GREEN+"/gauge プレーヤー名 減らすゲージ ゲージの引き落としに成功したときのコマンド");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if("gauge".equalsIgnoreCase(command.getName())) {

            if(!sender.isOp()){
                putsERROR(sender,"権限がありません!");
                return true;
            }
            if (args.length >= 3) {
                try{
                    Integer.parseInt(args[1]);
                }catch (NumberFormatException e){
                    putsERROR(sender,"レベルが数字ではありません");
                    return true;
                }
                Bukkit.getOnlinePlayers()
                        .stream()
                        .filter(p -> args[0].equalsIgnoreCase(p.getName()))
                        .findFirst()
                        .ifPresent(p -> {
                            if(p.getFoodLevel() - Integer.parseInt(args[1]) >= 0){
                                p.setFoodLevel(p.getFoodLevel() - Integer.parseInt(args[1]));
                                String cmd = String.join(" ",Arrays.asList(args).subList(2,args.length));
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),cmd);
                                Bukkit.getLogger().info(cmd);
                            }else{
                                //Bukkit.getLogger().info("失敗！ｗ");
                            }
                        });
            }
            else {
                putsERROR(sender,"引数が足りません");
            }
        }
        return true;
    }
}
