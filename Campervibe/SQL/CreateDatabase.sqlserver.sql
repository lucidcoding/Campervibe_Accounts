IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES
	WHERE TABLE_NAME = 'Depot')
BEGIN
	DROP TABLE [dbo].[Depot]
END 
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES
	WHERE TABLE_NAME = 'Vehicle')
BEGIN
	DROP TABLE [dbo].[Vehicle]
END 
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES
	WHERE TABLE_NAME = 'Booking')
BEGIN
	DROP TABLE [dbo].[Booking]
END 
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES
	WHERE TABLE_NAME = 'Role')
BEGIN
	DROP TABLE [dbo].[Role]
END 
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES
	WHERE TABLE_NAME = 'Permission')
BEGIN
	DROP TABLE [dbo].[Permission]
END 
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES
	WHERE TABLE_NAME = 'RolePermission')
BEGIN
	DROP TABLE [dbo].[RolePermission]
END 
GO

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES
	WHERE TABLE_NAME = 'User')
BEGIN
	DROP TABLE [dbo].[User]
END 
GO

CREATE TABLE [Depot] (
    [Id] UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
    [Code] NVARCHAR(5) NOT NULL,
    [Name] NVARCHAR(20) NOT NULL,
    [Address1] NVARCHAR(50) NOT NULL,
    [Address2] NVARCHAR(50) NOT NULL,
    [Address3] NVARCHAR(50) NOT NULL,
    [Address4] NVARCHAR(50) NOT NULL,
    [PostCode] NVARCHAR(10) NOT NULL
);

INSERT INTO [Depot] ([Id], [Code], [Name], [Address1], [Address2], [Address3], [Address4], [PostCode]) VALUES
('DBFD9572-46F4-4DA6-B877-02EC0F6A51CF', 'BLU01', 'Blue Depot', '1 Blue Street', 'Blueville', '', '', 'M11AA')

INSERT INTO [Depot] ([Id], [Code], [Name], [Address1], [Address2], [Address3], [Address4], [PostCode]) VALUES
('B90DE5AB-3550-4913-B8F6-8E1D526CA934', 'GRN02', 'Green Depot', '2 Green Lane', 'Greenborough', '', '', 'M11AA');

CREATE TABLE [Vehicle] (
    [Id] UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
    [Name] NVARCHAR(20) NOT NULL,
    [RegistrationNumber] NVARCHAR(20) NOT NULL,
    [Make] NVARCHAR(50) NOT NULL,
    [Model] NVARCHAR(50) NOT NULL,
    [PricePerDay] NUMERIC(5,2) NOT NULL,
    [HomeDepotId] NVARCHAR(36) NOT NULL
);

INSERT INTO [Vehicle] ([Id], [Name], [RegistrationNumber], [Make], [Model], [PricePerDay], [HomeDepotId]) VALUES
('911762e0-31ba-4c6c-83f8-3f2288904975', 'Abby', 'A11 AAA', 'VW', 'T1', 90, 'DBFD9572-46F4-4DA6-B877-02EC0F6A51CF')

INSERT INTO [Vehicle] ([Id], [Name], [RegistrationNumber], [Make], [Model], [PricePerDay], [HomeDepotId]) VALUES
('a50c62cd-b24a-4d0a-aada-9744fce7022c', 'Bobby', 'B11 BBB', 'VW', 'T2', 80, 'DBFD9572-46F4-4DA6-B877-02EC0F6A51CF')

INSERT INTO [Vehicle] ([Id], [Name], [RegistrationNumber], [Make], [Model], [PricePerDay], [HomeDepotId]) VALUES
('6850BF08-14D2-49A0-BC28-9285A69094BC', 'Carly', 'C11 CCC', 'VW', 'T25', 70, 'B90DE5AB-3550-4913-B8F6-8E1D526CA934')

CREATE TABLE [Booking] (
    [Id] UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
    [BookingNumber] NVARCHAR(50) NOT NULL,
    [StartDate] DATETIME NOT NULL,
    [EndDate] DATETIME NOT NULL,
    [StartMileage] NUMERIC(6,1) NULL,
    [EndMileage] NUMERIC(6,1) NULL,
    [VehicleId] NVARCHAR(36) NOT NULL,
    [CollectedOn] DATETIME NULL,
    [ReturnedOn] DATETIME NULL,
    [Total] NUMERIC(8,2) NOT NULL
);

CREATE TABLE [dbo].[Role](
	[Id] UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
	[Name] NVARCHAR(20) NOT NULL,
	[Description] NVARCHAR(50) NOT NULL
);

INSERT INTO [Role] ([Id], [Name], [Description]) VALUES ('8BB5C141-1F05-480E-9B7F-8070269BFD46', 'Admin', 'Admin User')
INSERT INTO [Role] ([Id], [Name], [Description]) VALUES ('2C6E33B8-BD7C-492C-807D-B4B1BCAE5F4F', 'Customer', 'Admin User')

CREATE TABLE [dbo].[Permission](
	[Id] UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
	[Name] NVARCHAR(50) NULL,
	[Description] NVARCHAR(50) NULL,
);

INSERT INTO [Permission] ([Id], [Name], [Description]) VALUES ('326FBC6E-65EC-48ED-B381-7F3648D404FB', 'MakeBooking', 'Make Booking')
INSERT INTO [Permission] ([Id], [Name], [Description]) VALUES ('6BE24D48-453A-4801-9535-8498A3391B74', 'ViewAllBookings', 'View All Bookings')

CREATE TABLE [dbo].[RolePermission](
	[Id] UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
	[RoleId] UNIQUEIDENTIFIER NOT NULL,
	[PermissionId] UNIQUEIDENTIFIER NOT NULL
);

INSERT INTO [RolePermission] ([Id], [PermissionId], [RoleId]) VALUES ('5B5C757D-8383-45AF-AE89-D30C711144A6', '8BB5C141-1F05-480E-9B7F-8070269BFD46', '326FBC6E-65EC-48ED-B381-7F3648D404FB')
INSERT INTO [RolePermission] ([Id], [PermissionId], [RoleId]) VALUES ('5C958082-B286-46DC-BBB6-3C9A2B970D13', '8BB5C141-1F05-480E-9B7F-8070269BFD46', '6BE24D48-453A-4801-9535-8498A3391B74')
INSERT INTO [RolePermission] ([Id], [PermissionId], [RoleId]) VALUES ('5105CF79-2E2D-4E84-93FD-595BCFB3FAFC', '2C6E33B8-BD7C-492C-807D-B4B1BCAE5F4F', '326FBC6E-65EC-48ED-B381-7F3648D404FB')

CREATE TABLE [User] (
    [Id] UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
    [Username] NVARCHAR(50) NOT NULL,
    [Password] NVARCHAR(50) NOT NULL,
    [FirstName] NVARCHAR(50) NOT NULL,
    [LastName] NVARCHAR(50) NOT NULL,
    [Email] NVARCHAR(50) NOT NULL,
    [Enabled] BIT NOT NULL,
    [RoleId] UNIQUEIDENTIFIER NOT NULL
);

INSERT INTO [User] ([Id], [Username], [Password], [FirstName], [LastName], [Email], [Enabled], [RoleId]) 
VALUES ('B9A3886F-4120-45C8-B060-AC09A4386859', 'barry@blue.com', 'Blue1234', 'Barry', 'Blue', 'barry@blue.com', 1, '8BB5C141-1F05-480E-9B7F-8070269BFD46')

INSERT INTO [User] ([Id], [Username], [Password], [FirstName], [LastName], [Email], [Enabled], [RoleId])
VALUES ('8508E688-4059-4208-9CB6-B23DC436F501', 'veronica@violet.com', 'Violet1234', 'Veronica', 'Violet', 'veronica@violet.com', 1, '2C6E33B8-BD7C-492C-807D-B4B1BCAE5F4F')
