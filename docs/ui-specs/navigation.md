# UI Spec: Navigation

Tài liệu này mô tả cấu trúc điều hướng chính của SoccMan, bao gồm sidebar, topbar, breadcrumb và hành vi responsive trên tablet/mobile.

## Sidebar

### Mục đích

Sidebar là điều hướng cấp cao giúp người dùng chuyển nhanh giữa các phân hệ vận hành bán hàng.

### Cấu trúc đề xuất

1. Logo/tên sản phẩm.
2. Nhóm điều hướng chính:
   - Dashboard
   - Customers
   - Quotations
   - Orders
   - Tasks
   - Reports
3. Nhóm hỗ trợ/quản trị:
   - Settings
   - Help hoặc Documentation
4. Vùng thông tin workspace nếu hệ thống hỗ trợ nhiều đội/chi nhánh.

### Hành vi

- Mục đang active có nền nhấn nhẹ, viền trái hoặc icon đổi màu theo primary color.
- Mục có số lượng cần xử lý có thể hiển thị badge nhỏ, ví dụ đơn hàng quá hạn hoặc task chưa hoàn tất.
- Sidebar desktop có thể thu gọn còn icon-only; khi hover hoặc focus vào icon phải hiển thị tooltip tên mục.
- Trạng thái thu gọn/mở rộng nên được ghi nhớ theo người dùng.
- Mục không đủ quyền xem sẽ bị ẩn hoặc disabled kèm tooltip giải thích, tùy chính sách phân quyền.

## Topbar

### Thành phần

- Tiêu đề ngữ cảnh hoặc tên màn hình hiện tại.
- Ô tìm kiếm toàn cục nếu được hỗ trợ.
- Hành động nhanh theo ngữ cảnh, ví dụ `Create quotation`, `Add customer`.
- Thông báo, trợ giúp và menu tài khoản người dùng.

### Hành vi

- Topbar cố định ở đầu vùng nội dung khi người dùng cuộn trang dài.
- Trên màn hình có thao tác lưu, topbar có thể hiển thị trạng thái `Unsaved changes`, `Saving`, `Saved`.
- Hành động chính chỉ xuất hiện khi phù hợp với quyền và trạng thái dữ liệu hiện tại.
- Menu tài khoản chứa hồ sơ, thiết lập cá nhân, chuyển workspace và đăng xuất.

## Breadcrumb

### Mục đích

Breadcrumb giúp người dùng nhận biết vị trí trong cấu trúc dữ liệu, đặc biệt ở các trang chi tiết như Customer Detail hoặc Order Detail.

### Quy tắc hiển thị

- Hiển thị dưới topbar hoặc trong phần header của trang.
- Không dùng breadcrumb cho Dashboard nếu đó là trang gốc.
- Mỗi cấp trước cấp hiện tại là link; cấp hiện tại là text không click.
- Tối đa 4 cấp; nếu dài hơn, rút gọn cấp giữa bằng dấu `…` có menu xổ xuống.

### Ví dụ

- `Customers / Acme Co.`
- `Customers / Acme Co. / Quotations / QT-2026-0012`
- `Orders / SO-2026-0048`

## Responsive behavior

### Desktop từ 1024px trở lên

- Sidebar hiển thị cố định bên trái.
- Topbar chiếm phần còn lại theo chiều ngang.
- Breadcrumb hiển thị đầy đủ nếu còn đủ không gian.
- Bảng dữ liệu ưu tiên hiển thị nhiều cột và hành động inline.

### Tablet từ 768px đến 1023px

- Sidebar mặc định có thể thu gọn icon-only.
- Topbar giữ tiêu đề ngắn, chuyển một số CTA phụ vào menu `More`.
- Breadcrumb có thể rút gọn cấp giữa để tránh tràn dòng.
- Bộ lọc nâng cao nên chuyển vào drawer hoặc popover.

### Mobile dưới 768px

- Sidebar chuyển thành navigation drawer mở bằng hamburger trong topbar.
- Drawer che phủ nội dung, có overlay và đóng bằng nút close, phím Escape hoặc click overlay.
- Topbar chỉ giữ hamburger, tiêu đề ngắn, hành động chính hoặc menu ba chấm.
- Breadcrumb hiển thị dạng rút gọn, ví dụ `‹ Customers` hoặc chỉ parent link.
- Các hành động hàng loạt trong bảng chuyển thành bottom action bar hoặc menu theo lựa chọn.

## Accessibility

- Navigation phải hỗ trợ keyboard đầy đủ: Tab, Shift+Tab, Enter/Space và Escape với drawer/menu.
- Mỗi icon-only item cần `aria-label` hoặc tooltip có thể đọc bởi screen reader.
- Focus state phải rõ ràng và không bị che bởi layout sticky.
- Drawer mobile cần trap focus khi mở và trả focus về nút hamburger khi đóng.
