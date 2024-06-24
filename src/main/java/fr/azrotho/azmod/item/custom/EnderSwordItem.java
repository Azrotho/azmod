package fr.azrotho.azmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;

public class EnderSwordItem extends SwordItem {

    public EnderSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int destX = (int) Math.floor(randomizeLocation(target.getX(), 10));
        int destY = (int) Math.floor(randomizeLocation(target.getY(), 10));
        int destZ = (int) Math.floor(randomizeLocation(target.getZ(), 10));
        
        while (!target.getWorld().getBlockState(new BlockPos(destX, destY, destZ)).isAir()) {
            destX = (int) Math.floor(randomizeLocation(target.getX(), 10));
            destY = (int) Math.floor(randomizeLocation(target.getY(), 10));
            destZ = (int) Math.floor(randomizeLocation(target.getZ(), 10));
        }
        
        target.teleport(destX, destY, destZ);
        return false;
    };

    public Double randomizeLocation(Double location, int max) {
        int toadd = (int) Math.floor((0.5 - Math.random()) * max * 2);
        return location + toadd;
    }
}
