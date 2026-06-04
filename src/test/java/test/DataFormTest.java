package test;

import hooks.Hooks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import steps.DataFormPageSteps;

public class DataFormTest extends Hooks {

    private static final String LOGIN_EMAIL    = "test@protei.ru";
    private static final String LOGIN_PASSWORD = "test";
    private static final String VALID_EMAIL    = "user@test.ru";
    private static final String VALID_NAME     = "Иван";
    private static final String GENDER_MALE    = "Мужской";
    private static final String GENDER_FEMALE  = "Женский";
    private static final String RADIO_2_1      = "2.1";
    private static final String RADIO_2_2      = "2.2";
    private static final String RADIO_2_3      = "2.3";

    private final DataFormPageSteps formSteps = new DataFormPageSteps();

    @Test
    void submitWithMinimalData() {
        formSteps
                .login(LOGIN_EMAIL, LOGIN_PASSWORD)
                .enterEmail(VALID_EMAIL)
                .enterName(VALID_NAME)
                .clickSubmit()
                .confirmModal()
                .verifyLastRowMatchesEntry();
    }

    @Test
    void genderFemaleIsSaved() {
        formSteps
                .login(LOGIN_EMAIL, LOGIN_PASSWORD)
                .enterEmail(VALID_EMAIL)
                .enterName("Мария")
                .selectGender(GENDER_FEMALE)
                .clickSubmit()
                .confirmModal()
                .verifyLastRowMatchesEntry();
    }

    @Test
    void genderMaleIsSaved() {
        formSteps
                .login(LOGIN_EMAIL, LOGIN_PASSWORD)
                .enterEmail(VALID_EMAIL)
                .enterName(VALID_NAME)
                .selectGender(GENDER_MALE)
                .clickSubmit()
                .confirmModal()
                .verifyLastRowMatchesEntry();
    }

    @Test
    void bothCheckboxesChecked() {
        formSteps
                .login(LOGIN_EMAIL, LOGIN_PASSWORD)
                .enterEmail(VALID_EMAIL)
                .enterName("Петр")
                .checkOption11()
                .checkOption12()
                .clickSubmit()
                .confirmModal()
                .verifyLastRowMatchesEntry();
    }

    @Test
    void onlyCheckbox11Checked() {
        formSteps
                .login(LOGIN_EMAIL, LOGIN_PASSWORD)
                .enterEmail(VALID_EMAIL)
                .enterName("Сергей")
                .checkOption11()
                .clickSubmit()
                .confirmModal()
                .verifyLastRowMatchesEntry();
    }

    @Test
    void noCheckboxesChecked() {
        formSteps
                .login(LOGIN_EMAIL, LOGIN_PASSWORD)
                .enterEmail(VALID_EMAIL)
                .enterName("Олег")
                .clickSubmit()
                .confirmModal()
                .verifyLastRowMatchesEntry();
    }

    @ParameterizedTest
    @ValueSource(strings = {RADIO_2_1, RADIO_2_2, RADIO_2_3})
    void allRadioOptions(String radio) {
        formSteps
                .login(LOGIN_EMAIL, LOGIN_PASSWORD)
                .enterEmail(VALID_EMAIL)
                .enterName("Тест")
                .selectRadio(radio)
                .clickSubmit()
                .confirmModal()
                .verifyLastRowMatchesEntry();
    }

    @Test
    void multipleRowsAddedToTable() {
        formSteps
                .login(LOGIN_EMAIL, LOGIN_PASSWORD)
                .enterEmail("first@test.ru")
                .enterName("Первый")
                .clickSubmit()
                .confirmModal()
                .enterEmail("second@test.ru")
                .enterName("Второй")
                .clickSubmit()
                .confirmModal()
                .verifyTableRowCount(2);
    }

    @Test
    void fiveParticipantsAddedWithVerification() {
        formSteps
                .login(LOGIN_EMAIL, LOGIN_PASSWORD)
                .enterEmail("alpha@test.ru")
                .enterName("Алексей")
                .selectGender(GENDER_MALE)
                .checkOption11()
                .clickSubmit()
                .confirmModal()
                .verifyLastRowMatchesEntry()

                .enterEmail("beta@test.ru")
                .enterName("Мария")
                .selectGender(GENDER_FEMALE)
                .resetCheckboxes()
                .checkOption12()
                .selectRadio(RADIO_2_2)
                .clickSubmit()
                .confirmModal()
                .verifyLastRowMatchesEntry()

                .enterEmail("gamma@test.ru")
                .enterName("Сергей")
                .selectGender(GENDER_MALE)
                .resetCheckboxes()
                .checkOption11()
                .checkOption12()
                .selectRadio(RADIO_2_3)
                .clickSubmit()
                .confirmModal()
                .verifyLastRowMatchesEntry()

                .enterEmail("delta@test.ru")
                .enterName("Анна")
                .selectGender(GENDER_FEMALE)
                .resetCheckboxes()
                .selectRadio(RADIO_2_1)
                .clickSubmit()
                .confirmModal()
                .verifyLastRowMatchesEntry()

                .enterEmail("epsilon@test.ru")
                .enterName("Петр")
                .selectGender(GENDER_MALE)
                .resetCheckboxes()
                .selectRadio(RADIO_2_1)
                .clickSubmit()
                .confirmModal()
                .verifyLastRowMatchesEntry()

                .verifyTableRowCount(5);
    }


    @ParameterizedTest
    @ValueSource(strings = {"notvalid", "-user@test.ru", "user@", "@test.ru"})
    void invalidEmailFormat(String invalidEmail) {
        formSteps
                .login(LOGIN_EMAIL, LOGIN_PASSWORD)
                .enterEmail(invalidEmail)
                .enterName(VALID_NAME)
                .clickSubmit()
                .emailFormatErrorIsDisplayed();
    }

    @Test
    void blankNameShowsError() {
        formSteps
                .login(LOGIN_EMAIL, LOGIN_PASSWORD)
                .enterEmail(VALID_EMAIL)
                .enterName("")
                .clickSubmit()
                .blankNameErrorIsDisplayed();
    }
}
