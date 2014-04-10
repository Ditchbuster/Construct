/*
 * Code adapted from microjunks tutorials
 * 
 * http://www.minecraftforum.net/topic/1924178-forge-164-micros-furnace-tutorials-will-update-all-parts-to-164/
 */

package org.ditchbuster.construct.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import org.ditchbuster.construct.tileEntity.PowerCoreEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PowerCoreContainer extends Container
{

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
