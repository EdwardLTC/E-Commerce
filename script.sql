USE [master]
GO
/****** Object:  Database [EdwardEcomerce]    Script Date: 11/4/2022 10:25:57 PM ******/
CREATE DATABASE [EdwardEcomerce]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'EdwardEcomerce', FILENAME = N'D:\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\EdwardEcomerce.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'EdwardEcomerce_log', FILENAME = N'D:\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\EdwardEcomerce_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [EdwardEcomerce] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [EdwardEcomerce].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [EdwardEcomerce] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET ARITHABORT OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [EdwardEcomerce] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [EdwardEcomerce] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [EdwardEcomerce] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET  ENABLE_BROKER 
GO
ALTER DATABASE [EdwardEcomerce] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [EdwardEcomerce] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [EdwardEcomerce] SET  MULTI_USER 
GO
ALTER DATABASE [EdwardEcomerce] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [EdwardEcomerce] SET DB_CHAINING OFF 
GO
ALTER DATABASE [EdwardEcomerce] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [EdwardEcomerce] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [EdwardEcomerce] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [EdwardEcomerce] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [EdwardEcomerce] SET QUERY_STORE = OFF
GO
USE [EdwardEcomerce]
GO
/****** Object:  User [Edward]    Script Date: 11/4/2022 10:25:58 PM ******/
CREATE USER [Edward] FOR LOGIN [Edward] WITH DEFAULT_SCHEMA=[Edward]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [Edward]
GO
ALTER ROLE [db_backupoperator] ADD MEMBER [Edward]
GO
ALTER ROLE [db_datareader] ADD MEMBER [Edward]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [Edward]
GO
/****** Object:  Schema [Edward]    Script Date: 11/4/2022 10:25:58 PM ******/
CREATE SCHEMA [Edward]
GO
/****** Object:  Schema [kynalabc]    Script Date: 11/4/2022 10:25:58 PM ******/
CREATE SCHEMA [kynalabc]
GO
/****** Object:  Table [Edward].[Bill]    Script Date: 11/4/2022 10:25:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[Bill](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[IDUser] [int] NULL,
	[IDvoucher] [int] NULL,
	[DateCreate] [date] NULL,
	[DateReceived] [date] NULL,
	[Status] [nvarchar](256) NULL,
 CONSTRAINT [PK_Bill] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[BillDetail]    Script Date: 11/4/2022 10:25:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[BillDetail](
	[IDBill] [int] NOT NULL,
	[IDClothes] [int] NOT NULL,
	[Quantity] [int] NULL,
	[price] [float] NULL,
 CONSTRAINT [PK_BillDetail] PRIMARY KEY CLUSTERED 
(
	[IDBill] ASC,
	[IDClothes] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Category]    Script Date: 11/4/2022 10:25:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](255) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Clothes]    Script Date: 11/4/2022 10:25:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[Clothes](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[IDseller] [int] NULL,
	[idCategory] [int] NULL,
	[des] [nvarchar](256) NULL,
 CONSTRAINT [PK_Clothes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Clothes_Properties]    Script Date: 11/4/2022 10:25:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[Clothes_Properties](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[IDClothes] [int] NULL,
	[size] [nvarchar](50) NULL,
	[Quantily] [int] NULL,
	[Price] [float] NULL,
 CONSTRAINT [PK_Table1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Favorites]    Script Date: 11/4/2022 10:25:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[Favorites](
	[IDpeople] [int] NOT NULL,
	[IDclothes] [int] NOT NULL,
 CONSTRAINT [PK_Favorites] PRIMARY KEY CLUSTERED 
(
	[IDpeople] ASC,
	[IDclothes] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[ImgUrls]    Script Date: 11/4/2022 10:25:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[ImgUrls](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[IDClothes] [int] NULL,
	[ImgUrl] [nvarchar](256) NULL,
 CONSTRAINT [PK_ImgUrls] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[People]    Script Date: 11/4/2022 10:25:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[People](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Mail] [nvarchar](50) NULL,
	[Psw] [nvarchar](50) NULL,
	[PhoneNum] [nvarchar](50) NULL,
	[Role] [int] NULL,
	[ImgUrl] [nvarchar](256) NULL,
	[Address] [nvarchar](50) NULL,
 CONSTRAINT [PK_People] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Voucher]    Script Date: 11/4/2022 10:25:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[Voucher](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[IDSeller] [int] NULL,
	[ratio] [int] NULL,
 CONSTRAINT [PK_Voucher] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [Edward].[Bill] ON 

INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (0, 75, 88, CAST(N'2022-04-03' AS Date), CAST(N'2022-08-13' AS Date), N'Andalax')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (1, 60, 94, CAST(N'2021-11-13' AS Date), CAST(N'2022-05-02' AS Date), N'Konklab')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (2, 79, 11, CAST(N'2021-11-12' AS Date), CAST(N'2021-11-27' AS Date), N'Sonair')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (3, 74, 1, CAST(N'2022-03-06' AS Date), CAST(N'2022-11-03' AS Date), N'Sub-Ex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (4, 44, 93, CAST(N'2022-03-25' AS Date), CAST(N'2022-02-04' AS Date), N'Keylex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (5, 99, 26, CAST(N'2022-04-05' AS Date), CAST(N'2022-04-06' AS Date), N'Bytecard')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (6, 92, 55, CAST(N'2022-04-30' AS Date), CAST(N'2022-05-29' AS Date), N'Solarbreeze')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (7, 60, 95, CAST(N'2022-02-09' AS Date), CAST(N'2022-09-16' AS Date), N'Tres-Zap')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (8, 24, 89, CAST(N'2022-03-09' AS Date), CAST(N'2022-09-19' AS Date), N'Alphazap')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (9, 51, 86, CAST(N'2022-05-16' AS Date), CAST(N'2022-05-27' AS Date), N'Keylex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (10, 100, 25, CAST(N'2022-08-31' AS Date), CAST(N'2022-03-31' AS Date), N'Otcom')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (11, 64, 2, CAST(N'2022-01-25' AS Date), CAST(N'2021-12-07' AS Date), N'Flowdesk')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (12, 78, 93, CAST(N'2022-05-13' AS Date), CAST(N'2022-02-27' AS Date), N'Tin')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (13, 12, 99, CAST(N'2022-04-09' AS Date), CAST(N'2021-12-19' AS Date), N'Mat Lam Tam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (14, 10, 96, CAST(N'2022-02-26' AS Date), CAST(N'2022-06-09' AS Date), N'Overhold')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (16, 2, 77, CAST(N'2021-12-03' AS Date), CAST(N'2022-11-01' AS Date), N'Aerified')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (17, 14, 44, CAST(N'2021-12-24' AS Date), CAST(N'2022-03-24' AS Date), N'Fintone')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (18, 36, 25, CAST(N'2022-05-05' AS Date), CAST(N'2022-04-29' AS Date), N'Flexidy')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (19, 76, 40, CAST(N'2022-03-15' AS Date), CAST(N'2022-08-01' AS Date), N'Latlux')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (20, 38, 16, CAST(N'2022-09-06' AS Date), CAST(N'2022-09-19' AS Date), N'Namfix')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (21, 54, 23, CAST(N'2022-04-22' AS Date), CAST(N'2021-11-07' AS Date), N'Fintone')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (22, 52, 49, CAST(N'2022-05-10' AS Date), CAST(N'2021-12-25' AS Date), N'Trippledex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (23, 87, 34, CAST(N'2021-11-19' AS Date), CAST(N'2022-10-10' AS Date), N'Keylex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (24, 62, 99, CAST(N'2022-04-06' AS Date), CAST(N'2022-06-04' AS Date), N'Sonair')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (25, 36, 98, CAST(N'2022-03-10' AS Date), CAST(N'2022-06-17' AS Date), N'Prodder')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (26, 10, 32, CAST(N'2022-02-21' AS Date), CAST(N'2021-11-05' AS Date), N'Prodder')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (27, 83, 93, CAST(N'2022-03-14' AS Date), CAST(N'2022-04-20' AS Date), N'Opela')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (28, 91, 6, CAST(N'2022-06-07' AS Date), CAST(N'2022-02-09' AS Date), N'Voltsillam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (29, 33, 69, CAST(N'2022-09-13' AS Date), CAST(N'2022-09-26' AS Date), N'Transcof')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (30, 87, 31, CAST(N'2021-11-04' AS Date), CAST(N'2022-02-20' AS Date), N'Stringtough')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (31, 48, 39, CAST(N'2022-01-02' AS Date), CAST(N'2022-04-25' AS Date), N'It')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (32, 4, 28, CAST(N'2022-08-07' AS Date), CAST(N'2022-01-26' AS Date), N'Ventosanzap')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (33, 81, 95, CAST(N'2021-12-02' AS Date), CAST(N'2022-01-13' AS Date), N'Asoka')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (34, 75, 64, CAST(N'2022-04-27' AS Date), CAST(N'2021-12-10' AS Date), N'Mat Lam Tam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (35, 5, 31, CAST(N'2022-02-20' AS Date), CAST(N'2022-03-15' AS Date), N'Stim')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (36, 69, 37, CAST(N'2021-11-23' AS Date), CAST(N'2022-09-20' AS Date), N'Sub-Ex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (37, 18, 38, CAST(N'2022-06-13' AS Date), CAST(N'2022-05-05' AS Date), N'Zaam-Dox')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (38, 8, 35, CAST(N'2022-08-14' AS Date), CAST(N'2022-03-17' AS Date), N'Viva')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (39, 22, 99, CAST(N'2022-07-12' AS Date), CAST(N'2022-03-27' AS Date), N'Matsoft')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (40, 2, 11, CAST(N'2022-05-07' AS Date), CAST(N'2021-11-16' AS Date), N'Home Ing')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (41, 47, 37, CAST(N'2022-03-10' AS Date), CAST(N'2022-03-14' AS Date), N'Quo Lux')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (42, 72, 38, CAST(N'2022-02-16' AS Date), CAST(N'2022-07-09' AS Date), N'Regrant')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (43, 59, 32, CAST(N'2022-10-03' AS Date), CAST(N'2022-07-03' AS Date), N'Toughjoyfax')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (44, 29, 25, CAST(N'2022-01-10' AS Date), CAST(N'2022-07-15' AS Date), N'Konklab')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (45, 97, 97, CAST(N'2022-03-06' AS Date), CAST(N'2022-08-06' AS Date), N'Opela')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (46, 9, 39, CAST(N'2021-12-05' AS Date), CAST(N'2022-03-24' AS Date), N'Temp')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (47, 93, 58, CAST(N'2022-01-10' AS Date), CAST(N'2022-10-29' AS Date), N'Bamity')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (48, 91, 68, CAST(N'2021-11-13' AS Date), CAST(N'2022-07-03' AS Date), N'Tempsoft')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (49, 54, 84, CAST(N'2022-01-29' AS Date), CAST(N'2022-06-21' AS Date), N'Redhold')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (51, 79, 26, CAST(N'2022-10-07' AS Date), CAST(N'2021-11-24' AS Date), N'Bitchip')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (52, 64, 8, CAST(N'2022-10-28' AS Date), CAST(N'2022-08-24' AS Date), N'Viva')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (53, 98, 1, CAST(N'2022-09-11' AS Date), CAST(N'2022-07-10' AS Date), N'Voyatouch')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (54, 99, 48, CAST(N'2022-06-14' AS Date), CAST(N'2022-01-24' AS Date), N'Aerified')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (55, 38, 31, CAST(N'2022-07-24' AS Date), CAST(N'2022-10-12' AS Date), N'Flowdesk')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (56, 71, 80, CAST(N'2021-11-17' AS Date), CAST(N'2022-08-08' AS Date), N'Fix San')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (57, 11, 18, CAST(N'2021-11-18' AS Date), CAST(N'2022-01-07' AS Date), N'Sonair')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (58, 57, 37, CAST(N'2022-01-16' AS Date), CAST(N'2021-12-22' AS Date), N'Alpha')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (59, 85, 82, CAST(N'2021-11-23' AS Date), CAST(N'2022-06-30' AS Date), N'Toughjoyfax')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (60, 49, 5, CAST(N'2021-11-25' AS Date), CAST(N'2022-05-17' AS Date), N'Stim')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (61, 44, 15, CAST(N'2022-09-14' AS Date), CAST(N'2022-01-26' AS Date), N'Tres-Zap')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (62, 2, 79, CAST(N'2022-03-28' AS Date), CAST(N'2022-02-19' AS Date), N'Treeflex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (63, 5, 22, CAST(N'2022-07-05' AS Date), CAST(N'2022-03-06' AS Date), N'Konklab')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (64, 35, 59, CAST(N'2022-10-08' AS Date), CAST(N'2022-07-18' AS Date), N'Stringtough')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (65, 23, 94, CAST(N'2022-05-31' AS Date), CAST(N'2021-12-24' AS Date), N'Quo Lux')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (66, 77, 16, CAST(N'2022-05-25' AS Date), CAST(N'2021-11-20' AS Date), N'Greenlam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (67, 54, 49, CAST(N'2022-07-23' AS Date), CAST(N'2022-08-06' AS Date), N'Sonsing')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (68, 41, 23, CAST(N'2021-12-30' AS Date), CAST(N'2022-07-12' AS Date), N'Otcom')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (69, 98, 5, CAST(N'2021-11-16' AS Date), CAST(N'2022-04-28' AS Date), N'Stringtough')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (70, 5, 29, CAST(N'2022-10-24' AS Date), CAST(N'2022-02-13' AS Date), N'Mat Lam Tam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (71, 72, 21, CAST(N'2022-10-09' AS Date), CAST(N'2022-09-11' AS Date), N'Rank')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (72, 16, 15, CAST(N'2022-05-22' AS Date), CAST(N'2022-10-21' AS Date), N'Greenlam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (73, 88, 78, CAST(N'2021-12-09' AS Date), CAST(N'2022-08-28' AS Date), N'Zoolab')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (74, 61, 99, CAST(N'2022-06-30' AS Date), CAST(N'2021-11-26' AS Date), N'Alphazap')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (75, 71, 26, CAST(N'2021-12-23' AS Date), CAST(N'2022-04-19' AS Date), N'Zontrax')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (76, 83, 78, CAST(N'2022-07-30' AS Date), CAST(N'2022-11-02' AS Date), N'Cardguard')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (77, 57, 34, CAST(N'2022-08-11' AS Date), CAST(N'2022-01-23' AS Date), N'Temp')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (78, 45, 87, CAST(N'2021-11-11' AS Date), CAST(N'2022-07-05' AS Date), N'Kanlam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (79, 96, 52, CAST(N'2022-05-10' AS Date), CAST(N'2022-04-13' AS Date), N'Home Ing')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (80, 88, 90, CAST(N'2022-04-07' AS Date), CAST(N'2022-04-11' AS Date), N'Tampflex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (81, 45, 21, CAST(N'2022-07-26' AS Date), CAST(N'2021-12-12' AS Date), N'Zathin')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (82, 100, 63, CAST(N'2022-10-25' AS Date), CAST(N'2022-02-05' AS Date), N'Bytecard')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (83, 21, 17, CAST(N'2022-05-08' AS Date), CAST(N'2022-07-21' AS Date), N'Cookley')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (84, 86, 35, CAST(N'2022-10-08' AS Date), CAST(N'2022-05-28' AS Date), N'Namfix')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (85, 29, 84, CAST(N'2022-10-08' AS Date), CAST(N'2022-04-08' AS Date), N'Bitchip')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (86, 42, 9, CAST(N'2021-11-12' AS Date), CAST(N'2022-11-02' AS Date), N'Bitchip')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (87, 23, 21, CAST(N'2022-04-24' AS Date), CAST(N'2022-08-09' AS Date), N'Kanlam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (88, 38, 38, CAST(N'2022-01-14' AS Date), CAST(N'2022-04-10' AS Date), N'Matsoft')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (89, 65, 42, CAST(N'2022-01-08' AS Date), CAST(N'2022-05-05' AS Date), N'Alphazap')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (90, 79, 39, CAST(N'2022-10-14' AS Date), CAST(N'2022-07-29' AS Date), N'It')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (91, 78, 41, CAST(N'2021-12-31' AS Date), CAST(N'2022-07-24' AS Date), N'Otcom')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (92, 36, 55, CAST(N'2022-01-21' AS Date), CAST(N'2021-12-13' AS Date), N'Voyatouch')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (93, 55, 75, CAST(N'2022-02-17' AS Date), CAST(N'2022-05-18' AS Date), N'Stim')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (94, 19, 47, CAST(N'2022-10-17' AS Date), CAST(N'2022-09-02' AS Date), N'Y-Solowarm')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (95, 10, 68, CAST(N'2022-02-27' AS Date), CAST(N'2022-02-26' AS Date), N'It')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (96, 59, 15, CAST(N'2022-02-10' AS Date), CAST(N'2022-01-06' AS Date), N'Biodex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (97, 28, 93, CAST(N'2021-12-14' AS Date), CAST(N'2022-06-30' AS Date), N'Domainer')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (98, 54, 74, CAST(N'2021-11-09' AS Date), CAST(N'2022-10-22' AS Date), N'Redhold')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (99, 68, 23, CAST(N'2022-06-03' AS Date), CAST(N'2022-03-17' AS Date), N'Gembucket')
SET IDENTITY_INSERT [Edward].[Bill] OFF
GO
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (2, 24, 49, 7587.13)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (3, 43, 27, 5099.97)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (4, 89, 24, 8045.01)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (8, 1, 18, 3073.07)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (8, 45, 17, 2746.67)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (9, 5, 74, 9564.65)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (10, 43, 61, 3798.72)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (11, 46, 67, 2644.35)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (11, 52, 78, 8284.81)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (12, 75, 80, 8611.94)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (14, 27, 9, 8356.05)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (16, 50, 22, 9016.32)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (19, 37, 45, 3840.89)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (20, 12, 94, 1405.29)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (20, 44, 43, 7183.2)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (22, 26, 73, 7605.2)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (22, 71, 95, 2162.48)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (25, 71, 30, 5441.4)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (26, 44, 81, 5000.25)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (26, 47, 2, 2282.25)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (26, 50, 5, 2911.25)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (27, 70, 60, 500.48)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (28, 75, 86, 4207.97)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (29, 32, 25, 1956.57)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (29, 99, 98, 711.93)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (30, 1, 71, 1781.27)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (30, 59, 39, 7231.27)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (32, 79, 90, 7434.81)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (32, 98, 28, 549.63)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (33, 5, 57, 7486.29)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (33, 100, 44, 4668.35)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (34, 33, 61, 9892.66)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (34, 98, 92, 6820.57)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (38, 27, 47, 6710.25)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (38, 35, 65, 7218.59)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (38, 50, 62, 5769.28)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (39, 3, 66, 6934.56)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (41, 37, 33, 3676.66)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (42, 37, 28, 9268.93)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (42, 50, 41, 2430.31)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (43, 93, 47, 8366.08)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (44, 42, 82, 3331.88)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (44, 49, 86, 5283.59)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (44, 96, 23, 2464.19)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (45, 77, 54, 1207.13)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (46, 49, 35, 6871.68)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (47, 99, 13, 293.59)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (48, 34, 36, 5412.4)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (51, 17, 77, 8330.43)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (51, 91, 95, 4928.05)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (51, 99, 93, 8496.98)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (53, 43, 27, 8676.28)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (54, 2, 58, 4675.56)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (56, 8, 95, 8962.58)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (58, 44, 61, 1214.7)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (58, 69, 54, 5140.55)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (59, 9, 34, 4410.04)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (60, 2, 42, 1900.91)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (62, 19, 28, 5109.24)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (62, 93, 58, 7711.29)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (63, 54, 91, 666.02)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (63, 62, 54, 8782.62)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (64, 62, 83, 1269.06)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (65, 18, 82, 1202.87)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (66, 9, 34, 5179.82)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (66, 55, 39, 9241.64)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (67, 32, 46, 8574.31)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (72, 46, 30, 7772.52)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (73, 17, 41, 4509.55)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (73, 45, 49, 8107.27)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (74, 44, 80, 3400.62)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (74, 79, 46, 1531.75)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (74, 89, 40, 9104.17)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (75, 93, 54, 1429.89)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (77, 16, 61, 6769.98)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (77, 31, 5, 636.9)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (77, 50, 46, 9674.2)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (79, 49, 49, 1929.12)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (80, 14, 96, 6283.56)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (80, 91, 31, 6925.6)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (84, 6, 63, 1997.97)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (84, 27, 32, 6376.9)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (85, 27, 62, 85.99)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (85, 45, 8, 1760.82)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (89, 2, 54, 3395.4)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (89, 75, 32, 6705.84)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (90, 77, 97, 6533.33)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (92, 17, 49, 846.76)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (92, 77, 32, 1173.29)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (92, 98, 46, 2951.47)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (93, 72, 55, 4005.32)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (95, 71, 70, 5765.98)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (96, 31, 100, 6530.73)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (97, 48, 49, 2021.34)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (97, 49, 48, 5038.84)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (97, 71, 39, 5301.98)
INSERT [Edward].[BillDetail] ([IDBill], [IDClothes], [Quantity], [price]) VALUES (98, 78, 46, 9714.68)
GO
SET IDENTITY_INSERT [Edward].[Category] ON 

INSERT [Edward].[Category] ([id], [Name]) VALUES (1, N'Structural & Misc Steel Erection')
INSERT [Edward].[Category] ([id], [Name]) VALUES (2, N'Landscaping & Irrigation')
INSERT [Edward].[Category] ([id], [Name]) VALUES (3, N'Drilled Shafts')
INSERT [Edward].[Category] ([id], [Name]) VALUES (4, N'Structural and Misc Steel (Fabrication)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (5, N'Drywall & Acoustical (FED)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (6, N'Temp Fencing, Decorative Fencing and Gates')
INSERT [Edward].[Category] ([id], [Name]) VALUES (7, N'Landscaping & Irrigation')
INSERT [Edward].[Category] ([id], [Name]) VALUES (8, N'Doors, Frames & Hardware')
INSERT [Edward].[Category] ([id], [Name]) VALUES (9, N'Retaining Wall and Brick Pavers')
INSERT [Edward].[Category] ([id], [Name]) VALUES (10, N'Waterproofing & Caulking')
INSERT [Edward].[Category] ([id], [Name]) VALUES (11, N'Waterproofing & Caulking')
INSERT [Edward].[Category] ([id], [Name]) VALUES (12, N'Prefabricated Aluminum Metal Canopies')
INSERT [Edward].[Category] ([id], [Name]) VALUES (13, N'EIFS')
INSERT [Edward].[Category] ([id], [Name]) VALUES (14, N'Structural and Misc Steel (Fabrication)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (15, N'Temp Fencing, Decorative Fencing and Gates')
INSERT [Edward].[Category] ([id], [Name]) VALUES (16, N'Plumbing & Medical Gas')
INSERT [Edward].[Category] ([id], [Name]) VALUES (17, N'Glass & Glazing')
INSERT [Edward].[Category] ([id], [Name]) VALUES (18, N'Landscaping & Irrigation')
INSERT [Edward].[Category] ([id], [Name]) VALUES (19, N'Electrical and Fire Alarm')
INSERT [Edward].[Category] ([id], [Name]) VALUES (20, N'Framing (Steel)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (21, N'Painting & Vinyl Wall Covering')
INSERT [Edward].[Category] ([id], [Name]) VALUES (22, N'Doors, Frames & Hardware')
INSERT [Edward].[Category] ([id], [Name]) VALUES (23, N'Elevator')
INSERT [Edward].[Category] ([id], [Name]) VALUES (24, N'Termite Control')
INSERT [Edward].[Category] ([id], [Name]) VALUES (25, N'EIFS')
INSERT [Edward].[Category] ([id], [Name]) VALUES (26, N'Waterproofing & Caulking')
INSERT [Edward].[Category] ([id], [Name]) VALUES (27, N'RF Shielding')
INSERT [Edward].[Category] ([id], [Name]) VALUES (28, N'RF Shielding')
INSERT [Edward].[Category] ([id], [Name]) VALUES (29, N'Landscaping & Irrigation')
INSERT [Edward].[Category] ([id], [Name]) VALUES (30, N'Drilled Shafts')
INSERT [Edward].[Category] ([id], [Name]) VALUES (31, N'Roofing (Metal)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (32, N'Elevator')
INSERT [Edward].[Category] ([id], [Name]) VALUES (33, N'Rebar & Wire Mesh Install')
INSERT [Edward].[Category] ([id], [Name]) VALUES (34, N'Structural & Misc Steel Erection')
INSERT [Edward].[Category] ([id], [Name]) VALUES (35, N'Termite Control')
INSERT [Edward].[Category] ([id], [Name]) VALUES (36, N'Hard Tile & Stone')
INSERT [Edward].[Category] ([id], [Name]) VALUES (37, N'Plumbing & Medical Gas')
INSERT [Edward].[Category] ([id], [Name]) VALUES (38, N'HVAC')
INSERT [Edward].[Category] ([id], [Name]) VALUES (39, N'Termite Control')
INSERT [Edward].[Category] ([id], [Name]) VALUES (40, N'Masonry & Precast')
INSERT [Edward].[Category] ([id], [Name]) VALUES (41, N'Fire Protection')
INSERT [Edward].[Category] ([id], [Name]) VALUES (42, N'Prefabricated Aluminum Metal Canopies')
INSERT [Edward].[Category] ([id], [Name]) VALUES (43, N'Retaining Wall and Brick Pavers')
INSERT [Edward].[Category] ([id], [Name]) VALUES (44, N'Electrical and Fire Alarm')
INSERT [Edward].[Category] ([id], [Name]) VALUES (45, N'Masonry & Precast')
INSERT [Edward].[Category] ([id], [Name]) VALUES (46, N'Casework')
INSERT [Edward].[Category] ([id], [Name]) VALUES (47, N'Exterior Signage')
INSERT [Edward].[Category] ([id], [Name]) VALUES (48, N'Framing (Wood)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (49, N'Framing (Steel)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (50, N'Drywall & Acoustical (FED)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (51, N'Plumbing & Medical Gas')
INSERT [Edward].[Category] ([id], [Name]) VALUES (52, N'RF Shielding')
INSERT [Edward].[Category] ([id], [Name]) VALUES (53, N'Site Furnishings')
INSERT [Edward].[Category] ([id], [Name]) VALUES (54, N'Drilled Shafts')
INSERT [Edward].[Category] ([id], [Name]) VALUES (55, N'Fire Sprinkler System')
INSERT [Edward].[Category] ([id], [Name]) VALUES (56, N'Epoxy Flooring')
INSERT [Edward].[Category] ([id], [Name]) VALUES (57, N'Doors, Frames & Hardware')
INSERT [Edward].[Category] ([id], [Name]) VALUES (58, N'Roofing (Metal)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (59, N'Hard Tile & Stone')
INSERT [Edward].[Category] ([id], [Name]) VALUES (60, N'Soft Flooring and Base')
INSERT [Edward].[Category] ([id], [Name]) VALUES (61, N'Electrical')
INSERT [Edward].[Category] ([id], [Name]) VALUES (62, N'Exterior Signage')
INSERT [Edward].[Category] ([id], [Name]) VALUES (63, N'Fire Sprinkler System')
INSERT [Edward].[Category] ([id], [Name]) VALUES (64, N'EIFS')
INSERT [Edward].[Category] ([id], [Name]) VALUES (65, N'Drywall & Acoustical (FED)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (66, N'Temp Fencing, Decorative Fencing and Gates')
INSERT [Edward].[Category] ([id], [Name]) VALUES (67, N'Fire Sprinkler System')
INSERT [Edward].[Category] ([id], [Name]) VALUES (68, N'Fire Sprinkler System')
INSERT [Edward].[Category] ([id], [Name]) VALUES (69, N'Framing (Steel)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (70, N'Elevator')
INSERT [Edward].[Category] ([id], [Name]) VALUES (71, N'Structural & Misc Steel Erection')
INSERT [Edward].[Category] ([id], [Name]) VALUES (72, N'Structural & Misc Steel Erection')
INSERT [Edward].[Category] ([id], [Name]) VALUES (73, N'Prefabricated Aluminum Metal Canopies')
INSERT [Edward].[Category] ([id], [Name]) VALUES (74, N'Prefabricated Aluminum Metal Canopies')
INSERT [Edward].[Category] ([id], [Name]) VALUES (75, N'Curb & Gutter')
INSERT [Edward].[Category] ([id], [Name]) VALUES (76, N'Waterproofing & Caulking')
INSERT [Edward].[Category] ([id], [Name]) VALUES (77, N'Roofing (Asphalt)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (78, N'Drywall & Acoustical (MOB)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (79, N'Overhead Doors')
INSERT [Edward].[Category] ([id], [Name]) VALUES (80, N'Epoxy Flooring')
INSERT [Edward].[Category] ([id], [Name]) VALUES (81, N'Temp Fencing, Decorative Fencing and Gates')
INSERT [Edward].[Category] ([id], [Name]) VALUES (82, N'Temp Fencing, Decorative Fencing and Gates')
INSERT [Edward].[Category] ([id], [Name]) VALUES (83, N'Structural & Misc Steel Erection')
INSERT [Edward].[Category] ([id], [Name]) VALUES (84, N'Granite Surfaces')
INSERT [Edward].[Category] ([id], [Name]) VALUES (85, N'Wall Protection')
INSERT [Edward].[Category] ([id], [Name]) VALUES (86, N'Curb & Gutter')
INSERT [Edward].[Category] ([id], [Name]) VALUES (87, N'Casework')
INSERT [Edward].[Category] ([id], [Name]) VALUES (88, N'Framing (Steel)')
INSERT [Edward].[Category] ([id], [Name]) VALUES (89, N'RF Shielding')
INSERT [Edward].[Category] ([id], [Name]) VALUES (90, N'Curb & Gutter')
INSERT [Edward].[Category] ([id], [Name]) VALUES (91, N'Painting & Vinyl Wall Covering')
INSERT [Edward].[Category] ([id], [Name]) VALUES (92, N'Overhead Doors')
INSERT [Edward].[Category] ([id], [Name]) VALUES (93, N'Doors, Frames & Hardware')
INSERT [Edward].[Category] ([id], [Name]) VALUES (94, N'Epoxy Flooring')
INSERT [Edward].[Category] ([id], [Name]) VALUES (95, N'Masonry & Precast')
INSERT [Edward].[Category] ([id], [Name]) VALUES (96, N'Soft Flooring and Base')
INSERT [Edward].[Category] ([id], [Name]) VALUES (97, N'Masonry & Precast')
INSERT [Edward].[Category] ([id], [Name]) VALUES (98, N'Doors, Frames & Hardware')
INSERT [Edward].[Category] ([id], [Name]) VALUES (99, N'Granite Surfaces')
GO
INSERT [Edward].[Category] ([id], [Name]) VALUES (100, N'Retaining Wall and Brick Pavers')
SET IDENTITY_INSERT [Edward].[Category] OFF
GO
SET IDENTITY_INSERT [Edward].[Clothes] ON 

INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (1, 48, 3, N'Orange')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (2, 77, 8, N'Crimson')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (3, 73, 2, N'Puce')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (4, 81, 9, N'Pink')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (5, 36, 1, N'Crimson')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (6, 57, 2, N'Green')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (7, 52, 7, N'Green')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (8, 70, 4, N'Teal')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (9, 94, 9, N'Teal')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (10, 23, 10, N'Yellow')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (11, 86, 4, N'Indigo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (12, 14, 7, N'Fuscia')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (13, 35, 6, N'Red')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (14, 92, 8, N'Teal')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (15, 39, 9, N'Indigo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (16, 52, 6, N'Yellow')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (17, 95, 7, N'Goldenrod')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (18, 11, 8, N'Green')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (19, 67, 2, N'Fuscia')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (20, 64, 4, N'Goldenrod')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (21, 87, 6, N'Pink')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (22, 11, 8, N'Violet')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (23, 52, 7, N'Violet')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (24, 16, 2, N'Aquamarine')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (25, 85, 9, N'Teal')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (26, 38, 2, N'Fuscia')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (27, 7, 6, N'Turquoise')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (28, 2, 7, N'Maroon')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (29, 73, 5, N'Teal')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (30, 3, 9, N'Pink')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (31, 71, 9, N'Fuscia')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (32, 96, 8, N'Purple')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (33, 62, 10, N'Orange')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (34, 91, 6, N'Khaki')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (35, 45, 2, N'Red')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (36, 52, 2, N'Mauv')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (37, 100, 8, N'Blue')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (38, 99, 6, N'Green')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (39, 55, 5, N'Violet')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (40, 60, 4, N'Blue')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (41, 11, 7, N'Turquoise')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (42, 83, 1, N'Indigo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (43, 26, 3, N'Mauv')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (44, 13, 2, N'Yellow')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (45, 36, 5, N'Khaki')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (46, 28, 2, N'Orange')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (47, 65, 6, N'Red')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (48, 31, 10, N'Purple')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (49, 34, 7, N'Violet')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (50, 84, 5, N'Maroon')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (51, 79, 10, N'Khaki')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (52, 88, 7, N'Red')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (53, 54, 9, N'Turquoise')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (54, 51, 3, N'Red')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (55, 35, 4, N'Pink')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (56, 60, 3, N'Indigo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (57, 38, 4, N'Red')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (58, 13, 2, N'Blue')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (59, 25, 1, N'Aquamarine')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (60, 30, 5, N'Blue')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (61, 27, 6, N'Maroon')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (62, 54, 4, N'Blue')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (63, 51, 3, N'Yellow')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (64, 15, 1, N'Indigo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (65, 94, 9, N'Indigo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (66, 35, 6, N'Mauv')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (67, 62, 3, N'Maroon')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (68, 8, 10, N'Violet')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (69, 81, 4, N'Orange')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (70, 5, 7, N'Puce')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (71, 28, 1, N'Green')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (72, 100, 2, N'Turquoise')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (73, 37, 10, N'Yellow')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (74, 15, 7, N'Fuscia')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (75, 24, 4, N'Red')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (76, 68, 6, N'Purple')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (77, 82, 5, N'Khaki')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (78, 95, 1, N'Puce')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (79, 21, 9, N'Maroon')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (80, 67, 10, N'Blue')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (81, 64, 3, N'Indigo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (82, 54, 7, N'Violet')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (83, 77, 2, N'Violet')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (84, 25, 8, N'Goldenrod')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (85, 62, 4, N'Pink')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (86, 5, 3, N'Yellow')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (87, 51, 10, N'Red')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (88, 44, 4, N'Goldenrod')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (89, 73, 1, N'Red')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (90, 70, 5, N'Aquamarine')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (91, 72, 7, N'Turquoise')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (92, 74, 4, N'Violet')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (93, 6, 2, N'Violet')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (94, 43, 9, N'Maroon')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (95, 40, 5, N'Teal')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (96, 4, 4, N'Khaki')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (97, 36, 2, N'Aquamarine')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (98, 80, 9, N'Purple')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (99, 62, 1, N'Red')
GO
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des]) VALUES (100, 25, 7, N'Yellow')
SET IDENTITY_INSERT [Edward].[Clothes] OFF
GO
SET IDENTITY_INSERT [Edward].[Clothes_Properties] ON 

INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (1, 53, N'S', 13, 33.86)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (2, 69, N'M', 54, 69.74)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (3, 19, N'M', 22, 931.34)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (4, 6, N'XL', 19, 476.92)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (5, 65, N'M', 21, 866.86)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (6, 6, N'2XL', 51, 20.75)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (7, 18, N'L', 2, 209.03)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (8, 50, N'S', 84, 757.49)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (9, 59, N'XS', 33, 189.3)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (10, 42, N'L', 77, 76.81)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (11, 94, N'XL', 41, 496.08)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (12, 33, N'S', 13, 756.11)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (13, 72, N'S', 34, 608.21)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (14, 98, N'XL', 98, 519.84)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (15, 84, N'M', 67, 463.43)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (16, 77, N'2XL', 30, 908.82)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (17, 96, N'2XL', 61, 948.49)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (18, 89, N'2XL', 58, 389.59)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (19, 50, N'M', 74, 209.11)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (20, 51, N'S', 69, 360.11)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (21, 47, N'3XL', 16, 238.43)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (22, 37, N'L', 61, 584.33)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (23, 99, N'S', 23, 876.85)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (24, 1, N'2XL', 91, 391.44)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (25, 19, N'XL', 72, 432.48)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (26, 44, N'2XL', 76, 893.11)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (27, 89, N'M', 20, 965.2)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (28, 62, N'2XL', 4, 625)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (29, 27, N'XS', 72, 152.53)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (30, 35, N'M', 73, 465.74)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (31, 16, N'3XL', 94, 903.34)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (32, 81, N'XL', 86, 668.09)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (33, 79, N'L', 55, 412.16)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (34, 71, N'L', 43, 904.82)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (35, 3, N'XL', 100, 810.97)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (36, 98, N'M', 10, 996.51)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (37, 14, N'XS', 6, 205.57)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (38, 50, N'L', 22, 224.22)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (39, 16, N'L', 60, 104.48)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (40, 13, N'2XL', 98, 797.33)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (41, 95, N'3XL', 67, 41.18)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (42, 37, N'2XL', 8, 998.84)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (43, 75, N'XS', 20, 880.78)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (44, 9, N'2XL', 73, 134.64)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (45, 33, N'XS', 53, 485.04)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (46, 14, N'S', 40, 83.94)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (47, 48, N'L', 47, 676.63)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (48, 42, N'L', 45, 802.78)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (49, 37, N'M', 72, 133.27)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (50, 77, N'XL', 28, 693.54)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (51, 32, N'M', 52, 227.13)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (52, 34, N'XS', 97, 32.99)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (53, 44, N'XL', 74, 337.71)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (54, 79, N'L', 90, 405.3)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (55, 5, N'XS', 72, 553.71)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (56, 3, N'L', 10, 711.64)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (57, 57, N'2XL', 30, 850.18)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (58, 28, N'XS', 56, 764.57)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (59, 26, N'L', 88, 646)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (60, 100, N'M', 6, 814.04)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (61, 43, N'L', 81, 146.86)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (62, 89, N'XL', 63, 364.84)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (63, 17, N'XL', 97, 62.5)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (64, 70, N'S', 82, 148.52)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (65, 96, N'S', 95, 551.95)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (66, 55, N'L', 29, 771.43)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (67, 9, N'S', 88, 39.71)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (68, 27, N'S', 83, 916.82)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (69, 78, N'XL', 81, 747.57)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (70, 24, N'3XL', 99, 178.32)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (71, 49, N'XL', 68, 572.61)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (72, 72, N'XL', 83, 931.49)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (73, 58, N'3XL', 32, 48.51)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (74, 48, N'L', 60, 484.76)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (75, 31, N'3XL', 60, 93.41)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (76, 2, N'L', 48, 684.76)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (77, 5, N'XL', 40, 957.04)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (78, 45, N'M', 75, 185.76)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (79, 43, N'XS', 68, 457.62)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (80, 71, N'XS', 84, 900.84)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (81, 44, N'L', 58, 68.72)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (82, 99, N'S', 100, 516.59)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (83, 77, N'S', 70, 841.87)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (84, 12, N'S', 9, 615.98)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (85, 46, N'L', 85, 155.67)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (86, 94, N'2XL', 47, 263.31)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (87, 54, N'XL', 29, 644.7)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (88, 62, N'XL', 79, 939.35)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (89, 49, N'3XL', 81, 57.3)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (90, 37, N'S', 57, 671.2)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (91, 93, N'XL', 94, 667.88)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (92, 32, N'XS', 62, 609.52)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (93, 8, N'3XL', 37, 579.22)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (94, 2, N'L', 21, 957.43)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (95, 52, N'2XL', 62, 485.34)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (96, 60, N'2XL', 67, 844.1)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (97, 85, N'M', 47, 109.32)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (98, 82, N'3XL', 57, 136)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (99, 75, N'XL', 21, 337.58)
GO
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (100, 91, N'M', 73, 143.3)
SET IDENTITY_INSERT [Edward].[Clothes_Properties] OFF
GO
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (3, 36)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (4, 35)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (5, 41)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (6, 3)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (6, 33)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (6, 35)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (7, 24)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (8, 71)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (9, 15)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (11, 3)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (11, 66)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (11, 73)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (14, 64)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (15, 75)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (15, 90)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (16, 19)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (16, 31)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (18, 22)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (20, 70)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (20, 93)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (22, 48)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (26, 50)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (26, 82)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (26, 87)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (26, 96)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (27, 45)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (29, 94)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (30, 37)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (30, 40)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (31, 34)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (31, 79)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (32, 17)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (33, 73)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (38, 15)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (38, 73)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (40, 32)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (40, 36)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (40, 74)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (41, 73)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (41, 90)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (42, 31)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (43, 50)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (47, 64)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (48, 1)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (48, 79)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (48, 93)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (49, 54)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (50, 76)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (51, 85)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (52, 20)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (53, 76)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (54, 9)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (54, 29)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (54, 37)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (55, 12)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (57, 54)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (58, 39)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (58, 55)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (61, 54)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (61, 55)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (61, 68)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (64, 50)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (65, 21)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (67, 9)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (68, 49)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (69, 12)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (69, 79)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (69, 95)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (70, 23)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (70, 71)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (71, 42)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (72, 37)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (72, 79)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (75, 24)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (76, 84)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (77, 13)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (77, 45)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (80, 88)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (81, 77)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (87, 83)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (89, 56)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (90, 55)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (90, 71)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (90, 95)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (91, 11)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (92, 67)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (92, 69)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (93, 78)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (93, 82)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (94, 91)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (95, 48)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (96, 43)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (96, 88)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (97, 7)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (97, 53)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (98, 9)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (98, 48)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (99, 61)
INSERT [Edward].[Favorites] ([IDpeople], [IDclothes]) VALUES (100, 8)
GO
SET IDENTITY_INSERT [Edward].[People] ON 

INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (1, N'Lin Lardiner', N'llardiner0@friendfeed.com', N'6XbKIQP', N'+86 (351) 313-3780', 1, N'http://dummyimage.com/154x100.png/5fa2dd/ffffff', N'79 Montana Trail')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (2, N'Jesselyn Schouthede', N'jschouthede1@moonfruit.com', N'UvBQR3P3e75', N'+86 (446) 996-1785', 2, N'http://dummyimage.com/112x100.png/cc0000/ffffff', N'29 Dixon Drive')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (3, N'Paulo Butters', N'pbutters2@adobe.com', N'az9VtYKemaX', N'+1 (501) 885-8197', 3, N'http://dummyimage.com/120x100.png/ff4444/ffffff', N'52012 Carpenter Park')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (4, N'Aindrea Kitney', N'akitney3@slideshare.net', N'0Gr5GWy', N'+82 (705) 649-4456', 2, N'http://dummyimage.com/131x100.png/5fa2dd/ffffff', N'08937 Sage Park')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (5, N'Tarra Aldcorn', N'taldcorn4@ning.com', N'WmlafHa7HR', N'+86 (879) 593-9941', 3, N'http://dummyimage.com/250x100.png/5fa2dd/ffffff', N'78365 Hansons Point')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (6, N'Munroe Sharma', N'msharma5@tumblr.com', N'1PeGdpoRC', N'+66 (570) 385-1174', 3, N'http://dummyimage.com/248x100.png/dddddd/000000', N'101 Linden Road')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (7, N'Robby Cars', N'rcars6@trellian.com', N'teXns69B', N'+351 (276) 240-2342', 2, N'http://dummyimage.com/188x100.png/dddddd/000000', N'9201 Commercial Street')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (8, N'Rad Passby', N'rpassby7@europa.eu', N'aaZqy2Rfo0B7', N'+86 (302) 186-5825', 3, N'http://dummyimage.com/120x100.png/ff4444/ffffff', N'0 Dawn Pass')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (9, N'Nikolia Blizard', N'nblizard8@washington.edu', N'JaQQxXWaNK', N'+963 (681) 694-8333', 2, N'http://dummyimage.com/227x100.png/ff4444/ffffff', N'0 Birchwood Pass')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (10, N'Andonis Van der Mark', N'avan9@dmoz.org', N'dvkR43Xh4', N'+62 (985) 755-7345', 1, N'http://dummyimage.com/130x100.png/ff4444/ffffff', N'13 Gale Trail')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (11, N'Marketa Thomann', N'mthomanna@miibeian.gov.cn', N'cD1YgEoG9nE', N'+48 (938) 555-5537', 1, N'http://dummyimage.com/237x100.png/5fa2dd/ffffff', N'9300 Tennessee Crossing')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (12, N'Klara Espinha', N'kespinhab@uol.com.br', N'Gd3bNia9DMc0', N'+66 (387) 657-0351', 3, N'http://dummyimage.com/165x100.png/5fa2dd/ffffff', N'4388 Vidon Pass')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (13, N'Floria Sharpe', N'fsharpec@ycombinator.com', N'kEq7GfyqQh', N'+218 (502) 340-6144', 3, N'http://dummyimage.com/196x100.png/5fa2dd/ffffff', N'04222 Granby Avenue')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (14, N'Ely Slaten', N'eslatend@drupal.org', N'ZTwMq4', N'+62 (370) 842-7984', 3, N'http://dummyimage.com/215x100.png/5fa2dd/ffffff', N'95206 Hooker Parkway')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (15, N'Charity Macey', N'cmaceye@youtube.com', N'ApOgpj9pOez', N'+1 (204) 639-8454', 2, N'http://dummyimage.com/137x100.png/dddddd/000000', N'24409 Lukken Parkway')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (16, N'Letizia Manolov', N'lmanolovf@bbc.co.uk', N'YdM2yR', N'+55 (390) 519-7797', 1, N'http://dummyimage.com/108x100.png/ff4444/ffffff', N'23951 Kedzie Park')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (17, N'Hadria Morritt', N'hmorrittg@nytimes.com', N'QlamfPS', N'+242 (330) 509-2564', 2, N'http://dummyimage.com/137x100.png/ff4444/ffffff', N'577 Old Shore Way')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (18, N'Giacomo Heineking', N'gheinekingh@ycombinator.com', N'lXeEjjXKUIX', N'+51 (618) 977-3332', 2, N'http://dummyimage.com/174x100.png/cc0000/ffffff', N'9913 Hagan Court')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (19, N'Tomasine Hanaford', N'thanafordi@free.fr', N'QOmRH6', N'+86 (287) 970-7671', 3, N'http://dummyimage.com/156x100.png/5fa2dd/ffffff', N'3563 Hoard Avenue')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (20, N'Davita Payler', N'dpaylerj@amazon.com', N'ge9lCrxc6', N'+54 (316) 475-9913', 3, N'http://dummyimage.com/178x100.png/ff4444/ffffff', N'92078 Messerschmidt Circle')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (21, N'Tanner Yurkevich', N'tyurkevichk@apache.org', N'HFzEVY', N'+359 (322) 393-3981', 3, N'http://dummyimage.com/135x100.png/ff4444/ffffff', N'19 Katie Place')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (22, N'Connie Leggat', N'cleggatl@drupal.org', N'jqVBxm', N'+86 (113) 320-7361', 3, N'http://dummyimage.com/130x100.png/dddddd/000000', N'078 Helena Street')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (23, N'Tanner Myerscough', N'tmyerscoughm@gnu.org', N'MGNGXiDq', N'+62 (104) 962-8582', 3, N'http://dummyimage.com/115x100.png/ff4444/ffffff', N'3 Atwood Hill')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (24, N'Merry Kloska', N'mkloskan@nhs.uk', N'ra7uaq', N'+1 (801) 199-8848', 1, N'http://dummyimage.com/198x100.png/ff4444/ffffff', N'3 Hoepker Park')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (25, N'Bran Pretswell', N'bpretswello@mapquest.com', N'QlAif6hdzPjB', N'+46 (164) 658-9620', 2, N'http://dummyimage.com/154x100.png/ff4444/ffffff', N'51 Graceland Parkway')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (26, N'Alie Skoughman', N'askoughmanp@mozilla.com', N'69HLUS', N'+92 (180) 544-5612', 2, N'http://dummyimage.com/163x100.png/5fa2dd/ffffff', N'3 Green Ridge Trail')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (27, N'Lara Vedmore', N'lvedmoreq@friendfeed.com', N'swbhAo', N'+371 (358) 644-2462', 1, N'http://dummyimage.com/220x100.png/ff4444/ffffff', N'7 Barby Road')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (28, N'Beau Screech', N'bscreechr@phpbb.com', N'5iNBwLoXkQwE', N'+212 (120) 254-0363', 1, N'http://dummyimage.com/137x100.png/ff4444/ffffff', N'18 Vera Hill')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (29, N'Nicolea Hanks', N'nhankss@etsy.com', N'oVb6Pf', N'+62 (914) 975-9481', 2, N'http://dummyimage.com/191x100.png/ff4444/ffffff', N'5 Mifflin Point')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (30, N'Ettie Adamkiewicz', N'eadamkiewiczt@washington.edu', N'3VOjZVX8ZC6', N'+86 (414) 955-6038', 2, N'http://dummyimage.com/140x100.png/5fa2dd/ffffff', N'21 Ridgeview Street')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (31, N'Daisie Creeghan', N'dcreeghanu@devhub.com', N'pxyUFcpWZp', N'+31 (125) 257-0021', 3, N'http://dummyimage.com/211x100.png/dddddd/000000', N'671 Express Drive')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (32, N'Alanah Pinke', N'apinkev@ehow.com', N'O3hTgJnPP', N'+48 (888) 313-9963', 1, N'http://dummyimage.com/107x100.png/cc0000/ffffff', N'32535 Hazelcrest Crossing')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (33, N'Dennie Poone', N'dpoonew@123-reg.co.uk', N'1RQIvhxfnT', N'+86 (825) 650-4325', 1, N'http://dummyimage.com/244x100.png/cc0000/ffffff', N'9991 Hollow Ridge Way')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (34, N'Jeanie Gravells', N'jgravellsx@wikimedia.org', N'qSdhkgt3EF7O', N'+355 (493) 424-7934', 3, N'http://dummyimage.com/210x100.png/ff4444/ffffff', N'31 Eagan Alley')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (35, N'Ellyn Gentiry', N'egentiryy@linkedin.com', N'9ssFeZWIp251', N'+55 (303) 313-3627', 2, N'http://dummyimage.com/111x100.png/5fa2dd/ffffff', N'5 Dorton Park')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (36, N'Rakel Clarson', N'rclarsonz@yellowbook.com', N'jC5U2i0P8q', N'+62 (555) 245-3349', 3, N'http://dummyimage.com/200x100.png/dddddd/000000', N'729 Fisk Trail')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (37, N'Kennett Akhurst', N'kakhurst10@phpbb.com', N'X9LiRfZr', N'+964 (989) 311-4900', 1, N'http://dummyimage.com/169x100.png/cc0000/ffffff', N'6679 Mifflin Terrace')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (38, N'Domini Hylton', N'dhylton11@miitbeian.gov.cn', N'eo0S9Gg5bHhh', N'+62 (464) 366-8241', 1, N'http://dummyimage.com/168x100.png/dddddd/000000', N'35 Westport Way')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (39, N'Junie Roelvink', N'jroelvink12@istockphoto.com', N'kXW1D2', N'+66 (436) 817-1347', 2, N'http://dummyimage.com/237x100.png/dddddd/000000', N'8 Montana Street')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (40, N'Nan Raiker', N'nraiker13@skyrock.com', N'2UBPB34ZI', N'+63 (109) 454-2773', 3, N'http://dummyimage.com/227x100.png/5fa2dd/ffffff', N'7 Twin Pines Pass')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (41, N'Georgie Brayley', N'gbrayley14@indiegogo.com', N'rFf98c', N'+232 (763) 550-2248', 1, N'http://dummyimage.com/195x100.png/cc0000/ffffff', N'478 Heffernan Point')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (42, N'Darbee Hellwing', N'dhellwing15@blinklist.com', N'tl2X9Z', N'+967 (400) 589-5733', 1, N'http://dummyimage.com/175x100.png/cc0000/ffffff', N'93 Bluestem Street')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (43, N'Estelle Godier', N'egodier16@webs.com', N'aMKSKCcq', N'+420 (618) 662-4280', 2, N'http://dummyimage.com/205x100.png/dddddd/000000', N'4600 Karstens Road')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (44, N'Christan Isakovic', N'cisakovic17@businesswire.com', N'PjkSpVXCX', N'+1 (932) 277-9617', 3, N'http://dummyimage.com/163x100.png/dddddd/000000', N'227 Spaight Way')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (45, N'Morganica Wardel', N'mwardel18@hibu.com', N'v6P4oPCyk3', N'+30 (218) 257-7354', 2, N'http://dummyimage.com/149x100.png/ff4444/ffffff', N'4 Iowa Avenue')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (46, N'Barthel Bardill', N'bbardill19@com.com', N'fy8XuHSZAhX', N'+62 (247) 115-1652', 3, N'http://dummyimage.com/141x100.png/5fa2dd/ffffff', N'6871 Blackbird Lane')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (47, N'Norah Sings', N'nsings1a@drupal.org', N'TpwHlNuraa24', N'+30 (710) 741-2014', 1, N'http://dummyimage.com/235x100.png/dddddd/000000', N'828 Commercial Way')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (48, N'Wait Friskey', N'wfriskey1b@reuters.com', N'ezgHFy1D', N'+86 (634) 571-6038', 3, N'http://dummyimage.com/189x100.png/dddddd/000000', N'0 Birchwood Court')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (49, N'Dunstan Leitche', N'dleitche1c@icq.com', N'oHcnUMjwu', N'+46 (485) 392-5801', 3, N'http://dummyimage.com/243x100.png/ff4444/ffffff', N'2 Mendota Place')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (50, N'Thain Brecknall', N'tbrecknall1d@unblog.fr', N'XzEKDT0omgs', N'+86 (547) 327-4679', 2, N'http://dummyimage.com/193x100.png/dddddd/000000', N'1 Bellgrove Lane')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (51, N'Loretta Jerke', N'ljerke1e@dailymotion.com', N't7GwZh0', N'+62 (273) 714-1284', 3, N'http://dummyimage.com/112x100.png/5fa2dd/ffffff', N'4 Kim Street')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (52, N'Caterina Jennery', N'cjennery1f@icq.com', N'pQZ9rDRY', N'+57 (535) 378-7452', 2, N'http://dummyimage.com/102x100.png/ff4444/ffffff', N'40610 Northridge Street')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (53, N'Franzen Rosenfrucht', N'frosenfrucht1g@stumbleupon.com', N'teGd5USa9omX', N'+996 (229) 444-9008', 3, N'http://dummyimage.com/114x100.png/cc0000/ffffff', N'38939 Rusk Drive')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (54, N'Rodney Abercrombie', N'rabercrombie1h@webeden.co.uk', N'mKkD5Kg68', N'+7 (744) 823-4967', 1, N'http://dummyimage.com/164x100.png/dddddd/000000', N'172 Cottonwood Trail')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (55, N'Marney Kopelman', N'mkopelman1i@bravesites.com', N'bdoztKtv3NpW', N'+351 (495) 394-0144', 3, N'http://dummyimage.com/219x100.png/ff4444/ffffff', N'112 Spaight Lane')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (56, N'Travus Purcell', N'tpurcell1j@drupal.org', N'7gpHp5xP5Rm', N'+62 (440) 717-4408', 2, N'http://dummyimage.com/136x100.png/dddddd/000000', N'1 Upham Place')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (57, N'Francis Turnpenny', N'fturnpenny1k@auda.org.au', N'fKHfjs7vlB', N'+354 (612) 425-7376', 1, N'http://dummyimage.com/137x100.png/5fa2dd/ffffff', N'92445 Mosinee Drive')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (58, N'Ginevra Hamments', N'ghamments1l@exblog.jp', N'IlO9camqC7dL', N'+380 (373) 751-2971', 1, N'http://dummyimage.com/115x100.png/5fa2dd/ffffff', N'21 Pine View Parkway')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (59, N'Renado Alexsandrowicz', N'ralexsandrowicz1m@domainmarket.com', N'E0UBsSzi', N'+86 (801) 575-4063', 3, N'http://dummyimage.com/126x100.png/5fa2dd/ffffff', N'9 Blackbird Pass')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (60, N'Cristina McColl', N'cmccoll1n@multiply.com', N'Um9QlKu3aQd', N'+47 (707) 256-5354', 2, N'http://dummyimage.com/146x100.png/dddddd/000000', N'5108 Muir Terrace')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (61, N'Maury Surpliss', N'msurpliss1o@tuttocitta.it', N'IAcvzdq', N'+86 (384) 699-1756', 1, N'http://dummyimage.com/140x100.png/dddddd/000000', N'72 Carberry Lane')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (62, N'Lorin Dumphy', N'ldumphy1p@slideshare.net', N'VdE3GExPUF', N'+57 (351) 615-1932', 1, N'http://dummyimage.com/250x100.png/dddddd/000000', N'03 Montana Place')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (63, N'Gannon Eckersall', N'geckersall1q@altervista.org', N'hrk1zYPKaHG', N'+353 (585) 740-4508', 3, N'http://dummyimage.com/100x100.png/dddddd/000000', N'20 Norway Maple Terrace')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (64, N'Lenna Marston', N'lmarston1r@sfgate.com', N'Mj7TJM', N'+63 (519) 456-8516', 1, N'http://dummyimage.com/250x100.png/dddddd/000000', N'2 Autumn Leaf Plaza')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (65, N'Giffie O''Sesnane', N'gosesnane1s@msn.com', N'PyeHRyVRed', N'+593 (657) 375-0407', 1, N'http://dummyimage.com/250x100.png/5fa2dd/ffffff', N'17882 Buell Road')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (66, N'Hewitt Taylor', N'htaylor1t@angelfire.com', N'k9xk9utL', N'+86 (118) 221-8500', 3, N'http://dummyimage.com/117x100.png/cc0000/ffffff', N'42085 Muir Parkway')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (67, N'Christophe Tutsell', N'ctutsell1u@soundcloud.com', N'dAq4Zcfo', N'+55 (273) 169-1313', 1, N'http://dummyimage.com/203x100.png/dddddd/000000', N'88 Sachs Center')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (68, N'Tobi McLurg', N'tmclurg1v@spotify.com', N'Ng31mBV', N'+62 (322) 703-1883', 2, N'http://dummyimage.com/232x100.png/ff4444/ffffff', N'8 Towne Trail')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (69, N'Sharai Norwell', N'snorwell1w@forbes.com', N'dN2aRryVH7t', N'+86 (127) 145-8934', 1, N'http://dummyimage.com/142x100.png/cc0000/ffffff', N'14 Tony Circle')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (70, N'Kessiah Higgen', N'khiggen1x@illinois.edu', N'gAU9iaHpSjwx', N'+237 (766) 665-4319', 3, N'http://dummyimage.com/195x100.png/5fa2dd/ffffff', N'3 Express Junction')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (71, N'Lizette Bartoszewski', N'lbartoszewski1y@reference.com', N'AaMxAC8KkTjY', N'+55 (307) 515-6507', 1, N'http://dummyimage.com/136x100.png/cc0000/ffffff', N'478 Center Plaza')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (72, N'Weidar Sankey', N'wsankey1z@nba.com', N'5N3mu0XMzlwf', N'+1 (813) 900-4820', 3, N'http://dummyimage.com/139x100.png/5fa2dd/ffffff', N'381 Rieder Hill')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (73, N'Aridatha Metcalf', N'ametcalf20@friendfeed.com', N'iSQ4OhdWXO2', N'+504 (471) 725-2585', 3, N'http://dummyimage.com/240x100.png/cc0000/ffffff', N'3983 Maple Alley')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (74, N'Robbin Lidgey', N'rlidgey21@youtu.be', N'JAvSu71', N'+7 (619) 925-7243', 1, N'http://dummyimage.com/182x100.png/ff4444/ffffff', N'0431 Thierer Circle')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (75, N'Colly Borborough', N'cborborough22@weebly.com', N'72vjxTp', N'+62 (290) 996-9223', 1, N'http://dummyimage.com/218x100.png/dddddd/000000', N'15298 Oneill Parkway')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (76, N'Rosemarie Mountney', N'rmountney23@si.edu', N'TN05xefgK8G4', N'+359 (816) 480-8750', 3, N'http://dummyimage.com/173x100.png/cc0000/ffffff', N'639 Riverside Road')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (77, N'Annemarie Hitscher', N'ahitscher24@businessweek.com', N'5sBo1P2Xg', N'+48 (636) 951-4701', 1, N'http://dummyimage.com/102x100.png/dddddd/000000', N'843 Dovetail Avenue')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (78, N'Helena Brawson', N'hbrawson25@ibm.com', N'aDTjXiY99eI', N'+58 (207) 163-1726', 3, N'http://dummyimage.com/185x100.png/5fa2dd/ffffff', N'2 Surrey Parkway')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (79, N'Logan Southouse', N'lsouthouse26@newsvine.com', N'V9et02NGK', N'+86 (865) 339-9056', 3, N'http://dummyimage.com/242x100.png/cc0000/ffffff', N'2 Rusk Parkway')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (80, N'Jerrilee Libreros', N'jlibreros27@sciencedirect.com', N'fi5i07', N'+33 (709) 215-6940', 3, N'http://dummyimage.com/249x100.png/cc0000/ffffff', N'9 Shoshone Lane')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (81, N'Viviyan Eliff', N'veliff28@cnbc.com', N'sGBd1rN', N'+995 (350) 788-3122', 2, N'http://dummyimage.com/228x100.png/cc0000/ffffff', N'0 Warrior Park')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (82, N'Lukas Mowsley', N'lmowsley29@mozilla.org', N'PbcFCgs0', N'+86 (926) 860-5309', 2, N'http://dummyimage.com/247x100.png/ff4444/ffffff', N'53103 Hanover Parkway')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (83, N'Adriana Lashmar', N'alashmar2a@huffingtonpost.com', N'HKQPio27O', N'+55 (603) 387-2078', 2, N'http://dummyimage.com/139x100.png/cc0000/ffffff', N'00 Grover Junction')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (84, N'Jerald Donegan', N'jdonegan2b@columbia.edu', N'OH12OulEO', N'+63 (638) 345-6441', 1, N'http://dummyimage.com/199x100.png/dddddd/000000', N'4 Golf Course Lane')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (85, N'Augy Bumfrey', N'abumfrey2c@icq.com', N'O8aBfKyrvX', N'+33 (591) 214-9582', 2, N'http://dummyimage.com/179x100.png/ff4444/ffffff', N'7 Steensland Court')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (86, N'Honey Burriss', N'hburriss2d@list-manage.com', N'9vNq4oPK', N'+86 (869) 224-6579', 1, N'http://dummyimage.com/188x100.png/ff4444/ffffff', N'25721 Everett Center')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (87, N'Jermayne MacMeeking', N'jmacmeeking2e@zimbio.com', N'ZWyyi6CQgm', N'+86 (891) 558-9723', 2, N'http://dummyimage.com/219x100.png/5fa2dd/ffffff', N'04067 Kennedy Plaza')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (88, N'Sancho Doxsey', N'sdoxsey2f@webs.com', N'2sAuMs', N'+86 (831) 414-0508', 2, N'http://dummyimage.com/200x100.png/ff4444/ffffff', N'54 Debra Court')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (89, N'Ari Gumey', N'agumey2g@themeforest.net', N'JxmqJA', N'+43 (179) 968-2257', 1, N'http://dummyimage.com/183x100.png/dddddd/000000', N'71 Fair Oaks Center')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (90, N'Gretal Greengrass', N'ggreengrass2h@zimbio.com', N'AYtjoQyiQB0Q', N'+1 (609) 772-0103', 3, N'http://dummyimage.com/243x100.png/ff4444/ffffff', N'7796 Kropf Road')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (91, N'Rey Setter', N'rsetter2i@si.edu', N'qcbyTF9s8Qvr', N'+62 (130) 317-8429', 2, N'http://dummyimage.com/223x100.png/dddddd/000000', N'03708 Ruskin Junction')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (92, N'Galvin Trebbett', N'gtrebbett2j@psu.edu', N'SbDqtEmVfW', N'+351 (407) 541-5327', 1, N'http://dummyimage.com/109x100.png/5fa2dd/ffffff', N'6 Barnett Terrace')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (93, N'Gerladina Sutherley', N'gsutherley2k@sourceforge.net', N'NmuqqF6fLD', N'+86 (945) 404-6410', 2, N'http://dummyimage.com/161x100.png/5fa2dd/ffffff', N'390 Sugar Alley')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (94, N'Cross Rheubottom', N'crheubottom2l@biglobe.ne.jp', N'Okln6hW', N'+57 (989) 598-5607', 3, N'http://dummyimage.com/187x100.png/dddddd/000000', N'281 Elgar Pass')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (95, N'Dorey Howroyd', N'dhowroyd2m@ifeng.com', N'qOCwkghJw', N'+1 (239) 106-7460', 1, N'http://dummyimage.com/180x100.png/cc0000/ffffff', N'9824 Marcy Parkway')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (96, N'Antonin McShane', N'amcshane2n@wordpress.com', N'jMPt83X', N'+51 (561) 434-2374', 1, N'http://dummyimage.com/209x100.png/ff4444/ffffff', N'00 Hayes Road')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (97, N'Gottfried Loker', N'gloker2o@shop-pro.jp', N'B88nKIAmX', N'+98 (453) 812-9020', 2, N'http://dummyimage.com/111x100.png/dddddd/000000', N'99 7th Street')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (98, N'Hetty Lanning', N'hlanning2p@list-manage.com', N'aT7n8T', N'+62 (970) 150-4391', 3, N'http://dummyimage.com/141x100.png/dddddd/000000', N'76643 Bluestem Place')
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (99, N'Arin Lyles', N'alyles2q@baidu.com', N'nXMVG7MVz', N'+1 (358) 375-7332', 1, N'http://dummyimage.com/183x100.png/5fa2dd/ffffff', N'65715 6th Road')
GO
INSERT [Edward].[People] ([id], [Name], [Mail], [Psw], [PhoneNum], [Role], [ImgUrl], [Address]) VALUES (100, N'Raymond Henzer', N'rhenzer2r@mysql.com', N'wrsVo97hzua', N'+7 (302) 980-0889', 1, N'http://dummyimage.com/145x100.png/5fa2dd/ffffff', N'1472 Glacier Hill Park')
SET IDENTITY_INSERT [Edward].[People] OFF
GO
SET IDENTITY_INSERT [Edward].[Voucher] ON 

INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (0, 15, 6)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (1, 93, 37)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (2, 1, 41)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (3, 14, 73)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (4, 71, 67)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (5, 67, 67)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (6, 94, 51)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (7, 59, 26)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (8, 46, 13)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (9, 50, 4)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (10, 41, 6)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (11, 11, 100)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (12, 1, 91)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (13, 80, 6)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (14, 65, 29)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (15, 17, 51)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (16, 49, 14)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (17, 45, 48)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (18, 32, 86)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (19, 53, 20)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (20, 67, 61)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (21, 52, 35)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (22, 42, 31)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (23, 79, 60)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (24, 15, 84)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (25, 68, 2)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (26, 11, 85)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (27, 33, 95)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (28, 35, 29)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (29, 52, 22)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (30, 18, 36)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (31, 55, 48)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (32, 9, 73)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (33, 73, 84)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (34, 17, 68)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (35, 23, 68)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (36, 73, 3)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (37, 75, 93)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (38, 32, 49)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (39, 47, 28)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (40, 40, 97)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (41, 58, 15)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (42, 61, 23)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (43, 38, 92)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (44, 32, 100)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (45, 75, 46)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (46, 6, 89)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (47, 77, 69)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (48, 3, 79)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (49, 61, 2)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (50, 88, 91)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (51, 24, 1)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (52, 63, 75)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (53, 82, 21)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (54, 54, 11)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (55, 37, 87)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (56, 73, 41)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (57, 6, 42)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (58, 56, 82)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (59, 65, 81)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (60, 67, 22)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (61, 15, 5)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (62, 78, 48)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (63, 59, 62)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (64, 33, 60)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (65, 20, 33)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (66, 24, 46)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (67, 35, 39)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (68, 67, 56)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (69, 84, 20)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (70, 26, 56)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (71, 97, 85)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (72, 5, 44)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (73, 75, 57)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (74, 46, 62)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (75, 34, 34)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (76, 10, 97)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (77, 27, 52)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (78, 48, 76)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (79, 95, 44)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (80, 60, 1)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (81, 90, 51)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (82, 25, 11)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (83, 68, 53)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (84, 7, 17)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (85, 84, 21)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (86, 32, 51)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (87, 87, 52)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (88, 6, 13)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (89, 3, 96)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (90, 2, 42)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (91, 58, 51)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (92, 25, 86)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (93, 42, 58)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (94, 38, 46)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (95, 47, 27)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (96, 83, 9)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (97, 79, 86)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (98, 54, 55)
GO
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (99, 87, 77)
SET IDENTITY_INSERT [Edward].[Voucher] OFF
GO
ALTER TABLE [Edward].[Bill]  WITH CHECK ADD  CONSTRAINT [FK_Bill_People] FOREIGN KEY([IDUser])
REFERENCES [Edward].[People] ([id])
GO
ALTER TABLE [Edward].[Bill] CHECK CONSTRAINT [FK_Bill_People]
GO
ALTER TABLE [Edward].[Bill]  WITH CHECK ADD  CONSTRAINT [FK_Bill_Voucher] FOREIGN KEY([IDvoucher])
REFERENCES [Edward].[Voucher] ([ID])
GO
ALTER TABLE [Edward].[Bill] CHECK CONSTRAINT [FK_Bill_Voucher]
GO
ALTER TABLE [Edward].[BillDetail]  WITH CHECK ADD  CONSTRAINT [FK_BillDetail_Bill] FOREIGN KEY([IDBill])
REFERENCES [Edward].[Bill] ([id])
GO
ALTER TABLE [Edward].[BillDetail] CHECK CONSTRAINT [FK_BillDetail_Bill]
GO
ALTER TABLE [Edward].[BillDetail]  WITH CHECK ADD  CONSTRAINT [FK_BillDetail_Clothes_Properties] FOREIGN KEY([IDClothes])
REFERENCES [Edward].[Clothes_Properties] ([ID])
GO
ALTER TABLE [Edward].[BillDetail] CHECK CONSTRAINT [FK_BillDetail_Clothes_Properties]
GO
ALTER TABLE [Edward].[Clothes]  WITH CHECK ADD  CONSTRAINT [FK_Clothes_Category] FOREIGN KEY([idCategory])
REFERENCES [Edward].[Category] ([id])
GO
ALTER TABLE [Edward].[Clothes] CHECK CONSTRAINT [FK_Clothes_Category]
GO
ALTER TABLE [Edward].[Clothes]  WITH CHECK ADD  CONSTRAINT [FK_Clothes_People] FOREIGN KEY([IDseller])
REFERENCES [Edward].[People] ([id])
GO
ALTER TABLE [Edward].[Clothes] CHECK CONSTRAINT [FK_Clothes_People]
GO
ALTER TABLE [Edward].[Clothes_Properties]  WITH CHECK ADD  CONSTRAINT [FK_Clothes_Properties_Clothes] FOREIGN KEY([ID])
REFERENCES [Edward].[Clothes] ([id])
GO
ALTER TABLE [Edward].[Clothes_Properties] CHECK CONSTRAINT [FK_Clothes_Properties_Clothes]
GO
ALTER TABLE [Edward].[Favorites]  WITH CHECK ADD  CONSTRAINT [FK_Favorites_Clothes] FOREIGN KEY([IDclothes])
REFERENCES [Edward].[Clothes] ([id])
GO
ALTER TABLE [Edward].[Favorites] CHECK CONSTRAINT [FK_Favorites_Clothes]
GO
ALTER TABLE [Edward].[Favorites]  WITH CHECK ADD  CONSTRAINT [FK_Favorites_People] FOREIGN KEY([IDpeople])
REFERENCES [Edward].[People] ([id])
GO
ALTER TABLE [Edward].[Favorites] CHECK CONSTRAINT [FK_Favorites_People]
GO
ALTER TABLE [Edward].[ImgUrls]  WITH CHECK ADD  CONSTRAINT [FK_ImgUrls_Clothes] FOREIGN KEY([IDClothes])
REFERENCES [Edward].[Clothes] ([id])
GO
ALTER TABLE [Edward].[ImgUrls] CHECK CONSTRAINT [FK_ImgUrls_Clothes]
GO
ALTER TABLE [Edward].[Voucher]  WITH CHECK ADD  CONSTRAINT [FK_Voucher_People] FOREIGN KEY([IDSeller])
REFERENCES [Edward].[People] ([id])
GO
ALTER TABLE [Edward].[Voucher] CHECK CONSTRAINT [FK_Voucher_People]
GO
USE [master]
GO
ALTER DATABASE [EdwardEcomerce] SET  READ_WRITE 
GO
