package de.jensklingenberg.jkwebsocket;

import android.content.Context
import fi.iki.elonen.NanoHTTPD
import fi.iki.elonen.NanoWSD
import de.jensklingenberg.jkwebsocket.network.websocket.WebSocket

class KMyHttpServer : NanoWSD {
    private val context: Context
    var main : MainActivity? = null
    val connections: ArrayList<WebSocket>


    constructor(context: Context) : super(PORT) {
        this.context = context
        this.connections = arrayListOf()
        main=context as MainActivity
        this.main = context
    }

    companion object {
        val PORT = 8766

    }



    override fun openWebSocket(handshake: NanoHTTPD.IHTTPSession): NanoWSD.WebSocket {
        var uri = handshake.uri
        uri=uri.replaceFirst("/","",true)



                return WebSocket(context,handshake, this)




    }

    public override fun serveHttp(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {
        var uri = session.uri


        return NanoHTTPD.newFixedLengthResponse("Command  not found")

    }




}


