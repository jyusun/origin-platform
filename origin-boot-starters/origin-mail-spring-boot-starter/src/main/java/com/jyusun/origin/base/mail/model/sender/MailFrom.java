package com.jyusun.origin.base.mail.model.sender;

import lombok.Data;

@Data
public class MailFrom {

    private String from;

    private String nickname;

    public MailFrom(String from, String nickname) {
        this.from = from;
        this.nickname = nickname;
    }

}