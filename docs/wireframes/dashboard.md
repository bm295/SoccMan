# Wireframe: Dashboard

## Mục tiêu màn hình

Dashboard giúp Manager, Sales và Operations nhìn nhanh tình hình doanh thu, đơn hàng, công việc vận hành và các điểm cần xử lý trong ngày.

## Bố cục desktop

```text
+--------------------------------------------------------------------------------+
| Topbar: Breadcrumb / Date range / Global search / Create action / User menu     |
+----------------------+---------------------------------------------------------+
| Sidebar              | Page header                                             |
|                      | "Dashboard" + mô tả ngắn + filter thời gian            |
|                      +---------------------------------------------------------+
|                      | KPI cards                                               |
|                      | +-----------+ +-----------+ +-----------+ +-----------+ |
|                      | | Revenue   | | New Quote | | Open Order| | Overdue  | |
|                      | +-----------+ +-----------+ +-----------+ +-----------+ |
|                      +---------------------------------------------------------+
|                      | Revenue chart                                           |
|                      | +-----------------------------------------------------+ |
|                      | | Line/Bar chart doanh thu theo ngày/tuần/tháng       | |
|                      | +-----------------------------------------------------+ |
|                      +---------------------------+-----------------------------+
|                      | Overdue orders table      | Overdue tasks table       |
|                      | +-----------------------+ | +-----------------------+ |
|                      | | customer / amount ... | | | assignee / due ...   | |
|                      | +-----------------------+ | +-----------------------+ |
+----------------------+---------------------------+-----------------------------+
```

## Vùng KPI cards

- Nằm ngay dưới page header để người dùng nắm tình hình trong 5 giây đầu.
- Hiển thị 4 card chính:
  - Revenue trong kỳ.
  - New quotations.
  - Open orders.
  - Overdue tasks hoặc overdue orders, tùy vai trò người dùng.
- Mỗi card gồm label, giá trị chính, delta so với kỳ trước, icon trạng thái và link xem chi tiết nếu có quyền.
- Card có trạng thái loading skeleton, empty state và error inline.

## Vùng biểu đồ doanh thu

- Chiếm toàn bộ chiều rộng content trên desktop để dễ đọc xu hướng.
- Header biểu đồ gồm tiêu đề, metric selector và filter thời gian.
- Nội dung biểu đồ hỗ trợ line hoặc bar chart theo ngày, tuần, tháng.
- Tooltip hiển thị kỳ thời gian, doanh thu, số đơn và tỷ lệ tăng/giảm nếu có dữ liệu so sánh.

## Vùng đơn trễ hạn

- Bảng `Overdue orders` nằm ở hàng dưới biểu đồ, ưu tiên bên trái trên desktop.
- Hiển thị các đơn đã quá hạn giao hàng hoặc quá hạn thanh toán.
- Cột chính: order code, customer, due date, overdue days, amount, owner, status và action.
- Action chính: xem chi tiết đơn, nhắc người phụ trách, cập nhật trạng thái nếu người dùng có quyền.

## Vùng task quá hạn

- Bảng `Overdue tasks` nằm cạnh bảng đơn trễ hạn trên desktop.
- Hiển thị task quá deadline hoặc task có ưu tiên cao sắp quá hạn.
- Cột chính: task title, customer/order liên quan, assignee, due date, overdue days, priority, status và quick action.
- Action chính: cập nhật trạng thái, đổi assignee, mở chi tiết task.

## Trạng thái màn hình

- **Loading**: skeleton cho KPI cards, chart placeholder và table rows.
- **Empty**: hiển thị thông điệp không có dữ liệu trong kỳ đã chọn và CTA điều chỉnh filter.
- **Error**: hiển thị banner lỗi ở đầu content và nút thử lại cho từng widget nếu tải một phần thất bại.
- **Permission limited**: ẩn KPI hoặc bảng không thuộc quyền xem; giữ layout cân bằng bằng card gợi ý liên hệ Admin nếu cần.
