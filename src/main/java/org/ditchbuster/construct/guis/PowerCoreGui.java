/*
 * Following Mouse tutorials
 * http://mousetutorial.co.nf/
 */

package org.ditchbuster.construct.guis;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PowerCoreGui extends GuiScreen
{

	/**
     * The gui file needs to be 256x256.
     * The GUI ITSELF can have any size you want
     * Define them here
     * These are the GUI sizes!
     */
    int xSize = 176;
    int ySize = 214;
    private static final ResourceLocation backgroundimage = new ResourceLocation("construct:textures/gui/mygui.png");
 
	
	
	public PowerCoreGui() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Override
    public void drawScreen(int par1, int par2, float par3)
    {
        //Bind Texture
        this.mc.getTextureManager().bindTexture(backgroundimage);
        // set the x for the texture, Total width - textureSize / 2
        par2 = (this.width - xSize) / 2;
        // set the y for the texture, Total height - textureSize - 30 (up) / 2,
        int j = (this.height - ySize - 30) / 2;
        // draw the texture
        drawTexturedModalRect(par2, j, 0, 0, xSize,  ySize);
    }
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }
	
	
   
}