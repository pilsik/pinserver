package by.sivko.pinserver.mail;

import by.sivko.pinserver.PinServerApplication;
import by.sivko.pinserver.models.Pin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;

@TestPropertySource("classpath:application-test.properties")
@ContextConfiguration(classes = PinServerApplication.class)
@RunWith(SpringRunner.class)
public class MailServiceTest {

    @Autowired
    @InjectMocks
    PinMailService mailService;

    @Mock
    JavaMailSender javaMailSender;

    @Before
    public void init(){
    }

    @Test
    public void sendEmail() throws Exception {
        Pin pin = new Pin("test@gmail.com",12L,"api_token");
        mailService.sendEmail(pin);
    }

}