package de.jensklingenberg.jkwebsocket;

import android.content.Context
import de.jensklingenberg.jkwebsocket.network.websocket.WebSocket
import fi.iki.elonen.NanoHTTPD
import fi.iki.elonen.NanoWSD

class KMyHttpServer(val context: Context) : NanoWSD(PORT) {
    val connections: ArrayList<WebSocket>


    init {
        this.connections = arrayListOf()
    }

    companion object {
        val PORT = 8766

    }


    override fun openWebSocket(handshake: NanoHTTPD.IHTTPSession): NanoWSD.WebSocket {
        var uri = handshake.uri
        uri = uri.replaceFirst("/", "", true)



        return WebSocket(handshake, this)


    }

    public override fun serveHttp(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {
        return NanoHTTPD.newFixedLengthResponse("Command  not found")

    }


}


