package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.model.Vaccine;
import br.ufmg.watchdogs.server.api.model.form.CreateVaccineForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateVaccineForm;
import br.ufmg.watchdogs.server.api.service.VaccineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class VaccineServiceImpl implements VaccineService {



    @Override
    public Vaccine create(CreateVaccineForm createVaccineForm) {
        return null;
    }

    @Override
    public Page<Vaccine> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Vaccine findById(Long id) {
        return null;
    }

    @Override
    public Vaccine updateById(UpdateVaccineForm updateVaccineForm) {
        return null;
    }

    @Override
    public Vaccine deleteById(Long id) {
        return null;
    }
}
