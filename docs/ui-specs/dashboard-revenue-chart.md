# UI Spec: Dashboard Revenue Chart

## Mục tiêu

Biểu đồ doanh thu giúp người dùng nhận biết xu hướng doanh thu theo thời gian và so sánh hiệu quả giữa các kỳ.

## Trục thời gian

- Trục X hiển thị theo filter thời gian đang chọn:
  - 7 ngày: nhóm theo ngày.
  - 30 ngày: nhóm theo ngày hoặc tuần tùy mật độ dữ liệu.
  - 90 ngày: nhóm theo tuần.
  - 12 tháng: nhóm theo tháng.
- Nhãn trục cần rút gọn trên màn hình nhỏ để tránh chồng chữ.

## Metric

| Metric | Mô tả | Cách tính |
| --- | --- | --- |
| Revenue | Doanh thu trong kỳ | Tổng giá trị order hợp lệ theo ngày/tuần/tháng. |
| Order count | Số đơn hàng | Đếm order hợp lệ theo bucket thời gian. |
| Average order value | Giá trị trung bình đơn hàng | `Revenue / Order count`, hiển thị `0` nếu không có order. |

## Filter

- Date range: `Last 7 days`, `Last 30 days`, `This month`, `Last quarter`, `Custom`.
- Metric selector: Revenue, Order count, Average order value.
- Optional filter theo owner/team nếu hệ thống đã có dữ liệu phân quyền tương ứng.
- Khi filter thay đổi, chart hiển thị loading overlay nhưng vẫn giữ khung chart.

## Tooltip

Tooltip xuất hiện khi hover hoặc focus vào một điểm dữ liệu và gồm:

- Khoảng thời gian hoặc ngày cụ thể.
- Giá trị metric chính đã format theo tiền tệ/số lượng.
- Số đơn liên quan nếu metric chính là revenue hoặc average order value.
- So sánh với bucket trước nếu có dữ liệu.

## Trạng thái dữ liệu

- **Loading**: hiển thị chart skeleton hoặc shimmer trong vùng plot.
- **Empty**: hiển thị icon nhẹ, text `No revenue data for this period` và CTA chỉnh filter.
- **Error**: hiển thị message `Unable to load revenue chart` và nút `Retry`.

## Accessibility

- Chart cần có title và mô tả ngắn cho screen reader.
- Cung cấp bảng dữ liệu rút gọn hoặc summary text cho người dùng không đọc được biểu đồ.
- Màu line/bar phải đạt tương phản và không phụ thuộc duy nhất vào màu để phân biệt series.
