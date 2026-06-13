package com.github.paczek224.tests.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Component {

    protected final Page page;

    public Component(Page page) {
        this.page = page;
    }

    protected Locator byTestId(String testId) {
        return page.getByTestId(testId);
    }

    /**
     * Sets a styled toggle (checkbox) to the desired state. The underlying checkbox is
     * visually collapsed (0x0), so the click is dispatched on the wrapping {@code label},
     * which toggles the input and fires its change event. Only acts when the state differs.
     *
     * @param checkbox the underlying checkbox input locator
     * @param desired  the target checked state
     */
    protected void setToggle(Locator checkbox, boolean desired) {
        if (checkbox.isChecked() != desired) {
            checkbox.locator("xpath=ancestor::label[1]").click();
        }
    }

    /**
     * Selects a month/year in the custom date-picker opened by the given trigger.
     * Opens the picker, navigates to the requested year and clicks the month cell.
     *
     * @param trigger the picker trigger button locator (e.g. {@code work-from-1})
     * @param year    four-digit year to navigate to
     * @param month   month number, 1-12
     */
    protected void selectDate(Locator trigger, int year, int month) {
        trigger.click();
        Locator panel = page.locator(".mp-panel.mp-open");
        Locator yearLabel = panel.locator(".mp-year");

        int displayed = Integer.parseInt(yearLabel.innerText().trim());
        String arrow = year < displayed ? ".mp-prev" : ".mp-next";
        while (displayed != year) {
            panel.locator(arrow).click();
            displayed = Integer.parseInt(yearLabel.innerText().trim());
        }

        panel.locator(".mp-month[data-m='" + month + "']").click();
    }
}
