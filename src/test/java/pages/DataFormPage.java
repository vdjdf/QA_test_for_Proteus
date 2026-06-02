package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class DataFormPage {

    private final SelenideElement emailInput = $("#dataEmail");
    private final SelenideElement nameInput = $("#dataName");
    private final SelenideElement genderSelect = $("#dataGender");
    private final SelenideElement checkbox11 = $("#dataCheck11");
    private final SelenideElement checkbox12 = $("#dataCheck12");
    private final SelenideElement radioButton21 = $("#dataSelect21");
    private final SelenideElement radioButton22 = $("#dataSelect22");
    private final SelenideElement radioButton23 = $("#dataSelect23");
    private final SelenideElement submitButton = $("#dataSend");
    private final SelenideElement emailFormatError = $("#emailFormatError");
    private final SelenideElement blankNameError = $("#blankNameError");
    private final SelenideElement modalOkButton
            = $(By.xpath("//div[contains(@class, 'uk-modal')]//button[contains(@class, 'uk-button')]"));
    private final ElementsCollection tableRows = $$(By.xpath("//tbody//tr"));
}