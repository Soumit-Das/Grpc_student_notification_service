package com.grpcpractice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notifications {

    @Id
    private int notificationId;
    private String notificationType;
    private Students payload;
    private String createdOn;

    @Override
    public String toString()
    {
        return "Notification : { notificationType : "+this.notificationType+", payload : "+this.payload+", createdOn : "+this.createdOn+" }";
    }
}
