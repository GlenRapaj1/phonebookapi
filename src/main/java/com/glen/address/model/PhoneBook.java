package com.glen.address.model;

import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Document
public class PhoneBook {

    @Id
    private String id;
    private String type;
    @NonNull
    @Indexed(unique = true )
    private Integer number;
    @NonNull
    private String first;
    @NonNull
    private String last;

    public PhoneBook(String type, @NonNull Integer number, @NonNull String first, @NonNull String last) {
        this.type = type;
        this.number = number;
        this.first = first;
        this.last = last;
    }
}
