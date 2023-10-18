package hcmute.it.furnitureshop.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;


    String description;

    String state;

}