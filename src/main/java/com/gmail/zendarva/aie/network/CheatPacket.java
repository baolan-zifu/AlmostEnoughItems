package com.gmail.zendarva.aie.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by James on 7/29/2018.
 */
public class CheatPacket implements Packet<INetHandlerPlayServer> {

    private ItemStack stack;
    private UUID playerID;

    public CheatPacket(){};

    public CheatPacket(ItemStack stack, UUID uuid){
        this.stack = stack;
        playerID = uuid;
    }

    @Override
    public void readPacketData(PacketBuffer packetBuffer) throws IOException {
        stack = ItemStack.func_199557_a(packetBuffer.readCompoundTag());
        playerID = packetBuffer.readUniqueId();
    }

    @Override
    public void writePacketData(PacketBuffer packetBuffer) throws IOException {
        NBTTagCompound tag = new NBTTagCompound();
        stack.writeToNBT(tag);
        packetBuffer.writeCompoundTag(tag);
        packetBuffer.writeUniqueId(playerID);
    }

    @Override
    public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
        NetHandlerPlayServer server = (NetHandlerPlayServer) iNetHandlerPlayServer;
        EntityPlayerMP player = server.server.getPlayerList().getPlayerByUUID(playerID);
        player.inventory.addItemStackToInventory(stack);
    }
}