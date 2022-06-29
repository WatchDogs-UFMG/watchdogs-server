package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.model.Spot;
import br.ufmg.watchdogs.server.api.model.form.CreateSpotForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateSpotForm;
import br.ufmg.watchdogs.server.api.service.SpotService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class SpotServiceImpl implements SpotService {



    @Override
    public Spot create(CreateSpotForm createSpotForm) {
        return null;
    }

    @Override
    public Page<Spot> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Spot findById(Long id) {
        return null;
    }

    @Override
    public Spot updateById(UpdateSpotForm updateSpotForm) {
        return null;
    }

    @Override
    public Spot deleteById(Long id) {
        return null;
    }
}
