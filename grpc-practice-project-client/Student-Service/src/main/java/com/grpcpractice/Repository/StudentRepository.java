package com.grpcpractice.Repository;

//import com.grpcpractice.Model.Student;
import com.grpcpractice.Model.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Students,Integer> {

}
