package org.ravlyk.com.ravlyk.screens.settings

import com.codeborne.selenide.Condition
import com.codeborne.selenide.appium.SelenideAppium.`$`
import io.appium.java_client.AppiumBy.id

class AndroidSettings : ISettingsScreen {
    override fun searchFor(text: String) {
        `$`(id("search_bar")).apply {
            shouldBe(Condition.visible)
            click()
        }
        `$`(id("com.google.android.settings.intelligence:id/open_search_view_edit_text"))
            .shouldBe(Condition.visible)
            .setValue(text)
    }

    override fun aboutIsVisible() {
        `$`(id("android:id/title")).shouldHave(Condition.text("About"))
    }
}