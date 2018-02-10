package tk.taroninak.chat.service;

import tk.taroninak.chat.domain.Message;

import java.util.List;

public interface MessageService extends BaseService<Message> {
    List<Message> findRoomMessages(Long roomId, int page, int size);
}
