package cn.daoren.websocket.message;

import lombok.Data;

@Data
public class UserJoinNoticeRequest implements Message {
    public static final String TYPE = "USER_JOIN_NOTICE_REQUEST";

    /**
     * 昵称
     */
    private String nickname;

    public UserJoinNoticeRequest setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
}
