package org.ravlyk.com.ravlyk.common

import com.codeborne.selenide.WebDriverRunner.getWebDriver
import org.openqa.selenium.Dimension
import org.openqa.selenium.interactions.Pause
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.Sequence
import org.openqa.selenium.remote.RemoteWebDriver
import java.time.Duration

fun scroll(to: Direction) {
    val webDriver = getWebDriver()
    val size: Dimension = webDriver.manage().window().getSize()
    val startX: Int = size.getWidth() / 2
    val startY: Int = size.getHeight() / 2
    val endX = startX

    val endY = when (to) {
        Direction.UP -> (size.getHeight() * 0.25).toInt()
        Direction.DOWN -> size.getHeight()
    }

    val finger1 = PointerInput(PointerInput.Kind.TOUCH, "finger1")
    val sequence = Sequence(finger1, 1)
        .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
        .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
        .addAction(Pause(finger1, Duration.ofMillis(200)))
        .addAction(
            finger1.createPointerMove(
                Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY
            )
        )
        .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))

    (getWebDriver() as RemoteWebDriver).perform(listOf(sequence))
}

enum class Direction {

    UP, DOWN

}
