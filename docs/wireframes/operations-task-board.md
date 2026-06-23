# Wireframe: Operations Task Board

## Mục tiêu màn hình

Operations Task Board giúp đội vận hành theo dõi task theo cột trạng thái, ưu tiên việc quá hạn và cập nhật tiến độ nhanh trong ngữ cảnh customer/order liên quan.

## Bố cục desktop

```text
+--------------------------------------------------------------------------------+
| Topbar: Breadcrumb Operations / Task Board / Global search / User menu          |
+----------------------+---------------------------------------------------------+
| Sidebar              | Header                                                  |
|                      | Operations Task Board + summary badges                  |
|                      | [Create task] [Saved views] [Board settings]            |
|                      +---------------------------------------------------------+
|                      | Filters                                                 |
|                      | Search | Assignee | Deadline | Customer | Order | Overdue|
|                      | [More filters] [Reset]                                  |
|                      +---------------------------------------------------------+
|                      | Status columns                                          |
|                      | +-----------+ +-------------+ +----------+ +----------+ |
|                      | | Todo      | | In Progress | | Blocked  | | Done     | |
|                      | | Task card | | Task card   | | Task card| | Task card| |
|                      | | ...       | | ...         | | ...      | | ...      | |
|                      | +-----------+ +-------------+ +----------+ +----------+ |
|                      | +-------------+                                           |
|                      | | Cancelled   |                                           |
|                      | +-------------+                                           |
+----------------------+---------------------------------------------------------+
```

## Cột trạng thái

- Board mặc định hiển thị các cột `Todo`, `In Progress`, `Blocked`, `Done` và `Cancelled`.
- Mỗi cột có header gồm tên trạng thái, số task, số task quá hạn và action nhanh nếu có quyền.
- Cột `Blocked` cần nổi bật bằng badge cảnh báo và yêu cầu lý do blocked trong card.
- Cột `Done` và `Cancelled` có thể collapsed mặc định khi số lượng task lớn.

## Task card

- Card hiển thị tên task, customer, order, assignee, priority, due date, overdue badge và quick actions.
- Card quá hạn hiển thị badge `Overdue` cùng số ngày trễ; không tô đỏ toàn bộ card.
- Card có thể mở detail drawer khi click vào vùng nội dung chính.
- Quick actions gồm update status, assign/reassign, add note và mark done theo quyền.

## Filter và saved views

- Filter bar nằm phía trên board để lọc theo assignee, deadline, customer, order, priority và overdue.
- Saved views hỗ trợ các view như `My tasks`, `Overdue tasks`, `Blocked tasks`, `Due today`.
- Khi filter đang áp dụng, hiển thị chip filter và nút `Reset`.
- Search tìm theo tên task, mã task, customer, order và assignee.

## Responsive

- Tablet: board giữ dạng cột ngang có scroll; filter nâng cao chuyển vào drawer.
- Mobile: board chuyển thành danh sách nhóm theo trạng thái; filter mở bằng bottom sheet.
- Drag-and-drop trên mobile có thể thay bằng menu `Move to status` để giảm lỗi thao tác.

## Trạng thái màn hình

- **Loading**: skeleton cho filter và mỗi cột trạng thái.
- **Empty board**: hiển thị CTA `Create task` nếu user có quyền.
- **Empty filtered**: hiển thị `No tasks match your filters` và CTA `Reset filters`.
- **Permission limited**: chỉ hiển thị task và action user có quyền xem/thao tác.
- **Error**: banner phía trên board và nút `Retry`.
