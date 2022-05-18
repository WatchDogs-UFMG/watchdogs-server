package br.ufmg.watchdogs.server.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    public String doEncrypt(String word) {
        return new BCryptPasswordEncoder().encode(word);
    }

    public static void main(String[] args) {
        System.out.println(new CryptoService().doEncrypt("Dev123456789"));
    }
}
