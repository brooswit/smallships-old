package com.talhanation.smallships.client.events;

import com.talhanation.smallships.Main;
import com.talhanation.smallships.entities.TNBoatEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyEvents {

    private boolean wasSailPressed;

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        ClientPlayerEntity clientPlayerEntity = minecraft.player;
        if (clientPlayerEntity == null)
            return;

        Entity riding = clientPlayerEntity.getRidingEntity();
        if (!(riding instanceof TNBoatEntity)) {
            return;
        }
        TNBoatEntity boat = (TNBoatEntity) riding;
        if (clientPlayerEntity.equals(boat.getDriver())) {
            if (Main.SAIL_KEY.isKeyDown()) {
                if (!this.wasSailPressed) {
                    boat.onSprintPressed();
                    this.wasSailPressed = true;
                }
            } else {
                this.wasSailPressed = false;
            }
        }
    }
}
