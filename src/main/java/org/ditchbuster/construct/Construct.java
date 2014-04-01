package org.ditchbuster.construct;


import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Construct.MODID, version = Construct.VERSION)
public class Construct
{
	public static final String MODID = "construct";
	public static final String VERSION = "0.0";

	@EventHandler
	public void preinit(FMLPreInitializationEvent event){
		GameRegistry.addRecipe(new ItemStack(Blocks.obsidian), new Object[]{
	    	"AAA",
	    	"AAA",
	    	"AAA",
	    	'A', Items.cookie
	});
	}
	public void init(FMLInitializationEvent event) {
		// some example code
		//System.out.println("DIRT BLOCK >> " + Blocks.dirt.func_147439_a());
	}
}
