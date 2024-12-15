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
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.world.Difficulty
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState

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
            AnimationController(this, "Walk/Run/Idle", 5,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticMinibossEntity> ->
                    if (state.isMoving) {
                        return@AnimationStateHandler state.setAndContinue(if (this.isSprinting) DefaultAnimations.RUN else DefaultAnimations.SWIM)
                    } else {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.IDLE)
                    }
                })
        )
        controllerRegistrar.add(
            DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE)
        )
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
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0)
        }
    }

    private fun getHandSwingDuration(): Int {
        return 20
    }

    override fun tickHandSwing() {
        val i = this.getHandSwingDuration()
        if (this.handSwinging) {
            ++this.handSwingTicks
            if (this.handSwingTicks >= i) {
                this.handSwingTicks = 0
                this.handSwinging = false
            }
        } else {
            this.handSwingTicks = 0
        }

        this.handSwingProgress = handSwingTicks.toFloat() / i.toFloat()
    }

    override fun getHandSwingProgress(tickDelta: Float): Float {
        var f = this.handSwingProgress - this.lastHandSwingProgress
        if (f < 0.0f) {
            ++f
        }

        return this.lastHandSwingProgress + f * tickDelta
    }

    internal open class ManglerfishAttackGoal(private val manglerfish: ManglerfishEntity) :
        MeleeAttackGoal(manglerfish, 0.7, false) {
        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.cooldown <= 0) {
                resetCooldown()
                manglerfish.swingHand(Hand.MAIN_HAND)
                manglerfish.tryAttack(target)
            }
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (manglerfish.width * 2.0 + entity.width)
        }

        override fun start() {
            super.start()
            manglerfish.isSprinting = true
            manglerfish.handSwinging = false
            manglerfish.handSwingTicks = 0
        }

        override fun stop() {
            val livingEntity = manglerfish.target
            if (!EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(livingEntity)) {
                manglerfish.target = null
            }

            manglerfish.isSprinting = false
            manglerfish.isAttacking = false
            manglerfish.navigation.stop()
        }

        override fun shouldRunEveryTick(): Boolean {
            return true
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