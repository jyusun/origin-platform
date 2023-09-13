package com.jyusun.origin.base.wecom.model.dto.groupchat;

import com.jyusun.origin.base.wecom.common.result.WocomResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群组创建
 *
 * @author jyusun at 2022-4-12 23:02:25
 */
@Data
@EqualsAndHashCode
public class GroupChatCreateDTO extends WocomResult {

    private String chatid;

}
