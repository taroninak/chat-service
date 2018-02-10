package tk.taroninak.chat.rest.dto;

import tk.taroninak.chat.domain.Message;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("body")
    private String body;

    @JsonProperty("room")
    private RoomDto room;

    @JsonProperty("creator")
    private UserDto creator;

    public MessageDto() {

    }

    public MessageDto(Message message) {
        setId(message.getId());
        setBody(message.getBody());
        setRoom(new RoomDto(message.getRoom()));
        setCreator(new UserDto(message.getCreator()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public RoomDto getRoom() {
        return room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }

    public UserDto getCreator() {
        return creator;
    }

    public void setCreator(UserDto creator) {
        this.creator = creator;
    }

    public Message toMessage() {
        Message message = new Message();
        message.setId(getId());
        message.setBody(getBody());
        message.setRoom(getRoom().toRoom());
        message.setCreator(getCreator().toUser());
        return message;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", room=" + room +
                ", creator=" + creator +
                '}';
    }
}
