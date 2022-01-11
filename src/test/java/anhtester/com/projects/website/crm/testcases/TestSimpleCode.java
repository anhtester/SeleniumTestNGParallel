package anhtester.com.projects.website.crm.testcases;

import anhtester.com.helpers.ExcelHelpers;
import anhtester.com.helpers.Helpers;
import anhtester.com.helpers.TxtFileHelpers;
import anhtester.com.helpers.Props;
import anhtester.com.helpers.DatabaseHelpers;
import anhtester.com.utils.Log;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//@Listeners(TestListener.class)
public class TestSimpleCode {

    @Test
    public void testGetCurrentDirectory() {
        System.out.println(Helpers.getCurrentDir());

        Log.warn("testGetCurrentDirectory");
    }

    @Test
    public void testGetPropertiesFile() {
        //Props.setFile("src/test/resources/DataTest.properties");
        Props.loadAllFiles();
        System.out.println(Props.getValue("hongthai"));

        Props.setFile("src/test/resources/DataTest.properties");
        Props.setValue("abc", "AN123");

        Log.info("testGetPropertiesFile");
    }

    @Test
    public void testSplitString() {
        String s1 = "Automation, Testing, Selenium, Java";

        for (String arr : Helpers.splitString(s1, ", ")) {
            System.out.println(arr);
        }

        Log.info("testSplitString");
    }

    @Test
    public void testEncryptDecryptData() {
        String pass = "123456";

        //Encrypt password
        System.out.println(Helpers.encrypt(pass));
        //Decrypt password
        System.out.println(Helpers.decrypt(Helpers.encrypt(pass)));
    }

    @Test
    public void testCreateFolder() {
        Helpers.CreateFolder("src/test/resources/TestCreateNewFolder");
    }

    @Test
    public void TestPropertiesFile() {
        //  Handle Properties file
        Helpers.logConsole(Props.getValue("browser"));
        Helpers.logConsole(Props.getValue("url"));
        Helpers.logConsole(Props.getValue("author"));
        Helpers.logConsole(Props.getValue("reportName"));
        Props.setFile("src/test/resources/DataTest.properties");
        Props.setValue("base.url", "https://anhtetser.com");
    }

    @Test
    public void testGetCurrentDateTime() {
        Helpers.logConsole(Helpers.CurrentDateTime());
        //Log.info(Helpers.CurrentDateTime());
    }

    @Test
    public void TestReadAndWriteTxtFile() {

        TxtFileHelpers.ReadTxtFile(Props.getValue("txtFilePath"));
    }

    @Test
    public void TestExcelFile() {
        //  Handle Excel file
        ExcelHelpers.setExcelFile(Props.getValue("excelSignIn"), "Sheet1");
        System.out.println(ExcelHelpers.getCellData("username", 2));
        System.out.println(ExcelHelpers.getCellData("password", 2));
        System.out.println(ExcelHelpers.getCellData("pin", 2));
        ExcelHelpers.setCellData("pass", 1, 3);
    }

    @Test()
    public void testExcelFile() throws Exception {

        ExcelHelpers.getDataArray("src/test/resources/Magento.xlsx", "storeProduct", 0, 2);
    }

    @Test
    public void connectDBMySQL() throws SQLException, ClassNotFoundException {
        //Này connect DB mẫu Free. Các bạn dùng thằng khác thì đổi thông tin connect mẫu bên dưới là được.
//        https://www.phpmyadmin.co/
//        Host: sql6.freesqldatabase.com
//        Database name: sql6464696
//        Database user: sql6464696
//        Database password: LIAGIkgd44
//        Port number: 3306

        Connection connection = DatabaseHelpers.getMySQLConnection("sql6.freesqldatabase.com", "sql6464696", "sql6464696", "LIAGIkgd44");

        // Tạo đối tượng Statement.
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM `company`";

        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
        ResultSet rs = statement.executeQuery(sql);

        Helpers.logConsole(rs);

        // Duyệt trên kết quả trả về.
        while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
            int Id = rs.getInt(1);
            String COMPANY_ID = rs.getString("COMPANY_ID");
            String COMPANY_NAME = rs.getString("COMPANY_NAME");
            String COMPANY_CITY = rs.getString("COMPANY_CITY");
            System.out.println("--------------------");
            System.out.println("COMPANY_ID:" + COMPANY_ID);
            System.out.println("COMPANY_NAME:" + COMPANY_NAME);
            System.out.println("COMPANY_CITY:" + COMPANY_CITY);
        }

        // Đóng kết nối
        connection.close();
    }

}
