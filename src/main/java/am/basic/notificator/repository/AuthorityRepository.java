package am.basic.notificator.repository;

 import am.basic.notificator.model.Authority;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Authority getById(int id);
}
