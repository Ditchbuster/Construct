/*
 * Following Mouse tutorials
 * http://mousetutorial.co.nf/
 */

package org.ditchbuster.construct.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.ditchbuster.construct.containers.PowerCoreContainer;
import org.ditchbuster.construct.guis.PowerCoreGui;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public void GUIHandler() {
	}

	/**
	 * Gets the server element. This means, do something server side, when this ID gets called.
	 */
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	/**
	 * Gets the client element. This means, do something client side, when this ID gets called.
	 */
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		System.out.println("Here is GUI Handler:"+ID);
		if (ID == 0)
			return new PowerCoreGui();
		return null;
	}
}
