package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.model.Log;
import br.ufmg.watchdogs.server.api.model.form.CreateLogForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateLogForm;
import br.ufmg.watchdogs.server.api.repository.LogRepository;
import br.ufmg.watchdogs.server.api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

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
