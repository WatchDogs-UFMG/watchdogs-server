package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.model.Log;
import br.ufmg.watchdogs.server.api.model.form.CreateLogForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateLogForm;
import br.ufmg.watchdogs.server.api.service.LogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class LogServiceImpl implements LogService {



    @Override
    public Log create(CreateLogForm createLogForm) {
        return null;
    }

    @Override
    public Page<Log> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Log findById(Long id) {
        return null;
    }

    @Override
    public Log updateById(UpdateLogForm updateLogForm) {
        return null;
    }

    @Override
    public Log deleteById(Long id) {
        return null;
    }
}
