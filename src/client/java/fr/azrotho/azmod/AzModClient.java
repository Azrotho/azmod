package fr.azrotho.azmod;

import fr.azrotho.azmod.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class AzModClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.HOOK_PROJECTILE, FlyingItemEntityRenderer::new);
    }
    
}
