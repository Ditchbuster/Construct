/*
 * Mouse tutorials
 */
package org.ditchbuster.construct.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import org.ditchbuster.construct.inventory.CoreSlot;
import org.ditchbuster.construct.tileEntity.PowerCoreEntity;

public class PowerCoreContainer extends Container
{
	private PowerCoreEntity tile;

	public PowerCoreContainer(InventoryPlayer inventory, PowerCoreEntity tileEntityTestContainer) {
		tile = tileEntityTestContainer;
		bindPlayerInventory(inventory);
	}

	private void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		int id = 0; // ID's for player inventory
		int id2 = 0;

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, id, i * 18 + 8, 166)); // Adds player hotbar
			id++;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, id, j * 18 + 8, i * 18 + 108)); // Adds player inventory
				id++;
			}

		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				addSlotToContainer(new CoreSlot(tile, id2, i * 18 + 62, j * 18 - 4)); // Adds custon slots
				id2++;
			}
		}
		addSlotToContainer(new CoreSlot(tile, id2, 81, 73)); // Adds custom output
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotIndex) {
		return null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return true;
	}

}
