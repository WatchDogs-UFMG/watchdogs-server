package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.model.FoodRelease;
import br.ufmg.watchdogs.server.api.model.form.CreateFoodReleaseForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateFoodReleaseForm;
import br.ufmg.watchdogs.server.api.repository.FoodReleaseRepository;
import br.ufmg.watchdogs.server.api.service.FoodReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FoodReleaseServiceImpl implements FoodReleaseService {

    private final FoodReleaseRepository foodReleaseRepository;

    @Autowired
    public FoodReleaseServiceImpl(FoodReleaseRepository foodReleaseRepository) {
        this.foodReleaseRepository = foodReleaseRepository;
    }

    @Override
    public FoodRelease create(CreateFoodReleaseForm createFoodReleaseForm) {
        return null;
    }

    @Override
    public Page<FoodRelease> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public FoodRelease findById(Long id) {
        return null;
    }

    @Override
    public FoodRelease updateById(UpdateFoodReleaseForm updateFoodReleaseForm) {
        return null;
    }

    @Override
    public FoodRelease deleteById(Long id) {
        return null;
    }
}
