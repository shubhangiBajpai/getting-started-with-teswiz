package com.znsio.sample.e2e.screen.android.vodqa;

import com.znsio.sample.e2e.screen.vodqa.DragAndDropScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import io.appium.java_client.AppiumBy;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class DragAndDropScreenAndroid extends DragAndDropScreen {

    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = DragAndDropScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final By byCircleDroppedAccessibilityId = AppiumBy.accessibilityId("success");
    private final By byDraggableObjectAccessibilityId = AppiumBy.accessibilityId("dragMe");
    private final By byDropZoneAccessibilityId = AppiumBy.accessibilityId("dropzone");


    public DragAndDropScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public boolean isMessageVisible() {
        driver.waitTillElementIsPresent(byCircleDroppedAccessibilityId);
        visually.checkWindow(SCREEN_NAME, "Circle Dropped");
        return driver.findElement(byCircleDroppedAccessibilityId).isDisplayed();
    }

    @Override
    public DragAndDropScreen dragAndDropCircleObject() {
        driver.waitTillElementIsVisible(byDraggableObjectAccessibilityId);
        visually.checkWindow(SCREEN_NAME, "Drag and Drop screen");
        driver.dragAndDrop(byDraggableObjectAccessibilityId, byDropZoneAccessibilityId);
        return this;
    }
}