/*
 * Code adapted from microjunks tutorials
 * 
 * http://www.minecraftforum.net/topic/1924178-forge-164-micros-furnace-tutorials-will-update-all-parts-to-164/
 */

package org.ditchbuster.construct.tileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

import org.ditchbuster.construct.blocks.PowerCore;

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

	public PowerCoreEntity() {
		furnaceItemStacks = new ItemStack[6];
		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime1 = 0;
		furnaceCookTime2 = 0;
		ReduceAmount = 1;
	}

	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setShort("BurnTime", (short) furnaceBurnTime);
		nbttagcompound.setShort("CookTime1", (short) furnaceCookTime1);
		nbttagcompound.setShort("CookTime2", (short) furnaceCookTime2);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < furnaceItemStacks.length; i++) {
			if (furnaceItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				furnaceItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		nbttagcompound.setTag("Items", nbttaglist);
	}

	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		NBTTagList nbttaglist = nbttagcompound.getTagList("Items", Constants.NBT.TAG_COMPOUND );
		furnaceItemStacks = new ItemStack[getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.getCompoundTagAt(i);
			byte byte0 = nbttagcompound1.getByte("Slot");
			if (byte0 >= 0 && byte0 < furnaceItemStacks.length) {
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
		if (furnaceItemStacks[i] != null) {
			if (furnaceItemStacks[i].stackSize <= j) {
				ItemStack itemstack = furnaceItemStacks[i];
				furnaceItemStacks[i] = null;
				return itemstack;
			}
			ItemStack itemstack1 = furnaceItemStacks[i].splitStack(j);
			if (furnaceItemStacks[i].stackSize == 0) {
				furnaceItemStacks[i] = null;
			}
			return itemstack1;
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.furnaceItemStacks[par1] != null) {
			ItemStack var2 = this.furnaceItemStacks[par1];
			this.furnaceItemStacks[par1] = null;
			return var2;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		furnaceItemStacks[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}

	}

	@Override
	public String getInventoryName() {
		return "Power Core";
	}

	@Override
	public boolean hasCustomInventoryName() {

		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double) this.xCoord + 0.5D,
				(double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;

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
		
		return false;
	}

	public int getCookProgressScaled(int i, int j) {
		if (j == 0) {
			return (furnaceCookTime1 * i) / 200;
		} else {
			return (furnaceCookTime2 * i) / 200;
		}
	}

	public int getBurnTimeRemainingScaled(int i) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = 200;
		}
		return (furnaceBurnTime * i) / currentItemBurnTime;
	}

	public boolean isBurning() {
		return furnaceBurnTime > 0;
	}

	public void updateEntity() {
		boolean flag = furnaceBurnTime > 0;
		boolean flag1 = false;
		if (furnaceBurnTime > 0) {
			furnaceBurnTime--;
		}
		if (!worldObj.isRemote) {
			if (furnaceBurnTime == 0 && (canSmelt(0, 2) || canSmelt(3, 4))) {
				int i = getItemBurnTime(furnaceItemStacks[1]);
				if (canSmelt(0, 2) || canSmelt(3, 4)) {
					i = (int) ((float) i * 0.79F);
				}
				currentItemBurnTime = furnaceBurnTime = i;
				currentsmeltingitem = furnaceItemStacks[5] = furnaceItemStacks[1];
				if (i > 0) {
					flag1 = true;
					if (furnaceItemStacks[1] != null) {
						furnaceItemStacks[1].stackSize--;
						if (furnaceItemStacks[1].stackSize == 0) {
							furnaceItemStacks[1] = null;
						}
					}
				}
			}
			if (isBurning()) {
				if (canSmelt(0, 2)) {
					furnaceCookTime1++;
					if (furnaceCookTime1 == 200) {
						furnaceCookTime1 = 0;
						smeltItem(0, 2);
						flag1 = true;
					}
				} else {
					furnaceCookTime1 = 0;
				}
				if (canSmelt(3, 4)) {
					furnaceCookTime2++;
					if (furnaceCookTime2 == 200) {
						furnaceCookTime2 = 0;
						smeltItem(3, 4);
						flag1 = true;
					}
				} else {
					furnaceCookTime2 = 0;
				}
			} else {
				furnaceCookTime1 = 0;
				furnaceCookTime2 = 0;
			}
			if (flag != (furnaceBurnTime > 0)) {
				flag1 = true;
				// PowerCore.updateFurnaceBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}
		if (flag1) {
			this.markDirty();
		}
	}

	private boolean canSmelt(int i, int j) {
		if (furnaceItemStacks[i] == null) {
			return false;
		}
		if (furnaceBurnTime == 0) {
			currentsmeltingitem = furnaceItemStacks[5] = null;
		}
		ItemStack itemstack = checkLargeFurnaceStack(furnaceItemStacks[i], furnaceItemStacks[1], this);
		if (itemstack == null) {
			return false;
		}
		if (furnaceItemStacks[j] == null) {
			return true;
		}
		if (!furnaceItemStacks[j].isItemEqual(itemstack)) {
			return false;
		}
		if (furnaceItemStacks[j].stackSize + itemstack.stackSize <= getInventoryStackLimit()
				&& furnaceItemStacks[j].stackSize + itemstack.stackSize <= furnaceItemStacks[j].getMaxStackSize()) {
			return true;
		} else {
			return furnaceItemStacks[j].stackSize + itemstack.stackSize <= itemstack.getMaxStackSize();
		}
	}

	private ItemStack checkLargeFurnaceStack(ItemStack itemStack, ItemStack itemStack2, PowerCoreEntity powerCoreEntity) {
		// TODO random placehold for smelting items
		return new ItemStack(itemStack.getItem(),2);
	}

	private void smeltItem(int i, int j) {
		if (!canSmelt(i, j)) {
			return;
		}
		ItemStack itemstack = checkLargeFurnaceStack(furnaceItemStacks[i], furnaceItemStacks[1], this);
		if (furnaceItemStacks[j] == null) {
			furnaceItemStacks[j] = itemstack.copy();
		} else if (furnaceItemStacks[j].getItem() == itemstack.getItem()) {
			furnaceItemStacks[j].stackSize += itemstack.stackSize;
		}
		if (furnaceItemStacks[i].stackSize - ReduceAmount <= 0) {
			furnaceItemStacks[i] = null;
		} else {
			furnaceItemStacks[i].stackSize -= ReduceAmount;
		}
	}

	public static int getItemBurnTime(ItemStack itemstack) {
		/*
		 * int i = FurnaceStack.checkFuel(itemstack); if (i != -1) { return i; } if (itemstack == null) { return 0; } int j = itemstack.getItem().itemID; if (j
		 * < 256 && Block.blocksList[j].blockMaterial == Material.wood) { return 300; } if (j == Item.stick.itemID) { return 100; } if (j == Item.coal.itemID) {
		 * return 1600; } return j != Item.bucketLava.itemID ? 0 : 20000;
		 */
		return 300;
	}

	/**
	 * Return true if item is a fuel source (getItemBurnTime() > 0).
	 */
	public static boolean isItemFuel(ItemStack par0ItemStack) {
		return getItemBurnTime(par0ItemStack) > 0;
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {
		if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		} else {
			return entityplayer.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;
		}
	}

	public void setDisplayName(String string) {
		customName=string;
	}

}
