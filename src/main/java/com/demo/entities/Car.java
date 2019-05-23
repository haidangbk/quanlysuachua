package com.demo.entities;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_car")
  private int id_car;

  @Column(name = "ngay_loi")
  private String ngay_loi;

  @Column(name = "ngay_bao_cao")
  private String ngay_bao_cao;

  @Column(name = "bien_kiem_soat")
  private String bien_kiem_soat;

  @Column(name = "tuyen_xe")
  private int tuyen_xe;

  // @Column(name = "id_bang_led")
  // private int id_bang_led;

  @ManyToMany(mappedBy = "listCar")
  Set<NhanVien> listNhanVien;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_bang_led", referencedColumnName = "id_bang_led")
  private BangLed bangLed;
}
