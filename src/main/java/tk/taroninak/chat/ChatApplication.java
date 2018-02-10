package tk.taroninak.chat;

import tk.taroninak.chat.domain.Authority;
import tk.taroninak.chat.domain.Room;
import tk.taroninak.chat.domain.User;
import tk.taroninak.chat.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);

    }

    @Bean
    CommandLineRunner init(RoomRepository roomRepository, PasswordEncoder passwordEncoder) {
        return strings -> {
            User user1 = new User("taron", passwordEncoder.encode("aaa"));
            user1.setFirstName("Taron");
            user1.setLastName("Petrosyan");

            Authority authority1 = new Authority();
            authority1.setName(Authority.AuthorityNames.GET_USER);

            Authority authority2 = new Authority();
            authority2.setName(Authority.AuthorityNames.LIST_USERS);

            Authority authority3 = new Authority();
            authority3.setName("LIST_ROOMS");

            user1.getAuthorities().add(authority1);
            user1.getAuthorities().add(authority2);
            user1.getAuthorities().add(authority3);


            User user2 = new User("gevorg", passwordEncoder.encode("bbb"));
            user2.setFirstName("Gevorg");
            user2.setLastName("Araqelyan");



            Room room = new Room("loosers");
            room.getUsers().add(user1);
            room.getUsers().add(user2);

            roomRepository.save(room);



            User user3 = new User("varo94", passwordEncoder.encode("bbb"));
            user3.setFirstName("Varo");
            user3.setLastName("Sargsyan");

            User user4 = new User("xeno777", passwordEncoder.encode("bbb"));
            user4.setFirstName("Hektor");
            user4.setLastName("Khachatryan");

            Room room2 = new Room("js");
            room2.getUsers().add(user3);
            room2.getUsers().add(user4);
            //room2.getUsers().add(user1);


            roomRepository.save(room2);

        };
    }
}
