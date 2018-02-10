package tk.taroninak.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="name")
    private AuthorityNames name;

    @Override
    public String getAuthority() {
        return name.name();
    }

    public void setName(String name) {
        this.name = AuthorityNames.valueOf(name);
    }

    public void setName(AuthorityNames name) {
        this.name = name;
    }

    @JsonIgnore
    public AuthorityNames getName() {
        return name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public enum AuthorityNames {
        LIST_USERS,
        GET_USER,
        CREATE_USER,
        EDIT_USER,
        REMOVE_USER,
        GET_USER_ROOMS,
        LIST_ROOMS,
        GET_ROOM,
        CREATE_ROOM,
        EDIT_ROOM,
        REMOVE_ROOM,
        GET_ROOM_USERS,
        ADD_ROOM_USERS,
        REMOVE_ROOM_USERS,
        LIST_ROOM_MESSAGES,
        LIST_MESSAGES,
        GET_MESSAGE,
        CREATE_MESSAGE,
        EDIT_MESSAGE,
        REMOVE_MESSAGE,
        LIST_BAD_WORDS,
        ADD_BAD_WORD,
        EDIT_BAD_WORD,
        REMOVE_BAD_WORD
    }
}
