package tk.taroninak.chat.service.impl;

import tk.taroninak.chat.domain.Room;
import tk.taroninak.chat.domain.User;
import tk.taroninak.chat.repository.RoomRepository;
import tk.taroninak.chat.repository.UserRepository;
import tk.taroninak.chat.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl extends BaseServiceImpl<Room> implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Room> findUserRooms(Long userId) {
        User user = userRepository.findOne(userId);
        if(user == null) {
            return Collections.emptyList();
        }
        return  new ArrayList<>(user.getRooms());
    }

    @Override
    public List<User> addRoomUser(Long roomId, Long userId) {
        Room room = findById(roomId);
        User user = userRepository.findOne(userId);
        room.getUsers().add(user);
        return new ArrayList<>(update(room).getUsers());
    }

    @Override
    public List<User> removeRoomUser(Long roomId, Long userId) {
        Room room = findById(roomId);
        User user = userRepository.findOne(userId);
        room.getUsers().remove(user);
        return new ArrayList<>(update(room).getUsers());
    }
}
