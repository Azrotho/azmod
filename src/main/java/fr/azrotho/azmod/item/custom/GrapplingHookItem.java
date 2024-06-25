package fr.azrotho.azmod.item.custom;

import fr.azrotho.azmod.entity.custom.HookProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
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

        //if (!world.isClient) {
            HookProjectileEntity hook = new HookProjectileEntity(player, world);
            hook.setItem(itemStack);
            hook.setCustomName(Text.of("hook"));
            hook.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 4.5F, 1.0F);
            world.spawnEntity(hook);
            //attachEntityToPlayer(hook, player);
        //}

        itemStack.damage(1, player, (p) -> p.sendToolBreakStatus(hand));

        return TypedActionResult.success(player.getStackInHand(hand));
    }

    public static void attachEntityToPlayer(HookProjectileEntity entity, PlayerEntity player) {
        World world = entity.getWorld();

        // Créer une canne à pêche factice
        FishingBobberEntity fishingBobber = new FishingBobberEntity(EntityType.FISHING_BOBBER, world);
        world.spawnEntity(fishingBobber);

        // Lier l'entité à la canne à pêche
        fishingBobber.startRiding(entity);

        // Lier la canne à pêche au joueur
        fishingBobber.setOwner(player);
    }
}
