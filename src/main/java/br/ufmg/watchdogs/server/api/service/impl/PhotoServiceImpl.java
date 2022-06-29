package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.model.Photo;
import br.ufmg.watchdogs.server.api.model.form.CreatePhotoForm;
import br.ufmg.watchdogs.server.api.model.form.UpdatePhotoForm;
import br.ufmg.watchdogs.server.api.service.PhotoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PhotoServiceImpl implements PhotoService {



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
