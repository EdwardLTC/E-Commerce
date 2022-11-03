USE [master]
GO
/****** Object:  Database [EdwardEcomerce]    Script Date: 11/3/2022 9:29:53 PM ******/
CREATE DATABASE [EdwardEcomerce]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'EdwardEcomerce', FILENAME = N'D:\SQL\MSSQL15.SQLEXPRESS\MSSQL\DATA\EdwardEcomerce.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'EdwardEcomerce_log', FILENAME = N'D:\SQL\MSSQL15.SQLEXPRESS\MSSQL\DATA\EdwardEcomerce_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
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
/****** Object:  Schema [Edward]    Script Date: 11/3/2022 9:29:53 PM ******/
CREATE SCHEMA [Edward]
GO
/****** Object:  Schema [kynalabc]    Script Date: 11/3/2022 9:29:53 PM ******/
CREATE SCHEMA [kynalabc]
GO
/****** Object:  Table [dbo].[Clothes_Properties]    Script Date: 11/3/2022 9:29:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clothes_Properties](
	[ID] [int] NOT NULL,
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
/****** Object:  Table [dbo].[ImgUrls]    Script Date: 11/3/2022 9:29:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImgUrls](
	[ID] [int] NOT NULL,
	[IDClothes] [int] NULL,
	[ImgUrl] [nvarchar](256) NULL,
 CONSTRAINT [PK_ImgUrls] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Bill]    Script Date: 11/3/2022 9:29:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[Bill](
	[id] [int] NOT NULL,
	[IDUser] [int] NULL,
	[IDvoucher] [int] NULL,
	[DateCreate] [date] NULL,
	[DateReceived] [date] NULL,
	[Status] [nvarchar](50) NULL,
 CONSTRAINT [PK_Bill] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[BillDetail]    Script Date: 11/3/2022 9:29:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[BillDetail](
	[IDBill] [int] NOT NULL,
	[IDClothes] [int] NOT NULL,
 CONSTRAINT [PK_BillDetail] PRIMARY KEY CLUSTERED 
(
	[IDBill] ASC,
	[IDClothes] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Category]    Script Date: 11/3/2022 9:29:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[Category](
	[id] [int] NOT NULL,
	[Name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Clothes]    Script Date: 11/3/2022 9:29:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[Clothes](
	[id] [int] NOT NULL,
	[IDseller] [int] NULL,
	[idCategory] [int] NULL,
	[des] [nchar](10) NULL,
 CONSTRAINT [PK_Clothes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Edward].[Favorites]    Script Date: 11/3/2022 9:29:53 PM ******/
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
/****** Object:  Table [Edward].[People]    Script Date: 11/3/2022 9:29:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[People](
	[id] [int] NOT NULL,
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
/****** Object:  Table [Edward].[Voucher]    Script Date: 11/3/2022 9:29:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Edward].[Voucher](
	[ID] [int] NOT NULL,
	[IDSeller] [int] NULL,
	[ratio] [int] NULL,
 CONSTRAINT [PK_Voucher] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Clothes_Properties]  WITH CHECK ADD  CONSTRAINT [FK_Clothes_Properties_Clothes] FOREIGN KEY([ID])
REFERENCES [Edward].[Clothes] ([id])
GO
ALTER TABLE [dbo].[Clothes_Properties] CHECK CONSTRAINT [FK_Clothes_Properties_Clothes]
GO
ALTER TABLE [dbo].[ImgUrls]  WITH CHECK ADD  CONSTRAINT [FK_ImgUrls_Clothes] FOREIGN KEY([IDClothes])
REFERENCES [Edward].[Clothes] ([id])
GO
ALTER TABLE [dbo].[ImgUrls] CHECK CONSTRAINT [FK_ImgUrls_Clothes]
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
REFERENCES [dbo].[Clothes_Properties] ([ID])
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
ALTER TABLE [Edward].[Voucher]  WITH CHECK ADD  CONSTRAINT [FK_Voucher_People] FOREIGN KEY([IDSeller])
REFERENCES [Edward].[People] ([id])
GO
ALTER TABLE [Edward].[Voucher] CHECK CONSTRAINT [FK_Voucher_People]
GO
USE [master]
GO
ALTER DATABASE [EdwardEcomerce] SET  READ_WRITE 
GO
