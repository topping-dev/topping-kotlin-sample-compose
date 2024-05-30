package dev.topping.entry

import dev.topping.kotlin.*
import dev.topping.kotlinsample.MainForm

class KTEntry {
    interface OnCompletePlatform {
        fun onComplete()
    }
    companion object {
        fun initFromPlatform(activityOrWindow: Any, onComplete: OnCompletePlatform) {
            Platform.init(activityOrWindow, object : OnBeforeInit {
                override fun onBeforeInit() {
                    init()
                }

            }, object : OnComplete {
                override fun onComplete() {
                    onComplete.onComplete()
                }
            })
        }
        fun init() {
            LuaEvent.registerForm("Main", ::MainForm)
        }
    }
}