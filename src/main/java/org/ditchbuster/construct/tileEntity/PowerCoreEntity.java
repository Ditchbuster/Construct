/*
 * Code adapted from microjunks tutorials
 * 
 * http://www.minecraftforum.net/topic/1924178-forge-164-micros-furnace-tutorials-will-update-all-parts-to-164/
 */



package org.ditchbuster.construct.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class PowerCoreEntity extends TileEntity implements IInventory
{

	private ItemStack furnaceItemStacks[];
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime1;
    public int furnaceCookTime2;
    public int ReduceAmount;
    private String customName;
    public ItemStack currentsmeltingitem;
    public ItemStack currentItemBurnTime1;
	
	public PowerCoreEntity(){
		furnaceItemStacks = new ItemStack[6];
        furnaceBurnTime = 0;
        currentItemBurnTime = 0;
        furnaceCookTime1 = 0;
        furnaceCookTime2 = 0;
        ReduceAmount = 1;
	}
	 public void writeToNBT(NBTTagCompound nbttagcompound)
     {
             super.writeToNBT(nbttagcompound);
             nbttagcompound.setShort("BurnTime", (short)furnaceBurnTime);
             nbttagcompound.setShort("CookTime1", (short)furnaceCookTime1);
             nbttagcompound.setShort("CookTime2", (short)furnaceCookTime2);
             NBTTagList nbttaglist = new NBTTagList();
             for(int i = 0; i < furnaceItemStacks.length; i++)
             {
                     if(furnaceItemStacks[i] != null)
                     {
                             NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                             nbttagcompound1.setByte("Slot", (byte)i);
                             furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                             nbttaglist.appendTag(nbttagcompound1);
                     }
             }
             nbttagcompound.setTag("Items", nbttaglist);
     }
	 public void readFromNBT(NBTTagCompound nbttagcompound)
     {
             super.readFromNBT(nbttagcompound);
             NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
             furnaceItemStacks = new ItemStack[getSizeInventory()];
             for(int i = 0; i < nbttaglist.tagCount(); i++)
             {
                     NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
                     byte byte0 = nbttagcompound1.getByte("Slot");
                     if(byte0 >= 0 && byte0 < furnaceItemStacks.length)
                     {
                             furnaceItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
                     }
             }
             furnaceBurnTime = nbttagcompound.getShort("BurnTime");
             furnaceCookTime1 = nbttagcompound.getShort("CookTime1");
             furnaceCookTime2 = nbttagcompound.getShort("CookTime2");
             currentItemBurnTime = getItemBurnTime(furnaceItemStacks[1]);
             currentsmeltingitem = furnaceItemStacks[3];
     }
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public int getSizeInventory() {
		return furnaceItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return furnaceItemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(furnaceItemStacks[i] != null)
        {
                if(furnaceItemStacks[i].stackSize <= j)
                {
                        ItemStack itemstack = furnaceItemStacks[i];
                        furnaceItemStacks[i] = null;
                        return itemstack;
                }
                ItemStack itemstack1 = furnaceItemStacks[i].splitStack(j);
                if(furnaceItemStacks[i].stackSize == 0)
                {
                        furnaceItemStacks[i] = null;
                }
                return itemstack1;
        } else
        {
                return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		furnaceItemStacks[i] = itemstack;
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
                itemstack.stackSize = getInventoryStackLimit();
        }

	}

	@Override
	public String getInventoryName() {
		 return "Power Core";
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		// TODO Auto-generated method stub
		return false;
	}

}
