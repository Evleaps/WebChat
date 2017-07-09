package net.webChat.chat.service;

import net.webChat.chat.dao.MessageDao;
import net.webChat.chat.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageService implements MessageDao {
    private static final Logger logger =  LoggerFactory.getLogger (MessageService.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addMessage(Message message) {
        Session session = this.sessionFactory.getCurrentSession ();
        session.persist (message);//добавили новое сообщение в бд
        logger.info ("Added new messages: " + message.toString ());
    }

    @Override
    public void updateMessage(Message message) {
        //
    }

    @Override
    public void refreshMessage(Message message) {

    }
}
