package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class LoginPage {

    private final SelenideElement emailInput        = $("#loginEmail");
    private final SelenideElement passwordInput     = $("#loginPassword");
    private final SelenideElement authButton        = $("#authButton");
    private final SelenideElement emailFormatError  = $("#emailFormatError");
    private final SelenideElement invalidCredError = $("#invalidEmailPassword");

    private final  String errorTextEmailFormat = "Неверный формат E-Mail";
    public final static String ERROR_INVALID_CREDENTIALS = "Неверный E-Mail или пароль";

    public void enterEmail(String email) {
        emailInput.shouldBe(visible).clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        authButton.click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

}