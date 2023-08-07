package cn.daoren.websocket.message;

import lombok.Data;

@Data
public class SendResponse implements Message {
    public static final String TYPE = "SEND_RESPONSE";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;

    public SendResponse setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public SendResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public SendResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
