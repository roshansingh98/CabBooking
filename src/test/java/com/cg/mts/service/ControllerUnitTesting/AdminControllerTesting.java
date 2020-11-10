package com.cg.mts.service.ControllerUnitTesting;

import com.cg.mts.controller.AdminController;
import com.cg.mts.entities.Admin;
import com.cg.mts.service.IAdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc
public class AdminControllerTesting {

    @Autowired
    MockMvc mvc;

    @MockBean
    IAdminService adminService;

    @Test
    public void sampleTest() throws Exception{

        Admin admin = new Admin("firstAdmin", "123456", "9600456781", "abcde@gmail.com");
        Admin admin1 = new Admin("firstAdmin", "123456", "9600456781", "abcde@gmail.com");
        admin1.setAdminId(1);

        Admin mockAdmin = Mockito.mock(Admin.class);

        Mockito.when(adminService.insertAdmin(admin)).thenReturn(mockAdmin);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestAddJson = objectMapper.writeValueAsString(admin);
        String responseAddJson = objectMapper.writeValueAsString(mockAdmin);

        System.out.println("inside testadd_1, request data json= "+ requestAddJson);
        System.out.println("inside testadd_1, expected json= "+responseAddJson);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin/add").
                contentType(MediaType.APPLICATION_JSON).
                content(requestAddJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(status , 201);



//        mvc.perform(MockMvcRequestBuilders.post("/admin/add").
//                content(requestAddJson).
//                contentType(MediaType.APPLICATION_JSON)).
//                andExpect(status().isCreated()).
//                andExpect(content().json(responseAddJson));


    }

}
