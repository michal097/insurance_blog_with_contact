package pl.ubezpieczenia.strona.contact.a_contact;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.stream.Stream;

@Entity
@Table(name = "contact")
public class ContactMailDao {

    @Id
    @GeneratedValue
    @Column(name="client_id")
    private Long clientId;
    @Email(message = "{pl.ubezpieczenia.strona.contact.a_contact.ContactMailDao.clientMail.Email}")
    @NotEmpty(message = "{pl.ubezpieczenia.strona.contact.a_contact.ContactMailDao.clientMail.NotEmpty}")
    @Column(name = "mail")
    private String clientMail;
    @NotEmpty(message = "{pl.ubezpieczenia.strona.contact.a_contact.ContactMailDao.clientName.NotEmpty}")
    @Column(name = "name")
    private String clientName;
    @NotEmpty(message = "{pl.ubezpieczenia.strona.contact.a_contact.ContactMailDao.content.NotEmpty}")
    @Column(name = "client_content")
    private String content;

    public ContactMailDao() {

    }

    public void setClientMail(String clientMail) {
        this.clientMail = clientMail.trim().toLowerCase();
    }

    public void setClientName(String clientName) {
        if(clientName.length()>0) {
            clientName = clientName.substring(0, 1).toUpperCase() + clientName.substring(1).toLowerCase();
            this.clientName = clientName.trim();
        }
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }


    public Long getClientId() {
        return clientId;
    }

    public String getClientMail() {
        return clientMail;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientContent() {
        return content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}