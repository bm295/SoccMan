# UI Spec: Quotation Form

## Mục tiêu

Quotation Form định nghĩa validation, cách tính total, rule discount và action chuyển quotation thành order để đảm bảo báo giá nhất quán trước khi gửi khách hàng.

## Validation

### Customer và thông tin chung

- `Customer` là bắt buộc; không được lưu draft nếu thiếu customer trong flow tạo mới.
- `Primary contact` bắt buộc khi action là `Send quotation`; draft có thể thiếu nhưng phải cảnh báo.
- `Valid until` không được nhỏ hơn ngày hiện tại khi gửi khách hàng.
- `Currency` lấy mặc định từ customer hoặc organization và không đổi sau khi quotation có order liên kết.
- `Payment terms` bắt buộc nếu quotation có thể chuyển thành order.

### Line items

| Field | Rule |
|---|---|
| Product/Service | Bắt buộc; cho phép custom item nếu organization bật cấu hình. |
| Quantity | Bắt buộc, lớn hơn 0 và theo precision của đơn vị. |
| Unit price | Bắt buộc, không âm; giá bằng 0 cần quyền hoặc lý do nếu rule yêu cầu. |
| Line discount | Không âm và không vượt line subtotal. |
| Tax rate | Bắt buộc nếu organization áp dụng thuế; có thể là 0% nhưng phải rõ ràng. |
| Description | Tùy chọn, giới hạn độ dài theo cấu hình. |

## Tính total

- `Line subtotal = quantity × unit price`.
- `Line discount amount` được tính theo percent hoặc fixed amount tùy mode của dòng.
- `Taxable amount = line subtotal - line discount amount`.
- `Line tax = taxable amount × tax rate`.
- `Line total = taxable amount + line tax`.
- `Quotation subtotal = tổng line subtotal`.
- `Quotation discount = tổng line discount + discount cấp báo giá`.
- `Tax total = tổng line tax sau mọi discount chịu thuế`.
- `Grand total = subtotal - quotation discount + tax total`.
- Tất cả giá trị tiền tệ phải làm tròn theo currency precision và hiển thị chênh lệch rounding nếu có.

## Rule discount

- Discount theo dòng áp dụng trước discount toàn báo giá.
- Discount toàn báo giá có thể là percent hoặc fixed amount và không được làm grand total âm.
- Nếu discount vượt ngưỡng cấu hình, quotation chuyển sang `Pending approval` thay vì gửi trực tiếp.
- Sales chỉ được sửa discount trong ngưỡng được cấp; Manager/Admin có thể override và phải ghi lý do.
- Discount trên customer `Credit hold` không được dùng để bỏ qua rule công nợ; cần credit exception riêng.

## Action chuyển quotation thành order

| Action | Điều kiện | Hành vi |
|---|---|---|
| Convert to order | Quotation ở trạng thái Approved/Won, chưa hết hạn và customer không bị chặn | Tạo order nháp với customer, line items, totals và payment terms từ quotation. |
| Request approval | Discount/risk vượt ngưỡng hoặc cần duyệt giá | Tạo approval task/event và khóa action gửi khách cho đến khi có quyết định. |
| Duplicate quotation | User có quyền tạo quotation | Tạo bản sao ở Draft, không sao chép approval cũ. |
| Send quotation | Quotation hợp lệ và user có quyền gửi | Ghi timestamp gửi, actor và event timeline. |

## Empty, error và quyền truy cập

- Empty line items hiển thị CTA `Add line item` và hướng dẫn tối thiểu một dòng trước khi gửi.
- Nếu tính total lỗi do dữ liệu không hợp lệ, totals panel hiển thị trạng thái không tính được và link tới dòng lỗi đầu tiên.
- Operations không được sửa quotation; Accountant chỉ xem phần payment terms/totals nếu được cấp quyền.
- Mọi thay đổi giá, discount, tax và trạng thái approval phải ghi audit log.
