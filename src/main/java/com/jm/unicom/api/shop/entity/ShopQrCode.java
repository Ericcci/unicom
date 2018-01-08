package com.jm.unicom.api.shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ShopQrCode
 *
 * @author Eric
 * @date 2018/1/2
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_shop_qrcode")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class ShopQrCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "shopqrcode-uuid", strategy = "uuid")
    @GeneratedValue(generator = "shopqrcode-uuid")
    private String uuid;

    @JsonBackReference("shop_qrcode")
    @ManyToOne
    private Shop shop;

    @Column(nullable = false, columnDefinition = "longtext COMMENT 'base64二维码'")
    private String imgData;

    @Column(nullable = false, columnDefinition = "varchar(200) COMMENT '二维码url'")
    private String imgUrl;

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

        ShopQrCode shopQrCode = (ShopQrCode) o;

        return uuid.equals(shopQrCode.uuid);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + uuid.hashCode();
        return result;
    }
}
