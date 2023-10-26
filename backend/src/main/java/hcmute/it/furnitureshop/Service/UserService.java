package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Entity.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> findByName(String name);
    public <S extends User> void savePhoneOfUser(User user);
}
