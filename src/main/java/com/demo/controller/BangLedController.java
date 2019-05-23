package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.demo.entities.BangLed;
import com.demo.entities.KetQuaSuaChua;
import com.demo.repository.BangLedRepository;

@Controller
public class BangLedController {
  @Autowired
  BangLedRepository bangLedRepository;

  // THêm bảng led
  @GetMapping(value = "add-bang-led")
  public String addBangLed() {
    return "addBangLed";
  }

  @PostMapping(value = "add-bang-led")
  public String addBangLed(@RequestParam int led_truoc, @RequestParam int led_suon,
      @RequestParam int led_sau, @RequestParam int led_trong, @RequestParam String tinh_trang_loi,
      @RequestParam String ngay_kh_sc, @RequestParam String ngay_sc_thuc_te,
      @RequestParam String nguyen_nhan, @RequestParam int kq, @RequestParam int tong_so_ngay_sc) {

    BangLed bangLed = new BangLed();
    bangLed.setLed_truoc(led_truoc);
    bangLed.setLed_suon(led_suon);
    bangLed.setLed_sau(led_sau);
    bangLed.setLed_trong(led_trong);
    bangLed.setTinh_trang_loi(tinh_trang_loi);
    bangLed.setNgay_kh_sc(ngay_kh_sc);
    bangLed.setNgay_sc_thuc_te(ngay_sc_thuc_te);
    bangLed.setNguyen_nhan(nguyen_nhan);
    KetQuaSuaChua kqcs = new KetQuaSuaChua();
    kqcs.setId_kq(kq);
    bangLed.setKq(kqcs);
    bangLed.setTong_so_ngay_sc(tong_so_ngay_sc);
    bangLedRepository.addBangLed(bangLed);
    return "redirect:/car";
  }
}
