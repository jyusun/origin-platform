package com.jyusun.origin.base.wecom.model.dto.groupchat;

import com.jyusun.origin.core.common.model.BaseCmd;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 * 群组创建
 * access_token	是	调用接口凭证
 * name	否	群聊名，最多50个utf8字符，超过将截断
 * owner	否	指定群主的id。如果不指定，系统会随机从userlist中选一人作为群主
 * userlist	是	群成员id列表。至少2人，至多2000人
 * chatid	否	群聊的唯一标志，不能与已有的群重复；字符串类型，最长32个字符。只允许字符0-9及字母a-zA-Z。如果不填，系统会随机生成群id
 *
 * @author jyusun at 2022-4-12 23:02:25
 */
@Data
@EqualsAndHashCode
public class GroupChatCreate implements BaseCmd {

    @NotBlank(message = "群组名称不允许为空")
    private String name;
    private String owner;
    @Size(min = 2, max = 2000, message ="群成员2~2000之间")
    private List<String> users;
    private String chatid;

}
