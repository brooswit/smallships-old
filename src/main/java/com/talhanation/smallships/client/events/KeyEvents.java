package com.talhanation.smallships.client.events;

import com.talhanation.smallships.Main;
import com.talhanation.smallships.entities.TNBoatEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;

public class KeyEvents {

    private boolean wasSailPressed;
    private boolean wasInvPressed;
    private boolean wasDismountPressed;

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        Player clientPlayerEntity = minecraft.player;
        if (clientPlayerEntity == null)
            return;

        Entity vehicle = clientPlayerEntity.getVehicle();
        if (!(vehicle instanceof TNBoatEntity)){
            return;
        }
        TNBoatEntity boat = (TNBoatEntity) vehicle;
        if (clientPlayerEntity.equals(boat.getDriver())) {
            if (Main.SAIL_KEY.isDown()) {
                boat.onKeyPressed();
                this.wasSailPressed = true;
            }
            else {
                this.wasSailPressed = false;
            }
        }
        if (boat.getPassengers().contains(clientPlayerEntity)) {
            if (Main.INV_KEY.isDown()) {
                boat.onInvPressed(clientPlayerEntity);
                this.wasInvPressed = true;
            }else {
                this.wasInvPressed = false;

            }
        }
        if (clientPlayerEntity.equals(boat.getDriver())) {
            if (Main.SAIL_L_KEY.isDown()) {
                boat.onKeyLowerPressed();
                this.wasSailPressed = true;
            }
            else {
                this.wasSailPressed = false;
            }
        }
        if (clientPlayerEntity.equals(boat.getDriver())) {
            if (Main.SAIL_H_KEY.isDown()) {
                boat.onKeyHigherPressed();
                this.wasSailPressed = true;
            }
            else {
                this.wasSailPressed = false;
            }
        }
        /*if (clientPlayerEntity.equals(boat.getDriver())) {
            if (Main.DISMOUNT_KEY.isDown()) {
                boat.onDismountPressed();
                this.wasDismountPressed = true;
            }
            else {
                this.wasDismountPressed = false;
            }
        }
    */
    }
}
