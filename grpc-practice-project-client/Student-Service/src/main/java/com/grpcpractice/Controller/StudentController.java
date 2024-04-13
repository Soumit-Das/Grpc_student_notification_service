package com.grpcpractice.Controller;

import com.google.protobuf.Descriptors;
import com.grpcpractice.Model.Notifications;
import com.grpcpractice.NotificationRequest;
import com.grpcpractice.NotificationResponse;
import com.grpcpractice.PaginationRequest;
import com.grpcpractice.PaginationResponse;
import com.grpcpractice.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Endpoint to get notification details by ID
    @GetMapping("/notification-by-Id")
    public Map<Descriptors.FieldDescriptor, Object> getNotificationById(@RequestParam int notification_id) {
        // Create a notification request with the given ID
        NotificationRequest req = NotificationRequest.newBuilder().setNotificationId(notification_id).build();
        // Call the service layer to get notification details
        return studentService.getNotificationById(req);
    }

    // Endpoint to get notifications pagewise
    @GetMapping("/notificationsPage")
    public List<Notifications> getNotificationsPagewise(@RequestParam int pageNumber, @RequestParam int pageSize) {
        // Call the service layer to get paginated notifications
        return studentService.getNotificationsPagewise(pageNumber, pageSize);
    }

}


/*

@GetMapping("/notification-by-Id"): This annotation maps the method to the /students/notification-by-Id endpoint for HTTP GET requests. It takes a notification_id as a request parameter and calls the getNotificationById method of the StudentService to fetch and return the notification details.

@GetMapping("/notificationsPage"): This annotation maps the method to the /students/notificationsPage endpoint for HTTP GET requests. It takes pageNumber and pageSize as request parameters and calls the getNotificationsPagewise method of the StudentService to fetch and return notifications pagewise.

@Autowired private StudentService studentService;: This annotation injects an instance of StudentService into the controller, allowing the controller to use the methods defined in the service.

NotificationRequest req = NotificationRequest.newBuilder().setNotificationId(notification_id).build();: This code creates a NotificationRequest message with the provided notification_id.

return studentService.getNotificationById(req);: This code calls the getNotificationById method of the StudentService with the created request message and returns the result.

return studentService.getNotificationsPagewise(pageNumber, pageSize);: This code calls the getNotificationsPagewise method of the StudentService with the provided pageNumber and pageSize and returns the paginated notifications.

 */

