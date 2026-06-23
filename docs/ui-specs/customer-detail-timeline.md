# UI Spec: Customer Detail Timeline

## Mục tiêu

Timeline cung cấp lịch sử hoạt động của khách hàng theo thời gian để người dùng hiểu bối cảnh chăm sóc, báo giá, đơn hàng, task vận hành và thanh toán.

## Loại event

| Event | Nội dung hiển thị | Link liên quan |
|---|---|---|
| Customer created/updated | Trường chính được tạo hoặc cập nhật | Customer audit/detail |
| Note added/updated | Tiêu đề hoặc đoạn trích note, loại note | Note detail hoặc tab Notes |
| Quotation created/sent/approved/rejected/won/lost | Mã báo giá, tổng tiền, trạng thái mới | Quotation detail |
| Order created/confirmed/updated/delivered/cancelled | Mã đơn, trạng thái vận hành, ngày dự kiến | Order detail |
| Task created/assigned/completed/overdue | Tên task, assignee, deadline | Task detail |
| Payment recorded/confirmed/voided | Số tiền, invoice/order liên quan, trạng thái xác nhận | Payment/receivable detail |
| Receivable due/overdue/resolved | Khoản phải thu, số ngày quá hạn, trạng thái xử lý | Tab Receivables |
| Owner changed | Owner cũ, owner mới, người thực hiện | Audit log nếu có quyền |
| Status changed | Trạng thái khách hàng/công nợ cũ và mới | Audit log hoặc customer summary |

## Thứ tự hiển thị

- Mặc định sắp xếp mới nhất trước.
- Event được nhóm theo ngày: Today, Yesterday, tuần hiện tại, tháng/năm cũ hơn.
- Trong cùng timestamp, ưu tiên thứ tự: payment/receivable, order, quotation, task, note, customer update.
- Event quan trọng như overdue, credit hold, quotation approved/rejected có thể được đánh dấu `Important` nhưng vẫn giữ đúng thứ tự thời gian.
- Pagination dạng `Load more` hoặc infinite scroll có giới hạn để tránh tải quá nhiều event.

## Cấu trúc một event

- Icon theo loại event.
- Tiêu đề ngắn, ví dụ `Quotation QT-2026-0012 approved`.
- Mô tả tóm tắt tối đa 2 dòng.
- Actor: người thực hiện hoặc `System` cho event tự động.
- Timestamp tương đối và tooltip timestamp tuyệt đối.
- Link đối tượng liên quan.
- Menu action nếu có quyền: view detail, copy link, hide system noise nếu filter cá nhân hỗ trợ.

## Filter timeline

### Filter nhanh

- `All events`.
- `Notes`.
- `Quotations`.
- `Orders`.
- `Tasks`.
- `Payments/Receivables`.
- `System updates`.

### Filter nâng cao

- Khoảng thời gian.
- Actor hoặc team.
- Important only.
- Event có file đính kèm.
- Event cần follow-up.

## Empty và error state

- Empty mặc định: `No activity yet` với CTA `Add note` nếu người dùng có quyền.
- Empty filtered: `No events match your filters` với CTA `Reset timeline filters`.
- Error: hiển thị inline trong vùng timeline, không làm mất summary hoặc tabs.

## Quyền truy cập dữ liệu

- Timeline chỉ hiển thị event mà user có quyền xem đối tượng liên quan.
- Event payment/receivable có thể bị ẩn số tiền với Sales/Operations nếu cấu hình hạn chế.
- Audit-sensitive event chỉ hiển thị cho Admin, Manager hoặc role được cấp quyền audit.
