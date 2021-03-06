package tests;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DemowebshopTests {

    @Test
    void addToCartAsNewUserTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your " +
                        "\u003ca href=\"/cart\"\u003eshopping cart\u003c/a\u003e"))
                .body("updatetopcartsectionhtml", is("(1)"));
    }

    @Test
    void addToCartWithCookieTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer=4a270881-47e7-42c9-a179-438c242fa564; " +
                        "ARRAffinity=1818b4c81d905377ced20e7ae987703a674897394db6e97dc1316168f754a687")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your " +
                        "\u003ca href=\"/cart\"\u003eshopping cart\u003c/a\u003e"))
                .body("updatetopcartsectionhtml", is("(8)"));
    }

    @Test
    void addToCartTestWithInt() {
        Integer cartSize = 0;

        ValidatableResponse response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .cookie("Nop.customer=4a270881-47e7-42c9-a179-438c242fa564; " +
                                "ARRAffinity=1818b4c81d905377ced20e7ae987703a674897394db6e97dc1316168f754a687")
                        .body("product_attribute_72_5_18=53" +
                                "&product_attribute_72_6_19=54" +
                                "&product_attribute_72_3_20=57" +
                                "&addtocart_72.EnteredQuantity=1")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your " +
                                "\u003ca href=\"/cart\"\u003eshopping cart\u003c/a\u003e"));

//        to do
//        assertThat(response.extract().path("updatetopcartsectionhtml").toString());
//                .body("updatetopcartsectionhtml", is("(8)"));
    }

}
