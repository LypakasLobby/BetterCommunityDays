package com.lypaka.bettercommunitydays.API;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class CommunityDayCaptureEvent extends Event {

    private final ServerPlayerEntity player;
    private final Pokemon pokemon;
    private int catchrate;

    public CommunityDayCaptureEvent (ServerPlayerEntity player, Pokemon pokemon, int catchrate) {

        this.player = player;
        this.pokemon = pokemon;
        this.catchrate = catchrate;

    }

    public ServerPlayerEntity getPlayer() {

        return this.player;

    }

    public Pokemon getPokemon() {

        return this.pokemon;

    }

    public int getCatchrate() {

        return this.catchrate;

    }

    public void setCatchrate (int catchrate) {

        this.catchrate = catchrate;

    }

}
