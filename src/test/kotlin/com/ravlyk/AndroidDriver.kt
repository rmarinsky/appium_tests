package com.ravlyk

import com.codeborne.selenide.WebDriverProvider
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import java.net.URL


class AndroidDriver : WebDriverProvider {


    override fun createDriver(capabilities: Capabilities): WebDriver {
        val options = UiAutomator2Options()
            .setPlatformName("Android")
            .setPlatformVersion("13")
            .setDeviceName("emulator-5554")
            .setAppPackage("com.android.settings") // Set the package name of the Clock app
            .setAppActivity("com.android.settings.Settings") // Set the main activity of the Clock app
            .setNoReset(false)

        val driver = AndroidDriver(URL("http://127.0.0.1:4723/"), options)

        driver.activateApp("com.android.settings") // Activate the Clock app

        return driver
    }

}