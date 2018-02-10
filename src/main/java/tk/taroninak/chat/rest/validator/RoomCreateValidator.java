package tk.taroninak.chat.rest.validator;

import tk.taroninak.chat.rest.dto.RoomDto;
import tk.taroninak.chat.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RoomCreateValidator implements Validator {
    @Autowired
    RoomService roomService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RoomDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //TODO: Add appropriate validation checks
    }
}
