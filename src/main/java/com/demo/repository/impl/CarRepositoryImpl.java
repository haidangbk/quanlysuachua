package com.demo.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.demo.entities.Car;
import com.demo.repository.CarRepository;

@Repository
@Transactional
public class CarRepositoryImpl implements CarRepository {

  @Autowired
  EntityManager entityManager;

  @Override
  public void addCar(Car car) {
    String sql = "INSERT INTO car(ngay_loi,ngay_bao_cao,bien_kiem_soat,tuyen_xe,id_bang_led) "
        + "VALUES (:ngay_loi,:ngay_bao_cao,:bien_kiem_soat,:tuyen_xe,:id_bang_led)";
    entityManager.createNativeQuery(sql).setParameter("ngay_loi", car.getNgay_loi())
        .setParameter("ngay_bao_cao", car.getNgay_bao_cao())
        .setParameter("bien_kiem_soat", car.getBien_kiem_soat())
        .setParameter("tuyen_xe", car.getTuyen_xe())
        .setParameter("id_bang_led", car.getBangLed().getId_bang_led()).executeUpdate();
  }

  @Override
  public List<Car> getAllCar() {
    String sql = "SELECT c FROM Car c";
    return entityManager.createQuery(sql).getResultList();
  }

  @Override
  public Car getCarById(int id) {
    String sql = "SELECT c FROM Car c WHERE c.id_car =:id";
    return (Car) entityManager.createQuery(sql).setParameter("id", id).getSingleResult();
  }

  @Override
  public Car getCarByBKS(String s) {
    String sql = "SELECT c FROM Car c WHERE c.bien_kiem_soat = :s";
    return (Car) entityManager.createQuery(sql).setParameter("s", s).getSingleResult();

  }

  @Override
  public void deleteCar(Car car) {
    entityManager.remove(car);
  }

}
