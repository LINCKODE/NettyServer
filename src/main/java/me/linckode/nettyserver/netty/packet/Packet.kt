package me.linckode.nettyserver.netty.packet

import me.linckode.nettyserver.netty.packet.codec.PacketBuffer
import me.linckode.nettyserver.netty.packet.packets.serverbound.SHandShakePacket
import java.io.IOException

abstract class Packet {
    abstract val packetId: Int
    abstract fun serialize(packetBuffer: PacketBuffer)
    abstract fun deserialize(packetBuffer: PacketBuffer): Packet

    companion object {

        fun create(packetBuffer: PacketBuffer): Packet {

            when (val packetId = packetBuffer.readVarInt()) {
                0  -> {
                    return SHandShakePacket().deserialize(packetBuffer)
                }
                else -> {
                    throw IOException("Packet ID [$packetId] not found.")
                }
            }

        }
    }

}