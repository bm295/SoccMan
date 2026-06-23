# UI Spec: Dashboard KPI Cards

## Mục tiêu

KPI cards cung cấp các chỉ số quan trọng nhất để người dùng đánh giá nhanh tình hình bán hàng và vận hành trong khoảng thời gian đang chọn.

## Danh sách KPI card

| Card | Label | Công thức tính | Link/action |
| --- | --- | --- | --- |
| Revenue | `Revenue` | Tổng `orders.total_amount` của đơn có trạng thái hợp lệ trong kỳ, không tính đơn bị hủy. | Mở báo cáo doanh thu hoặc danh sách order đã lọc. |
| New Quotations | `New quotations` | Đếm quotation được tạo trong kỳ theo `created_at`. | Mở danh sách quotation đã lọc theo kỳ. |
| Open Orders | `Open orders` | Đếm order chưa ở trạng thái `Delivered`, `Paid` hoặc `Cancelled`. | Mở danh sách order đang mở. |
| Overdue Orders | `Overdue orders` | Đếm order có `due_date` nhỏ hơn ngày hiện tại và chưa hoàn tất/thanh toán theo rule nghiệp vụ. | Mở bảng đơn trễ hạn. |
| Overdue Tasks | `Overdue tasks` | Đếm task có `deadline` nhỏ hơn thời điểm hiện tại và status chưa `Done`/`Cancelled`. | Mở bảng task quá hạn hoặc Task Board. |

## Cấu trúc card

- Label ngắn ở đầu card.
- Giá trị chính nổi bật, dùng tabular numbers.
- Delta so với kỳ trước nếu có dữ liệu so sánh.
- Icon hoặc badge trạng thái để nhấn mạnh tăng/giảm/cảnh báo.
- Footer link như `View details` nếu người dùng có quyền truy cập màn hình liên quan.

## Trạng thái loading

- Dùng skeleton cho label, value và delta.
- Giữ chiều cao card ổn định để tránh layout shift.
- Nếu một card loading lâu hơn các card khác, chỉ skeleton card đó thay vì khóa toàn bộ Dashboard.

## Empty state

- Hiển thị giá trị `0` khi kết quả tính toán hợp lệ nhưng không có dữ liệu.
- Hiển thị helper text như `No orders in selected period` cho KPI phụ.
- Với user mới chưa có dữ liệu, có thể hiển thị CTA `Create first customer` hoặc `Create quotation` theo quyền.

## Error state

- Hiển thị icon cảnh báo, label `Unable to load` và nút `Retry` nhỏ trong card.
- Không hiển thị số liệu cũ nếu dữ liệu đã lỗi và không có timestamp rõ ràng.
- Nếu chỉ delta lỗi nhưng value tải được, vẫn hiển thị value và thay delta bằng `Comparison unavailable`.

## Quy tắc phân quyền

- Sales ưu tiên thấy quotation, open orders và customer-related KPI.
- Operations ưu tiên thấy open orders, overdue tasks và workload.
- Accountant ưu tiên thấy revenue, receivables và overdue payments nếu được bổ sung sau.
- Manager/Admin có thể thấy toàn bộ KPI.
