package com.wechat.apiobject;

import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DepartmentObject {
    //获取accessToken
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

    //创建部门--name是动态传参的
    public static Response  createDepartment(String createName, String createEnName, String accessToken){
        String body = "{\n" +
                "   \"name\": \""+createName+"\",\n" +
                "   \"name_en\": \""+createEnName+"\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}";
        Response createResponse = given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken)
                .then().log().all()
                .extract()
                .response();
        return createResponse;
    }

    //创建部门-name是随机生成的
    public static String createDepartment(String accessToken){
        String createName = "name" + FakerUtils.getTimeStamp();
        String createEnName = "en_name" + FakerUtils.getTimeStamp();
        Response createResponse = createDepartment(createName, createEnName,accessToken);
        String departmentId = createResponse.path("id")!=null ? createResponse.path("id").toString():null;
        return departmentId;
    }

    //更新部门
    public  static Response updateDepartment(String updateName, String updateEnName, String departmentId, String accessToken){
        String updateBody = "{\n" +
                "   \"id\": "+departmentId+",\n" +
                "   \"name\": "+updateName+",\n" +
                "   \"name_en\": \""+updateEnName+"\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}";
        Response updateResponse = given().log().all()
                .contentType("application/json")
                .body(updateBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+accessToken)
                .then().log().all()
                .extract().response();
        return updateResponse;
    }

    //查询部门
    public  static Response listDepartment(String departmentId, String accessToken){
        Response listResponse = given().log().all()
                .when()
                .param("id", departmentId)
                .param("access_token", accessToken)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .extract().response();
        return listResponse;
    }

    //删除部门
    public static Response deleteDepartment(String departmentId, String accessToken){
        Response deleteResponse = given().log().all()
                .contentType("application/json")
                .param("id", departmentId)
                .param("access_token", accessToken)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .extract().response();
        return deleteResponse;
    }

}
