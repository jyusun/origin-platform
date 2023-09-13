package com.jyusun.origin.base.wecom.model.dto.groupchat;

import com.alibaba.fastjson.annotation.JSONField;
import com.jyusun.origin.core.common.model.BaseCmd;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * 群组创建
 * access_token	是	调用接口凭证
 * chatid	是	群聊id
 * name	否	新的群聊名。若不需更新，请忽略此参数。最多50个utf8字符，超过将截断
 * owner	否	新群主的id。若不需更新，请忽略此参数
 * add_user_list	否	添加成员的id列表
 * del_user_list	否	踢出成员的id列表
 *
 *
 * @author jyusun at 2022-4-12 23:02:25
 */
@Data
public class GroupChatUpdate implements BaseCmd {

    @NotBlank(message = "聊天编号不允许为空")
    private String chatid;

    private String name;

    private String owner;

    @JSONField(name = "add_user_list")
    private List<String> addUsers;

    @JSONField(name = "del_user_list")
    private List<String> delUsers;

}
