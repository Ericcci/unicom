package com.jm.unicom.api.user.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Resource
 *
 * @author Eric
 * @date 2018/1/1
 */
@Data
//@Entity
//@Table(name = "t_resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "resource-uuid", strategy = "uuid")
    @GeneratedValue(generator = "resource-uuid")
    private String uuid;

    @Column(columnDefinition = "varchar(100) COMMENT '资源名称'")
    private String resourceName;

    @Column(columnDefinition = "varchar(100) COMMENT '资源类型'")
    private String resourceType;
}
