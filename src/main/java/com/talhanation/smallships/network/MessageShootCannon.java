package com.talhanation.smallships.network;

import com.talhanation.smallships.entities.AbstractShipCannon;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageShootCannon implements Message<MessageShootCannon> {

    private boolean shoot;

    public MessageShootCannon() {
    }

    public MessageShootCannon(boolean shoot) {
        this.shoot = shoot;

    }

    public Dist getExecutingSide() {
        return Dist.DEDICATED_SERVER;
    }

    public void executeServerSide(NetworkEvent.Context context) {
        Entity riding = context.getSender().getVehicle();
        if (!(riding instanceof AbstractShipCannon))
            return;
        AbstractShipCannon sailBoat = (AbstractShipCannon) riding;
        if (context.getSender() == (sailBoat.getDriver())) {
            sailBoat.shootCannon(shoot);
        }
    }

    public MessageShootCannon fromBytes(PacketBuffer buf) {
        this.shoot = buf.readBoolean();
        return this;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeBoolean(this.shoot);
    }

}
