package fr.azrotho.azmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DiamondMagnetItem extends Item {

    public DiamondMagnetItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        int diamonds = 0;
        for(PlayerEntity p : world.getPlayers()) {
            if(p != player) {
                if(distance(player, p) < 10) {
                    for(int i = 0; i < p.getInventory().size(); i++) {
                        if(p.getInventory().getStack(i).getItem().toString().contains("diamond")) {
                            diamonds += p.getInventory().getStack(i).getCount();
                            p.getInventory().removeStack(i);
                        }
                    }
                }
            }
        }
        player.getInventory().insertStack(new ItemStack(Items.DIAMOND, diamonds));
        player.getStackInHand(hand).damage(1, player, (entity) -> entity.sendToolBreakStatus(hand));
        return TypedActionResult.success(player.getStackInHand(hand));
    }


    public Double distance(PlayerEntity p1, PlayerEntity p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2) + Math.pow(p1.getZ() - p2.getZ(), 2));
    }

    
}
