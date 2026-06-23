# UI Spec: Customer Detail Summary

## Mục tiêu

Summary giúp người dùng nắm nhanh danh tính khách hàng, người phụ trách, tình trạng thương mại và rủi ro công nợ trước khi thực hiện hành động tiếp theo.

## Thông tin summary

### Header

- Tên khách hàng là tiêu đề chính.
- Mã khách hàng hiển thị dưới tên hoặc cạnh badge trạng thái.
- Badge trạng thái khách hàng: Lead, Active, Inactive, Overdue, Archived.
- Badge trạng thái công nợ: Current, Due soon, Overdue, Credit hold.
- Owner và team phụ trách hiển thị gần actions để Manager dễ kiểm tra trách nhiệm.

### Hồ sơ khách hàng

- Customer code.
- Legal name nếu khác display name.
- Tax ID hoặc mã số doanh nghiệp.
- Customer group/segment.
- Industry.
- Tags.
- Created date và created by.
- Last activity date.

### Liên hệ và địa chỉ

- Primary contact: tên, vai trò, điện thoại, email.
- Secondary contacts hiển thị dạng collapsed list nếu nhiều hơn một liên hệ.
- Billing address.
- Shipping/default service address.
- Copy action cho phone, email, tax ID và địa chỉ.

### Thông tin thương mại

- Payment terms.
- Credit limit nếu organization sử dụng hạn mức.
- Default currency.
- Pricing tier hoặc discount policy nếu có.
- Sales owner.
- Accountant owner nếu có phân công riêng.

## Trạng thái công nợ

- `Outstanding amount`: tổng còn phải thu.
- `Overdue amount`: tổng quá hạn.
- `Due soon`: khoản đến hạn trong ngưỡng cấu hình.
- `Aging buckets`: chưa đến hạn, 1-30 ngày, 31-60 ngày, trên 60 ngày.
- `Credit status`:
  - **Current**: không có khoản quá hạn.
  - **Due soon**: có khoản sắp đến hạn.
  - **Overdue**: có khoản quá hạn.
  - **Credit hold**: bị chặn tạo báo giá/đơn theo rule hoặc quyết định quản lý.
- Với khách hàng Overdue/Credit hold, summary phải hiển thị lý do, ngày phát sinh và link đến tab Receivables.

## Action chính

| Action | Điều kiện hiển thị | Hành vi |
|---|---|---|
| Create quotation | User có quyền tạo báo giá và khách không bị chặn công nợ | Mở flow tạo quotation với customer đã chọn. |
| Add note | User có quyền ghi chú trên khách hàng | Mở modal/drawer tạo note nhanh. |
| Edit customer | Admin, Manager hoặc Sales được phân công | Mở form chỉnh sửa hồ sơ khách hàng. |
| Request credit exception | Khách bị overdue/credit hold và user cần tiếp tục giao dịch | Tạo yêu cầu duyệt cho Manager/Admin. |
| Record payment | Accountant/Admin hoặc role được cấp quyền payment | Mở form ghi nhận thanh toán, liên kết tab Receivables. |
| Reassign owner | Manager/Admin | Mở dialog đổi owner và ghi audit log. |
| Archive/restore | Admin hoặc Manager theo quyền | Yêu cầu xác nhận và lý do. |

## Quy tắc hiển thị theo quyền

- Sales chỉ xem và sửa khách được phân công; thông tin công nợ nhạy cảm có thể hiển thị ở mức tổng quan nếu cấu hình yêu cầu.
- Operations chỉ xem thông tin cần thiết cho order/task liên quan, không thấy trường thương mại nhạy cảm.
- Accountant xem đầy đủ công nợ và thanh toán, nhưng không sửa thông tin bán hàng ngoài trường được phép.
- Admin/Manager xem toàn bộ summary và có quyền thực hiện action quản lý theo ma trận quyền.
