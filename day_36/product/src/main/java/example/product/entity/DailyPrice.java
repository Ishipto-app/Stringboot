package example.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "daily_price")
public class DailyPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "hn_toanphat")
    private Float hnToanPhat;
    @Column(name = "hn_hailiang")
    private Float hnHailiang;
    @Column(name = "hn_Taisei")
    private Float hnTaisei;
    @Column(name = "hcm_toanphat")
    private Float hcmToanPhat;
    @Column(name = "hcm_hailiang")
    private Float hcmHailiang;
    @Column(name = "hcm_Taisei")
    private Float hcmTaisei;
    @Column(name = "da_toanphat")
    private Float daToanPhat;
    @Column(name = "da_hailiang")
    private Float daHailiang;
    @Column(name = "da_Taisei")
    private Float daTaisei;
    @Column(name = "valid_date")
    private Date validDate;
}