# 1. Domain Model & Architecture

## 1.1 Domain Model

### **Item**
Mewakili produk utama yang dijual.

**Field utama:**
- `id`
- `name`
- `description`
- `basePrice` *(nullable)*
- `stockQuantity`
- `hasVariants` *(Boolean)*

**Behavior:**

| Kondisi | Digunakan sebagai | Keterangan |
|--------|------------------|------------|
| `hasVariants = false` | Simple Item | `basePrice` dan `stockQuantity` digunakan |
| `hasVariants = true` | Item dengan variants | Harga & stok dikelola di `ItemVariant` |

---

### **ItemVariant**
Mewakili varian produk (misal beda SKU, size, atau color).

**Field utama:**
- `id`
- `sku`
- `size`
- `color`
- `price`
- `stockQuantity`
- `item` *(Many-to-one ke Item)*

💡 Setiap varian punya harga & stok sendiri.

---

### Alasan Desain
- Mendukung produk simple tanpa varian, dan produk kompleks dengan banyak varian
- Mudah dikembangkan untuk atribut baru seperti brand, category, dsb.

---

## 1.2 Architecture & Layering

Aplikasi menggunakan arsitektur layered:

### Controller → Service → Repository → Database

### Controller Layer
- Menangani HTTP request/response
- Validasi input dengan `@Valid`
- Mapping endpoint

### Service Layer
- Business logic seperti pengecekan stok
- Transformasi DTO ↔ Entity

### Repository Layer
- Database operation via Spring Data JPA

📌 Memisahkan setiap concern untuk memudahkan pengembangan & testing.

---

## 1.3 Database Schema Design

Schema di-generate otomatis dengan JPA (`ddl-auto=update`)

### Tabel `items`

| Field | Tipe | Keterangan |
|-------|------|------------|
| id | PK | Unique identifier |
| name | unique, not null | Nama Item |
| description | nullable | Deskripsi |
| base_price | numeric | Dipakai jika `hasVariants = false` |
| stock_quantity | numeric | Dipakai jika `hasVariants = false` |
| has_variants | boolean | Penanda memiliki variants |

### Tabel `item_variants`

| Field | Tipe | Keterangan |
|-------|------|------------|
| id | PK | Unique identifier |
| sku | not null | Kode varian |
| size | nullable | Ukuran |
| color | nullable | Warna |
| price | not null | Harga varian |
| stock_quantity | not null | Stok varian |
| item_id | FK → items.id | Relasi ke Item |

📌 Struktur mendukung query laporan stok baik per-item maupun per-variant.

---

## 1.4 Validation & Error Handling

### Validasi
Menggunakan Jakarta Bean Validation:
- `@NotNull`
- `@NotBlank`
- `@Positive`
- `@PositiveOrZero`

### Error Handling
`@RestControllerAdvice` dengan mapping:

| Error | HTTP Code | Penjelasan |
|-------|-----------|------------|
| `NotFoundException` | 404 | Data tidak ditemukan |
| `InsufficientStockException` | 400 | Stok tidak mencukupi |
| `MethodArgumentNotValidException` | 400 | Validasi gagal |

📌 Response error yang jelas memudahkan integrasi dengan frontend.

---

## 1.5 Prevent Selling Out of Stock (Business Rule)

Tidak boleh terjadi penjualan jika stok kurang.

Logika pada `InventoryService.sell(SellRequest request)`:

| Mode | Field digunakan | Kondisi |
|------|----------------|---------|
| Simple item | `itemId + quantity` | Jika `hasVariants = false` |
| Variant item | `variantId + quantity` | Jika `hasVariants = true` |

### Flow:
1. Ambil entity dari DB
2. Cek stok (`stockQuantity`)
3. Jika `stock < quantity` → lempar `InsufficientStockException`
4. Jika cukup → kurangi stok dan simpan

📌 Business rule terpusat → Controller tetap ringan.

---

## 1.6 Extensibility

Sudah disiapkan untuk pengembangan kedepan:

- DTO layer → API dapat berubah tanpa ubah entity
- Endpoint terstruktur:
    - `/api/items`
    - `/api/items/{itemId}/variants`
    - `/api/variants`
    - `/api/inventory`
- Bisa dikembangkan:
    - Pagination & filtering data
    - Authentication & Authorization
    - Integrasi sistem lain

---

## 1.7 Assumptions

| Asumsi | Alasan |
|--------|--------|
| Item hanya simple **atau** hanya pakai variants | Mencegah stok ambigu |
| Penjualan item dengan variant *harus* via `variantId` | Stok dikelola di level variant |
| Tidak ada reservation/backorder | Sesuai scope assessment |
| Tidak ada authentication/authorization | Fokus fitur inventaris |
| Concurrency belum diatur detil | Optional: `@Version` |

---

## 1.8 Flyway Migrations & Sample Data

### Migrations Structure
Menggunakan Flyway otomatis berjalan saat aplikasi start.
