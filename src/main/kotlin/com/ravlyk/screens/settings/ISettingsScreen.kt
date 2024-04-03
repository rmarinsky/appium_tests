package org.ravlyk.com.ravlyk.screens.settings

import com.codeborne.selenide.WebDriverRunner
import io.appium.java_client.ios.IOSDriver

interface ISettingsScreen {

    fun searchFor(text: String)

    fun aboutIsVisible()

}

class SettingsScreen() {

    private var strategy: ISettingsScreen? = null

    init {
        when (WebDriverRunner.getWebDriver()) {
            is IOSDriver -> this.strategy = IosSettings()
            else -> this.strategy = AndroidSettings()
        }
    }

    fun searchFor(text: String) {
        strategy?.searchFor(text)
    }

    fun aboutIsVisible() {
        strategy?.aboutIsVisible()
    }
}