package hcmute.it.furnitureshop.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private int reviewId;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private String content;

    private int rating;

    private Date date;
}
