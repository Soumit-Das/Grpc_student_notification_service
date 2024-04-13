package com.grpcpractice.Service;

import com.grpcpractice.Model.Notifications;
import com.grpcpractice.PaginationRequest;
import com.grpcpractice.PaginationResponse;
import com.grpcpractice.Repository.NotificationRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notifications getNotificationById(int notificationId) {
        Optional<Notifications> noti = notificationRepository.findById(notificationId);
        return noti.get();
    }

    @Override
    public List<Notifications> getAllNotificationsPagewise(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Notifications> page = notificationRepository.findAll(pageable);
        return page.getContent();

    }

}
