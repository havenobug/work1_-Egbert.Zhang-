package com.zy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

    @Entity(name = "message")
    public class Message {
        @Id
        @GeneratedValue
        private long id;
        private String leaveMessage;
        public long getId() {
            return id;
        }

        public Message setId(long id) {
            this.id = id;
            return this;
        }

        public String getLeaveMessage() {
            return leaveMessage;
        }

        public Message setLeaveMessage(String leaveMessage) {
            this.leaveMessage = leaveMessage;
            return this;
        }
    }
