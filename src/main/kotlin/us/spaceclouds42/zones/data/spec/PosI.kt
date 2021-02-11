package us.spaceclouds42.zones.data.spec

import kotlinx.serialization.Serializable

/**
 * PositionBase type, used to store
 * position of zone corners
 */
@Serializable
data class PosI(
    /**
     * Dimension identifier as string. Ex: "minecraft:overworld"
     */
    val world: String,

    /**
     * x coordinate
     */
    val x: Int,

    /**
     * y coordinate
     */
    val y: Int,

    /**
     * z coordinate
     */
    val z: Int,
)