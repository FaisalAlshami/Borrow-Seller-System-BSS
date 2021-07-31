-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 23, 2021 at 10:16 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bssinfo`
--

-- --------------------------------------------------------

--
-- Table structure for table `brand_product`
--

CREATE TABLE `brand_product` (
  `Brand_id` int(11) NOT NULL,
  `Brand_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `brand_product`
--

INSERT INTO `brand_product` (`Brand_id`, `Brand_name`) VALUES
(1, 'HERMES'),
(2, 'PRADA'),
(3, 'CHANEL'),
(4, 'ARMANI'),
(5, 'Hugo'),
(6, 'Boss'),
(7, 'Lacoste'),
(8, 'Levis'),
(9, 'Chanel'),
(10, 'Fendi'),
(11, 'Mouawad'),
(12, 'Gucci'),
(13, 'Blancpain'),
(14, 'Omega'),
(15, 'Glashutte Original'),
(16, 'Rolex'),
(17, 'Lenovo '),
(18, 'Asus'),
(19, 'Dell'),
(20, 'Acer');

-- --------------------------------------------------------

--
-- Table structure for table `brand_product_categ_product_relationship`
--

CREATE TABLE `brand_product_categ_product_relationship` (
  `id` int(11) NOT NULL,
  `Categ_id` int(11) DEFAULT NULL,
  `Brand_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `brand_product_categ_product_relationship`
--

INSERT INTO `brand_product_categ_product_relationship` (`id`, `Categ_id`, `Brand_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 2, 5),
(6, 2, 6),
(7, 2, 7),
(8, 2, 8),
(9, 3, 9),
(10, 3, 10),
(11, 3, 11),
(12, 3, 12),
(13, 4, 13),
(14, 4, 14),
(15, 4, 15),
(16, 4, 16);

-- --------------------------------------------------------

--
-- Table structure for table `categ_product`
--

CREATE TABLE `categ_product` (
  `Categ_id` int(11) NOT NULL,
  `Categ_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `categ_product`
--

INSERT INTO `categ_product` (`Categ_id`, `Categ_name`) VALUES
(1, 'WOMEN'),
(2, 'MEN'),
(3, 'BAGS'),
(4, 'WATCHES'),
(5, 'Lenovo ');

-- --------------------------------------------------------

--
-- Table structure for table `dept`
--

CREATE TABLE `dept` (
  `DEPTNO` int(2) NOT NULL,
  `DNAME` varchar(14) DEFAULT NULL,
  `LOC` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `dept`
--

INSERT INTO `dept` (`DEPTNO`, `DNAME`, `LOC`) VALUES
(10, 'ACCOUNTING', 'NEW YORK'),
(20, 'RESEARCH', 'DALLAS'),
(30, 'SALES', 'CHICAGO'),
(40, 'OPERATIONS', 'BOSTON');

-- --------------------------------------------------------

--
-- Table structure for table `emp`
--

CREATE TABLE `emp` (
  `EMPNO` int(4) NOT NULL,
  `ENAME` varchar(10) DEFAULT NULL,
  `JOB` varchar(9) DEFAULT NULL,
  `MGR` int(4) DEFAULT NULL,
  `HIREDATE` date DEFAULT NULL,
  `SAL` double(7,2) DEFAULT NULL,
  `COMM` double(7,2) DEFAULT NULL,
  `DEPTNO` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `emp`
--

INSERT INTO `emp` (`EMPNO`, `ENAME`, `JOB`, `MGR`, `HIREDATE`, `SAL`, `COMM`, `DEPTNO`) VALUES
(1001, 'tom333', NULL, NULL, NULL, NULL, NULL, NULL),
(1002, 'jerry222', NULL, NULL, NULL, NULL, NULL, NULL),
(1003, 'jerry333', NULL, NULL, NULL, NULL, NULL, NULL),
(1004, 'tom44444', NULL, NULL, NULL, NULL, NULL, NULL),
(7369, 'tom', 'salesman', 7902, '1980-12-17', 800.00, NULL, 20),
(7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600.00, 300.00, 30),
(7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250.00, 500.00, 30),
(7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975.00, NULL, 20),
(7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250.00, 1400.00, 30),
(7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850.00, NULL, 30),
(7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450.00, NULL, 10),
(7788, 'SCOTT', 'ANALYST', 7566, '1987-04-19', 3000.00, NULL, 20),
(7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', 5000.00, NULL, 10),
(7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500.00, 0.00, 30),
(7876, 'ADAMS', 'CLERK', 7788, '1987-05-23', 1100.00, NULL, 20),
(7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950.00, NULL, 30),
(7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000.00, NULL, 20),
(7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 1300.00, NULL, 10),
(7935, 'tom', NULL, NULL, NULL, NULL, NULL, NULL),
(7936, 'jerry', NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `man_manufacturer`
--

CREATE TABLE `man_manufacturer` (
  `MAN_ID` int(11) NOT NULL,
  `NAME_EN` varchar(100) DEFAULT NULL,
  `NAME_CN` varchar(100) DEFAULT NULL,
  `GMC_REPORT_TYPE` varchar(20) DEFAULT NULL,
  `GMC_REPORT_URL` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(1024) DEFAULT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `CREATION_DATE` date DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(20) DEFAULT NULL,
  `LAST_UPDATE_DATE` date DEFAULT NULL,
  `CALL_CNT` int(11) DEFAULT NULL,
  `REMARK` varchar(50) DEFAULT NULL,
  `STS_CD` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `man_manufacturer`
--

INSERT INTO `man_manufacturer` (`MAN_ID`, `NAME_EN`, `NAME_CN`, `GMC_REPORT_TYPE`, `GMC_REPORT_URL`, `DESCRIPTION`, `CREATED_BY`, `CREATION_DATE`, `LAST_UPDATE_BY`, `LAST_UPDATE_DATE`, `CALL_CNT`, `REMARK`, `STS_CD`) VALUES
(2, 'Company 12', NULL, '1', 'ytreyte', 'ytryuru', 'mvo', '2021-03-11', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `par_parameter`
--

CREATE TABLE `par_parameter` (
  `PAR_ID` int(11) NOT NULL,
  `PARAM_CD` varchar(200) DEFAULT NULL,
  `PARAM_VALUE` varchar(500) DEFAULT NULL,
  `DESCRIPTION` text DEFAULT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `CREATION_DATE` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(20) DEFAULT NULL,
  `LAST_UPDATE_DATE` timestamp NULL DEFAULT NULL,
  `CALL_CNT` int(11) DEFAULT NULL,
  `REMARK` varchar(50) DEFAULT NULL,
  `STS_CD` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `par_parameter`
--

INSERT INTO `par_parameter` (`PAR_ID`, `PARAM_CD`, `PARAM_VALUE`, `DESCRIPTION`, `CREATED_BY`, `CREATION_DATE`, `LAST_UPDATE_BY`, `LAST_UPDATE_DATE`, `CALL_CNT`, `REMARK`, `STS_CD`) VALUES
(133, 'AAA', '1', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(134, 'Parameter 1', '212', '87', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pro_brand`
--

CREATE TABLE `pro_brand` (
  `BRD_ID` int(11) NOT NULL,
  `MAN_ID` int(11) DEFAULT NULL,
  `NAME_EN` varchar(100) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME_CN` varchar(100) DEFAULT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `CREATION_DATE` date DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(20) DEFAULT NULL,
  `LAST_UPDATE_DATE` date DEFAULT NULL,
  `CALL_CNT` int(11) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `STS_CD` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `pro_brand`
--

INSERT INTO `pro_brand` (`BRD_ID`, `MAN_ID`, `NAME_EN`, `DESCRIPTION`, `NAME_CN`, `CREATED_BY`, `CREATION_DATE`, `LAST_UPDATE_BY`, `LAST_UPDATE_DATE`, `CALL_CNT`, `REMARK`, `STS_CD`) VALUES
(58, 2, 'Brand 1', '', NULL, 'mvo', '2021-03-11', 'mvo', '2021-03-11', NULL, NULL, NULL),
(59, 2, 'Brand 2', 'fdfds', NULL, 'mvo', '2021-03-20', 'mvo', '2021-03-20', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pro_cart`
--

CREATE TABLE `pro_cart` (
  `Cart_id` int(11) NOT NULL,
  `pro_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT 1,
  `createDate` datetime DEFAULT NULL,
  `status` int(3) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pro_cart`
--

INSERT INTO `pro_cart` (`Cart_id`, `pro_id`, `user_id`, `quantity`, `createDate`, `status`) VALUES
(15, 77, 3, 1, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `pro_order`
--

CREATE TABLE `pro_order` (
  `or_id` int(11) NOT NULL,
  `bvo_user_id` int(11) DEFAULT NULL,
  `STR_ID` int(11) DEFAULT NULL,
  `man_id` int(11) DEFAULT NULL,
  `pro_id` int(11) DEFAULT NULL,
  `sts_cd` char(1) DEFAULT NULL,
  `ORDER_ID` varchar(45) DEFAULT NULL,
  `SALES_PRICE` decimal(8,2) DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `SKU_NO` varchar(45) DEFAULT NULL,
  `receive_name` varchar(46) DEFAULT NULL,
  `receive_address` varchar(47) DEFAULT NULL,
  `receive_phone` varchar(48) DEFAULT NULL,
  `tracking_company` varchar(49) DEFAULT NULL,
  `tracking_number` varchar(49) DEFAULT NULL,
  `ORDER_CREATED_TIME` timestamp NULL DEFAULT NULL,
  `PAID_TIME` timestamp NULL DEFAULT NULL,
  `ship_by` varchar(255) DEFAULT NULL,
  `ship_time` timestamp NULL DEFAULT NULL,
  `completed_time` timestamp NULL DEFAULT NULL,
  `cancelled_time` timestamp NULL DEFAULT NULL,
  `canceled_by` varchar(255) DEFAULT NULL,
  `completed_by` varchar(255) DEFAULT NULL,
  `paid_by` varchar(255) DEFAULT NULL,
  `canceled_reason` varchar(255) DEFAULT NULL,
  `transaction_withdraw_id` int(11) DEFAULT NULL,
  `transaction_deposit_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `pro_order`
--

INSERT INTO `pro_order` (`or_id`, `bvo_user_id`, `STR_ID`, `man_id`, `pro_id`, `sts_cd`, `ORDER_ID`, `SALES_PRICE`, `QTY`, `SKU_NO`, `receive_name`, `receive_address`, `receive_phone`, `tracking_company`, `tracking_number`, `ORDER_CREATED_TIME`, `PAID_TIME`, `ship_by`, `ship_time`, `completed_time`, `cancelled_time`, `canceled_by`, `completed_by`, `paid_by`, `canceled_reason`, `transaction_withdraw_id`, `transaction_deposit_id`) VALUES
(24, 1, NULL, 2, 88, '1', 'amazon', '14000.00', 1, '123', NULL, NULL, NULL, NULL, NULL, '2021-03-09 02:29:05', NULL, NULL, NULL, NULL, '2021-03-10 18:55:55', 'mvo', NULL, NULL, 'not trusted', NULL, NULL),
(32, 1, NULL, 2, 89, '4', 'ebay', '2000.00', 2, '123', 'ggsdg', 'sdgds', 'sdgs', 'UPS', '7093846692', '2021-03-09 05:17:11', '2021-03-10 20:53:11', 'mvo', '2021-03-11 08:12:10', '2021-03-11 08:12:20', NULL, NULL, 'bvo', 'bvo', 'Not ready', NULL, NULL),
(36, 1, NULL, 2, 88, '4', 'amazon', '12000.00', 1, '123', 'Faisal Alshami', 'shenyang, china', '098798798', 'UPS', '9148715079', '2021-03-10 15:08:15', '2021-03-10 17:27:25', 'mvo', '2021-03-10 20:00:14', '2021-03-10 20:40:39', NULL, NULL, 'bvo', 'bvo', NULL, NULL, NULL),
(37, 1, NULL, 2, 81, '4', 'amazon', '24000.00', 1, '111', 'Ali saleh', 'ytrtyeyte', '45', 'UPS', '4954267584', '2021-03-10 20:57:06', '2021-03-10 21:00:56', 'mvo', '2021-03-10 21:01:23', '2021-03-10 21:01:43', NULL, NULL, 'bvo', 'bvo', NULL, NULL, NULL),
(41, 1, NULL, 2, 83, '2', 'ebay', '1000.00', 1, '111', NULL, NULL, NULL, NULL, NULL, '2021-03-11 06:52:48', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(42, 1, NULL, 2, 92, '1', 'ebay', '11500.00', 1, '123', NULL, NULL, NULL, NULL, NULL, '2021-03-11 06:53:55', NULL, NULL, NULL, NULL, '2021-03-11 06:54:28', 'mvo', NULL, NULL, 'not available', NULL, NULL),
(43, 1, NULL, 2, 90, '4', 'amazon', '12000.00', 1, '123', 'Faisal Alshami', 'yteyteye', '5797987', 'UPS', '4379340425', '2021-03-11 06:54:48', '2021-03-11 06:55:26', 'mvo', '2021-03-11 06:55:58', '2021-03-11 06:56:14', NULL, NULL, 'bvo', 'bvo', NULL, NULL, NULL),
(44, 1, NULL, 2, 91, '1', 'amazon', '11000.00', 1, '123', NULL, NULL, NULL, NULL, NULL, '2021-03-11 07:15:49', NULL, NULL, NULL, NULL, '2021-03-11 07:16:39', 'mvo', NULL, NULL, 'not exist', NULL, NULL),
(45, 1, NULL, 2, 91, '4', 'ebay', '14000.00', 1, '123', 'user2', 'hjhdskjfs', '445465', 'SF Express', '0051861144', '2021-03-11 07:16:57', '2021-03-11 07:17:39', 'mvo', '2021-03-11 07:17:56', '2021-03-11 07:18:08', NULL, NULL, 'bvo', 'bvo', NULL, NULL, NULL),
(46, 1, NULL, 2, 88, '1', 'amazon', '14000.00', 1, '123', NULL, NULL, NULL, NULL, NULL, '2021-03-11 08:08:55', NULL, NULL, NULL, NULL, '2021-03-11 08:09:30', 'mvo', NULL, NULL, 'not existed', NULL, NULL),
(47, 1, NULL, 2, 88, '4', 'ebay', '12000.00', 1, '123', 'Faisal Alshami', 'address 1', '7885', 'UPS', '0308032492', '2021-03-11 08:09:53', '2021-03-11 08:10:41', 'mvo', '2021-03-11 08:11:00', '2021-03-11 08:11:22', NULL, NULL, 'bvo', 'bvo', NULL, NULL, NULL),
(48, 1, NULL, 2, 83, '4', 'amazon', '21000.00', 1, '111', 'Faisal Alshami', 'iutiut', '5646798', 'SF Express', '3184396214', '2021-03-11 08:55:50', '2021-03-11 08:56:41', 'mvo', '2021-03-11 08:56:57', '2021-03-11 08:57:06', NULL, NULL, 'bvo', 'bvo', NULL, NULL, NULL),
(49, 1, NULL, 2, 101, '4', 'amazon', '22.00', 1, NULL, 'Faisal', 'yuryur', '214534', 'SF Express', '2555656438', '2021-03-19 13:57:38', '2021-03-19 14:06:35', 'mvo', '2021-03-19 21:02:47', '2021-03-19 21:03:10', NULL, NULL, 'bvo', 'bvo', NULL, 1, 1),
(51, 1, NULL, 2, 103, '7', 'amazon', '9600.00', 2, NULL, 'Faisal Alshami', 'kjkj', '12164', NULL, NULL, '2021-03-19 14:50:04', '2021-03-19 14:51:11', NULL, NULL, NULL, NULL, NULL, NULL, 'bvo', NULL, 1, 1),
(52, 1, NULL, 2, 103, '7', 'amazon', '9600.00', 2, NULL, 'Faisal', 'hgkgj', '98798', NULL, NULL, '2021-03-19 15:30:26', '2021-03-19 15:30:50', NULL, NULL, NULL, NULL, NULL, NULL, 'bvo', NULL, 1, 1),
(53, 1, NULL, 2, 103, '7', 'amazon', '9600.00', 2, NULL, 'aaaaaaa', '98798', '98798789', NULL, NULL, '2021-03-19 15:33:52', '2021-03-19 15:34:13', NULL, NULL, NULL, NULL, NULL, NULL, 'bvo', NULL, 1, 1),
(54, 1, NULL, 2, 103, '7', 'amazon', '14400.00', 3, NULL, 'Jack', 'yutryur', '987987', NULL, NULL, '2021-03-19 15:39:51', '2021-03-19 15:40:18', NULL, NULL, NULL, NULL, NULL, NULL, 'bvo', NULL, 1, 1),
(55, 1, NULL, 2, 103, '7', 'amazon', '9600.00', 2, NULL, 'jack', '98798', '987987', NULL, NULL, '2021-03-19 15:42:43', '2021-03-19 15:43:05', NULL, NULL, NULL, NULL, NULL, NULL, 'bvo', NULL, 1, 1),
(56, 1, NULL, 2, 103, '7', 'amazon', '48000.00', 10, NULL, 'Jack', 'iuttiu', '87987', NULL, NULL, '2021-03-19 15:54:37', '2021-03-19 15:55:48', NULL, NULL, NULL, NULL, NULL, NULL, 'bvo', NULL, 1, 1),
(57, 1, NULL, 2, 103, '7', 'amazon', '4800.00', 1, NULL, 'kkk', 'ytiutiu', '123456', NULL, NULL, '2021-03-19 16:03:19', '2021-03-19 16:03:40', NULL, NULL, NULL, NULL, NULL, NULL, 'bvo', NULL, 1, 1),
(58, 1, NULL, 2, 103, '7', 'amazon', '4800.00', 1, NULL, 'oo', 'jyutyut', '89798', NULL, NULL, '2021-03-19 16:08:31', '2021-03-19 16:11:03', NULL, NULL, NULL, NULL, NULL, NULL, 'bvo', NULL, 1, 1),
(59, 1, NULL, 2, 103, '4', 'amazon', '19200.00', 4, NULL, 'yryuryu', '87987', 'ytrtyre', 'SF Express', '6587524433', '2021-03-19 16:13:53', '2021-03-19 16:14:37', 'mvo', '2021-03-19 16:15:00', '2021-03-19 16:15:19', NULL, NULL, 'bvo', 'bvo', NULL, 1, 1),
(60, 1, NULL, 2, 103, '4', 'amazon', '9600.00', 2, NULL, 'Khushed', 'china', '564987', 'SF Express', '9385223279', '2021-03-19 21:00:06', '2021-03-19 21:01:26', 'mvo', '2021-03-19 21:03:40', '2021-03-19 21:03:49', NULL, NULL, 'bvo', 'bvo', NULL, 1, 1),
(61, 1, NULL, 2, 103, '7', 'ebay', '19200.00', 4, NULL, 'yuryur', 'yuryur', '2341564', NULL, NULL, '2021-03-19 21:04:14', '2021-03-19 21:04:50', NULL, NULL, NULL, NULL, NULL, NULL, 'bvo', NULL, 1, 1),
(62, 1, NULL, 2, 104, '7', 'amazon', '6000.00', 6, NULL, 'iutiut', 'iutiuti', '987987', NULL, NULL, '2021-03-20 12:31:52', '2021-03-20 12:32:17', NULL, NULL, NULL, NULL, NULL, NULL, 'bvo', NULL, 1, 1),
(63, 1, NULL, 2, 103, '3', 'amazon', '14400.00', 3, NULL, 'Faisal', 'oiuyoiy', 'oiyoi', '', '3347061900', '2021-03-20 13:21:09', '2021-03-20 13:24:42', 'mvo', '2021-03-20 13:36:49', NULL, NULL, NULL, NULL, 'bvo', NULL, 1, 1),
(64, 1, NULL, 3, 101, '0', 'amazon', '22.00', 1, NULL, NULL, NULL, NULL, NULL, NULL, '2021-03-20 13:34:36', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(65, 1, NULL, 2, 103, '6', 'amazon', '14400.00', 3, NULL, 'gdyryre', 'dgdsg', 'gsdgds', NULL, NULL, '2021-03-20 13:35:26', '2021-03-20 13:36:10', NULL, NULL, NULL, NULL, NULL, NULL, 'bvo', NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pro_product`
--

CREATE TABLE `pro_product` (
  `PRO_ID` int(10) UNSIGNED NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `SKU_CD` varchar(8) DEFAULT NULL,
  `BRD_ID` int(11) DEFAULT NULL,
  `MAN_ID` int(11) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `BRD_NAME` varchar(255) DEFAULT NULL,
  `UPC` varchar(15) DEFAULT NULL,
  `EAN` varchar(13) DEFAULT NULL,
  `MODEL` varchar(45) DEFAULT NULL,
  `WARRANTY_DAY` varchar(45) DEFAULT NULL,
  `RETAIL_PRICE` decimal(10,2) DEFAULT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `CREATION_DATE` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(20) DEFAULT NULL,
  `LAST_UPDATE_DATE` timestamp NULL DEFAULT NULL,
  `CALL_CNT` int(11) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `STS_CD` varchar(255) DEFAULT NULL,
  `MININUM_RETAIL_PRICE` decimal(10,2) DEFAULT NULL,
  `REPLENISHMENT_PERIOD` int(11) DEFAULT NULL,
  `KEY_WORDS` varchar(255) DEFAULT NULL,
  `WARRANTY` text DEFAULT NULL,
  `TIME_UNIT` varchar(10) DEFAULT NULL,
  `STOCKSETING` int(11) DEFAULT NULL,
  `HEIGHT` varchar(100) DEFAULT NULL,
  `WEIGHT` varchar(100) DEFAULT NULL,
  `LENGTH` varchar(100) DEFAULT NULL,
  `WIDTH` varchar(100) DEFAULT NULL,
  `EBAY_DESCRIPTION` varchar(100) DEFAULT NULL,
  `AMAZON_DESCRIPTION` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `pro_product`
--

INSERT INTO `pro_product` (`PRO_ID`, `USER_ID`, `SKU_CD`, `BRD_ID`, `MAN_ID`, `TITLE`, `BRD_NAME`, `UPC`, `EAN`, `MODEL`, `WARRANTY_DAY`, `RETAIL_PRICE`, `CREATED_BY`, `CREATION_DATE`, `LAST_UPDATE_BY`, `LAST_UPDATE_DATE`, `CALL_CNT`, `REMARK`, `STS_CD`, `MININUM_RETAIL_PRICE`, `REPLENISHMENT_PERIOD`, `KEY_WORDS`, `WARRANTY`, `TIME_UNIT`, `STOCKSETING`, `HEIGHT`, `WEIGHT`, `LENGTH`, `WIDTH`, `EBAY_DESCRIPTION`, `AMAZON_DESCRIPTION`) VALUES
(101, 3, NULL, NULL, 3, 'phone', 'xiaomi', NULL, NULL, '2', '2', '22.00', NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8091/img/74f8a49e370f4dc48fe334f10a1254c9.jpg', 'On shelf', NULL, 2, 'Phone', NULL, NULL, 2, '2', '2', '2', '2', NULL, NULL),
(103, 2, NULL, NULL, 2, 'Huawei mate 40', 'huawei', NULL, NULL, '564564', '3', '4800.00', NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8091/image/f9167a43394c49f4a6cfdc61e0040d5c.jpg', 'On shelf', NULL, 88, 'Phone', NULL, NULL, NULL, '30', '30', '10', '10', NULL, NULL),
(104, 2, NULL, NULL, 2, 'GGG', 'xiaomi', NULL, NULL, '98798798', '3', '1000.00', NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8091/image/2027d1e6ba7342df8c858f878d8be8dd.gif', 'On shelf', NULL, 50, 'Phone', NULL, NULL, NULL, '30', '30', '10', '10', NULL, NULL),
(111, 2, NULL, NULL, 2, 'Lenovo Tab M30', 'Lenovo', NULL, NULL, '87987987', '3', '1800.00', NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8091/image/e3d54acf0b544add97972f1eec37d873.PNG', 'On shelf', NULL, 98, 'Laptops', NULL, NULL, NULL, '30', '30', '10', '10', NULL, NULL),
(112, 2, NULL, NULL, 2, 'Galaxy Tab S6 Lite', 'Samsung', NULL, NULL, '89755324', '3', '2100.00', NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8091/image/38fca6878a9c492faf7c5b8477a5369a.PNG', 'On shelf', NULL, 60, 'Laptops', NULL, NULL, NULL, '30', '30', '10', '10', NULL, NULL),
(113, 2, NULL, NULL, 2, 'Samsung Galaxy A11', 'Samsung', NULL, NULL, '979874456', '3', '5000.00', NULL, NULL, NULL, NULL, NULL, 'http://127.0.0.1:8091/image/2571b2dcd0994631b1587a9f6a799629.PNG', 'On shelf', NULL, 20, 'Laptops', NULL, NULL, NULL, '30', '30', '10', '10', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pro_status`
--

CREATE TABLE `pro_status` (
  `status_code` int(11) NOT NULL,
  `status_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pro_status`
--

INSERT INTO `pro_status` (`status_code`, `status_name`) VALUES
(0, 'In Process'),
(1, 'Canceled'),
(2, 'Waiting Payemnet'),
(3, 'Shipment'),
(4, 'Delivered'),
(5, 'Completed'),
(6, 'Paid'),
(7, 'Refund');

-- --------------------------------------------------------

--
-- Table structure for table `pro_wish`
--

CREATE TABLE `pro_wish` (
  `wish_id` int(10) NOT NULL,
  `pro_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `createdate` datetime DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pro_wish`
--

INSERT INTO `pro_wish` (`wish_id`, `pro_id`, `user_id`, `createdate`) VALUES
(5, 76, 3, '2021-02-18 00:06:45'),
(7, 90, 1, '2021-02-19 03:04:54'),
(8, 91, 1, '2021-02-19 03:04:58'),
(9, 89, 1, '2021-02-19 03:05:16'),
(11, 101, 1, '2021-03-20 21:28:27'),
(12, 103, 1, '2021-03-20 21:28:29');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `salgrade`
--

CREATE TABLE `salgrade` (
  `GRADE` int(1) DEFAULT NULL,
  `LOSAL` int(5) DEFAULT NULL,
  `HISAL` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `salgrade`
--

INSERT INTO `salgrade` (`GRADE`, `LOSAL`, `HISAL`) VALUES
(1, 700, 1200),
(2, 1201, 1400),
(3, 1401, 2000),
(4, 2001, 3000),
(5, 3001, 9999);

-- --------------------------------------------------------

--
-- Table structure for table `sys_company`
--

CREATE TABLE `sys_company` (
  `user_id` int(11) NOT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `company_description` varchar(500) DEFAULT NULL,
  `company_phone` varchar(20) DEFAULT NULL,
  `company_address` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_company`
--

INSERT INTO `sys_company` (`user_id`, `company_name`, `company_description`, `company_phone`, `company_address`) VALUES
(2, 'CompanyName1', 'yteyt', '123456789', 'yteyt');

-- --------------------------------------------------------

--
-- Table structure for table `sys_data`
--

CREATE TABLE `sys_data` (
  `id` int(10) NOT NULL,
  `code_type` varchar(100) DEFAULT NULL,
  `type_cd` varchar(100) DEFAULT NULL,
  `code_value` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sys_menu`
--

CREATE TABLE `sys_menu` (
  `MENU_ID` int(11) NOT NULL,
  `MENU_NAME` varchar(255) DEFAULT NULL,
  `MENU_URL` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `MENU_ORDER` varchar(100) DEFAULT NULL,
  `MENU_ICON` varchar(30) DEFAULT NULL,
  `MENU_TYPE` varchar(10) DEFAULT '2',
  `COMPONENT` varchar(255) DEFAULT NULL,
  `M_NAMEE` varchar(100) DEFAULT NULL,
  `ENABLE` varchar(50) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_menu`
--

INSERT INTO `sys_menu` (`MENU_ID`, `MENU_NAME`, `MENU_URL`, `PARENT_ID`, `MENU_ORDER`, `MENU_ICON`, `MENU_TYPE`, `COMPONENT`, `M_NAMEE`, `ENABLE`) VALUES
(1, 'Menu manage', '/menu', '1', '1', 'el-icon-notebook-2', '2', 'components/page/Admin/Menu.vue', 'Menu', '1'),
(2, 'User manage', '/user', '1', '2', 'el-icon-user', '2', 'components/page/Admin/User.vue', 'User', '1'),
(3, 'Parameter manage', '/param', '1', '3', 'el-icon-notebook-1', '2', 'components/page/Admin/Param.vue', 'Param', '1'),
(4, 'Data dictionary', '/data', '1', '4', 'el-icon-collection', '2', 'components/page/Admin/Data.vue', 'Data', '1'),
(5, 'Fund check', '/check', '1', '5', 'el-icon-document-checked', '2', 'components/page/Admin/Check.vue', 'Check', '1'),
(6, 'Company info', '/company', '2', '6', 'el-icon-school', '2', 'components/page/MVO/Company.vue', 'Company', '1'),
(7, 'Goods manage', '/goods', '2', '7', 'el-icon-goods', '2', 'components/page/MVO/Goods.vue', 'Goods', '1'),
(8, 'Order manage', '/MVO/order', '2', '8', 'el-icon-s-order', '2', 'components/page/MVO/Order.vue', 'MVOOrder', '1'),
(9, 'Wallet', '/MVO/wallet', '2', '9', 'el-icon-wallet', '2', 'components/page/MVO/Wallet.vue', 'MVOWallet', '1'),
(10, 'Store manage', '/store', '3', '10', 'el-icon-house', '2', 'components/page/BVO/Store.vue', 'Store', '1'),
(11, 'Goods list', '/goodslist', '3', '11', 'el-icon-goods', '2', 'components/page/BVO/GoodsList.vue', 'GoodsList', '1'),
(12, 'Wish list', '/wishlist', '3', '12', 'el-icon-star-off', '2', 'components/page/BVO/WishList.vue', 'WishList', '1'),
(13, 'Order manage', '/BVO/order', '3', '13', 'el-icon-s-order', '2', 'components/page/BVO/Order.vue', 'BVOOrder', '1'),
(14, 'Wallet', '/BVO/wallet', '3', '14', 'el-icon-wallet', '2', 'components/page/BVO/Wallet.vue', 'BVOWallet', '1'),
(15, 'Home', '/main', '0', '15', 'el-icon-s-home', '1', 'components/page/Home.vue', 'Home', '1'),
(16, 'Management', '1', '0', '16', 'el-icon-s-tools', '0', NULL, 'Admin', '1'),
(17, 'MVO', '2', '0', '17', 'el-icon-office-building', '0', NULL, 'MVO', '1'),
(18, 'BVO', '3', '0', '18', 'el-icon-s-shop', '0', NULL, 'BVO', '1'),
(19, 'My', '/my', '0', '20', NULL, '3', 'components/My.vue', NULL, '1'),
(22, 'Commission Manage', '/Admin/Commission', '1', '10', 'el-icon-goods', '2', 'components/page/Admin/Commission.vue', 'Commission', '1');

-- --------------------------------------------------------

--
-- Table structure for table `sys_menu_role_relationship`
--

CREATE TABLE `sys_menu_role_relationship` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `sys_menu_role_relationship`
--

INSERT INTO `sys_menu_role_relationship` (`id`, `role_id`, `menu_id`) VALUES
(3, 1, 15),
(4, 1, 1),
(5, 1, 2),
(6, 1, 3),
(7, 1, 4),
(8, 1, 5),
(9, 1, 6),
(10, 1, 7),
(11, 1, 8),
(12, 1, 9),
(21, 2, 15),
(22, 2, 6),
(23, 2, 7),
(24, 2, 8),
(25, 2, 9),
(26, 2, 17),
(27, 3, 15),
(28, 3, 10),
(29, 3, 11),
(30, 3, 12),
(31, 3, 13),
(32, 3, 18),
(33, 3, 14),
(34, 3, 19),
(35, 2, 19),
(36, 1, 19),
(40, 1, 22),
(42, 1, 17),
(43, 1, 18),
(44, 1, 10),
(45, 1, 11),
(46, 1, 12),
(47, 1, 13),
(48, 1, 14),
(49, 1, 16);

-- --------------------------------------------------------

--
-- Table structure for table `sys_role`
--

CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `sys_role`
--

INSERT INTO `sys_role` (`role_id`, `role_name`) VALUES
(1, 'Admin'),
(2, 'MVO'),
(3, 'BVO');

-- --------------------------------------------------------

--
-- Table structure for table `sys_user`
--

CREATE TABLE `sys_user` (
  `USER_ID` int(11) NOT NULL,
  `USERNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT '0',
  `BZ` varchar(255) DEFAULT NULL,
  `SKIN` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  `MAN_BUYER_ID` int(11) DEFAULT NULL,
  `IMG_URL` varchar(255) DEFAULT NULL,
  `ROLE_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sys_user`
--

INSERT INTO `sys_user` (`USER_ID`, `USERNAME`, `PASSWORD`, `NAME`, `RIGHTS`, `ROLE_ID`, `LAST_LOGIN`, `IP`, `STATUS`, `BZ`, `SKIN`, `EMAIL`, `NUMBER`, `PHONE`, `MAN_BUYER_ID`, `IMG_URL`, `ROLE_NAME`) VALUES
(1, 'bvo', '$2a$10$jiHZck.LrhPi2DHSc31zyOJXryzZDFPdfFleQ6X0NNWWh9n.PS0U.', 'bvorrrr', '1', '3', '2020-01-06', '1', '1', '1', '1', '123@qq.com', '1', '15911111111', 1, 'http://127.0.0.1:8091/img/user2.jpeg', NULL),
(2, 'mvo', '$2a$10$jiHZck.LrhPi2DHSc31zyOJXryzZDFPdfFleQ6X0NNWWh9n.PS0U.', 'mvo111', '2', '2', '2020-01-26', NULL, '0', NULL, NULL, '234@qq.com', '2', '11112', 2, 'http://127.0.0.1:8091/img/marc.jpg', NULL),
(3, 'admin1', '$2a$10$jiHZck.LrhPi2DHSc31zyOJXryzZDFPdfFleQ6X0NNWWh9n.PS0U.', 'adminsaf', '3', '1', '2021-01-10', NULL, '0', NULL, NULL, '345@qq.com', '3', '124333333123', 4, 'http://127.0.0.1:8091/img/user1.jpg', 'Admin'),
(4, 'tom', '$2a$10$jiHZck.LrhPi2DHSc31zyOJXryzZDFPdfFleQ6X0NNWWh9n.PS0U.', 'tom', '2', '2', '2021-01-11', NULL, '0', NULL, NULL, 'tom@outlook.com', '4', '12345678', NULL, NULL, NULL),
(9, 'blake', '123456', 'blake', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'blake@outlook.com', NULL, '138121111111', NULL, NULL, NULL),
(11, 'feibu', '12345', 'Faisal Alshami', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '235@qq.com', NULL, '123456789', NULL, NULL, NULL),
(12, 'feibu1', '$2a$10$jiHZck.LrhPi2DHSc31zyOJXryzZDFPdfFleQ6X0NNWWh9n.PS0U.', 'feibu1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '333333333', NULL, '12333', NULL, NULL, NULL),
(13, 'usermvo1', '$2a$10$jiHZck.LrhPi2DHSc31zyOJXryzZDFPdfFleQ6X0NNWWh9n.PS0U.', 'USERMVO1', NULL, '2', NULL, NULL, NULL, NULL, NULL, '00@qq.com', NULL, '000000000', NULL, NULL, NULL),
(17, 'bvo1', '$2a$10$GKMH5gGjeJlb27ZD8/VpqOxeAQbv48rw4KXWsVIYDcNqLKTtBaeGW', 'bvo1', NULL, '2', NULL, NULL, NULL, NULL, NULL, 'qq@qq.com', NULL, '1234565', NULL, NULL, NULL),
(18, 'bvo2', '$2a$10$oFhJ96lJU1RqTyr.44fwk.UU7vWljtnGCI8VLNYHGFK3Fxir3MC6i', 'bvo2', NULL, '2', NULL, NULL, '1', NULL, NULL, 'qq@qq.com', NULL, '68798', NULL, NULL, NULL),
(21, 'REEM', '$2a$10$QWAF.nOBq2TtzGCKA7W4t.quz.Sm9LBgeTmp.36HZ9JsqzhHoP8sG', 'REEM', NULL, '3', NULL, NULL, '1', NULL, NULL, '343345@qq.com', NULL, '8789789', NULL, NULL, 'BVO'),
(22, 'r', '$2a$10$aEJxT2GJwJiXDcQuTgvvDuk/dvq5EspsSeKJnz3TktaBD9JTK373S', 'r', NULL, '3', NULL, NULL, '0', NULL, NULL, '235@qq.com', NULL, '1928007', NULL, NULL, 'BVO');

-- --------------------------------------------------------

--
-- Table structure for table `sys_user_role_relationship`
--

CREATE TABLE `sys_user_role_relationship` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `sys_user_role_relationship`
--

INSERT INTO `sys_user_role_relationship` (`id`, `user_id`, `role_id`) VALUES
(1, 3, 1),
(2, 2, 2),
(4, 3, 2),
(5, 3, 3),
(6, 4, 2),
(7, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_type`
--

CREATE TABLE `transaction_type` (
  `transaction_type_id` int(11) NOT NULL,
  `transaction_type_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction_type`
--

INSERT INTO `transaction_type` (`transaction_type_id`, `transaction_type_name`) VALUES
(1, 'Deposit'),
(2, 'Withdraw'),
(3, 'Transaction'),
(4, 'Payment'),
(5, 'Refund');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT '' COMMENT '昵称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `nickname`) VALUES
(1, 'tom', '123', 'tom'),
(2, 'jerry', '123', 'jerry'),
(3, 'scott', '123', 'scott'),
(7, 'blake', '123', 'blake'),
(8, 'tom1', '$2a$10$jiHZck.LrhPi2DHSc31zyOJXryzZDFPdfFleQ6X0NNWWh9n.PS0U.', 'tom1'),
(9, 'tom2', '$2a$10$jiHZck.LrhPi2DHSc31zyOJXryzZDFPdfFleQ6X0NNWWh9n.PS0U.', 'tom2'),
(10, 'tom4', '$2a$10$L90dC.oI4LLo7Eu6kDhEIehmhIw7GmhDeNuxIYUssG54d3HdspdO2', 'tom4'),
(11, 'tom5', '{bcrypt}$2a$10$yXX2atQo3ElYOQ.2dcFCXO5eXr476X9GGis5yU/KSs9JEYYjw1UFu', 'tom5'),
(12, 'tom6', '$2a$10$W.UJX0KGuz8JY2dyfu9ebuc6Logk/ClWNv9scnYEH91mq88kxIDWO', 'tom6'),
(13, 'admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `user_role_relationship`
--

CREATE TABLE `user_role_relationship` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `user_role_relationship`
--

INSERT INTO `user_role_relationship` (`id`, `user_id`, `role_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(10, 8, 2),
(11, 9, 2);

-- --------------------------------------------------------

--
-- Table structure for table `wallet_account`
--

CREATE TABLE `wallet_account` (
  `BUYER_ID` int(11) NOT NULL,
  `ACCOUNT_NAME` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(256) DEFAULT NULL,
  `ACTIVE_TIME` timestamp NULL DEFAULT NULL,
  `IS_ACTIVE` char(1) DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  `CREATE_BY` varchar(20) DEFAULT NULL,
  `CREATE_TIME` date DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(20) DEFAULT NULL,
  `LAST_UPDATE_TIME` date DEFAULT NULL,
  `ACCOUNT_TYPE` int(11) DEFAULT NULL,
  `HOLD_ORDER_TIME` char(2) DEFAULT NULL,
  `AUTO_PAY_STATUS` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `wallet_account`
--

INSERT INTO `wallet_account` (`BUYER_ID`, `ACCOUNT_NAME`, `EMAIL`, `PASSWORD`, `ACTIVE_TIME`, `IS_ACTIVE`, `STATUS`, `CREATE_BY`, `CREATE_TIME`, `LAST_UPDATE_BY`, `LAST_UPDATE_TIME`, `ACCOUNT_TYPE`, `HOLD_ORDER_TIME`, `AUTO_PAY_STATUS`) VALUES
(1, 'bvo', NULL, '1234', NULL, 'Y', NULL, 'bvo', '2021-03-14', NULL, NULL, 3, NULL, NULL),
(2, 'mvo', NULL, '1234', NULL, NULL, NULL, 'mvo', '2021-03-19', NULL, NULL, 2, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `wallet_account_fund`
--

CREATE TABLE `wallet_account_fund` (
  `BUYER_ID` int(11) NOT NULL,
  `AVAILABLE_MONEY` decimal(10,2) DEFAULT NULL,
  `DEPOSITING_MONEY` decimal(10,2) DEFAULT NULL,
  `WITHDRAWING_MONEY` decimal(10,2) DEFAULT NULL,
  `COMMISSION` decimal(10,4) DEFAULT NULL,
  `CREATE_BY` varchar(20) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(20) DEFAULT NULL,
  `LAST_UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `CURRENCY` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `wallet_account_fund`
--

INSERT INTO `wallet_account_fund` (`BUYER_ID`, `AVAILABLE_MONEY`, `DEPOSITING_MONEY`, `WITHDRAWING_MONEY`, `COMMISSION`, `CREATE_BY`, `CREATE_TIME`, `LAST_UPDATE_BY`, `LAST_UPDATE_TIME`, `CURRENCY`) VALUES
(1, '0.00', '4314386.00', '-3420337.00', '63735.9700', NULL, NULL, 'bvorrrr', '2021-03-14 20:00:43', NULL),
(2, NULL, '194122.00', '-135622.00', '100.0000', 'mvo', '2021-03-19 14:06:17', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `wallet_account_perm`
--

CREATE TABLE `wallet_account_perm` (
  `buyer_id` int(11) NOT NULL,
  `is_direct_deposit` int(2) DEFAULT NULL,
  `is_direct_withdraw` int(2) DEFAULT NULL,
  `is_direct_transaction` int(2) DEFAULT NULL,
  `add_deposit` int(2) DEFAULT NULL,
  `add_withdraw` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `wallet_account_perm`
--

INSERT INTO `wallet_account_perm` (`buyer_id`, `is_direct_deposit`, `is_direct_withdraw`, `is_direct_transaction`, `add_deposit`, `add_withdraw`) VALUES
(1, 1, 1, 0, 1, 1),
(2, 1, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `wallet_commission`
--

CREATE TABLE `wallet_commission` (
  `commission_id` int(11) NOT NULL,
  `percent` decimal(10,3) DEFAULT NULL,
  `transaction_type_id` bigint(11) DEFAULT NULL,
  `status` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `wallet_commission`
--

INSERT INTO `wallet_commission` (`commission_id`, `percent`, `transaction_type_id`, `status`) VALUES
(3, '1.200', 2, NULL),
(4, '2.500', 3, NULL),
(5, '10.000', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `wallet_transaction_audit`
--

CREATE TABLE `wallet_transaction_audit` (
  `Buyer_Id` int(11) NOT NULL,
  `depositing_money` decimal(10,2) DEFAULT NULL,
  `withdrawing_money` decimal(10,2) DEFAULT 0.00,
  `commission` decimal(10,2) UNSIGNED DEFAULT 0.00,
  `transaction_type_id` int(10) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `wallet_transaction_record`
--

CREATE TABLE `wallet_transaction_record` (
  `TRANSACTION_AUDIT_ID` int(11) NOT NULL,
  `BUYER_ID` int(11) DEFAULT NULL,
  `TRANSACTION_ID` int(11) DEFAULT NULL,
  `AVAILABLE_MONEY_BEFORE` decimal(50,4) DEFAULT NULL,
  `DEPOSITING_MONEY_BEFORE` decimal(50,4) DEFAULT NULL,
  `WITHDRAWING_MONEY_BEFORE` decimal(50,4) DEFAULT NULL,
  `COMMISSION_BEFORE` decimal(50,4) DEFAULT NULL,
  `OPERATE_MONEY` decimal(50,4) DEFAULT NULL,
  `OPERATE_TYPE` tinyint(4) DEFAULT NULL,
  `AVAILABLE_MONEY_AFTER` decimal(50,4) DEFAULT NULL,
  `DEPOSITING_MONEY_AFTER` decimal(50,4) DEFAULT NULL,
  `WITHDRAWING_MONEY_AFTER` decimal(50,4) DEFAULT NULL,
  `COMMISSION_AFTER` decimal(50,4) DEFAULT NULL,
  `DEPOSITING_MONEY_ACTUAL` decimal(50,4) DEFAULT NULL,
  `WITHDRAWING_MONEY_ACTUAL` decimal(50,4) DEFAULT NULL,
  `COMMISSION_MONEY_ACTUAL` decimal(50,4) DEFAULT NULL,
  `TRANSACTION_TYPE_ID` int(10) DEFAULT NULL,
  `IS_CANCEL` int(2) DEFAULT NULL,
  `REASON_CANCEL` varchar(255) DEFAULT NULL,
  `OPERATE_BY` varchar(20) DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  `CREATE_BY` varchar(20) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(20) DEFAULT NULL,
  `LAST_UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `order_id` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `wallet_transaction_record`
--

INSERT INTO `wallet_transaction_record` (`TRANSACTION_AUDIT_ID`, `BUYER_ID`, `TRANSACTION_ID`, `AVAILABLE_MONEY_BEFORE`, `DEPOSITING_MONEY_BEFORE`, `WITHDRAWING_MONEY_BEFORE`, `COMMISSION_BEFORE`, `OPERATE_MONEY`, `OPERATE_TYPE`, `AVAILABLE_MONEY_AFTER`, `DEPOSITING_MONEY_AFTER`, `WITHDRAWING_MONEY_AFTER`, `COMMISSION_AFTER`, `DEPOSITING_MONEY_ACTUAL`, `WITHDRAWING_MONEY_ACTUAL`, `COMMISSION_MONEY_ACTUAL`, `TRANSACTION_TYPE_ID`, `IS_CANCEL`, `REASON_CANCEL`, `OPERATE_BY`, `STATUS`, `CREATE_BY`, `CREATE_TIME`, `LAST_UPDATE_BY`, `LAST_UPDATE_TIME`, `order_id`) VALUES
(52, 1, NULL, NULL, '10000.0000', NULL, NULL, '10000.0000', 1, '10000.0000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'bvo', '2021-03-14 19:57:34', 'bvo', '2021-03-14 19:57:34', NULL),
(53, 1, NULL, '5000.0000', '10000.0000', '5000.0000', '0.0000', '0.0000', NULL, NULL, '1185289.5000', '5000.0000', '25210.5000', '1200500.0000', '0.0000', '25210.5000', 1, 0, NULL, NULL, NULL, 'bvo', '2021-03-16 03:04:48', NULL, NULL, NULL),
(54, 1, NULL, '1180289.5000', '1185289.5000', '5000.0000', '25210.5000', '0.0000', NULL, NULL, '1186289.5000', '5000.0000', '25210.5000', '1000.0000', '0.0000', '0.0000', 1, 0, NULL, NULL, NULL, 'bvo', '2021-03-16 03:11:05', NULL, NULL, NULL),
(55, 1, NULL, '1181289.5000', '1186289.5000', '5000.0000', '25210.5000', '0.0000', NULL, NULL, '1172006.0000', '-1199573.0000', '39493.9700', '0.0000', '1190289.5000', '14283.4700', 2, 0, NULL, NULL, NULL, 'bvo', '2021-03-16 03:20:34', NULL, NULL, NULL),
(56, 1, NULL, '2371579.0000', '1172006.0000', '-1199573.0000', '39493.9700', '0.0000', NULL, NULL, '4172006.0000', '-1199573.0000', '39493.9700', '3000000.0000', '0.0000', '0.0000', 1, 0, NULL, NULL, NULL, 'bvo', '2021-03-16 03:21:34', NULL, NULL, NULL),
(57, 1, NULL, '5371579.0000', '4172006.0000', '-1199573.0000', '39493.9700', '0.0000', NULL, NULL, '4148006.0000', '-3223573.0000', '63493.9700', '0.0000', '2000000.0000', '24000.0000', 2, 0, NULL, NULL, NULL, 'bvo', '2021-03-16 03:22:21', NULL, NULL, NULL),
(58, 1, NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', NULL, '7383579.0000', '4160006.0000', '-3223573.0000', '63493.9700', '12000.0000', '0.0000', '0.0000', 1, 0, 'this amount doesn\'t enogh', NULL, NULL, 'bvo', '2021-03-16 12:50:02', NULL, NULL, NULL),
(59, 1, NULL, '7383579.0000', '4160006.0000', '-3223573.0000', '63493.9700', '0.0000', NULL, '7395579.0000', '4172006.0000', '-3223573.0000', '63493.9700', '12000.0000', '0.0000', '0.0000', 1, 0, NULL, NULL, NULL, 'bvo', '2021-03-17 06:55:15', NULL, NULL, NULL),
(60, 1, NULL, '7395579.0000', '4172006.0000', '-3223573.0000', '63493.9700', '0.0000', NULL, '7396579.0000', '4173006.0000', '-3223573.0000', '63493.9700', '1000.0000', '0.0000', '0.0000', 1, 0, NULL, NULL, NULL, 'bvo', '2021-03-17 06:55:51', NULL, NULL, NULL),
(61, 1, NULL, '7396579.0000', '4173006.0000', '-3223573.0000', '63493.9700', '0.0000', NULL, '7397579.0000', '4174006.0000', '-3223573.0000', '63493.9700', '1000.0000', '0.0000', '0.0000', 1, 0, NULL, NULL, NULL, 'bvo', '2021-03-17 07:09:23', NULL, NULL, NULL),
(62, 1, NULL, '7397579.0000', '4174006.0000', '-3223573.0000', '63493.9700', '0.0000', NULL, '7399579.0000', '4176006.0000', '-3223573.0000', '63493.9700', '2000.0000', '0.0000', '0.0000', 1, 0, NULL, NULL, NULL, 'bvo', '2021-03-17 08:55:50', NULL, NULL, NULL),
(63, 1, NULL, '7399579.0000', '4176006.0000', '-3223573.0000', '63493.9700', '0.0000', NULL, '7400579.0000', '4175994.0000', '-3224585.0000', '63505.9700', '0.0000', '1000.0000', '12.0000', 2, 0, NULL, NULL, NULL, 'bvo', '2021-03-17 08:57:20', NULL, NULL, NULL),
(64, 1, NULL, '7400579.0000', '4175994.0000', '-3224585.0000', '63505.9700', '0.0000', NULL, '7401579.0000', '4175982.0000', '-3225597.0000', '63517.9700', '0.0000', '1000.0000', '12.0000', 2, 0, NULL, NULL, NULL, 'bvo', '2021-03-17 09:03:47', NULL, NULL, NULL),
(65, 1, NULL, '7401579.0000', '4175982.0000', '-3225597.0000', '63517.9700', '0.0000', NULL, '7402579.0000', '4176982.0000', '-3225597.0000', '63517.9700', '1000.0000', '0.0000', '0.0000', 1, 0, NULL, NULL, NULL, 'bvo', '2021-03-17 09:08:48', NULL, NULL, NULL),
(66, 1, NULL, '951385.0000', '4176982.0000', '-3225597.0000', '63517.9700', '0.0000', NULL, '951363.0000', '4176982.0000', '-3225619.0000', '63517.9700', '0.0000', '22.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 14:06:35', NULL, NULL, 49),
(67, 2, NULL, '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', NULL, '22.0000', '22.0000', '0.0000', '0.0000', '22.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 14:06:35', NULL, NULL, 49),
(68, 1, NULL, '951363.0000', '4176982.0000', '-3225619.0000', '63517.9700', '0.0000', NULL, '941763.0000', '4176982.0000', '-3235219.0000', '63517.9700', '0.0000', '9600.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 14:51:11', NULL, NULL, 51),
(69, 2, NULL, '22.0000', '22.0000', '0.0000', '0.0000', '0.0000', NULL, '9622.0000', '9622.0000', '0.0000', '0.0000', '9600.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 14:51:11', NULL, NULL, 51),
(70, 1, NULL, '941763.0000', '4176982.0000', '-3235219.0000', '63517.9700', '0.0000', NULL, '941763.0000', '4176982.0000', '-3235219.0000', '63517.9700', '0.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 15:28:04', NULL, NULL, 51),
(71, 2, NULL, '9622.0000', '9622.0000', '0.0000', '0.0000', '0.0000', NULL, '9622.0000', '9622.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 15:28:04', NULL, NULL, 51),
(72, 1, NULL, '941763.0000', '4176982.0000', '-3235219.0000', '63517.9700', '0.0000', NULL, '932163.0000', '4176982.0000', '-3244819.0000', '63517.9700', '0.0000', '9600.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 15:30:50', NULL, NULL, 52),
(73, 2, NULL, '9622.0000', '9622.0000', '0.0000', '0.0000', '0.0000', NULL, '19222.0000', '19222.0000', '0.0000', '0.0000', '9600.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 15:30:50', NULL, NULL, 52),
(74, 1, NULL, '932163.0000', '4176982.0000', '-3244819.0000', '63517.9700', '0.0000', NULL, '932163.0000', '4176982.0000', '-3244819.0000', '63517.9700', '0.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 15:31:31', NULL, NULL, 52),
(75, 2, NULL, '19222.0000', '19222.0000', '0.0000', '0.0000', '0.0000', NULL, '19222.0000', '19222.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 15:31:31', NULL, NULL, 52),
(76, 1, NULL, '932163.0000', '4176982.0000', '-3244819.0000', '63517.9700', '0.0000', NULL, '922563.0000', '4176982.0000', '-3254419.0000', '63517.9700', '0.0000', '9600.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 15:34:13', NULL, NULL, 53),
(77, 2, NULL, '19222.0000', '19222.0000', '0.0000', '0.0000', '0.0000', NULL, '28822.0000', '28822.0000', '0.0000', '0.0000', '9600.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 15:34:13', NULL, NULL, 53),
(78, 2, NULL, '28822.0000', '28822.0000', '0.0000', '0.0000', '0.0000', NULL, '28822.0000', '28822.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 15:34:29', NULL, NULL, 53),
(79, 1, NULL, '922563.0000', '4176982.0000', '-3254419.0000', '63517.9700', '0.0000', NULL, '922563.0000', '4176982.0000', '-3254419.0000', '63517.9700', '0.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 15:34:29', NULL, NULL, 53),
(80, 1, NULL, '922563.0000', '4176982.0000', '-3254419.0000', '63517.9700', '0.0000', NULL, '908163.0000', '4176982.0000', '-3268819.0000', '63517.9700', '0.0000', '14400.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 15:40:18', NULL, NULL, 54),
(81, 2, NULL, '28822.0000', '28822.0000', '0.0000', '0.0000', '0.0000', NULL, '43222.0000', '43222.0000', '0.0000', '0.0000', '14400.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 15:40:18', NULL, NULL, 54),
(82, 2, NULL, '43222.0000', '43222.0000', '0.0000', '0.0000', '0.0000', NULL, '43222.0000', '43222.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 15:40:27', NULL, NULL, 54),
(83, 1, NULL, '908163.0000', '4176982.0000', '-3268819.0000', '63517.9700', '0.0000', NULL, '908163.0000', '4176982.0000', '-3268819.0000', '63517.9700', '0.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 15:40:27', NULL, NULL, 54),
(84, 1, NULL, '908163.0000', '4176982.0000', '-3268819.0000', '63517.9700', '0.0000', NULL, '898563.0000', '4176982.0000', '-3278419.0000', '63517.9700', '0.0000', '9600.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 15:43:05', NULL, NULL, 55),
(85, 2, NULL, '43222.0000', '43222.0000', '0.0000', '0.0000', '0.0000', NULL, '52822.0000', '52822.0000', '0.0000', '0.0000', '9600.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 15:43:05', NULL, NULL, 55),
(86, 2, NULL, '52822.0000', '52822.0000', '0.0000', '0.0000', '0.0000', NULL, '52822.0000', '52822.0000', '0.0000', '0.0000', '0.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 15:43:12', NULL, NULL, 55),
(87, 1, NULL, '898563.0000', '4176982.0000', '-3278419.0000', '63517.9700', '0.0000', NULL, '898563.0000', '4176982.0000', '-3278419.0000', '63517.9700', '0.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 15:43:12', NULL, NULL, 55),
(88, 1, NULL, '898563.0000', '4176982.0000', '-3278419.0000', '63517.9700', '0.0000', NULL, '899463.0000', '4177882.0000', '-3278419.0000', '63617.9700', '1000.0000', '0.0000', '100.0000', 1, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 15:51:17', NULL, NULL, 0),
(89, 1, NULL, '899463.0000', '4177882.0000', '-3278419.0000', '63617.9700', '0.0000', NULL, '851463.0000', '4177882.0000', '-3326419.0000', '63617.9700', '0.0000', '48000.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 15:55:47', NULL, NULL, 56),
(90, 2, NULL, '52822.0000', '52822.0000', '0.0000', '0.0000', '0.0000', NULL, '100822.0000', '100822.0000', '0.0000', '0.0000', '48000.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 15:55:48', NULL, NULL, 56),
(91, 1, NULL, '851463.0000', '4177882.0000', '-3326419.0000', '63617.9700', '0.0000', NULL, '852363.0000', '4178782.0000', '-3326419.0000', '63717.9700', '1000.0000', '0.0000', '100.0000', 1, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 15:57:36', NULL, NULL, 0),
(92, 2, NULL, '100822.0000', '100822.0000', '0.0000', '0.0000', '0.0000', NULL, '0.0000', '100822.0000', '-100822.0000', '0.0000', '0.0000', '100822.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 15:58:36', NULL, NULL, 56),
(93, 1, NULL, '852363.0000', '4178782.0000', '-3326419.0000', '63717.9700', '0.0000', NULL, '953185.0000', '4279604.0000', '-3326419.0000', '63717.9700', '100822.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 15:58:36', NULL, NULL, 56),
(94, 1, NULL, '953185.0000', '4279604.0000', '-3326419.0000', '63717.9700', '0.0000', NULL, '948385.0000', '4279604.0000', '-3331219.0000', '63717.9700', '0.0000', '4800.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 16:03:40', NULL, NULL, 57),
(95, 2, NULL, '0.0000', '100822.0000', '-100822.0000', '0.0000', '0.0000', NULL, '4800.0000', '105622.0000', '-100822.0000', '0.0000', '4800.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 16:03:40', NULL, NULL, 57),
(96, 2, NULL, '4800.0000', '105622.0000', '-100822.0000', '0.0000', '0.0000', NULL, '5700.0000', '106522.0000', '-100822.0000', '100.0000', '1000.0000', '0.0000', '100.0000', 1, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 16:04:32', NULL, NULL, 0),
(97, 2, NULL, '5700.0000', '106522.0000', '-100822.0000', '100.0000', '0.0000', NULL, '900.0000', '106522.0000', '-105622.0000', '100.0000', '0.0000', '4800.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 16:07:12', NULL, NULL, 57),
(98, 1, NULL, '948385.0000', '4279604.0000', '-3331219.0000', '63717.9700', '0.0000', NULL, '953185.0000', '4284404.0000', '-3331219.0000', '63717.9700', '4800.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 16:07:12', NULL, NULL, 57),
(99, 1, NULL, '953185.0000', '4284404.0000', '-3331219.0000', '63717.9700', '0.0000', NULL, '948385.0000', '4284404.0000', '-3336019.0000', '63717.9700', '0.0000', '4800.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 16:11:03', NULL, NULL, 58),
(100, 2, NULL, '900.0000', '106522.0000', '-105622.0000', '100.0000', '0.0000', NULL, '5700.0000', '111322.0000', '-105622.0000', '100.0000', '4800.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 16:11:03', NULL, NULL, 58),
(101, 2, NULL, '5700.0000', '111322.0000', '-105622.0000', '100.0000', '0.0000', NULL, '900.0000', '111322.0000', '-110422.0000', '100.0000', '0.0000', '4800.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 16:12:16', NULL, NULL, 58),
(102, 1, NULL, '948385.0000', '4284404.0000', '-3336019.0000', '63717.9700', '0.0000', NULL, '953185.0000', '4289204.0000', '-3336019.0000', '63717.9700', '4800.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 16:12:16', NULL, NULL, 58),
(103, 1, NULL, '953185.0000', '4289204.0000', '-3336019.0000', '63717.9700', '0.0000', NULL, '933985.0000', '4289204.0000', '-3355219.0000', '63717.9700', '0.0000', '19200.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 16:14:37', NULL, NULL, 59),
(104, 2, NULL, '900.0000', '111322.0000', '-110422.0000', '100.0000', '0.0000', NULL, '20100.0000', '130522.0000', '-110422.0000', '100.0000', '19200.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 16:14:37', NULL, NULL, 59),
(105, 1, NULL, '933985.0000', '4289204.0000', '-3355219.0000', '63717.9700', '0.0000', NULL, '932961.0000', '4289192.0000', '-3356231.0000', '63729.9700', '0.0000', '1000.0000', '12.0000', 2, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 20:55:40', NULL, NULL, 0),
(106, 1, NULL, '932961.0000', '4289192.0000', '-3356231.0000', '63729.9700', '0.0000', NULL, '932449.0000', '4289186.0000', '-3356737.0000', '63735.9700', '0.0000', '500.0000', '6.0000', 2, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 20:57:30', NULL, NULL, 0),
(107, 1, NULL, '932449.0000', '4289186.0000', '-3356737.0000', '63735.9700', '0.0000', NULL, '922849.0000', '4289186.0000', '-3366337.0000', '63735.9700', '0.0000', '9600.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 21:01:26', NULL, NULL, 60),
(108, 2, NULL, '20100.0000', '130522.0000', '-110422.0000', '100.0000', '0.0000', NULL, '29700.0000', '140122.0000', '-110422.0000', '100.0000', '9600.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 21:01:26', NULL, NULL, 60),
(109, 1, NULL, '922849.0000', '4289186.0000', '-3366337.0000', '63735.9700', '0.0000', NULL, '903649.0000', '4289186.0000', '-3385537.0000', '63735.9700', '0.0000', '19200.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 21:04:50', NULL, NULL, 61),
(110, 2, NULL, '29700.0000', '140122.0000', '-110422.0000', '100.0000', '0.0000', NULL, '48900.0000', '159322.0000', '-110422.0000', '100.0000', '19200.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-19 21:04:50', NULL, NULL, 61),
(111, 2, NULL, '48900.0000', '159322.0000', '-110422.0000', '100.0000', '0.0000', NULL, '29700.0000', '159322.0000', '-129622.0000', '100.0000', '0.0000', '19200.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 21:05:16', NULL, NULL, 61),
(112, 1, NULL, '903649.0000', '4289186.0000', '-3385537.0000', '63735.9700', '0.0000', NULL, '922849.0000', '4308386.0000', '-3385537.0000', '63735.9700', '19200.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-19 21:05:16', NULL, NULL, 61),
(113, 1, NULL, '922849.0000', '4308386.0000', '-3385537.0000', '63735.9700', '0.0000', NULL, '916849.0000', '4308386.0000', '-3391537.0000', '63735.9700', '0.0000', '6000.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-20 12:32:17', NULL, NULL, 62),
(114, 2, NULL, '29700.0000', '159322.0000', '-129622.0000', '100.0000', '0.0000', NULL, '35700.0000', '165322.0000', '-129622.0000', '100.0000', '6000.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-20 12:32:17', NULL, NULL, 62),
(115, 2, NULL, '35700.0000', '165322.0000', '-129622.0000', '100.0000', '0.0000', NULL, '29700.0000', '165322.0000', '-135622.0000', '100.0000', '0.0000', '6000.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-20 12:33:00', NULL, NULL, 62),
(116, 1, NULL, '916849.0000', '4308386.0000', '-3391537.0000', '63735.9700', '0.0000', NULL, '922849.0000', '4314386.0000', '-3391537.0000', '63735.9700', '6000.0000', '0.0000', '0.0000', 5, 0, NULL, NULL, NULL, 'mvo', '2021-03-20 12:33:00', NULL, NULL, 62),
(117, 1, NULL, '922849.0000', '4314386.0000', '-3391537.0000', '63735.9700', '0.0000', NULL, '908449.0000', '4314386.0000', '-3405937.0000', '63735.9700', '0.0000', '14400.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-20 13:24:42', NULL, NULL, 63),
(118, 2, NULL, '29700.0000', '165322.0000', '-135622.0000', '100.0000', '0.0000', NULL, '44100.0000', '179722.0000', '-135622.0000', '100.0000', '14400.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-20 13:24:42', NULL, NULL, 63),
(119, 1, NULL, '908449.0000', '4314386.0000', '-3405937.0000', '63735.9700', '0.0000', NULL, '894049.0000', '4314386.0000', '-3420337.0000', '63735.9700', '0.0000', '14400.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-20 13:36:10', NULL, NULL, 65),
(120, 2, NULL, '44100.0000', '179722.0000', '-135622.0000', '100.0000', '0.0000', NULL, '58500.0000', '194122.0000', '-135622.0000', '100.0000', '14400.0000', '0.0000', '0.0000', 4, 0, NULL, NULL, NULL, 'bvo', '2021-03-20 13:36:10', NULL, NULL, 65);

-- --------------------------------------------------------

--
-- Table structure for table `wallet_transaction_record1`
--

CREATE TABLE `wallet_transaction_record1` (
  `TRANSACTION_ID` int(11) NOT NULL,
  `BUYER_ID` int(11) DEFAULT NULL,
  `BANK_CARD_ID` int(11) DEFAULT NULL,
  `TRANSACTION_NUMBER` varchar(255) DEFAULT NULL,
  `TRANSACTION_TYPE` tinyint(4) DEFAULT NULL,
  `ACCOUNT_NAME` varchar(50) DEFAULT NULL,
  `TRANSACTION_MONEY` decimal(10,4) DEFAULT NULL,
  `COMMISSION` decimal(10,4) DEFAULT NULL,
  `DISCOUNT_COMMISSION` decimal(10,4) DEFAULT NULL,
  `TRANSACTION_METHOD` tinyint(4) DEFAULT NULL,
  `COMPLETE_TIME` timestamp NULL DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  `ACTUAL_MONEY` decimal(10,4) DEFAULT NULL,
  `ACTUAL_COMMISSION` decimal(10,4) DEFAULT NULL,
  `ACTUAL_DISCOUNT_COMMISSION` decimal(10,4) DEFAULT NULL,
  `BALANCE` decimal(10,4) DEFAULT NULL,
  `BUSINESS_ID` int(11) DEFAULT NULL,
  `FINANCE_TYPE` tinyint(4) DEFAULT NULL,
  `NOTE` varchar(1024) DEFAULT NULL,
  `OPERATOR_NAME` varchar(64) DEFAULT NULL,
  `OPERATOR_IP` varchar(32) DEFAULT NULL,
  `CREATE_BY` varchar(20) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_BY` varchar(20) DEFAULT NULL,
  `LAST_UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `BANK_RECEIPT_URL` varchar(255) DEFAULT NULL,
  `EXCHANGE_RATE` decimal(10,4) DEFAULT NULL,
  `TRANSACTION_DESC` varchar(255) DEFAULT NULL,
  `FOREIGN_EXCHANGE_FEE` decimal(10,4) DEFAULT NULL,
  `WITHDRAW_FEE` decimal(10,4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `wallet_transaction_record1`
--

INSERT INTO `wallet_transaction_record1` (`TRANSACTION_ID`, `BUYER_ID`, `BANK_CARD_ID`, `TRANSACTION_NUMBER`, `TRANSACTION_TYPE`, `ACCOUNT_NAME`, `TRANSACTION_MONEY`, `COMMISSION`, `DISCOUNT_COMMISSION`, `TRANSACTION_METHOD`, `COMPLETE_TIME`, `STATUS`, `ACTUAL_MONEY`, `ACTUAL_COMMISSION`, `ACTUAL_DISCOUNT_COMMISSION`, `BALANCE`, `BUSINESS_ID`, `FINANCE_TYPE`, `NOTE`, `OPERATOR_NAME`, `OPERATOR_IP`, `CREATE_BY`, `CREATE_TIME`, `LAST_UPDATE_BY`, `LAST_UPDATE_TIME`, `BANK_RECEIPT_URL`, `EXCHANGE_RATE`, `TRANSACTION_DESC`, `FOREIGN_EXCHANGE_FEE`, `WITHDRAW_FEE`) VALUES
(144, 1, NULL, NULL, 1, 'bvo', '10000.0000', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bvo', '2021-03-14 19:57:34', 'bvo', '2021-03-14 19:57:34', NULL, NULL, NULL, NULL, NULL),
(145, 1, NULL, NULL, 2, NULL, '5000.0000', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-03-14 19:57:53', NULL, '2021-03-14 19:57:53', NULL, NULL, NULL, NULL, NULL),
(146, 1, NULL, NULL, 2, NULL, '1000.0000', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-03-14 20:00:43', NULL, '2021-03-14 20:00:43', NULL, NULL, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brand_product`
--
ALTER TABLE `brand_product`
  ADD PRIMARY KEY (`Brand_id`) USING BTREE;

--
-- Indexes for table `brand_product_categ_product_relationship`
--
ALTER TABLE `brand_product_categ_product_relationship`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `categ_product`
--
ALTER TABLE `categ_product`
  ADD PRIMARY KEY (`Categ_id`) USING BTREE;

--
-- Indexes for table `dept`
--
ALTER TABLE `dept`
  ADD PRIMARY KEY (`DEPTNO`) USING BTREE;

--
-- Indexes for table `emp`
--
ALTER TABLE `emp`
  ADD PRIMARY KEY (`EMPNO`) USING BTREE,
  ADD KEY `FK_DEPTNO` (`DEPTNO`) USING BTREE;

--
-- Indexes for table `man_manufacturer`
--
ALTER TABLE `man_manufacturer`
  ADD PRIMARY KEY (`MAN_ID`) USING BTREE;

--
-- Indexes for table `par_parameter`
--
ALTER TABLE `par_parameter`
  ADD PRIMARY KEY (`PAR_ID`) USING BTREE;

--
-- Indexes for table `pro_brand`
--
ALTER TABLE `pro_brand`
  ADD PRIMARY KEY (`BRD_ID`) USING BTREE,
  ADD KEY `brd_brand_ibfk_1` (`MAN_ID`) USING BTREE;

--
-- Indexes for table `pro_cart`
--
ALTER TABLE `pro_cart`
  ADD PRIMARY KEY (`Cart_id`);

--
-- Indexes for table `pro_order`
--
ALTER TABLE `pro_order`
  ADD PRIMARY KEY (`or_id`) USING BTREE;

--
-- Indexes for table `pro_product`
--
ALTER TABLE `pro_product`
  ADD PRIMARY KEY (`PRO_ID`) USING BTREE,
  ADD KEY `BRD_ID` (`BRD_ID`) USING BTREE,
  ADD KEY `MAN_ID` (`MAN_ID`) USING BTREE;

--
-- Indexes for table `pro_status`
--
ALTER TABLE `pro_status`
  ADD PRIMARY KEY (`status_code`) USING BTREE;

--
-- Indexes for table `pro_wish`
--
ALTER TABLE `pro_wish`
  ADD PRIMARY KEY (`wish_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`) USING BTREE;

--
-- Indexes for table `sys_company`
--
ALTER TABLE `sys_company`
  ADD PRIMARY KEY (`user_id`) USING BTREE;

--
-- Indexes for table `sys_data`
--
ALTER TABLE `sys_data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sys_menu`
--
ALTER TABLE `sys_menu`
  ADD PRIMARY KEY (`MENU_ID`) USING BTREE;

--
-- Indexes for table `sys_menu_role_relationship`
--
ALTER TABLE `sys_menu_role_relationship`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `sys_role`
--
ALTER TABLE `sys_role`
  ADD PRIMARY KEY (`role_id`) USING BTREE;

--
-- Indexes for table `sys_user`
--
ALTER TABLE `sys_user`
  ADD PRIMARY KEY (`USER_ID`) USING BTREE;

--
-- Indexes for table `sys_user_role_relationship`
--
ALTER TABLE `sys_user_role_relationship`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `transaction_type`
--
ALTER TABLE `transaction_type`
  ADD PRIMARY KEY (`transaction_type_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `user_role_relationship`
--
ALTER TABLE `user_role_relationship`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `wallet_account`
--
ALTER TABLE `wallet_account`
  ADD PRIMARY KEY (`BUYER_ID`) USING BTREE;

--
-- Indexes for table `wallet_account_fund`
--
ALTER TABLE `wallet_account_fund`
  ADD PRIMARY KEY (`BUYER_ID`) USING BTREE;

--
-- Indexes for table `wallet_account_perm`
--
ALTER TABLE `wallet_account_perm`
  ADD PRIMARY KEY (`buyer_id`);

--
-- Indexes for table `wallet_commission`
--
ALTER TABLE `wallet_commission`
  ADD PRIMARY KEY (`commission_id`);

--
-- Indexes for table `wallet_transaction_audit`
--
ALTER TABLE `wallet_transaction_audit`
  ADD PRIMARY KEY (`Buyer_Id`);

--
-- Indexes for table `wallet_transaction_record`
--
ALTER TABLE `wallet_transaction_record`
  ADD PRIMARY KEY (`TRANSACTION_AUDIT_ID`) USING BTREE,
  ADD KEY `BUYER_ID` (`BUYER_ID`) USING BTREE,
  ADD KEY `TRANSACTION_ID` (`TRANSACTION_ID`) USING BTREE;

--
-- Indexes for table `wallet_transaction_record1`
--
ALTER TABLE `wallet_transaction_record1`
  ADD PRIMARY KEY (`TRANSACTION_ID`) USING BTREE,
  ADD KEY `BUYER_ID` (`BUYER_ID`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `brand_product`
--
ALTER TABLE `brand_product`
  MODIFY `Brand_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `brand_product_categ_product_relationship`
--
ALTER TABLE `brand_product_categ_product_relationship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `categ_product`
--
ALTER TABLE `categ_product`
  MODIFY `Categ_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `dept`
--
ALTER TABLE `dept`
  MODIFY `DEPTNO` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `emp`
--
ALTER TABLE `emp`
  MODIFY `EMPNO` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7937;

--
-- AUTO_INCREMENT for table `par_parameter`
--
ALTER TABLE `par_parameter`
  MODIFY `PAR_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=135;

--
-- AUTO_INCREMENT for table `pro_brand`
--
ALTER TABLE `pro_brand`
  MODIFY `BRD_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `pro_cart`
--
ALTER TABLE `pro_cart`
  MODIFY `Cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT for table `pro_order`
--
ALTER TABLE `pro_order`
  MODIFY `or_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `pro_product`
--
ALTER TABLE `pro_product`
  MODIFY `PRO_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=114;

--
-- AUTO_INCREMENT for table `pro_wish`
--
ALTER TABLE `pro_wish`
  MODIFY `wish_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sys_data`
--
ALTER TABLE `sys_data`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sys_menu`
--
ALTER TABLE `sys_menu`
  MODIFY `MENU_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `sys_menu_role_relationship`
--
ALTER TABLE `sys_menu_role_relationship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `sys_role`
--
ALTER TABLE `sys_role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sys_user`
--
ALTER TABLE `sys_user`
  MODIFY `USER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `sys_user_role_relationship`
--
ALTER TABLE `sys_user_role_relationship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `transaction_type`
--
ALTER TABLE `transaction_type`
  MODIFY `transaction_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `user_role_relationship`
--
ALTER TABLE `user_role_relationship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `wallet_commission`
--
ALTER TABLE `wallet_commission`
  MODIFY `commission_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `wallet_transaction_record`
--
ALTER TABLE `wallet_transaction_record`
  MODIFY `TRANSACTION_AUDIT_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121;

--
-- AUTO_INCREMENT for table `wallet_transaction_record1`
--
ALTER TABLE `wallet_transaction_record1`
  MODIFY `TRANSACTION_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=147;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `emp`
--
ALTER TABLE `emp`
  ADD CONSTRAINT `FK_DEPTNO` FOREIGN KEY (`DEPTNO`) REFERENCES `dept` (`DEPTNO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
