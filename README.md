# Shop Warehouse Management API

Java Backend Developer - Technical Assessment  
Simple shop warehouse management system built with Spring Boot.

## 1. Overview

Project ini dibuat untuk memenuhi requirement assessment:

- Mengelola **items** dan **variants** (contoh: size, color)
- Menyimpan **price** dan **stock** untuk item/variant
- Menyediakan **RESTful API** dengan **CRUD operations**
- Memiliki mekanisme **mencegah penjualan ketika stok tidak cukup**
- Menggunakan **Java 17+**, **Spring Boot 3.x**, dan **MySQL Database**

---

## 2. Tech Stack

| Layer | Technology |
|------|------------|
| Backend | Java 17 + Spring Boot 3.x |
| Database | MySQL 8 |
| Documentation | Swagger UI (OpenAPI) |
| Build Tool | Maven |

Dependencies:
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- mysql-connector-j
- springdoc-openapi-starter-webmvc-ui

---

## 3. How to Run

### 3.1 Prerequisites
- JDK 17+
- Maven 3.x
- MySQL Server 8+

### 3.2 Database Setup

Buat database baru:

```sql
CREATE DATABASE `shop-warehouse`;
```

### 3.3 Steps

```bash
git clone <your-repo-url>
cd shop-warehouse
Sesuaikan database connection di src/main/resources/application.yml:
mvn spring-boot:run
