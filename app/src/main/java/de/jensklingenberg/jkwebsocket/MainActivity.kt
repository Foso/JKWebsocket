package de.jensklingenberg.jkwebsocket

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.text.format.Formatter
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var serverK: KMyHttpServer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initIPAddress()
        try {
            serverK = KMyHttpServer(this)

        } catch (e: IOException) {
            e.printStackTrace()
        }

        serverK?.start()
    }


    private fun initIPAddress() {
        val wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ip = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
        textview.text = "My IP Address: " + ip + ":" + KMyHttpServer.PORT
        Log.i("TAG", "onCreate: " + ip)
    }
}
