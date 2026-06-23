# UI Spec: Dashboard Overdue Orders

## Mục tiêu

Bảng đơn trễ hạn giúp Sales, Operations và Manager ưu tiên xử lý các order có rủi ro giao hàng, thanh toán hoặc trải nghiệm khách hàng.

## Cột dữ liệu

| Cột | Nội dung | Ghi chú |
| --- | --- | --- |
| Order | Mã order và link chi tiết | Ví dụ `SO-2026-0048`. |
| Customer | Tên khách hàng | Link tới Customer Detail nếu có quyền. |
| Due date | Hạn giao hàng/thanh toán | Format theo locale người dùng. |
| Overdue | Số ngày trễ | Badge warning/error theo mức độ. |
| Amount | Giá trị đơn hoặc số tiền còn phải thu | Dùng tabular numbers. |
| Owner | Người phụ trách | Avatar + tên ngắn nếu có. |
| Status | Trạng thái order/payment | Dùng badge từ design system. |
| Actions | Hành động nhanh | View, remind, update status. |

## Sort

- Mặc định sort theo `Overdue` giảm dần, đơn trễ lâu nhất ở trên.
- Cho phép sort theo `Due date`, `Amount`, `Customer` và `Status`.
- Khi người dùng đổi sort, giữ filter hiện tại và cập nhật trạng thái URL nếu Dashboard hỗ trợ deep link.

## Filter

- Owner/assignee.
- Order status.
- Payment status.
- Overdue range: `1-3 days`, `4-7 days`, `>7 days`.
- Customer hoặc keyword theo mã order/tên khách hàng.

## Action cần có

- `View order`: mở Order Detail.
- `Remind owner`: tạo notification hoặc mở modal nhắc người phụ trách.
- `Update status`: cho phép cập nhật trạng thái nếu người dùng có quyền.
- `Export`: optional, chỉ hiển thị với Manager/Admin nếu cần báo cáo.

## Trạng thái bảng

- **Loading**: skeleton 5 dòng, giữ header bảng.
- **Empty**: text `No overdue orders` và mô tả rằng không có đơn trễ trong filter hiện tại.
- **Error**: inline alert phía trên bảng với nút `Retry`.
- **Permission limited**: ẩn amount hoặc action nhạy cảm nếu role không được xem.
