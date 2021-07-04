package com.wechat.department;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *1、编写基础脚本，分别执行了创建，更新，查询，删除部门的操作
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_01_base {
    static String accessToken;
    static String departmentId;
    @BeforeAll
    public static void getAccessToken(){
        accessToken = given().log().all()
                .when()
                .param("corpid","ww14285fddfd1a18e5")
                .param("corpsecret","dDLrfeDNbFFDDyM5NBOk8fuGEGyoWJ50VtCfiJhpsw4")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all()
                .extract().response().path("access_token");
    }

    @Test
    @Order(1)
    @DisplayName("创建部门")
    void createDepartment(){
        String body = "{\n" +
                "   \"name\": \"重庆研发中心\",\n" +
                "   \"name_en\": \"CDBA\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}";
        Response response = given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken)
                .then().log().all()
                .extract()
                .response();
        departmentId = response.path("id").toString();
        System.out.println(departmentId);
    }
    @DisplayName("修改部门")
    @Test
    @Order(2)
    void updateDepartment(){
        String body = "{\n" +
                "   \"id\": 2,\n" +
                "   \"name\": "+departmentId+",\n" +
                "   \"name_en\": \"根部门ID1\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}";
        Response response = given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+accessToken)
                .then().log().all()
                .extract().response();
        assertEquals("0",response.path("errcode").toString());
    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    void listDepartment(){
        Response response = given().log().all()
                .when()
                .param("id",departmentId)
                .param("access_token", accessToken)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+accessToken)
                .then().log().all()
                .extract().response();
        assertEquals("0",response.path("errcode").toString());
        assertEquals(departmentId, response.path("department.id[0]").toString());
    }

    @DisplayName("删除部门")
    @Test
    @Order(4)
    void deleteDepartment(){
        Response response = given().log().all()
                .contentType("application/json")
                .param("access_token",accessToken)
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .extract().response();
        assertEquals("0", response.path("errcode").toString());
    }
}
