package br.ufmg.watchdogs.server.api.repository;

import br.ufmg.watchdogs.server.api.model.Profile;
import br.ufmg.watchdogs.server.api.model.UserProfiles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Page<Profile> findAll(Pageable pageable);
    Optional<Profile> findByRole(UserProfiles role);
}
