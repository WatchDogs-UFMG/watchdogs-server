package br.ufmg.watchdogs.server.repository;

import br.ufmg.watchdogs.server.model.Profile;
import br.ufmg.watchdogs.server.model.UserProfiles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Page<Profile> findAll(Pageable pageable);
    Optional<Profile> findByRole(UserProfiles role);
}
