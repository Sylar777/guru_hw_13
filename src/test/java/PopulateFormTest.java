import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static pages.data.ProvidedTestData.*;

public class PopulateFormTest extends BaseTest {

    @Test
    void populateFormWithCorrectDataTest() {
        formPage.populateFirstLastName(firstName, lastName);
        formPage.setUserEmailInput(userEmail);
        formPage.setGenderMale();
        formPage.setUserNumberInput(userNumber);
        formPage.setDateOfBirth(day, month, year);
        formPage.setSubjects(subjectFirst);
        formPage.setSubjects(subjectSecond);
        formPage.setHobbies(hobby);
        formPage.uploadPicture(fileName);
        formPage.setCurrentAddressInput(currentAddress);
        formPage.setStateAndCity(state, city);
        formPage.submitForm();

        formPage.checkTableData();
    }

    @Test
    void setFormWithOutProvidedDataTest() {
        formPage.submitForm();

        formPage.tableResponsiveComponent.tableResponsive.shouldNotBe(visible);
        formPage.checkFieldsRequirements();
    }

    @Test
    void setIncorrectDataTest() {
        formPage.populateFirstLastName(firstName, lastName);
        formPage.setGenderMale();
        formPage.setUserNumberInput(incorrectUserNumber);
        formPage.submitForm();

        formPage.tableResponsiveComponent.tableResponsive.shouldNotBe(visible);
    }
}
