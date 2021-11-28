package com.glen.address.repo;

import com.glen.address.model.PhoneBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneBookRepo extends MongoRepository<PhoneBook, String> {
    Optional<PhoneBook> findByNumber(int number );

    List<PhoneBook> findByFirst(String first );

    List<PhoneBook> findByLast(String last );

    @Query(value = "{'type' : ?0 }")
    Page<PhoneBook> getPagePhoneBookEntryByType(String type,  Pageable paging);
}
