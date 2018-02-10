package tk.taroninak.chat.service.impl;

import tk.taroninak.chat.domain.Message;
import tk.taroninak.chat.repository.MessageRepository;
import tk.taroninak.chat.service.BadWordService;
import tk.taroninak.chat.service.MessageService;
import tk.taroninak.chat.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private BadWordService badWordService;

    @Autowired
    private RoomService roomService;

    @Override
    public List<Message> findRoomMessages(Long roomId, int page, int size) {
        return messageRepository.findByRoomId(new PageRequest(page, size), roomId)
                .getContent();
    }

    @Override
    public Message create(Message message) {
        List<String> words = Arrays.asList(message.getBody().split("\\s"));
        if(badWordService.containsBadWords(words)) {
            roomService.removeRoomUser(message.getRoom().getId(), message.getCreator().getId());
            //TODO: Throw appropriate exception instead of null
            return null;
        }
        return super.create(message);
    }

    @Override
    public Message update(Message message) {
        List<String> words = Arrays.asList(message.getBody().split("\\s"));
        if(badWordService.containsBadWords(words)) {
            roomService.removeRoomUser(message.getRoom().getId(), message.getCreator().getId());
            //TODO: Throw appropriate exception instead of null
            return null;
        }
        return super.update(message);
    }
}
