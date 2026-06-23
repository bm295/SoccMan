# Wireframe: Customer List

## Mục tiêu màn hình

Màn hình Customer List giúp người dùng tìm kiếm, lọc, theo dõi trạng thái khách hàng và mở nhanh các hành động chăm sóc, báo giá hoặc xử lý công nợ.

## Bố cục desktop

```text
+--------------------------------------------------------------------------------+
| Topbar: Breadcrumb / Global search / Add customer / User menu                   |
+----------------------+---------------------------------------------------------+
| Sidebar              | Page header                                             |
|                      | "Customers" + mô tả + Add customer                    |
|                      +---------------------------------------------------------+
|                      | Search & quick filters                                  |
|                      | +----------------------+ +----------+ +----------+     |
|                      | | Search customers...  | | Status   | | Owner    |     |
|                      | +----------------------+ +----------+ +----------+     |
|                      | [Advanced filters] [Reset] [Saved views]               |
|                      +---------------------------------------------------------+
|                      | Customer status summary                                 |
|                      | +--------+ +--------+ +---------+ +---------+          |
|                      | | Active | | Lead   | | Overdue | | Archived|          |
|                      | +--------+ +--------+ +---------+ +---------+          |
|                      +---------------------------------------------------------+
|                      | Customers table                                         |
|                      | +-----------------------------------------------------+ |
|                      | | □ Name | Status | Owner | Revenue | Debt | Actions | |
|                      | | □ ...                                           ... | |
|                      | +-----------------------------------------------------+ |
|                      | Pagination / rows per page                              |
+----------------------+---------------------------------------------------------+
```

## Search và filter

- Ô search nằm đầu vùng filter, hỗ trợ tìm theo tên khách hàng, mã khách hàng, mã số thuế, email, số điện thoại và người liên hệ chính.
- Quick filters hiển thị inline trên desktop: trạng thái khách hàng, owner, nhóm khách hàng, trạng thái công nợ và ngày tạo/cập nhật.
- Advanced filters mở dạng drawer hoặc popover để lọc thêm theo khu vực, ngành, doanh thu lũy kế, lần tương tác gần nhất và tag.
- Saved views cho phép lưu bộ lọc thường dùng như `Khách overdue`, `Lead mới`, `Khách của tôi`.
- Khi filter đang áp dụng, hiển thị chip filter có nút xóa từng điều kiện và nút `Reset` toàn bộ.

## Trạng thái khách hàng

- Status summary nằm dưới filter để người dùng nhìn nhanh phân bổ khách hàng theo trạng thái.
- Trạng thái đề xuất:
  - **Lead**: khách tiềm năng mới hoặc chưa phát sinh báo giá thắng.
  - **Active**: khách đang có báo giá, đơn hàng hoặc hoạt động chăm sóc trong kỳ.
  - **Inactive**: không có tương tác hoặc đơn hàng trong ngưỡng thời gian cấu hình.
  - **Overdue**: có công nợ quá hạn hoặc rủi ro thanh toán.
  - **Archived**: khách đã lưu trữ, chỉ cho xem và khôi phục theo quyền.
- Màu trạng thái phải nhất quán với design system: neutral cho Lead/Inactive, success cho Active, warning/error cho Overdue, muted cho Archived.

## Bảng danh sách

- Bảng chiếm phần lớn màn hình, hỗ trợ chọn nhiều dòng nếu người dùng có quyền bulk action.
- Cột đầu tiên là checkbox chọn dòng; click vào tên khách hàng mở Customer Detail.
- Cột action nằm bên phải và có thể sticky trên desktop.
- Dòng khách overdue cần nhấn mạnh bằng badge công nợ, không tô đỏ toàn bộ dòng để tránh gây nhiễu.
- Sorting mặc định theo `Last activity` giảm dần, sau đó theo tên khách hàng tăng dần.

## Responsive

- Tablet: filter nâng cao chuyển vào drawer; bảng giữ các cột quan trọng gồm tên, status, owner, debt và actions.
- Mobile: danh sách chuyển thành card list; search full-width; filter mở bằng bottom sheet; bulk action hiển thị bằng bottom bar khi có lựa chọn.

## Trạng thái màn hình

- **Loading**: skeleton cho search/filter summary và 8-10 dòng bảng.
- **Empty no data**: hiển thị CTA `Add customer` nếu người dùng có quyền tạo khách hàng.
- **Empty filtered**: hiển thị thông điệp không có khách phù hợp và CTA `Reset filters`.
- **Permission limited**: ẩn cột/hành động không đủ quyền; hiển thị tooltip giải thích với action bị disabled nếu cần.
- **Error**: banner lỗi phía trên bảng và nút `Retry`.
