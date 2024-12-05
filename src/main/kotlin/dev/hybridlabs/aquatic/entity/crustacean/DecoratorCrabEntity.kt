package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import kotlin.random.Random

class DecoratorCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: World) :
    HybridAquaticCrustaceanEntity(
        entityType, world, true, false, emptyMap()) {

    private var coralTimer = -1
    var randomValue = Random.nextInt(0, 10)

    public override fun getLootTableId(): Identifier {
        return Identifier("hybrid-aquatic", "entities/decorator_crab")
    }

    override fun interactMob(player: PlayerEntity, hand: Hand?): ActionResult {
        val itemStack = player.getStackInHand(hand)
        if (!itemStack.isEmpty && itemStack.isOf(Items.SHEARS)) {
            if (randomValue != 9) {
                if (!world.isClient) {
                    this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0f, 1.0f)
                    this.emitGameEvent(GameEvent.SHEAR, player)
                    itemStack.damage(1, player) { it.sendToolBreakStatus(hand) }
                    randomValue = 9
                    coralTimer = 3600
                    dropStack(ItemStack(HybridAquaticItems.CORAL_CHUNK))
                    return ActionResult.SUCCESS
                } else {
                    return ActionResult.CONSUME
                }
            } else {
                return ActionResult.PASS
            }
        } else {
            return super.interactMob(player, hand)
        }
    }

    override fun tick() {
        super.tick()

        if (coralTimer > 0) {
            coralTimer--
            if (coralTimer == 0) {
                randomValue = Random.nextInt(1, 9)
            }
        }
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}