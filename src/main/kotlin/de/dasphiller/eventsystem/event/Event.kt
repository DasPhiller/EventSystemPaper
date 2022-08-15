package de.dasphiller.eventsystem.event

import net.kyori.adventure.text.Component
import org.bukkit.Material

abstract class Event {

    abstract val name: String
    abstract val id: String
    abstract val icon: Material
    abstract val desc: List<Component>


}