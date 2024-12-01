package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.block.Blocks
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis", "UNUSED_PARAMETER", "DEPRECATION")
open class HybridAquaticMinibossEntity(type: EntityType<out HostileEntity>, world: World) : HostileEntity(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var attackTick = 0
    var attemptAttack: Boolean
        get() = dataTracker.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            dataTracker.set(ATTEMPT_ATTACK, attemptAttack)
        }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(ATTEMPT_ATTACK, false)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("AttackTick", this.attackTick)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        this.attackTick = nbt.getInt("AttackTick")
    }

    override fun tick() {
        super.tick()
        if (isAiDisabled) {
            return
        }
    }

    override fun tickMovement() {
        this.tickHandSwing()
        super.tickMovement()
    }

    override fun tryAttack(target: Entity?): Boolean {
        this.attackTick = 10
        world.sendEntityStatus(this, 4.toByte())
        return super.tryAttack(target)
    }

    override fun isPushedByFluids(): Boolean {
        return false
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return false
    }

    override fun canBreatheInWater(): Boolean {
        return true
    }

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        return PlayState.STOP
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "controller",
                5,
                ::predicate
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }



    override fun isAngryAt(player: PlayerEntity?): Boolean {
        return true
    }

    companion object {

        val ATTEMPT_ATTACK: TrackedData<Boolean> =
            DataTracker.registerData(HybridAquaticMinibossEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

        fun canSpawn(
            type: EntityType<out HostileEntity>,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            val topY = world.seaLevel
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.getFluidState(pos.down()).isIn(FluidTags.WATER) &&
                    world.getBlockState(pos.up()).isOf(Blocks.WATER)
        }
    }
}

