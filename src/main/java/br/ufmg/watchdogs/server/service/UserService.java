package br.ufmg.watchdogs.server.service;

import br.ufmg.watchdogs.server.exception.MyDataNotFoundException;
import br.ufmg.watchdogs.server.form.CreateUserForm;
import br.ufmg.watchdogs.server.form.UpdateUserForm;
import br.ufmg.watchdogs.server.model.Profile;
import br.ufmg.watchdogs.server.model.User;
import br.ufmg.watchdogs.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProfileService profileService;
    private final CryptoService cryptoService;

    @Autowired
    public UserService(UserRepository userRepository, ProfileService profileService, CryptoService cryptoService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
        this.cryptoService = cryptoService;
    }

    public User createUser(CreateUserForm createUserForm) {

        Profile profile = profileService.getProfileByRole(createUserForm.getRole());
        User user = createUserForm.convertToUser(
                profile,
                this.cryptoService.doEncrypt(createUserForm.getPassword())
        );

        this.userRepository.save(user);

        return user;
    }

    public Page<User> getUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public User getUserById(Long id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new MyDataNotFoundException(
                        "Não foi possível encontrar o usuário de id " + id,
                        this.getClass().getName()
                ));
    }

    public User updateUserById(UpdateUserForm updateUserForm) {

        Profile profile = this.profileService.getProfileByRole(updateUserForm.getRole());
        User user = this.userRepository
                .findById(updateUserForm.getId())
                .orElseThrow(() -> new MyDataNotFoundException(
                        "Não foi possível encontrar o usuário de id " + updateUserForm.getId(),
                        this.getClass().getName()
                ));

        return this.userRepository.save(updateUserForm.updateUser(user, profile));
    }

    public User deleteByUserId(Long id) {

        User user = this.getUserById(id);

        this.userRepository.delete(user);

        return user;
    }

    public User findByEmail(String username) {
        return this.userRepository.findByEmail(username)
                .orElseThrow(() -> new MyDataNotFoundException(
                        "Não foi possível encontrar o usuário com o email " + username,
                        this.getClass().getName()
                ));
    }
}
