package tk.taroninak.chat.rest.dto;


import tk.taroninak.chat.domain.Room;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("users")
    private List<UserDto> users;

    public RoomDto () {

    }

    public RoomDto (Room room) {
        setId(room.getId());
        setName(room.getName());
        setUsers(room.getUsers().stream().map(UserDto::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public Room toRoom() {
        Room room = new Room();
        room.setId(getId());
        room.setName(getName());
        room.setUsers(getUsers().stream().map(UserDto::toUser).collect(Collectors.toSet()));

        return room;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
