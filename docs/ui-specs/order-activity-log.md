# UI Spec: Order Activity Log

## Mục tiêu

Order Activity Log mô tả event, actor, timestamp và dữ liệu audit cần hiển thị để người dùng hiểu lịch sử thay đổi của order.

## Loại event

| Event | Nội dung hiển thị | Dữ liệu audit cần có |
|---|---|---|
| Order created | Mã order, customer, quotation nguồn nếu có | Actor, timestamp, source quotation. |
| Status changed | Trạng thái cũ, trạng thái mới, lý do nếu có | Actor, timestamp, old/new status, reason. |
| Task created/updated | Task, assignee, status, due date | Actor, timestamp, old/new field values. |
| Payment recorded/voided | Số tiền, reference, trạng thái payment | Actor, timestamp, payment id, reason nếu void. |
| Delivery updated | Expected/actual delivery date hoặc milestone | Actor, timestamp, old/new dates. |
| Owner/team changed | Owner/team cũ và mới | Actor, timestamp, old/new owner/team. |
| Note added | Tiêu đề hoặc excerpt note | Actor, timestamp, visibility. |
| System rule triggered | Rule credit, overdue, automation hoặc sync | System actor, timestamp, rule id/source. |

## Cấu trúc một log item

- Icon theo loại event.
- Tiêu đề ngắn dễ scan, ví dụ `Order moved to In Progress`.
- Mô tả tối đa 2 dòng; chi tiết dài mở bằng expand hoặc drawer.
- Actor gồm tên người dùng hoặc `System` với nguồn automation.
- Timestamp tương đối và tooltip timestamp tuyệt đối.
- Link tới đối tượng liên quan như task, payment, quotation hoặc audit detail nếu có quyền.

## Thứ tự và filter

- Mặc định sắp xếp mới nhất trước.
- Nhóm theo ngày: Today, Yesterday, tuần hiện tại, tháng/năm cũ hơn.
- Filter nhanh: All, Status, Tasks, Payments, Delivery, Notes, Audit.
- Filter nâng cao: actor, khoảng thời gian, event type, important only và system events.
- Event quan trọng như cancel order, payment voided, forced status rollback và required task cancelled phải được đánh dấu `Important`.

## Quyền truy cập và bảo mật

- User chỉ thấy event của đối tượng mà họ có quyền xem.
- Sales/Operations có thể bị ẩn số tiền payment nếu cấu hình hạn chế dữ liệu tài chính.
- Audit detail đầy đủ chỉ hiển thị cho Admin, Manager hoặc role có quyền audit.
- Nếu event liên quan bản ghi đã xóa hoặc không còn quyền xem, hiển thị mô tả an toàn và ẩn link chi tiết.

## Empty và error state

- Empty mặc định: `No activity yet` cho order mới tạo.
- Error state hiển thị inline trong vùng log và không làm mất summary/progress hiện có.
- `Load more` dùng để tải lịch sử cũ hơn, tránh tải toàn bộ audit log trong lần render đầu tiên.
