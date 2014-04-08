package org.ditchbuster.construct.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import org.ditchbuster.construct.containers.PowerCoreContainer;
import org.ditchbuster.construct.tileEntity.PowerCoreEntity;


public class PowerCore extends Block
{
	public PowerCoreEntity powerCoreEnity;
	public PowerCoreContainer powerCoreContainer;
	
	public PowerCore(Material material) {
		super(material);
		powerCoreContainer =new PowerCoreContainer();
		
	}
}
