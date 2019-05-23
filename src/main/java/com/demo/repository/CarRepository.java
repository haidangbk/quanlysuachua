package com.demo.repository;

import java.util.List;
import com.demo.entities.Car;

public interface CarRepository {
  public void addCar(Car car);

  public List<Car> getAllCar();

  public Car getCarById(int id);

  public Car getCarByBKS(String s);

  public void deleteCar(Car car);
}
