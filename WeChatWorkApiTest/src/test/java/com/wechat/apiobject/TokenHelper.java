package com.wechat.apiobject;

import static io.restassured.RestAssured.given;

public class TokenHelper {
    public static String getAccessToken(){
       String accessToken = given().log().all()
                .when()
                .param("corpid","ww14285fddfd1a18e5")
                .param("corpsecret","dDLrfeDNbFFDDyM5NBOk8fuGEGyoWJ50VtCfiJhpsw4")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all()
                .extract().response().path("access_token");
       return accessToken;
    }
}
