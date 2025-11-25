## API Endpoint Details & Examples

---

### 1 🔹 Items API

#### ➕ Create Item
**POST** `/api/items`  
Content-Type: `application/json`

```json
{
  "name": "string",
  "description": "string",
  "basePrice": 0,
  "stockQuantity": 0,
  "hasVariants": true
}
```

Response:
```json
{
  "id": 0,
  "name": "string",
  "description": "string",
  "basePrice": 0,
  "stockQuantity": 0,
  "hasVariants": true,
  "variants": [
    {
      "id": 0,
      "sku": "string",
      "size": "string",
      "color": "string",
      "price": 0,
      "stockQuantity": 0
    }
  ]
}
```

---

#### Get All Items
**GET** `/api/items`

#### Query Parameters

| Name  | Type   | Default | Description                          |
|-------|--------|---------|--------------------------------------|
| page  | int    | 0       | Nomor halaman (0-based index)       |
| size  | int    | 20      | Jumlah data per halaman             |
| sort  | string | name,asc (contoh) | Sorting field & direction, format: `field,dir` |

Contoh:
```http
GET /api/items?page=0&size=10&sort=field,dir
```

Response:
```json
{
  "totalPages": 0,
  "totalElements": 0,
  "size": 0,
  "content": [
    {
      "id": 0,
      "name": "string",
      "description": "string",
      "basePrice": 0,
      "stockQuantity": 0,
      "hasVariants": true,
      "variants": [
        {
          "id": 0,
          "sku": "string",
          "size": "string",
          "color": "string",
          "price": 0,
          "stockQuantity": 0
        }
      ]
    }
  ],
  "number": 0,
  "numberOfElements": 0,
  "pageable": {
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 0,
    "paged": true,
    "sort": {
      "empty": true,
      "sorted": true,
      "unsorted": true
    },
    "unpaged": true
  },
  "sort": {
    "empty": true,
    "sorted": true,
    "unsorted": true
  },
  "first": true,
  "last": true,
  "empty": true
}
```

---


#### Get Item By Id
**GET** `/api/items/{id}`

Response:
```json
{
  "id": 0,
  "name": "string",
  "description": "string",
  "basePrice": 0,
  "stockQuantity": 0,
  "hasVariants": true,
  "variants": [
    {
      "id": 0,
      "sku": "string",
      "size": "string",
      "color": "string",
      "price": 0,
      "stockQuantity": 0
    }
  ]
}
```

---

#### ✏️ Update Item
**PUT** `/api/items/{id}`  
Content-Type: `application/json`

```json
{
  "name": "string",
  "description": "string",
  "basePrice": 0,
  "stockQuantity": 0,
  "hasVariants": true
}
```

Response:
```json
{
  "id": 0,
  "name": "string",
  "description": "string",
  "basePrice": 0,
  "stockQuantity": 0,
  "hasVariants": true,
  "variants": [
    {
      "id": 0,
      "sku": "string",
      "size": "string",
      "color": "string",
      "price": 0,
      "stockQuantity": 0
    }
  ]
}
```

---

#### Delete Item
**DELETE** `/api/items/{id}`

---

### 2 🔹 Variants API


#### ➕ Create Variant
**POST** `/api/items/{itemId}/variants`  
Content-Type: `application/json`

```json
{
  "sku": "string",
  "size": "string",
  "color": "string",
  "price": 0,
  "stockQuantity": 0
}
```

Response:
```json
{
  "id": 0,
  "sku": "string",
  "size": "string",
  "color": "string",
  "price": 0,
  "stockQuantity": 0
}
```

---

#### Get All Variants by Item
**GET** `/api/items/{itemId}/variants`

#### Query Parameters

| Name  | Type   | Default | Description                          |
|-------|--------|---------|--------------------------------------|
| page  | int    | 0       | Nomor halaman (0-based index)       |
| size  | int    | 20      | Jumlah data per halaman             |
| sort  | string | name,asc (contoh) | Sorting field & direction, format: `field,dir` |

Contoh:
```http
GET /api/items/{itemId}/variants?page=0&size=10&sort=field,dir
```

Response:
```json
{
  "totalPages": 0,
  "totalElements": 0,
  "size": 0,
  "content": [
    {
      "id": 0,
      "sku": "string",
      "size": "string",
      "color": "string",
      "price": 0,
      "stockQuantity": 0
    }
  ],
  "number": 0,
  "numberOfElements": 0,
  "pageable": {
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 0,
    "paged": true,
    "sort": {
      "empty": true,
      "sorted": true,
      "unsorted": true
    },
    "unpaged": true
  },
  "sort": {
    "empty": true,
    "sorted": true,
    "unsorted": true
  },
  "first": true,
  "last": true,
  "empty": true
}
```

---


#### Get Variant By Id
**GET** `/api/variants/{id}`

Response:
```json
{
  "id": 0,
  "sku": "string",
  "size": "string",
  "color": "string",
  "price": 0,
  "stockQuantity": 0
}
```

---

#### ✏️ Update Variant
**PUT** `/api/variants/{id}`  
Content-Type: `application/json`

```json
{
  "sku": "string",
  "size": "string",
  "color": "string",
  "price": 0,
  "stockQuantity": 0
}
```

Response:
```json
{
  "id": 0,
  "sku": "string",
  "size": "string",
  "color": "string",
  "price": 0,
  "stockQuantity": 0
}
```

---

#### Delete Variant
**DELETE** `/api/variants/{id}`

---

### 3 🔹 🛒 Inventory API (Sell Stock)

#### Sell Simple Item
**POST** `/api/inventory/sell`  
Content-Type: `application/json`

```json
{
  "itemId": 0,
  "quantity": 0
}
```

#### Sell Variant Item
**POST** `/api/inventory/sell`  
Content-Type: `application/json`

```json
{
  "variantId": 0,
  "quantity": 0
}

```