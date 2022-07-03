package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.exception.MyDataNotFoundException;
import br.ufmg.watchdogs.server.api.repository.UserRepository;
import br.ufmg.watchdogs.server.api.model.form.CreateUserForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateUserForm;
import br.ufmg.watchdogs.server.api.model.Profile;
import br.ufmg.watchdogs.server.api.model.User;
import br.ufmg.watchdogs.server.api.service.CryptoService;
import br.ufmg.watchdogs.server.api.service.ProfileService;
import br.ufmg.watchdogs.server.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProfileService profileService;
    private final CryptoService cryptoService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProfileServiceImpl profileService, CryptoServiceImpl cryptoService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
        this.cryptoService = cryptoService;
    }

    @Override
    public User create(CreateUserForm createUserForm) {

        Profile profile = profileService.findByRole(createUserForm.getRole());
        User user = createUserForm.convertToUser(
                profile,
                this.cryptoService.doEncrypt(createUserForm.getPassword())
        );

        this.userRepository.save(user);

        return user;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new MyDataNotFoundException(
                        "Não foi possível encontrar o usuário de id " + id,
                        this.getClass().getName()
                ));
    }

    @Override
    public User updateById(UpdateUserForm updateUserForm) {

        Profile profile = this.profileService.findByRole(updateUserForm.getRole());
        User user = this.userRepository
                .findById(updateUserForm.getId())
                .orElseThrow(() -> new MyDataNotFoundException(
                        "Não foi possível encontrar o usuário de id " + updateUserForm.getId(),
                        this.getClass().getName()
                ));

        return this.userRepository.save(updateUserForm.updateUser(user, profile));
    }

    @Override
    public User deleteById(Long id) {

        User user = this.findById(id);

        this.userRepository.delete(user);

        return user;
    }

    @Override
    public User findByEmail(String username) {
        return this.userRepository.findByEmail(username)
                .orElseThrow(() -> new MyDataNotFoundException(
                        "Não foi possível encontrar o usuário com o email " + username,
                        this.getClass().getName()
                ));
    }
}
