package de.dasphiller.eventsystem

import de.dasphiller.eventsystem.commands.StartCommand
import de.dasphiller.eventsystem.listener.ConnectionListeners
import de.dasphiller.eventsystem.listener.PlayerDeathListener
import net.axay.kspigot.main.KSpigot
import org.bukkit.entity.Player

class Main : KSpigot() {

    companion object {
        val masters: ArrayList<Player> = ArrayList<Player>()
        val mastersAlive: ArrayList<Player> = ArrayList<Player>()
        val players: ArrayList<Player> = ArrayList<Player>()
    }
    override fun startup() {
        ConnectionListeners
        PlayerDeathListener
        getCommand("start")?.setExecutor(StartCommand())
        logger.info("The Plugin was enabled!")
    }

    override fun shutdown() {
        logger.info("The Plugin was disabled!")
    }
}
