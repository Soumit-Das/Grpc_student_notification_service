package com.grpcpractice.Service;

import com.grpcpractice.*;
import com.grpcpractice.Model.Address;
import com.grpcpractice.Model.Notifications;
import com.grpcpractice.Model.Students;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


// Marks this class as a gRPC service
@GrpcService
public class StudentNotificationService extends StudentNotificationServiceGrpc.StudentNotificationServiceImplBase {

    // Autowired notificationService for retrieving notification data
    @Autowired
    private NotificationService notificationService;

    // Method to handle gRPC request for getting notification details by ID
    @Override
    public void getNotificationDetailsById(NotificationRequest request, StreamObserver<NotificationResponse> responseObserver) {
        // Get notification ID from request
        int notificationId = request.getNotificationId();
        // Retrieve notification details from the notification service
        Notifications notifications = notificationService.getNotificationById(notificationId);
        // Create and send a response with the notification ID and student name
        NotificationResponse response = NotificationResponse.newBuilder()
                .setNotificationId(notifications.getNotificationId())
                .setStudentName(notifications.getPayload().getName())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    // Method to handle gRPC request for getting notifications pagewise
    @Override
    public void getNotificationsPagewise(PaginationRequest request, StreamObserver<PaginationResponse> responseObserver) {
        // Retrieve notifications pagewise from the notification service
        List<Notifications> notifications = notificationService.getAllNotificationsPagewise(request.getPageNumber(), request.getPageSize());
        // Build the response with paginated notifications
        PaginationResponse.Builder responseBuilder = PaginationResponse.newBuilder();
        for (Notifications notification : notifications) {
            Notification notificationResponse = mapToNotificationsResponse(notification);
            responseBuilder.addNotifications(notificationResponse);
        }
        // Send the response to the client
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    // Helper method to map Notifications object to NotificationResponse object
    private Notification mapToNotificationsResponse(Notifications notification) {
        Students student = notification.getPayload();
        List<Address> addresses = student.getAddress();
        AddressResponseList.Builder addressBuilder = AddressResponseList.newBuilder();
        for (Address address : addresses) {
            AddressResponse addressResponse = AddressResponse.newBuilder()
                    .setCity(address.getCity())
                    .setPincode(address.getPincode())
                    .build();
            addressBuilder.addAddress(addressResponse);
        }
        Student studentResponse = Student.newBuilder()
                .setName(student.getName())
                .setBatch(student.getBatch())
                .setAge(student.getAge())
                .setAddresses(addressBuilder.build())
                .build();
        return Notification.newBuilder()
                .setNotificationId(notification.getNotificationId())
                .setNotificationType(notification.getNotificationType())
                .setPayload(studentResponse)
                .setCreatedOn(notification.getCreatedOn().toString())
                .build();
    }
}


/*


getNotificationDetailsById: Retrieves notification details by ID and sends a response with the notification ID and student name.

getNotificationsPagewise: Retrieves notifications pagewise and sends a response with paginated notifications.

mapToNotificationsResponse: Maps a Notifications object to a NotificationResponse object, handling nested address objects and building the response.


*/