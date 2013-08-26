package andeedee.homecommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HomePlugin extends JavaPlugin {

	FileConfiguration config;
	public final char ColourSymbol = '\u00A7';

    @Override
    public void onEnable(){
        getLogger().info("Home Commands Enabled");
        config = this.getConfig();
        config.set("andeedee.hc.errorColor", "4");
        config.set("andeedee.hc.consoleError", "This command cannot be run from the console");
        config.set("andeedee.hc.homeNotFoundError", "Could not find that home");
        config.set("andeedee.hc.permissionError", "You do not have permission to run that command");
        config.set("andeedee.hc.internalError", "Arrrg you broke it");
    }

    @Override
    public void onDisable() {
    	getLogger().info("Home Commands Disabled");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(cmd.getName().equalsIgnoreCase("home")){
    		if(sender instanceof Player) {//only players can tp to home
    			if(checkPerm("andeedee.hc.home", sender)) {
    				Player p = (Player) sender;
    				home(p, args);
    			}
    			sender.sendMessage(ColourSymbol+config.getString("andeedee.hc.errorColor")+config.getString("andeedee.hc.permissionError"));
    			return true;
    		}
    		sender.sendMessage(ColourSymbol+config.getString("andeedee.hc.errorColor")+config.getString("andeedee.hc.consoleError"));
    		return true;
    	}


    	if(cmd.getName().equalsIgnoreCase("sethome")){
    		if(sender instanceof Player) { //only players can set homes
    			if(checkPerm("andeedee.hc.sethome", sender)) {
    				Player p = (Player) sender;
    				sethome(p, args);
    			}
    			sender.sendMessage(ColourSymbol+config.getString("andeedee.hc.errorColor")+config.getString("andeedee.hc.permissionError"));
    			return true;
    		}
    		sender.sendMessage(ColourSymbol+config.getString("andeedee.hc.errorColor")+config.getString("andeedee.hc.consoleError"));
    		return true;
    	}


    	if(cmd.getName().equalsIgnoreCase("listhomes")){
    		if(sender instanceof Player) { //only players can list homes.... TODO: let console list another players homes
    			if(checkPerm("andeedee.hc.listhomes", sender)) {
    				Player p = (Player) sender;
    				listhomes(p, args);
    			}
    			sender.sendMessage(ColourSymbol+config.getString("andeedee.hc.errorColor")+config.getString("andeedee.hc.permissionError"));
    			return true;
    		}
    		sender.sendMessage(ColourSymbol+config.getString("andeedee.hc.errorColor")+config.getString("andeedee.hc.consoleError"));
    		return true;
    	}


    	if(cmd.getName().equalsIgnoreCase("deletehome")){
			if(checkPerm("andeedee.hc.deletehome", sender)) {
				deletehome(sender, args);
			}
			sender.sendMessage(ColourSymbol+config.getString("andeedee.hc.errorColor")+config.getString("andeedee.hc.permissionError"));
			return true;
    	}


    	sender.sendMessage(ColourSymbol+config.getString("andeedee.hc.errorColor")+config.getString("andeedee.hc.internalError"));
    	return true;
    }

    private boolean checkPerm(String perm, CommandSender sender) {
    	if(sender.isOp()) return true;
    	if(sender.hasPermission(perm)) return true;
    	if(sender.hasPermission("andeedee.hc.*")) return true;
    	if(sender.hasPermission("andeedee.*")) return true;
    	return false;
    }

    private void home(Player p, String[] args) {

    }
    private void sethome(Player p, String[] args) {

    }
    private void deletehome(CommandSender s, String[] args) {

    }
    private void listhomes(Player p, String[] args) {

    }
}
