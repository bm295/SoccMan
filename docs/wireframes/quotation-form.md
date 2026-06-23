# Wireframe: Quotation Form

## Mục tiêu màn hình

Quotation Form giúp Sales/Admin tạo hoặc chỉnh sửa báo giá với đầy đủ customer, line items, discount, tax và tổng tiền trước khi gửi khách hàng hoặc chuyển thành order.

## Bố cục desktop

```text
+--------------------------------------------------------------------------------+
| Topbar: Breadcrumb Customers / Quotations / New quotation                       |
+----------------------+---------------------------------------------------------+
| Sidebar              | Header                                                  |
|                      | New quotation / Edit quotation + status badge           |
|                      | [Save draft] [Send quotation] [More]                    |
|                      +---------------------------------------------------------+
|                      | Customer section                                         |
|                      | +-------------------+ +-------------------+             |
|                      | | Customer selector | | Contact / address |             |
|                      | +-------------------+ +-------------------+             |
|                      +---------------------------------------------------------+
|                      | Line items                                              |
|                      | +-----------------------------------------------------+ |
|                      | | Product/Service | Qty | Unit | Price | Disc | Tax | |
|                      | | + Add line item                                  ... | |
|                      | +-----------------------------------------------------+ |
|                      +----------------------+----------------------------------+
|                      | Terms & notes        | Totals panel                     |
|                      | - Valid until        | Subtotal                         |
|                      | - Payment terms      | Discount                         |
|                      | - Internal note      | Tax                              |
|                      | - Customer note      | Grand total                      |
|                      +----------------------+----------------------------------+
|                      | Footer actions: Cancel / Save draft / Submit approval   |
+----------------------+---------------------------------------------------------+
```

## Customer

- Customer selector nằm đầu form và bắt buộc trước khi thêm hoặc lưu báo giá.
- Khi chọn customer, form tự điền primary contact, billing address, payment terms, currency và credit status nếu có dữ liệu.
- Nếu customer đang `Credit hold` hoặc `Overdue`, hiển thị warning ngay dưới customer selector và vô hiệu hóa action gửi/chuyển order theo rule.
- Cho phép tạo customer nhanh chỉ khi user có quyền tạo khách hàng.

## Line items

- Bảng line items là vùng thao tác chính, hỗ trợ thêm dòng, nhân bản dòng, xóa dòng và sắp xếp lại thứ tự.
- Mỗi dòng gồm sản phẩm/dịch vụ, mô tả, số lượng, đơn vị, đơn giá, discount dòng, tax rate và line total.
- Dòng đang lỗi validation cần highlight tại field lỗi và hiển thị message inline.
- Trên mobile, line item chuyển thành card có section giá/tax/tổng tiền rõ ràng.

## Discount, tax và total

- Totals panel sticky bên phải trên desktop để người dùng luôn thấy subtotal, discount, tax và grand total.
- Hỗ trợ discount theo dòng và discount toàn báo giá; tổng discount phải hiển thị riêng để dễ kiểm tra.
- Tax được tính theo từng line item và cộng vào tax total.
- Grand total là số tiền cuối cùng dùng cho gửi báo giá, duyệt và chuyển thành order.

## Trạng thái màn hình

- **Loading**: skeleton customer selector, line item table và totals panel.
- **Draft**: cho phép lưu tạm, chỉnh sửa line items và gửi duyệt/gửi khách tùy quyền.
- **Pending approval**: khóa các trường giá/discount quan trọng; cho phép hủy yêu cầu nếu có quyền.
- **Approved/Sent**: hạn chế chỉnh sửa; sửa đổi cần tạo revision hoặc duplicate theo rule.
- **Error**: banner lỗi phía trên form và giữ dữ liệu người dùng đã nhập.
