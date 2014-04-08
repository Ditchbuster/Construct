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
	private PowerCoreEntity pce;
	public int furnaceBurnTime = 0;
	public int currentItemBurnTime = 0;
	public int furnaceCookTime1 = 0;
	public int furnaceCookTime2 = 0;

	public PowerCoreContainer(InventoryPlayer p1invPlayer, PowerCoreEntity p2PCEntity) {
		this.pce = p2PCEntity;
		this.addSlotToContainer(new Slot(p2PCEntity, 0, 52, 15));
		this.addSlotToContainer(new Slot(p2PCEntity, 1, 52, 57));
		this.addSlotToContainer(new SlotFurnace(p1invPlayer.player, p2PCEntity, 2, 108, 15));
		this.addSlotToContainer(new Slot(p2PCEntity, 3, 52, 33));
		this.addSlotToContainer(new SlotFurnace(p1invPlayer.player, p2PCEntity, 4, 108, 33));

		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				this.addSlotToContainer(new Slot(p1invPlayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}
		}
		for (int j = 0; j < 9; j++) {
			this.addSlotToContainer(new Slot(p1invPlayer, j, 8 + j * 18, 142));
		}

	}

	public void addCraftingToCrafters(ICrafting par1ICrafting) {
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.pce.furnaceCookTime1);
		par1ICrafting.sendProgressBarUpdate(this, 1, this.pce.furnaceBurnTime);
		par1ICrafting.sendProgressBarUpdate(this, 2, this.pce.currentItemBurnTime);
		par1ICrafting.sendProgressBarUpdate(this, 3, this.pce.furnaceCookTime2);
	}

	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int var1 = 0; var1 < this.crafters.size(); ++var1) {
			ICrafting var2 = (ICrafting) this.crafters.get(var1);
			if (furnaceBurnTime != pce.furnaceBurnTime) {
				var2.sendProgressBarUpdate(this, 1, pce.furnaceBurnTime);
			}
			if (currentItemBurnTime != pce.currentItemBurnTime) {
				var2.sendProgressBarUpdate(this, 2, pce.currentItemBurnTime);
			}
			if (furnaceCookTime1 != pce.furnaceCookTime1) {
				var2.sendProgressBarUpdate(this, 0, pce.furnaceCookTime1);
			}
			if (furnaceCookTime2 != pce.furnaceCookTime2) {
				var2.sendProgressBarUpdate(this, 3, pce.furnaceCookTime2);
			}
		}
		this.furnaceCookTime1 = this.pce.furnaceCookTime1;
		this.furnaceCookTime2 = this.pce.furnaceCookTime2;
		this.furnaceBurnTime = this.pce.furnaceBurnTime;
		this.currentItemBurnTime = this.pce.currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			pce.furnaceCookTime1 = par2;
		}
		if (par1 == 1) {
			pce.furnaceBurnTime = par2;
		}
		if (par1 == 2) {
			pce.currentItemBurnTime = par2;
		}
		if (par1 == 3) {
			pce.furnaceCookTime2 = par2;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {

		return this.pce.isUseableByPlayer(par1EntityPlayer);
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack var3 = null;
		Slot var4 = (Slot) this.inventorySlots.get(par2);
		if (var4 != null && var4.getHasStack()) {
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			if (par2 == 2) {
				if (!this.mergeItemStack(var5, 3, 39, true)) {
					return null;
				}
				var4.onSlotChange(var5, var3);
			} else if (par2 != 1 && par2 != 0) {
				if (FurnaceRecipes.smelting().getSmeltingResult(var5) != null) {
					if (!this.mergeItemStack(var5, 0, 1, false)) {
						return null;
					}
				} else if (PowerCoreEntity.isItemFuel(var5)) {
					if (!this.mergeItemStack(var5, 1, 2, false)) {
						return null;
					}
				} else if (par2 >= 3 && par2 < 30) {
					if (!this.mergeItemStack(var5, 30, 39, false)) {
						return null;
					}
				} else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(var5, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(var5, 3, 39, false)) {
				return null;
			}
			if (var5.stackSize == 0) {
				var4.putStack((ItemStack) null);
			} else {
				var4.onSlotChanged();
			}
			if (var5.stackSize == var3.stackSize) {
				return null;
			}
			var4.onPickupFromSlot(par1EntityPlayer, var5);
		}
		return var3;
	}
}
