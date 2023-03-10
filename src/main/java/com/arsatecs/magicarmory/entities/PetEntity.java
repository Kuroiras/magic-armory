package com.arsatecs.magicarmory.entities;

import com.arsatecs.magicarmory.init.MobsInit;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.entity.animal.Turtle.FOOD_ITEMS;

public class PetEntity extends TamableAnimal {
    public PetEntity(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return MobsInit.PET.get().create(p_146743_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean hurt(DamageSource damageSource, float p_27568) {
        return false;
    }

    public static AttributeSupplier.Builder getPetEntityAttrs() {
        return Mob.createMobAttributes()
                .add(ForgeMod.ENTITY_GRAVITY.get(), 2f)
                .add(Attributes.MOVEMENT_SPEED, 0.30D);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (itemstack.is(Items.PUFFERFISH)) {
            this.spawnTamingParticles(true);
            this.tame(player);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

//    private void customTamingParticles(){
//        ParticleOptions particleOptions = ParticleTypes.FIREWORK;
//        for (int i = 0; i < 7; i++) {
//            double d0 = this.random.nextGaussian() * 0.020;
//            double d1 = this.random.nextGaussian() * 0.020;
//            double d2 = this.random.nextGaussian() * 0.020;
//            this.level.addParticle(particleOptions, this.getRandomX(1.0D), );
//
//        }
//    }
}
