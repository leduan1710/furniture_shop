package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Auth.RegisterRequest;
import hcmute.it.furnitureshop.Common.RoleEnum;
import hcmute.it.furnitureshop.DTO.CreateUserDTO;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Repository.UserRepository;
import hcmute.it.furnitureshop.Service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository  userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public <S extends User> void savePhoneOfUser(User user){
        userRepository.save(user);
    }

    @Override
    public <S extends User> void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User createUser(CreateUserDTO request){
        if (userRepository.findByPhone(request.getPhone()).isEmpty())
        {
            var user = User.builder().name(request.getFullname())
                    .username(request.getPhone())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phone(request.getPhone())
                    .createDate(new Date())
                    .image(request.getImage())
                    .role(RoleEnum.USER)
                    .status("active")
                    .build();
            userRepository.save(user);
            return user;
        }
        else return null;
    }

    @Override
    public String updateStatusUser(Integer userId)
    {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent())
        {
            if (Objects.equals(user.get().getStatus(), "active"))
                user.get().setStatus("inactive");
            else user.get().setStatus("active");
            userRepository.save(user.get());
            return "Update status thành công";
        }
        else return "Không tồn tại user trong hệ thống";
    }

    @Override
    public void deleteUser(Integer userId){
        userRepository.deleteById(userId);
    }
}
