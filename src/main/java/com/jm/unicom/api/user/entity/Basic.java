package com.jm.unicom.api.user.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Basic
 *
 * @author Eric
 * @date 2017/12/27
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
class Basic {

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "update_time", columnDefinition = "DATETIME COMMENT '最后更新时间'")
    private Date updateTime;

    @LastModifiedBy
    @Column(name = "update_user", columnDefinition = "varchar(30) COMMENT '最后更新的用户'")
    private String updateUser;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;

    @CreatedBy
    @Column(name = "create_user", columnDefinition = "varchar(30) COMMENT '创建的用户'")
    private String createUser;
}
