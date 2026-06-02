package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import model.FormEntry;
import org.junit.Assert;
import pages.DataFormPage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;

public class DataFormPageSteps {

    private final LoginPage loginPage = new LoginPage();
    private final DataFormPage dataFormPage = new DataFormPage();
    private FormEntry currentEntry;

    @Дано("пользователь авторизован")
    public void userIsLoggedIn() {
        loginPage.getEmailInput().shouldBe(visible).clear();
        loginPage.getEmailInput().sendKeys("test@protei.ru");
        loginPage.getPasswordInput().shouldBe(visible).clear();
        loginPage.getPasswordInput().sendKeys("test");
        loginPage.getAuthButton().click();
    }

    @Когда("вводим email {string}")
    public void enterEmail(String email) {
        currentEntry = new FormEntry();
        currentEntry.setEmail(email);
        dataFormPage.getEmailInput().shouldBe(visible).clear();
        dataFormPage.getEmailInput().sendKeys(email);
    }

    @Когда("вводим имя {string}")
    public void enterName(String name) {
        currentEntry.setName(name);
        dataFormPage.getNameInput().shouldBe(visible).clear();
        dataFormPage.getNameInput().sendKeys(name);
    }

    @И("выбираем пол (Мужской|Женский)?$")
    public void selectGender(String gender) {
        currentEntry.setGender(gender);
        dataFormPage.getGenderSelect().selectOptionByValue(
                gender.equals("Мужской") ? "Мужской" : "Женский"
        );
    }

    @И("отмечаем чекбокс 1.1")
    public void checkOption11() {
        currentEntry.setCheck11(true);
        toggleCheckbox(dataFormPage.getCheckbox11(), true);
    }

    @И("отмечаем чекбокс 1.2")
    public void checkOption12() {
        currentEntry.setCheck12(true);
        toggleCheckbox(dataFormPage.getCheckbox12(), true);
    }

    @И("выбираем радиокнопку {string}")
    public void selectRadioOption(String option) {
        currentEntry.setChoice2(option);

        switch (option) {
            case "2.1":
                dataFormPage.getRadioButton21().click();
                break;
            case "2.2":
                dataFormPage.getRadioButton22().click();
                break;
            case "2.3":
                dataFormPage.getRadioButton23().click();
                break;
        }
    }

    @И("нажимаем Добавить")
    public void clickSubmit() {
        dataFormPage.getSubmitButton().click();
    }

    @И("подтверждаем модальное окно")
    public void confirmModal() {
        dataFormPage.getModalOkButton().shouldBe(visible).click();
    }

    @И("отжали чекбоксы 1.1 и 1.2")
    public void resetCheckboxes() {
        toggleCheckbox(dataFormPage.getCheckbox11(), false);
        toggleCheckbox(dataFormPage.getCheckbox12(), false);
    }

    @Тогда("запись корректно отображается в таблице")
    public void verifyLastRowMatchesEntry() {
        Assert.assertFalse("Таблица не должна быть пустой", dataFormPage.getTableRows().isEmpty());
        List<String> row = new ArrayList<>();
        for (SelenideElement cell : dataFormPage.getTableRows().last().$$("td").asFixedIterable()) {
            row.add(cell.getText());
        }

        Assert.assertEquals("Email",   currentEntry.getEmail(), row.get(0));
        Assert.assertEquals("Имя",     currentEntry.getName(), row.get(1));
        Assert.assertEquals("Пол",     currentEntry.getGender(), row.get(2));
        Assert.assertEquals("Выбор 1", currentEntry.getChoice1(), row.get(3));
        Assert.assertEquals("Выбор 2", currentEntry.getChoice2(), row.get(4));
    }

    @Тогда("в таблице {int} строк")
    public void tableHasRowCount(int expectedCount) {
        Assert.assertEquals(
                "Количество строк в таблице должно быть " + expectedCount,
                expectedCount, dataFormPage.getTableRows().size());
    }

    @Тогда("отображается ошибка формата email в форме анкеты")
    public void emailFormatErrorIsDisplayedInDataForm() {
        Assert.assertTrue("Ошибка формата email должна отобразиться",
                dataFormPage.getEmailFormatError().isDisplayed());
    }

    @Тогда("отображается ошибка пустого имени")
    public void blankNameErrorIsDisplayed() {
        Assert.assertTrue("Ошибка пустого имени должна отобразиться",
                dataFormPage.getBlankNameError().isDisplayed());
    }

    private void toggleCheckbox(SelenideElement checkbox, boolean shouldBeChecked) {
        if (checkbox.isSelected() != shouldBeChecked) {
            checkbox.click();
        }
    }
}