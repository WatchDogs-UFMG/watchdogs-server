package br.ufmg.watchdogs.server.api.repository;

import br.ufmg.watchdogs.server.api.model.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    Page<Photo> findAll(Pageable pageable);
}
