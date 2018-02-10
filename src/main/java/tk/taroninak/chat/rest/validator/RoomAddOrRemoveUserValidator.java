package tk.taroninak.chat.rest.validator;

import tk.taroninak.chat.rest.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RoomAddOrRemoveUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto) o;
        if(userDto == null) {
            errors.rejectValue("Body", "NotEmpty");
            return;
        }
        if(userDto.getId() == null) {
            errors.rejectValue("id", "NotEmpty");
        }
    }
}
