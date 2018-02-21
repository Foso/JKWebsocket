package de.jensklingenberg.jkwebsocket.network.websocket;

import android.content.Context;
import android.util.Log;
import de.jensklingenberg.jkwebsocket.KMyHttpServer

import java.io.IOException;
import java.nio.charset.CharacterCodingException;
import java.util.concurrent.TimeUnit;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoWSD;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.*


open class WebSocket(context: Context, internal var httpSession: NanoHTTPD.IHTTPSession, internal var httpServer: KMyHttpServer) : NanoWSD.WebSocket(httpSession) {

        override fun onOpen() {
        Log.d(TAG, "onOpen: ")
        httpServer.connections.add(this)

        try {
        val pingframe = NanoWSD.WebSocketFrame(NanoWSD.WebSocketFrame.OpCode.Ping, false, "")

        ping(pingframe.binaryPayload)
        Log.d(TAG, "onOpen: ping")
        startRunner()
        } catch (e: CharacterCodingException) {
        e.printStackTrace()
        } catch (e: IOException) {
        e.printStackTrace()
        }


        }

        private fun startRunner() {

        Observable.fromCallable {
        //Do Something

        Log.d(TAG, "onOpen: 2")

        val pingframe = NanoWSD.WebSocketFrame(NanoWSD.WebSocketFrame.OpCode.Ping, false, "")

                send("Hello World"+ Date())


        ping(pingframe.binaryPayload)
        //send("Hallo");
        true
        }.delay(1, TimeUnit.SECONDS)
        .repeat()
        .subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.newThread())
        .subscribe { result ->
        //Use result for something
        }


        }

        override fun onClose(code: NanoWSD.WebSocketFrame.CloseCode, reason: String, initiatedByRemote: Boolean) {
        this.httpServer.connections.remove(this)
        }

        override fun onMessage(message: NanoWSD.WebSocketFrame) {
        Log.d(TAG, message.textPayload.toString())
        Log.d(TAG, "onMessage: ")
        message.setUnmasked()

        }

        override fun onPong(pong: NanoWSD.WebSocketFrame) {
        Log.d(TAG, "onPong: ")
        }

        override fun onException(exception: IOException) {
        Log.d(TAG, "onException: " + exception.message)
        }

        companion object {
        val TAG = "ddd"
        }
        }