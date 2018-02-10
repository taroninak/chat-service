package tk.taroninak.chat.rest.dto;


import tk.taroninak.chat.domain.BadWord;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BadWordDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("text")
    private String text;

    public BadWordDto() {

    }

    public BadWordDto(String text) {
        this.text = text;
    }

    public BadWordDto(BadWord word) {
        setId(word.getId());
        setText(word.getText());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BadWord toBadWord() {
        BadWord word = new BadWord();
        word.setId(getId());
        word.setText(getText());
        return word;
    }
}

