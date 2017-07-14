
package net.webChat.registration.dao;

import net.webChat.registration.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<Message, Long> {
}

