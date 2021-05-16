package me.linckode.nettyserver

import me.linckode.nettyserver.netty.Bootstrap

fun main() {
    Bootstrap().start(25565)
}