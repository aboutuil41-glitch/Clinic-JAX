package ma.youcode.clinic.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Column(name = "email")
    private String Email;

    @Column(name = "password")
    private String Password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole Role;

    protected User() { }

    public User(int id, String name, String email, String password, UserRole role) {
        this.id = id;
        this.name = name;
        Email = email;
        Password = password;
        Role = role;
    }

    public enum UserRole {
        Doctor,
        Nurse,
        SPECIALISTE,
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public UserRole getRole() {
        return Role;
    }

    public void setRole(UserRole role) {
        Role = role;
    }

    @OneToOne
    private Specialist specialist;
}