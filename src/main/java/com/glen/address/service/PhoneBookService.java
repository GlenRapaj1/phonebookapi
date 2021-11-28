package com.glen.address.service;

import com.glen.address.model.PhoneBook;
import com.glen.address.repo.PhoneBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageImpl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneBookService {

    @Autowired
    private PhoneBookRepo phoneBookRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    public PhoneBook getPhoneBookEntryById(String id){
        Optional<PhoneBook> phoneBook = phoneBookRepo.findById(id);
        if(phoneBook.isPresent()){
            return phoneBook.get();
        }
        return null;
    }

    public Optional<PhoneBook> getPhoneBookEntryByNumber(int number){
        Optional<PhoneBook> phoneBook = phoneBookRepo.findByNumber(number);
        return phoneBook;
    }

    public PhoneBook addPhoneBookEntry(PhoneBook phoneBook){
        return mongoTemplate.save(phoneBook);
//        return phoneBookRepo.save(phoneBook);
    }

    public void deletePhoneBookEntryById(String id){
        PhoneBook phoneBook = mongoTemplate.findById(id, PhoneBook.class);
        mongoTemplate.remove(phoneBook);
    }

    public Page<PhoneBook> getAllPhoneBookEntry(int page, int size){
        Query query = new Query();
        long count = mongoTemplate.count(query.skip(-1).limit(-1), PhoneBook.class);
        Pageable pageable = PageRequest.of(page, size, Sort.by("first"));
        query.with( pageable );
        query.skip( pageable.getPageSize() * pageable.getPageNumber() );
        query.limit( pageable.getPageSize() );
        List<PhoneBook> phoneBooks = mongoTemplate.find(query, PhoneBook.class);
        return new PageImpl<PhoneBook>(phoneBooks, pageable, count);
    }

    public PhoneBook modifyPhoneBookEntry(PhoneBook phoneBook){
        return mongoTemplate.save(phoneBook);
    }

    public Page<PhoneBook> getPagePhoneBookEntryByType(int page, int size, String type){
        Query query = new Query();
        query.addCriteria( where("type").is(type));
        long count = mongoTemplate.count(query.skip(-1).limit(-1), PhoneBook.class);
        Pageable pageable = PageRequest.of(page, size, Sort.by("first"));
        query.with( pageable );
        query.skip( pageable.getPageSize() * pageable.getPageNumber() );
        query.limit( pageable.getPageSize() );
        List<PhoneBook> phoneBooks = mongoTemplate.find(query, PhoneBook.class);
        return new PageImpl<PhoneBook>(phoneBooks, pageable, count);
    }

    public Page<PhoneBook> getAllPhoneBookEntryWithoutSort(int page, int size){
        Query query = new Query();
        long count = mongoTemplate.count(query.skip(-1).limit(-1), PhoneBook.class);
        Pageable pageable = PageRequest.of(page, size);
        query.with( pageable );
        query.skip( pageable.getPageSize() * pageable.getPageNumber() );
        query.limit( pageable.getPageSize() );
        List<PhoneBook> phoneBooks = mongoTemplate.find(query, PhoneBook.class);
        return new PageImpl<PhoneBook>(phoneBooks, pageable, count);
    }

    public Page<PhoneBook> getAllPhoneBookEntrySortedByLast(int page, int size){
        // Sorting by last name
        Query query = new Query();
        long count = mongoTemplate.count(query.skip(-1).limit(-1), PhoneBook.class);
        Pageable pageable = PageRequest.of(page, size, Sort.by( "last" ));
        query.with( pageable );
        query.skip( pageable.getPageSize() * pageable.getPageNumber() );
        query.limit( pageable.getPageSize() );
        List<PhoneBook> phoneBooks = mongoTemplate.find(query, PhoneBook.class);
        return new PageImpl<PhoneBook>(phoneBooks, pageable, count);
    }

    public List<PhoneBook> getAllPhoneBookEntryByFirstName(String first){
        Query query = new Query();
        query.addCriteria( where("first").is( first ) );
        return mongoTemplate.find(query, PhoneBook.class);
    }

    public List<PhoneBook> getAllPhoneBookEntryByLastName(String last){
        Query query = new Query();
        query.addCriteria( where("last").is( last ) );
        return mongoTemplate.find(query, PhoneBook.class);
    }
}
