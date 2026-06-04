package steps;

import pages.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageSteps {

    private final LoginPage loginPage = new LoginPage();

    public LoginPageSteps enterEmail(String email) {
        loginPage.getEmailInput().shouldBe(visible).clear();
        loginPage.getEmailInput().sendKeys(email);
        return this;
    }

    public LoginPageSteps enterPassword(String password) {
        loginPage.getPasswordInput().shouldBe(visible).clear();
        loginPage.getPasswordInput().sendKeys(password);
        return this;
    }

    public LoginPageSteps clickLogin() {
        loginPage.getAuthButton().click();
        return this;
    }

    public LoginPageSteps emailFormatErrorIsDisplayed() {
        assertTrue(loginPage.getEmailFormatError().isDisplayed(),
                "Ошибка формата email не отобразилась");
        return this;
    }

    public LoginPageSteps emailFormatErrorTextIsCorrect() {
        assertEquals(loginPage.getErrorTextEmailFormat(),
                loginPage.getEmailFormatError().getText(),
                "Текст ошибки формата email не совпал с ожидаемым");
        return this;
    }

    public LoginPageSteps invalidCredentialsErrorIsDisplayed() {
        assertTrue(loginPage.getInvalidCredError().isDisplayed(),
                "Ошибка неверных учётных данных не отобразилась");
        return this;
    }

    public LoginPageSteps invalidCredentialsErrorTextIsCorrect() {
        assertEquals(loginPage.getErrorTextInvalidCredentials(),
                loginPage.getInvalidCredError().getText(),
                "Текст ошибки неверных учётных данных не совпал с ожидаемым");
        return this;
    }
}