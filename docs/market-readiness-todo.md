# To-do list để Sales & Operations Control Center sẵn sàng ra thị trường

Danh sách dưới đây chia nhỏ các việc cần làm để biến ý tưởng thành một MVP có thể demo, bán thử và triển khai pilot cho SME.

## 1) Nền tảng sản phẩm

- [ ] Tạo file `docs/product-requirements.md` mô tả persona, pain points, mục tiêu MVP và phạm vi không làm trong MVP.
- [ ] Tạo file `docs/user-roles-and-permissions.md` liệt kê quyền của Admin, Sales, Operations, Accountant và Manager.
- [ ] Tạo file `docs/core-workflows.md` mô tả chi tiết workflow Lead → Quotation → Order → Operations Task → Payment.
- [ ] Tạo file `docs/data-model.md` định nghĩa bảng chính: organizations, users, customers, quotations, orders, tasks, payments, audit_logs.
- [ ] Tạo file `docs/business-rules.md` ghi rõ rule duyệt đơn, overdue customer, cảnh báo task trễ và discount approval.
- [ ] Tạo file `docs/non-functional-requirements.md` ghi yêu cầu bảo mật, backup, hiệu năng, auditability và uptime mục tiêu.

## 2) Thiết kế UX/UI

- [ ] Tạo file `docs/wireframes.md` liệt kê wireframe cần có cho Dashboard, Customer List, Customer Detail, Quotation, Order Detail và Task Board.
- [ ] Tạo file `docs/design-system.md` định nghĩa màu trạng thái, typography, spacing, button style, badge style và empty states.
- [ ] Thiết kế màn hình `Dashboard` với KPI cards, biểu đồ doanh thu, đơn trễ hạn và task quá hạn.
- [ ] Thiết kế màn hình `Customer Detail` với timeline, notes, quotations, orders và receivables.
- [ ] Thiết kế màn hình `Order Detail` với progress bar, linked tasks, payment status và activity log.
- [ ] Thiết kế màn hình `Operations Task Board` theo cột trạng thái và filter theo assignee/deadline.

## 3) Thiết lập kỹ thuật

- [ ] Tạo project Angular 22 với TypeScript strict mode, Angular Router và cấu trúc thư mục theo mục 7 trong `docs/sales-operations-control-center-sme.md`.
- [ ] Tạo file `.env.example` chứa biến môi trường mẫu cho Supabase URL, Supabase anon key và Sentry DSN.
- [ ] Tạo project Supabase cho môi trường `dev` và ghi thông tin setup vào `docs/setup-supabase.md`.
- [ ] Tạo migration `supabase/migrations/001_initial_schema.sql` cho các bảng lõi của MVP.
- [ ] Tạo migration `supabase/migrations/002_rls_policies.sql` cho RLS theo organization_id và role.
- [ ] Tạo seed file `supabase/seed.sql` với dữ liệu demo: 2 công ty, 5 khách hàng, 5 đơn hàng, 10 task.
- [ ] Cài PrimeNG và tạo layout shell gồm sidebar, topbar, breadcrumb và content area.
- [ ] Cài TanStack Query for Angular và tạo query client/provider dùng chung.
- [ ] Cài Dexie và tạo local cache service cho lookup data và draft forms.

## 4) MVP feature delivery

- [ ] Implement login/logout bằng Supabase Auth.
- [ ] Implement route guard yêu cầu đăng nhập trước khi vào dashboard.
- [ ] Implement permission directive để ẩn/hiện action theo role.
- [ ] Implement Customer CRUD với search, filter và customer status.
- [ ] Implement Quotation CRUD với line items, discount và chuyển quotation thành order.
- [ ] Implement Order CRUD với status workflow Draft → Confirmed → In Progress → Delivered → Paid.
- [ ] Implement tự động tạo operations tasks khi order được confirmed.
- [ ] Implement Dashboard đọc dữ liệu thật từ Supabase views.
- [ ] Implement Audit Log cho customer, quotation, order và task status changes.
- [ ] Implement export Excel/PDF tối thiểu cho quotation và order list.

## 5) Kiểm thử và chất lượng

- [ ] Tạo file `docs/test-plan.md` liệt kê test cases cho login, customer, quotation, order, task và dashboard.
- [ ] Tạo unit tests cho policy service, permission directive và business rules.
- [ ] Tạo Playwright E2E test cho flow: login → tạo customer → tạo quotation → convert order → confirm order → task sinh tự động.
- [ ] Tạo Playwright E2E test cho role Manager duyệt discount/exception.
- [ ] Chạy kiểm tra accessibility cơ bản cho Dashboard, forms và Task Board.
- [ ] Chạy smoke test trên môi trường staging trước mỗi demo khách hàng.

## 6) Go-to-market và bán thử

- [ ] Tạo file `docs/pricing.md` đề xuất gói Starter, Growth và Pro theo số user/chi nhánh.
- [ ] Tạo file `docs/demo-script.md` mô tả kịch bản demo 15 phút cho chủ SME.
- [ ] Tạo file `docs/sales-one-pager.md` tóm tắt vấn đề, giải pháp, lợi ích, module chính và giá trị ROI.
- [ ] Tạo file `docs/pilot-onboarding-checklist.md` mô tả các bước triển khai pilot cho 1 SME đầu tiên.
- [ ] Tạo file `docs/customer-feedback-template.md` để ghi nhận feedback sau mỗi buổi demo/pilot.
- [ ] Chuẩn bị bộ dữ liệu demo sát thực tế cho 3 ngành: phân phối hàng hóa, dịch vụ B2B và sản xuất nhỏ.
- [ ] Xác định 5 SME mục tiêu để phỏng vấn và demo sản phẩm.
- [ ] Chạy pilot 2-4 tuần với 1 khách hàng đầu tiên và ghi lại các blocker lớn nhất.

## 7) Vận hành sau khi có khách hàng đầu tiên

- [ ] Tạo file `docs/support-process.md` mô tả cách tiếp nhận bug, phân loại mức độ nghiêm trọng và thời gian phản hồi.
- [ ] Tạo file `docs/release-checklist.md` liệt kê các bước cần làm trước khi release production.
- [ ] Tạo file `docs/backup-and-restore.md` mô tả lịch backup Supabase và cách restore dữ liệu.
- [ ] Tạo dashboard Sentry để theo dõi lỗi frontend quan trọng.
- [ ] Tạo checklist migration dữ liệu từ Excel của khách hàng sang hệ thống.
- [ ] Xây dựng quy trình training 60 phút cho Admin/Sales/Operations trước khi go-live.
