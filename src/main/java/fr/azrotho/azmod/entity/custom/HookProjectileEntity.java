package fr.azrotho.azmod.entity.custom;

import fr.azrotho.azmod.entity.ModEntities;
import fr.azrotho.azmod.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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

        if(this.getOwner() == null) {
            this.discard();
            return;
        }

        if(this.getOwner().distanceTo(this) < 3) {
            this.discard();
            return;
        }
        PlayerEntity playerEntity = (PlayerEntity) this.getOwner();
        if(playerEntity.isSneaking()) {
            this.discard();
            return;
        }
        

        tick--;

        double dx = this.getX() - this.getOwner().getX();
        double dy = this.getY() - this.getOwner().getY() + 1;
        double dz = this.getZ() - this.getOwner().getZ();
        double xzDist = (double) Math.sqrt((float) (dx * dx + dz * dz));

        double theta = Math.atan2(dy, xzDist);
        double phi = Math.atan2(dz, dx);

        double xzAmount = Math.cos(theta);
        Vec3d movement = new Vec3d(xzAmount * Math.cos(phi), Math.sin(theta), xzAmount * Math.sin(phi));
        movement = movement.normalize();

        Vec3d playerVelocity = this.getOwner().getVelocity();
        Vec3d velocity = movement.subtract(movement.subtract(playerVelocity).multiply(0.5));
        this.getOwner().setVelocity(velocity);
        this.getOwner().velocityModified = true;
        this.getOwner().fallDistance = 0;


        if(tick == 0) {
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if(!this.getWorld().isClient() && !this.getCustomName().equals(Text.of("hited"))) {
            //this.getOwner().teleport(blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY() + 1, blockHitResult.getBlockPos().getZ());
            this.setVelocity(0, 0, 0);
            this.setNoGravity(true);
            this.setCustomName(Text.of("hited"));
            this.tick = 200;
        }
        //this.discard();
        super.onBlockHit(blockHitResult);
    }
}