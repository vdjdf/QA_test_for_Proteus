package steps;

import com.codeborne.selenide.SelenideElement;
import model.FormEntry;
import pages.DataFormPage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataFormPageSteps {

    private final LoginPage loginPage = new LoginPage();
    private final DataFormPage dataFormPage = new DataFormPage();
    private FormEntry currentEntry;

    public DataFormPageSteps login(String email, String password) {
        loginPage.getEmailInput().shouldBe(visible).clear();
        loginPage.getEmailInput().sendKeys(email);
        loginPage.getPasswordInput().shouldBe(visible).clear();
        loginPage.getPasswordInput().sendKeys(password);
        loginPage.getAuthButton().click();
        return this;
    }

    public DataFormPageSteps enterEmail(String email) {
        currentEntry = new FormEntry();
        currentEntry.setEmail(email);
        dataFormPage.getEmailInput().shouldBe(visible).clear();
        dataFormPage.getEmailInput().sendKeys(email);
        return this;
    }

    public DataFormPageSteps enterName(String name) {
        currentEntry.setName(name);
        dataFormPage.getNameInput().shouldBe(visible).clear();
        dataFormPage.getNameInput().sendKeys(name);
        return this;
    }

    public DataFormPageSteps selectGender(String gender) {
        currentEntry.setGender(gender);
        dataFormPage.getGenderSelect().selectOptionByValue(gender);
        return this;
    }

    public DataFormPageSteps checkOption11() {
        currentEntry.setCheck11(true);
        toggleCheckbox(dataFormPage.getCheckbox11(), true);
        return this;
    }

    public DataFormPageSteps checkOption12() {
        currentEntry.setCheck12(true);
        toggleCheckbox(dataFormPage.getCheckbox12(), true);
        return this;
    }

    public DataFormPageSteps resetCheckboxes() {
        currentEntry.setCheck11(false);
        currentEntry.setCheck12(false);
        toggleCheckbox(dataFormPage.getCheckbox11(), false);
        toggleCheckbox(dataFormPage.getCheckbox12(), false);
        return this;
    }

    public DataFormPageSteps selectRadio(String option) {
        currentEntry.setChoice2(option);
        switch (option) {
            case "2.1" -> dataFormPage.getRadioButton21().click();
            case "2.2" -> dataFormPage.getRadioButton22().click();
            case "2.3" -> dataFormPage.getRadioButton23().click();
        }
        return this;
    }

    public DataFormPageSteps clickSubmit() {
        dataFormPage.getSubmitButton().click();
        return this;
    }

    public DataFormPageSteps confirmModal() {
        dataFormPage.getModalOkButton().shouldBe(visible).click();
        return this;
    }

    public DataFormPageSteps verifyLastRowMatchesEntry() {
        assertFalse(dataFormPage.getTableRows().isEmpty(), "Таблица не должна быть пустой");
        List<String> row = new ArrayList<>();
        for (SelenideElement cell : dataFormPage.getTableRows().last().$$("td").asFixedIterable()) {
            row.add(cell.getText());
        }
        assertEquals(currentEntry.getEmail(), row.get(0), "Email");
        assertEquals(currentEntry.getName(), row.get(1), "Имя");
        assertEquals(currentEntry.getGender(), row.get(2), "Пол");
        assertEquals(currentEntry.getChoice1(), row.get(3), "Выбор 1");
        assertEquals(currentEntry.getChoice2(), row.get(4), "Выбор 2");
        return this;
    }

    public DataFormPageSteps verifyTableRowCount(int expected) {
        assertEquals(expected, dataFormPage.getTableRows().size(),
                "Количество строк в таблице должно быть " + expected);
        return this;
    }

    public DataFormPageSteps emailFormatErrorIsDisplayed() {
        assertTrue(dataFormPage.getEmailFormatError().isDisplayed(),
                "Ошибка формата email должна отобразиться");
        return this;
    }

    public DataFormPageSteps blankNameErrorIsDisplayed() {
        assertTrue(dataFormPage.getBlankNameError().isDisplayed(),
                "Ошибка пустого имени должна отобразиться");
        return this;
    }

    private void toggleCheckbox(SelenideElement checkbox, boolean shouldBeChecked) {
        if (checkbox.isSelected() != shouldBeChecked) {
            checkbox.click();
        }
    }
}