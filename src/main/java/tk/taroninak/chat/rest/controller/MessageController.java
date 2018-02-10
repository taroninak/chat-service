package tk.taroninak.chat.rest.controller;

import tk.taroninak.chat.rest.dto.MessageDto;
import tk.taroninak.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE )
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PreAuthorize("hasAuthority('LIST_MESSAGES')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<MessageDto>> list(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, @RequestParam(value = "size", required = false, defaultValue = "${request.defaultPageSize:10}") Integer size) {
        return ResponseEntity.ok(messageService
                .findAll(page, size)
                .stream()
                .map(MessageDto::new)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasAuthority('CREATE_MESSAGE')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<MessageDto> create(@RequestBody MessageDto messageDto) {
        MessageDto result = new MessageDto(messageService.create(messageDto.toMessage()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @PreAuthorize("hasAuthority('EDIT_MESSAGE')")
    @RequestMapping(value = "/{messageId}", method = RequestMethod.PUT)
    public ResponseEntity<MessageDto> put(@RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(new MessageDto(messageService.create(messageDto.toMessage())));
    }

    @PreAuthorize("hasAuthority('REMOVE_MESSAGE')")
    @RequestMapping(value = "/{messageId}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageDto> delete(@PathVariable Long messageId) {
        return ResponseEntity.ok(new MessageDto(messageService.delete(messageId)));
    }
}
