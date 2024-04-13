package com.grpcpractice.Model;

import com.grpcpractice.AddressResponse;
import com.grpcpractice.AddressResponseList;
import com.grpcpractice.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Students {

    @Id
    private int studentId;
    private String name;
    private int age;
    private String batch;
    private List<Address> address;
    private LocalDate createdAt;

    public Students(Student studentResponse) {

        this.name = studentResponse.getName();
        this.age = studentResponse.getAge();
        this.batch = studentResponse.getBatch();
        AddressResponseList addressResponseList = studentResponse.getAddresses();
        List<AddressResponse> addressResponses = addressResponseList.getAddressList();
        address = new ArrayList<>();
        for(AddressResponse addressResponse : addressResponses)
        {
            Address address1 = new Address(addressResponse.getCity(),addressResponse.getPincode());
            address.add(address1);
        }
        System.out.println(address);

    }

    @Override
    public String toString() {
        return "Students{" +
                "studentId= " + studentId +
                ", name= '" + name + '\'' +
                ", age= " + age +
                ", batch= '" + batch + '\'' +
                ", address= " + address +
                ", createdAt= " + createdAt +
                '}';
    }

}
