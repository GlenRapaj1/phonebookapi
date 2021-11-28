package com.glen.address.controller;

import com.glen.address.model.PhoneBook;
import com.glen.address.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PhoneBookController {

    @Autowired
    PhoneBookService phoneBookService;

    @GetMapping("/get-phone-entry/{id}")
    public PhoneBook getPhoneEntryById(@PathVariable("id") String id){
        return phoneBookService.getPhoneBookEntryById(id);
    }

    @PostMapping("/add-phone-book-entry")
    public PhoneBook addPhoneBookEntry(@RequestBody PhoneBook phoneBook){
        Optional<PhoneBook> optPhoneBook = phoneBookService.getPhoneBookEntryByNumber(phoneBook.getNumber());
        if(optPhoneBook.isPresent()){
            return null;
        }
        return phoneBookService.addPhoneBookEntry(phoneBook);
    }

    @DeleteMapping("/delete-phone-book-entry-id/{id}")
    public void deletePhoneBookEntryById( @PathVariable("id") String id){ // ResponseEntity<String>
        PhoneBook phoneBook = phoneBookService.getPhoneBookEntryById(id);
        if(phoneBook == null){
//            return new ResponseEntity<>( "Not Found", HttpStatus.NOT_FOUND);
            return;
        }
        phoneBookService.deletePhoneBookEntryById(id);
//        return new ResponseEntity<>( "Deleted", HttpStatus.OK);
    }

    @GetMapping("/get-all-phone-entry/{page}/{size}")
    public Page<PhoneBook> getAllPhoneBookEntry(@PathVariable("page") int page, @PathVariable("size") int size){
        return phoneBookService.getAllPhoneBookEntry(page, size);
    }

    @PutMapping("modify-phone-book-entry/{id}")
    public PhoneBook modifyPhoneBookEntry(@RequestBody PhoneBook phoneBook, @PathVariable("id") String id){
        Optional<PhoneBook> optPhoneBook = phoneBookService.getPhoneBookEntryByNumber(phoneBook.getNumber());
        if(optPhoneBook.isEmpty()){
            return null;
        }
        return phoneBookService.modifyPhoneBookEntry(phoneBook);
    }

    @GetMapping("/get-all-phone-entry-by-type/{page}/{size}/{type}")
    public Page<PhoneBook> getPagePhoneBookEntryByType(@PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("type") String type){
        return phoneBookService.getPagePhoneBookEntryByType(page, size, type);
    }

    @GetMapping("/get-all-phone-entry-by-first-name/{firs}")
    public List<PhoneBook> getAllPhoneBookEntryByFirstName(@PathVariable("firs") String firs){
        return phoneBookService.getAllPhoneBookEntryByFirstName(firs);
    }

    @GetMapping("/get-all-phone-entry-by-last-name/{last}")
    public List<PhoneBook> getAllPhoneBookEntryByLastName(@PathVariable("last") String last){
        return phoneBookService.getAllPhoneBookEntryByLastName(last);
    }
}
