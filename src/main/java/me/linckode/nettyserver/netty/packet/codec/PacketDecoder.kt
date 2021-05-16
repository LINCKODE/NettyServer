package me.linckode.nettyserver.netty.packet.codec

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import me.linckode.nettyserver.netty.packet.Packet
import me.linckode.nettyserver.netty.packet.packets.serverbound.SHandShakePacket
import java.io.IOException

class PacketDecoder: ByteToMessageDecoder() {
    override fun decode(context: ChannelHandlerContext, byteBuf: ByteBuf, list: MutableList<Any>) {

        if(byteBuf.readableBytes() != 0) {
            val packetBuffer = PacketBuffer(byteBuf)
            packetBuffer.readVarInt()
            val packet: Packet = Packet.create(packetBuffer)
            list.add(packet)
        }
    }
}