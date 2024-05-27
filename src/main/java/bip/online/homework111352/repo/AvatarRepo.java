package bip.online.homework111352.repo;

import bip.online.homework111352.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepo extends JpaRepository<Avatar,Long> {
    Optional<Avatar> findByStudent_Id(Long id);
}
