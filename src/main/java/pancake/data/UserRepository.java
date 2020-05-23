package pancake.data;
import org.springframework.data.repository.CrudRepository;
import pancake.User;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
  
}
