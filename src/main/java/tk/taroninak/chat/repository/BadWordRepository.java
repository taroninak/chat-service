package tk.taroninak.chat.repository;

import tk.taroninak.chat.domain.BadWord;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BadWordRepository extends PagingAndSortingRepository<BadWord, Long> {
    //@Query("select * from bad_words where text in (:textList)")
    List<BadWord> findByTextIn(List<String> textList);
}
