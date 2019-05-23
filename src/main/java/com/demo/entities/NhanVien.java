package com.demo.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "nhan_vien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_nhan_vien")
  private int id_nhan_vien;

  @Column(name = "user_name")
  private String user_name;

  @Column(name = "password")
  private String password;

  @Column(name = "role")
  private int role;

  @ManyToMany
  @JoinTable(name = "nhan_vien_car", joinColumns = @JoinColumn(name = "id_nhan_vien"),
      inverseJoinColumns = @JoinColumn(name = "id_car"))
  Set<Car> listCar;
}
