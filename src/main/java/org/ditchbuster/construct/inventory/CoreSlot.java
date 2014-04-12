package org.ditchbuster.construct.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CoreSlot extends Slot
{
    public CoreSlot(IInventory inventory, int slotIndex, int x, int y)
    {
        super(inventory, slotIndex, x ,y);
    }
 
    /*
    All Items
     */
    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return true;
    }
}