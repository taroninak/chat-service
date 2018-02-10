package tk.taroninak.chat.service.impl;

import tk.taroninak.chat.domain.BadWord;
import tk.taroninak.chat.repository.BadWordRepository;
import tk.taroninak.chat.service.BadWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadWordServiceImpl extends BaseServiceImpl<BadWord> implements BadWordService {
    @Autowired
    private BadWordRepository badWordRepository;

    @Override
    public boolean containsBadWords(List<String> words) {
        return badWordRepository.findByTextIn(words).isEmpty();
    }
}
