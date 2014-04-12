package org.ditchbuster.construct.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.ditchbuster.construct.Construct;
import org.ditchbuster.construct.tileEntity.PowerCoreEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PowerCore extends BlockContainer
{

	public PowerCore(Material p_i45386_1_) {
		super(p_i45386_1_);
		
	}

	@Override
    public boolean hasTileEntity(int meta)
    {
        return true;
    }
	@Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new PowerCoreEntity();
    }
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par1, float par2, float par3, float par4)
    {
        entityPlayer.openGui(Construct.instance, 0, world, x ,y, z);
        return true;
    }
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(this.getUnlocalizedName());
    }
   
	
}