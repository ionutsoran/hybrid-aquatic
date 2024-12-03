package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.feature.DefaultFeatureConfig

class DeepCoralMushroomFeature(codec: Codec<DefaultFeatureConfig>) : DeepCoralFeature(codec) {
    override fun generateDeepCoral(world: WorldAccess, random: Random, pos: BlockPos, state: BlockState): Boolean {
        val i = random.nextInt(3) + 3
        val j = random.nextInt(3) + 3
        val k = random.nextInt(3) + 3
        val l = random.nextInt(3) + 1
        val mutable = pos.mutableCopy()

        for (m in 0..j) {
            for (n in 0..i) {
                for (o in 0..k) {
                    mutable[m + pos.x, n + pos.y] = o + pos.z
                    mutable.move(Direction.DOWN, l)
                    if ((m != 0 && m != j || n != 0 && n != i) && (o != 0 && o != k || n != 0 && n != i) && (m != 0 && m != j || o != 0 && o != k) && (m == 0 || m == j || n == 0 || n == i || o == 0 || o == k) && !(random.nextFloat() < 0.1f) && !this.generateDeepCoralPiece(
                            world,
                            random,
                            mutable,
                            state
                        )
                    ) {
                    }
                }
            }
        }

        return true
    }
}
