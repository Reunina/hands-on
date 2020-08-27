package com.example.demospringboot;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

    @Test
    public void getHelloFromCustomPath() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customPath").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Greetings from Spring Boot!")))
                .andExpect(content().string(containsString("customPath")));
    }

    @Test
    public void useActuatorSpringBootModule() throws Exception {
        String expectedOutput="{'_links':{'self':{'href':'http://localhost/actuator','templated':false},'health-path':{'href':'http://localhost/actuator/health/{*path}','templated':true},'health':{'href':'http://localhost/actuator/health','templated':false},'info':{'href':'http://localhost/actuator/info','templated':false}}}";
        mvc.perform(MockMvcRequestBuilders.get("/actuator").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutput));
    }

    @Test
    public void shutdownActuatorSpringBootModuleShouldBeActivated() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/actuator/shutdown").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'message':'Shutting down, bye...'}"));
    }
}