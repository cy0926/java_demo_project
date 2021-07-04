package com.wechat.department;

import com.wechat.apiobject.DepartmentObject;
import com.wechat.apiobject.TokenHelper;
import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *1、编写基础脚本，分别执行了创建，更新，查询，删除部门的操作
 */

public class Demo_06_02_concurrent {
    static String accessToken;
    static String departmentId;
    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
    }

    @DisplayName("创建部门")
    @RepeatedTest(10)
    void createDepartment(){
        String backendStr = Thread.currentThread().getId() + FakerUtils.getTimeStamp()+ "";

        String createName = "createName" + backendStr;
        String createEnName = "createEnName" + backendStr;
        Response response = DepartmentObject.createDepartment(createName, createEnName, accessToken);
        assertEquals("0",response.path("errcode").toString());
    }

    @DisplayName("修改部门")
    @RepeatedTest(10)
    void updateDepartment(){
        String backendStr = Thread.currentThread().getId() + FakerUtils.getTimeStamp()+ "";

        String createName = "createName" + backendStr;
        String createEnName = "createEnName" + backendStr;
        Response createResponse = DepartmentObject.createDepartment(createName,createEnName,accessToken);
        departmentId = createResponse.path("id")!=null ? createResponse.path("id").toString():null;

        String updateName = "updateName" + backendStr;
        String updateEnName = "updateEnName" + backendStr;

        Response updateResponse = DepartmentObject.updateDepartment(updateName,updateEnName,departmentId,accessToken);
        assertEquals("0",updateResponse.path("errcode").toString());
    }

}
