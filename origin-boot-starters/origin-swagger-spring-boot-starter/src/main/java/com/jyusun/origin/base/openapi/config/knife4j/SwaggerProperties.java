package com.jyusun.origin.base.openapi.config.knife4j;

import com.jyusun.origin.core.common.constant.SystemConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * swagger 属性配置
 *
 * @author jyusun
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "origin-system.swagger")
public class SwaggerProperties {

    /**
     * isEnabled：true or false
     */
    private Boolean enabled = true;

    /**
     * title
     */
    private String title = "Origin Platform";

    /**
     * description
     */
    private String description = "Description Project";

    /**
     * termsOfServiceUrl
     */
    private String termsOfServiceUrl;

    /**
     * contact
     */
    private SwaggerProperties.ContactInfo contact = new SwaggerProperties.ContactInfo();

    /**
     * license
     */
    private String license;

    /**
     * licenseUrl
     */
    private String licenseUrl;

    /**
     * version
     */
    private String version = "1.0.0";

    /**
     * base package
     */
    private String basePackage = SystemConstant.BASE_WEB;

    public Info buildApiInfo() {
        Info info = new Info();
        info.setTitle(this.getTitle());
        info.setDescription(this.getDescription());
        info.setTermsOfService(this.getTermsOfServiceUrl());
        info.setContact(
                ContactInfo.creator().name(contact.getName()).url(contact.getUrl()).email(contact.getEmail()).build());
        info.setVersion(this.getVersion());
        return info;
    }

    @Setter
    @Getter
    private static class ContactInfo {

        /**
         * name
         */
        @Schema(description = "联系人")
        private String name;

        /**
         * url
         */
        @Schema(description = "URL")
        private String url;

        /**
         * email
         */
        @Schema(description = "联络邮箱")
        private String email;

        public static ContactBuilder creator() {
            return new ContactBuilder();
        }

        public static class ContactBuilder {

            @Schema(description = "联系人")
            private String name;

            @Schema(description = "URL")
            private String url;

            @Schema(description = "联络邮箱")
            private String email;

            private ContactBuilder name(String name) {
                this.name = name;
                return this;
            }

            private ContactBuilder url(String url) {
                this.url = url;
                return this;
            }

            private ContactBuilder email(String email) {
                this.email = email;
                return this;
            }

            private Contact build() {
                Contact contactInfo = new Contact();
                contactInfo.setName(name);
                contactInfo.setUrl(url);
                contactInfo.setEmail(email);
                return contactInfo;
            }

        }

    }

}
