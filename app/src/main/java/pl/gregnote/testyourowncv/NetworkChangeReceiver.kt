package pl.gregnote.testyourowncv

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager

class NetworkChangeReceiver : BroadcastReceiver() {
    private var listener: ConnectionChangeListener? = null
    override fun onReceive(context: Context, intent: Intent) {
        if (listener != null) listener!!.callback(isOnline(context))
    }

    interface ConnectionChangeListener {
        fun callback(connected: Boolean)
    }

    companion object {
        fun registerListener(
            context: Context,
            listener: ConnectionChangeListener?
        ): NetworkChangeReceiver {
            val receiver = NetworkChangeReceiver()
            receiver.listener = listener
            context.registerReceiver(
                receiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
            return receiver
        }

        fun unregisterListener(
            context: Context,
            receiver: NetworkChangeReceiver
        ) {
            receiver.listener = null
            context.unregisterReceiver(receiver)
        }

        fun isOnline(context: Context): Boolean {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }
    }
}
