# Product Requirements - Sales & Operations Control Center MVP

## 1) Persona mục tiêu

### Chủ doanh nghiệp / Manager
- Điều hành SME 10-100 nhân sự, thường quản lý bằng Excel, chat và họp miệng.
- Cần nhìn nhanh doanh thu, đơn đang xử lý, công nợ và việc trễ hạn.
- Muốn giảm phụ thuộc vào một vài nhân sự nắm thông tin vận hành.

### Sales
- Tiếp nhận lead, chăm sóc khách hàng, tạo báo giá và theo dõi cơ hội bán hàng.
- Cần biết lịch sử khách hàng, báo giá gần nhất, trạng thái đơn và tình trạng thanh toán.
- Muốn thao tác nhanh, ít nhập liệu lặp lại và dễ chuyển báo giá thành đơn hàng.

### Operations
- Nhận việc sau khi đơn được xác nhận và cập nhật tiến độ thực hiện.
- Cần danh sách task rõ ràng theo deadline, assignee, trạng thái và mức ưu tiên.
- Muốn tránh bị giao việc qua nhiều kênh rời rạc như Zalo, email và file Excel.

### Accountant
- Theo dõi thanh toán, công nợ và khách hàng quá hạn.
- Cần biết đơn nào đã giao nhưng chưa thu tiền, khoản nào đến hạn hoặc quá hạn.
- Muốn dữ liệu đơn hàng và thanh toán có audit trail rõ ràng.

## 2) Pain points chính

- Dữ liệu khách hàng, báo giá, đơn hàng, task và thanh toán bị phân tán ở nhiều file/kênh.
- Chủ doanh nghiệp không có dashboard realtime để biết đơn nào nghẽn, ai đang xử lý và tiền về đến đâu.
- Sales khó theo dõi lịch sử chăm sóc và dễ bỏ sót follow-up.
- Operations nhận việc thiếu ngữ cảnh, không rõ deadline hoặc tiêu chí hoàn thành.
- Accountant phải tổng hợp công nợ thủ công, dễ sai lệch với trạng thái đơn hàng.
- Thiếu quy trình duyệt khi giảm giá cao, bán cho khách quá hạn hoặc thay đổi đơn quan trọng.

## 3) Mục tiêu MVP

- Cung cấp một luồng nghiệp vụ thống nhất: Lead → Quotation → Order → Operations Task → Payment.
- Cho phép SME quản lý khách hàng, báo giá, đơn hàng, task vận hành và thanh toán cơ bản trong một ứng dụng.
- Hỗ trợ phân quyền theo vai trò Admin, Sales, Operations, Accountant và Manager.
- Tự động tạo task vận hành khi đơn hàng được xác nhận.
- Hiển thị dashboard tối thiểu về doanh thu, đơn hàng, task trễ hạn và công nợ.
- Ghi audit log cho các hành động quan trọng để dễ truy vết.
- Đủ ổn định để demo, bán thử và chạy pilot 2-4 tuần với khách hàng SME đầu tiên.

## 4) Phạm vi MVP

### Có trong MVP
- Đăng nhập/đăng xuất và phân quyền theo organization/role.
- CRUD khách hàng với trạng thái lead/customer/overdue.
- CRUD quotation với line items, discount và trạng thái duyệt.
- Chuyển quotation đã duyệt/thắng thành order.
- CRUD order với workflow trạng thái chuẩn.
- Tự động tạo operations tasks từ order confirmed.
- Task board cơ bản theo trạng thái, assignee và deadline.
- Ghi nhận payment theo order và cập nhật trạng thái công nợ.
- Dashboard KPI cơ bản cho Manager/Admin.
- Audit log cho customer, quotation, order, task và payment.

## 5) Không làm trong MVP

- Không làm kế toán đầy đủ, sổ cái, cân đối kế toán hoặc khai báo thuế.
- Không tích hợp ngân hàng, ví điện tử hoặc cổng thanh toán realtime.
- Không làm quản lý kho nâng cao, serial/lot, định mức sản xuất hoặc MRP.
- Không làm mobile app native; chỉ ưu tiên web responsive/PWA cơ bản.
- Không làm automation marketing, email campaign hoặc CRM pipeline phức tạp.
- Không làm tích hợp sâu với ERP bên thứ ba trong giai đoạn MVP.
- Không làm BI nâng cao, forecast doanh thu hoặc phân tích lợi nhuận theo nhiều chiều.
- Không hỗ trợ tùy biến workflow phức tạp theo từng khách hàng ngoài cấu hình tối thiểu.
