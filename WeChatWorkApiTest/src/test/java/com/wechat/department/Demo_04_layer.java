package com.wechat.department;

import com.wechat.apiobject.DepartmentObject;
import com.wechat.apiobject.TokenHelper;
import com.wechat.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *1、编写基础脚本，分别执行了创建，更新，查询，删除部门的操作
 */

public class Demo_04_layer {
    static String accessToken;
    static String departmentId;
    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
    }

    @AfterEach
    @BeforeEach
    void clearDepartment(){
        Response listResponse = DepartmentObject.listDepartment("1", accessToken);
        ArrayList<Integer> departmentIdList = listResponse.path("department.id");
        for (int departmentId : departmentIdList){
            if (1==departmentId){
                continue;
            }
            Response deleteResponse = DepartmentObject.deleteDepartment(departmentId+"", accessToken);
            assertEquals("0", deleteResponse.path("errcode").toString());
        }
    }
    @Test
    @DisplayName("创建部门")
    void createDepartment(){
        String createName = "name" + FakerUtils.getTimeStamp();
        String createEnName = "en_name" + FakerUtils.getTimeStamp();
        Response response = DepartmentObject.createDepartment(createName, createEnName, accessToken);
        departmentId = response.path("id")!=null ? response.path("id").toString():null;
        assertEquals("0",response.path("errcode").toString());
    }
    @DisplayName("修改部门")
    @Test
    void updateDepartment(){
        String departmentId = DepartmentObject.createDepartment(accessToken);

        String updateName = "name" + FakerUtils.getTimeStamp();
        String updateEnName = "en_name" + FakerUtils.getTimeStamp();

        Response updateResponse = DepartmentObject.updateDepartment(updateName,updateEnName,departmentId,accessToken);
        assertEquals("0",updateResponse.path("errcode").toString());
    }

    @DisplayName("查询部门")
    @Test
    void listDepartment(){
        String departmentId = DepartmentObject.createDepartment(accessToken);
        Response listResponse = DepartmentObject.listDepartment(departmentId, accessToken);
        assertEquals("0",listResponse.path("errcode").toString());
        assertEquals(departmentId, listResponse.path("department.id[0]").toString());
    }

    @DisplayName("删除部门")
    @Test
    void deleteDepartment(){
        String departmentId = DepartmentObject.createDepartment(accessToken);
        Response response = DepartmentObject.deleteDepartment(departmentId, accessToken);
        assertEquals("0", response.path("errcode").toString());
    }
}
