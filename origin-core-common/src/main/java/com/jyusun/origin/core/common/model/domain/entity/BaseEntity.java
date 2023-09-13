package com.jyusun.origin.core.common.model.domain.entity;

import java.io.Serializable;

/**
 * 基础领域实体
 *
 * @param <T>
 * @author jyusun at 19:33:37
 */
public interface BaseEntity<T> extends Serializable {

    /**
     * 实体通过唯一ID比较
     * @param other 另一个实体
     * @return true 只要ID相同就返回ture，忽略属性
     */
    boolean sameIdentityAs(T other);

}
