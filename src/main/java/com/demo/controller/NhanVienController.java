package com.demo.controller;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.demo.entities.NhanVien;
import com.demo.repository.impl.NhanVienRepositoryImpl;


/**
 * TODO.
 * 
 * @author ...
 *
 *         annataion @Controller khai báo cho spring biết class này là class controller
 */
@Controller
public class NhanVienController {

  @Autowired
  NhanVienRepositoryImpl nhanVienRepositoryImpl;

  // Khi nhập đường dẫn localhost:8080/login thì vào trang này
  @GetMapping(value = "/login")
  public String login(HttpServletRequest request, HttpSession session) {
    request.setAttribute("msg", null);
    return "login";
  }

  // khi đăng nhập thì dữ liệu được lấy từ trang html vào xử lý
  @PostMapping(value = "/login")
  public String login(@RequestParam(name = "userName") String userName,
      @RequestParam(name = "password") String password, HttpSession session,
      HttpServletRequest request) {
    Optional<NhanVien> nv = nhanVienRepositoryImpl.login(userName, password);
    if (nv.isPresent()) {
      session.setAttribute("user", nv.get());
      return "car";
    } else {
      request.setAttribute("msg", "Tài khoản hoặc mật khẩu không chính xác");
      return "login";
    }

  }

  // Trang hiển thị car
  @GetMapping(value = "/car")
  public String quanLyCar(HttpSession session) {
    NhanVien nv = (NhanVien) session.getAttribute("user");
    if (nv == null) {
      return "login";
    }
    return "car";
  }

  // logout người dùng
  @GetMapping(value = "/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "login";
  }

  // Vào trang quản lý admin, chỉ người có role = 1 mới có quyền truy cập trang này
  @GetMapping(value = "/admin")
  public String admin(HttpServletRequest request, HttpSession session) {
    NhanVien nv = (NhanVien) session.getAttribute("user");
    if (nv == null || nv.getRole() == 0) {
      return "redirect:/car";
    } else {
      List<NhanVien> nhanViens = nhanVienRepositoryImpl.getAllNhanVien();
      request.setAttribute("nhanViens", nhanViens);
      return "admin";
    }
  }

  // chỉ có tài khoản có role = 1 mới có quyền thêm mới nhân viên
  @GetMapping(value = "/add-user")
  public String addNhanVien() {
    return "addUser";
  }

  // xử lý thêm mới nhân viên
  @PostMapping(value = "/add-user")
  public String addNhanVien(@RequestParam(name = "userName") String userName,
      @RequestParam(name = "password") String password, HttpServletRequest request) {
    boolean exist = nhanVienRepositoryImpl.checkUserExist(userName);
    if (exist) {
      request.setAttribute("msg", "Tên người dùng đã tồn tại");
      return "addUser";
    } else {
      nhanVienRepositoryImpl.addUser(userName, password);
      return "redirect:/list-nhan-vien";
    }
  }

  // hiển thị ra list nhân viên
  @GetMapping(value = "/list-nhan-vien")
  public String listNhanVien(HttpServletRequest request) {
    List<NhanVien> nhanViens = nhanVienRepositoryImpl.getAllNhanVien();
    request.setAttribute("nhanViens", nhanViens);
    return "nhanvien";

  }

  // Xóa đi nhân viên được chọn sau đó chuyển trang đến trang hiển thị list nhân viên
  @GetMapping(value = "/delete-user/{id}")
  public String deleteNhanVien(@PathVariable(name = "id") int id) {
    nhanVienRepositoryImpl.deleteUser(id);
    return "redirect:/list-nhan-vien";
  }

  @GetMapping(value = "/detail-user/{id}")
  public String detailNhanVien(@PathVariable int id, HttpServletRequest request) {
    Optional<NhanVien> user = nhanVienRepositoryImpl.getUserById(id);
    request.setAttribute("user", user.get());
    return "userDetail";
  }



}
