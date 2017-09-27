package by.sivko.pinserver.mail;

import by.sivko.pinserver.models.Pin;
import by.sivko.pinserver.models.dao.pin.PinDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

@Service
public class PinMailServiceImpl implements PinMailService {

    private static final Logger logger = LoggerFactory.getLogger(PinMailServiceImpl.class);


    private final String TEXT_BEFORE_CODE = "You code is";

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailSetFrom;

    @Override
    public void sendEmail(Object object) {
        Pin pin = (Pin) object;
        logger.info(String.format("Выполняется отправка кода на email %s", pin.getEmail()));
        try {
            mailSender.send(mimeMessage -> {
                mimeMessage.setFrom(mailSetFrom);
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(pin.getEmail()));
                mimeMessage.setText(String.format("%s %s", TEXT_BEFORE_CODE, pin.getCode()));
            });
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }


}