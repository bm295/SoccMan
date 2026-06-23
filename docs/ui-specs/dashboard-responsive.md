# UI Spec: Dashboard Responsive

## Mục tiêu

Dashboard phải giữ được khả năng quét thông tin nhanh trên desktop, tablet và mobile, đồng thời tránh làm người dùng phải cuộn ngang trừ khi xem bảng dữ liệu chi tiết.

## Desktop từ 1024px trở lên

- Sidebar cố định bên trái, content dùng grid 12 cột.
- KPI cards hiển thị 4 card trên một hàng; nếu có nhiều hơn 4 card, xuống hàng theo nhóm ưu tiên.
- Revenue chart chiếm toàn bộ chiều rộng content hoặc 8/12 cột nếu có panel phụ trong tương lai.
- Overdue orders và overdue tasks hiển thị cạnh nhau, mỗi bảng chiếm 6/12 cột.
- Table hiển thị đầy đủ cột và action inline.

## Tablet từ 768px đến 1023px

- Sidebar có thể thu gọn icon-only hoặc chuyển thành drawer tùy layout shell.
- KPI cards hiển thị 2 card mỗi hàng.
- Revenue chart chiếm toàn bộ chiều rộng.
- Overdue orders và overdue tasks xếp dọc để tránh bảng quá hẹp.
- Filter nâng cao chuyển vào popover/drawer; topbar chỉ giữ filter thời gian chính và action quan trọng.

## Mobile dưới 768px

- Sidebar chuyển thành drawer mở bằng hamburger.
- Page header rút gọn: tiêu đề, date range và menu `More`.
- KPI cards hiển thị dạng horizontal scroll hoặc 1 card mỗi hàng; ưu tiên không làm giá trị chính bị cắt.
- Revenue chart giảm chiều cao, rút gọn nhãn trục X và hỗ trợ summary text bên dưới.
- Overdue orders và overdue tasks chuyển từ table sang card list:
  - Mỗi card hiển thị title, customer/order, due date, overdue badge và action chính.
  - Action phụ nằm trong menu ba chấm.
- Không dùng hover-only interaction; mọi thao tác phải click/tap được.

## Breakpoint và spacing

| Breakpoint | Layout | Gutter | Padding content |
| --- | --- | ---: | ---: |
| `< 768px` | 1 cột | 16px | 16px |
| `768-1023px` | 2-6 cột tùy section | 16px | 20px |
| `>= 1024px` | 12 cột | 24px | 24px |

## Performance và loading trên mobile

- Ưu tiên tải KPI cards và overdue summaries trước chart nếu mạng chậm.
- Chart có thể lazy render sau khi vùng chart đi vào viewport.
- Giữ skeleton ngắn gọn để tránh cuộn quá dài khi loading.
