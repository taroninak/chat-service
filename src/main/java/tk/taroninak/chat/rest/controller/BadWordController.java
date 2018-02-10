package tk.taroninak.chat.rest.controller;

import tk.taroninak.chat.rest.dto.BadWordDto;
import tk.taroninak.chat.domain.BadWord;
import tk.taroninak.chat.service.BadWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( value = "/bad-words", produces = MediaType.APPLICATION_JSON_VALUE )
public class BadWordController {
    @Autowired
    private BadWordService badWordService;

    @PreAuthorize("hasAuthority('LIST_BAD_WORDS')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<String>> list(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, @RequestParam(value = "size", required = false, defaultValue = "${request.defaultPageSize:10}") Integer size) {
        return ResponseEntity.ok(badWordService
                .findAll(page, size)
                .stream()
                .map(BadWord::getText)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasAuthority('ADD_BAD_WORD')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<List<BadWordDto>> create(@RequestBody List<String> badWords) {
        List<BadWordDto> words = badWords.stream().map(BadWord::new).map(badWordService::create).map(BadWord::getText).map(BadWordDto::new).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(words);
    }

    @PreAuthorize("hasAuthority('EDIT_BAD_WORD')")
    @RequestMapping(value = "/{wordId}", method = RequestMethod.PUT)
    public ResponseEntity<BadWordDto> put(@RequestBody BadWordDto badWordDto) {
        return ResponseEntity.ok(new BadWordDto(badWordService.create(badWordDto.toBadWord())));
    }

    @PreAuthorize("hasAuthority('REMOVE_BAD_WORD')")
    @RequestMapping(value = "/{wordId}", method = RequestMethod.DELETE)
    public ResponseEntity<BadWordDto> delete(@PathVariable Long wordId) {
        return ResponseEntity.ok(new BadWordDto(badWordService.delete(wordId)));
    }
}
