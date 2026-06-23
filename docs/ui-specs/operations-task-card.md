# UI Spec: Operations Task Card

## Mục tiêu

Operations Task Card mô tả nội dung card, badge ưu tiên, deadline và quick actions để người dùng cập nhật task nhanh trên board.

## Nội dung card

| Thành phần | Nội dung | Ghi chú |
|---|---|---|
| Title | Tên task và mã task nếu có | Click mở task detail drawer. |
| Customer | Tên khách hàng liên quan | Link sang Customer Detail nếu có quyền. |
| Order | Mã order liên quan | Link sang Order Detail nếu có quyền. |
| Assignee | Avatar/tên assignee hoặc Unassigned | Cho phép assign nhanh theo quyền. |
| Priority | Badge Low/Medium/High/Urgent | Badge dùng text + màu/icon. |
| Deadline | Due date hoặc relative time | Hiển thị overdue badge nếu quá hạn. |
| Status reason | Lý do blocked/cancelled nếu có | Bắt buộc hiển thị trong trạng thái Blocked. |
| Metadata | Tag, task type, attachment/comment count | Tùy cấu hình và không làm card quá tải. |

## Badge ưu tiên và deadline

- `Urgent` và `High` hiển thị nổi bật hơn nhưng vẫn cần text để hỗ trợ accessibility.
- Deadline hiển thị dạng relative như `Due today`, `Due tomorrow`, `Overdue 3d` và tooltip ngày giờ tuyệt đối.
- Task quá hạn là task có due date nhỏ hơn hiện tại và status chưa `Done`/`Cancelled`.
- Nếu task vừa urgent vừa overdue, hiển thị cả hai badge nhưng giới hạn số badge trên card để không quá nhiễu.

## Quick actions

| Action | Điều kiện | Hành vi |
|---|---|---|
| Mark done | User có quyền hoàn tất task và task chưa Done/Cancelled | Chuyển sang Done, ghi timestamp và activity log. |
| Move status | User có quyền cập nhật status | Mở menu trạng thái hợp lệ; dùng thay drag-and-drop trên mobile/keyboard. |
| Assign/Reassign | Manager, Operations lead, Admin hoặc role được cấp quyền | Mở assignee picker và ghi audit log. |
| Add note | User có quyền ghi chú task | Mở inline composer hoặc drawer note. |
| Open order/customer | User có quyền xem đối tượng liên quan | Điều hướng sang detail tương ứng. |

## Trạng thái card

- Card loading dùng skeleton nhỏ trong cột.
- Card disabled khi user chỉ có quyền xem; quick actions bị ẩn hoặc disabled kèm tooltip lý do.
- Card blocked hiển thị reason và action `Resolve blocker` nếu user có quyền.
- Card done hiển thị completed timestamp và actor nếu có đủ không gian.

## Responsive và accessibility

- Card trên mobile mở rộng chiều ngang full-width trong nhóm trạng thái.
- Toàn bộ quick actions phải dùng được bằng keyboard.
- Menu action mở bằng Enter/Space và đóng bằng Escape.
- Badge không được chỉ dựa vào màu; luôn có text hoặc aria-label rõ ràng.
