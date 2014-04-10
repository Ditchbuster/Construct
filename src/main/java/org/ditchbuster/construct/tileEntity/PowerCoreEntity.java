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

public class PowerCoreEntity extends TileEntity
{

	
	/**
     * A basic Tile Entity.
     * For demonstration purpose, ill change the worldtime every 5 seconds.
     */
    int tick;
    int worldTime = 1;
 
    /**
     * This method gets called every tick (tick = 1/20 sec)
     */
    @Override
    public void updateEntity()
    {
        //Only on the server side do
        if(!worldObj.isRemote)
        {
            tick++;
            if(tick == 100)
            {
                if(worldTime == 1)
                {
                    this.worldObj.setWorldTime(1000);
                    worldTime = 0;
                }
                else
                {
                    this.worldObj.setWorldTime(0);
                    worldTime = 1;
                }
                tick = 0;
            }
        }
    }
	

}
