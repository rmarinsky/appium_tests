package com.ravlyk

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.appium.SelenideAppium
import com.codeborne.selenide.appium.SelenideAppium.`$`
import com.codeborne.selenide.appium.SelenideAppiumElement
import io.appium.java_client.AppiumBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By


class FirstTest {

    @BeforeEach
    fun setUp() {
        Configuration.browser = AndroidDriver::class.java.name
        SelenideAppium.launchApp()
    }


    @Test
    fun login_With_Valid_Credentials() {
        `$`(AppiumBy.id("search_bar")).apply {
            shouldBe(visible)
            click()
        }
        `$`(AppiumBy.id("com.google.android.settings.intelligence:id/open_search_view_edit_text"))
            .shouldBe(visible)
            .setValue("Система")
        `$`(AppiumBy.id("android:id/title")).shouldHave(text("Система"))

    }


    @AfterEach
    fun closeApp() {
        Selenide.closeWebDriver();
    }

}
