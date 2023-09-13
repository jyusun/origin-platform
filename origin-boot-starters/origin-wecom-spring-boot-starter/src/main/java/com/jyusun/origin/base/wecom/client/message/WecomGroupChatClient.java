package com.jyusun.origin.base.wecom.client.message;

import com.jyusun.origin.base.wecom.client.WecomAccessTokenClient;
import com.jyusun.origin.base.wecom.config.props.WecomProperties;
import com.jyusun.origin.base.wecom.model.dto.groupchat.GroupChatCreate;
import com.jyusun.origin.base.wecom.model.dto.groupchat.GroupChatCreateDTO;
import com.jyusun.origin.base.wecom.model.dto.groupchat.GroupChatInfoDTO;
import com.jyusun.origin.base.wecom.model.dto.groupchat.GroupChatUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

/**
 * 群聊客户端
 *
 * @author jyusun at 2022-4-12 22:45:08
 */
@RequiredArgsConstructor
public class WecomGroupChatClient {

    private final RestTemplate restTemplate;
    private final WecomProperties wecomProperties;
    private final WecomAccessTokenClient accessTokenClient;

    private static final String URL_GROUP_CREATE = "/appchat/create?access_token={token}";
    private static final String URL_GROUP_UPDATE = "/appchat/update?access_token={token}";
    private static final String URL_GROUP_GET = "/appchat/get?access_token={token}&chatid={groupId}";

    public String token() {
        return accessTokenClient.getToken().getAccessToken();
    }

    private String getUrl(String path) {
        return wecomProperties.getEndpoint() + path;
    }

    /**
     * 群聊新增
     *
     * @param chatCreate 创建参数
     * @return
     */
    public GroupChatCreateDTO create(@Validated GroupChatCreate chatCreate) {
        ResponseEntity<GroupChatCreateDTO> resp = restTemplate.postForEntity(this.getUrl(URL_GROUP_CREATE)
                , chatCreate, GroupChatCreateDTO.class, this.token());
        return resp.getBody();
    }


    /**
     * 群聊更新
     *
     * @param chatUpdate 更新参数
     * @return
     */
    public GroupChatCreateDTO update(@Validated GroupChatUpdate chatUpdate) {
        ResponseEntity<GroupChatCreateDTO> resp = restTemplate.postForEntity(this.getUrl(URL_GROUP_UPDATE)
                , chatUpdate, GroupChatCreateDTO.class, this.token());
        return resp.getBody();
    }

    /**
     * 数据查询
     *
     * @param chatid 群聊编号
     * @return {@link GroupChatInfoDTO}
     */
    public GroupChatInfoDTO getChatInfo(String chatid) {
        return restTemplate.getForObject(this.getUrl(URL_GROUP_GET),
                GroupChatInfoDTO.class, this.token(), chatid);
    }
}
