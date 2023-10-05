# Tugas Besar 1 IF2123 - Aljabar Linear dan Geometri




## Kelompok JawaScript

| NIM | Nama           |
| :-------- | :------------------------- |
| 13522122 |  Maulvi Ziadinda Maulana |
| 13522163 | Atqiya Haydar Lukman |
| 13522122 | Mohammad Akmal Ramadhan |


## Tentang Program Ini
- Menyelesaikan Sistem Persamaan Linear (SPL) dengan metode Gauss, Gauss-Jordan, Inverse (Balikan), dan Cramer
- Menghitung determinan sebuah matriks dengan metode ekspansi kofaktor dan inverse matriks
- Menyelesaikan interpolasi polinom
- Menyelesaikan interpolasi bicubic spline
- Menyelesaikan permesalahan regresi linear berganda

## Cara Menjalankan Program

Clone the project

```bash
  git clone https://github.com/akmalrmn/Algeo01-22122
```

Go to the project directory, do

```bash
  javac -d bin -sourcepath src -classpath lib/* src/*.java
```

Run driver

```bash
  java -cp bin driver
```


## Deskripsi Singkat Fungsi
| File | Fungsi    |
| :-------- | :------------------------- |
| Balikan_Adjoin.java| Class ini berisi tentang algoritma matriks balikan menggunakan matriks adjoin. |
| Balikan_Gauss.java | Class ini berisi tentang algoritma matriks balikan menggunakan metode Gauss-Jordan. |
| BicubicSI.java | Class ini berisi metode untuk menghitung interpolasi bicubic berdasarkan matriks m dan koordinat x dan y. |
| Determinan_EK.java | Class ini berisi metode untuk menghitung determinan dari matriks persegi dengan ekspansi kofaktor. |
| Determinan_ReduksiBaris.java |Class ini berisi metode untuk menghitung determinan dari matriks persegi dengan reduksi baris. |
| ImageScaler.java | Class ini berisi algoritma untuk melakukan penskalaan gambar. |
| Interpolasi_Polinom.java | Class ini berisi algoritma untuk melakukan interpolasi polinomial berdasarkan titik-titik yang diberikan. |
| SPL_Balikan.java| Class ini berisi algoritma untuk menyelesaikan Sistem Persamaan Linear (SPL) dengan metode matriks balikan. |
| SPL_Cramer.java | Class ini berisi algoritma untuk menyelesaikan Sistem Persamaan Linear (SPL) dengan metode Cramer. |
| SPL_Gauss.java | Class ini berisi algoritma untuk menyelesaikan Sistem Persamaan Linear (SPL) dengan metode eliminasi Gauss.|
| SPL_GaussJ.java| Class ini berisi algoritma untuk menyelesaikan Sistem Persamaan Linear (SPL) dengan metode eliminasi Gauss-Jordan |
| RegresiLinearBerganda.java | Class ini berisi algoritma untuk melakukan regresi linear berganda berdasarkan data yang dimasukkan oleh pengguna. |
