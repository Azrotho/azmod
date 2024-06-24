package fr.azrotho.azmod.item.custom;

import fr.azrotho.azmod.entity.custom.HookProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GrapplingHookItem extends Item {

    public GrapplingHookItem(Settings settings) {
        super(settings);
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.random.nextFloat() * 0.4F + 0.8F));

        if (!world.isClient) {
            HookProjectileEntity hook = new HookProjectileEntity(player, world);
            hook.setItem(itemStack);
            hook.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(hook);
        }

        itemStack.damage(1, player, (p) -> p.sendToolBreakStatus(hand));

        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
