package de.dasphiller.eventsystem.listener

import de.dasphiller.eventsystem.Main
import de.dasphiller.eventsystem.game.GameState
import de.dasphiller.eventsystem.util.state
import de.dasphiller.extensions.extensions.color
import de.dasphiller.extensions.message.Message
import de.dasphiller.extensions.message.MessagePrefix
import de.dasphiller.extensions.message.MiniMessageHelper
import net.axay.kspigot.event.listen
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent

object PlayerDeathListener {
    private val death = listen<PlayerDeathEvent> {
        if (Main.masters.contains(it.player)) {
            Message(MessagePrefix.DEFAULT, "player won").broadcast()
        } else {
            if (Main.players.size == 0) {
                Message(MessagePrefix.DEFAULT, "master won").broadcast()
            }
            it.player.kick(MiniMessageHelper.message("kicked lol"))
        }
        it.deathMessage(MiniMessageHelper.message("${color("blue")}${it.player.name} ${color("gray")}wurde von ${color("blue")}${it.player.killer?.name} ${color("gray")}getötet"))
    }
    private val damage = listen<EntityDamageEvent> {
        if (state != GameState.RUNNING) {
            it.isCancelled = true
        }
    }
}