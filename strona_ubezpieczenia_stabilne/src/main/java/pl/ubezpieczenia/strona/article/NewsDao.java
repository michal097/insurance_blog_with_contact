package pl.ubezpieczenia.strona.article;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "news")
public class NewsDao {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "description")
    private String description;
    @Column(name = "tag")
    private String tag;
    @Column(name = "image")
    private String urlToImage;
    @Column(name = "accepted")
    private Boolean isAccepted;
    @Column(name ="fixed_name")
    private String fixedName;
    private LocalDate timeStamp;

    public LocalDate getTimeStamp(){
        return this.timeStamp;
    }


    public String getFixedName() {
        return fixedName = title.replace(" ", "-");
    }

    public void setFixedName(String fixedName) {
        this.fixedName = fixedName.replace(" ", "-").toLowerCase();
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public NewsDao(){
    this.timeStamp=LocalDate.now();
    }

    public Boolean getIsAccepted(){
        return isAccepted;
    }
    public void setIsAccepted(Boolean isAccepted){
        this.isAccepted=isAccepted;
    }

public Long getId(){
        return id;
}
    public String getTitle() {
        return title;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
