package com.grpcpractice.Repository;

import com.grpcpractice.Model.Notifications;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notifications,Integer> {


}
