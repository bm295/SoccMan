# Core Workflows

## 1) Tổng quan luồng chính

Luồng MVP thống nhất dữ liệu từ bán hàng đến vận hành và thu tiền:

`Lead → Quotation → Order → Operations Task → Payment`

Mỗi bước phải giữ liên kết dữ liệu để Manager có thể truy vết từ doanh thu dự kiến đến công việc thực hiện và tiền đã thu.

## 2) Lead → Quotation

### Trigger
- Sales tạo lead mới từ cuộc gọi, form, referral hoặc dữ liệu nhập từ Excel.
- Lead đủ thông tin nhu cầu, người liên hệ và cơ hội bán hàng.

### Các bước
1. Sales tạo hoặc cập nhật customer với trạng thái `lead`.
2. Sales ghi thông tin nhu cầu, nguồn lead, người phụ trách và ngày follow-up.
3. Khi khách hàng yêu cầu giá, Sales tạo quotation từ customer.
4. Sales nhập line items, số lượng, đơn giá, discount, thuế/phí nếu có và hiệu lực báo giá.
5. Hệ thống tính tổng tiền, margin/discount indicator và kiểm tra ngưỡng cần duyệt.
6. Nếu hợp lệ, quotation chuyển sang `sent` hoặc `pending_approval`.

### Output
- Customer có lịch sử quotation.
- Quotation có trạng thái, tổng giá trị, người phụ trách và audit log.

## 3) Quotation → Order

### Trigger
- Khách hàng chấp nhận báo giá.
- Quotation ở trạng thái `approved` hoặc không cần duyệt theo rule.

### Các bước
1. Sales đánh dấu quotation là `won`.
2. Hệ thống kiểm tra customer có overdue hay bị block không.
3. Nếu customer overdue, tạo yêu cầu Manager duyệt exception.
4. Nếu được phép, Sales chuyển quotation thành order.
5. Hệ thống copy line items, giá trị, discount và customer info sang order.
6. Order khởi tạo ở trạng thái `draft` hoặc `confirmed` tùy cấu hình duyệt đơn.
7. Hệ thống ghi liên kết `quotation_id` trên order.

### Output
- Order được tạo từ quotation.
- Quotation chuyển sang `converted` và không cho sửa nội dung thương mại nếu không tạo revision.

## 4) Order → Operations Task

### Trigger
- Order được Manager/Admin/Sales có quyền xác nhận sang `confirmed`.

### Các bước
1. Hệ thống kiểm tra các trường bắt buộc: customer, line items, delivery date, owner và payment terms.
2. Nếu thiếu thông tin, order không được confirmed.
3. Khi confirmed, hệ thống tạo task vận hành từ template theo loại đơn/dịch vụ.
4. Mỗi task có assignee, deadline, priority, mô tả việc cần làm và order liên kết.
5. Operations nhận task trên task board và cập nhật trạng thái `todo → in_progress → blocked/done`.
6. Nếu task bị blocked, Operations ghi lý do và hệ thống cảnh báo Manager/Sales liên quan.
7. Khi tất cả task bắt buộc hoàn thành, order có thể chuyển sang `delivered`.

### Output
- Bộ operations tasks liên kết order.
- Order có tiến độ vận hành tổng hợp từ trạng thái task.

## 5) Operations Task → Payment

### Trigger
- Order được delivered hoặc đạt milestone thanh toán theo điều khoản.

### Các bước
1. Accountant xem danh sách order đến hạn thu tiền.
2. Accountant tạo payment record theo order, số tiền, ngày đến hạn, ngày thanh toán và phương thức.
3. Nếu thanh toán một phần, order giữ trạng thái `partially_paid` hoặc payment status tương ứng.
4. Khi tổng payment confirmed bằng giá trị phải thu, order chuyển payment status sang `paid`.
5. Nếu quá hạn thanh toán, hệ thống đánh dấu receivable overdue và cảnh báo Sales/Manager.
6. Customer có nhiều khoản quá hạn sẽ bị gắn trạng thái `overdue` hoặc `blocked` theo business rule.

### Output
- Payment record có trạng thái rõ ràng.
- Order thể hiện số tiền đã thu, còn phải thu và trạng thái thanh toán.
- Dashboard cập nhật doanh thu, công nợ và overdue.

## 6) Trạng thái đề xuất

### Customer
- `lead`, `active`, `overdue`, `blocked`, `inactive`

### Quotation
- `draft`, `pending_approval`, `approved`, `sent`, `won`, `lost`, `converted`, `expired`

### Order
- `draft`, `pending_approval`, `confirmed`, `in_progress`, `delivered`, `closed`, `cancelled`

### Task
- `todo`, `in_progress`, `blocked`, `done`, `cancelled`

### Payment
- `pending`, `partially_paid`, `paid`, `overdue`, `voided`
