# UI Spec: Order Payment Status

## Mục tiêu

Order Payment Status mô tả payment status, receivable amount, paid amount và overdue badge để người dùng nhận biết rủi ro thanh toán của order.

## Chỉ số hiển thị

| Chỉ số | Mô tả |
|---|---|
| Total amount | Tổng giá trị order sau discount và tax. |
| Receivable amount | Tổng khoản phải thu đã phát sinh từ order. |
| Paid amount | Tổng thanh toán đã xác nhận và chưa bị void. |
| Remaining amount | Receivable amount trừ paid amount và adjustment hợp lệ. |
| Due date | Ngày đến hạn gần nhất của khoản còn phải thu. |
| Overdue days | Số ngày quá hạn nếu remaining amount quá hạn. |

## Payment status

- `No receivable`: chưa phát sinh khoản phải thu.
- `Unpaid`: đã có receivable nhưng chưa có thanh toán xác nhận.
- `Partially paid`: paid amount lớn hơn 0 nhưng nhỏ hơn receivable amount.
- `Paid`: remaining amount bằng 0 sau payment/adjustment hợp lệ.
- `Overdue`: có remaining amount quá hạn.
- `Voided/Adjusted`: có payment bị void hoặc adjustment ảnh hưởng số dư; cần hiển thị rõ trong activity log.

## Overdue badge

- Badge overdue hiển thị khi remaining amount > 0 và due date nhỏ hơn ngày hiện tại.
- Badge gồm label `Overdue`, số ngày trễ và số tiền còn quá hạn nếu user có quyền xem tiền.
- Không tô đỏ toàn bộ card; dùng badge và icon cảnh báo để tránh gây nhiễu.
- Tooltip badge giải thích due date, amount và link sang tab/section receivables nếu có.

## Action và quyền

| Action | Role | Điều kiện |
|---|---|---|
| Record payment | Accountant/Admin | Order có receivable còn lại hoặc được phép ghi nhận advance payment. |
| Send reminder | Sales/Accountant/Manager | Có khoản due soon hoặc overdue và user có quyền liên hệ khách. |
| Export statement | Accountant/Admin/Manager | Chỉ xuất dữ liệu user có quyền xem. |
| Request adjustment/write-off | Accountant/Manager/Admin | Có remaining amount cần điều chỉnh và phải nhập lý do. |

## Đồng bộ trạng thái

- Khi payment được ghi nhận, card cập nhật paid amount, remaining amount và payment status ngay sau khi lưu thành công.
- Khi payment bị void, hệ thống cập nhật lại status và tạo activity log.
- Khi order chuyển `Paid`, payment status phải là `Paid`; nếu không, hiển thị lỗi điều kiện chuyển trạng thái.
