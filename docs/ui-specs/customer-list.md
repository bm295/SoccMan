# UI Spec: Customer List

## Mục tiêu

Customer List là màn hình quản lý danh sách khách hàng, tập trung vào tra cứu nhanh, lọc theo trạng thái nghiệp vụ, thao tác hàng loạt và điều hướng sang chi tiết khách hàng.

## Cột bảng

| Cột | Nội dung | Hành vi |
|---|---|---|
| Select | Checkbox chọn dòng | Chỉ hiển thị khi user có ít nhất một bulk action hợp lệ. |
| Customer | Tên khách hàng, mã khách hàng, tag nhóm khách | Tên là link sang Customer Detail; hiển thị avatar/initial nếu có. |
| Status | Badge Lead, Active, Inactive, Overdue, Archived | Có tooltip mô tả lý do trạng thái nếu trạng thái do rule tự động xác định. |
| Primary contact | Tên, số điện thoại hoặc email liên hệ chính | Click email/phone mở action tương ứng nếu được hỗ trợ. |
| Owner | Sales owner hoặc team phụ trách | Manager/Admin có thể đổi owner qua inline action hoặc bulk action. |
| Last activity | Ngày tương tác gần nhất và loại hoạt động | Sort được; dùng làm sort mặc định giảm dần. |
| Open quotations | Số báo giá đang mở | Click lọc tab quotations trong Customer Detail nếu có quyền xem. |
| Open orders | Số đơn hàng đang mở | Click lọc tab orders trong Customer Detail nếu có quyền xem. |
| Receivables | Tổng công nợ còn phải thu và overdue amount | Accountant/Manager/Admin xem đầy đủ; Sales chỉ xem mức tổng quan theo quyền. |
| Actions | View, Create quotation, Add note, Archive/Restore | Action hiển thị theo quyền và trạng thái khách hàng. |

## Search và filter

### Search

- Placeholder: `Search by customer name, code, tax ID, email or phone`.
- Search thực hiện sau debounce ngắn hoặc khi nhấn Enter.
- Kết quả search giữ nguyên filter đang áp dụng.
- Highlight phần khớp trong tên khách hàng hoặc mã khách hàng nếu khả thi.

### Filter cơ bản

- `Status`: multi-select gồm Lead, Active, Inactive, Overdue, Archived.
- `Owner`: multi-select user/team; mặc định Sales có thể thấy `My customers`.
- `Customer group`: nhóm khách hàng theo cấu hình organization.
- `Receivable status`: All, No debt, Due soon, Overdue, Credit hold.
- `Last activity`: Today, 7 days, 30 days, Custom range.

### Filter nâng cao

- Khu vực/địa chỉ giao dịch.
- Ngành hoặc phân khúc khách hàng.
- Doanh thu lũy kế theo khoảng giá trị.
- Ngày tạo khách hàng.
- Tag tùy chỉnh.
- Có/không có báo giá mở, đơn hàng mở hoặc công nợ quá hạn.

## Empty state

### Không có dữ liệu khách hàng

- Tiêu đề: `No customers yet`.
- Mô tả: khuyến khích tạo khách hàng đầu tiên để bắt đầu quản lý báo giá và đơn hàng.
- CTA chính: `Add customer` cho Admin, Manager và Sales có quyền tạo.
- CTA phụ: link tài liệu hoặc import nếu tính năng import được bật.

### Không có kết quả sau filter/search

- Tiêu đề: `No customers match your filters`.
- Mô tả: gợi ý xóa search hoặc điều chỉnh filter.
- CTA chính: `Reset filters`.
- Giữ lại các chip filter để người dùng biết điều kiện đang áp dụng.

## Bulk action

- Bulk action bar xuất hiện khi có ít nhất một dòng được chọn.
- Nội dung bar: số dòng đã chọn, `Clear selection` và các action hợp lệ.
- Action đề xuất:
  - Assign/reassign owner.
  - Add tag hoặc remove tag.
  - Change status với các trạng thái được phép.
  - Export selected.
  - Archive selected.
- Nếu selection chứa khách hàng có trạng thái không tương thích, action vẫn hiển thị nhưng cần xác nhận và báo số bản ghi sẽ bị bỏ qua.
- Mọi bulk action thay đổi dữ liệu phải ghi audit log.

## Quyền truy cập action

| Action | Admin | Manager | Sales | Operations | Accountant |
|---|---|---|---|---|---|
| View customer | Tất cả | Tất cả | Khách được phân công | Khách liên quan order/task | Thông tin công nợ |
| Add customer | Có | Có | Có nếu được cấp quyền | Không | Không |
| Edit customer | Có | Có | Khách được phân công | Không | Trường công nợ được phép |
| Create quotation | Có | Có | Khách được phân công và không bị chặn công nợ | Không | Không |
| Add note | Có | Có | Khách được phân công | Khách liên quan task/order | Ghi chú thanh toán |
| Reassign owner | Có | Có | Không | Không | Không |
| Archive/restore | Có | Archive giới hạn, không xóa | Không | Không | Không |
| Export | Có | Có | Dữ liệu được phép xem | Dữ liệu được phép xem | Dữ liệu công nợ |

## Accessibility

- Bảng phải hỗ trợ điều hướng bằng keyboard, focus state rõ ràng và checkbox có label đọc được.
- Badge trạng thái không chỉ dựa vào màu; cần text hiển thị đầy đủ.
- Menu action phải mở bằng Enter/Space và đóng bằng Escape.
- Bulk action bar phải được thông báo qua screen reader khi selection thay đổi.
