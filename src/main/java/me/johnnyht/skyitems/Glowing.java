package me.johnnyht.skyitems;

import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Glowing {
    //Packet Effect
    MobEffectInstance effectPacket = new MobEffectInstance(MobEffect.byId(24),100,0,true,false,false);

    public void glow(Player user, Player badGuy){
        ServerPlayer itemUser = ((CraftPlayer)user).getHandle();
        ServerPlayer badBoi = ((CraftPlayer)badGuy).getHandle();

        ClientboundUpdateMobEffectPacket packet = new ClientboundUpdateMobEffectPacket(badBoi.getId(),effectPacket);
        itemUser.connection.send(packet);
        Bukkit.getLogger().info("2");
    }
}
