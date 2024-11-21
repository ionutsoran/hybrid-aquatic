package dev.hybridlabs.aquatic.block.enum

import net.minecraft.util.StringIdentifiable

enum class GiantClamState : StringIdentifiable {
    OPEN, CLOSED, DEAD;

    override fun asString(): String {
        return name.lowercase()
    }
}