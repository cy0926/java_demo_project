package com.wechat.department;

import com.wechat.apiobject.DepartmentObject;
import com.wechat.apiobject.TokenHelper;
import com.wechat.task.EvnHelperTask;
import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *1、编写基础脚本，分别执行了创建，更新，查询，删除部门的操作
 */

public class Demo_06_01_thread_createdepartment {
    static String accessToken;
    static String departmentId;
    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
    }

    @DisplayName("创建部门")
    @RepeatedTest(100)
    void createDepartment(){
        String createName = "name_thread";
        String createEnName = "en_name_thread";
        Response response = DepartmentObject.createDepartment(createName, createEnName, accessToken);
        assertEquals("0",response.path("errcode").toString());
    }
}
