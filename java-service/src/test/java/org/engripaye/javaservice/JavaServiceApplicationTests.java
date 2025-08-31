package org.engripaye.javaservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import static java.nio.file.Paths.get;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JavaServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void rootEndPointShouldReturnStatusOk() throws Exception {
        mockMvc.perform((RequestBuilder) get("/"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType("application/json"))
                .andExpect((ResultMatcher) jsonPath("$.status").value("ok"))
                .andExpect((ResultMatcher) jsonPath("$.service").value("java-service"));
    }
}
