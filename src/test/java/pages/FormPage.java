package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.TableResponsiveComponent;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static pages.data.ProvidedTestData.*;

public class FormPage {
    //  Components
    public TableResponsiveComponent tableResponsiveComponent;
    public CalendarComponent calendarComponent;

    //  Input fields
    public final SelenideElement firstNameInput = $("#firstName");
    public final SelenideElement lastNameInput = $("#lastName");
    public final SelenideElement userEmailInput = $("#userEmail");
    public final SelenideElement userNumberInput = $("#userNumber");
    public final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    public final SelenideElement subjectsInput = $("#subjectsInput");
    public final SelenideElement uploadPicture = $("#uploadPicture");
    public final SelenideElement currentAddressInput = $("#currentAddress");
    public final SelenideElement statePickList = $("#state input");
    public final SelenideElement cityPickList = $("#city input");

    // Buttons
    public final SelenideElement genderMaleRadioButton = $("#genterWrapper input[value='Male']");
    public final SelenideElement genderFemaleRadioButton = $("#genterWrapper input[value='Female']");
    public final SelenideElement genderOtherRadioButton = $("#genterWrapper input[value='Other']");
    public final ElementsCollection hobbiesCheckboxes = $$("#hobbiesWrapper .custom-control-label");
    public final SelenideElement hobbiesSportsCheckbox = hobbiesCheckboxes.filterBy(text("Sports")).first();
    public final SelenideElement hobbiesReadingCheckbox = hobbiesCheckboxes.filterBy(text("Reading")).first();
    public final SelenideElement hobbiesMusicCheckbox = hobbiesCheckboxes.filterBy(text("Music")).first();
    public final SelenideElement submit = $("#submit");

    public FormPage() {
        this.tableResponsiveComponent = new TableResponsiveComponent();
        this.calendarComponent = new CalendarComponent();
    }

    /**
     * Remove elements from the page
     */
    @Step("Remove elements from the page")
    public void removeElements() {
        try {
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
            System.out.println("JavaScript executed successfully");
        } catch (Exception e) {
            System.err.println("Error executing JavaScript: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Step("Populate first and last name")
    public void populateFirstLastName(String firstName, String lastName) {
        firstNameInput.setValue(firstName);
        lastNameInput.setValue(lastName);
    }

    @Step("Set user number")
    public void setUserNumberInput(String number) {
        userNumberInput.setValue(number);
    }

    @Step("Set date of birth")
    public void setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);
    }

    @Step("Set subjects")
    public void setSubjects(String subject) {
        subjectsInput.setValue(subject).pressEnter();
    }

    @Step("Set hobbies")
    public void setHobbies(String hobby) {
        switch (hobby) {
            case "Sports":
                hobbiesSportsCheckbox.click();
                break;
            case "Reading":
                hobbiesReadingCheckbox.click();
                break;
            case "Music":
                hobbiesMusicCheckbox.click();
                break;
        }
    }

    @Step("Upload picture")
    public void uploadPicture(String fileName) {
        uploadPicture.uploadFromClasspath(fileName);
    }

    @Step("Set current address")
    public void setCurrentAddressInput(String address) {
        currentAddressInput.setValue(address);
    }

    @Step("Set user email")
    public void setUserEmailInput(String email) {
        userEmailInput.setValue(email);
    }

    /**
     * Set state and city
     *
     * @param state String of state
     * @param city  String of city
     */
    @Step("Set state and city")
    public void setStateAndCity(String state, String city) {
        statePickList.setValue(state).pressEnter();
        cityPickList.setValue(city).pressEnter();
    }

    /**
     * Click on label Male for selecting checkbox Male
     */
    @Step("Click on gender Male radio Button")
    public void setGenderMale() {
        genderMaleRadioButton.sibling(0).click();
    }

    /**
     * Click on label Female for selecting checkbox Female
     */
    @Step("Click on gender Female radio Button")
    public void setGenderFemale() {
        genderFemaleRadioButton.sibling(0).click();
    }

    /**
     * Click on label Other for selecting checkbox Other
     */
    @Step("Click on gender Other radio Button")
    public void setGenderOther() {
        genderOtherRadioButton.sibling(0).click();
    }

    @Step("Submit form")
    public void submitForm() {
        submit.click();
    }

    /**
     * Check fields requirements
     */
    @Step("Check fields requirements")
    public void checkFieldsRequirements() {
        //  Check that the fields are required
        firstNameInput.shouldHave(attribute("required"));
        lastNameInput.shouldHave(attribute("required"));
        genderMaleRadioButton.shouldHave(attribute("required"));
        genderFemaleRadioButton.shouldHave(attribute("required"));
        genderOtherRadioButton.shouldHave(attribute("required"));
        userNumberInput.shouldHave(attribute("required"));

        // Check that the fields are not required
        userEmailInput.shouldNotHave(attribute("required"));
        dateOfBirthInput.shouldNotHave(attribute("required"));
        subjectsInput.shouldNotHave(attribute("required"));
        hobbiesSportsCheckbox.shouldNotHave(attribute("required"));
        hobbiesReadingCheckbox.shouldNotHave(attribute("required"));
        hobbiesMusicCheckbox.shouldNotHave(attribute("required"));
        currentAddressInput.shouldNotHave(attribute("required"));
        statePickList.shouldNotHave(attribute("required"));
        cityPickList.shouldNotHave(attribute("required"));
    }

    @Step("Check that the table contains the following data")
    public void checkTableData() {
        tableResponsiveComponent.tableResponsive.shouldHave(text(firstName + " " + lastName));
        tableResponsiveComponent.tableResponsive.shouldHave(text(userEmail));
        tableResponsiveComponent.tableResponsive.shouldHave(text(gender));
        tableResponsiveComponent.tableResponsive.shouldHave(text(userNumber));
        tableResponsiveComponent.tableResponsive.shouldHave(text(day + " " + month + "," + year));
        tableResponsiveComponent.tableResponsive.shouldHave(text(subjectFirst + ", " + subjectSecond));
        tableResponsiveComponent.tableResponsive.shouldHave(text(hobby));
        tableResponsiveComponent.tableResponsive.shouldHave(text(fileName));
        tableResponsiveComponent.tableResponsive.shouldHave(text(currentAddress));
        tableResponsiveComponent.tableResponsive.shouldHave(text(state + " " + city));
    }
}
