package by.sivko.pinserver.controllers;

import by.sivko.pinserver.mail.PinMailService;
import by.sivko.pinserver.models.Pin;
import by.sivko.pinserver.models.dao.pin.PinDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/pinserver")
public class PinController {

    private static final String RESPONSE_HEADER_NAME_FOR_PIN_ID = "pinId";
    private static final int RESPONSE_SUCCESSES_STATUS = 200;
    private static final int RESPONSE_BAD_STATUS = 400;

    @Autowired
    PinDao pinDao;

    @Autowired
    PinMailService pinMailService;

    @RequestMapping(method = RequestMethod.GET)
    public void createPin(@RequestHeader String email, @RequestHeader Long operation_id, @RequestHeader String api_token, HttpServletResponse httpServletResponse) {
        Pin pin = pinDao.save(new Pin(email, operation_id, api_token));
        pinMailService.sendEmail(pin);
        httpServletResponse.addHeader(RESPONSE_HEADER_NAME_FOR_PIN_ID, pin.getId().toString());
        httpServletResponse.setStatus(RESPONSE_SUCCESSES_STATUS);

    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public void checkPin(@RequestHeader Long pinId, @RequestHeader int code, HttpServletResponse httpServletResponse) {
        Pin pin = pinDao.findOne(pinId);
        if (pin == null || pin.getCode() != code) {
            httpServletResponse.setStatus(RESPONSE_BAD_STATUS);
        } else {
            httpServletResponse.setStatus(RESPONSE_SUCCESSES_STATUS);
        }
    }

}
