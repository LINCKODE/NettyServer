package me.linckode.nettyserver.netty.packet.packets.serverbound

import me.linckode.nettyserver.netty.packet.Packet
import me.linckode.nettyserver.netty.packet.codec.PacketBuffer

class SHandShakePacket(): Packet() {

    var protocolVersion: Int = 0
    var hostname: String = ""
    var port: Int = 0
    var nextState: Int = -1

    override val packetId: Int = 0x00

    override fun serialize(packetBuffer: PacketBuffer) {
        packetBuffer.writeVarInt(protocolVersion)
        packetBuffer.writeString(hostname)
        packetBuffer.writeShort(port)
        packetBuffer.writeVarInt(nextState)
    }

    override fun deserialize(packetBuffer: PacketBuffer): Packet {
        protocolVersion = packetBuffer.readVarInt()
        hostname = packetBuffer.readString(255)
        port = packetBuffer.readUnsignedShort()
        nextState = packetBuffer.readVarInt()
        return this
    }
}