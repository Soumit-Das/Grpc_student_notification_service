package com.grpcpractice.Service;

import com.google.protobuf.Descriptors;
import com.grpcpractice.*;
import com.grpcpractice.Model.Notifications;
import com.grpcpractice.Model.Students;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    // Create a gRPC channel to connect to the server
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
            .usePlaintext() // Use plaintext communication (no encryption)
            .defaultLoadBalancingPolicy("round_robin") // Use round-robin load balancing
            .build();

    // Create a blocking stub for making synchronous gRPC calls
    StudentNotificationServiceGrpc.StudentNotificationServiceBlockingStub stub = StudentNotificationServiceGrpc.newBlockingStub(channel);

    // Method to get notification details by ID
    @Override
    public Map<Descriptors.FieldDescriptor, Object> getNotificationById(NotificationRequest request) {
        // Make a gRPC call to get notification details by ID
        NotificationResponse response = stub.getNotificationDetailsById(request);
        // Return the notification details as a map
        return response.getAllFields();
    }

    // Method to get notifications pagewise
    @Override
    public List<Notifications> getNotificationsPagewise(int pageNum, int pageSize) {
        // Create a pagination request with page number and page size
        PaginationRequest pageRequest = PaginationRequest.newBuilder()
                .setPageNumber(pageNum)
                .setPageSize(pageSize)
                .build();
        // Make a gRPC call to get paginated notifications
        PaginationResponse responseList = stub.getNotificationsPagewise(pageRequest);
        // Convert the gRPC response to a list of Notifications objects
        List<Notification> notificationsResponseList = responseList.getNotificationsList();
        List<Notifications> notifications = new ArrayList<>();
        for (Notification notificationsResponse : notificationsResponseList) {
            notifications.add(mapToNotification(notificationsResponse));
        }
        return notifications;
    }

    // Helper method to map Notification gRPC message to Notifications object
    private Notifications mapToNotification(Notification notificationsResponse) {
        Notifications notification = new Notifications();
        notification.setNotificationId(notificationsResponse.getNotificationId());
        notification.setNotificationType(notificationsResponse.getNotificationType());
        notification.setCreatedOn(notificationsResponse.getCreatedOn());
        Student studentResponse = notificationsResponse.getPayload();
        Students student = new Students(studentResponse);
        notification.setPayload(student);
        return notification;
    }

}




/*

ManagedChannel: Represents a connection to the gRPC server. We need it to establish a connection and communicate with the server. It works by providing methods to create and manage the connection, including handling the underlying network communication.

Load Balancing: Load balancing is a technique used to distribute incoming network traffic across multiple servers. In this code, defaultLoadBalancingPolicy("round_robin") specifies that the client should use round-robin load balancing, where each request is distributed to servers in a circular order.

StudentNotificationServiceBlockingStub: This is a blocking stub generated by gRPC that provides a synchronous way to make RPC (Remote Procedure Call) calls to the server. We use it to send requests to the server and wait for the response. It works by providing methods that correspond to the RPC methods defined in the gRPC service, allowing us to make calls as if they were local method calls.

Descriptors.FieldDescriptor: This is a class from the Protocol Buffers library that represents a field in a message type. It is used to describe the fields in a message and provides metadata about the field, such as its name, number, and type. In this code, it is used to access the fields of a message in a generic way, allowing us to extract the fields and their values dynamically.

 */
