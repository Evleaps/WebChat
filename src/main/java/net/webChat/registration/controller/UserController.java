package net.webChat.registration.controller;

import net.webChat.registration.model.Message;
import net.webChat.registration.model.User;
import net.webChat.registration.service.MessageService;
import net.webChat.registration.service.SecurityService;
import net.webChat.registration.service.UserService;
import net.webChat.registration.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for {@link net.webChat.registration.model.User}'s pages.
 */

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private MessageService messageService;



    /** Первая страница, проверяем на наличае ошибок*/
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    /** Если все хорошо, переводим на страницу самого чата*/
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "chat";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }

    /** При получении запроса "/registration" переводим на соответствующую страницу*/
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    /** Получаем данные, делегируем проверку сервисам и переводим на страницу чата*/
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "chat";
    }
///////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "chat", method = RequestMethod.GET)
    public String theChat(Model model) {
        model.addAttribute("/message", new Message ());
        return "chat";
    }

    @RequestMapping(value = "/chat/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("chat") Message message, Model model){
        if(message.getId() == 0){
            messageService.save (message);
        }

        return "redirect:/books";
    }

   /* @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("messageForm") Message message, BindingResult bindingResult, Model model) {

        return "chat";
    }*/
}
