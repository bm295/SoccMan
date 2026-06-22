# Business Rules MVP

## 1) Rule duyệt đơn

- Order chỉ được chuyển sang `confirmed` khi có customer, line items, tổng tiền, owner và payment terms hợp lệ.
- Order tạo từ quotation phải giữ liên kết `quotation_id` và không được tự ý thay đổi giá trị thương mại nếu quotation đã approved, trừ khi tạo revision hoặc có Manager/Admin duyệt.
- Order có customer đang `blocked` không được confirmed.
- Order có customer `overdue` phải chuyển sang `pending_approval` và cần Manager/Admin duyệt exception.
- Khi order được confirmed, hệ thống tự động tạo operations tasks theo template.
- Mọi thay đổi trạng thái order phải ghi audit log gồm trạng thái cũ, trạng thái mới, actor và thời điểm.

## 2) Rule overdue customer

- Payment được xem là overdue khi `due_date` nhỏ hơn ngày hiện tại và status chưa phải `confirmed`/`paid`.
- Customer được đánh dấu `overdue` khi có ít nhất một khoản quá hạn hoặc tổng `overdue_amount` lớn hơn 0.
- Customer có overdue vượt ngưỡng cấu hình, ví dụ số ngày quá hạn > 30 hoặc số tiền quá hạn > hạn mức, có thể bị chuyển sang `blocked`.
- Sales vẫn được xem và chăm sóc customer overdue nhưng không được tự confirmed order mới.
- Accountant có quyền cập nhật payment sau đối soát; khi không còn khoản quá hạn, hệ thống có thể đưa customer về `active`.
- Manager/Admin có thể duyệt bán tiếp cho customer overdue nhưng phải nhập lý do exception.

## 3) Rule cảnh báo task trễ

- Task được xem là trễ khi `due_date` nhỏ hơn thời điểm hiện tại và status chưa phải `done`/`cancelled`.
- Task sắp trễ khi còn dưới 24 giờ đến deadline và chưa hoàn thành.
- Task trễ phải hiển thị badge cảnh báo trên task board, order detail và dashboard.
- Task priority `urgent` hoặc task trễ quá 1 ngày phải được đưa vào danh sách escalation cho Manager.
- Nếu task bị `blocked`, assignee bắt buộc nhập `blocked_reason`.
- Order không được chuyển sang `delivered` nếu còn task bắt buộc chưa `done`, trừ khi Manager/Admin override và ghi lý do.

## 4) Rule discount approval

- Discount trong ngưỡng cấu hình, ví dụ <= 5%, Sales có thể gửi quotation mà không cần duyệt.
- Discount vượt ngưỡng cấu hình phải chuyển quotation sang `pending_approval`.
- Chỉ Manager/Admin được duyệt hoặc từ chối discount vượt ngưỡng.
- Quotation pending approval không được chuyển thành order.
- Nếu discount bị từ chối, Sales phải chỉnh quotation về ngưỡng hợp lệ hoặc tạo yêu cầu duyệt mới.
- Mọi quyết định duyệt/từ chối discount phải ghi audit log gồm discount cũ/mới, người duyệt và lý do.

## 5) Rule bổ sung cho tính toàn vẹn dữ liệu

- Không cho xóa hard customer, quotation, order, payment đã phát sinh giao dịch; chỉ được archive/cancel theo quyền.
- Tổng `paid_amount` của order không được vượt `total_amount`, trừ khi có payment adjustment được Admin duyệt.
- Payment đã confirmed chỉ Accountant/Admin được void hoặc chỉnh sửa, bắt buộc nhập lý do.
- Các action quan trọng như duyệt discount, confirmed order, delivered order, confirmed payment và override task phải ghi audit log.
