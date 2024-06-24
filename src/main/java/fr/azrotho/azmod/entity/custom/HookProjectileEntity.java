package fr.azrotho.azmod.entity.custom;

import fr.azrotho.azmod.entity.ModEntities;
import fr.azrotho.azmod.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;

public class HookProjectileEntity extends ThrownItemEntity {

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
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if(!this.getWorld().isClient()) {
            this.getOwner().teleport(blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ());
            this.getWorld().sendEntityStatus(this, (byte) 3);
        }
        this.discard();
        super.onBlockHit(blockHitResult);
    }
}