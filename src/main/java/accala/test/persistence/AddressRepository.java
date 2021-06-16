package accala.test.persistence;

import accala.test.persistence.entity.AddressEntity;
import accala.test.persistence.entity.PersonEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface AddressRepository extends PagingAndSortingRepository<AddressEntity, UUID> {

}
