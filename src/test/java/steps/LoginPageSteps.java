package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import pages.LoginPage;

import static com.codeborne.selenide.Condition.visible;

public class LoginPageSteps {

    private final LoginPage loginPage = new LoginPage();

    @Когда("вводим email {string} в форме анкеты")
    public void enterEmail(String email) {
        loginPage.getEmailInput().shouldBe(visible).clear();
        loginPage.getEmailInput().sendKeys(email);
    }

    @Когда("вводим пароль {string}")
    public void enterPassword(String password) {
        loginPage.getPasswordInput().shouldBe(visible).clear();
        loginPage.getPasswordInput().sendKeys(password);
    }

    @И("нажимаем кнопку Вход")
    public void clickLoginButton() {
        loginPage.getAuthButton().click();
    }

    @Тогда("отображается ошибка формата email")
    public void emailFormatErrorIsDisplayed() {
        Assert.assertTrue("ошибка формата email не отобразилась",
                loginPage.getEmailFormatError().isDisplayed());
    }

    @Тогда("отображается текст ошибки формата email")
    public void emailFormatErrorIsDisplayedText() {
        Assert.assertEquals("Текст ошибки формата email не совпал с ожидаемым",
                loginPage.getErrorTextEmailFormat(),
                loginPage.getEmailFormatError().getText());
    }

    @Тогда("отображается ошибка неверных учётных данных")
    public void invalidCredentialsErrorIsDisplayed() {
        Assert.assertTrue("ошибка неверных учётных данных не отобразилась",
                loginPage.getInvalidCredError().isDisplayed());
    }

    @Тогда("отображается текст ошибки неверных учётных данных")
    public void invalidCredentialsErrorIsDisplayedText() {
        Assert.assertEquals("Текст ошибки неверных учётных данных не совпал с ожидаемым",
                loginPage.getErrorTextInvalidCredentials(),
                loginPage.getInvalidCredError().getText());
    }
}