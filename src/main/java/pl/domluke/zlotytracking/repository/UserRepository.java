package pl.domluke.zlotytracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.domluke.zlotytracking.domain.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}