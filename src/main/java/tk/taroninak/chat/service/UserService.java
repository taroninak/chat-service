package tk.taroninak.chat.service;

import tk.taroninak.chat.domain.User;

public interface UserService extends BaseService<User> {
    User findByUsername(String username);
}
