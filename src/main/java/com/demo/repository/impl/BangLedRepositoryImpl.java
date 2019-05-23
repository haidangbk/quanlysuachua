package com.demo.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.demo.entities.BangLed;
import com.demo.repository.BangLedRepository;

@Transactional
@Repository
public class BangLedRepositoryImpl implements BangLedRepository {
  @Autowired
  EntityManager entityManager;

  @Override
  public void addBangLed(BangLed bangLed) {
    String sql =
        "INSERT INTO bang_led(led_truoc,led_suon,led_sau,led_trong,tinh_trang_loi,ngay_kh_sc,ngay_sc_thuc_te,nguyen_nhan,id_kq,tong_so_ngay_sc)"
            + "VALUES(:led_truoc,:led_suon,:led_sau,:led_trong,:tinh_trang_loi,:ngay_kh_sc,:ngay_sc_thuc_te,:nguyen_nhan,:id_kq,:tong_so_ngay_sc)";
    entityManager.createNativeQuery(sql).setParameter("led_truoc", bangLed.getLed_truoc())
        .setParameter("led_suon", bangLed.getLed_suon())
        .setParameter("led_sau", bangLed.getLed_sau())
        .setParameter("led_trong", bangLed.getLed_trong())
        .setParameter("tinh_trang_loi", bangLed.getTinh_trang_loi())
        .setParameter("ngay_kh_sc", bangLed.getNgay_kh_sc())
        .setParameter("ngay_sc_thuc_te", bangLed.getNgay_sc_thuc_te())
        .setParameter("nguyen_nhan", bangLed.getNguyen_nhan())
        .setParameter("id_kq", bangLed.getKq().getId_kq())
        .setParameter("tong_so_ngay_sc", bangLed.getTong_so_ngay_sc()).executeUpdate();
  }

  @Override
  public List<BangLed> getAllBangLed() {
    String sql = "SELECT bl FROM BangLed bl";
    return entityManager.createQuery(sql).getResultList();
  }
}
