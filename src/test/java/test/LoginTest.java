package test;

import hooks.Hooks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import steps.LoginPageSteps;

public class LoginTest extends Hooks {

    private static final String VALID_EMAIL    = "test@protei.ru";
    private static final String VALID_PASSWORD = "test";
    private static final String WRONG_PASSWORD = "wrongpassword";
    private static final String WRONG_EMAIL    = "wrong@protei.ru";

    private final LoginPageSteps loginSteps = new LoginPageSteps();

    @Test
    void successfulLogin() {
        loginSteps
                .enterEmail(VALID_EMAIL)
                .enterPassword(VALID_PASSWORD)
                .clickLogin();
    }

    @ParameterizedTest
    @ValueSource(strings = {"notanemail", "-user@protei.ru", "user@", "@protei.ru"})
    void invalidEmailFormat(String invalidEmail) {
        loginSteps
                .enterEmail(invalidEmail)
                .enterPassword(VALID_PASSWORD)
                .clickLogin()
                .emailFormatErrorIsDisplayed()
                .emailFormatErrorTextIsCorrect();
    }

    @Test
    void wrongPassword() {
        loginSteps
                .enterEmail(VALID_EMAIL)
                .enterPassword(WRONG_PASSWORD)
                .clickLogin()
                .invalidCredentialsErrorIsDisplayed()
                .invalidCredentialsErrorTextIsCorrect();
    }

    @Test
    void wrongEmail() {
        loginSteps
                .enterEmail(WRONG_EMAIL)
                .enterPassword(VALID_PASSWORD)
                .clickLogin()
                .invalidCredentialsErrorIsDisplayed()
                .invalidCredentialsErrorTextIsCorrect();
    }

    @Test
    void emptyEmailAndPassword() {
        loginSteps
                .enterEmail("")
                .enterPassword("")
                .clickLogin()
                .emailFormatErrorIsDisplayed()
                .emailFormatErrorTextIsCorrect();
    }
}
