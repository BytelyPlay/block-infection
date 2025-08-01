package org.hyperoil.blockinfection.Items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StaffOfUnlimitedPower extends Item {
    public StaffOfUnlimitedPower(Properties properties) {
        super(properties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        Level level = player.level();
        if (level instanceof ServerLevel serverLevel) {
            entity.kill(serverLevel);
            if (entity.isAlive()) {
                if (entity instanceof LivingEntity living) {
                    living.setHealth(0);
                    if (entity.isAlive()) {
                        entity.remove(Entity.RemovalReason.KILLED);
                    }
                } else {
                    entity.remove(Entity.RemovalReason.KILLED);
                }
            }
        }
        return true;
    }
}
