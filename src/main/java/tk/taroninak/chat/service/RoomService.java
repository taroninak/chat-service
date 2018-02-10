package tk.taroninak.chat.service;

import tk.taroninak.chat.domain.Room;
import tk.taroninak.chat.domain.User;

import java.util.List;

public interface RoomService extends BaseService<Room> {
    List<Room> findUserRooms(Long userId);
    List<User> addRoomUser(Long roomId, Long userId);
    List<User> removeRoomUser(Long roomId, Long userId);
}
