USE [master]
GO
/****** Object:  Database [EdwardEcomerce]    Script Date: 11/22/2022 10:05:43 PM ******/
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
/****** Object:  User [Edward]    Script Date: 11/22/2022 10:05:44 PM ******/
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
/****** Object:  Schema [Edward]    Script Date: 11/22/2022 10:05:44 PM ******/
CREATE SCHEMA [Edward]
GO
/****** Object:  Schema [kynalabc]    Script Date: 11/22/2022 10:05:44 PM ******/
CREATE SCHEMA [kynalabc]
GO
/****** Object:  Table [Edward].[Bill]    Script Date: 11/22/2022 10:05:44 PM ******/
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
	[Status] [nvarchar](255) NULL,
 CONSTRAINT [PK_Bill] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[BillDetail]    Script Date: 11/22/2022 10:05:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[BillDetail](
	[IDBill] [int] NOT NULL,
	[IDClotheProperties] [int] NOT NULL,
	[Quantity] [int] NULL,
	[Price] [float] NULL,
 CONSTRAINT [PK_BillDetail] PRIMARY KEY CLUSTERED 
(
	[IDBill] ASC,
	[IDClotheProperties] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Category]    Script Date: 11/22/2022 10:05:44 PM ******/
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
/****** Object:  Table [Edward].[Clothes]    Script Date: 11/22/2022 10:05:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[Clothes](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[IDseller] [int] NULL,
	[idCategory] [int] NULL,
	[des] [nvarchar](255) NULL,
	[Name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Clothes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Clothes_Properties]    Script Date: 11/22/2022 10:05:44 PM ******/
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
 CONSTRAINT [PK_Clothes_Properties] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Favorites]    Script Date: 11/22/2022 10:05:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[Favorites](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[IDClothes] [int] NULL,
	[IDUser] [int] NULL,
 CONSTRAINT [PK_Favorites] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[ImgUrls]    Script Date: 11/22/2022 10:05:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[ImgUrls](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[IDClothes] [int] NULL,
	[ImgUrl] [nvarchar](255) NULL,
 CONSTRAINT [PK_ImgUrls] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[People]    Script Date: 11/22/2022 10:05:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[People](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nchar](50) NULL,
	[Mail] [nchar](50) NULL,
	[Password] [nchar](255) NULL,
	[PhoneNumber] [nchar](20) NULL,
	[Role] [int] NULL,
	[img] [nvarchar](255) NULL,
	[Address] [nchar](50) NULL,
 CONSTRAINT [PK_People] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Voucher]    Script Date: 11/22/2022 10:05:44 PM ******/
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

INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (1, 75, 88, CAST(N'2022-04-03' AS Date), CAST(N'2022-08-13' AS Date), N'Andalax')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (2, 60, 94, CAST(N'2021-11-13' AS Date), CAST(N'2022-05-02' AS Date), N'Konklab')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (3, 79, 11, CAST(N'2021-11-12' AS Date), CAST(N'2021-11-27' AS Date), N'Sonair')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (4, 74, 1, CAST(N'2022-03-06' AS Date), CAST(N'2022-11-03' AS Date), N'Sub-Ex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (5, 44, 93, CAST(N'2022-03-25' AS Date), CAST(N'2022-02-04' AS Date), N'Keylex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (6, 99, 26, CAST(N'2022-04-05' AS Date), CAST(N'2022-04-06' AS Date), N'Bytecard')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (7, 92, 55, CAST(N'2022-04-30' AS Date), CAST(N'2022-05-29' AS Date), N'Solarbreeze')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (8, 60, 95, CAST(N'2022-02-09' AS Date), CAST(N'2022-09-16' AS Date), N'Tres-Zap')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (9, 24, 89, CAST(N'2022-03-09' AS Date), CAST(N'2022-09-19' AS Date), N'Alphazap')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (10, 51, 86, CAST(N'2022-05-16' AS Date), CAST(N'2022-05-27' AS Date), N'Keylex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (11, 100, 25, CAST(N'2022-08-31' AS Date), CAST(N'2022-03-31' AS Date), N'Otcom')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (12, 64, 2, CAST(N'2022-01-25' AS Date), CAST(N'2021-12-07' AS Date), N'Flowdesk')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (13, 78, 93, CAST(N'2022-05-13' AS Date), CAST(N'2022-02-27' AS Date), N'Tin')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (14, 12, 99, CAST(N'2022-04-09' AS Date), CAST(N'2021-12-19' AS Date), N'Mat Lam Tam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (15, 10, 96, CAST(N'2022-02-26' AS Date), CAST(N'2022-06-09' AS Date), N'Overhold')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (16, 93, 100, CAST(N'2021-12-25' AS Date), CAST(N'2022-06-21' AS Date), N'Zontrax')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (17, 2, 77, CAST(N'2021-12-03' AS Date), CAST(N'2022-11-01' AS Date), N'Aerified')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (18, 14, 44, CAST(N'2021-12-24' AS Date), CAST(N'2022-03-24' AS Date), N'Fintone')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (19, 36, 25, CAST(N'2022-05-05' AS Date), CAST(N'2022-04-29' AS Date), N'Flexidy')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (20, 76, 40, CAST(N'2022-03-15' AS Date), CAST(N'2022-08-01' AS Date), N'Latlux')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (21, 38, 16, CAST(N'2022-09-06' AS Date), CAST(N'2022-09-19' AS Date), N'Namfix')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (22, 54, 23, CAST(N'2022-04-22' AS Date), CAST(N'2021-11-07' AS Date), N'Fintone')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (23, 52, 49, CAST(N'2022-05-10' AS Date), CAST(N'2021-12-25' AS Date), N'Trippledex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (24, 87, 34, CAST(N'2021-11-19' AS Date), CAST(N'2022-10-10' AS Date), N'Keylex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (25, 62, 99, CAST(N'2022-04-06' AS Date), CAST(N'2022-06-04' AS Date), N'Sonair')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (26, 36, 98, CAST(N'2022-03-10' AS Date), CAST(N'2022-06-17' AS Date), N'Prodder')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (27, 10, 32, CAST(N'2022-02-21' AS Date), CAST(N'2021-11-05' AS Date), N'Prodder')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (28, 83, 93, CAST(N'2022-03-14' AS Date), CAST(N'2022-04-20' AS Date), N'Opela')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (29, 91, 6, CAST(N'2022-06-07' AS Date), CAST(N'2022-02-09' AS Date), N'Voltsillam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (30, 33, 69, CAST(N'2022-09-13' AS Date), CAST(N'2022-09-26' AS Date), N'Transcof')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (31, 87, 31, CAST(N'2021-11-04' AS Date), CAST(N'2022-02-20' AS Date), N'Stringtough')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (32, 48, 39, CAST(N'2022-01-02' AS Date), CAST(N'2022-04-25' AS Date), N'It')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (33, 4, 28, CAST(N'2022-08-07' AS Date), CAST(N'2022-01-26' AS Date), N'Ventosanzap')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (34, 81, 95, CAST(N'2021-12-02' AS Date), CAST(N'2022-01-13' AS Date), N'Asoka')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (35, 75, 64, CAST(N'2022-04-27' AS Date), CAST(N'2021-12-10' AS Date), N'Mat Lam Tam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (36, 5, 31, CAST(N'2022-02-20' AS Date), CAST(N'2022-03-15' AS Date), N'Stim')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (37, 69, 37, CAST(N'2021-11-23' AS Date), CAST(N'2022-09-20' AS Date), N'Sub-Ex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (38, 18, 38, CAST(N'2022-06-13' AS Date), CAST(N'2022-05-05' AS Date), N'Zaam-Dox')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (39, 8, 35, CAST(N'2022-08-14' AS Date), CAST(N'2022-03-17' AS Date), N'Viva')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (40, 22, 99, CAST(N'2022-07-12' AS Date), CAST(N'2022-03-27' AS Date), N'Matsoft')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (41, 2, 11, CAST(N'2022-05-07' AS Date), CAST(N'2021-11-16' AS Date), N'Home Ing')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (42, 47, 37, CAST(N'2022-03-10' AS Date), CAST(N'2022-03-14' AS Date), N'Quo Lux')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (43, 72, 38, CAST(N'2022-02-16' AS Date), CAST(N'2022-07-09' AS Date), N'Regrant')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (44, 59, 32, CAST(N'2022-10-03' AS Date), CAST(N'2022-07-03' AS Date), N'Toughjoyfax')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (45, 29, 25, CAST(N'2022-01-10' AS Date), CAST(N'2022-07-15' AS Date), N'Konklab')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (46, 97, 97, CAST(N'2022-03-06' AS Date), CAST(N'2022-08-06' AS Date), N'Opela')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (47, 9, 39, CAST(N'2021-12-05' AS Date), CAST(N'2022-03-24' AS Date), N'Temp')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (48, 93, 58, CAST(N'2022-01-10' AS Date), CAST(N'2022-10-29' AS Date), N'Bamity')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (49, 91, 68, CAST(N'2021-11-13' AS Date), CAST(N'2022-07-03' AS Date), N'Tempsoft')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (50, 54, 84, CAST(N'2022-01-29' AS Date), CAST(N'2022-06-21' AS Date), N'Redhold')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (51, 14, 100, CAST(N'2022-09-07' AS Date), CAST(N'2022-06-17' AS Date), N'Voyatouch')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (52, 79, 26, CAST(N'2022-10-07' AS Date), CAST(N'2021-11-24' AS Date), N'Bitchip')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (53, 64, 8, CAST(N'2022-10-28' AS Date), CAST(N'2022-08-24' AS Date), N'Viva')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (54, 98, 1, CAST(N'2022-09-11' AS Date), CAST(N'2022-07-10' AS Date), N'Voyatouch')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (55, 99, 48, CAST(N'2022-06-14' AS Date), CAST(N'2022-01-24' AS Date), N'Aerified')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (56, 38, 31, CAST(N'2022-07-24' AS Date), CAST(N'2022-10-12' AS Date), N'Flowdesk')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (57, 71, 80, CAST(N'2021-11-17' AS Date), CAST(N'2022-08-08' AS Date), N'Fix San')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (58, 11, 18, CAST(N'2021-11-18' AS Date), CAST(N'2022-01-07' AS Date), N'Sonair')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (59, 57, 37, CAST(N'2022-01-16' AS Date), CAST(N'2021-12-22' AS Date), N'Alpha')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (60, 85, 82, CAST(N'2021-11-23' AS Date), CAST(N'2022-06-30' AS Date), N'Toughjoyfax')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (61, 49, 5, CAST(N'2021-11-25' AS Date), CAST(N'2022-05-17' AS Date), N'Stim')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (62, 44, 15, CAST(N'2022-09-14' AS Date), CAST(N'2022-01-26' AS Date), N'Tres-Zap')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (63, 2, 79, CAST(N'2022-03-28' AS Date), CAST(N'2022-02-19' AS Date), N'Treeflex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (64, 5, 22, CAST(N'2022-07-05' AS Date), CAST(N'2022-03-06' AS Date), N'Konklab')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (65, 35, 59, CAST(N'2022-10-08' AS Date), CAST(N'2022-07-18' AS Date), N'Stringtough')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (66, 23, 94, CAST(N'2022-05-31' AS Date), CAST(N'2021-12-24' AS Date), N'Quo Lux')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (67, 77, 16, CAST(N'2022-05-25' AS Date), CAST(N'2021-11-20' AS Date), N'Greenlam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (68, 54, 49, CAST(N'2022-07-23' AS Date), CAST(N'2022-08-06' AS Date), N'Sonsing')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (69, 41, 23, CAST(N'2021-12-30' AS Date), CAST(N'2022-07-12' AS Date), N'Otcom')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (70, 98, 5, CAST(N'2021-11-16' AS Date), CAST(N'2022-04-28' AS Date), N'Stringtough')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (71, 5, 29, CAST(N'2022-10-24' AS Date), CAST(N'2022-02-13' AS Date), N'Mat Lam Tam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (72, 72, 21, CAST(N'2022-10-09' AS Date), CAST(N'2022-09-11' AS Date), N'Rank')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (73, 16, 15, CAST(N'2022-05-22' AS Date), CAST(N'2022-10-21' AS Date), N'Greenlam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (74, 88, 78, CAST(N'2021-12-09' AS Date), CAST(N'2022-08-28' AS Date), N'Zoolab')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (75, 61, 99, CAST(N'2022-06-30' AS Date), CAST(N'2021-11-26' AS Date), N'Alphazap')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (76, 71, 26, CAST(N'2021-12-23' AS Date), CAST(N'2022-04-19' AS Date), N'Zontrax')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (77, 83, 78, CAST(N'2022-07-30' AS Date), CAST(N'2022-11-02' AS Date), N'Cardguard')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (78, 57, 34, CAST(N'2022-08-11' AS Date), CAST(N'2022-01-23' AS Date), N'Temp')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (79, 45, 87, CAST(N'2021-11-11' AS Date), CAST(N'2022-07-05' AS Date), N'Kanlam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (80, 96, 52, CAST(N'2022-05-10' AS Date), CAST(N'2022-04-13' AS Date), N'Home Ing')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (81, 88, 90, CAST(N'2022-04-07' AS Date), CAST(N'2022-04-11' AS Date), N'Tampflex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (82, 45, 21, CAST(N'2022-07-26' AS Date), CAST(N'2021-12-12' AS Date), N'Zathin')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (83, 100, 63, CAST(N'2022-10-25' AS Date), CAST(N'2022-02-05' AS Date), N'Bytecard')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (84, 21, 17, CAST(N'2022-05-08' AS Date), CAST(N'2022-07-21' AS Date), N'Cookley')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (85, 86, 35, CAST(N'2022-10-08' AS Date), CAST(N'2022-05-28' AS Date), N'Namfix')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (86, 29, 84, CAST(N'2022-10-08' AS Date), CAST(N'2022-04-08' AS Date), N'Bitchip')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (87, 42, 9, CAST(N'2021-11-12' AS Date), CAST(N'2022-11-02' AS Date), N'Bitchip')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (88, 23, 21, CAST(N'2022-04-24' AS Date), CAST(N'2022-08-09' AS Date), N'Kanlam')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (89, 38, 38, CAST(N'2022-01-14' AS Date), CAST(N'2022-04-10' AS Date), N'Matsoft')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (90, 65, 42, CAST(N'2022-01-08' AS Date), CAST(N'2022-05-05' AS Date), N'Alphazap')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (91, 79, 39, CAST(N'2022-10-14' AS Date), CAST(N'2022-07-29' AS Date), N'It')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (92, 78, 41, CAST(N'2021-12-31' AS Date), CAST(N'2022-07-24' AS Date), N'Otcom')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (93, 36, 55, CAST(N'2022-01-21' AS Date), CAST(N'2021-12-13' AS Date), N'Voyatouch')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (94, 55, 75, CAST(N'2022-02-17' AS Date), CAST(N'2022-05-18' AS Date), N'Stim')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (95, 19, 47, CAST(N'2022-10-17' AS Date), CAST(N'2022-09-02' AS Date), N'Y-Solowarm')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (96, 10, 68, CAST(N'2022-02-27' AS Date), CAST(N'2022-02-26' AS Date), N'It')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (97, 59, 15, CAST(N'2022-02-10' AS Date), CAST(N'2022-01-06' AS Date), N'Biodex')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (98, 28, 93, CAST(N'2021-12-14' AS Date), CAST(N'2022-06-30' AS Date), N'Domainer')
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (99, 54, 74, CAST(N'2021-11-09' AS Date), CAST(N'2022-10-22' AS Date), N'Redhold')
GO
INSERT [Edward].[Bill] ([id], [IDUser], [IDvoucher], [DateCreate], [DateReceived], [Status]) VALUES (100, 68, 23, CAST(N'2022-06-03' AS Date), CAST(N'2022-03-17' AS Date), N'Gembucket')
SET IDENTITY_INSERT [Edward].[Bill] OFF
GO
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (1, 89, 40, 2412.5)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (2, 20, 46, 8555.66)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (3, 43, 71, 4102.6)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (4, 57, 50, 7305.61)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (5, 43, 83, 4494.12)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (6, 93, 21, 7957.72)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (7, 34, 46, 3033.34)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (8, 12, 19, 6925.89)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (9, 80, 83, 9516.59)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (10, 13, 93, 4847.22)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (11, 31, 30, 1037.08)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (12, 91, 57, 4682.57)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (13, 86, 18, 8806.24)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (14, 19, 38, 5263.44)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (15, 26, 59, 5286.55)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (16, 49, 88, 3332.7)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (17, 100, 97, 8754.8)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (18, 65, 79, 5942.05)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (19, 41, 15, 6293.73)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (20, 93, 74, 1671.85)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (21, 69, 54, 917.09)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (22, 73, 53, 3143.43)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (23, 27, 24, 302.88)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (24, 73, 9, 3578.26)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (25, 34, 88, 9422.04)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (26, 31, 85, 7489.02)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (27, 65, 80, 2999.94)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (28, 78, 97, 4055.89)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (29, 77, 45, 6475.54)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (30, 12, 21, 7389.62)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (31, 6, 67, 317.69)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (32, 23, 97, 3711.46)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (33, 84, 60, 2070.43)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (34, 73, 5, 9674.47)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (35, 69, 8, 1443)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (36, 8, 10, 4822.36)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (37, 39, 98, 8046.91)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (38, 25, 79, 163.98)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (39, 57, 60, 2882.84)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (40, 86, 34, 7898.58)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (41, 86, 52, 6135.81)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (42, 34, 95, 5604.39)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (43, 5, 40, 9977.92)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (44, 70, 4, 8367.53)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (45, 19, 11, 5362.46)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (46, 64, 77, 3171.99)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (47, 58, 70, 7225.75)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (48, 57, 48, 7592.1)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (49, 42, 50, 6548.8)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (50, 17, 77, 2875.69)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (51, 33, 49, 7269.17)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (52, 5, 38, 9795.48)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (53, 49, 26, 8676.74)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (54, 26, 72, 4647.31)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (55, 95, 36, 9957.89)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (56, 90, 5, 333.52)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (57, 92, 15, 762.08)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (58, 94, 25, 9971.66)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (59, 98, 5, 2449.53)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (60, 35, 86, 6522.31)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (61, 100, 90, 9631.09)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (62, 96, 33, 4416.37)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (63, 38, 10, 3955.17)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (64, 94, 48, 9529.16)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (65, 55, 20, 4019.34)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (66, 99, 74, 4640.56)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (67, 38, 37, 6136.97)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (68, 56, 63, 9021.5)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (69, 92, 94, 8437.82)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (70, 38, 12, 8495.92)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (71, 56, 95, 933.14)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (72, 96, 27, 7589.99)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (73, 60, 36, 9554.06)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (74, 61, 39, 7565.72)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (75, 61, 61, 649.27)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (76, 81, 74, 2767.82)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (77, 42, 31, 5217.62)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (78, 40, 67, 1287.13)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (79, 63, 41, 5479.77)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (80, 46, 23, 2359.91)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (81, 21, 24, 9944.09)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (82, 37, 90, 8941.21)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (83, 63, 38, 498.55)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (84, 9, 47, 641.97)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (85, 3, 41, 5155.5)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (86, 48, 44, 8588.57)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (87, 26, 39, 3530.62)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (88, 39, 29, 3679.87)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (89, 23, 10, 2635.56)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (90, 20, 78, 556.09)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (91, 74, 93, 5695.31)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (92, 78, 32, 128.26)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (93, 27, 10, 9259.25)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (94, 57, 7, 2402.99)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (95, 99, 73, 4004.05)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (96, 96, 68, 921.96)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (97, 14, 81, 226.02)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (98, 35, 70, 1263.17)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (99, 72, 20, 9131.83)
INSERT [Edward].[BillDetail] ([IDBill], [IDClotheProperties], [Quantity], [Price]) VALUES (100, 45, 2, 4606.69)
GO
SET IDENTITY_INSERT [Edward].[Category] ON 

INSERT [Edward].[Category] ([id], [Name]) VALUES (1, N'Ventosanzap')
INSERT [Edward].[Category] ([id], [Name]) VALUES (2, N'Opela')
INSERT [Edward].[Category] ([id], [Name]) VALUES (3, N'Span')
INSERT [Edward].[Category] ([id], [Name]) VALUES (4, N'Cardguard')
INSERT [Edward].[Category] ([id], [Name]) VALUES (5, N'Stim')
INSERT [Edward].[Category] ([id], [Name]) VALUES (6, N'Andalax')
INSERT [Edward].[Category] ([id], [Name]) VALUES (7, N'Prodder')
INSERT [Edward].[Category] ([id], [Name]) VALUES (8, N'Flexidy')
INSERT [Edward].[Category] ([id], [Name]) VALUES (9, N'Tresom')
INSERT [Edward].[Category] ([id], [Name]) VALUES (10, N'Fixflex')
SET IDENTITY_INSERT [Edward].[Category] OFF
GO
SET IDENTITY_INSERT [Edward].[Clothes] ON 

INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (1, 39, 5, N'Puce', N'Ooba')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (2, 50, 7, N'Maroon', N'Livefish')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (3, 92, 8, N'Goldenrod', N'Yozio')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (4, 87, 1, N'Blue', N'Yabox')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (5, 77, 9, N'Green', N'Zoomzone')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (6, 20, 5, N'Crimson', N'Skivee')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (7, 93, 4, N'Mauv', N'Abata')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (8, 39, 7, N'Turquoise', N'Oyoyo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (9, 56, 8, N'Blue', N'Ntags')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (10, 82, 6, N'Purple', N'Devpoint')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (11, 92, 8, N'Green', N'Roodel')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (12, 6, 3, N'Khaki', N'Mycat')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (13, 18, 7, N'Pink', N'Topicstorm')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (14, 3, 10, N'Teal', N'Dynabox')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (15, 94, 2, N'Fuscia', N'Ntags')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (16, 2, 1, N'Turquoise', N'Skyba')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (17, 63, 2, N'Fuscia', N'Buzzdog')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (18, 78, 9, N'Maroon', N'Aimbu')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (19, 28, 10, N'Turquoise', N'Youspan')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (20, 32, 6, N'Indigo', N'Jayo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (21, 52, 2, N'Purple', N'Oloo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (22, 47, 3, N'Fuscia', N'Babbleset')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (23, 79, 7, N'Red', N'Vipe')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (24, 83, 9, N'Orange', N'Jabbertype')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (25, 31, 8, N'Mauv', N'Latz')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (26, 73, 4, N'Pink', N'Realblab')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (27, 13, 9, N'Aquamarine', N'Skimia')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (28, 80, 5, N'Aquamarine', N'Eire')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (29, 85, 3, N'Aquamarine', N'Jayo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (30, 84, 7, N'Red', N'Ooba')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (31, 24, 9, N'Maroon', N'Fadeo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (32, 59, 3, N'Turquoise', N'Gabcube')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (33, 76, 7, N'Orange', N'Twitterlist')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (34, 86, 8, N'Aquamarine', N'Topicblab')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (35, 12, 1, N'Turquoise', N'Yadel')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (36, 16, 10, N'Pink', N'Brainbox')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (37, 97, 5, N'Red', N'Ozu')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (38, 49, 7, N'Indigo', N'Yabox')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (39, 11, 4, N'Fuscia', N'Snaptags')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (40, 78, 3, N'Purple', N'Katz')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (41, 8, 3, N'Goldenrod', N'Twitterlist')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (42, 99, 9, N'Orange', N'Mita')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (43, 25, 6, N'Indigo', N'Npath')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (44, 58, 2, N'Purple', N'Browsebug')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (45, 62, 4, N'Green', N'Blogtags')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (46, 56, 1, N'Orange', N'Roomm')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (47, 11, 6, N'Pink', N'Eabox')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (48, 21, 5, N'Blue', N'Babblestorm')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (49, 45, 3, N'Indigo', N'Skibox')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (50, 68, 4, N'Pink', N'Jayo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (51, 83, 6, N'Pink', N'Skinder')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (52, 21, 5, N'Crimson', N'Skyba')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (53, 26, 4, N'Red', N'Quamba')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (54, 33, 2, N'Teal', N'Divavu')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (55, 34, 8, N'Aquamarine', N'Oyope')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (56, 67, 4, N'Teal', N'Brainlounge')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (57, 29, 7, N'Fuscia', N'Talane')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (58, 20, 6, N'Puce', N'Flashdog')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (59, 77, 9, N'Puce', N'Skiptube')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (60, 28, 5, N'Orange', N'Myworks')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (61, 82, 4, N'Puce', N'Rhynoodle')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (62, 44, 9, N'Indigo', N'Linklinks')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (63, 86, 3, N'Crimson', N'Talane')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (64, 36, 10, N'Teal', N'Divavu')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (65, 11, 7, N'Pink', N'Chatterbridge')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (66, 100, 4, N'Violet', N'Aimbo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (67, 88, 5, N'Khaki', N'Digitube')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (68, 83, 7, N'Goldenrod', N'Jatri')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (69, 97, 8, N'Violet', N'Rhynyx')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (70, 12, 3, N'Aquamarine', N'Skivee')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (71, 40, 6, N'Yellow', N'Eamia')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (72, 41, 10, N'Aquamarine', N'Babbleblab')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (73, 43, 1, N'Yellow', N'Jabberstorm')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (74, 78, 9, N'Puce', N'Feedfire')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (75, 18, 4, N'Fuscia', N'Linklinks')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (76, 92, 7, N'Khaki', N'Demizz')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (77, 35, 8, N'Green', N'Ailane')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (78, 100, 3, N'Maroon', N'Rhynoodle')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (79, 26, 4, N'Green', N'Zoovu')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (80, 27, 10, N'Maroon', N'Livetube')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (81, 66, 1, N'Turquoise', N'Latz')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (82, 86, 6, N'Aquamarine', N'Vinte')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (83, 3, 8, N'Indigo', N'Brainbox')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (84, 19, 7, N'Aquamarine', N'Photobean')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (85, 5, 5, N'Purple', N'Flashspan')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (86, 96, 9, N'Orange', N'Quaxo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (87, 76, 1, N'Green', N'Latz')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (88, 57, 2, N'Goldenrod', N'Meejo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (89, 24, 7, N'Indigo', N'Eare')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (90, 99, 10, N'Khaki', N'Skyndu')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (91, 64, 10, N'Maroon', N'Vidoo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (92, 31, 5, N'Yellow', N'Riffpath')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (93, 29, 1, N'Turquoise', N'Yakidoo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (94, 18, 7, N'Puce', N'Jazzy')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (95, 86, 6, N'Blue', N'Kaymbo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (96, 88, 3, N'Pink', N'Yodel')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (97, 22, 8, N'Yellow', N'Gabvine')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (98, 21, 1, N'Puce', N'Jayo')
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (99, 41, 2, N'Red', N'Yakitri')
GO
INSERT [Edward].[Clothes] ([id], [IDseller], [idCategory], [des], [Name]) VALUES (100, 46, 10, N'Red', N'Eabox')
SET IDENTITY_INSERT [Edward].[Clothes] OFF
GO
SET IDENTITY_INSERT [Edward].[Clothes_Properties] ON 

INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (1, 53, N'S                                                 ', 13, 33.86)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (2, 69, N'M                                                 ', 54, 69.74)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (3, 19, N'M                                                 ', 22, 931.34)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (4, 6, N'XL                                                ', 19, 476.92)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (5, 65, N'M                                                 ', 21, 866.86)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (6, 6, N'2XL                                               ', 51, 20.75)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (7, 18, N'L                                                 ', 2, 209.03)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (8, 50, N'S                                                 ', 84, 757.49)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (9, 59, N'XS                                                ', 33, 189.3)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (10, 42, N'L                                                 ', 77, 76.81)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (11, 94, N'XL                                                ', 41, 496.08)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (12, 33, N'S                                                 ', 13, 756.11)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (13, 72, N'S                                                 ', 34, 608.21)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (14, 98, N'XL                                                ', 98, 519.84)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (15, 84, N'M                                                 ', 67, 463.43)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (16, 77, N'2XL                                               ', 30, 908.82)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (17, 96, N'2XL                                               ', 61, 948.49)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (18, 89, N'2XL                                               ', 58, 389.59)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (19, 50, N'M                                                 ', 74, 209.11)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (20, 51, N'S                                                 ', 69, 360.11)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (21, 47, N'3XL                                               ', 16, 238.43)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (22, 37, N'L                                                 ', 61, 584.33)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (23, 99, N'S                                                 ', 23, 876.85)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (24, 1, N'2XL                                               ', 91, 391.44)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (25, 19, N'XL                                                ', 72, 432.48)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (26, 44, N'2XL                                               ', 76, 893.11)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (27, 89, N'M                                                 ', 20, 965.2)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (28, 62, N'2XL                                               ', 4, 625)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (29, 27, N'XS                                                ', 72, 152.53)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (30, 35, N'M                                                 ', 73, 465.74)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (31, 16, N'3XL                                               ', 94, 903.34)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (32, 81, N'XL                                                ', 86, 668.09)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (33, 79, N'L                                                 ', 55, 412.16)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (34, 71, N'L                                                 ', 43, 904.82)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (35, 3, N'XL                                                ', 100, 810.97)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (36, 98, N'M                                                 ', 10, 996.51)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (37, 14, N'XS                                                ', 6, 205.57)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (38, 50, N'L                                                 ', 22, 224.22)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (39, 16, N'L                                                 ', 60, 104.48)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (40, 13, N'2XL                                               ', 98, 797.33)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (41, 95, N'3XL                                               ', 67, 41.18)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (42, 37, N'2XL                                               ', 8, 998.84)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (43, 75, N'XS                                                ', 20, 880.78)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (44, 9, N'2XL                                               ', 73, 134.64)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (45, 33, N'XS                                                ', 53, 485.04)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (46, 14, N'S                                                 ', 40, 83.94)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (47, 48, N'L                                                 ', 47, 676.63)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (48, 42, N'L                                                 ', 45, 802.78)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (49, 37, N'M                                                 ', 72, 133.27)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (50, 77, N'XL                                                ', 28, 693.54)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (51, 32, N'M                                                 ', 52, 227.13)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (52, 34, N'XS                                                ', 97, 32.99)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (53, 44, N'XL                                                ', 74, 337.71)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (54, 79, N'L                                                 ', 90, 405.3)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (55, 5, N'XS                                                ', 72, 553.71)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (56, 3, N'L                                                 ', 10, 711.64)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (57, 57, N'2XL                                               ', 30, 850.18)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (58, 28, N'XS                                                ', 56, 764.57)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (59, 26, N'L                                                 ', 88, 646)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (60, 100, N'M                                                 ', 6, 814.04)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (61, 43, N'L                                                 ', 81, 146.86)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (62, 89, N'XL                                                ', 63, 364.84)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (63, 17, N'XL                                                ', 97, 62.5)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (64, 70, N'S                                                 ', 82, 148.52)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (65, 96, N'S                                                 ', 95, 551.95)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (66, 55, N'L                                                 ', 29, 771.43)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (67, 9, N'S                                                 ', 88, 39.71)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (68, 27, N'S                                                 ', 83, 916.82)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (69, 78, N'XL                                                ', 81, 747.57)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (70, 24, N'3XL                                               ', 99, 178.32)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (71, 49, N'XL                                                ', 68, 572.61)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (72, 72, N'XL                                                ', 83, 931.49)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (73, 58, N'3XL                                               ', 32, 48.51)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (74, 48, N'L                                                 ', 60, 484.76)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (75, 31, N'3XL                                               ', 60, 93.41)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (76, 2, N'L                                                 ', 48, 684.76)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (77, 5, N'XL                                                ', 40, 957.04)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (78, 45, N'M                                                 ', 75, 185.76)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (79, 43, N'XS                                                ', 68, 457.62)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (80, 71, N'XS                                                ', 84, 900.84)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (81, 44, N'L                                                 ', 58, 68.72)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (82, 99, N'S                                                 ', 100, 516.59)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (83, 77, N'S                                                 ', 70, 841.87)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (84, 12, N'S                                                 ', 9, 615.98)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (85, 46, N'L                                                 ', 85, 155.67)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (86, 94, N'2XL                                               ', 47, 263.31)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (87, 54, N'XL                                                ', 29, 644.7)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (88, 62, N'XL                                                ', 79, 939.35)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (89, 49, N'3XL                                               ', 81, 57.3)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (90, 37, N'S                                                 ', 57, 671.2)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (91, 93, N'XL                                                ', 94, 667.88)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (92, 32, N'XS                                                ', 62, 609.52)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (93, 8, N'3XL                                               ', 37, 579.22)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (94, 2, N'L                                                 ', 21, 957.43)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (95, 52, N'2XL                                               ', 62, 485.34)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (96, 60, N'2XL                                               ', 67, 844.1)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (97, 85, N'M                                                 ', 47, 109.32)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (98, 82, N'3XL                                               ', 57, 136)
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (99, 75, N'XL                                                ', 21, 337.58)
GO
INSERT [Edward].[Clothes_Properties] ([ID], [IDClothes], [size], [Quantily], [Price]) VALUES (100, 91, N'M                                                 ', 73, 143.3)
SET IDENTITY_INSERT [Edward].[Clothes_Properties] OFF
GO
SET IDENTITY_INSERT [Edward].[Favorites] ON 

INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (1, 24, 81)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (2, 95, 71)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (3, 86, 38)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (4, 14, 3)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (5, 48, 5)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (6, 15, 3)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (7, 12, 97)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (8, 79, 61)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (9, 90, 52)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (10, 44, 56)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (11, 1, 69)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (12, 98, 22)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (13, 87, 80)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (14, 8, 28)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (15, 91, 26)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (16, 97, 46)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (17, 50, 43)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (18, 33, 27)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (19, 100, 30)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (20, 4, 59)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (21, 1, 58)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (22, 84, 75)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (23, 4, 30)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (24, 94, 93)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (25, 98, 91)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (26, 60, 15)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (27, 99, 50)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (28, 34, 8)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (29, 90, 11)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (30, 49, 2)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (31, 73, 96)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (32, 66, 34)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (33, 31, 74)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (34, 80, 87)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (35, 85, 29)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (36, 87, 27)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (37, 41, 74)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (38, 31, 87)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (39, 72, 90)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (40, 13, 50)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (41, 33, 18)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (42, 19, 77)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (43, 84, 45)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (44, 42, 31)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (45, 27, 90)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (46, 63, 21)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (47, 24, 66)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (48, 31, 90)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (49, 55, 78)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (50, 33, 19)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (51, 2, 65)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (52, 64, 37)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (53, 90, 12)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (54, 61, 69)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (55, 41, 78)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (56, 42, 60)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (57, 58, 84)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (58, 28, 64)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (59, 8, 43)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (60, 15, 13)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (61, 37, 76)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (62, 57, 65)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (63, 49, 95)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (64, 51, 81)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (65, 68, 50)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (66, 30, 43)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (67, 47, 67)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (68, 92, 98)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (69, 89, 3)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (70, 58, 35)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (71, 71, 9)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (72, 4, 5)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (73, 16, 79)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (74, 64, 15)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (75, 94, 53)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (76, 48, 99)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (77, 98, 81)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (78, 84, 26)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (79, 64, 52)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (80, 11, 7)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (81, 58, 44)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (82, 25, 85)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (83, 75, 88)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (84, 60, 48)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (85, 34, 51)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (86, 20, 87)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (87, 26, 84)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (88, 39, 38)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (89, 89, 53)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (90, 32, 98)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (91, 61, 79)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (92, 85, 23)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (93, 92, 56)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (94, 25, 22)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (95, 26, 72)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (96, 87, 96)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (97, 73, 44)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (98, 20, 60)
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (99, 86, 58)
GO
INSERT [Edward].[Favorites] ([ID], [IDClothes], [IDUser]) VALUES (100, 39, 29)
SET IDENTITY_INSERT [Edward].[Favorites] OFF
GO
SET IDENTITY_INSERT [Edward].[ImgUrls] ON 

INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (1, 52, N'http://dummyimage.com/161x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (2, 64, N'http://dummyimage.com/129x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (3, 18, N'http://dummyimage.com/160x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (4, 99, N'http://dummyimage.com/169x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (5, 98, N'http://dummyimage.com/118x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (6, 60, N'http://dummyimage.com/168x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (7, 100, N'http://dummyimage.com/161x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (8, 8, N'http://dummyimage.com/104x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (9, 34, N'http://dummyimage.com/185x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (10, 48, N'http://dummyimage.com/172x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (11, 66, N'http://dummyimage.com/236x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (12, 87, N'http://dummyimage.com/168x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (13, 30, N'http://dummyimage.com/184x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (14, 2, N'http://dummyimage.com/172x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (15, 31, N'http://dummyimage.com/231x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (16, 69, N'http://dummyimage.com/188x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (17, 33, N'http://dummyimage.com/200x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (18, 25, N'http://dummyimage.com/112x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (19, 67, N'http://dummyimage.com/243x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (20, 97, N'http://dummyimage.com/211x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (21, 100, N'http://dummyimage.com/210x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (22, 22, N'http://dummyimage.com/168x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (23, 70, N'http://dummyimage.com/113x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (24, 46, N'http://dummyimage.com/110x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (25, 74, N'http://dummyimage.com/249x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (26, 67, N'http://dummyimage.com/177x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (27, 88, N'http://dummyimage.com/139x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (28, 51, N'http://dummyimage.com/202x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (29, 58, N'http://dummyimage.com/183x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (30, 7, N'http://dummyimage.com/146x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (31, 91, N'http://dummyimage.com/208x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (32, 31, N'http://dummyimage.com/158x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (33, 63, N'http://dummyimage.com/199x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (34, 4, N'http://dummyimage.com/202x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (35, 94, N'http://dummyimage.com/164x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (36, 65, N'http://dummyimage.com/245x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (37, 10, N'http://dummyimage.com/210x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (38, 41, N'http://dummyimage.com/157x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (39, 88, N'http://dummyimage.com/136x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (40, 38, N'http://dummyimage.com/180x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (41, 61, N'http://dummyimage.com/118x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (42, 88, N'http://dummyimage.com/215x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (43, 48, N'http://dummyimage.com/135x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (44, 80, N'http://dummyimage.com/103x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (45, 67, N'http://dummyimage.com/182x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (46, 79, N'http://dummyimage.com/124x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (47, 65, N'http://dummyimage.com/105x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (48, 28, N'http://dummyimage.com/117x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (49, 29, N'http://dummyimage.com/130x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (50, 18, N'http://dummyimage.com/225x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (51, 31, N'http://dummyimage.com/240x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (52, 23, N'http://dummyimage.com/234x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (53, 24, N'http://dummyimage.com/176x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (54, 27, N'http://dummyimage.com/131x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (55, 50, N'http://dummyimage.com/215x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (56, 86, N'http://dummyimage.com/181x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (57, 50, N'http://dummyimage.com/150x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (58, 58, N'http://dummyimage.com/104x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (59, 77, N'http://dummyimage.com/196x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (60, 95, N'http://dummyimage.com/177x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (61, 42, N'http://dummyimage.com/162x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (62, 22, N'http://dummyimage.com/136x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (63, 58, N'http://dummyimage.com/144x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (64, 33, N'http://dummyimage.com/183x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (65, 93, N'http://dummyimage.com/232x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (66, 51, N'http://dummyimage.com/160x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (67, 10, N'http://dummyimage.com/154x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (68, 28, N'http://dummyimage.com/178x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (69, 62, N'http://dummyimage.com/214x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (70, 84, N'http://dummyimage.com/158x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (71, 26, N'http://dummyimage.com/186x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (72, 40, N'http://dummyimage.com/207x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (73, 88, N'http://dummyimage.com/127x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (74, 89, N'http://dummyimage.com/217x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (75, 11, N'http://dummyimage.com/184x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (76, 100, N'http://dummyimage.com/108x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (77, 13, N'http://dummyimage.com/242x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (78, 75, N'http://dummyimage.com/132x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (79, 66, N'http://dummyimage.com/180x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (80, 57, N'http://dummyimage.com/200x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (81, 84, N'http://dummyimage.com/148x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (82, 76, N'http://dummyimage.com/164x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (83, 57, N'http://dummyimage.com/245x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (84, 77, N'http://dummyimage.com/241x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (85, 95, N'http://dummyimage.com/205x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (86, 68, N'http://dummyimage.com/109x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (87, 7, N'http://dummyimage.com/203x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (88, 11, N'http://dummyimage.com/113x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (89, 30, N'http://dummyimage.com/227x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (90, 14, N'http://dummyimage.com/217x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (91, 38, N'http://dummyimage.com/128x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (92, 11, N'http://dummyimage.com/180x100.png/dddddd/000000')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (93, 57, N'http://dummyimage.com/209x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (94, 71, N'http://dummyimage.com/216x100.png/5fa2dd/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (95, 14, N'http://dummyimage.com/124x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (96, 51, N'http://dummyimage.com/196x100.png/ff4444/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (97, 63, N'http://dummyimage.com/155x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (98, 27, N'http://dummyimage.com/151x100.png/cc0000/ffffff')
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (99, 30, N'http://dummyimage.com/206x100.png/5fa2dd/ffffff')
GO
INSERT [Edward].[ImgUrls] ([ID], [IDClothes], [ImgUrl]) VALUES (100, 87, N'http://dummyimage.com/180x100.png/cc0000/ffffff')
SET IDENTITY_INSERT [Edward].[ImgUrls] OFF
GO
SET IDENTITY_INSERT [Edward].[People] ON 

INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (1, N'Lin Lardiner                                      ', N'llardiner0@friendfeed.com                         ', N'6XbKIQP                                                                                                                                                                                                                                                        ', N'+86 (351) 313-3780  ', 1, N'http://dummyimage.com/154x100.png/5fa2dd/ffffff', N'79 Montana Trail                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (2, N'Jesselyn Schouthede                               ', N'jschouthede1@moonfruit.com                        ', N'UvBQR3P3e75                                                                                                                                                                                                                                                    ', N'+86 (446) 996-1785  ', 2, N'http://dummyimage.com/112x100.png/cc0000/ffffff', N'29 Dixon Drive                                    ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (3, N'Paulo Butters                                     ', N'pbutters2@adobe.com                               ', N'az9VtYKemaX                                                                                                                                                                                                                                                    ', N'+1 (501) 885-8197   ', 3, N'http://dummyimage.com/120x100.png/ff4444/ffffff', N'52012 Carpenter Park                              ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (4, N'Aindrea Kitney                                    ', N'akitney3@slideshare.net                           ', N'0Gr5GWy                                                                                                                                                                                                                                                        ', N'+82 (705) 649-4456  ', 2, N'http://dummyimage.com/131x100.png/5fa2dd/ffffff', N'08937 Sage Park                                   ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (5, N'Tarra Aldcorn                                     ', N'taldcorn4@ning.com                                ', N'WmlafHa7HR                                                                                                                                                                                                                                                     ', N'+86 (879) 593-9941  ', 3, N'http://dummyimage.com/250x100.png/5fa2dd/ffffff', N'78365 Hansons Point                               ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (6, N'Munroe Sharma                                     ', N'msharma5@tumblr.com                               ', N'1PeGdpoRC                                                                                                                                                                                                                                                      ', N'+66 (570) 385-1174  ', 3, N'http://dummyimage.com/248x100.png/dddddd/000000', N'101 Linden Road                                   ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (7, N'Robby Cars                                        ', N'rcars6@trellian.com                               ', N'teXns69B                                                                                                                                                                                                                                                       ', N'+351 (276) 240-2342 ', 2, N'http://dummyimage.com/188x100.png/dddddd/000000', N'9201 Commercial Street                            ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (8, N'Rad Passby                                        ', N'rpassby7@europa.eu                                ', N'aaZqy2Rfo0B7                                                                                                                                                                                                                                                   ', N'+86 (302) 186-5825  ', 3, N'http://dummyimage.com/120x100.png/ff4444/ffffff', N'0 Dawn Pass                                       ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (9, N'Nikolia Blizard                                   ', N'nblizard8@washington.edu                          ', N'JaQQxXWaNK                                                                                                                                                                                                                                                     ', N'+963 (681) 694-8333 ', 2, N'http://dummyimage.com/227x100.png/ff4444/ffffff', N'0 Birchwood Pass                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (10, N'Andonis Van der Mark                              ', N'avan9@dmoz.org                                    ', N'dvkR43Xh4                                                                                                                                                                                                                                                      ', N'+62 (985) 755-7345  ', 1, N'http://dummyimage.com/130x100.png/ff4444/ffffff', N'13 Gale Trail                                     ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (11, N'Marketa Thomann                                   ', N'mthomanna@miibeian.gov.cn                         ', N'cD1YgEoG9nE                                                                                                                                                                                                                                                    ', N'+48 (938) 555-5537  ', 1, N'http://dummyimage.com/237x100.png/5fa2dd/ffffff', N'9300 Tennessee Crossing                           ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (12, N'Klara Espinha                                     ', N'kespinhab@uol.com.br                              ', N'Gd3bNia9DMc0                                                                                                                                                                                                                                                   ', N'+66 (387) 657-0351  ', 3, N'http://dummyimage.com/165x100.png/5fa2dd/ffffff', N'4388 Vidon Pass                                   ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (13, N'Floria Sharpe                                     ', N'fsharpec@ycombinator.com                          ', N'kEq7GfyqQh                                                                                                                                                                                                                                                     ', N'+218 (502) 340-6144 ', 3, N'http://dummyimage.com/196x100.png/5fa2dd/ffffff', N'04222 Granby Avenue                               ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (14, N'Ely Slaten                                        ', N'eslatend@drupal.org                               ', N'ZTwMq4                                                                                                                                                                                                                                                         ', N'+62 (370) 842-7984  ', 3, N'http://dummyimage.com/215x100.png/5fa2dd/ffffff', N'95206 Hooker Parkway                              ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (15, N'Charity Macey                                     ', N'cmaceye@youtube.com                               ', N'ApOgpj9pOez                                                                                                                                                                                                                                                    ', N'+1 (204) 639-8454   ', 2, N'http://dummyimage.com/137x100.png/dddddd/000000', N'24409 Lukken Parkway                              ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (16, N'Letizia Manolov                                   ', N'lmanolovf@bbc.co.uk                               ', N'YdM2yR                                                                                                                                                                                                                                                         ', N'+55 (390) 519-7797  ', 1, N'http://dummyimage.com/108x100.png/ff4444/ffffff', N'23951 Kedzie Park                                 ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (17, N'Hadria Morritt                                    ', N'hmorrittg@nytimes.com                             ', N'QlamfPS                                                                                                                                                                                                                                                        ', N'+242 (330) 509-2564 ', 2, N'http://dummyimage.com/137x100.png/ff4444/ffffff', N'577 Old Shore Way                                 ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (18, N'Giacomo Heineking                                 ', N'gheinekingh@ycombinator.com                       ', N'lXeEjjXKUIX                                                                                                                                                                                                                                                    ', N'+51 (618) 977-3332  ', 2, N'http://dummyimage.com/174x100.png/cc0000/ffffff', N'9913 Hagan Court                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (19, N'Tomasine Hanaford                                 ', N'thanafordi@free.fr                                ', N'QOmRH6                                                                                                                                                                                                                                                         ', N'+86 (287) 970-7671  ', 3, N'http://dummyimage.com/156x100.png/5fa2dd/ffffff', N'3563 Hoard Avenue                                 ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (20, N'Davita Payler                                     ', N'dpaylerj@amazon.com                               ', N'ge9lCrxc6                                                                                                                                                                                                                                                      ', N'+54 (316) 475-9913  ', 3, N'http://dummyimage.com/178x100.png/ff4444/ffffff', N'92078 Messerschmidt Circle                        ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (21, N'Tanner Yurkevich                                  ', N'tyurkevichk@apache.org                            ', N'HFzEVY                                                                                                                                                                                                                                                         ', N'+359 (322) 393-3981 ', 3, N'http://dummyimage.com/135x100.png/ff4444/ffffff', N'19 Katie Place                                    ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (22, N'Connie Leggat                                     ', N'cleggatl@drupal.org                               ', N'jqVBxm                                                                                                                                                                                                                                                         ', N'+86 (113) 320-7361  ', 3, N'http://dummyimage.com/130x100.png/dddddd/000000', N'078 Helena Street                                 ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (23, N'Tanner Myerscough                                 ', N'tmyerscoughm@gnu.org                              ', N'MGNGXiDq                                                                                                                                                                                                                                                       ', N'+62 (104) 962-8582  ', 3, N'http://dummyimage.com/115x100.png/ff4444/ffffff', N'3 Atwood Hill                                     ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (24, N'Merry Kloska                                      ', N'mkloskan@nhs.uk                                   ', N'ra7uaq                                                                                                                                                                                                                                                         ', N'+1 (801) 199-8848   ', 1, N'http://dummyimage.com/198x100.png/ff4444/ffffff', N'3 Hoepker Park                                    ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (25, N'Bran Pretswell                                    ', N'bpretswello@mapquest.com                          ', N'QlAif6hdzPjB                                                                                                                                                                                                                                                   ', N'+46 (164) 658-9620  ', 2, N'http://dummyimage.com/154x100.png/ff4444/ffffff', N'51 Graceland Parkway                              ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (26, N'Alie Skoughman                                    ', N'askoughmanp@mozilla.com                           ', N'69HLUS                                                                                                                                                                                                                                                         ', N'+92 (180) 544-5612  ', 2, N'http://dummyimage.com/163x100.png/5fa2dd/ffffff', N'3 Green Ridge Trail                               ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (27, N'Lara Vedmore                                      ', N'lvedmoreq@friendfeed.com                          ', N'swbhAo                                                                                                                                                                                                                                                         ', N'+371 (358) 644-2462 ', 1, N'http://dummyimage.com/220x100.png/ff4444/ffffff', N'7 Barby Road                                      ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (28, N'Beau Screech                                      ', N'bscreechr@phpbb.com                               ', N'5iNBwLoXkQwE                                                                                                                                                                                                                                                   ', N'+212 (120) 254-0363 ', 1, N'http://dummyimage.com/137x100.png/ff4444/ffffff', N'18 Vera Hill                                      ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (29, N'Nicolea Hanks                                     ', N'nhankss@etsy.com                                  ', N'oVb6Pf                                                                                                                                                                                                                                                         ', N'+62 (914) 975-9481  ', 2, N'http://dummyimage.com/191x100.png/ff4444/ffffff', N'5 Mifflin Point                                   ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (30, N'Ettie Adamkiewicz                                 ', N'eadamkiewiczt@washington.edu                      ', N'3VOjZVX8ZC6                                                                                                                                                                                                                                                    ', N'+86 (414) 955-6038  ', 2, N'http://dummyimage.com/140x100.png/5fa2dd/ffffff', N'21 Ridgeview Street                               ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (31, N'Daisie Creeghan                                   ', N'dcreeghanu@devhub.com                             ', N'pxyUFcpWZp                                                                                                                                                                                                                                                     ', N'+31 (125) 257-0021  ', 3, N'http://dummyimage.com/211x100.png/dddddd/000000', N'671 Express Drive                                 ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (32, N'Alanah Pinke                                      ', N'apinkev@ehow.com                                  ', N'O3hTgJnPP                                                                                                                                                                                                                                                      ', N'+48 (888) 313-9963  ', 1, N'http://dummyimage.com/107x100.png/cc0000/ffffff', N'32535 Hazelcrest Crossing                         ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (33, N'Dennie Poone                                      ', N'dpoonew@123-reg.co.uk                             ', N'1RQIvhxfnT                                                                                                                                                                                                                                                     ', N'+86 (825) 650-4325  ', 1, N'http://dummyimage.com/244x100.png/cc0000/ffffff', N'9991 Hollow Ridge Way                             ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (34, N'Jeanie Gravells                                   ', N'jgravellsx@wikimedia.org                          ', N'qSdhkgt3EF7O                                                                                                                                                                                                                                                   ', N'+355 (493) 424-7934 ', 3, N'http://dummyimage.com/210x100.png/ff4444/ffffff', N'31 Eagan Alley                                    ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (35, N'Ellyn Gentiry                                     ', N'egentiryy@linkedin.com                            ', N'9ssFeZWIp251                                                                                                                                                                                                                                                   ', N'+55 (303) 313-3627  ', 2, N'http://dummyimage.com/111x100.png/5fa2dd/ffffff', N'5 Dorton Park                                     ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (36, N'Rakel Clarson                                     ', N'rclarsonz@yellowbook.com                          ', N'jC5U2i0P8q                                                                                                                                                                                                                                                     ', N'+62 (555) 245-3349  ', 3, N'http://dummyimage.com/200x100.png/dddddd/000000', N'729 Fisk Trail                                    ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (37, N'Kennett Akhurst                                   ', N'kakhurst10@phpbb.com                              ', N'X9LiRfZr                                                                                                                                                                                                                                                       ', N'+964 (989) 311-4900 ', 1, N'http://dummyimage.com/169x100.png/cc0000/ffffff', N'6679 Mifflin Terrace                              ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (38, N'Domini Hylton                                     ', N'dhylton11@miitbeian.gov.cn                        ', N'eo0S9Gg5bHhh                                                                                                                                                                                                                                                   ', N'+62 (464) 366-8241  ', 1, N'http://dummyimage.com/168x100.png/dddddd/000000', N'35 Westport Way                                   ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (39, N'Junie Roelvink                                    ', N'jroelvink12@istockphoto.com                       ', N'kXW1D2                                                                                                                                                                                                                                                         ', N'+66 (436) 817-1347  ', 2, N'http://dummyimage.com/237x100.png/dddddd/000000', N'8 Montana Street                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (40, N'Nan Raiker                                        ', N'nraiker13@skyrock.com                             ', N'2UBPB34ZI                                                                                                                                                                                                                                                      ', N'+63 (109) 454-2773  ', 3, N'http://dummyimage.com/227x100.png/5fa2dd/ffffff', N'7 Twin Pines Pass                                 ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (41, N'Georgie Brayley                                   ', N'gbrayley14@indiegogo.com                          ', N'rFf98c                                                                                                                                                                                                                                                         ', N'+232 (763) 550-2248 ', 1, N'http://dummyimage.com/195x100.png/cc0000/ffffff', N'478 Heffernan Point                               ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (42, N'Darbee Hellwing                                   ', N'dhellwing15@blinklist.com                         ', N'tl2X9Z                                                                                                                                                                                                                                                         ', N'+967 (400) 589-5733 ', 1, N'http://dummyimage.com/175x100.png/cc0000/ffffff', N'93 Bluestem Street                                ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (43, N'Estelle Godier                                    ', N'egodier16@webs.com                                ', N'aMKSKCcq                                                                                                                                                                                                                                                       ', N'+420 (618) 662-4280 ', 2, N'http://dummyimage.com/205x100.png/dddddd/000000', N'4600 Karstens Road                                ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (44, N'Christan Isakovic                                 ', N'cisakovic17@businesswire.com                      ', N'PjkSpVXCX                                                                                                                                                                                                                                                      ', N'+1 (932) 277-9617   ', 3, N'http://dummyimage.com/163x100.png/dddddd/000000', N'227 Spaight Way                                   ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (45, N'Morganica Wardel                                  ', N'mwardel18@hibu.com                                ', N'v6P4oPCyk3                                                                                                                                                                                                                                                     ', N'+30 (218) 257-7354  ', 2, N'http://dummyimage.com/149x100.png/ff4444/ffffff', N'4 Iowa Avenue                                     ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (46, N'Barthel Bardill                                   ', N'bbardill19@com.com                                ', N'fy8XuHSZAhX                                                                                                                                                                                                                                                    ', N'+62 (247) 115-1652  ', 3, N'http://dummyimage.com/141x100.png/5fa2dd/ffffff', N'6871 Blackbird Lane                               ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (47, N'Norah Sings                                       ', N'nsings1a@drupal.org                               ', N'TpwHlNuraa24                                                                                                                                                                                                                                                   ', N'+30 (710) 741-2014  ', 1, N'http://dummyimage.com/235x100.png/dddddd/000000', N'828 Commercial Way                                ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (48, N'Wait Friskey                                      ', N'wfriskey1b@reuters.com                            ', N'ezgHFy1D                                                                                                                                                                                                                                                       ', N'+86 (634) 571-6038  ', 3, N'http://dummyimage.com/189x100.png/dddddd/000000', N'0 Birchwood Court                                 ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (49, N'Dunstan Leitche                                   ', N'dleitche1c@icq.com                                ', N'oHcnUMjwu                                                                                                                                                                                                                                                      ', N'+46 (485) 392-5801  ', 3, N'http://dummyimage.com/243x100.png/ff4444/ffffff', N'2 Mendota Place                                   ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (50, N'Thain Brecknall                                   ', N'tbrecknall1d@unblog.fr                            ', N'XzEKDT0omgs                                                                                                                                                                                                                                                    ', N'+86 (547) 327-4679  ', 2, N'http://dummyimage.com/193x100.png/dddddd/000000', N'1 Bellgrove Lane                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (51, N'Loretta Jerke                                     ', N'ljerke1e@dailymotion.com                          ', N't7GwZh0                                                                                                                                                                                                                                                        ', N'+62 (273) 714-1284  ', 3, N'http://dummyimage.com/112x100.png/5fa2dd/ffffff', N'4 Kim Street                                      ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (52, N'Caterina Jennery                                  ', N'cjennery1f@icq.com                                ', N'pQZ9rDRY                                                                                                                                                                                                                                                       ', N'+57 (535) 378-7452  ', 2, N'http://dummyimage.com/102x100.png/ff4444/ffffff', N'40610 Northridge Street                           ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (53, N'Franzen Rosenfrucht                               ', N'frosenfrucht1g@stumbleupon.com                    ', N'teGd5USa9omX                                                                                                                                                                                                                                                   ', N'+996 (229) 444-9008 ', 3, N'http://dummyimage.com/114x100.png/cc0000/ffffff', N'38939 Rusk Drive                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (54, N'Rodney Abercrombie                                ', N'rabercrombie1h@webeden.co.uk                      ', N'mKkD5Kg68                                                                                                                                                                                                                                                      ', N'+7 (744) 823-4967   ', 1, N'http://dummyimage.com/164x100.png/dddddd/000000', N'172 Cottonwood Trail                              ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (55, N'Marney Kopelman                                   ', N'mkopelman1i@bravesites.com                        ', N'bdoztKtv3NpW                                                                                                                                                                                                                                                   ', N'+351 (495) 394-0144 ', 3, N'http://dummyimage.com/219x100.png/ff4444/ffffff', N'112 Spaight Lane                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (56, N'Travus Purcell                                    ', N'tpurcell1j@drupal.org                             ', N'7gpHp5xP5Rm                                                                                                                                                                                                                                                    ', N'+62 (440) 717-4408  ', 2, N'http://dummyimage.com/136x100.png/dddddd/000000', N'1 Upham Place                                     ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (57, N'Francis Turnpenny                                 ', N'fturnpenny1k@auda.org.au                          ', N'fKHfjs7vlB                                                                                                                                                                                                                                                     ', N'+354 (612) 425-7376 ', 1, N'http://dummyimage.com/137x100.png/5fa2dd/ffffff', N'92445 Mosinee Drive                               ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (58, N'Ginevra Hamments                                  ', N'ghamments1l@exblog.jp                             ', N'IlO9camqC7dL                                                                                                                                                                                                                                                   ', N'+380 (373) 751-2971 ', 1, N'http://dummyimage.com/115x100.png/5fa2dd/ffffff', N'21 Pine View Parkway                              ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (59, N'Renado Alexsandrowicz                             ', N'ralexsandrowicz1m@domainmarket.com                ', N'E0UBsSzi                                                                                                                                                                                                                                                       ', N'+86 (801) 575-4063  ', 3, N'http://dummyimage.com/126x100.png/5fa2dd/ffffff', N'9 Blackbird Pass                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (60, N'Cristina McColl                                   ', N'cmccoll1n@multiply.com                            ', N'Um9QlKu3aQd                                                                                                                                                                                                                                                    ', N'+47 (707) 256-5354  ', 2, N'http://dummyimage.com/146x100.png/dddddd/000000', N'5108 Muir Terrace                                 ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (61, N'Maury Surpliss                                    ', N'msurpliss1o@tuttocitta.it                         ', N'IAcvzdq                                                                                                                                                                                                                                                        ', N'+86 (384) 699-1756  ', 1, N'http://dummyimage.com/140x100.png/dddddd/000000', N'72 Carberry Lane                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (62, N'Lorin Dumphy                                      ', N'ldumphy1p@slideshare.net                          ', N'VdE3GExPUF                                                                                                                                                                                                                                                     ', N'+57 (351) 615-1932  ', 1, N'http://dummyimage.com/250x100.png/dddddd/000000', N'03 Montana Place                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (63, N'Gannon Eckersall                                  ', N'geckersall1q@altervista.org                       ', N'hrk1zYPKaHG                                                                                                                                                                                                                                                    ', N'+353 (585) 740-4508 ', 3, N'http://dummyimage.com/100x100.png/dddddd/000000', N'20 Norway Maple Terrace                           ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (64, N'Lenna Marston                                     ', N'lmarston1r@sfgate.com                             ', N'Mj7TJM                                                                                                                                                                                                                                                         ', N'+63 (519) 456-8516  ', 1, N'http://dummyimage.com/250x100.png/dddddd/000000', N'2 Autumn Leaf Plaza                               ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (65, N'Giffie O''Sesnane                                  ', N'gosesnane1s@msn.com                               ', N'PyeHRyVRed                                                                                                                                                                                                                                                     ', N'+593 (657) 375-0407 ', 1, N'http://dummyimage.com/250x100.png/5fa2dd/ffffff', N'17882 Buell Road                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (66, N'Hewitt Taylor                                     ', N'htaylor1t@angelfire.com                           ', N'k9xk9utL                                                                                                                                                                                                                                                       ', N'+86 (118) 221-8500  ', 3, N'http://dummyimage.com/117x100.png/cc0000/ffffff', N'42085 Muir Parkway                                ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (67, N'Christophe Tutsell                                ', N'ctutsell1u@soundcloud.com                         ', N'dAq4Zcfo                                                                                                                                                                                                                                                       ', N'+55 (273) 169-1313  ', 1, N'http://dummyimage.com/203x100.png/dddddd/000000', N'88 Sachs Center                                   ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (68, N'Tobi McLurg                                       ', N'tmclurg1v@spotify.com                             ', N'Ng31mBV                                                                                                                                                                                                                                                        ', N'+62 (322) 703-1883  ', 2, N'http://dummyimage.com/232x100.png/ff4444/ffffff', N'8 Towne Trail                                     ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (69, N'Sharai Norwell                                    ', N'snorwell1w@forbes.com                             ', N'dN2aRryVH7t                                                                                                                                                                                                                                                    ', N'+86 (127) 145-8934  ', 1, N'http://dummyimage.com/142x100.png/cc0000/ffffff', N'14 Tony Circle                                    ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (70, N'Kessiah Higgen                                    ', N'khiggen1x@illinois.edu                            ', N'gAU9iaHpSjwx                                                                                                                                                                                                                                                   ', N'+237 (766) 665-4319 ', 3, N'http://dummyimage.com/195x100.png/5fa2dd/ffffff', N'3 Express Junction                                ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (71, N'Lizette Bartoszewski                              ', N'lbartoszewski1y@reference.com                     ', N'AaMxAC8KkTjY                                                                                                                                                                                                                                                   ', N'+55 (307) 515-6507  ', 1, N'http://dummyimage.com/136x100.png/cc0000/ffffff', N'478 Center Plaza                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (72, N'Weidar Sankey                                     ', N'wsankey1z@nba.com                                 ', N'5N3mu0XMzlwf                                                                                                                                                                                                                                                   ', N'+1 (813) 900-4820   ', 3, N'http://dummyimage.com/139x100.png/5fa2dd/ffffff', N'381 Rieder Hill                                   ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (73, N'Aridatha Metcalf                                  ', N'ametcalf20@friendfeed.com                         ', N'iSQ4OhdWXO2                                                                                                                                                                                                                                                    ', N'+504 (471) 725-2585 ', 3, N'http://dummyimage.com/240x100.png/cc0000/ffffff', N'3983 Maple Alley                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (74, N'Robbin Lidgey                                     ', N'rlidgey21@youtu.be                                ', N'JAvSu71                                                                                                                                                                                                                                                        ', N'+7 (619) 925-7243   ', 1, N'http://dummyimage.com/182x100.png/ff4444/ffffff', N'0431 Thierer Circle                               ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (75, N'Colly Borborough                                  ', N'cborborough22@weebly.com                          ', N'72vjxTp                                                                                                                                                                                                                                                        ', N'+62 (290) 996-9223  ', 1, N'http://dummyimage.com/218x100.png/dddddd/000000', N'15298 Oneill Parkway                              ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (76, N'Rosemarie Mountney                                ', N'rmountney23@si.edu                                ', N'TN05xefgK8G4                                                                                                                                                                                                                                                   ', N'+359 (816) 480-8750 ', 3, N'http://dummyimage.com/173x100.png/cc0000/ffffff', N'639 Riverside Road                                ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (77, N'Annemarie Hitscher                                ', N'ahitscher24@businessweek.com                      ', N'5sBo1P2Xg                                                                                                                                                                                                                                                      ', N'+48 (636) 951-4701  ', 1, N'http://dummyimage.com/102x100.png/dddddd/000000', N'843 Dovetail Avenue                               ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (78, N'Helena Brawson                                    ', N'hbrawson25@ibm.com                                ', N'aDTjXiY99eI                                                                                                                                                                                                                                                    ', N'+58 (207) 163-1726  ', 3, N'http://dummyimage.com/185x100.png/5fa2dd/ffffff', N'2 Surrey Parkway                                  ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (79, N'Logan Southouse                                   ', N'lsouthouse26@newsvine.com                         ', N'V9et02NGK                                                                                                                                                                                                                                                      ', N'+86 (865) 339-9056  ', 3, N'http://dummyimage.com/242x100.png/cc0000/ffffff', N'2 Rusk Parkway                                    ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (80, N'Jerrilee Libreros                                 ', N'jlibreros27@sciencedirect.com                     ', N'fi5i07                                                                                                                                                                                                                                                         ', N'+33 (709) 215-6940  ', 3, N'http://dummyimage.com/249x100.png/cc0000/ffffff', N'9 Shoshone Lane                                   ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (81, N'Viviyan Eliff                                     ', N'veliff28@cnbc.com                                 ', N'sGBd1rN                                                                                                                                                                                                                                                        ', N'+995 (350) 788-3122 ', 2, N'http://dummyimage.com/228x100.png/cc0000/ffffff', N'0 Warrior Park                                    ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (82, N'Lukas Mowsley                                     ', N'lmowsley29@mozilla.org                            ', N'PbcFCgs0                                                                                                                                                                                                                                                       ', N'+86 (926) 860-5309  ', 2, N'http://dummyimage.com/247x100.png/ff4444/ffffff', N'53103 Hanover Parkway                             ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (83, N'Adriana Lashmar                                   ', N'alashmar2a@huffingtonpost.com                     ', N'HKQPio27O                                                                                                                                                                                                                                                      ', N'+55 (603) 387-2078  ', 2, N'http://dummyimage.com/139x100.png/cc0000/ffffff', N'00 Grover Junction                                ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (84, N'Jerald Donegan                                    ', N'jdonegan2b@columbia.edu                           ', N'OH12OulEO                                                                                                                                                                                                                                                      ', N'+63 (638) 345-6441  ', 1, N'http://dummyimage.com/199x100.png/dddddd/000000', N'4 Golf Course Lane                                ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (85, N'Augy Bumfrey                                      ', N'abumfrey2c@icq.com                                ', N'O8aBfKyrvX                                                                                                                                                                                                                                                     ', N'+33 (591) 214-9582  ', 2, N'http://dummyimage.com/179x100.png/ff4444/ffffff', N'7 Steensland Court                                ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (86, N'Honey Burriss                                     ', N'hburriss2d@list-manage.com                        ', N'9vNq4oPK                                                                                                                                                                                                                                                       ', N'+86 (869) 224-6579  ', 1, N'http://dummyimage.com/188x100.png/ff4444/ffffff', N'25721 Everett Center                              ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (87, N'Jermayne MacMeeking                               ', N'jmacmeeking2e@zimbio.com                          ', N'ZWyyi6CQgm                                                                                                                                                                                                                                                     ', N'+86 (891) 558-9723  ', 2, N'http://dummyimage.com/219x100.png/5fa2dd/ffffff', N'04067 Kennedy Plaza                               ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (88, N'Sancho Doxsey                                     ', N'sdoxsey2f@webs.com                                ', N'2sAuMs                                                                                                                                                                                                                                                         ', N'+86 (831) 414-0508  ', 2, N'http://dummyimage.com/200x100.png/ff4444/ffffff', N'54 Debra Court                                    ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (89, N'Ari Gumey                                         ', N'agumey2g@themeforest.net                          ', N'JxmqJA                                                                                                                                                                                                                                                         ', N'+43 (179) 968-2257  ', 1, N'http://dummyimage.com/183x100.png/dddddd/000000', N'71 Fair Oaks Center                               ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (90, N'Gretal Greengrass                                 ', N'ggreengrass2h@zimbio.com                          ', N'AYtjoQyiQB0Q                                                                                                                                                                                                                                                   ', N'+1 (609) 772-0103   ', 3, N'http://dummyimage.com/243x100.png/ff4444/ffffff', N'7796 Kropf Road                                   ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (91, N'Rey Setter                                        ', N'rsetter2i@si.edu                                  ', N'qcbyTF9s8Qvr                                                                                                                                                                                                                                                   ', N'+62 (130) 317-8429  ', 2, N'http://dummyimage.com/223x100.png/dddddd/000000', N'03708 Ruskin Junction                             ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (92, N'Galvin Trebbett                                   ', N'gtrebbett2j@psu.edu                               ', N'SbDqtEmVfW                                                                                                                                                                                                                                                     ', N'+351 (407) 541-5327 ', 1, N'http://dummyimage.com/109x100.png/5fa2dd/ffffff', N'6 Barnett Terrace                                 ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (93, N'Gerladina Sutherley                               ', N'gsutherley2k@sourceforge.net                      ', N'NmuqqF6fLD                                                                                                                                                                                                                                                     ', N'+86 (945) 404-6410  ', 2, N'http://dummyimage.com/161x100.png/5fa2dd/ffffff', N'390 Sugar Alley                                   ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (94, N'Cross Rheubottom                                  ', N'crheubottom2l@biglobe.ne.jp                       ', N'Okln6hW                                                                                                                                                                                                                                                        ', N'+57 (989) 598-5607  ', 3, N'http://dummyimage.com/187x100.png/dddddd/000000', N'281 Elgar Pass                                    ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (95, N'Dorey Howroyd                                     ', N'dhowroyd2m@ifeng.com                              ', N'qOCwkghJw                                                                                                                                                                                                                                                      ', N'+1 (239) 106-7460   ', 1, N'http://dummyimage.com/180x100.png/cc0000/ffffff', N'9824 Marcy Parkway                                ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (96, N'Antonin McShane                                   ', N'amcshane2n@wordpress.com                          ', N'jMPt83X                                                                                                                                                                                                                                                        ', N'+51 (561) 434-2374  ', 1, N'http://dummyimage.com/209x100.png/ff4444/ffffff', N'00 Hayes Road                                     ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (97, N'Gottfried Loker                                   ', N'gloker2o@shop-pro.jp                              ', N'B88nKIAmX                                                                                                                                                                                                                                                      ', N'+98 (453) 812-9020  ', 2, N'http://dummyimage.com/111x100.png/dddddd/000000', N'99 7th Street                                     ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (98, N'Hetty Lanning                                     ', N'hlanning2p@list-manage.com                        ', N'aT7n8T                                                                                                                                                                                                                                                         ', N'+62 (970) 150-4391  ', 3, N'http://dummyimage.com/141x100.png/dddddd/000000', N'76643 Bluestem Place                              ')
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (99, N'Arin Lyles                                        ', N'alyles2q@baidu.com                                ', N'nXMVG7MVz                                                                                                                                                                                                                                                      ', N'+1 (358) 375-7332   ', 1, N'http://dummyimage.com/183x100.png/5fa2dd/ffffff', N'65715 6th Road                                    ')
GO
INSERT [Edward].[People] ([ID], [Name], [Mail], [Password], [PhoneNumber], [Role], [img], [Address]) VALUES (100, N'Raymond Henzer                                    ', N'rhenzer2r@mysql.com                               ', N'wrsVo97hzua                                                                                                                                                                                                                                                    ', N'+7 (302) 980-0889   ', 1, N'http://dummyimage.com/145x100.png/5fa2dd/ffffff', N'1472 Glacier Hill Park                            ')
SET IDENTITY_INSERT [Edward].[People] OFF
GO
SET IDENTITY_INSERT [Edward].[Voucher] ON 

INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (1, 15, 6)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (2, 93, 37)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (3, 1, 41)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (4, 14, 73)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (5, 71, 67)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (6, 67, 67)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (7, 94, 51)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (8, 59, 26)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (9, 46, 13)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (10, 50, 4)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (11, 41, 6)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (12, 11, 100)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (13, 1, 91)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (14, 80, 6)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (15, 65, 29)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (16, 17, 51)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (17, 49, 14)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (18, 45, 48)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (19, 32, 86)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (20, 53, 20)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (21, 67, 61)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (22, 52, 35)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (23, 42, 31)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (24, 79, 60)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (25, 15, 84)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (26, 68, 2)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (27, 11, 85)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (28, 33, 95)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (29, 35, 29)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (30, 52, 22)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (31, 18, 36)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (32, 55, 48)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (33, 9, 73)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (34, 73, 84)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (35, 17, 68)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (36, 23, 68)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (37, 73, 3)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (38, 75, 93)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (39, 32, 49)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (40, 47, 28)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (41, 40, 97)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (42, 58, 15)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (43, 61, 23)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (44, 38, 92)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (45, 32, 100)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (46, 75, 46)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (47, 6, 89)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (48, 77, 69)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (49, 3, 79)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (50, 61, 2)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (51, 88, 91)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (52, 24, 1)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (53, 63, 75)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (54, 82, 21)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (55, 54, 11)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (56, 37, 87)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (57, 73, 41)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (58, 6, 42)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (59, 56, 82)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (60, 65, 81)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (61, 67, 22)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (62, 15, 5)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (63, 78, 48)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (64, 59, 62)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (65, 33, 60)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (66, 20, 33)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (67, 24, 46)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (68, 35, 39)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (69, 67, 56)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (70, 84, 20)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (71, 26, 56)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (72, 97, 85)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (73, 5, 44)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (74, 75, 57)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (75, 46, 62)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (76, 34, 34)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (77, 10, 97)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (78, 27, 52)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (79, 48, 76)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (80, 95, 44)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (81, 60, 1)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (82, 90, 51)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (83, 25, 11)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (84, 68, 53)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (85, 7, 17)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (86, 84, 21)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (87, 32, 51)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (88, 87, 52)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (89, 6, 13)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (90, 3, 96)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (91, 2, 42)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (92, 58, 51)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (93, 25, 86)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (94, 42, 58)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (95, 38, 46)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (96, 47, 27)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (97, 83, 9)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (98, 79, 86)
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (99, 54, 55)
GO
INSERT [Edward].[Voucher] ([ID], [IDSeller], [ratio]) VALUES (100, 87, 77)
SET IDENTITY_INSERT [Edward].[Voucher] OFF
GO
ALTER TABLE [Edward].[Bill]  WITH CHECK ADD  CONSTRAINT [FK_Bill_Voucher] FOREIGN KEY([IDvoucher])
REFERENCES [Edward].[Voucher] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [Edward].[Bill] CHECK CONSTRAINT [FK_Bill_Voucher]
GO
ALTER TABLE [Edward].[BillDetail]  WITH CHECK ADD  CONSTRAINT [FK_BillDetail_Bill] FOREIGN KEY([IDBill])
REFERENCES [Edward].[Bill] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [Edward].[BillDetail] CHECK CONSTRAINT [FK_BillDetail_Bill]
GO
ALTER TABLE [Edward].[BillDetail]  WITH CHECK ADD  CONSTRAINT [FK_BillDetail_Clothes_Properties] FOREIGN KEY([IDClotheProperties])
REFERENCES [Edward].[Clothes_Properties] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [Edward].[BillDetail] CHECK CONSTRAINT [FK_BillDetail_Clothes_Properties]
GO
ALTER TABLE [Edward].[Clothes]  WITH CHECK ADD  CONSTRAINT [FK_Clothes_Category] FOREIGN KEY([idCategory])
REFERENCES [Edward].[Category] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [Edward].[Clothes] CHECK CONSTRAINT [FK_Clothes_Category]
GO
ALTER TABLE [Edward].[Clothes_Properties]  WITH CHECK ADD  CONSTRAINT [FK_Clothes_Properties_Clothes] FOREIGN KEY([IDClothes])
REFERENCES [Edward].[Clothes] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [Edward].[Clothes_Properties] CHECK CONSTRAINT [FK_Clothes_Properties_Clothes]
GO
ALTER TABLE [Edward].[Favorites]  WITH CHECK ADD  CONSTRAINT [FK_Favorites_Clothes] FOREIGN KEY([IDClothes])
REFERENCES [Edward].[Clothes] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [Edward].[Favorites] CHECK CONSTRAINT [FK_Favorites_Clothes]
GO
ALTER TABLE [Edward].[Favorites]  WITH CHECK ADD  CONSTRAINT [FK_Favorites_People] FOREIGN KEY([IDUser])
REFERENCES [Edward].[People] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [Edward].[Favorites] CHECK CONSTRAINT [FK_Favorites_People]
GO
ALTER TABLE [Edward].[ImgUrls]  WITH CHECK ADD  CONSTRAINT [FK_ImgUrls_Clothes] FOREIGN KEY([IDClothes])
REFERENCES [Edward].[Clothes] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [Edward].[ImgUrls] CHECK CONSTRAINT [FK_ImgUrls_Clothes]
GO
ALTER TABLE [Edward].[Voucher]  WITH CHECK ADD  CONSTRAINT [FK_Voucher_People] FOREIGN KEY([IDSeller])
REFERENCES [Edward].[People] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [Edward].[Voucher] CHECK CONSTRAINT [FK_Voucher_People]
GO
USE [master]
GO
ALTER DATABASE [EdwardEcomerce] SET  READ_WRITE 
GO
