package com.ravlyk

import com.codeborne.selenide.WebDriverProvider
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.ios.options.XCUITestOptions
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import java.net.URL


class DriverProvider : WebDriverProvider {


    override fun createDriver(capabilities: Capabilities): WebDriver {
        return when (Platform.valueOf(System.getProperty("platform"))) {
            Platform.ANDROID -> getAndroidDriver();
            Platform.IOS -> getIOSDriver()
        }
    }

    private fun getAndroidDriver(): AndroidDriver {
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

    private fun getIOSDriver(): IOSDriver {
        val options = XCUITestOptions()
            .setPlatformName("iOS")
            .setPlatformVersion("17.0")
            .setDeviceName("iPhone 15 Pro")
            .setApp("com.apple.Preferences") // Set the bundle ID of the Clock app
            .setNoReset(false)

        val iosDriver = IOSDriver(URL("http://127.0.0.1:4723/"), options)
        iosDriver.activateApp("com.apple.Preferences") // Activate the Clock app
        return iosDriver
    }

}

enum class Platform {
    ANDROID, IOS
}