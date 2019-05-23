package com.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bang_led")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BangLed {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_bang_led;

  private int led_truoc;

  private int led_suon;

  private int led_sau;

  private int led_trong;

  private String tinh_trang_loi;

  private String ngay_kh_sc;

  private String ngay_sc_thuc_te;

  private String nguyen_nhan;

  // private int id_kq;

  private int tong_so_ngay_sc;

  @OneToOne(mappedBy = "bangLed")
  private Car car;

  @ManyToOne
  @JoinColumn(name = "id_kq", nullable = false)
  private KetQuaSuaChua kq;
}
