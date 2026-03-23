# 💳 Bank Management System

##  Giới thiệu

Đây là hệ thống backend mô phỏng quản lý ngân hàng, cho phép:

* Tạo khách hàng
* Tạo tài khoản
* Nạp / rút tiền
* Xem lịch sử giao dịch

##  Công nghệ sử dụng

* Java Spring Boot
* Spring Data JPA
* PostgreSQL
* RESTful API
* Spring Security + JWT
* Postman(Test API)

##  Chức năng chính

* Customer Management
* Account Management
* Transaction (Deposit / Withdraw)
* Transaction History

##  Hướng dẫn chạy

1. Clone project:

```bash
git clone https://github.com/tritran2711/bank_system.git
```

2. Cấu hình database trong `application.properties`

3. Chạy: Chạy file BankSystemApplication.java trong IDE

##  API mẫu

// Tạo customer

POST /customers

// Tạo account

POST /accounts

// Nạp tiền

POST /transactions/deposit

// Rút tiền

POST /transactions/withdraw

##  Điểm nổi bật

* Áp dụng kiến trúc layered (Controller - Service - Repository)
* Xử lý transaction cơ bản trong hệ thống ngân hàng
* Thống kê và phân loại để phục vụ cho báo cáo

##  Liên hệ

tritran20031@gmail.com
0938942430
