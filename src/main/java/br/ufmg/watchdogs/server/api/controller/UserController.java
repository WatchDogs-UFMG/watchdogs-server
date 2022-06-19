package br.ufmg.watchdogs.server.api.controller;

import br.ufmg.watchdogs.server.api.model.dto.UserDto;
import br.ufmg.watchdogs.server.api.model.form.CreateUserForm;
import br.ufmg.watchdogs.server.api.model.form.UpdateUserForm;
import br.ufmg.watchdogs.server.api.model.User;
import br.ufmg.watchdogs.server.api.service.UserService;
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
@RequestMapping("v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserForm createUserForm, UriComponentsBuilder uriComponentsBuilder) {

        User user = this.userService.createUser(createUserForm);
        URI uri = uriComponentsBuilder
                .path("/user/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new UserDto(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getUsers(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(
                this.userService
                        .getUsers(pageable)
                        .map(UserDto::new)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = this.userService.getUserById(id);
        return ResponseEntity.ok(new UserDto(user));
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUserById(@Valid @RequestBody UpdateUserForm updateUserForm) {
        User user = this.userService.updateUserById(updateUserForm);
        return ResponseEntity.ok(new UserDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteByUserId(@PathVariable Long id) {
        User user = this.userService.deleteByUserId(id);
        return ResponseEntity.ok(new UserDto(user));
    }
}
