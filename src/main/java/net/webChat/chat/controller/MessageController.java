package net.webChat.chat.controller;

import net.webChat.chat.model.Message;
import net.webChat.chat.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for {@link Message}'s pages.
 */

@Controller
public class MessageController {
    private MessageService messageService;

    @RequestMapping(value = "/chat/send", method = RequestMethod.POST)
    public String sendMessage(@ModelAttribute("message") Message message) {
        messageService.addMessage (message);
        return "redirect:/chat";
    }
}
