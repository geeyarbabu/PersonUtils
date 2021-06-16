package accala.test.transformer;

import accala.test.model.AddressRequest;
import accala.test.persistence.entity.AddressEntity;
import accala.test.persistence.entity.PersonEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressRequestToPersonEntityTransformer {

    public PersonEntity transform(final AddressRequest addressRequest, final PersonEntity personEntity){

        personEntity.getAddresses().add(transformAddressRequestToAddressEntity(addressRequest));

        return PersonEntity.builder()
                .id(personEntity.getId())
                .firstName(personEntity.getFirstName())
                .lastName(personEntity.getLastName())
                .addresses(personEntity.getAddresses())
                .build();
    }

    public PersonEntity transformForEditAddress(final AddressRequest request, final PersonEntity personEntity, final UUID addressId){

        Set<AddressEntity> addresses = personEntity.getAddresses();

        List<AddressEntity> currentAddress = addresses.stream().filter(s -> s.getId().equals(addressId)).collect(Collectors.toList());
        addresses.remove(currentAddress.get(0));

        addresses.add(transformAddressRequestToAddressEntityWithId(request,addressId));

        return PersonEntity.builder()
                .id(personEntity.getId())
                .firstName(personEntity.getFirstName())
                .lastName(personEntity.getLastName())
                .addresses(personEntity.getAddresses())
                .build();
    }

    private AddressEntity transformAddressRequestToAddressEntity(AddressRequest request){
        return AddressEntity.builder()
                .street(request.getStreet())
                .city(request.getCity())
                .postalCode(request.getPostalCode())
                .state(request.getState())
                .build();
    }

    private AddressEntity transformAddressRequestToAddressEntityWithId(AddressRequest request, UUID id){
        return AddressEntity.builder()
                .id(id)
                .street(request.getStreet())
                .city(request.getCity())
                .postalCode(request.getPostalCode())
                .state(request.getState())
                .build();
    }
}
