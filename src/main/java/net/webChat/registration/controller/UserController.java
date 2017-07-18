package net.webChat.registration.controller;

import net.webChat.registration.model.Message;
import net.webChat.registration.model.User;
import net.webChat.registration.service.MessageService;
import net.webChat.registration.service.SecurityService;
import net.webChat.registration.service.UserService;
import net.webChat.registration.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    /* Первая страница, проверяем на наличае ошибок*/
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


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }


    /* При получении запроса "/registration" переводим на соответствующую страницу*/
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    /* Получаем данные, делегируем проверку сервисам и переводим на страницу чата*/
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }



        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "/chat";
    }

    /* Если все хорошо, переводим на страницу самого чата
     * 1. передаем в jsp сообщения в виде List<Message> messages. В jsp он будет искаться по имени s: "messages"
     * 2. передаем само сообщение, оно пустое и ничего не содержит, заполнять будем при сохранении*/
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        List<Message> allInstanceMessages = messageService.getAllMessages ();
        List<User> allInstanceUsers = userService.getAllUsers ();
        model.addAttribute ("allInstanceUsers", allInstanceUsers);//jsp увидит поля всех инстансов User
        model.addAttribute ("allInstanceMessages", allInstanceMessages);//jsp увидит поля всех инстансов Message
        model.addAttribute ("messageForm", new Message ());//отправляем в конструктор
        return "chat";
    }




    /*Тут мы принимаем наш атрибут, который ищется по имени messageForm и хранит в себе инстанс Message,
       * проверка на null (Long obj)
        * добавляем имя юзера
        * добавляем дату и сохраняем в бд*/
    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("messageForm") Message messageForm, Model model){
        if(messageForm.getId() == null){
            messageForm.setUsername (SecurityContextHolder.getContext ().getAuthentication ().getName ());
            messageForm.setDate (new Date ());
            messageService.save (messageForm);
        }
        return "redirect:/";
    }
}
