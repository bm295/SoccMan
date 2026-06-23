# UI Spec: Operations Task Filters

## Mục tiêu

Operations Task Filters mô tả filter theo assignee, deadline, customer, order và trạng thái quá hạn để người dùng nhanh chóng thu hẹp task cần xử lý.

## Filter cơ bản

| Filter | Kiểu | Hành vi |
|---|---|---|
| Search | Text input | Tìm theo task name, task code, customer, order và assignee. |
| Assignee | Multi-select user/team | Có shortcut `Me`, `Unassigned`, `My team`. |
| Deadline | Date preset/custom range | Today, Tomorrow, This week, Overdue, Custom range. |
| Customer | Async select | Tìm theo tên/mã khách hàng; giữ quyền xem dữ liệu customer. |
| Order | Async select | Tìm theo mã order hoặc customer; chỉ hiển thị order được phép xem. |
| Overdue | Toggle/select | All, Overdue only, Not overdue, Due soon. |

## Filter nâng cao

- Status columns được giữ trên board, nhưng filter advanced có thể lọc thêm một hoặc nhiều status.
- Priority: Low, Medium, High, Urgent.
- Required task: All, Required only, Optional only.
- Blocked reason hoặc blocked owner nếu organization cấu hình.
- Created by, created date và last updated date.
- Tag hoặc task type nếu có cấu hình vận hành.

## Hành vi filter

- Thay đổi filter cập nhật board và reset lazy-load/page trong từng cột.
- Filter đang áp dụng hiển thị dưới dạng chip có thể xóa từng chip.
- Nút `Reset filters` xóa toàn bộ filter trừ saved view mặc định nếu user đang dùng saved view.
- Search nên debounce ngắn và giữ nguyên các filter khác.
- URL phản ánh filter chính để có thể chia sẻ view khi không chứa dữ liệu nhạy cảm.

## Saved views

- `My tasks`: assignee là user hiện tại và status chưa Done/Cancelled.
- `Overdue tasks`: chỉ task quá hạn và chưa Done/Cancelled.
- `Blocked tasks`: status Blocked.
- `Due today`: due date là ngày hiện tại và chưa Done/Cancelled.
- Manager/Operations lead có thể lưu view cho team nếu được cấp quyền.

## Empty và quyền

- Empty filtered hiển thị `No tasks match your filters` và CTA `Reset filters`.
- Nếu filter customer/order không có dữ liệu do thiếu quyền, hiển thị option rỗng an toàn, không leak tên khách/order.
- Export board chỉ bao gồm task và trường dữ liệu user hiện tại có quyền xem.
