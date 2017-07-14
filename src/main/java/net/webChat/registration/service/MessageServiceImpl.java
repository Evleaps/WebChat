
package net.webChat.registration.service;

import net.webChat.registration.dao.MessageDao;
import net.webChat.registration.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public void save(Message message) {
        messageDao.save (message);
    }
}

