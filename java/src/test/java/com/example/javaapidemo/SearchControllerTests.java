package com.example.javaapidemo;

import com.example.javaapidemo.Controller.SearchController;
import com.example.javaapidemo.DAO.SearchDAO;
import com.example.javaapidemo.Exception.InvalidParametersException;
import com.example.javaapidemo.Model.Contact;
import com.example.javaapidemo.Model.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerTests {

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @MockBean
    private SearchDAO searchDAO;

    @Test
    public void testGetResultAPI() throws Exception {

        // create a new Result object
        ArrayList<Contact> contacts = new ArrayList<>();
        Result result = new Result(contacts, 10, 1);

        // serialize User object to JSON string using ObjectMapper


        // use Mockito APIs to mock method calls on UserService object

        // use MockMVC to perform request (make API call)

        // use MockMvcResultMatchers to assert the response (status code, content type, JSON fields,...)
    }


    }

}

