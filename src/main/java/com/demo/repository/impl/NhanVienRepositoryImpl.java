package com.demo.repository.impl;


import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.demo.entities.NhanVien;
import com.demo.repository.NhanVienRepository;

@Transactional
@Repository
public class NhanVienRepositoryImpl implements NhanVienRepository {

  @PersistenceContext
  EntityManager entityManager;


  @Override
  public Optional<NhanVien> login(String userName, String password) {
    String sql =
        "SELECT nv FROM NhanVien nv WHERE nv.user_name= :userName AND nv.password= :password";
    Query query = entityManager.createQuery(sql).setParameter("userName", userName)
        .setParameter("password", password);
    Optional<NhanVien> nv = query.getResultList().stream().findFirst();
    return nv;
  }


  @Override
  public List<NhanVien> getAllNhanVien() {
    String sql = "SELECT nv FROM NhanVien nv";
    Query query = entityManager.createQuery(sql);
    return query.getResultList();
  }


  @Override
  public void addUser(String userName, String password) {
    entityManager
        .createNativeQuery(
            "INSERT INTO Nhan_Vien(user_name, password) VALUES (:userName,:password)")
        .setParameter("userName", userName).setParameter("password", password).executeUpdate();

  }


  @Override
  public boolean checkUserExist(String userName) {
    String sql = "SELECT nv FROM NhanVien nv WHERE nv.user_name=:userName";
    Query query = entityManager.createQuery(sql).setParameter("userName", userName);
    Optional<NhanVien> nv = query.getResultList().stream().findFirst();
    return nv.isPresent();
  }


  @Override
  public void deleteUser(int id) {
    entityManager.createQuery("DELETE FROM NhanVien nv WHERE nv.id_nhan_vien = :id")
        .setParameter("id", id).executeUpdate();

  }


  @Override
  public Optional<NhanVien> getUserById(int id) {
    String sql = "SELECT nv FROM NhanVien nv WHERE nv.id_nhan_vien = :id";
    Query query = entityManager.createQuery(sql).setParameter("id", id);
    Optional<NhanVien> nv = query.getResultList().stream().findFirst();
    return nv;
  }


  @Override
  public void updateUser(NhanVien nv) {
    entityManager.merge(nv);

  }

}
