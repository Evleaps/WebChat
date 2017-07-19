package net.webChat.registration.validator;

import net.webChat.registration.model.User;
import net.webChat.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link net.webChat.registration.model.User} class,
 * implements {@link Validator} interface.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
/**Если поле username не заполнили, выводим сообщение, что оно обязательно к заполнению*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 30) {
            errors.rejectValue("username", "Size.userForm.username");
        }
/**Если такой пипл уже есть в БД*/
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
/**Если поле password не заполнили, выводим сообщение, что оно обязательно к заполнению*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        /**Если поле password имеет короткий пароль, выводим сообщение из проперти*/
        if (user.getPassword().length() < 3 || user.getPassword().length() > 30) {
            errors.rejectValue("password", "Size.userForm.password");
        }
/**Если поле password не совпадает с повторите пароль, выводим сообщение из проперти*/
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
