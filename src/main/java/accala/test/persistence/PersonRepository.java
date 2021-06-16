package accala.test.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import accala.test.persistence.entity.PersonEntity;

import java.util.UUID;

public interface PersonRepository extends PagingAndSortingRepository<PersonEntity, UUID> {

}
