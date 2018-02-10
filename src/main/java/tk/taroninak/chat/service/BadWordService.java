package tk.taroninak.chat.service;

import tk.taroninak.chat.domain.BadWord;

import java.util.List;

public interface BadWordService extends BaseService<BadWord>{
    boolean containsBadWords(List<String> words);
}
