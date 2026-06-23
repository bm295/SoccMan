# UI Spec: Order Progress

## Mục tiêu

Order Progress mô tả trạng thái `Draft → Confirmed → In Progress → Delivered → Paid` và điều kiện chuyển trạng thái để đảm bảo order không đi sai workflow.

## Trạng thái

| Status | Ý nghĩa | Người thao tác chính |
|---|---|---|
| Draft | Order mới tạo, chưa xác nhận thực thi | Sales/Admin |
| Confirmed | Order đã được xác nhận và sẵn sàng vận hành | Sales/Manager/Admin |
| In Progress | Operations đang thực hiện task/giao hàng/dịch vụ | Operations/Manager/Admin |
| Delivered | Hàng hóa/dịch vụ đã bàn giao hoặc hoàn tất | Operations/Manager/Admin |
| Paid | Order đã thanh toán đủ hoặc được quyết toán | Accountant/Admin |
| Cancelled | Order bị hủy trước khi hoàn tất | Manager/Admin theo quyền |

## Điều kiện chuyển trạng thái

| Từ | Đến | Điều kiện |
|---|---|---|
| Draft | Confirmed | Có customer, line items hợp lệ, total > 0 hoặc được phép total 0, không bị credit hold. |
| Confirmed | In Progress | Có operations owner hoặc ít nhất một linked task bắt buộc nếu workflow yêu cầu. |
| In Progress | Delivered | Tất cả task bắt buộc đã `done` hoặc có override Manager/Admin với lý do. |
| Delivered | Paid | Receivable remaining amount bằng 0 hoặc được write-off/adjustment hợp lệ. |
| Any active | Cancelled | User có quyền hủy, nhập lý do và không vi phạm rule tài chính/vận hành. |

## Hành vi UI

- Progress bar hiển thị step hiện tại, step hoàn tất và step bị chặn với tooltip lý do.
- Action `Update status` chỉ hiển thị những trạng thái tiếp theo hợp lệ cho user hiện tại.
- Nếu điều kiện chưa đạt, action bị disabled và hiển thị checklist điều kiện còn thiếu.
- Chuyển trạng thái thành công phải tạo activity log với actor, timestamp, trạng thái cũ/mới và lý do nếu có.
- Chuyển ngược trạng thái chỉ cho Manager/Admin và phải ghi audit log.

## Edge cases

- Order có công nợ quá hạn vẫn có thể ở `Delivered`, nhưng không chuyển `Paid` khi còn remaining amount.
- Nếu payment bị void sau khi order đã `Paid`, order phải quay về trạng thái thanh toán phù hợp hoặc hiển thị cảnh báo cần rà soát.
- Nếu task bắt buộc được reopen sau `Delivered`, progress bar hiển thị cảnh báo vận hành nhưng không tự động đổi trạng thái nếu rule không yêu cầu.
