package org.ditchbuster.construct.core.proxy;

import org.ditchbuster.construct.tileEntity.PowerCoreEntity;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(PowerCoreEntity.class, "PowerCoreTileEntity");
    }
}
