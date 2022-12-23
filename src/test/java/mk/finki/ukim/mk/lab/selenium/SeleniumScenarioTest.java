//package mk.finki.ukim.mk.lab.selenium;
//
//import mk.finki.ukim.mk.lab.model.Manufacturer;
//import mk.finki.ukim.mk.lab.model.Role;
//import mk.finki.ukim.mk.lab.model.User;
//import mk.finki.ukim.mk.lab.model.UserFullName;
//import mk.finki.ukim.mk.lab.service.BalloonService;
//import mk.finki.ukim.mk.lab.service.ManufacturerService;
//import mk.finki.ukim.mk.lab.service.UserService;
//import org.junit.Test;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//@ActiveProfiles("test")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//public class SeleniumScenarioTest {
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    ManufacturerService manufacturerService;
//
//    @Autowired
//    BalloonService balloonService;
//
//
//    private HtmlUnitDriver driver;
//
////    private static Category c1;
////    private static Category c2;
//    private static Manufacturer m1;
//    private static Manufacturer m2;
//    private static User regularUser;
//    private static User adminUser;
//
//    private static String user = "user";
//    private static String admin = "admin";
//
//    private static boolean dataInitialized = false;
//
//
//    @BeforeEach
//    private void setup() {
//        this.driver = new HtmlUnitDriver(true);
//        initData();
//    }
//
//    @AfterEach
//    public void destroy() {
//        if (this.driver != null) {
//            this.driver.close();
//        }
//    }
//
//
//    private void initData() {
//        if (!dataInitialized) {
//            m1 = manufacturerService.save("m1", "m1", "m1").get();
//            m2 = manufacturerService.save("m2", "m2", "m2").get();
//
//            UserFullName userFullName = new UserFullName(user, user);
//            UserFullName userFullName1 = new UserFullName(admin, admin);
//            regularUser = userService.register(user, user, user, userFullName, Role.ROLE_USER);
//            adminUser = userService.register(admin, admin, admin, userFullName1, Role.ROLE_ADMIN);
//            dataInitialized = true;
//        }
//    }
//
//    @Test
//    public void testScenario() throws Exception {
//        BalloonsPage productsPage = ProductsPage.to(this.driver);
//        productsPage.assertElemts(0, 0, 0, 0, 0);
//        LoginPage loginPage = LoginPage.openLogin(this.driver);
//
//        productsPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), admin);
//        productsPage.assertElemts(0, 0, 0, 0, 1);
//
//        productsPage = AddOrEditProduct.addProduct(this.driver, "test", "100", "5", c2.getName(), m2.getName());
//        productsPage.assertElemts(1, 1, 1, 1, 1);
//
//        productsPage = AddOrEditProduct.addProduct(this.driver, "test1", "200", "4", c1.getName(), m2.getName());
//        productsPage.assertElemts(2, 2, 2, 2, 1);
//
//        productsPage.getDeleteButtons().get(1).click();
//        productsPage.assertElemts(1, 1, 1, 1, 1);
//
//        productsPage = AddOrEditProduct.editProduct(this.driver, productsPage.getEditButtons().get(0), "test1", "200", "4", c1.getName(), m2.getName());
//        productsPage.assertElemts(1, 1, 1, 1, 1);
//
//    }
