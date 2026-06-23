# UI Spec: Operations Task Columns

## Mục tiêu

Operations Task Columns mô tả các cột trạng thái task và rule kéo-thả/chuyển trạng thái để board vận hành nhất quán với workflow task.

## Cột trạng thái

| Cột | Ý nghĩa | Hành vi |
|---|---|---|
| Todo | Task đã tạo, chưa bắt đầu | Cho phép kéo sang In Progress, Blocked hoặc Cancelled theo quyền. |
| In Progress | Task đang được xử lý | Cho phép kéo sang Done, Blocked hoặc Todo nếu cần hoàn tác. |
| Blocked | Task bị chặn | Bắt buộc có lý do blocked; hiển thị nổi bật trong board. |
| Done | Task hoàn tất | Khóa chỉnh sửa nội dung chính; reopen cần quyền và lý do. |
| Cancelled | Task bị hủy | Yêu cầu lý do hủy, đặc biệt với task bắt buộc cho order. |

## Rule kéo-thả

- Drag-and-drop chỉ bật với user có quyền cập nhật task đó.
- Khi thả card vào cột mới, hệ thống kiểm tra điều kiện chuyển trạng thái trước khi lưu.
- Nếu điều kiện cần thêm dữ liệu như lý do blocked/cancelled, mở dialog xác nhận trước khi cập nhật.
- Nếu cập nhật thất bại, card quay về cột cũ và hiển thị toast lỗi.
- Mọi chuyển trạng thái phải ghi audit log gồm trạng thái cũ, trạng thái mới, actor và timestamp.

## Điều kiện chuyển trạng thái

| Từ | Đến | Điều kiện |
|---|---|---|
| Todo | In Progress | User là assignee, Operations lead, Manager hoặc Admin. |
| Todo/In Progress | Blocked | Bắt buộc nhập lý do blocked và optional expected resolution date. |
| In Progress | Done | Task đủ điều kiện hoàn tất; task required có thể cập nhật progress order. |
| Blocked | In Progress | Lý do blocked đã được xử lý hoặc user có quyền override. |
| Any active | Cancelled | User có quyền hủy và nhập lý do. |
| Done/Cancelled | Todo/In Progress | Reopen cần Manager/Admin hoặc role được cấp quyền, kèm lý do. |

## Header và giới hạn cột

- Header cột hiển thị tổng số task, số task quá hạn và số task high/urgent nếu có.
- Cột có nhiều task dùng lazy load hoặc pagination trong cột để tránh render quá nhiều card.
- Cột `Done` và `Cancelled` có filter thời gian mặc định như 7 hoặc 30 ngày gần nhất.
- Board phải giữ thứ tự card trong cột theo priority, overdue và due date nếu chưa có manual ordering.

## Accessibility

- Drag-and-drop phải có phương án keyboard qua menu `Move to status`.
- Cột và card cần label đọc được cho screen reader.
- Không chỉ dùng màu để phân biệt trạng thái; badge phải có text.
