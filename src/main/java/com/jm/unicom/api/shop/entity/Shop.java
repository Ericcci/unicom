package com.jm.unicom.api.shop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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
public class Shop {

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "shop-uuid", strategy = "uuid")
    @GeneratedValue(generator = "shop-uuid")
    private String uuid;

    @Column(columnDefinition = "varchar(200) COMMENT '店铺名称'")
    private String shopName;

    @Column(columnDefinition = "varchar(50) COMMENT '店主姓名'")
    private String shopKeeper;

    @Column(columnDefinition = "varchar(20) COMMENT '店铺联系方式'")
    private String telpohone;

    @Column(columnDefinition = "varchar(200) COMMENT '店铺地址'")
    private String shopAddress;

    @Column(columnDefinition = "int(30) COMMENT 'qq'")
    private Integer shopQq;

    @Column(columnDefinition = "varchar(30) COMMENT '微信'")
    private String shopWechat;

    @Column(columnDefinition = "int(25) COMMENT '银行卡号'")
    private Integer bankNo;

    @Column(columnDefinition = "int(5) COMMENT '删除标记  -1:删除 1:有效'")
    private Integer status = 1;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;

    @JsonManagedReference("shop_qrcode")
    @OneToMany(mappedBy = "shop", cascade = CascadeType.REMOVE)
    private Set<ShopQrCode> shopQrCodeSet = new HashSet<>();

    @JsonManagedReference("personal_qrcode")
    @OneToMany(mappedBy = "shop", cascade = CascadeType.REMOVE)
    private Set<PersonalQrCode> personalQrCodeSet = new HashSet<>();

    public Shop(String uuid) {
        this.uuid = uuid;
    }
}
