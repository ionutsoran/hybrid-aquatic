package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import net.minecraft.block.BlockState
import net.minecraft.util.Util
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import java.util.stream.Stream

class DeepCoralClawFeature(codec: Codec<DefaultFeatureConfig>) : DeepCoralFeature(codec) {
    override fun generateDeepCoral(world: WorldAccess, random: Random, pos: BlockPos, state: BlockState): Boolean {
        if (!this.generateDeepCoralPiece(world, random, pos, state)) {
            return false
        } else {
            val direction = Direction.Type.HORIZONTAL.random(random)
            val i = random.nextInt(2) + 2
            val list = Util.copyShuffled(
                Stream.of(
                    direction,
                    direction.rotateYClockwise(),
                    direction.rotateYCounterclockwise()
                ), random
            )
            val list2: List<Direction> = list.subList(0, i)
            val var9: Iterator<*> = list2.iterator()

            while (var9.hasNext()) {
                val direction2 = var9.next() as Direction
                val mutable = pos.mutableCopy()
                val j = random.nextInt(2) + 1
                mutable.move(direction2)
                var k: Int
                var direction3: Direction
                if (direction2 == direction) {
                    direction3 = direction
                    k = random.nextInt(3) + 2
                } else {
                    mutable.move(Direction.UP)
                    val directions = arrayOf(direction2, Direction.UP)
                    direction3 = Util.getRandom(directions, random) as Direction
                    k = random.nextInt(3) + 3
                }
                var l = 0
                while (l < j && this.generateDeepCoralPiece(world, random, mutable, state)) {
                    mutable.move(direction3)
                    ++l
                }

                mutable.move(direction3.opposite)
                mutable.move(Direction.UP)

                l = 0
                while (l < k) {
                    mutable.move(direction)
                    if (!this.generateDeepCoralPiece(world, random, mutable, state)) {
                        break
                    }

                    if (random.nextFloat() < 0.25f) {
                        mutable.move(Direction.UP)
                    }
                    ++l
                }
            }

            return true
        }
    }
}
