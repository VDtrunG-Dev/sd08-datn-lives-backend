Create database ShopThoiTrang
use ShopThoiTrang

create table Role(
                     Id int identity(1,1) not null primary key,
                     Name nvarchar(50),
                     CreateDate Datetime,
                     UpdateDate Datetime,
                     CreateBy varchar(30),
                     UpdateBy varchar(30),
                     Status int

)
Create table Employee(
                         Id int identity(1,1) not null primary key,
                         Code varchar(30),
                         Fullname nvarchar(100),
                         Username varchar(30),
                         Password varchar(30),
                         Image varchar(255),
                         Gender bit,
                         Phone varchar(15),
                         Email varchar(50),
                         CreateDate Datetime,
                         UpdateDate Datetime,
                         CreateBy varchar(30),
                         UpdateBy varchar(30),
                         Status int,
                         IdRole int foreign key references Role(Id)

)
Create table Customer(
                         Id int identity(1,1) not null primary key,
                         Code varchar(30),
                         Fullname nvarchar(100),
                         Username varchar(30),
                         Password varchar(30),
                         Image varchar(255),
                         Gender bit,
                         Phone varchar(15),
                         Email varchar(50),
                         CreateDate Datetime,
                         UpdateDate Datetime,
                         CreateBy varchar(30),
                         UpdateBy varchar(30),
                         Status int
)
Create table Coupon(
                       Id int identity(1,1) not null primary key,
                       Code varchar(30),
                       Name nvarchar(100),
                       IsType bit,
                       Discount int,
                       Cash money,
                       CreateDate Datetime,
                       UpdateDate Datetime,
                       CreateBy varchar(30),
                       UpdateBy varchar(30),
                       Status int,
                       IdCustomer int foreign key references Customer(Id)

)
Create table Background(
                           Id int identity(1,1) not null primary key,
                           Type varchar(30),
                           Url varchar(255),
                           Content nvarchar(255),
                           CreateDate Datetime,
                           UpdateDate Datetime,
                           CreateBy varchar(30),
                           UpdateBy varchar(30),
                           Status int

)
Create table Product(
                        Id int identity(1,1) not null primary key,
                        Code varchar(30),
                        Name nvarchar(100),
                        Description nvarchar(255),
                        CreateDate Datetime,
                        UpdateDate Datetime,
                        CreateBy varchar(30),
                        UpdateBy varchar(30),
                        Status int

)
Create table Brand(
                      Id int identity(1,1) not null primary key,
                      Name nvarchar(100),
                      Description nvarchar(255),
                      CreateDate Datetime,
                      UpdateDate Datetime,
                      CreateBy varchar(30),
                      UpdateBy varchar(30),
                      Status int

)


Create table Category(
                         Id int identity(1,1) not null primary key,
                         Name nvarchar(100),
                         Description nvarchar(255),
                         CreateDate Datetime,
                         UpdateDate Datetime,
                         CreateBy varchar(30),
                         UpdateBy varchar(30),
                         Status int
)


Create table ProductImage(
                             Id int identity(1,1) not null primary key,
                             Url nvarchar(255),
                             MainImage bit,
                             CreateDate Datetime,
                             UpdateDate Datetime,
                             CreateBy varchar(30),
                             UpdateBy varchar(30),
                             Status int,
                             IdProduct int foreign key references Product(Id)
)
Create table ProductDetail(
                              Id int identity(1,1) not null primary key,
                              Weight float,
                              Price money,
                              Discount int ,
                              Description nvarchar(255),
                              CreateDate Datetime,
                              UpdateDate Datetime,
                              CreateBy varchar(30),
                              UpdateBy varchar(30),
                              Status int,
                              IdProduct int foreign key references Product(Id),
                              IdBrand int foreign key references Brand(Id),        
                              IdCategory int foreign key references Category(Id),
							   DiscountDate DATETIME NULL


)


Create table ProductFault(
                             Id int identity(1,1) not null primary key,
                             Note nvarchar(255),
                             IdColor int ,
                             IdSize int ,
                             Quantity int ,
                             IdProductDetail int foreign key references ProductDetail(Id)

)
Create table Material(
                         Id int identity(1,1) not null primary key,
                         Name nvarchar(100),
                         Description nvarchar(255),
                         CreateDate Datetime,
                         UpdateDate Datetime,
                         CreateBy varchar(30),
                         UpdateBy varchar(30),
                         Status int
)
Create table ProductDetail_Material(
                                       Id int identity(1,1) not null primary key,
                                       IdProductDetail int foreign key references ProductDetail(Id),
                                       IdMaterial int foreign key references Material(Id)
)
Create table Size(
                     Id int identity(1,1) not null primary key,
                     Name nvarchar(100),
                     Description nvarchar(255),
                     CreateDate Datetime,
                     UpdateDate Datetime,
                     CreateBy varchar(30),
                     UpdateBy varchar(30),
                     Status int
)
Create table Color(
                      Id int identity(1,1) not null primary key,
                      Name nvarchar(100),
                      Description nvarchar(255),
                      CreateDate Datetime,
                      UpdateDate Datetime,
                      CreateBy varchar(30),
                      UpdateBy varchar(30),
                      Status int
)
Create table ProductDetail_Color_Size(
                                         Id int identity(1,1) not null primary key,
                                         IdProductDetail int foreign key references ProductDetail(Id),
                                         IdColor int foreign key references Color(Id),
                                         IdSize int foreign key references Size(Id),
                                         Quantity int
)

Create table Voucher(
                        Id int identity(1,1) not null primary key,
                        Code varchar(30),
                        Name nvarchar(100),
                        TypeVoucher bit,
                        IsVoucher bit,
                        Discount int ,
                        Cash money,
                        StartDate Datetime,
                        EndDate Datetime,
                        CreateDate Datetime,
                        UpdateDate Datetime,
                        CreateBy varchar(30),
                        UpdateBy varchar(30),
                        Status int,
						Minimum int

)


Create table Product_Voucher(
                                Id int identity(1,1) not null primary key,
                                IdVoucher int foreign key references Voucher(Id),
                                IdProduct int foreign key references Product(Id),
)
Create table Address(
                        Id int identity(1,1) not null primary key,
                        Fullname nvarchar(100),
                        Phone varchar(15),
                        Address nvarchar(255),
                        CityName nvarchar(100),
                        DistrictName nvarchar(100),
                        WardName nvarchar(100),
                        CityId int,
                        DistrictId int ,
                        WardId int,
                        CreateDate Datetime,
                        UpdateDate Datetime,
                        CreateBy varchar(30),
                        UpdateBy varchar(30),
                        Status int,
                        IdCustomer int foreign key references Customer(Id),
)
Create table Bill(
                     Id int identity(1,1) not null primary key,
                     Code varchar(30),
                     PurchaseDate Datetime,
                     EstimatedDate Datetime,
                     PaymentDate Datetime,
                     DelyveryDate Datetime,
                     TotalPrice money,
                     ShipPrice money,
                     TotalPriceLast money,
                     Note nvarchar(255),
                     PayType int ,
                     PayStatus int,
                     TypeStatus int,
                     Status int,
                     CodeGHN varchar(30),
                     IdCoupon int,
                     IdAddress int foreign key references Address(Id),
                     IdCustomer int foreign key references Customer(Id),
                     IdVoucher int foreign key references Voucher(Id),
                     IdEmployee int foreign key references Employee(Id)

)
Create table BillDetail(
                           Id int identity(1,1) not null primary key,
                           UnitPrice money,
                           Quantity int,
                           IdColor int,
                           IdSize int,
                           IdOrder int foreign key references Bill(Id),
                           IdProductDetail int foreign key references ProductDetail(Id)


)
Create table BillHistory(
                            Id int identity(1,1) not null primary key,
                            Note nvarchar(255),
                            CreateDate Datetime,
                            UpdateDate Datetime,
                            CreateBy varchar(30),
                            UpdateBy varchar(30),
                            Status int,
                            IdOrder int foreign key references Bill(Id)

)
Create table Rating(
                       Id int identity(1,1) not null primary key,
                       Score int,
                       Note nvarchar(255),
                       CreateDate Datetime,
                       UpdateDate Datetime,
                       CreateBy varchar(30),
                       UpdateBy varchar(30),
                       Status int,
                       IdProductDetail int foreign key references ProductDetail(Id),
                       IdCustomer int foreign key references Customer(Id)

)
Create table RatingImage(
                            Id int identity(1,1) not null primary key,
                            Url varchar(255),
                            IdRating int foreign key references Rating(Id)

)
Create table Cart(
                     Id int identity(1,1) not null primary key,
                     IdCustomer int foreign key references Customer(Id)

)
Create table CartDetail(
                           Id int identity(1,1) not null primary key,
                           UnitPrice money,
                           Quantity int,
                           IdColor int,
                           IdSize int,
                           IdCart int foreign key references Cart(Id),
                           IdProductDetail int foreign key references ProductDetail(Id)

)
Create table ReturnExchange(
                               Id int identity(1,1) not null primary key,
                               Code varchar(30),
                               Image  varchar(255),
                               Note nvarchar(255),
                               RequestDate Datetime,
                               ProcessingDate Datetime,
                               IsReturnExchange bit,
                               CreateDate Datetime,
                               UpdateDate Datetime,
                               CreateBy varchar(30),
                               UpdateBy varchar(30),
                               Status int,
                               IdCustomer int foreign key references Customer(Id)


)
Create table ReturnExchangeHistory(

                                      Id int identity(1,1) not null primary key,
                                      Note nvarchar(255),
                                      CreateDate Datetime,
                                      UpdateDate Datetime,
                                      CreateBy varchar(30),
                                      UpdateBy varchar(30),
                                      Status int,
                                      IdReturnExchange int foreign key references ReturnExchange(Id)

)
Create table ReturnExchangeDetail(
                                     Id int identity(1,1) not null primary key,
                                     IdReturnExchange int foreign key references ReturnExchange(Id),
                                     IdOrderDetail int foreign key references BillDetail(Id)
)
Create table ProductDetailHistory(
                                     Id int identity(1,1) primary key not null,
                                     ImageMain varchar(max),
	ImageList varchar(max),
	UpdateDate datetime,
	UpdateBy varchar(30),
	Name nvarchar(100),
	Price money,
	Weight float,
	Description nvarchar(255),
	IdCategory int ,
	IdBrand int,
	IdToe int,
	IdSole int,
	IdShoelace int,
	IdHeelcushion int ,
	IdDesign int ,
	IdMaterial varchar(max),
	IdVoucher varchar(max),
	IdColor_Size_Quantity varchar(max),
	Discount int,
	DiscountDate datetime,
	SupplierName nvarchar(100),
	SupplierPhone varchar(15),
	SupplierAddress nvarchar(255),
	SupplierAgree nvarchar(255),
	IdProductDetail int foreign key references ProductDetail(Id)


)
create table OperationHistory(
                                 Id int identity(1,1) not null primary key,
                                 CreateDate datetime,
                                 CreateBy nvarchar(100),
                                 IdProductDetail int,
                                 Status int

)
INSERT INTO Role (Name, CreateDate, UpdateDate, CreateBy, UpdateBy, Status)
VALUES 
    ('admin', GETDATE(), GETDATE(), 'CreatedByExample', 'UpdatedByExample', 1);

INSERT INTO Employee (Code, Fullname, Username, Password, Image, Gender, Phone, Email, CreateDate, UpdateDate, CreateBy, UpdateBy, Status, IdRole)
VALUES ('EMP001', N'John Doe', 'sy', '123', 'avatar.jpg', 1, '123456789', 'johndoe@example.com', '2023-11-02 08:00:00', '2023-11-02 08:00:00', 'Admin', 'Admin', 1, 1);