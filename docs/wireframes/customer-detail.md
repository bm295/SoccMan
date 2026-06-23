# Wireframe: Customer Detail

## Mục tiêu màn hình

Customer Detail cung cấp một hồ sơ khách hàng thống nhất để xem summary, lịch sử tương tác, ghi chú, báo giá, đơn hàng và công nợ phải thu.

## Bố cục desktop

```text
+--------------------------------------------------------------------------------+
| Topbar: Breadcrumb Customers / Customer name / Global search / User menu        |
+----------------------+---------------------------------------------------------+
| Sidebar              | Header                                                  |
|                      | Customer name + status badges + primary actions         |
|                      | [Create quotation] [Add note] [More]                    |
|                      +----------------------+----------------------------------+
|                      | Summary panel        | Receivables / risk card        |
|                      | - Code              | - Outstanding amount           |
|                      | - Owner             | - Overdue amount               |
|                      | - Contacts          | - Credit status                |
|                      | - Address/tax ID    | - Payment terms                |
|                      +----------------------+----------------------------------+
|                      | Timeline filters                                       |
|                      | [All events] [Notes] [Quotes] [Orders] [Payments]      |
|                      +---------------------------------------------------------+
|                      | Timeline                                                |
|                      | +-----------------------------------------------------+ |
|                      | | Today: note added / quotation sent / payment ...    | |
|                      | | Older: order updated / task completed ...           | |
|                      | +-----------------------------------------------------+ |
|                      +---------------------------------------------------------+
|                      | Tabs                                                    |
|                      | [Notes] [Quotations] [Orders] [Receivables]            |
|                      | +-----------------------------------------------------+ |
|                      | | Tab content table/list with contextual actions      | |
|                      | +-----------------------------------------------------+ |
+----------------------+---------------------------------------------------------+
```

## Header và summary

- Header hiển thị tên khách hàng, mã khách hàng, trạng thái khách hàng, trạng thái công nợ và owner.
- Primary actions nằm bên phải: `Create quotation`, `Add note`, `Edit customer` hoặc `More` tùy quyền.
- Summary panel chia thành hai khối: thông tin nhận diện/liên hệ và thông tin rủi ro/công nợ.
- Các thông tin quan trọng như tax ID, payment terms, credit limit và contact chính phải có copy action nếu phù hợp.

## Timeline

- Timeline nằm ngay dưới summary để phản ánh diễn biến mới nhất trước khi người dùng vào từng tab.
- Filter timeline gồm loại event, người thực hiện, khoảng thời gian và chỉ hiển thị event quan trọng.
- Event hiển thị theo nhóm ngày, mới nhất ở trên.
- Mỗi event có icon loại event, tiêu đề, mô tả ngắn, actor, timestamp và link mở đối tượng liên quan.

## Tabs nội dung

- Tabs nằm dưới timeline, giữ cùng URL state để có thể chia sẻ link trực tiếp đến từng tab.
- `Notes`: ghi chú chăm sóc, ghi chú nội bộ và ghi chú thanh toán.
- `Quotations`: danh sách báo giá, trạng thái duyệt, tổng tiền và action tạo/chỉnh sửa theo quyền.
- `Orders`: danh sách đơn hàng, tiến độ giao hàng/vận hành và trạng thái thanh toán.
- `Receivables`: công nợ, lịch sử thanh toán, aging buckets và các khoản quá hạn.

## Responsive

- Tablet: summary và receivables/risk card xếp dọc; actions phụ chuyển vào menu `More`.
- Mobile: header cô đọng; summary chuyển thành accordion; timeline và tabs xếp theo chiều dọc; bảng trong tab chuyển thành card list.

## Trạng thái màn hình

- **Loading**: skeleton cho header, summary, timeline và tab đang active.
- **Not found**: thông báo khách hàng không tồn tại hoặc đã bị xóa/lưu trữ ngoài quyền xem.
- **Permission limited**: chỉ hiển thị các khối dữ liệu người dùng được phép xem; action không đủ quyền bị ẩn hoặc disabled có lý do.
- **Error partial**: nếu timeline hoặc tab lỗi riêng, giữ summary và hiển thị nút retry ở vùng lỗi.

## Ghi chú tương tác bổ sung

- Header phải sticky khi cuộn qua timeline để người dùng luôn thấy tên khách hàng, trạng thái và action chính.
- Click vào badge công nợ trong summary điều hướng xuống tab Receivables và áp dụng filter `Overdue` nếu có nợ quá hạn.
- Timeline đóng vai trò luồng hoạt động tổng hợp; các tab bên dưới là nơi thao tác và xem dữ liệu chi tiết theo nghiệp vụ.
- Khi mở trực tiếp URL của một tab, màn hình vẫn render summary trước rồi scroll/focus vào tab tương ứng.
