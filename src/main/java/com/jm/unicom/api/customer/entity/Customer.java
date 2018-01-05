package com.jm.unicom.api.customer.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.api.customer.entity
 *          <br><b>Date:</b> 2018/1/5 16:53
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "customer-uuid", strategy = "uuid")
    @GeneratedValue(generator = "customer-uuid")
    private String uuid;

    @Column(columnDefinition = "int(25) COMMENT '顾客手机号'")
    private Integer customerPhone;

    @Column(columnDefinition = "int(25) COMMENT '顾客IP'")
    private String customerIp;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;
}
