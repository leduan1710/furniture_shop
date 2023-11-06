package hcmute.it.furnitureshop.Service.Impl;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Repository.UserRepository;
import hcmute.it.furnitureshop.Service.UserService;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class UserServiceImplTest extends TestCase {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @BeforeEach
    protected void setUp() throws Exception {
        System.out.println("Setup");
    }

    @AfterEach
    protected void tearDown() throws Exception {
        System.out.println("Teardown");
    }

    @Test
    void findByPhone() {
        Optional<User> user = userService.findByPhone("0865762251");
        assertNotNull(user);
        System.out.println("test findByPhone success");
    }

    @Test
    void findById() {
        Optional<User> user = userService.findById(1);
        assertNotNull(user);
        System.out.println("test findById success");
    }
}