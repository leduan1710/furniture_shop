package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Auth.RegisterRequest;
import hcmute.it.furnitureshop.DTO.CreateUserDTO;
import hcmute.it.furnitureshop.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAll();
    public Optional<User> findByName(String name);
    public <S extends User> void savePhoneOfUser(User user);
    public <S extends User> void saveUser(User user);

    public Optional<User> findByPhone(String phone);

    public Optional<User> findById(Integer userId);
    public  User createUser(CreateUserDTO request);
}
