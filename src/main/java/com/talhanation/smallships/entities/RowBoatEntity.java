package com.talhanation.smallships.entities;

import com.talhanation.smallships.init.ModEntityTypes;
import com.talhanation.smallships.items.ModItems;
import com.talhanation.smallships.util.RowBoatItemStackHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;

public class RowBoatEntity extends AbstractRowBoatEntity {
    public boolean Cargo_0 = true;
    public boolean Cargo_1 = true;

    private static final EntityDataAccessor<Integer> CARGO = SynchedEntityData.defineId(AbstractRowBoatEntity.class, EntityDataSerializers.INT);

    public RowBoatEntity(EntityType<? extends AbstractRowBoatEntity> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    @Override
    public void tick(){
        super.tick();
        this.getCargo();
        if (0 < this.getCargo()) Cargo_0 = true;
        else Cargo_0 = false;
        if (1 < this.getCargo()) Cargo_1 = true;
        else Cargo_1 = false;

    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (player.isSecondaryUseActive()) {
            if (this.isVehicle() && !(getControllingPassenger() instanceof Player)){
                this.ejectPassengers();
                this.passengerwaittime = 200;
            }
            else {
                if (!(getControllingPassenger() instanceof Player)) {
                   openContainer(player);
                } return InteractionResult.sidedSuccess(this.level.isClientSide);
            } return InteractionResult.PASS;
        } else if (this.outOfControlTicks < 60.0F) {
            if (!this.level.isClientSide) {
                return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;

            } else {
                return InteractionResult.SUCCESS;
            }
        } else {
            return InteractionResult.PASS;
        }
    }

    public RowBoatEntity(Level worldIn, double x, double y, double z) {
        this(ModEntityTypes.ROWBOAT_ENTITY.get(), worldIn);
        setPos(x, y, z);
        setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0.75D;
    }

    @Override
    public void positionRider(Entity passenger) {
        if (hasPassenger(passenger)) {
            float f = -0.75F; //driver x pos
            float d = 0.0F;   //driver z pos
            float f1 = (float) ((this.removed ? 0.02D : getPassengersRidingOffset()) + passenger.getMyRidingOffset());
            if (getPassengers().size() == 2) {
                int i = getPassengers().indexOf(passenger);
                if (i == 0) {
                    f = -0.75F;
                    d = 0.0F;
                } else {
                    f = 0.5F;
                    d = -0.0F;
                }
            }
            Vec3 vector3d = (new Vec3((double) f, 0.0D, d)).yRot(-this.getYRot() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
            passenger.setPos(this.getX() + vector3d.x, this.getY() + (double) f1, +this.getZ() + vector3d.z);
            passenger.setYRot(passenger.getYRot() + this.deltaRotation);
            clampRotation(passenger);
        }
    }

    public Item getItemBoat() {
        switch (this.getBoatType()) {
            case OAK:
            default:
                return ModItems.OAK_ROWBOAT_ITEM.get();
            case SPRUCE:
                return ModItems.SPRUCE_ROWBOAT_ITEM.get();
            case BIRCH:
                return ModItems.BIRCH_ROWBOAT_ITEM.get();
            case JUNGLE:
                return ModItems.JUNGLE_ROWBOAT_ITEM.get();
            case ACACIA:
                return ModItems.ACACIA_ROWBOAT_ITEM.get();
            case DARK_OAK:
                return ModItems.DARK_OAK_ROWBOAT_ITEM.get();

                //BOP
            case BOP_CHERRY:
                return ModItems.BOP_CHERRY_ROWBOAT_ITEM.get();
            case BOP_DEAD:
                return ModItems.BOP_DEAD_ROWBOAT_ITEM.get();
            case BOP_FIR:
                return ModItems.BOP_FIR_ROWBOAT_ITEM.get();
            case BOP_HELLBARK:
                return ModItems.BOP_HELLBARK_ROWBOAT_ITEM.get();
            case BOP_JACARANDA:
                return ModItems.BOP_JACARANDA_ROWBOAT_ITEM.get();
            case BOP_MAGIC:
                return ModItems.BOP_MAGIC_ROWBOAT_ITEM.get();
            case BOP_MAHOGANY:
                return ModItems.BOP_MAHOGANY_ROWBOAT_ITEM.get();
            case BOP_PALM:
                return ModItems.BOP_PALM_ROWBOAT_ITEM.get();
            case BOP_REDWOOD:
                return ModItems.BOP_REDWOOD_ROWBOAT_ITEM.get();
            case BOP_UMBRAN:
                return ModItems.BOP_UMBRAN_ROWBOAT_ITEM.get();
            case BOP_WILLOW:
                return ModItems.BOP_WILLOW_ROWBOAT_ITEM.get();
            //LOTR
            case LOTR_APPLE:
                return ModItems.LOTR_APPLE_ROWBOAT_ITEM.get();
            case LOTR_ASPEN:
                return ModItems.LOTR_ASPEN_ROWBOAT_ITEM.get();
            case LOTR_BEECH:
                return ModItems.LOTR_BEECH_ROWBOAT_ITEM.get();
            case LOTR_CEDAR:
                return ModItems.LOTR_CEDAR_ROWBOAT_ITEM.get();
            case LOTR_CHERRY:
                return ModItems.LOTR_CHERRY_ROWBOAT_ITEM.get();
            case LOTR_CHARRED:
                return ModItems.LOTR_CHARRED_ROWBOAT_ITEM.get();
            case LOTR_CYPRESS:
                return ModItems.LOTR_CYPRESS_ROWBOAT_ITEM.get();
            case LOTR_FIR:
                return ModItems.LOTR_FIR_ROWBOAT_ITEM.get();
            case LOTR_GREEN_OAK:
                return ModItems.LOTR_GREEN_OAK_ROWBOAT_ITEM.get();
            case LOTR_HOLLY:
                return ModItems.LOTR_HOLLY_ROWBOAT_ITEM.get();
            case LOTR_LAIRELOSSE:
                return ModItems.LOTR_LAIRELOSSE_ROWBOAT_ITEM.get();
            case LOTR_LARCH:
                return ModItems.LOTR_LARCH_ROWBOAT_ITEM.get();
            case LOTR_LEBETHRON:
                return ModItems.LOTR_LEBETHRON_ROWBOAT_ITEM.get();
            case LOTR_MALLORN:
                return ModItems.LOTR_MALLORN_ROWBOAT_ITEM.get();
            case LOTR_MAPLE:
                return ModItems.LOTR_MAPLE_ROWBOAT_ITEM.get();
            case LOTR_MIRK_OAK:
                return ModItems.LOTR_MIRK_OAK_ROWBOAT_ITEM.get();
            case LOTR_PEAR:
                return ModItems.LOTR_PEAR_ROWBOAT_ITEM.get();
            case LOTR_PINE:
                return ModItems.LOTR_PINE_ROWBOAT_ITEM.get();
            case LOTR_ROTTEN:
                return ModItems.LOTR_ROTTEN_ROWBOAT_ITEM.get();

            //ENVI//
            case ENVI_CHERRY:
                return ModItems.ENVI_CHERRY_ROWBOAT_ITEM.get();
            case ENVI_WILLOW:
                return ModItems.ENVI_WILLOW_ROWBOAT_ITEM.get();
            case ENVI_WISTERIA:
                return ModItems.ENVI_WISTERIA_ROWBOAT_ITEM.get();

        }

    }

    protected ItemStackHandler initInventory() {
        return new RowBoatItemStackHandler<RowBoatEntity>(9, this) {
            protected void onContentsChanged(int slot) {
                int sigma, tempload = 0;
                for (int i = 0; i < getSlots(); i++) {
                    if (!getStackInSlot(i).isEmpty())
                        tempload++;
                }
                if (tempload > 7) {
                    sigma = 2;
                } else if (tempload > 3) {
                    sigma = 1;
                } else {
                    sigma = 0;
                }
                (this.rowboat).getEntityData().set(RowBoatEntity.CARGO, sigma);
            }
        };
    }

    public int getCargo() {
        return this.entityData.get(CARGO);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CARGO, 0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(CARGO, compound.getInt("Cargo"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Cargo", this.entityData.get(CARGO));
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return (getPassengers().size() < 2);
    }
}
