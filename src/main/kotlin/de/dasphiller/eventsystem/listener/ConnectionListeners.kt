package de.dasphiller.eventsystem.listener

import de.dasphiller.eventsystem.Main
import de.dasphiller.eventsystem.game.GameState
import de.dasphiller.eventsystem.util.state
import de.dasphiller.extensions.extensions.color
import de.dasphiller.extensions.extensions.itemBuilder
import de.dasphiller.extensions.message.MiniMessageHelper
import net.axay.kspigot.event.listen
import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent

object ConnectionListeners {
    private val join = listen<PlayerJoinEvent> {
        if (onlinePlayers.size == 1) {
            it.player.isOp = true
            Main.masters.add(it.player)
            Main.mastersAlive.add(it.player)
            it.player.inventory.setItem(4, itemBuilder(Material.REPEATER, MiniMessageHelper.message("${color("candypink")}Settings lol"), 1, lore = listOf(MiniMessageHelper.message("testt"))))
        } else {
            Main.players.add(it.player)
        }
        it.joinMessage(MiniMessageHelper.message("${color("blue")}${it.player.name} ${color("gray")}hat das Spiel betreten"))
    }
    private val login = listen<PlayerLoginEvent> {
        if (state != GameState.LOBBY) {
            it.disallow(PlayerLoginEvent.Result.KICK_OTHER, MiniMessageHelper.message("${color("gray")}Das Spiel hat bereits begonnen!"))
        }
    }
    private val quit = listen<PlayerQuitEvent> {
        if (Main.masters.contains(it.player)) {
            it.player.isOp = false
            Main.masters.remove(it.player)
            Main.mastersAlive.remove(it.player)
        } else {
            Main.players.remove(it.player)
        }
        it.quitMessage(MiniMessageHelper.message("${color("red")}${it.player.name}${color("gray")} hat das Spiel verlassen"))
    }
}