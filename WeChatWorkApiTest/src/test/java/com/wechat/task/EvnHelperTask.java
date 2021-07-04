package com.wechat.task;

import com.wechat.apiobject.DepartmentObject;
import io.restassured.response.Response;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvnHelperTask {
    public static void clearDepartmentTask(String accessToken){
        ArrayList<Integer> departmentIdList = DepartmentObject.listDepartment("1",accessToken).path("department.id");
        for (int departmentId : departmentIdList){
            if (1==departmentId){
                continue;
            }
            System.out.println("departmenId is :" + departmentId);
            Response deleteResponse = DepartmentObject.deleteDepartment(departmentId+"", accessToken);
            assertEquals("0", deleteResponse.path("errcode").toString());
        }
    }
}
