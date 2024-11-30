package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.entity.*
import net.minecraft.entity.ai.control.AquaticMoveControl
import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.boss.BossBar
import net.minecraft.entity.boss.ServerBossBar
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.world.Difficulty
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.EasingType

class ManglerfishEntity(entityType: EntityType<out HybridAquaticMinibossEntity>, world: World) :
    HybridAquaticMinibossEntity(entityType, world) {
    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        pitch = 0.0f
        yaw = 0.0f
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        moveControl = AquaticMoveControl(this, 75, 5, movementSpeed, 0.1f, true)
        lookControl = YawAdjustingLookControl(this, 10)
        navigation = SwimNavigation(this, world)
    }

    override fun initGoals() {
        goalSelector.add(1, ManglerfishAttackGoal(this))
        goalSelector.add(1, LookAtEntityGoal(this, PlayerEntity::class.java, 12.0f))
        goalSelector.add(4, SwimAroundGoal(this, 1.0, 10))
        targetSelector.add(1, RevengeGoal(this))
        targetSelector.add(2, ManglerfishTargetGoal(this, PlayerEntity::class.java, 10, true, true))
    }

    override fun createNavigation(world: World): EntityNavigation {
        return SwimNavigation(this, world)
    }

    override fun getGroup(): EntityGroup {
        return EntityGroup.AQUATIC
    }

    override fun getSoundCategory(): SoundCategory {
        return SoundCategory.HOSTILE
    }

    override fun checkDespawn() {
        if (world.difficulty == Difficulty.PEACEFUL && this.isDisallowedInPeaceful) {
            discard()
        } else {
            despawnCounter = 0
        }
    }

    //#region Bossbar
    private var bossBar: ServerBossBar = ServerBossBar(displayName, BossBar.Color.BLUE, BossBar.Style.NOTCHED_6)

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        if (hasCustomName()) {
            bossBar.name = this.displayName
        }
        super.readCustomDataFromNbt(nbt)
    }

    override fun setCustomName(name: Text?) {
        super.setCustomName(name)
        bossBar.name = this.displayName
    }

    override fun onStartedTrackingBy(player: ServerPlayerEntity) {
        super.onStartedTrackingBy(player)
        bossBar.addPlayer(player)
    }

    override fun onStoppedTrackingBy(player: ServerPlayerEntity) {
        super.onStoppedTrackingBy(player)
        bossBar.removePlayer(player)
    }

    override fun mobTick() {

        bossBar.percent = health / maxHealth

        super.mobTick()
    }

    //#endregion

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "Walk/Run/Idle",
                20
            ) { state: AnimationState<HybridAquaticMinibossEntity> ->
                when {
                    state.isMoving -> {
                        state.setAndContinue(if (this.isSprinting) DefaultAnimations.RUN else DefaultAnimations.SWIM)
                    }

                    else -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            }
        )
        controllerRegistrar.add(
            AnimationController(
                this,
                "Block/Idle",
                5
            ) { state: AnimationState<HybridAquaticMinibossEntity> ->
                when {
                    this.isBlocking -> {
                        state.setAndContinue(DefaultAnimations.ATTACK_BLOCK)
                    }

                    else -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            }.setOverrideEasingType(EasingType.EASE_IN_OUT_SINE)
        )
        controllerRegistrar.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SWING))
    }

    //#endregion

    //#region SFX
    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_ELDER_GUARDIAN_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_ELDER_GUARDIAN_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_FISH_SWIM
    }

    //#endregion

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 8.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0)
        }
    }

    internal open class ManglerfishAttackGoal(private val manglerfish: ManglerfishEntity) :
        MeleeAttackGoal(manglerfish, 0.7, false) {
        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.isCooledDown) {
                resetCooldown()
                mob.tryAttack(target)
                manglerfish.attemptAttack = true
            }
            if (!this.isCooledDown)
                manglerfish.handSwinging
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (4.0f + entity.width).toDouble()
        }

        override fun start() {
            super.start()
            manglerfish.isSprinting = true
            manglerfish.attemptAttack = false
        }

        override fun stop() {
            super.stop()
            manglerfish.isSprinting = false
            manglerfish.attemptAttack = false
        }
    }

    internal class ManglerfishTargetGoal(
        private val manglerfish: ManglerfishEntity,
        targetClass: Class<PlayerEntity>,
        reciprocalChance: Int,
        checkVisibility: Boolean,
        checkCanNavigate: Boolean
    ) : ActiveTargetGoal<PlayerEntity>(
        manglerfish,
        targetClass,
        reciprocalChance,
        checkVisibility,
        checkCanNavigate,
        null
    ) {

        override fun start() {
            super.start()
            manglerfish.world.playSound(
                null,
                manglerfish.blockPos,
                SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE,
                manglerfish.soundCategory,
                1.0f,
                1.0f
            )
        }
    }
}