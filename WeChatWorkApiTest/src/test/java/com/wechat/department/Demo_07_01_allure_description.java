package com.wechat.department;

import com.wechat.apiobject.DepartmentObject;
import com.wechat.apiobject.TokenHelper;
import com.wechat.task.EvnHelperTask;
import com.wechat.utils.FakerUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import javax.xml.ws.FaultAction;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 ** allure 注解举例
 */
@Epic("Epic企业微信接口测试用例")
@Feature("Feature部门相关接口测试")
public class Demo_07_01_allure_description {
    static String accessToken;

    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
    }

    @AfterEach
    @BeforeEach
    void clearDepartment(){
        EvnHelperTask.clearDepartmentTask(accessToken);
    }
    @Test
    @DisplayName("DisplayName创建部门")
    @Description("Description这个测试方法会测试创建部门的接口功能")
    @Story("Story创建部门接口测试")
    void createDepartment(){
        String createName = "name" + FakerUtils.getTimeStamp();
        String createEnName = "en_name" + FakerUtils.getTimeStamp();
        Response response = DepartmentObject.createDepartment(createName, createEnName, accessToken);
        assertEquals("0",response.path("errcode").toString());
    }
    @DisplayName("修改部门")
    @Description("Description这个测试方法会测试修改部门的接口功能")
    @Story("Story修改部门接口测试")
    @Test
    void updateDepartment(){
        String departmentId = DepartmentObject.createDepartment(accessToken);

        String updateName = "name" + FakerUtils.getTimeStamp();
        String updateEnName = "en_name" + FakerUtils.getTimeStamp();

        Response updateResponse = DepartmentObject.updateDepartment(updateName,updateEnName,departmentId,accessToken);
        assertEquals("0",updateResponse.path("errcode").toString());
    }

    @DisplayName("查询部门")
    @Description("Description这个测试方法会测试查询部门的接口功能")
    @Story("Story查询部门接口测试")
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

    @DisplayName("删除部门")
    @Description("Description这个测试方法会测试删除部门的接口功能")
    @Story("Story删除部门接口测试")
    @Test
    void deleteDepartment(){
        String departmentId = DepartmentObject.createDepartment(accessToken);
        Response response = DepartmentObject.deleteDepartment(departmentId, accessToken);
        assertEquals("0", response.path("errcode").toString());
    }
}
