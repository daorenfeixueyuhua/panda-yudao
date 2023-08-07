package cn.daoren.websocket.message;

import lombok.Data;

@Data
public class AuthRequest implements Message {
    public static final String TYPE = "AUTH_REQUEST";

    /**
     * 认证 Token
     */
    private String accessToken;
}
