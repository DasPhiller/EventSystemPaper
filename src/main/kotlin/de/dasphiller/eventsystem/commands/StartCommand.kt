package de.dasphiller.eventsystem.commands

import de.dasphiller.eventsystem.Main
import de.dasphiller.eventsystem.game.GameState
import de.dasphiller.eventsystem.util.state
import net.axay.kspigot.runnables.task
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class StartCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return true
        val player: Player = sender
        player.sendMessage(if (Main.masters.contains(player)) "master" else "player")
        if (player.isOp) {
            state = GameState.RUNNING
            task(false, 600, 0) {

            }
        }
        return true
    }
}