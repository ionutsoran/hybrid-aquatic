package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.boss.BossBar
import net.minecraft.entity.boss.ServerBossBar
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageType
import net.minecraft.entity.damage.DamageTypes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.world.Difficulty
import net.minecraft.world.World
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationController.AnimationStateHandler
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class KarkinosEntity(entityType: EntityType<out HybridAquaticMinibossEntity>, world: World) :
    HybridAquaticMinibossEntity(entityType, world) {

    private var landNavigation: EntityNavigation = createNavigation(world)

    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        moveControl = MoveControl(this)
        navigation = this.landNavigation
        stepHeight = 2.0F
    }

    private var flipTimer: Int = 0
    private val flipDuration: Int = 60
    private var bossBar: ServerBossBar = ServerBossBar(displayName, BossBar.Color.RED, BossBar.Style.NOTCHED_20)

    var isFlipped: Boolean
        get() = dataTracker.get(FLIPPED)
        set(bool) = dataTracker.set(FLIPPED, bool)

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(FLIPPED, false)
    }

    override fun initGoals() {
        goalSelector.add(1, AttackGoal(this))
        goalSelector.add(1, LookAtEntityGoal(this, PlayerEntity::class.java, 12.0f))
        goalSelector.add(4, KarkinosWanderAroundGoal(this, 0.3))
        goalSelector.add(4, LookAroundGoal(this))
        targetSelector.add(1, RevengeGoal(this))
        targetSelector.add(2, ActiveTargetGoal(this, PlayerEntity::class.java, 10, true, true, null))
    }

    private fun beFlipped() {
        isFlipped = true
        flipTimer = flipDuration
    }

    override fun mobTick() {

        if (isFlipped) {
            flipTimer--

            if (flipTimer <= 0) {
                isFlipped = false
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.6
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)?.baseValue = 5.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 8.0
            } else {
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)?.baseValue = 0.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 0.0
            }
        }

        bossBar.percent = health / maxHealth

        super.mobTick()
    }

    override fun onStartedTrackingBy(player: ServerPlayerEntity) {
        super.onStartedTrackingBy(player)
        bossBar.addPlayer(player)
    }

    override fun onStoppedTrackingBy(player: ServerPlayerEntity) {
        super.onStoppedTrackingBy(player)
        bossBar.removePlayer(player)
    }

    override fun checkDespawn() {
        if (world.difficulty == Difficulty.PEACEFUL && this.isDisallowedInPeaceful) {
            discard()
        } else {
            despawnCounter = 0
        }
    }

    override fun isPushable(): Boolean =
        this.isFlipped

    override fun getMovementSpeed(): Float {
        return if (isFlipped) 0.0f else super.getMovementSpeed()
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        val dmgSourcesRegistry = damageSources.registry

        if (source.type == dmgSourcesRegistry.entryOf(DamageTypes.ARROW).value() as DamageType) return false
        else if (source.type == dmgSourcesRegistry.entryOf(DamageTypes.IN_WALL).value() as DamageType) return false

        val damaged = super.damage(source, amount)

        if (damaged && source.source is PlayerEntity && !isFlipped) {
            val player = source.source as PlayerEntity
            val heldItem = player.mainHandStack

            if (EnchantmentHelper.getLevel(Enchantments.BANE_OF_ARTHROPODS, heldItem) > 2 ||
                (EnchantmentHelper.getLevel(Enchantments.RIPTIDE, heldItem) > 0)
            ) {
                beFlipped()
            }
        }

        return damaged
    }

    override fun getGroup(): EntityGroup {
        return EntityGroup.AQUATIC
    }

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

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(this, "Walk/Run/Idle", 0,
                AnimationStateHandler { state: AnimationState<HybridAquaticMinibossEntity> ->
                    if (state.isMoving) {
                        return@AnimationStateHandler state.setAndContinue(if (this.isSprinting) DefaultAnimations.RUN else DefaultAnimations.WALK)
                    } else {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.IDLE)
                    }
                })
        )
        controllerRegistrar.add(
            AnimationController(this, "Attack", 5,
                AnimationStateHandler { state: AnimationState<HybridAquaticMinibossEntity> ->
                    if (this.handSwinging) return@AnimationStateHandler state.setAndContinue(DefaultAnimations.ATTACK_SWING)
                    state.controller.forceAnimationReset()
                    PlayState.STOP
                })
        )
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 8.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 50.0)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 5.0)
                .add(EntityAttributes.GENERIC_ARMOR, 8.0)
        }

        val FLIPPED: TrackedData<Boolean> = DataTracker.registerData(KarkinosEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
    }

    internal open class AttackGoal(private val karkinos: KarkinosEntity) : MeleeAttackGoal(karkinos, 0.6, false) {
        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.isCooledDown) {
                resetCooldown()
                mob.tryAttack(target)
                karkinos.attemptAttack = true
            }
            if (!this.isCooledDown)
                karkinos.handSwinging
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (4.0f + entity.width).toDouble()
        }

        override fun start() {
            super.start()
            karkinos.isSprinting = true
            karkinos.attemptAttack = false
        }

        override fun stop() {
            super.stop()
            karkinos.isSprinting = false
            karkinos.attemptAttack = false
        }

        override fun shouldContinue(): Boolean {
            return !karkinos.isFlipped && super.shouldContinue()
        }
    }

    class KarkinosWanderAroundGoal(private val karkinos: KarkinosEntity, speed: Double) : WanderAroundGoal(karkinos, speed) {
        override fun shouldContinue(): Boolean {
            return !karkinos.isFlipped && super.shouldContinue()
        }
    }
}