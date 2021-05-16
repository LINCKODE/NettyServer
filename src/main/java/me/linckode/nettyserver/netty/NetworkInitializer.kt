package me.linckode.nettyserver.netty

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import me.linckode.nettyserver.netty.packet.codec.PacketDecoder
import me.linckode.nettyserver.netty.packet.codec.PacketEncoder

class NetworkInitializer: ChannelInitializer<SocketChannel>() {
    override fun initChannel(socketChannel: SocketChannel) {
        socketChannel.pipeline().addLast(PacketEncoder())
        socketChannel.pipeline().addLast(PacketDecoder())
    }
}