package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class LoginPage {

    private final SelenideElement emailInput = $("#loginEmail");
    private final SelenideElement passwordInput = $("#loginPassword");
    private final SelenideElement authButton = $("#authButton");
    private final SelenideElement emailFormatError = $("#emailFormatError");
    private final SelenideElement invalidCredError = $("#invalidEmailPassword");

    private final String errorTextEmailFormat = "Неверный формат E-Mail";
    private final String errorTextInvalidCredentials = "Неверный E-Mail или пароль";
}