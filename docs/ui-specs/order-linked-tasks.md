# UI Spec: Order Linked Tasks

## Mục tiêu

Linked Tasks mô tả bảng task gắn với order, trạng thái task và action tạo/cập nhật task để Operations theo dõi việc thực thi đơn hàng.

## Cột bảng

| Cột | Nội dung | Hành vi |
|---|---|---|
| Task | Tên task, mã task và mô tả ngắn | Link mở task detail hoặc drawer. |
| Assignee | Người phụ trách hoặc team | Manager/Operations lead có thể reassign. |
| Status | Todo, In progress, Blocked, Done, Cancelled | Badge rõ chữ, không chỉ dùng màu. |
| Due date | Deadline và overdue indicator | Sort được; overdue có badge số ngày trễ. |
| Priority | Low, Medium, High, Urgent | Dùng icon + text. |
| Required | Task bắt buộc để delivered hay không | Ảnh hưởng điều kiện chuyển order. |
| Actions | View, update status, assign, add note, cancel | Theo quyền và trạng thái task. |

## Trạng thái task

- `Todo`: task đã tạo nhưng chưa bắt đầu.
- `In progress`: assignee đang xử lý.
- `Blocked`: cần lý do blocked và người phụ trách xử lý.
- `Done`: task hoàn tất; cần timestamp hoàn tất.
- `Cancelled`: task hủy; cần lý do nếu là task bắt buộc.

## Action tạo/cập nhật task

- `Create task`: mở drawer với order đã được chọn sẵn, cho phép nhập tên, assignee, due date, priority và required flag.
- `Update status`: cho phép chuyển trạng thái hợp lệ; nếu chuyển `Blocked` hoặc `Cancelled` phải nhập lý do.
- `Assign/Reassign`: chỉ Manager, Operations lead hoặc Admin được đổi assignee theo rule.
- `Mark done`: chỉ assignee, Operations lead, Manager hoặc Admin; task bắt buộc hoàn tất có thể mở khóa chuyển order sang Delivered.
- `Add note`: ghi chú vào task và sinh event trong activity log của order.

## Filter, empty và quyền

- Filter theo status, assignee, due date, priority, required và overdue.
- Empty state: `No linked tasks yet` với CTA `Create task` nếu user có quyền.
- Sales xem task liên quan order của khách được phân công nhưng chỉ cập nhật trong phạm vi được cấp quyền.
- Operations xem và thao tác task được phân công hoặc thuộc team.
- Mọi thay đổi status, assignee, due date và required flag phải ghi audit log.
