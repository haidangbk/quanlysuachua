package com.demo.repository;

import java.util.List;
import java.util.Optional;
import com.demo.entities.NhanVien;

public interface NhanVienRepository {
  public Optional<NhanVien> login(String userName, String password);

  public List<NhanVien> getAllNhanVien();

  public void addUser(String userName, String password);

  public boolean checkUserExist(String userName);

  public void deleteUser(int id);

  public Optional<NhanVien> getUserById(int id);

  public void updateUser(NhanVien nv);
}
