package tk.taroninak.chat.rest.dto;


import tk.taroninak.chat.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("password")
    private String password;

    @JsonProperty("is_active")
    private boolean isActive;

    public UserDto() {
    }

    public UserDto(User user) {
        setId(user.getId());
        setLastName(user.getLastName());
        setFirstName(user.getFirstName());
        setUsername(user.getUsername());
        setAvatar(user.getAvatar());
        setIsActive(user.isActive());
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser() {
        User user = new User();
        user.setLastName(this.getLastName());
        user.setFirstName(this.getFirstName());
        user.setUsername(this.getUsername());
        user.setAvatar(this.getAvatar());
        user.setIsActive(this.isActive());
        user.setPassword(this.getPassword());

        return user;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
