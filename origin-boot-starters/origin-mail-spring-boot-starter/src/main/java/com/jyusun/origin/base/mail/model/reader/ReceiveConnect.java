package com.jyusun.origin.base.mail.model.reader;

import com.jyusun.origin.base.mail.common.enums.ProtocolEnum;
import com.jyusun.origin.base.mail.model.context.MailConfigContext;
import com.jyusun.origin.base.mail.model.context.props.AccountProperties;
import com.jyusun.origin.base.mail.model.context.props.ServerProperties;
import com.jyusun.origin.core.common.util.ArrayUtil;
import com.jyusun.origin.core.common.util.ObjectUtil;
import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.Store;
import lombok.SneakyThrows;

/**
 * 读取邮件
 *
 * @author jyusun
 */
public class ReceiveConnect {

    private final MailConfigContext configContext;

    private Session session;

    private Store store;

    private Folder folder;

    private Folder[] folders;

    public ReceiveConnect(MailConfigContext configContext) {
        this.configContext = configContext;
    }

    protected Store getStore(Session session) throws NoSuchProviderException {
        String protocol = this.configContext.getProtocol();
        if (protocol == null) {
            protocol = session.getProperty("mail.transport.protocol");
            if (protocol == null) {
                protocol = ProtocolEnum.IMAP.getProtocol();
            }
        }
        return session.getStore(protocol);
    }

    /**
     * 链接邮箱
     * @return
     */
    @SneakyThrows
    public void connect() {
        this.init();
    }

    public void testStoreConnection() throws MessagingException {
        try {
            // this.store = this.connectStore();
        }
        finally {
            this.stop();
        }
    }

    @SneakyThrows
    public Folder getDefaultFolder() {
        this.folder = this.store.getDefaultFolder();
        return this.store.getDefaultFolder();
    }

    @SneakyThrows
    public Folder getFolder(String folderName) {
        this.folder = this.store.getFolder(folderName);
        return this.folder;
    }

    /**
     * 停止连接
     * @return
     */
    @SneakyThrows
    public void stop() {
        if (ObjectUtil.isNotEmpty(this.store) && this.store.isConnected()) {
            this.store.close();
        }
    }

    @SneakyThrows
    public void init() {
        AccountProperties accountProps = this.configContext.getAccountProps();
        ServerProperties serverProps = this.configContext.getServerProps();

        String host = serverProps.getHost();
        Integer port = serverProps.getPort();
        String username = accountProps.getAddress();
        String password = accountProps.getPassword();

        if (ObjectUtil.isEmpty(this.session)) {
            this.session = Session.getInstance(this.configContext.getJavaMailProps());
            this.session.setDebug(false);
        }
        if (ObjectUtil.isEmpty(this.store)) {
            this.store = this.getStore(this.session);
            this.store.connect(host, port, username, password);
        }
        if (ObjectUtil.isEmpty(this.folder)) {
            this.folder = this.store.getDefaultFolder();
        }
        if (ArrayUtil.isEmpty(this.folders)) {
            this.folders = this.folder.list();
        }
    }

}
