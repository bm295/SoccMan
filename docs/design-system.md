# Design System

Tài liệu này định nghĩa nền tảng giao diện cho SoccMan để các màn hình có trải nghiệm nhất quán, dễ mở rộng và phù hợp với quy trình bán hàng/điều phối vận hành.

## Màu trạng thái

| Trạng thái | Màu nền | Màu chữ/viền | Mục đích sử dụng |
| --- | --- | --- | --- |
| Default | `#F3F4F6` | `#374151` | Nội dung trung tính, trạng thái chưa phân loại. |
| Draft | `#EEF2FF` | `#4338CA` | Báo giá/đơn hàng đang soạn thảo. |
| Pending | `#FEF3C7` | `#B45309` | Chờ duyệt, chờ phản hồi, chờ thanh toán. |
| In Progress | `#DBEAFE` | `#1D4ED8` | Công việc hoặc đơn hàng đang xử lý. |
| Success | `#DCFCE7` | `#15803D` | Hoàn tất, đã duyệt, thanh toán thành công. |
| Warning | `#FFEDD5` | `#C2410C` | Sắp quá hạn, dữ liệu cần kiểm tra. |
| Error | `#FEE2E2` | `#B91C1C` | Lỗi, quá hạn, bị từ chối, thanh toán thất bại. |
| Disabled | `#E5E7EB` | `#9CA3AF` | Không khả dụng hoặc không đủ quyền thao tác. |

### Nguyên tắc dùng màu

- Không dùng màu làm tín hiệu duy nhất; luôn đi kèm nhãn, icon hoặc tooltip.
- Dùng màu trạng thái nhất quán giữa badge, timeline, bảng dữ liệu và Kanban card.
- Với nền tối hoặc ảnh, tăng tương phản bằng lớp nền bán trong suốt hoặc viền rõ ràng.

## Typography

| Token | Kích thước | Line-height | Weight | Ứng dụng |
| --- | ---: | ---: | ---: | --- |
| Display | 32px | 40px | 700 | Tiêu đề trang dashboard hoặc trang tổng quan quan trọng. |
| H1 | 28px | 36px | 700 | Tiêu đề màn hình. |
| H2 | 24px | 32px | 600 | Tiêu đề khu vực chính. |
| H3 | 20px | 28px | 600 | Tiêu đề card, modal hoặc panel. |
| Body | 16px | 24px | 400 | Nội dung chính, mô tả biểu mẫu. |
| Body Small | 14px | 20px | 400 | Bảng dữ liệu, metadata, mô tả phụ. |
| Caption | 12px | 16px | 500 | Badge, nhãn phụ, helper text. |

- Font mặc định: ưu tiên Inter, nếu không có dùng `system-ui`, `-apple-system`, `Segoe UI`, sans-serif.
- Số liệu tài chính và KPI nên dùng tabular numbers để dễ quét dữ liệu.
- Tránh dùng quá 2 cấp weight trong cùng một card để giữ nhịp thị giác rõ ràng.

## Spacing

Sử dụng thang spacing 4px để đảm bảo căn chỉnh nhất quán.

| Token | Giá trị | Ứng dụng |
| --- | ---: | --- |
| `space-1` | 4px | Khoảng cách rất nhỏ giữa icon và text. |
| `space-2` | 8px | Khoảng cách trong badge, chip, cụm control nhỏ. |
| `space-3` | 12px | Khoảng cách giữa label và input hoặc các dòng metadata. |
| `space-4` | 16px | Padding card nhỏ, khoảng cách giữa field. |
| `space-6` | 24px | Padding card chuẩn, khoảng cách giữa section. |
| `space-8` | 32px | Khoảng cách giữa các khối lớn trong trang. |
| `space-10` | 40px | Lề trên/dưới cho vùng nội dung chính. |

- Card mặc định dùng padding 24px trên desktop và 16px trên mobile.
- Grid dashboard dùng gutter 24px trên desktop, 16px trên tablet/mobile.
- Vùng nội dung chính nên có max-width phù hợp từng màn hình để tránh bảng quá rộng hoặc form khó đọc.

## Button style

| Loại button | Nền | Chữ/viền | Khi dùng |
| --- | --- | --- | --- |
| Primary | `#2563EB` | Chữ trắng | Hành động chính của màn hình như tạo báo giá, lưu đơn hàng. |
| Secondary | `#FFFFFF` | Viền `#D1D5DB`, chữ `#374151` | Hành động phụ như lọc, xuất file, xem chi tiết. |
| Tertiary/Ghost | Trong suốt | Chữ `#2563EB` | Link action, thao tác nhẹ trong bảng hoặc card. |
| Danger | `#DC2626` | Chữ trắng | Xóa, hủy, từ chối hoặc thao tác không thể hoàn tác. |
| Disabled | `#E5E7EB` | Chữ `#9CA3AF` | Không đủ điều kiện hoặc đang xử lý. |

### Kích thước button

- Small: cao 32px, padding ngang 12px, font 14px.
- Medium: cao 40px, padding ngang 16px, font 14px hoặc 16px.
- Large: cao 48px, padding ngang 20px, font 16px.

### Trạng thái tương tác

- Hover: làm tối nền 6-8% hoặc tăng độ đậm viền.
- Focus: hiển thị outline 2px màu `#93C5FD` và offset 2px.
- Loading: giữ nguyên chiều rộng, hiển thị spinner và vô hiệu hóa click lặp.

## Badge style

- Badge dùng font Caption, chữ in thường hoặc title case; không dùng toàn chữ hoa cho nhãn dài.
- Border radius: 999px cho badge trạng thái, 6px cho tag nghiệp vụ.
- Padding: 2px 8px cho badge nhỏ, 4px 10px cho badge chuẩn.
- Badge phải có nhãn rõ nghĩa: ví dụ `Pending approval`, `Paid`, `Overdue`, `High priority`.
- Với nhiều badge trong một hàng, ưu tiên thứ tự: trạng thái chính, mức ưu tiên, phân loại nghiệp vụ.

## Empty states

Mỗi empty state cần có 4 phần:

1. Icon hoặc minh họa đơn giản, không gây nhiễu.
2. Tiêu đề ngắn mô tả trạng thái, ví dụ `Chưa có báo giá`.
3. Mô tả gợi ý bước tiếp theo hoặc lý do chưa có dữ liệu.
4. CTA phù hợp nếu người dùng có quyền, ví dụ `Tạo báo giá mới`.

### Các biến thể empty state

- **No data**: chưa có dữ liệu trong hệ thống hoặc trong phạm vi khách hàng hiện tại.
- **No results**: bộ lọc/tìm kiếm không trả về kết quả; cung cấp nút xóa bộ lọc.
- **No permission**: người dùng không có quyền xem; hướng dẫn liên hệ quản trị viên.
- **Error loading**: tải dữ liệu thất bại; cung cấp nút thử lại và mã lỗi nếu có.
