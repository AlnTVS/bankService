package com.alntvs.common.models;

import com.alntvs.common.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Message {

    private Map<String,String> message;
    private TransactionStatus status;

    public Message() {
    }

    public Message(Map<String,String> message, TransactionStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
