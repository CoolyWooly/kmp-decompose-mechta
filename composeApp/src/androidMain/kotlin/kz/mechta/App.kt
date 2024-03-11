package kz.mechta

import android.app.Application
import cloud.mindbox.mobile_sdk.Mindbox
import cloud.mindbox.mobile_sdk.MindboxConfiguration
import di.initKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initMindbox()
    }

    private fun initMindbox() {
        val configuration = MindboxConfiguration
            .Builder(
                applicationContext,
                "api.mindbox.ru",
                "mechta-Android-sandbox"
            )
            .shouldCreateCustomer(true)
            .subscribeCustomerIfCreated(true)
            .build()
        Mindbox.init(this, configuration, listOf())
    }
}