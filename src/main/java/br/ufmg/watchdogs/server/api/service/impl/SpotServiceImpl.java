package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.model.Spot;
import br.ufmg.watchdogs.server.api.model.form.CreateSpotForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateSpotForm;
import br.ufmg.watchdogs.server.api.repository.SpotRepository;
import br.ufmg.watchdogs.server.api.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SpotServiceImpl implements SpotService {

    private final SpotRepository spotRepository;

    @Autowired
    public SpotServiceImpl(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

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
