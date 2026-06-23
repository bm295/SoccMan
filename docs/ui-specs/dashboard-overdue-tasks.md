# UI Spec: Dashboard Overdue Tasks

## Mục tiêu

Bảng task quá hạn giúp Operations và Manager nhận diện công việc cần xử lý ngay, giảm rủi ro trễ đơn hàng và thất lạc trách nhiệm.

## Cột dữ liệu

| Cột | Nội dung | Ghi chú |
| --- | --- | --- |
| Task | Tiêu đề task và link chi tiết | Hiển thị tối đa 2 dòng. |
| Related | Customer/order liên quan | Link nếu có quyền xem. |
| Assignee | Người phụ trách | Avatar + tên; hiển thị `Unassigned` nếu chưa gán. |
| Deadline | Hạn xử lý | Date/time tùy loại task. |
| Overdue | Thời gian quá hạn | Badge warning/error. |
| Priority | Low/Medium/High/Urgent | Badge ưu tiên. |
| Status | Trạng thái task | Todo, In Progress, Blocked, Done. |
| Actions | Quick actions | Update status, reassign, open detail. |

## Filter theo assignee/deadline

- Assignee: `My tasks`, một người cụ thể, hoặc `Unassigned`.
- Deadline: `Today`, `Yesterday`, `Last 7 days`, `Custom range`.
- Priority: High/Urgent để tập trung xử lý rủi ro cao.
- Status: loại trừ `Done` và `Cancelled` theo mặc định.
- Customer/order keyword để tìm task theo ngữ cảnh nghiệp vụ.

## Action cập nhật trạng thái

- Quick action `Mark in progress` cho task đang Todo.
- Quick action `Mark done` nếu task đủ điều kiện hoàn tất.
- Action `Reassign` mở popover chọn assignee mới.
- Action `Change deadline` mở date picker, yêu cầu lý do nếu deadline bị lùi.
- Mọi cập nhật trạng thái cần ghi audit log khi hệ thống audit được triển khai.

## Sort

- Mặc định sort theo mức độ quá hạn giảm dần, sau đó priority giảm dần.
- Cho phép sort theo deadline, assignee, priority và status.

## Trạng thái bảng

- **Loading**: skeleton rows trong table/card list.
- **Empty**: text `No overdue tasks` và CTA mở Task Board nếu muốn xem toàn bộ task.
- **Error**: inline alert với nút `Retry`.
- **Bulk update unavailable**: nếu không đủ quyền, ẩn checkbox chọn nhiều và action hàng loạt.
