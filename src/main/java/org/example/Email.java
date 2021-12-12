package org.example;

import java.time.LocalDateTime;

public class Email {


    private String to;
    private String from;
    private String subject;
    private LocalDateTime timeSent;
    private String text;

    public Email(String to, String from, String subject, LocalDateTime timeSent, String text)
    {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.timeSent = timeSent;
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(LocalDateTime timeSent) {
        this.timeSent = timeSent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "------------------------------------------------------------------------------------------------------------------------\n" +
                "Email{" +
                "to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                ", timeSent=" + timeSent +
                ", \n\ntext=\n\n'" + text + '\'' +
                '}' +
                "\n------------------------------------------------------------------------------------------------------------------------";
    }
}
