package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.model.Photo;
import br.ufmg.watchdogs.server.api.model.form.CreatePhotoForm;
import br.ufmg.watchdogs.server.api.model.form.UpdatePhotoForm;
import br.ufmg.watchdogs.server.api.repository.PhotoRepository;
import br.ufmg.watchdogs.server.api.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo create(CreatePhotoForm createPhotoForm) {
        return null;
    }

    @Override
    public Page<Photo> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Photo findById(Long id) {
        return null;
    }

    @Override
    public Photo updateById(UpdatePhotoForm updatePhotoForm) {
        return null;
    }

    @Override
    public Photo deleteById(Long id) {
        return null;
    }
}
