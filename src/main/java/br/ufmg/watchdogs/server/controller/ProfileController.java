package br.ufmg.watchdogs.server.controller;

import br.ufmg.watchdogs.server.dto.ProfileDto;
import br.ufmg.watchdogs.server.form.CreateProfileForm;
import br.ufmg.watchdogs.server.form.UpdateProfileForm;
import br.ufmg.watchdogs.server.model.Profile;
import br.ufmg.watchdogs.server.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@Valid @RequestBody CreateProfileForm createProfileForm, UriComponentsBuilder uriComponentsBuilder) {

        Profile profile = this.profileService.createProfile(createProfileForm);
        URI uri = uriComponentsBuilder
                .path("/profile/{id}")
                .buildAndExpand(profile.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new ProfileDto(profile));
    }

    @GetMapping
    public ResponseEntity<Page<ProfileDto>> getProfiles(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable
    ) {
            return ResponseEntity.ok(
                    this.profileService
                            .getProfiles(pageable)
                            .map(ProfileDto::new)
            );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable Long id) {
            Profile profile = this.profileService.getProfileById(id);
            return ResponseEntity.ok(new ProfileDto(profile));
    }

    @PutMapping
    public ResponseEntity<ProfileDto> updateProfileById(@Valid @RequestBody UpdateProfileForm updateProfileForm) {
            Profile profile = this.profileService.updateProfileById(updateProfileForm);
            return ResponseEntity.ok(new ProfileDto(profile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProfileDto> deleteByProfileId(@PathVariable Long id) {
            Profile profile = this.profileService.deleteByProfileId(id);
            return ResponseEntity.ok(new ProfileDto(profile));
    }
}
