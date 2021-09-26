package htwb.ai.SIAB.songmicroservice.domain;


import javax.persistence.*;
import java.util.Set;

/**
 * User Model Class
 *
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    private String userId;

    @Column(name = "firstName")
    private String firstname;

    @Column(name = "lastName")
    private String lastname;

    @Column(name = "password")
    private String password;

/*    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL,orphanRemoval=true)
    private Set<SongListe> songListeSet;*/


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
