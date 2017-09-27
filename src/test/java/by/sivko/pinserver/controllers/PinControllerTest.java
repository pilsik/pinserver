package by.sivko.pinserver.controllers;

import by.sivko.pinserver.PinServerApplication;
import by.sivko.pinserver.mail.PinMailService;
import by.sivko.pinserver.models.Pin;
import by.sivko.pinserver.models.dao.pin.PinDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@TestPropertySource("classpath:application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PinServerApplication.class)
@WebAppConfiguration
public class PinControllerTest {

    private static final String PATH = "/pinserver";
    private static final int SUCCESSES_STATUS = 200;
    private static final int RESPONSE_BAD_STATUS = 400;
    private static final String PIN_ID = "pinId";
    private static final Long PIN_ID_VALUE = 100500L;
    private static final String REQUEST_HEADER_EMAIL = "email";
    private static final String REQUEST_HEADER_OPERATION_ID = "operation_id";
    private static final String REQUEST_HEADER_TOKEN_API = "api_token";
    private static final String REQUEST_HEADER_EMAIL_VALUE = "test@test.te";
    private static final Long REQUEST_HEADER_OPERATION_ID_VALUE = 10L;
    private static final String REQUEST_HEADER_TOKEN_API_VALUE = "value_api_token";
    private static final String CHECK_PATH = "/pinserver/check";
    private static final String CODE = "code";
    private static final int CODE_VALUE = 1000;
    private static final Long WRONG_PIN_ID_VALUE = 100501L;
    private static final int WRONG_CODE_VALUE = 0000;

    @Mock
    PinDao pinDao;

    @Mock
    PinMailService pinMailService;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    PinController pinController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Pin pinReturnMock = new Pin(REQUEST_HEADER_EMAIL_VALUE, REQUEST_HEADER_OPERATION_ID_VALUE,
                REQUEST_HEADER_TOKEN_API_VALUE);
        pinReturnMock.setId(PIN_ID_VALUE);
        pinReturnMock.setCode(CODE_VALUE);
        Mockito
                .when(pinDao.save(Mockito.any(Pin.class)))
                .thenReturn(pinReturnMock);
        Mockito
                .when(pinDao.findOne(PIN_ID_VALUE))
                .thenReturn(pinReturnMock);
        Mockito
                .when(pinDao.findOne(WRONG_PIN_ID_VALUE))
                .thenReturn(null);
    }

    @Test
    public void createPinForRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                .header(REQUEST_HEADER_EMAIL, REQUEST_HEADER_EMAIL_VALUE)
                .header(REQUEST_HEADER_OPERATION_ID, REQUEST_HEADER_OPERATION_ID_VALUE)
                .header(REQUEST_HEADER_TOKEN_API, REQUEST_HEADER_TOKEN_API_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(SUCCESSES_STATUS))
                .andExpect(MockMvcResultMatchers.header().string(PIN_ID, PIN_ID_VALUE.toString()));
    }

    @Test
    public void trueCheckPinTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CHECK_PATH)
                .header(PIN_ID, PIN_ID_VALUE)
                .header(CODE, CODE_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(SUCCESSES_STATUS));
    }

    @Test
    public void falseCheckPinTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CHECK_PATH)
                .header(PIN_ID, WRONG_PIN_ID_VALUE)
                .header(CODE, CODE_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(RESPONSE_BAD_STATUS));
    }

    @Test
    public void falseCheckCodePinTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CHECK_PATH)
                .header(PIN_ID, PIN_ID_VALUE)
                .header(CODE, WRONG_CODE_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(RESPONSE_BAD_STATUS));
    }
}
