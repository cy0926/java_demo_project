package com.wechat.department;

import com.wechat.apiobject.DepartmentObject;
import com.wechat.apiobject.TokenHelper;
import com.wechat.task.EvnHelperTask;
import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *1、编写基础脚本，分别执行了创建，更新，查询，删除部门的操作
 */

public class Demo_05_param_demo {
    static String accessToken;
    static String departmentId;
    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
    }

    @AfterEach
    @BeforeEach
    void clearDepartment(){
        EvnHelperTask.clearDepartmentTask(accessToken);
    }
    @ParameterizedTest
    @DisplayName("创建部门")
    @CsvFileSource(resources = "/data/createDepartment.csv",numLinesToSkip = 1)
    void createDepartment(String createName, String createEnName, String returnCode){
        Response response = DepartmentObject.createDepartment(createName, createEnName, accessToken);
        departmentId = response.path("id")!=null ? response.path("id").toString():null;
        assertEquals(returnCode,response.path("errcode").toString());
    }

    @Test
    void listDepartment(){
        String createName = "name"+FakerUtils.getTimeStamp();
        String createEnName = "en_name"+FakerUtils.getTimeStamp();
        Response createResponse = DepartmentObject.createDepartment(createName,createEnName,accessToken);
        String departmentId = createResponse.path("id")!=null ? createResponse.path("id").toString():null;

        Response listResponse = DepartmentObject.listDepartment(departmentId,accessToken);
        assertAll("查询返回值校验",
                ()->assertEquals("0",listResponse.path("errcode").toString()),
                ()->assertEquals(departmentId,listResponse.path("department[0].id").toString()),
                ()->assertEquals(createName,listResponse.path("department[0].name").toString()),
                ()->assertEquals(createEnName,listResponse.path("department[0].name_en").toString())
        );
    }
}
