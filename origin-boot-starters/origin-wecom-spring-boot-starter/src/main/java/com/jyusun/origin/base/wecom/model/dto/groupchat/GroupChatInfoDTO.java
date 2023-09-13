package com.jyusun.origin.base.wecom.model.dto.groupchat;

import com.alibaba.fastjson.annotation.JSONField;
import com.jyusun.origin.base.wecom.common.result.WocomResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 群组创建
 * <p>
 * "chat_info" : {
 * "chatid" : "CHATID",
 * "name" : "NAME",
 * "owner" : "userid2",
 * "userlist" : ["userid1", "userid2", "userid3"]
 * }
 *
 * @author jyusun at 2022-4-12 23:02:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupChatInfoDTO extends WocomResult {

    private ChatInfo chatInfo;

    @Data
    public static class ChatInfo {
        private String chatid;
        private String name;
        private String owner;

        @JSONField(name = "userlist")
        private List<String> users;
    }


}
