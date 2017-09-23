package by.sivko.pinserver.controllers;

import by.sivko.pinserver.PinserverApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PinserverApplication.class)
@WebAppConfiguration
public class PinControllerTest {

    private static final String PATH = "/pinserver";
    private static final int SUCCESSES_STATUS = 200;
    private static final String RESPONSE_HEADER_NAME = "pin_id";
    private static final String RESPONSE_HEADER_VALUE = "100500";
    private static final String REQUEST_HEADER_EMAIL = "email" ;
    private static final Object REQUEST_HEADER_EMAIL_VALUE = "test@test.te" ;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createPinForRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(PATH).header(REQUEST_HEADER_EMAIL, REQUEST_HEADER_EMAIL_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(SUCCESSES_STATUS))
                .andExpect(MockMvcResultMatchers.header().string(RESPONSE_HEADER_NAME, RESPONSE_HEADER_VALUE));
    }

}
