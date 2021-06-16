package accala.test.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Person")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;
    private String lastName;

    @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @CollectionTable(name = "person_addresses", joinColumns = @JoinColumn(name = "user_id"))
    Set<AddressEntity> addresses;
}
