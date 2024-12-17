package dev.hybridlabs.aquatic.block

import net.minecraft.util.StringIdentifiable

enum class GiantClamState : StringIdentifiable {
    OPEN, CLOSED, DEAD;

    override fun asString(): String {
        return name.lowercase()
    }
}