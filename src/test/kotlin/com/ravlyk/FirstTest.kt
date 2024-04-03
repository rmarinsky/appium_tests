package com.ravlyk

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import com.codeborne.selenide.appium.SelenideAppium
import com.codeborne.selenide.appium.SelenideAppium.`$`
import com.ravlyk.Platform.IOS
import io.appium.java_client.AppiumBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.ravlyk.com.ravlyk.screens.settings.SettingsScreen
import java.time.Duration


class FirstTest {

    fun platform(platform: Platform) {
        System.setProperty("platform", platform.name)
    }

    fun platform(): Platform {
        return Platform.valueOf(System.getProperty("platform"))
    }

    @BeforeEach
    fun setUp() {
        platform(IOS)

        Configuration.browser = DriverProvider::class.java.name
        SelenideAppium.launchApp()
    }


    @Test
    fun oopTest() {
        val targetQuery = if (platform() == IOS) "General" else "Система"

        SettingsScreen().apply {
            searchFor(targetQuery)
            aboutIsVisible()
        }
    }

    @Test
    fun rawIos() {
        `$`(AppiumBy.iOSNsPredicateString("name == 'General'")).apply {
            shouldBe(visible)
            click()
        }
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0))

        WebDriverRunner.getWebDriver().findElement(AppiumBy.iOSNsPredicateString("name=='Fonts'")).click()


        WebDriverRunner.getWebDriver().findElement(AppiumBy.iOSNsPredicateString("name=='No Fonts Installed'")).click()
    }

    @Test
    fun rawAndroid() {
        `$`(AppiumBy.id("search_bar")).apply {
            shouldBe(visible)
            click()
        }
        `$`(AppiumBy.id("com.google.android.settings.intelligence:id/open_search_view_edit_text"))
            .shouldBe(visible)
            .setValue("Система")
        `$`(AppiumBy.id("android:id/title")).shouldHave(Condition.text("Система"))
    }


    @AfterEach
    fun closeApp() {
        Selenide.closeWebDriver();
    }

}
