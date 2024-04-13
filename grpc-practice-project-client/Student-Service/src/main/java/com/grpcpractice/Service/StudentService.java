package com.grpcpractice.Service;

import com.google.protobuf.Descriptors;
import com.grpcpractice.Model.Notifications;
import com.grpcpractice.Notification;
import com.grpcpractice.NotificationRequest;
import com.grpcpractice.PaginationRequest;

import java.util.List;
import java.util.Map;

public interface StudentService {

    Map<Descriptors.FieldDescriptor,Object> getNotificationById(NotificationRequest request);

    List<Notifications> getNotificationsPagewise(int pageNum, int pageSize);

}
