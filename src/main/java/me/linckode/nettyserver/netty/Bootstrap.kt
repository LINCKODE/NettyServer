package me.linckode.nettyserver.netty

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import me.linckode.nettyserver.Util

class Bootstrap {


    fun start(port: Int) {
        Util.log("Server starting on port $port...")

        val masterGroup: EventLoopGroup = NioEventLoopGroup()
        val slaveGroup: EventLoopGroup = NioEventLoopGroup()

        try {
            val serverBootstrap = ServerBootstrap()
            serverBootstrap.group(masterGroup,slaveGroup)
                .channel(NioServerSocketChannel::class.java)
                .childHandler(NetworkInitializer())
                .childOption(ChannelOption.SO_KEEPALIVE, true)
            val channelFuture = serverBootstrap.bind(port).sync()
            if (channelFuture.isSuccess) Util.log("Server started successfully.")
            channelFuture.channel().closeFuture().sync()
        } finally {
           Util.log("Stopping server.")
           masterGroup.shutdownGracefully()
           slaveGroup.shutdownGracefully()
        }


    }


}