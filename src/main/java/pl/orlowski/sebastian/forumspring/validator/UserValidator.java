package pl.orlowski.sebastian.forumspring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.orlowski.sebastian.forumspring.dto.UserRegistrationDto;
import pl.orlowski.sebastian.forumspring.service.UserService;

@Component
public class UserValidator implements Validator {

        private UserService userService;

        @Autowired
        public UserValidator(UserService userService) {
            this.userService = userService;
        }

        @Override
        public boolean supports(Class<?> aClass) {
            return UserRegistrationDto.class.equals(aClass);
        }

        @Override
        public void validate(Object o, Errors errors) {
            UserRegistrationDto user = (UserRegistrationDto) o;

//            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty");
            if (userService.findByLogin(user.getLogin()) != null) {
                errors.rejectValue("login", "Duplicate.user.username");
            }

            if (userService.emailExist(user.getEmail())) {
                errors.rejectValue("login", "Duplicate.user.email");
            }

            }
}
