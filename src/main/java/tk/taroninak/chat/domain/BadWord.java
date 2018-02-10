package tk.taroninak.chat.domain;

import javax.persistence.*;

@Entity
@Table(name = "bad_words")
public class BadWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text", unique = true)
    private String text;

    public BadWord() {

    }

    public BadWord(String text) {
        this.text = text;
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

    @Override
    public String toString() {
        return "BadWord{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
