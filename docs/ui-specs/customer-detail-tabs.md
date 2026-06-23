# UI Spec: Customer Detail Tabs

## Mục tiêu

Tabs trong Customer Detail tổ chức dữ liệu chi tiết theo nghiệp vụ: notes chăm sóc, quotations bán hàng, orders vận hành và receivables/công nợ.

## Hành vi chung

- Tab active được phản ánh trên URL để chia sẻ link trực tiếp.
- Mỗi tab có loading, empty, error và permission-limited state riêng.
- Số lượng bản ghi hoặc badge cảnh báo hiển thị trên label tab nếu hữu ích, ví dụ `Receivables (Overdue)`.
- Filter trong từng tab không ảnh hưởng timeline hoặc tab khác trừ khi người dùng chọn action liên kết.

## Tab Notes

### Nội dung

- Danh sách note theo thời gian mới nhất trước.
- Mỗi note gồm loại note, tiêu đề, nội dung rút gọn, tác giả, thời gian, visibility và file đính kèm nếu có.
- Loại note đề xuất: Sales, Operations, Payment, Internal, Follow-up.

### Filter và action

- Filter theo loại note, tác giả, khoảng thời gian và visibility.
- Action chính: `Add note`.
- Action trên từng note: view, edit, pin/unpin, delete/archive theo quyền.
- Note quan trọng có thể pin lên đầu nhưng vẫn cần thể hiện timestamp gốc.

### Empty state

- `No notes yet`.
- CTA `Add note` nếu user có quyền ghi chú.

## Tab Quotations

### Cột bảng

| Cột | Nội dung |
|---|---|
| Quotation code | Link sang chi tiết báo giá. |
| Status | Draft, Sent, Pending approval, Approved, Won, Lost, Expired. |
| Amount | Tổng tiền và currency. |
| Discount/risk | Badge nếu vượt ngưỡng cần duyệt. |
| Created by | Người tạo hoặc owner. |
| Valid until | Ngày hết hiệu lực. |
| Last updated | Thời điểm cập nhật gần nhất. |
| Actions | View, edit draft, send, duplicate, convert to order. |

### Filter và action

- Filter theo status, owner, khoảng ngày tạo và khoảng giá trị.
- CTA chính: `Create quotation` nếu đủ quyền và khách không bị credit hold.
- Nếu khách bị overdue/credit hold, CTA tạo báo giá bị disabled hoặc chuyển thành `Request credit exception` theo rule.

## Tab Orders

### Cột bảng

| Cột | Nội dung |
|---|---|
| Order code | Link sang chi tiết đơn hàng. |
| Status | Draft/Confirmed/In progress/Delivered/Cancelled/Paid tùy workflow. |
| Operation progress | Số task hoàn thành/tổng task hoặc milestone hiện tại. |
| Amount | Tổng giá trị đơn. |
| Payment status | Unpaid, Partially paid, Paid, Overdue. |
| Expected delivery | Ngày giao/due date. |
| Owner/team | Sales owner hoặc operations team. |
| Actions | View, update status, create task, record payment theo quyền. |

### Filter và action

- Filter theo order status, payment status, operations owner, ngày giao và overdue.
- CTA có thể gồm `Create order from quotation` nếu có báo giá đủ điều kiện.
- Operations chỉ thấy và thao tác các trạng thái vận hành được phân quyền.

## Tab Receivables

### Nội dung summary

- Outstanding amount.
- Overdue amount.
- Due soon amount.
- Aging buckets.
- Payment terms và credit limit.
- Credit hold reason nếu có.

### Bảng khoản phải thu/thanh toán

| Cột | Nội dung |
|---|---|
| Reference | Invoice/order/payment reference. |
| Type | Receivable, payment, adjustment, credit note. |
| Due date | Ngày đến hạn nếu có. |
| Amount | Số tiền khoản phải thu/thanh toán. |
| Remaining | Số còn lại. |
| Status | Current, due soon, overdue, paid, voided. |
| Related order | Link đơn hàng liên quan. |
| Actions | Record payment, confirm, void, view audit theo quyền. |

### Filter và action

- Filter theo status, due date, related order và type.
- Action chính: `Record payment` cho Accountant/Admin.
- Action phụ: export statement, send reminder, request write-off/adjustment nếu được hỗ trợ.
- Với khoản quá hạn, hiển thị số ngày quá hạn và mức cảnh báo.

## Quyền truy cập theo tab

- Admin/Manager: xem toàn bộ tabs; action quản lý theo quyền.
- Sales: xem notes/quotations/orders của khách được phân công; receivables có thể bị giới hạn trường nhạy cảm.
- Operations: xem notes liên quan vận hành và orders/tasks liên quan; không xem hoặc không thao tác quotation/payment nhạy cảm.
- Accountant: xem receivables đầy đủ, notes thanh toán và order/payment liên quan; không chỉnh sửa quotation/order ngoài trường thanh toán được phép.

## Liên kết giữa các tab

- Tạo quotation thành công phải sinh event trên timeline và cập nhật badge số lượng ở tab Quotations.
- Ghi nhận payment trong tab Receivables phải cập nhật payment status ở tab Orders nếu khoản thanh toán liên quan order.
- Pin note trong tab Notes không thay đổi thứ tự timeline; timeline vẫn theo timestamp sự kiện.
- Các action tạo/sửa/xóa trong tab phải refresh vùng tab hiện tại và summary liên quan mà không reload toàn bộ trang.
