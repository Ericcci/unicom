package com.jm.unicom.shop.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * QrCode
 *
 * @author Eric
 * @date 2018/1/2
 */
@Data
@Entity
@Table(name = "t_qrcode")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class QrCode {

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "qrcode-uuid", strategy = "uuid")
    @GeneratedValue(generator = "qrcode-uuid")
    private String uuid;

    @ManyToOne
    private Shop shop;

    @Column(columnDefinition = "longtext COMMENT 'base64二维码'")
    private String imgData;

    @Column(columnDefinition = "varchar(200) COMMENT '二维码url'")
    private String imgUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        QrCode qrCode = (QrCode) o;

        return uuid.equals(qrCode.uuid);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + uuid.hashCode();
        return result;
    }
}
