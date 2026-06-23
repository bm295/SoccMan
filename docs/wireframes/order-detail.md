# Wireframe: Order Detail

## Mục tiêu màn hình

Order Detail cung cấp một trang điều phối đơn hàng với progress bar, linked tasks, payment status và activity log để Sales, Operations và Accountant cùng theo dõi tiến độ.

## Bố cục desktop

```text
+--------------------------------------------------------------------------------+
| Topbar: Breadcrumb Customers / Orders / Order code                              |
+----------------------+---------------------------------------------------------+
| Sidebar              | Header                                                  |
|                      | Order code + customer + status badges                   |
|                      | [Update status] [Create task] [Record payment] [More]   |
|                      +---------------------------------------------------------+
|                      | Progress bar                                            |
|                      | Draft -> Confirmed -> In Progress -> Delivered -> Paid  |
|                      +----------------------+----------------------------------+
|                      | Order summary        | Payment status card             |
|                      | - Customer           | - Total amount                  |
|                      | - Quotation link     | - Paid amount                   |
|                      | - Owner/team         | - Receivable amount             |
|                      | - Expected delivery  | - Overdue badge                 |
|                      +----------------------+----------------------------------+
|                      | Linked tasks                                            |
|                      | +-----------------------------------------------------+ |
|                      | | Task | Assignee | Status | Due date | Priority | ... | |
|                      | +-----------------------------------------------------+ |
|                      +---------------------------------------------------------+
|                      | Activity log                                            |
|                      | Status changes / task updates / payment events / audit  |
+----------------------+---------------------------------------------------------+
```

## Progress bar

- Progress bar nằm ngay dưới header để phản ánh trạng thái hiện tại của order.
- Các bước chuẩn: `Draft`, `Confirmed`, `In Progress`, `Delivered`, `Paid`.
- Step đang active có badge trạng thái; step hoàn tất có check icon; step bị blocked có cảnh báo và lý do.
- Click vào step mở chi tiết điều kiện chuyển trạng thái nếu user có quyền xem.

## Linked tasks

- Linked tasks nằm giữa summary và activity log vì task vận hành là trung tâm thực thi order.
- Bảng task hiển thị task name, assignee, status, due date, priority và quick actions.
- Task quá hạn cần hiển thị overdue badge nhưng không tô màu toàn dòng.
- CTA `Create task` hiển thị ở header và trong empty state của linked tasks nếu user có quyền.

## Payment status

- Payment status card hiển thị total amount, receivable amount, paid amount, remaining amount và overdue badge.
- Nếu order chưa phát sinh công nợ, card hiển thị trạng thái `No receivable yet` cùng thông tin điều kiện tạo công nợ.
- Accountant/Admin có action `Record payment`; Sales có thể thấy action request/payment reminder theo quyền.

## Activity log

- Activity log hiển thị cuối màn hình, theo thứ tự mới nhất trước.
- Log bao gồm thay đổi trạng thái order, tạo/cập nhật task, ghi nhận thanh toán, cập nhật ngày giao và các audit event quan trọng.
- Filter nhanh: All, Status, Tasks, Payments, Audit.

## Responsive

- Tablet: summary và payment card xếp dọc; linked tasks giữ dạng bảng rút gọn.
- Mobile: progress bar chuyển thành vertical stepper; linked tasks và activity log chuyển thành card list.
