package fr.azrotho.azmod.entity.custom;

import fr.azrotho.azmod.entity.ModEntities;
import fr.azrotho.azmod.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;

public class HookProjectileEntity extends ThrownItemEntity {

    int tick = -1;

    public HookProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public HookProjectileEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.HOOK_PROJECTILE, livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.GRAPPLING_HOOK;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        if(tick == -1) {
            return;
        }

        tick--;

        // Propulser le joueur vers la tête du grappin
        Vec3d velocityToPlayer = this.getOwner().getPos().subtract(this.getPos()).normalize().multiply(0.5);
        this.getOwner().setVelocity(velocityToPlayer.multiply(100));
        if(tick == 0) {
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if(!this.getWorld().isClient() && !this.getCustomName().equals("hited")) {
            //this.getOwner().teleport(blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY() + 1, blockHitResult.getBlockPos().getZ());
            this.setVelocity(0, 0, 0);
            this.setNoGravity(true);
            this.setCustomName(Text.of("hited"));
            this.tick = 100;
        }
        //this.discard();
        super.onBlockHit(blockHitResult);
    }
}