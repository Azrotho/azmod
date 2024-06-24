package fr.azrotho.azmod.entity;

import fr.azrotho.azmod.AzMod;
import fr.azrotho.azmod.entity.custom.HookProjectileEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<HookProjectileEntity> HOOK_PROJECTILE = Registry.register(
        Registries.ENTITY_TYPE,
        new Identifier(AzMod.MODID, "hook_projectile"),
        FabricEntityTypeBuilder.<HookProjectileEntity>create(SpawnGroup.MISC, HookProjectileEntity::new)
            .dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());
}
