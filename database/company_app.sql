-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Jul 31, 2024 at 11:03 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `company_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `id` int(20) NOT NULL,
  `nama_perusahaan` varchar(50) NOT NULL,
  `no_ijin` int(15) NOT NULL,
  `pemilik` varchar(50) NOT NULL,
  `jenis_usaha` varchar(200) NOT NULL,
  `tanggal_berdiri` date DEFAULT NULL,
  `no_telepon` int(13) NOT NULL,
  `provinsi` varchar(50) NOT NULL,
  `kabupaten` varchar(50) NOT NULL,
  `kecamatan` varchar(50) NOT NULL,
  `desa` varchar(50) NOT NULL,
  `nama_jalan` varchar(50) NOT NULL,
  `rt` int(3) NOT NULL,
  `rw` int(3) NOT NULL,
  `no_gedung` int(3) NOT NULL,
  `kode_pos` int(5) NOT NULL,
  `img` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`id`, `nama_perusahaan`, `no_ijin`, `pemilik`, `jenis_usaha`, `tanggal_berdiri`, `no_telepon`, `provinsi`, `kabupaten`, `kecamatan`, `desa`, `nama_jalan`, `rt`, `rw`, `no_gedung`, `kode_pos`, `img`) VALUES
(4, 'Riodes', 64456, 'Maria', 'Ritel', '2016-11-17', 87832423, 'Jabar', 'Bandung Timur', 'Majalaya', 'Padaulun', 'Pelangi', 1, 8, 8, 40315, 'C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\CompaniesApps\\src\\asset\\Company8.jpeg'),
(5, 'Lion', 4546, 'Loi', 'Pendidikan', '2021-08-04', 343, 'asdsd', 'Garut', 'asas', 'asas', 'asas', 12, 12, 34, 543, 'C:\\\\Users\\\\LENOVO\\\\Documents\\\\NetBeansProjects\\\\CompaniesApps\\\\src\\\\asset\\\\Company4.jpg'),
(6, 'MargaJaya', 3225435, 'Hana', 'Properti', '2011-09-15', 124345443, 'Jabar', 'Bandung', 'Majalaya', 'Padaulun', 'Sadang', 4, 1, 1, 24584, 'C:\\\\Users\\\\LENOVO\\\\Documents\\\\NetBeansProjects\\\\CompaniesApps\\\\src\\\\asset\\\\Company3.jpg'),
(8, 'Mutiara Intan', 7545, 'Lina', 'Logistik', '2021-05-05', 12345678, 'Jabar', 'Karawang', 'Ibun', 'Pangauban', 'Baros', 21, 31, 65, 12965, 'C:\\\\Users\\\\LENOVO\\\\Documents\\\\NetBeansProjects\\\\CompaniesApps\\\\src\\\\asset\\\\Company2.jpg'),
(9, 'HiuFont', 7655768, 'Susi', 'Teknologi', '2010-09-16', 7645345, 'Meikarta', 'Bogor', 'Majakerta', 'Majasetra', 'Sadang', 3, 5, 2, 26865, 'C:\\\\Users\\\\LENOVO\\\\Documents\\\\NetBeansProjects\\\\CompaniesApps\\\\src\\\\asset\\\\Company6.jpeg'),
(10, 'Microsoft', 7544575, 'Bill Gates', 'Manufaktur', '1996-09-18', 127645, 'Amerika', 'Los Angeles', 'Frid', 'Amoi', 'Frediana', 3, 6, 23, 43876, 'C:\\\\Users\\\\LENOVO\\\\Documents\\\\NetBeansProjects\\\\CompaniesApps\\\\src\\\\asset\\\\Company7.jpeg'),
(12, 'YuSehat', 8746375, 'Haikal', 'Kesehatan', '2017-09-05', 23434234, 'Jawa Barat', 'Karawang', 'Majalaya', 'Padamulya', 'Cihanjuang', 1, 7, 12, 12234, 'C:\\\\Users\\\\LENOVO\\\\Documents\\\\NetBeansProjects\\\\CompaniesApps\\\\src\\\\asset\\\\Company1.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `data`
--
ALTER TABLE `data`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
