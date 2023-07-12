package com.feldsher.feldshergreg.item;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.feldsher.feldshergreg.Config;
import com.feldsher.feldshergreg.Tags;

// Class must be a singleton
public abstract class BaseMetaItem extends Item {
    private IIcon[] icons;
    private int count = 0;

    // Set unlocalizedName
    public abstract String name();

    public void registerItem() {
        super.setHasSubtypes(true);
        super.setUnlocalizedName(name());
        super.setMaxStackSize(64);
        GameRegistry.registerItem(this, name());
    }

    public ItemStack getItemStack(int meta) {
        return new ItemStack(this, 1, meta);
    }

    public ItemStack newItem() {
        return getItemStack(count++);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        icons = new IIcon[count];
        for (int i = 0; i < count; i++) {
            icons[i] = iconRegister.registerIcon(Tags.MODID + ":metaitem." + name() + "/" + i);
        }
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return icons[meta];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < count; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + stack.getItemDamage();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        super.addInformation(stack, player, list, b);
        list.add(StatCollector.translateToLocal(super.getUnlocalizedName() + "." + stack.getItemDamage() + ".tooltip"));
        list.add(Config.copyright);
    }
}