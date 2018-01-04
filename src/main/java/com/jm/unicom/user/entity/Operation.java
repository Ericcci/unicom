package com.jm.unicom.user.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Operation
 *
 * @author Eric
 * @date 2018/1/1
 */
@Data
//@Entity
//@Table(name = "t_operation")
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "operation-uuid", strategy = "uuid")
    @GeneratedValue(generator = "operation-uuid")
    private String uuid;

    @Column(columnDefinition = "varchar(100) COMMENT '操作名称'")
    private String operationName;

    @Column(columnDefinition = "varchar(100) COMMENT '操作地址'")
    private String operationUrl;

    @Column(columnDefinition = "varchar(100) COMMENT '操作描述'")
    private String operationDescription;
}
