package com.jm.unicom.api.customer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jm.unicom.api.shop.entity.Shop;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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
@EntityListeners(AuditingEntityListener.class)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'", unique = true)
    @GenericGenerator(name = "customer-uuid", strategy = "uuid")
    @GeneratedValue(generator = "customer-uuid")
    private String uuid;

    @JsonBackReference("customer")
    @ManyToOne
    private Shop shop;

    @Column(nullable = false, columnDefinition = "varchar(200) COMMENT '顾客手机号'")
    private String customerPhone;

    @Column(columnDefinition = "int(5) COMMENT '手机号码运营商 -1:未知运营商 0:中国移动 1:中国联通 2:中国电信'")
    private Integer phoneOperator;

    @Column(columnDefinition = "varchar(50) COMMENT '手机号码归属地'")
    private String phoneAddress;

    @Column(columnDefinition = "varchar(50) COMMENT '手机号码类型'")
    private String phoneType;

    @Column(columnDefinition = "varchar(50) COMMENT '区号'")
    private String phoneZone;

    @Column(columnDefinition = "varchar(50) COMMENT '邮编'")
    private String zipCode;

    @Column(columnDefinition = "varchar(25) COMMENT '顾客IP'")
    private String customerIp;

    @Column(columnDefinition = "varchar(50) COMMENT '奖品名称'")
    private String prizeName;

    @Column(columnDefinition = "int(10) COMMENT '扫码类型 -1:第三方扫码, 1:微信扫码 2:支付宝扫码'")
    private Integer scanType;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(uuid, customer.uuid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), uuid);
    }
}
