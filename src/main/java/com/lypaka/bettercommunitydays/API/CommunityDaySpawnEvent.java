package com.lypaka.bettercommunitydays.API;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class CommunityDaySpawnEvent extends Event {

    private final ServerPlayerEntity player;
    private Pokemon communityDaySpawn;
    private final Pokemon originalSpawn;

    public CommunityDaySpawnEvent (ServerPlayerEntity player, Pokemon communityDaySpawn, Pokemon originalSpawn) {

        this.player = player;
        this.communityDaySpawn = communityDaySpawn;
        this.originalSpawn = originalSpawn;

    }

    public ServerPlayerEntity getPlayer() {

        return this.player;

    }

    public Pokemon getCommunityDaySpawn() {

        return this.communityDaySpawn;

    }

    public void setCommunityDaySpawn (Pokemon pokemon) {

        this.communityDaySpawn = pokemon;

    }

    public Pokemon getOriginalSpawn() {

        return this.originalSpawn;

    }

}
