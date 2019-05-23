package com.demo.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.demo.entities.BangLed;
import com.demo.entities.Car;
import com.demo.entities.NhanVien;
import com.demo.repository.BangLedRepository;
import com.demo.repository.CarRepository;
import com.demo.repository.NhanVienRepository;

@Controller
public class CarController {

  @Autowired
  CarRepository carRepository;

  @Autowired
  BangLedRepository bangLedRepository;

  @Autowired
  NhanVienRepository nhanVienRepository;

  @GetMapping(value = "/add-car")
  public String addCar(HttpServletRequest request) {
    // Lấy ra list bảng led chưa được liên kết với xe nào. sử dụng stream filter java 8
    List<BangLed> listBangLed = bangLedRepository.getAllBangLed().stream()
        .filter(e -> e.getCar() == null).collect(Collectors.toList());

    request.setAttribute("listBangLed", listBangLed);
    return "addCar";
  }

  @PostMapping(value = "/add-car")
  public String addCar(@RequestParam String ngay_loi, @RequestParam String ngay_bao_cao,
      @RequestParam String bien_kiem_soat, @RequestParam int tuyen_xe,
      @RequestParam int id_bang_led) {
    Car car = new Car();
    car.setNgay_loi(ngay_loi);
    car.setNgay_bao_cao(ngay_bao_cao);
    car.setBien_kiem_soat(bien_kiem_soat);
    car.setTuyen_xe(tuyen_xe);
    BangLed bangLed = new BangLed();
    bangLed.setId_bang_led(id_bang_led);
    car.setBangLed(bangLed);
    carRepository.addCar(car);
    return "redirect:/car";
  }

  @GetMapping(value = "/add-car-to-nhan-vien")
  public String addCarToNhanVien(HttpServletRequest request, @RequestParam int idnv) {
    List<Car> listCar = carRepository.getAllCar();
    request.setAttribute("idnv", idnv);
    request.setAttribute("listCar", listCar);
    return "listCar";
  }

  @GetMapping(value = "/add-car-to-nhan-vien/detail")
  public String addCarToNhanVien(@RequestParam int id, @RequestParam int idnv) {
    NhanVien nv = nhanVienRepository.getUserById(idnv).get();
    Car car = carRepository.getCarById(id);
    Set<Car> listCar = nv.getListCar();
    listCar.add(car);
    nv.setListCar(listCar);
    nhanVienRepository.updateUser(nv);
    return "redirect:/list-nhan-vien";
  }

  @PostMapping(value = "/search")
  public String searchCar(@RequestParam String search, HttpServletRequest request) {
    Car car = carRepository.getCarByBKS(search);
    request.setAttribute("listCar", car);
    return "listCar";
  }

  @GetMapping(value = "/list-car")
  public String listCar(HttpServletRequest request) {
    List<Car> listCar = carRepository.getAllCar();
    request.setAttribute("listCar", listCar);
    return "listCar";
  }

  @GetMapping(value = "/delete-car/{id}")
  public String deleteCar(@PathVariable int id) {
    Car car = carRepository.getCarById(id);
    Set<NhanVien> listNhanVien = car.getListNhanVien();
    for (NhanVien nv : listNhanVien) {
      nv.getListCar().remove(car);
    }
    carRepository.deleteCar(car);
    return "redirect:/list-car";
  }
}
