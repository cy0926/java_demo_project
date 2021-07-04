package com.result.assured.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTestDemo {
    public static String access_token;
    @BeforeAll
    public static void getMethod(){
        access_token = given()
                .params("corpid","ww14285fddfd1a18e5","corpsecret","iHxWY9TqrqODxMV9y_a6jtNyuxyeUWbeQFxTsp9iHJw")
                .get(" https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .statusCode(200)
                .log().all().extract().response().path("access_token");
        System.out.println(access_token);
    }

    @Test
    void postMethod(){
        given()
                .contentType("application/json;charset=utf-8")
                .body("{\n" +
                        "   \"touser\" : \"@all\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000003,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"这只是一个测试的通知。\\n可以点击链接查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
                        "   },\n" +
                        "}")
                .queryParam("access_token",access_token)
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send")
                .then()
                .log().all();
    }
}
