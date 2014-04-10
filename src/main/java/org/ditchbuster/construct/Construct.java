package org.ditchbuster.construct;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.ditchbuster.construct.blocks.PowerCore;
import org.ditchbuster.construct.core.handler.GuiHandler;
import org.ditchbuster.construct.core.proxy.CommonProxy;
import org.ditchbuster.construct.items.LinkChair;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Construct.MODID, version = Construct.VERSION)
public class Construct
{
	 /**
     * An instance of our @Mod
     */
    @Mod.Instance
    public static Construct instance;
    
	public static final String MODID = "construct";
	public static final String VERSION = "0.0";
	public static final String CLIENTPROXYLOCATION = "org.ditchbuster." + MODID + ".core.proxy.ClientProxy";
	public static final String COMMONPROXYLOCATION = "org.ditchbuster." + MODID + ".core.proxy.CommonProxy";
	
	@SidedProxy(clientSide = CLIENTPROXYLOCATION, serverSide = COMMONPROXYLOCATION)
	public static CommonProxy proxy;
	
	public static Item linkChair;
	public static Block powerCore; 
	
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event){
		proxy.registerTileEntities();
		
		linkChair = new LinkChair().setTextureName("construct:linkChair");
		GameRegistry.registerItem(linkChair,linkChair.getUnlocalizedName());
		
		powerCore = new PowerCore(Material.wood).setHardness(0.5F).setCreativeTab(CreativeTabs.tabBlock).setBlockName("powerCore").setBlockTextureName("construct:powerCore");
		//powerCore = new PowerCore(Material.ground);
		GameRegistry.registerBlock(powerCore, "powerCore");
		
		
		GameRegistry.addRecipe(new ItemStack(Blocks.obsidian), new Object[]{
	    	"AAA",
	    	"AAA",
	    	"AAA",
	    	'A', Items.cookie
	});
	}
	public void init(FMLInitializationEvent event) {
		 //Register our GUI Handler.
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}
}
