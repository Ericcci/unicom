package com.jm.unicom.api.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Shop
 *
 * @author Eric
 * @date 2018/1/2
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_shop")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@EntityListeners(AuditingEntityListener.class)
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "shop-uuid", strategy = "uuid")
    @GeneratedValue(generator = "shop-uuid")
    private String uuid;

    @Excel(name = "店铺名称", width = 35, orderNum = "1")
    @Column(nullable = false, columnDefinition = "varchar(200) COMMENT '店铺名称'")
    private String shopName;

    @Excel(name = "店主姓名", width = 20, orderNum = "2")
    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '店主姓名'")
    private String shopKeeper;

    @Excel(name = "手机号码", width = 20, orderNum = "3")
    @Column(nullable = false, columnDefinition = "varchar(200) COMMENT '店铺手机号码'")
    private String telpohone;

    @Excel(name = "店铺地址", width = 45, orderNum = "4")
    @Column(nullable = false, columnDefinition = "varchar(200) COMMENT '店铺地址'")
    private String shopAddress;

    @Excel(name = "QQ", width = 20, orderNum = "5")
    @Column(columnDefinition = "varchar(200) COMMENT 'qq'")
    private String shopQq;

    @Excel(name = "微信", width = 20, orderNum = "6")
    @Column(columnDefinition = "varchar(200) COMMENT '微信'")
    private String shopWechat;

    @Excel(name = "银行卡号", width = 30, orderNum = "7")
    @Column(columnDefinition = "varchar(200) COMMENT '银行卡号'")
    private String bankNo;

    @Column(columnDefinition = "int(5) COMMENT '删除标记  -1:删除 1:有效'")
    private Integer status = 1;

    @Excel(name = "导入时间", width = 30, format = "yyyy-MM-dd HH:mm:ss", orderNum = "8")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;

    @JsonManagedReference("shop_qrcode")
    @OneToMany(mappedBy = "shop", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<ShopQrCode> shopQrCodeSet = new HashSet<>();

    @JsonManagedReference("customer")
    @OneToMany(mappedBy = "shop", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Customer> customerSet = new HashSet<>();

    @JsonManagedReference("personal_qrcode")
    @OneToMany(mappedBy = "shop", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<PersonalQrCode> personalQrCodeSet = new HashSet<>();

    public Shop(String uuid) {
        this.uuid = uuid;
    }

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
        Shop shop = (Shop) o;
        return Objects.equals(uuid, shop.uuid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), uuid);
    }
}
