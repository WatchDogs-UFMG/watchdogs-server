package br.ufmg.watchdogs.server.api.service.impl;

import br.ufmg.watchdogs.server.api.exception.MyDataAlreadyExistsException;
import br.ufmg.watchdogs.server.api.exception.MyDataNotFoundException;
import br.ufmg.watchdogs.server.api.exception.MyInvalidEnumValueException;
import br.ufmg.watchdogs.server.api.repository.ProfileRepository;
import br.ufmg.watchdogs.server.api.model.form.CreateProfileForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateProfileForm;
import br.ufmg.watchdogs.server.api.model.Profile;
import br.ufmg.watchdogs.server.api.model.UserProfiles;
import br.ufmg.watchdogs.server.api.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile create(CreateProfileForm createProfileForm) {

        String roleName = createProfileForm.getRole();

        try {

            UserProfiles role = UserProfiles.valueOf(roleName);
            Optional<Profile> optionalProfile = this.profileRepository.findByRole(role);

            if (optionalProfile.isPresent()) {

                throw new MyDataAlreadyExistsException(
                        "O perfil " + roleName + " já existe no banco de dados!",
                        this.getClass().getName()
                );
            }

            return this.profileRepository.save(createProfileForm.convertToProfile(role));

        } catch (IllegalArgumentException exception) {

            throw new MyInvalidEnumValueException(
                    "Não foi possível encontrar definição do enum UserProfiles." + roleName,
                    this.getClass().getName()
            );
        }
    }

    @Override
    public Page<Profile> findAll(Pageable pageable) {
        return this.profileRepository.findAll(pageable);
    }

    @Override
    public Profile findById(Long id) {
        return this.profileRepository
                .findById(id)
                .orElseThrow(() -> new MyDataNotFoundException(
                        "Não foi possível encontrar o perfil de id " + id,
                        this.getClass().getName()
                ));
    }

    @Override
    public Profile updateById(UpdateProfileForm updateProfileForm) {

        Long id = updateProfileForm.getId();
        Profile profile = this.profileRepository
                .findById(updateProfileForm.getId())
                .orElseThrow(() -> new MyDataNotFoundException(
                        "Não foi possível encontrar o perfil de id " + id,
                        this.getClass().getName()
                ));

        return updateProfileForm.updateProfile(profile, updateProfileForm.getRole());
    }

    @Override
    public Profile deleteById(Long id) {

        Profile profile = this.findById(id);

        this.profileRepository.delete(profile);

        return profile;
    }

    @Override
    public Profile findByRole(String roleName) {

        try {

            UserProfiles role = UserProfiles.valueOf(roleName);
            return this.profileRepository
                    .findByRole(role)
                    .orElseThrow(() -> new MyDataNotFoundException(
                            "Não foi possível encontrar o perfil " + roleName,
                            this.getClass().getName()
                    ));

        } catch (IllegalArgumentException exception) {

            throw new MyInvalidEnumValueException(
                    "Não foi possível encontrar definição do enum UserProfiles." + roleName,
                    this.getClass().getName()
            );
        }
    }
}
