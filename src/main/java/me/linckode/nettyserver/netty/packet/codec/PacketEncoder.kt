package me.linckode.nettyserver.netty.packet.codec

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import me.linckode.nettyserver.netty.packet.Packet

class PacketEncoder : MessageToByteEncoder<Packet>() {
    override fun encode(context: ChannelHandlerContext?, packet: Packet, byteBuf: ByteBuf) {
        val packetBuffer = PacketBuffer(byteBuf)
        packet.serialize(packetBuffer)
        println(byteBuf.readInt())

        
    }
}