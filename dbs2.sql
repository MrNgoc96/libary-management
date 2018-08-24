CREATE DATABASE LibraryManagement
GO
USE LibraryManagement
GO
CREATE TABLE User
(
username varchar(100) primary key,
password varchar(100),
role int
)
GO
CREATE TABLE Book
(
id int identity primary key,
name nvarchar(500),
category nvarchar (100),
author nvarchar(100),
publisher nvarchar(200),
release_date date,
avatar nvarchar(500)
)
GO

CREATE TABLE Staff
(
id int identity primary key,
name nvarchar (500),
date_of_birth date,
gender nvarchar (20),
address nvarchar (500),
phone varchar(12),
work_day date,
avatar nvarchar(500),
username nvarchar(500)
)
GO

CREATE TABLE Readers
(
id int identity primary key,
name nvarchar (500),
date_of_birth date,
gender nvarchar(20),
address nvarchar (500),
create_date date,
avatar nvarchar(500),
)
GO
CREATE TABLE LoanCard
(
id  int identity primary key,
staff_id int FOREIGN KEY REFERENCES Staff(id) ON DELETE CASCADE,
reader_id int FOREIGN KEY REFERENCES Readers(id) ON DELETE CASCADE,
book_id int FOREIGN KEY REFERENCES Book(id) ON DELETE CASCADE,
create_date date,
return_date date,
)
GO

INSERT INTO Book
VALUES (N'Lập trình Java 1',N'Sách lập trình',N'Nguyễn Duy Thế',N'NXB Đại Học Bách Khoa','9-1-2018','https://thaythanhjava.files.wordpress.com/2017/09/anhnen.png')
INSERT INTO Book
VALUES (N'Lập trình C#',N'Sách lập trình',N'Lò Hồng Ngọc',N'NXB Đại Học FPT','9-2-2018','http://www.glib.hcmuns.edu.vn/sachmoi/2011/09-11/laptrinh.jpg')
INSERT INTO Book
VALUES (N'Lập trình .Net ',N'Sách lập trình',N'Phạm Văn Tuấn',N'NXB Đại Học Công Nghệ','9-1-2018','https://i.pinimg.com/originals/94/03/dd/9403dd92c8eea33480489502bb323a72.png')
INSERT INTO Book
VALUES (N'Truyện tranh Doremon',N'Truyện tranh',N'Nguyễn Duy Thế',N'NXB Đại Học Bách Khoa','9-1-2018','https://congdongshop.com/wp-content/uploads/2017/09/doremon__01.jpg')
INSERT INTO Book
VALUES (N'Lập trình Python',N'Sách lập trình',N'Nguyễn Duy Thế',N'NXB Đại Học Bách Khoa','9-1-2018','https://lh5.googleusercontent.com/-Yp-679jSJ_w/T0vT6t3zbyI/AAAAAAAABj4/4vZLhRgwMMQ/s1322/Snap1.jpg')
INSERT INTO Book
VALUES (N'Lập trình JSP Serverlet',N'Sách lập trình',N'Lò Hồng Ngọc',N'NXB Đại Học Bách Khoa','9-1-2018','https://www.ybook.vn/uploads/books/full-img-18354-1479282045.jpg')
INSERT INTO Book
VALUES (N'Tiếng chim hot trong bụi mận gai',N'Tiểu thuyết',N' Colleen McCullough',N'NXB Văn Học','9-1-2018','http://www.elle.vn/wp-content/uploads/2018/01/22/Tieng-Chim-Hot-Trong-Bui-Man-Gai.gif')

INSERT INTO Staff
VALUES (N'Nguyễn Duy Thế','1-6-1998',N'Nam',N'Thái Bình','0868792398','4-5-2018','https://znews-photo-td.zadn.vn/w1024/Uploaded/wopthuo/2014_04_18/b6c120f128df40519b33abb98a4bf0db_1.jpg','duythe98')
INSERT INTO Staff
VALUES (N'Lê Xuân Huy','1-7-1998',N'Nam',N'Hà Nội','0868792198','4-5-2018','http://skds3.vcmedia.vn/2015/bacsyhinh-3x4-1436097320448.jpg','xuanhuy98')
INSERT INTO Staff
VALUES (N'Nguyễn Phương Thảo','2-6-1998',N'Nữ',N'Thái Bình','0868112398','4-5-2018','http://tamsubantre.org/media/news/201805/1527594606_lam-hotgirl-anh-khong-kho-voi-5-meo-chup-hinh-nay-2.jpg','phuongthao01')
INSERT INTO Staff
VALUES (N'Nguyễn Tuân','1-3-1998',N'Nam',N'Nam Định','0861792398','4-5-2018','https://nghetudo.vn/uploads/avatar/Untitled-1_zpsdc9e0ccd.jpg','tuan1')
INSERT INTO Staff
VALUES (N'Đõ Ngọc Duy','1-5-1998',N'Nam',N'Thanh Hóa','0868792398','4-5-2018','http://bizweb.dktcdn.net/thumb/grande/100/175/849/products/chup-anh-the-lay-ngay-ha-noi-01.jpg?v=1500453126897','duy2011')
INSERT INTO Staff
VALUES (N'Trần Thành Công','1-5-1991',N'Nam',N'Sơn La','0868792789','4-5-2018','http://bizweb.dktcdn.net/thumb/grande/100/093/881/products/chup-anh-the-dep-ha-noi-1.png?v=1468560040877','cong2018')
INSERT INTO Staff
VALUES (N'Nguyễn Duy Nam','1-5-1998',N'Nam',N'Hòa Bình','0868792118','4-5-2018','http://pik.vn/2014668483c8-de3d-447d-ba7e-3dd65708c5a2.jpeg','nam023')
INSERT INTO Staff
VALUES (N'Hà Phương Bảo','1-5-1998',N'Nữ',N'Bắc Giang','0868792398','4-5-2018','http://www.xaluan.com/images/news/Image/2016/04/16/95711dd672cd5f.img.jpg','baohp')
INSERT INTO Staff
VALUES (N'Đinh Bích','1-5-1998',N'Nữ',N'Thái Bình','0868792398','4-5-2018','http://media.doisongphapluat.com/thumb_x500x/306/2014/5/17/anh-the.jpg','bich122343')
INSERT INTO Staff
VALUES (N'Đõ Linh','1-5-1998',N'Nữ',N'Thanh Hóa','0868792398','4-5-2018','http://www.xaluan.com/images/news/Image/2014/07/13/553c1b92e04faf.img.jpg','linh234')


INSERT INTO Readers 
VALUES (N'Lê Công Huy','2-2-1990',N'Nam',N'Thanh Hóa','2-1-2017','https://c1.staticflickr.com/3/2732/4462217174_3ca335c8ed.jpg')
INSERT INTO Readers 
VALUES (N'Trần Văn Huy','2-2-1990',N'Nam',N'Yên Bái','2-1-2017','http://sannhadathaiphong.com/upload/news/huunam_1510652241.jpeg')
INSERT INTO Readers 
VALUES (N'Lê Xuân Tùng','2-2-1990',N'Nam',N'Thanh Hóa','2-1-2017','http://www.bomonnhiydhue.edu.vn/wp-content/uploads/2015/08/BS-Nguyen-Duy-Nam-Anh.jpg')
INSERT INTO Readers 
VALUES (N'Đỗ Văn Phong','2-2-1990',N'Nam',N'Hà Nams','2-1-2017','http://2sao.vietnamnetjsc.vn/images/2017/08/29/14/28/phau-thuat-tham-my-5.jpg')
INSERT INTO Readers 
VALUES (N'Trần Thị Lương','2-2-1990',N'Nữ',N'Thanh Hóa','2-1-2017','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmIR91AzxUQfuhdzy9gYYmI3onTILyihLc9Th6_PplhOY41KBr')
INSERT INTO Readers 
VALUES (N'Hoàng Thế Diệu','2-2-1990',N'Nam',N'Thái Bình','2-1-2017','http://www.tailinhart.com/wp-content/uploads/2017/09/12963836_1700979280191056_7457497487255505878_n.jpg')
INSERT INTO Readers 
VALUES (N'Phan Văn Tuyển','2-2-1990',N'Nữ',N'Thanh Hóa','2-1-2017','https://hungmoitruong.files.wordpress.com/2010/08/the1.jpg')
INSERT INTO Readers 
VALUES (N'Nguyễn Phương Anh','2-2-1990',N'Nữ',N'Nam Định','2-1-2017','http://xabuon.com/uploads1/news/xabuon-haivl-4471921474094519.jpg')
INSERT INTO Readers 
VALUES (N'Nguyễn Văn Oai','2-2-1990',N'Nam',N'Thanh Hóa','2-1-2017','https://media.ngoisao.vn/resize_580/news/2013/11/10/47/anh_the9117_jpg4.jpg')

INSERT INTO LoanCard
VALUES (5,2,1,'2-9-2018','2-10-2019')
INSERT INTO LoanCard
VALUES (6,3,1,'2-9-2018','2-10-2019')
INSERT INTO LoanCard
VALUES (7,4,1,'2-9-2018','2-10-2019')
INSERT INTO LoanCard
VALUES (8,5,1,'2-9-2018','2-10-2019')
INSERT INTO LoanCard
VALUES (9,6,1,'2-9-2018','2-10-2019')
INSERT INTO LoanCard
VALUES (5,7,1,'2-9-2018','2-10-2019')
INSERT INTO LoanCard
VALUES (7,5,2,'2-9-2018','2-10-2019')
INSERT INTO LoanCard
VALUES (8,2,2,'2-9-2018','2-10-2019')
INSERT INTO LoanCard
VALUES (9,4,1,'2-9-2018','2-10-2019')
INSERT INTO LoanCard
VALUES (7,6,1,'2-9-2018','2-10-2019')
INSERT INTO LoanCard
VALUES (8,5,2,'2-9-2018','2-10-2019')

INSERT INTO User
VALUES('longoc96','12345',0)
INSERT INTO User
VALUES('doanhuy93','12345',1)
INSERT INTO User
VALUES('duythe98','12345',1)
INSERT INTO User
VALUES('xuanhuy98','12345',1)
