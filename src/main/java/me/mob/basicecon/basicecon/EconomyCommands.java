package me.mob.basicecon.basicecon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EconomyCommands implements CommandExecutor {

    private final BasicEcon plugin = BasicEcon.getInstance;
    private final EconomyImplementer eIm = plugin.economyImplementer;
    private final PlayerInfo pi = plugin.playerInfo;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("send")) {
                if (args.length == 2) {
                    try {
                        String targ = args[0];
                        if (pi.getPlayerUUID(targ) != null) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(pi.getPlayerUUID(targ));
                            int depositAmount = Integer.parseInt(args[1]);
                            if (!eIm.hasAccount(target)) {
                                eIm.createPlayerAccount(target);
                            }
                            if (depositAmount <= 0) {
                                player.sendMessage(ChatColor.RED + "§lHEY! §r§7You must send a positive amount of money!");
                                return true;
                            }
                            if (depositAmount > eIm.getBalance(player)) {
                                player.sendMessage(ChatColor.RED + "§lHEY! §r§7You can't send more than you have!");
                                return true;
                            }

                            eIm.withdrawPlayer(player, depositAmount);
                            eIm.depositPlayer(target, depositAmount);
                            player.sendMessage(ChatColor.GRAY + "You have sent §a$" + depositAmount + "§7 to §a" + target.getName() + "§7's account.");
                        } else {
                            sender.sendMessage(ChatColor.RED + "§lHEY! §r§7Make sure that you spelled the name right, and that this person has played before!");
                            return true;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
            if (command.getName().equalsIgnoreCase("balance")) {
                try {
                    if (args.length == 1) {
                        String targ = args[0];
                        if (pi.getPlayerUUID(targ) != null) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(pi.getPlayerUUID(targ));
                            if (!eIm.hasAccount(target)) {
                                eIm.createPlayerAccount(target);
                            }
                            int balance = (int) eIm.getBalance(target);
                            player.sendMessage(ChatColor.GREEN + target.getName() + "§7 has §a$" + balance + "§7 in their account.");
                        } else {
                            sender.sendMessage(ChatColor.RED + "§lHEY! §r§7Make sure that you spelled the name right, and that this person has played before!");
                            return true;
                        }
                    }
                    if (args.length == 0) {
                        int balance = (int) eIm.getBalance(player);
                        player.sendMessage(ChatColor.GREEN + "You §7have §a$" + balance + "§7 in your account.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("addcash")) {
            if (args.length == 2) {
                String targ = args[0];
                OfflinePlayer target = Bukkit.getOfflinePlayer(pi.getPlayerUUID(targ));
                int depositAmount = Integer.parseInt(args[1]);
                if (target != null) {
                    if (!eIm.hasAccount(target)) {
                        eIm.createPlayerAccount(target);
                    }
                    if (depositAmount > 0) {
                        eIm.depositPlayer(target, depositAmount);
                        sender.sendMessage(ChatColor.GRAY + "You have sent §a$" + depositAmount + "§7 to §a" + target.getName() + "§7's account.");
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.RED + "§lHEY! §r§7You must send a positive amount of money!");
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "§lHEY! §r§7Make sure that you spelled the name right, and that this person has played before!");
                    return true;
                }
            }
        }
        if (command.getName().equalsIgnoreCase("setcash")) {
            if (args.length == 2) {
                String targ = args[0];
                OfflinePlayer target = Bukkit.getOfflinePlayer(pi.getPlayerUUID(targ));
                double setAmount = Double.parseDouble(args[1]);
                if (target != null) {
                    if (!eIm.hasAccount(target)) {
                        eIm.createPlayerAccount(target);
                    }
                    if (setAmount >= 0) {
                        UUID targetuuid = target.getUniqueId();
                        plugin.playerBank.put(targetuuid, setAmount);
                        sender.sendMessage(ChatColor.GRAY + "You set §a" + target.getName() + "§7's account to §a$" + setAmount + "§7.");
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.RED + "§lHEY! §r§7You must set a player account to a non-negative amount of money!");
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "§lHEY! §r§7Make sure that you spelled the name right, and that this person has played before!");
                    return true;
                }
            }
        }
        if (command.getName().equalsIgnoreCase("removecash")) {
            if (args.length == 2) {
                String targ = args[0];
                OfflinePlayer target = Bukkit.getOfflinePlayer(pi.getPlayerUUID(targ));
                double withdrawlAmount = Double.parseDouble(args[1]);
                if (target != null) {
                    if (!eIm.hasAccount(target)) {
                        eIm.createPlayerAccount(target);
                    }
                    if (withdrawlAmount > eIm.getBalance(target)) {
                        eIm.withdrawPlayer(target, withdrawlAmount);
                        sender.sendMessage(ChatColor.GRAY + "You have removed §a$" + withdrawlAmount + "§7 from §a" + target.getName() + "§7's account.");
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.RED + "§lHEY! §r§7You cannot remove more than the worth of the account!");
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "§lHEY! §r§7Make sure that you spelled the name right, and that this person has played before!");
                    return true;
                }
            }
        }
        return false;
    }
}
