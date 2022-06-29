package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.service.CryptoService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CryptoServiceImpl implements CryptoService {

    @Override
    public String doEncrypt(String word) {
        return new BCryptPasswordEncoder().encode(word);
    }
}
