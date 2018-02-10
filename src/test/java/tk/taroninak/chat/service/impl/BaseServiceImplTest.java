package tk.taroninak.chat.service.impl;

import org.assertj.core.api.Assertions;
import tk.taroninak.chat.domain.BadWord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseServiceImplTest {
    @Autowired
    private BaseServiceImpl<BadWord> baseServiceImpl;
    private List<String> badWords = new ArrayList<>();


    @Before
    public void setUp() {
        BadWord word1 = new BadWord("fu**");
        baseServiceImpl.create(word1);


        BadWord word2 = new BadWord("as*");
        baseServiceImpl.create(word2);

        badWords.add(word1.getText());
        badWords.add(word2.getText());

    }

    @Test
    public void findAllTest() {
        List<String> words = baseServiceImpl.findAll(0, 2).stream().map(BadWord::getText).collect(Collectors.toList());
        if(!words.containsAll(badWords)){
            Assert.fail("Can not find all words");
        }

    }

    @Test
    public void createTest() {
    }
}
