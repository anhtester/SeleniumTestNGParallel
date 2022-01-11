package anhtester.com.projects.website.crm.pages.Clients;

import anhtester.com.driver.DriverManager;
import anhtester.com.helpers.ExcelHelpers;
import anhtester.com.utils.WebUI;
import anhtester.com.helpers.Props;
import anhtester.com.projects.website.crm.pages.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClientPage extends CommonPage {

    public ClientPage() {
    }

    public String pageText = "Clients";
    public String pageUrl = "/clients";

    //Client Element
    private By clientTab = By.xpath("//ul[@id='client-tabs']//li[2]");
    private By addClientBtn = By.xpath("//a[normalize-space()='Add client']");
    private By companyNameInput = By.xpath("//input[@id='company_name']");
    private By ownerSelect = By.xpath("//div[@id='s2id_created_by']");
    private By ownerSearchInput = By.xpath("//div[@id='select2-drop']//input");
    private By ownerFirstItemSelect = By.xpath("(//div[contains(@id,'select2-result-label')])[1]");
    private By addressInput = By.xpath("//textarea[@id='address']");
    private By cityInput = By.xpath("//input[@id='city']");
    private By stateInput = By.xpath("//input[@id='state']");
    private By zipInput = By.xpath("//input[@id='zip']");
    private By countryInput = By.xpath("//input[@id='country']");
    private By phoneInput = By.xpath("//input[@id='phone']");


    public void openClientTabPage() {
        webUI.clickElement(clientTab);
    }

    public void addClient() {
        ExcelHelpers.setExcelFile(Props.getValue("excelClients"), "AddClient");

        webUI.clickElement(addClientBtn);
        webUI.setText(companyNameInput, ExcelHelpers.getCellData("company_name", 2));
        webUI.clickElement(ownerSelect);
        webUI.setText(ownerSearchInput, ExcelHelpers.getCellData("owner", 1));
        webUI.clickElement(ownerFirstItemSelect);
        webUI.setText(addressInput, ExcelHelpers.getCellData("address", 1));
        webUI.setText(cityInput, ExcelHelpers.getCellData("city", 1));
        webUI.setText(stateInput, ExcelHelpers.getCellData("state", 1));
        webUI.setText(zipInput, ExcelHelpers.getCellData("zip", 1));
        webUI.setText(countryInput, ExcelHelpers.getCellData("country", 1));
        webUI.setText(phoneInput, ExcelHelpers.getCellData("phone", 1));
//        validateuiHelpers.clickElement(closeDialogBtn);
        webUI.clickElement(saveDialogBtn);
        webUI.setText(searchInput, ExcelHelpers.getCellData("company_name", 2));
        webUI.checkContainsSearchTableByColumn(2, ExcelHelpers.getCellData("company_name", 2));
    }

    public void enterDataSearchClient(String value) {
        webUI.clearText(searchInput);
        webUI.setText(searchInput, value);
    }

}
