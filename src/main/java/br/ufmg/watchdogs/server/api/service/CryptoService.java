package br.ufmg.watchdogs.server.api.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    public String doEncrypt(String word) {
        return new BCryptPasswordEncoder().encode(word);
    }
}
