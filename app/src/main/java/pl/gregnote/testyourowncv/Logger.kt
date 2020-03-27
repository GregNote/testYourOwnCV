package pl.gregnote.testyourowncv

import android.util.Log

class Logger {
    companion object {
        private const val TAG = "HTTP"
        private val log = BuildConfig.BUILD_TYPE.equals("debug", true)

        fun log(msg: String) {
            if (log) Log.d(TAG, msg)
        }
    }
}
