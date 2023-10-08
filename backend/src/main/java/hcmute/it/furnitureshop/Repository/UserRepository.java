package hcmute.it.furnitureshop.Repository;

import hcmute.it.furnitureshop.Entity.User;
<<<<<<< HEAD
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
=======
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findByUsername(String username);
>>>>>>> c2fcefc52ed64502f04394fe8d4eae2cf11916c8
}
