package hcmute.it.furnitureshop.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String password;
    private String name;
    private String image;
    private String address;
    private String address2;
    private String address3;
    private Date createDate;
    private String role;

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Order> orders;

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Review> reviews;

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Notification> notifications;

}