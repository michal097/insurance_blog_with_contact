package pl.ubezpieczenia.strona.contact.a_contact;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<ContactMailDao, Long > {

}
