package com.grpcpractice.Service;

import com.grpcpractice.Model.Notifications;
import com.grpcpractice.PaginationRequest;
import com.grpcpractice.PaginationResponse;
import io.grpc.stub.StreamObserver;

import java.util.List;

public interface NotificationService {

    Notifications getNotificationById(int notificationId);

    List<Notifications> getAllNotificationsPagewise(int pageNumber, int pageSize);

//    void getAllNotification(PaginationRequest request, StreamObserver<PaginationResponse> responseObserver)

}
