package net.webChat.chat.dao;

import net.webChat.chat.model.Message;

public interface MessageDao {
    public void addMessage(Message message);
    public void updateMessage(Message message);
    public void refreshMessage(Message message);
}
