package com.grpcpractice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Students {

    @Id
    private int studentId;
    private String name;
    private int age;
    private String batch;
    private List<Address> address;
    private LocalDate createdAt;



}
