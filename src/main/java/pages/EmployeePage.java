package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class EmployeePage extends BasePage {

    private WebDriverWait wait;
    private WebDriverWait longWait;

    public EmployeePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        longWait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    // ================= ADD ELEMENTS =================

    @FindBy(id = "button-add-employee")
    private WebElement addBtn;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "employeeId")
    private WebElement empId;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "phoneNumber")
    private WebElement phone;

    @FindBy(id = "employeeRole")
    private WebElement role;

    @FindBy(id = "button-add-employee-submit")
    private WebElement submit;

    // ================= SEARCH =================

    @FindBy(xpath = "//input[@placeholder='Search name, e-mail, phone...']")
    private WebElement search;

    // ================= EDIT ELEMENTS =================

    @FindBy(id = "edit-employee-name-input")
    private WebElement editName;

    @FindBy(id = "edit-employee-employee-id-input")
    private WebElement editEmpId;

    @FindBy(id = "edit-employee-email-input")
    private WebElement editEmail;

    @FindBy(id = "edit-employee-phone-number-input")
    private WebElement editPhone;

    @FindBy(id = "edit-employee-employee-role-input")
    private WebElement editRole;

    @FindBy(id = "edit-employee-save-changes-button")
    private WebElement saveEdit;

    // ================= DELETE =================

    @FindBy(id = "delete-employee-button")
    private WebElement deleteBtn;

    @FindBy(id = "confirm-delete-button")
    private WebElement confirmDelete;

    // ================= LOCATORS =================

    private By row(String nameVal) {
        return By.xpath("//tbody//tr[td[contains(.,'" + nameVal + "')]]");
    }

    private By detailBtn(String nameVal) {
        return By.xpath("//tbody//tr[td[contains(.,'" + nameVal + "')]]//button[contains(.,'Detail')]");
    }

    private By editBtn() {
        return By.xpath("//button[contains(.,'Edit')]");
    }

    private By modal() {
        return By.cssSelector(".chakra-modal__content, .chakra-modal");
    }

    // ================= HELPERS =================

    private void type(WebElement el, String val) {
        wait.until(ExpectedConditions.visibilityOf(el));
        el.click();
        el.sendKeys(Keys.CONTROL + "a");
        el.sendKeys(Keys.DELETE);
        el.sendKeys(val);
    }

    private void closeModal() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(modal()));
        } catch (Exception ignored) {}
    }

    public void goToEmployeeList() {
        driver.get("https://YOUR_BASE_URL/admin/employee");
    }

    // ================= ADD =================

    public void openAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }

    public void addEmployee(String n, String id, String e,
                            String phoneVal, String roleVal, String programVal) {

        openAdd();

        type(name, n);
        type(empId, id);
        type(email, e);
        type(phone, phoneVal);

        // role FIX (WAJIB)
        type(role, roleVal);

        selectProgram(programVal);

        wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
        closeModal();
    }

    // ================= EDIT =================

    public void openEdit(String nameVal) {

        searchEmployee(nameVal);

        wait.until(ExpectedConditions.elementToBeClickable(detailBtn(nameVal))).click();
        wait.until(ExpectedConditions.elementToBeClickable(editBtn())).click();
        wait.until(ExpectedConditions.visibilityOf(editName));
    }

    public void editEmployee(String oldName,
                             String newName,
                             String newId,
                             String newEmail,
                             String newPhone,
                             String newRole,
                             String newProgram) {

        openEdit(oldName);

        type(editName, newName);
        type(editEmpId, newId);
        type(editEmail, newEmail);
        type(editPhone, newPhone);
        type(editRole, newRole);

        selectProgram(newProgram);

        wait.until(ExpectedConditions.elementToBeClickable(saveEdit)).click();
        closeModal();
    }

    // ================= DELETE =================

    public void deleteEmployee(String nameVal) {

        searchEmployee(nameVal);

        wait.until(ExpectedConditions.elementToBeClickable(detailBtn(nameVal))).click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDelete)).click();
    }

    // ================= SEARCH =================

    public void searchEmployee(String nameVal) {

        WebElement s = wait.until(ExpectedConditions.elementToBeClickable(search));

        s.click();
        s.sendKeys(Keys.CONTROL + "a");
        s.sendKeys(Keys.DELETE);
        s.sendKeys(nameVal);
    }

    // ================= VERIFY =================

    public void verifyEmployeeAdded(String nameVal) {
        searchEmployee(nameVal);
        longWait.until(ExpectedConditions.visibilityOfElementLocated(row(nameVal)));
    }

    public void verifyEmployeeDeleted(String nameVal) {
        searchEmployee(nameVal);
        longWait.until(ExpectedConditions.invisibilityOfElementLocated(row(nameVal)));
    }

    // ================= PROGRAM DROPDOWN =================

    public void selectProgram(String value) {

        WebElement dd = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[contains(@id,'division') or contains(.,'Program')]")
                )
        );

        dd.click();

        By opt = By.xpath(
                "//*[contains(@id,'select-option') or @role='option'][contains(.,'" + value + "')]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(opt)).click();
    }
}