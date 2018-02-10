package tk.taroninak.chat.rest.controller;


import tk.taroninak.chat.rest.dto.RoomDto;
import tk.taroninak.chat.rest.dto.UserDto;
import tk.taroninak.chat.rest.validator.UserCreateValidator;
import tk.taroninak.chat.domain.User;
import tk.taroninak.chat.service.RoomService;
import tk.taroninak.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserCreateValidator userCreateValidator;

    @PreAuthorize("hasAuthority('LIST_USERS')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> list(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, @RequestParam(value = "size", required = false, defaultValue = "${request.defaultPageSize:10}") Integer size) {
        return ResponseEntity.ok(userService
                .findAll(page, size)
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList()));
    }


    @PreAuthorize("hasAuthority('GET_USER')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> get(@PathVariable("userId") long userId) {
        return ResponseEntity.ok(new UserDto(userService.findById(userId)));
    }


    @PreAuthorize("hasAuthority('CREATE_USER')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto, BindingResult bindingResult) {

        userCreateValidator.validate(userDto, bindingResult);

        //TODO: Throw appropriate exception and handle it globally
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User user = userService.create(userDto.toUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(user));
    }

    @PreAuthorize("hasAuthority('EDIT_USER')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<UserDto> put(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(new UserDto(userService.create(userDto.toUser())));
    }

    @PreAuthorize("hasAuthority('REMOVE_USER')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<UserDto> delete(@PathVariable Long userId) {
        return ResponseEntity.ok(new UserDto(userService.delete(userId)));
    }

    @PreAuthorize("hasAuthority('GET_USER_ROOMS')")
    @RequestMapping(value = "/{userId}/rooms", method = RequestMethod.GET)
    public ResponseEntity<List<RoomDto>> getUserRooms(@PathVariable("userId") long id) {

        return ResponseEntity.ok(userService.findById(id).getRooms().stream().map(RoomDto::new).collect(Collectors.toList()));
    }
}
