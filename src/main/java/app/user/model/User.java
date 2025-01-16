package app.user.model;

import app.subscription.model.Subscription;
import app.wallet.model.Wallet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity//анотираме базата от данни
// да създаде една таблица, която да бъде от потребители
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //базата от данни създава ID автоматично
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String profilePicture;
   @Column(unique = true)
    private String email;
   @Column(nullable = false)
    private String password;
@Enumerated(EnumType.STRING)
   private UserRole role;
    @Enumerated(EnumType.STRING)
    private Country country;

    private boolean isActive;

    @Column(nullable = false)
    private LocalDateTime createdOn;
    @Column(nullable = false)
    private LocalDateTime updatedOn;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "owner")
    private List<Subscription> subscriptions=new ArrayList<>();
    //=new ArrayList<>() - Ако User няма subscription,
    // полето се инициализира като празен списък вместо null.


    @OneToMany(fetch = FetchType.EAGER,mappedBy = "owner")
    private List<Wallet> wallets=new ArrayList<>();
}
