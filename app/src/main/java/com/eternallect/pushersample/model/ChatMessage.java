package com.eternallect.pushersample.model;

/**
 * Created by MOHIT on 15-10-2015.
 */
public class ChatMessage {
        private String fromName, message;
        private boolean isSelf;
        private long timeStamp;

    public ChatMessage(String fromName, String message, boolean isSelf, long timeStamp) {
        this.fromName = fromName;
        this.message = message;
        this.isSelf = isSelf;
        this.timeStamp = timeStamp;
    }

    public ChatMessage() {
        }

        public ChatMessage(String fromName, String message, boolean isSelf) {
            this.fromName = fromName;
            this.message = message;
            this.isSelf = isSelf;
        }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFromName() {
            return fromName;
        }

        public void setFromName(String fromName) {
            this.fromName = fromName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isSelf() {
            return isSelf;
        }

        public void setSelf(boolean isSelf) {
            this.isSelf = isSelf;
        }

}

