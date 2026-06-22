# Non-Functional Requirements MVP

## 1) Bảo mật

- Bắt buộc xác thực người dùng trước khi truy cập ứng dụng.
- Áp dụng phân quyền theo role và organization cho mọi màn hình và API/query.
- Dữ liệu multi-tenant phải được cô lập bằng `organization_id` và Row Level Security khi dùng Supabase.
- Không lưu secret ở frontend; các key nhạy cảm phải nằm trong biến môi trường hoặc backend/edge function.
- Mật khẩu và đăng nhập tuân theo cơ chế của auth provider; khuyến nghị bật MFA cho Admin/Manager sau MVP.
- File/chứng từ tải lên phải giới hạn quyền xem theo organization và entity liên quan.
- Các action nhạy cảm phải yêu cầu quyền phù hợp và ghi audit log.

## 2) Backup và khôi phục

- Database production cần backup tự động hằng ngày trong giai đoạn MVP pilot.
- Giữ tối thiểu 7 bản backup gần nhất cho pilot; tăng retention khi có khách hàng trả phí.
- Cần có tài liệu restore dữ liệu và kiểm thử restore định kỳ trước go-live production.
- File upload quan trọng cần có chính sách backup hoặc versioning theo khả năng của storage provider.
- Trước mỗi migration production phải có backup gần nhất và kế hoạch rollback.

## 3) Hiệu năng

- Dashboard chính tải dữ liệu ban đầu trong mục tiêu dưới 3 giây với dữ liệu pilot.
- Các màn hình danh sách customer, quotation, order, task phải hỗ trợ pagination/filter server-side.
- Truy vấn thường dùng phải có index theo `organization_id`, `status`, `due_date`, `customer_id` và owner/assignee.
- Form tạo/sửa nghiệp vụ phải phản hồi thao tác người dùng gần như tức thì, tránh block UI khi lưu dữ liệu.
- Báo cáo/KPI nặng nên dùng view/materialized view hoặc query tối ưu thay vì tính toán toàn bộ ở client.

## 4) Auditability

- Hệ thống phải ghi audit log cho các thay đổi quan trọng trên customer, quotation, order, task và payment.
- Audit log cần lưu actor, entity, action, timestamp, before/after data và reason nếu là action duyệt/override.
- Người dùng không được tự sửa hoặc xóa audit log qua UI.
- Manager/Admin có thể xem audit trail theo entity detail để giải thích ai đã làm gì và khi nào.
- Payment, discount approval, overdue override và order status change là nhóm bắt buộc audit.

## 5) Uptime mục tiêu

- MVP pilot đặt mục tiêu uptime 99.0% theo tháng cho giờ làm việc chính.
- Khi có khách hàng trả phí, mục tiêu tăng lên 99.5% hoặc cao hơn tùy gói dịch vụ.
- Cần có trang/ghi chú vận hành nội bộ để theo dõi incident, thời gian bắt đầu, tác động và hành động khắc phục.
- Hệ thống nên có monitoring lỗi frontend/backend tối thiểu trước khi triển khai production.
- Các thay đổi production nên triển khai qua staging/preview trước để giảm rủi ro downtime.

## 6) Khả năng mở rộng và bảo trì

- Kiến trúc phải cho phép thêm module kho, kế toán nâng cao hoặc tích hợp bên thứ ba sau MVP mà không phá vỡ schema lõi.
- Code frontend nên chia theo feature module/domain để dễ mở rộng.
- Business rules quan trọng nên được gom vào service/rule layer thay vì rải rác trong component.
- Migration database phải được version control và có quy trình review trước khi chạy production.
