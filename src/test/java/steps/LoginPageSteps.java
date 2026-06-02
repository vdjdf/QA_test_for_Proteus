package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import pages.LoginPage;


public class LoginPageSteps {

    private final LoginPage loginPage = new LoginPage();

    @Дано("открыта страница авторизации")
    public void loginPageIsOpen() {
        // страница открыта в Hooks.setUp()
    }

    @Когда("вводим email {string} и пароль {string}")
    public void enterEmailAndPassword(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @И("нажимаем кнопку Вход")
    public void clickLoginButton() {
        loginPage.clickLogin();
    }

    @Тогда("отображается ошибка формата email")
    public void emailFormatErrorIsDisplayed() {
        Assert.assertTrue("ошибка формата email не отобразилась",
                loginPage.getEmailFormatError().isDisplayed());
    }
    @Тогда("отображается текст ошибки формата email")
    public void emailFormatErrorIsDisplayedTEXT() {
        Assert.assertEquals("Текст ошибки формата email не совпал с ожидаемым",
                loginPage.getErrorTextEmailFormat(),
                loginPage.getEmailFormatError().getText());
    }

    @Тогда("отображается ошибка неверных учётных данных")
    public void invalidCredentialsErrorIsDisplayed() {
        Assert.assertTrue("ошибка неверных учётных данных не отобразилась",
                loginPage.getInvalidCredError().isDisplayed());
    }
}