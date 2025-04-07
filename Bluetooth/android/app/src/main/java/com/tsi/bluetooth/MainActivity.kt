package com.tsi.bluetooth;

import android.os.Bundle
import com.getcapacitor.BridgeActivity;
import com.tsi.bluetooth.plugins.bluetooth.BluetoothLe

class MainActivity : BridgeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        registerPlugin(BluetoothLe::class.java)
        super.onCreate(savedInstanceState)
    }
}
