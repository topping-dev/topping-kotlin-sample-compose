package dev.topping.kotlin.androidApp

import android.os.Bundle
import dev.topping.android.LuaEvent
import dev.topping.android.LuaForm
import dev.topping.android.backend.LuaInterface
import dev.topping.entry.KTEntry

class MainActivity : LuaForm(), LuaInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.empty)

        KTEntry.initFromPlatform(this, object : KTEntry.OnCompletePlatform {
            override fun onComplete() {
            }
        })
    }

    override fun GetId(): String {
        return "Main"
    }
}
