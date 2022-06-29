package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.model.Vaccine;
import br.ufmg.watchdogs.server.api.model.form.CreateVaccineForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateVaccineForm;
import br.ufmg.watchdogs.server.api.repository.VaccineRepository;
import br.ufmg.watchdogs.server.api.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository vaccineRepository;

    @Autowired
    public VaccineServiceImpl(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

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
