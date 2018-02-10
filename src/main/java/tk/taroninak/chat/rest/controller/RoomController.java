package tk.taroninak.chat.rest.controller;

import tk.taroninak.chat.rest.dto.MessageDto;
import tk.taroninak.chat.rest.dto.RoomDto;
import tk.taroninak.chat.rest.dto.UserDto;
import tk.taroninak.chat.rest.validator.RoomAddOrRemoveUserValidator;
import tk.taroninak.chat.rest.validator.RoomCreateValidator;
import tk.taroninak.chat.domain.Room;
import tk.taroninak.chat.service.MessageService;
import tk.taroninak.chat.service.RoomService;
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
@RequestMapping( value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE )
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RoomCreateValidator roomCreateValidator;

    @Autowired
    private RoomAddOrRemoveUserValidator roomAddOrRemoveUserValidator;


    @PreAuthorize("hasAuthority('LIST_ROOMS')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<RoomDto>> list(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, @RequestParam(value = "size", required = false, defaultValue = "${request.defaultPageSize:10}") Integer size) {
        return ResponseEntity.ok(roomService
                .findAll(page, size)
                .stream()
                .map(RoomDto::new)
                .collect(Collectors.toList()));
    }


    @PreAuthorize("hasAuthority('GET_ROOM')")
    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET)
    public ResponseEntity<RoomDto> get(@PathVariable("roomId") long roomId) {
        Room room = roomService.findById(roomId);

        //TODO: Throw appropriate exception and handle it globally
        if (room == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new RoomDto(room));
    }

    @PreAuthorize("hasAuthority('CREATE_ROOM')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<RoomDto> create(@RequestBody RoomDto roomDto, BindingResult bindingResult) {

        roomCreateValidator.validate(roomDto, bindingResult);

        //TODO: Throw appropriate exception and handle it globally
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Room room = roomService.create(roomDto.toRoom());

        return ResponseEntity.status(HttpStatus.CREATED).body(new RoomDto(room));
    }

    @PreAuthorize("hasAuthority('EDIT_ROOM')")
    @RequestMapping(value = "/{roomId}", method = RequestMethod.PUT)
    public ResponseEntity<RoomDto> put(@PathVariable long roomId, @RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(new RoomDto(roomService.create(roomDto.toRoom())));
    }

    @PreAuthorize("hasAuthority('REMOVE_ROOM')")
    @RequestMapping(value = "/{roomId}", method = RequestMethod.DELETE)
    public ResponseEntity<RoomDto> delete(@PathVariable long roomId) {
        return ResponseEntity.ok(new RoomDto(roomService.delete(roomId)));
    }

    @PreAuthorize("hasAuthority('GET_ROOM_USERS')")
    @RequestMapping(value = "/{roomId}/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getRoomUsers(@PathVariable("roomId") long roomId) {

        return ResponseEntity.ok(roomService
                .findById(roomId)
                .getUsers()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasAuthority('ADD_ROOM_USERS')")
    @RequestMapping(value = "/{roomId}/users", method = RequestMethod.POST)
    public ResponseEntity<List<UserDto>> addUserToRoom(@PathVariable("roomId") long roomId, @RequestBody UserDto userDto, BindingResult bindingResult) {
        roomAddOrRemoveUserValidator.validate(userDto, bindingResult);

        //TODO: Throw appropriate exception and handle it globally
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok(roomService.addRoomUser(roomId, userDto.getId())
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasAuthority('REMOVE_ROOM_USERS')")
    @RequestMapping(value = "/{roomId}/users", method = RequestMethod.DELETE)
    public ResponseEntity<List<UserDto>> removeUserFromRoom(@PathVariable("roomId") long roomId, @RequestBody UserDto userDto, BindingResult bindingResult) {
        roomAddOrRemoveUserValidator.validate(userDto, bindingResult);

        //TODO: Throw appropriate exception and handle it globally
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok(roomService.removeRoomUser(roomId, userDto.getId())
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasAuthority('LIST_ROOM_MESSAGES')")
    @RequestMapping(value = "/{roomId}/messages", method = RequestMethod.GET)
    public ResponseEntity<List<MessageDto>> listRoomMessages(@PathVariable("roomId") long roomId, @RequestParam(value = "page", required = false, defaultValue = "0") Integer page, @RequestParam(value = "size", required = false, defaultValue = "${request.defaultPageSize:10}") Integer size) {
        return ResponseEntity.ok(messageService
                .findRoomMessages(roomId, page, size)
                .stream()
                .map(MessageDto::new)
                .collect(Collectors.toList()));
    }
}
