package org.ravlyk.com.ravlyk.screens.settings

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import io.appium.java_client.AppiumBy
import org.ravlyk.com.ravlyk.common.Direction.DOWN
import org.ravlyk.com.ravlyk.common.scroll


class IosSettings : ISettingsScreen {
    override fun searchFor(text: String) {
        scroll(DOWN)

        `$`(AppiumBy.iOSNsPredicateString("name=='Search'")).apply {
            click()
            setValue(text)
        }
        `$`(AppiumBy.accessibilityId(text)).click()
    }

    override fun aboutIsVisible() {
        `$`(AppiumBy.iOSNsPredicateString("name=='About'")).shouldHave(Condition.text("About"))
    }
}
