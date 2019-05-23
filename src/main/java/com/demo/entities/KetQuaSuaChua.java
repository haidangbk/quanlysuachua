package com.demo.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kq_sua_chua")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class KetQuaSuaChua {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_kq;

  private String ket_qua_sua_chua;

  @OneToMany(mappedBy = "kq")
  private Set<BangLed> bangLed;
}
