package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.feature.DefaultFeatureConfig

class DeepCoralTreeFeature(codec: Codec<DefaultFeatureConfig?>?) : DeepCoralFeature(codec) {
    override fun generateDeepCoral(world: WorldAccess, random: Random, pos: BlockPos, state: BlockState): Boolean {
        val mutable = pos.mutableCopy()
        val i = random.nextInt(3) + 1

        for (j in 0 until i) {
            if (!this.generateDeepCoralPiece(world, random, mutable, state)) {
                return true
            }

            mutable.move(Direction.UP)
        }

        val blockPos = mutable.toImmutable()
        val k = random.nextInt(3) + 2
        val list = Direction.Type.HORIZONTAL.getShuffled(random)
        val list2: List<Direction> = list.subList(0, k)
        val var11: Iterator<*> = list2.iterator()

        while (var11.hasNext()) {
            val direction = var11.next() as Direction
            mutable.set(blockPos)
            mutable.move(direction)
            val l = random.nextInt(5) + 2
            var m = 0

            var n = 0
            while (n < l && this.generateDeepCoralPiece(world, random, mutable, state)) {
                ++m
                mutable.move(Direction.UP)
                if (n == 0 || m >= 2 && random.nextFloat() < 0.25f) {
                    mutable.move(direction)
                    m = 0
                }
                ++n
            }
        }

        return true
    }
}