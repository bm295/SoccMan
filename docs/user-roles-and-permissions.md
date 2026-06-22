# User Roles and Permissions

## 1) Nguyên tắc chung

- Mọi dữ liệu nghiệp vụ phải thuộc một `organization_id`.
- Người dùng chỉ được truy cập dữ liệu trong organization của mình.
- Quyền được gán theo role chính: Admin, Sales, Operations, Accountant, Manager.
- Hành động tạo/sửa/xóa dữ liệu quan trọng phải ghi audit log.
- Các hành động duyệt exception chỉ dành cho Manager hoặc Admin.

## 2) Ma trận quyền

| Chức năng | Admin | Manager | Sales | Operations | Accountant |
|---|---|---|---|---|---|
| Quản lý users và roles | Toàn quyền | Xem | Không | Không | Không |
| Cấu hình organization | Toàn quyền | Xem/Sửa giới hạn | Không | Không | Không |
| Xem dashboard | Toàn quyền | Toàn quyền | KPI sales của mình | KPI task của mình | KPI công nợ/thanh toán |
| Customers | Toàn quyền | Toàn quyền | Tạo/Sửa/Xem khách được phân công | Xem khách liên quan task/order | Xem thông tin công nợ |
| Quotations | Toàn quyền | Xem/Duyệt/Sửa giới hạn | Tạo/Sửa báo giá của mình | Xem khi liên quan order | Xem giá trị/thanh toán |
| Orders | Toàn quyền | Xem/Duyệt/Sửa trạng thái quản lý | Tạo từ quotation, xem đơn của mình | Cập nhật tiến độ vận hành | Xem và cập nhật payment status |
| Tasks | Toàn quyền | Toàn quyền | Xem task liên quan đơn của mình | Cập nhật task được giao | Xem task liên quan payment nếu cần |
| Payments | Toàn quyền | Xem/Duyệt exception | Xem trạng thái | Xem trạng thái liên quan giao hàng | Tạo/Sửa/Xác nhận thanh toán |
| Audit logs | Toàn quyền | Xem | Không | Không | Xem log payment |
| Xóa dữ liệu | Toàn quyền có audit | Không, chỉ archive | Không | Không | Không |

## 3) Chi tiết từng role

### Admin
- Tạo, khóa, mở khóa user và gán role.
- Cấu hình organization, ngưỡng discount, hạn mức overdue và template task.
- Toàn quyền trên customer, quotation, order, task, payment và audit log.
- Có quyền sửa dữ liệu sai nhưng bắt buộc lưu lý do trong audit log.

### Manager
- Xem dashboard toàn công ty và toàn bộ dữ liệu nghiệp vụ.
- Duyệt quotation/order có discount vượt ngưỡng.
- Duyệt bán hàng cho customer overdue hoặc order có rủi ro.
- Reassign task, thay đổi deadline và xử lý escalation.
- Không quản lý user/role trừ khi được nâng quyền Admin.

### Sales
- Tạo lead/customer và cập nhật thông tin chăm sóc.
- Tạo quotation, chỉnh quotation ở trạng thái draft/revision.
- Gửi yêu cầu duyệt discount hoặc exception.
- Chuyển quotation won/approved thành order theo quyền được cấp.
- Xem trạng thái operations task và payment của đơn mình phụ trách.

### Operations
- Xem order đã confirmed/in progress liên quan đến task của mình hoặc team mình.
- Nhận, cập nhật trạng thái, ghi chú và hoàn thành task.
- Báo rủi ro trễ hạn, yêu cầu đổi deadline hoặc reassign.
- Không được sửa giá, discount, payment hoặc thông tin thương mại nhạy cảm.

### Accountant
- Xem order đã confirmed/delivered/paid và thông tin công nợ.
- Tạo, cập nhật, xác nhận payment.
- Đánh dấu customer overdue theo rule hoặc sau khi đối soát.
- Xem audit log liên quan payment và thay đổi công nợ.
- Không được thay đổi nội dung quotation/order ngoài trường thanh toán được phép.
