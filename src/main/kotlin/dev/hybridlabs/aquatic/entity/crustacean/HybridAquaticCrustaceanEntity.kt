package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.fish.ray.HybridAquaticRayEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.minecraft.block.Blocks
import net.minecraft.entity.*
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.TagKey
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.*
import net.minecraft.world.biome.Biome
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("DEPRECATION", "LeakingThis", "UNUSED_PARAMETER")
open class HybridAquaticCrustaceanEntity(
    type: EntityType<out HybridAquaticCrustaceanEntity>,
    world: World,
    private val variants: Map<String, CrustaceanVariant> = mutableMapOf(),
    open val canDance: Boolean,
    open val assumeDefault: Boolean = false,
    open val collisionRules: List<HybridAquaticFishEntity.VariantCollisionRules> = listOf(),
) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var landNavigation: EntityNavigation = createNavigation(world)
    private var songSource: BlockPos? = null
    private var songPlaying: Boolean = false
    private var fromFishingNet = false

    var size: Int
        get() = dataTracker.get(CRUSTACEAN_SIZE)
        set(size) {
            dataTracker.set(CRUSTACEAN_SIZE, size)
        }

private var attemptAttack: Boolean
    get() = dataTracker.get(ATTEMPT_ATTACK)
    set(attemptAttack) {
        dataTracker.set(ATTEMPT_ATTACK, attemptAttack)
    }

    private var variantData: NbtCompound
        get() = dataTracker.get(VARIANT_DATA)
        set(value) {
            dataTracker.set(VARIANT_DATA, value)
        }

    private var variantKey: String
        get() = dataTracker.get(VARIANT).ifBlank {
            if (!assumeDefault && variants.isNotEmpty()) {
                variants.isNotEmpty()
            }
            dataTracker.get(VARIANT)
        }
        private set(value) {
            dataTracker.set(VARIANT, value)
        }

    @Suppress("UNUSED_PARAMETER")
    var variant: CrustaceanVariant?
        get() = variants[variantKey]
        private set(value) {}

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(CRUSTACEAN_SIZE, 0)
        dataTracker.startTracking(ATTEMPT_ATTACK, false)
        dataTracker.startTracking(VARIANT, "")
        dataTracker.startTracking(VARIANT_DATA, NbtCompound())
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, TemptGoal(this, 0.5, Ingredient.fromTag(HybridAquaticItemTags.CRUSTACEAN_TEMPT_ITEMS), true))
        goalSelector.add(2, WanderAroundGoal(this, 0.4))
        goalSelector.add(2, WanderAroundFarGoal(this, 0.4))
        goalSelector.add(5, LookAroundGoal(this))
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.size = this.random.nextBetween(getMinSize(),getMaxSize())

        if (variants.isNotEmpty()) {
            if (spawnReason == SpawnReason.SPAWN_EGG) {
                variantKey = variants.keys.elementAt(random.nextBetween(0, variants.size - 1))
            } else {
                // Handle collisions
                val validKeys = variants.filter { it.value.spawnCondition(world, spawnReason, blockPos, random) }.map { it.key }

                if (validKeys.isEmpty()) {
                    variantKey = variants.keys.random()
                } else if (collisionRules.isNotEmpty()) {
                    for (rule in collisionRules) {
                        val variantSet = rule.variants.toSet()
                        if ((rule.exclusionStatus == HybridAquaticFishEntity.VariantCollisionRules.ExclusionStatus.EXCLUSIVE && validKeys.toSet() == variantSet) ||
                            (rule.exclusionStatus == HybridAquaticFishEntity.VariantCollisionRules.ExclusionStatus.INCLUSIVE && validKeys.containsAll(variantSet))) {
                            variantKey = rule.collisionHandler(validKeys.toSet(), random, world)
                            break
                        }
                    }
                } else {
                    // Default to a priority based system
                    val validityFilter = variants.filter { validKeys.contains(it.key) }
                    variantKey = if (validityFilter.isNotEmpty()) {
                        val maxPriority = validityFilter.values.maxOf { it.priority }
                        val filteredMap = validityFilter.filter { it.value.priority == maxPriority }
                        if (filteredMap.isNotEmpty()) {
                            filteredMap.keys.random()
                        } else {
                            validKeys.random()
                        }
                    } else {
                        validKeys.random()
                    }
                }
            }
        }

        this.size = this.random.nextBetween(getMinSize(), getMaxSize())
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    // region movement
   init {
        moveControl = MoveControl(this)
        navigation = this.landNavigation
        stepHeight = 1.0F
    }

    override fun getPathfindingFavor(pos: BlockPos?, world: WorldView?): Float {
        return 0.0f
    }

    override fun hasNoDrag(): Boolean {
        return false
    }

    override fun shouldSwimInFluids(): Boolean {
        return false
    }
    // end region

    override fun setNearbySongPlaying(songPosition: BlockPos?, playing: Boolean) {
        songSource = songPosition
        songPlaying = playing
        super.setNearbySongPlaying(songPosition, playing)
    }


    override fun getGroup(): EntityGroup? {
        return EntityGroup.ARTHROPOD
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putString(VARIANT_KEY, variantKey)
        nbt.put(VARIANT_DATA_KEY, variantData)
        nbt.putInt(CRUSTACEAN_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        variantKey = nbt.getString(VARIANT_KEY)
        variantData = nbt.getCompound(VARIANT_DATA_KEY)
        size = nbt.getInt(CRUSTACEAN_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun isPushedByFluids(): Boolean {
        return false
    }

    // region sounds

    override fun calculateNextStepSoundDistance(): Float {
        return this.distanceTraveled + 0.5f
    }

    override fun getMinAmbientSoundDelay(): Int {
        return 200
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_TURTLE_EGG_CRACK
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_TURTLE_EGG_BREAK
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_COD_AMBIENT
    }

    // endregion

    // region water breathing
    override fun canBreatheInWater(): Boolean {
        return true
    }

    override fun tickWaterBreathingAir(air: Int) {}

    // endregion

    override fun dropLoot(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.attacker
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticRayEntity && attacker !is HybridAquaticCephalopodEntity) {
            super.dropLoot(source, causedByPlayer)
        }
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !hasCustomName() && !fromFishingNet
    }


    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "controller",
                0,
                ::predicate
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (event.isMoving) {
            event.controller.setAnimation(WALK_ANIMATION)
        } else {
            event.controller.setAnimation(IDLE_ANIMATION)
        }
        if (canDance && songPlaying)
            event.controller.setAnimation(DANCE_ANIMATION)
        return PlayState.CONTINUE
    }


    internal class AttackGoal(private val crustacean: HybridAquaticCrustaceanEntity) : MeleeAttackGoal(crustacean, 1.0,true) {
        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.isCooledDown) {
                resetCooldown()
                mob.tryAttack(target)
                crustacean.isSprinting = true
                crustacean.attemptAttack = true
            }
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (1.25f + entity.width).toDouble()
        }

        override fun start() {
            super.start()
            crustacean.attemptAttack = false
        }

        override fun stop() {
            super.stop()
            crustacean.attemptAttack = false
        }
    }

    companion object {
        val CRUSTACEAN_SIZE: TrackedData<Int> = DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val ATTEMPT_ATTACK: TrackedData<Boolean> = DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        val VARIANT: TrackedData<String> = DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.STRING)
        var VARIANT_DATA: TrackedData<NbtCompound> = DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.NBT_COMPOUND)

        val DANCE_ANIMATION: RawAnimation = RawAnimation.begin().then("dance", Animation.LoopType.LOOP)
        val WALK_ANIMATION: RawAnimation = RawAnimation.begin().then("walk", Animation.LoopType.LOOP)
        val IDLE_ANIMATION: RawAnimation = RawAnimation.begin().then("idle", Animation.LoopType.LOOP)
        val HIDING_ANIMATION: RawAnimation = RawAnimation.begin().then("hide", Animation.LoopType.LOOP)

        fun canSpawn(
            type: EntityType<out WaterCreatureEntity?>?,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            val topY = world.seaLevel + 6
            val bottomY = world.seaLevel - 48

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.down()).isIn(HybridAquaticBlockTags.CRABS_SPAWN_ON) &&
                    (world.isWater(pos) || world.isAir(pos))
        }

        fun canUndergroundSpawn(
            type: EntityType<out WaterCreatureEntity?>?,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            val topY = world.seaLevel + 6
            val bottomY = world.seaLevel - 48

            return pos.y in bottomY..topY &&
                    world.getBaseLightLevel(pos, 0) == 0 &&
                    world.getBlockState(pos).isOf(Blocks.WATER) &&
                    world.getBlockState(pos.down()).isIn(HybridAquaticBlockTags.CRABS_SPAWN_ON)
        }

        fun getScaleAdjustment(crustacean : HybridAquaticCrustaceanEntity, adjustment : Float): Float {
            return 1.0f + (crustacean.size * adjustment)
        }

        const val VARIANT_KEY = "Variant"
        const val VARIANT_DATA_KEY = "VariantData"
        const val CRUSTACEAN_SIZE_KEY = "CrustaceanSize"
    }

    @Suppress("UNUSED")
    data class CrustaceanVariant(
        val variantName : String,
        val spawnCondition: (WorldAccess, SpawnReason, BlockPos, Random ) -> Boolean,
        val ignore: List<Ignore> = emptyList(),
        val priority: Int = 0,
        var providedVariant: (World, BlockPos, Random, HybridAquaticCrustaceanEntity) -> String = { _, _, _, _ ->
            variantName
        }
    ) {

        fun getProvidedVariant(crustacean: HybridAquaticCrustaceanEntity) : String {
            return providedVariant(crustacean.world, crustacean.blockPos, crustacean.random, crustacean)
        }

        companion object {
            /**
             * Creates a biome variant of a fish
             */
            fun biomeVariant(variantName: String, biomes : TagKey<Biome>, ignore : List<Ignore> = emptyList()): CrustaceanVariant {
                return CrustaceanVariant(variantName, { world, _, pos, _ ->
                    world.getBiome(pos).isIn(biomes)
                }, ignore)
            }
        }

        enum class Ignore {
            TEXTURE,
            MODEL,
            ANIMATION
        }
    }

    @Suppress("UNUSED")
    data class VariantCollisionRules(val variants : Set<String>, val collisionHandler: (Set<String>, Random, ServerWorldAccess) -> String, val exclusionStatus: ExclusionStatus = ExclusionStatus.INCLUSIVE) {

        enum class ExclusionStatus {
            INCLUSIVE,
            EXCLUSIVE
        }

        fun equalDistribution(variants: Set<String>, status : ExclusionStatus = ExclusionStatus.INCLUSIVE) : VariantCollisionRules {
            return VariantCollisionRules(variants, { possibleVariants, _, _ ->
                possibleVariants.random()
            }, status)
        }

        fun weightedDistribution(weights: Set<Pair<String, Double>>, status: ExclusionStatus = ExclusionStatus.EXCLUSIVE) : VariantCollisionRules {
            return VariantCollisionRules(weights.map { pair -> pair.first }.toSet(), { _, random, _ ->
                // sum up weights
                val weightTotal = weights.sumOf { pair -> pair.second }
                val randomVal = random.nextFloat() * weightTotal
                var accumulatedWeight = 0.0
                var result = ""

                for (pair in weights) {
                    accumulatedWeight += pair.second
                    if (randomVal < accumulatedWeight) {
                        result = pair.first
                        break
                    }
                }

                result
            }, status)
        }
    }
}