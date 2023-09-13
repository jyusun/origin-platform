package com.jyusun.origin.base.mail.model.reader;

import com.google.common.collect.Lists;
import com.jyusun.origin.core.common.util.ArrayUtil;
import com.jyusun.origin.core.common.util.CollectionUtil;
import com.jyusun.origin.core.common.util.ObjectUtil;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import lombok.SneakyThrows;

import java.util.List;

/**
 * 作用描述：folder
 *
 * @author jyusun at 2023/5/4 11:39
 * @since 1.0.0
 */
public class ReceiveFolder {

    private final Folder folder;

    /**
     * 邮件内容
     */
    private List<ReceiveContent> receiveContents;

    private int messageCount;

    private int newCount;

    private int deletedCount;

    private int unreadCount;

    /**
     * 消息集合
     */
    private Message[] messages;

    public ReceiveFolder(Folder folder) {
        this.folder = folder;
        this.init();
    }

    @SneakyThrows
    public void init() {
        this.receiveContents = Lists.newArrayList();
        this.messageCount = this.initMessageCount();
        this.newCount = this.initNewCount();
        this.unreadCount = this.initUnreadCount();
        this.deletedCount = this.initDeletedCount();
    }

    @SneakyThrows
    public int initMessageCount() {
        if (ObjectUtil.isEmpty(this.messageCount) || this.messageCount == 0) {
            return this.messageCount = this.folder.getNewMessageCount();
        }
        return this.messageCount;
    }

    @SneakyThrows
    public int initNewCount() {
        if (ObjectUtil.isEmpty(this.newCount) || this.newCount == 0) {
            return this.folder.getNewMessageCount();
        }
        return this.newCount;
    }

    @SneakyThrows
    public int initUnreadCount() {
        if (ObjectUtil.isEmpty(this.unreadCount) || this.unreadCount == 0) {
            return this.folder.getUnreadMessageCount();
        }
        return this.unreadCount;
    }

    @SneakyThrows
    public int initDeletedCount() {
        if (ObjectUtil.isEmpty(this.deletedCount) || this.deletedCount == 0) {
            return this.folder.getDeletedMessageCount();
        }
        return this.deletedCount;
    }

    @SneakyThrows
    public int getMessageCount() {
        if (ObjectUtil.isEmpty(this.messageCount) || this.messageCount == 0) {
            this.messageCount = this.folder.getNewMessageCount();
        }
        return this.messageCount;
    }

    @SneakyThrows
    public int getNewCount() {
        if (ObjectUtil.isEmpty(this.newCount) || this.newCount == 0) {
            this.newCount = this.folder.getNewMessageCount();
        }
        return this.newCount;
    }

    @SneakyThrows
    public int getUnreadCount() {
        if (ObjectUtil.isEmpty(this.unreadCount) || this.unreadCount == 0) {
            this.unreadCount = this.folder.getUnreadMessageCount();
        }
        return this.unreadCount;
    }

    @SneakyThrows
    public int getDeletedCount() {
        if (ObjectUtil.isEmpty(this.deletedCount) || this.deletedCount == 0) {
            this.deletedCount = this.folder.getDeletedMessageCount();
        }
        return this.deletedCount;
    }

    public List<ReceiveContent> findMailContents() {
        if (CollectionUtil.isEmpty(this.receiveContents)) {
            this.receiveContents = Lists.newArrayList();
            Message[] messages = this.getMessage();
            for (Message message : messages) {
                this.receiveContents.add(new ReceiveContent("uid", message));
            }
        }
        return this.receiveContents;
    }

    public List<ReceiveContent> findMailContents(int start, int end) {
        if (CollectionUtil.isEmpty(this.receiveContents)) {
            this.receiveContents = Lists.newArrayList();
            Message[] messages = this.getMessage(start, end);
            for (Message message : messages) {
                this.receiveContents.add(new ReceiveContent("uid", message));
            }
        }
        return this.receiveContents;
    }

    /**
     * 获取消息
     * @return
     */
    @SneakyThrows
    private Message[] getMessage() {
        if (ArrayUtil.isEmpty(this.messages)) {
            // 如果没有打开则 打开
            if (!this.folder.isOpen()) {
                this.folder.open(Folder.READ_WRITE);
            }
            return this.folder.getMessages();
        }
        return this.messages;
    }

    /**
     * 获取消息
     * @return
     */
    @SneakyThrows
    private Message[] getMessage(int start, int end) {
        if (ArrayUtil.isEmpty(this.messages)) {
            // 如果没有打开则 打开
            if (!this.folder.isOpen()) {
                this.folder.open(Folder.READ_WRITE);
            }
            this.messages = this.folder.getMessages(start, end);
        }
        return this.messages;
    }

}
