package by.sivko.pinserver.controllers;

import by.sivko.pinserver.models.Pin;
import by.sivko.pinserver.models.repositories.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/pinserver")
public class PinController {

    private static final String RESPONSE_HEADER_NAME_FOR_PIN_ID = "pin_id";
    private static final int RESPONSE_SUCCESSES_STATUS = 200;
    private static final int RESPONSE_BAD_STATUS = 400;

    @Autowired
    PinRepository pinRepository;

    @RequestMapping(method = RequestMethod.GET)
    public void createPin(@RequestHeader String email,@RequestHeader Long operation_id,@RequestHeader String api_token, HttpServletResponse httpServletResponse) {
        Pin pin = pinRepository.save(new Pin(email, operation_id, api_token));
        httpServletResponse.addHeader(RESPONSE_HEADER_NAME_FOR_PIN_ID, pin.getId().toString());
        httpServletResponse.setStatus(RESPONSE_SUCCESSES_STATUS);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void checkPin(@RequestBody Long pinId, @RequestBody int code, HttpServletResponse httpServletResponse) {
        Optional<Pin> pin = pinRepository.findOne(pinId);
        if (!pin.isPresent() || pin.get().getCode() == code) {
            httpServletResponse.setStatus(RESPONSE_BAD_STATUS);
        } else {
            httpServletResponse.setStatus(RESPONSE_SUCCESSES_STATUS);
        }
    }

}
