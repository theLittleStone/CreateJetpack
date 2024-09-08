package com.possible_triangle.create_jetpack.block

import com.possible_triangle.create_jetpack.Content
import com.simibubi.create.content.equipment.armor.BacktankBlock
import com.simibubi.create.content.equipment.armor.BacktankBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

class JetpackBlock(private val item: ItemLike, properties: Properties) : BacktankBlock(properties) {

    override fun getCloneItemStack(
        world: BlockGetter,
        pos: BlockPos,
        state: BlockState
    ): ItemStack {

        val tile = getBlockEntityOptional(world, pos)

        val air = tile.map { it.getAirLevel() }.orElse(0) as Int

        val forgeCapsTag = tile.map { it.getForgeCapsTag() }.orElse(null)
        val vanillaTag = tile.map {it.getVanillaTag()}.orElse(CompoundTag())

        val item = ItemStack(item, 1, forgeCapsTag)
        vanillaTag.putInt("Air", air)

        item.setTag(vanillaTag)

        return item
    }

    override fun getBlockEntityType(): BlockEntityType<out BacktankBlockEntity> {
        return Content.JETPACK_BLOCK_ENTITY.get()
    }

}