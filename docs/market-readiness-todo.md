# To-do list để Sales & Operations Control Center sẵn sàng ra thị trường

Danh sách dưới đây chia nhỏ các việc cần làm để biến ý tưởng thành một MVP có thể demo, bán thử và triển khai pilot cho SME.

Nguyên tắc viết task:

- Mỗi task nên tạo/sửa một file cụ thể hoặc hoàn thành một đầu ra nhỏ, dễ review.
- Nếu một task cần nhiều màn hình, nhiều module hoặc nhiều workflow, hãy tách thành các task con theo file, component, service hoặc checklist.
- Task thiết kế nên chỉ rõ file đầu ra, ví dụ: `docs/wireframes/dashboard.md` hoặc `docs/ui-specs/dashboard.md`.
- Task implement nên chỉ rõ file code cần tạo/sửa, ví dụ: `src/app/features/dashboard/dashboard.page.ts`, `src/app/features/dashboard/dashboard.routes.ts`, hoặc `src/app/core/auth/auth.service.ts`.
- Sau phần discovery/design, ưu tiên task tạo file code thật (`.ts`, `.html`, `.scss`, `.spec.ts`, `.sql`) thay vì chỉ tạo thêm tài liệu `.md`.

## 1) Nền tảng sản phẩm

- [x] Tạo file `docs/product-requirements.md` mô tả persona, pain points, mục tiêu MVP và phạm vi không làm trong MVP.
- [x] Tạo file `docs/user-roles-and-permissions.md` liệt kê quyền của Admin, Sales, Operations, Accountant và Manager.
- [x] Tạo file `docs/core-workflows.md` mô tả chi tiết workflow Lead → Quotation → Order → Operations Task → Payment.
- [x] Tạo file `docs/data-model.md` định nghĩa bảng chính: organizations, users, customers, quotations, orders, tasks, payments, audit_logs.
- [x] Tạo file `docs/business-rules.md` ghi rõ rule duyệt đơn, overdue customer, cảnh báo task trễ và discount approval.
- [x] Tạo file `docs/non-functional-requirements.md` ghi yêu cầu bảo mật, backup, hiệu năng, auditability và uptime mục tiêu.

## 2) Thiết kế UX/UI

### 2.1) Danh mục màn hình và design system

- [ ] Tạo thư mục `docs/wireframes/` để chứa wireframe theo từng màn hình.
- [ ] Tạo thư mục `docs/ui-specs/` để chứa đặc tả UI chi tiết theo từng màn hình.
- [ ] Tạo file `docs/wireframes/README.md` liệt kê các wireframe cần có cho Dashboard, Customer List, Customer Detail, Quotation, Order Detail và Task Board.
- [ ] Tạo file `docs/design-system.md` định nghĩa màu trạng thái, typography, spacing, button style, badge style và empty states.
- [ ] Tạo file `docs/ui-specs/navigation.md` mô tả sidebar, topbar, breadcrumb và hành vi responsive.

### 2.2) Dashboard

- [ ] Tạo file `docs/wireframes/dashboard.md` phác thảo layout Dashboard gồm vùng KPI cards, biểu đồ doanh thu, đơn trễ hạn và task quá hạn.
- [ ] Tạo file `docs/ui-specs/dashboard-kpi-cards.md` mô tả từng KPI card: label, công thức tính, trạng thái loading, empty state và error state.
- [ ] Tạo file `docs/ui-specs/dashboard-revenue-chart.md` mô tả biểu đồ doanh thu: trục thời gian, metric, filter và tooltip.
- [ ] Tạo file `docs/ui-specs/dashboard-overdue-orders.md` mô tả bảng đơn trễ hạn: cột dữ liệu, sort, filter và action cần có.
- [ ] Tạo file `docs/ui-specs/dashboard-overdue-tasks.md` mô tả bảng task quá hạn: cột dữ liệu, filter theo assignee/deadline và action cập nhật trạng thái.
- [ ] Tạo file `docs/ui-specs/dashboard-responsive.md` mô tả cách Dashboard hiển thị trên desktop, tablet và mobile.

### 2.3) Customer screens

- [ ] Tạo file `docs/wireframes/customer-list.md` phác thảo màn hình danh sách khách hàng với search, filter và trạng thái khách hàng.
- [ ] Tạo file `docs/ui-specs/customer-list.md` mô tả cột bảng, filter, empty state, bulk action và quyền truy cập action.
- [ ] Tạo file `docs/wireframes/customer-detail.md` phác thảo màn hình Customer Detail với summary, timeline, notes, quotations, orders và receivables.
- [ ] Tạo file `docs/ui-specs/customer-detail-summary.md` mô tả thông tin summary, trạng thái công nợ và action chính.
- [ ] Tạo file `docs/ui-specs/customer-detail-timeline.md` mô tả event trong timeline, thứ tự hiển thị và filter.
- [ ] Tạo file `docs/ui-specs/customer-detail-tabs.md` mô tả tab notes, quotations, orders và receivables.

### 2.4) Quotation và Order screens

- [ ] Tạo file `docs/wireframes/quotation-form.md` phác thảo form quotation gồm customer, line items, discount, tax và total.
- [ ] Tạo file `docs/ui-specs/quotation-form.md` mô tả validation, tính total, rule discount và action chuyển quotation thành order.
- [ ] Tạo file `docs/wireframes/order-detail.md` phác thảo Order Detail với progress bar, linked tasks, payment status và activity log.
- [ ] Tạo file `docs/ui-specs/order-progress.md` mô tả trạng thái Draft → Confirmed → In Progress → Delivered → Paid và điều kiện chuyển trạng thái.
- [ ] Tạo file `docs/ui-specs/order-linked-tasks.md` mô tả bảng linked tasks, trạng thái task và action tạo/cập nhật task.
- [ ] Tạo file `docs/ui-specs/order-payment-status.md` mô tả payment status, receivable amount, paid amount và overdue badge.
- [ ] Tạo file `docs/ui-specs/order-activity-log.md` mô tả activity log, actor, timestamp và dữ liệu audit cần hiển thị.

### 2.5) Operations Task Board

- [ ] Tạo file `docs/wireframes/operations-task-board.md` phác thảo board theo cột trạng thái.
- [ ] Tạo file `docs/ui-specs/operations-task-columns.md` mô tả các cột trạng thái task và rule kéo-thả/chuyển trạng thái.
- [ ] Tạo file `docs/ui-specs/operations-task-filters.md` mô tả filter theo assignee, deadline, customer, order và trạng thái quá hạn.
- [ ] Tạo file `docs/ui-specs/operations-task-card.md` mô tả nội dung task card, badge ưu tiên, deadline và quick actions.

## 3) Thiết lập kỹ thuật

- [ ] Tạo project Angular 22 với TypeScript strict mode, Angular Router và cấu trúc thư mục theo mục 7 trong `docs/sales-operations-control-center-sme.md`.
- [ ] Tạo file `.env.example` chứa biến môi trường mẫu cho Supabase URL, Supabase anon key và Sentry DSN.
- [ ] Tạo file `src/environments/environment.ts` đọc cấu hình Supabase/Sentry cho local dev.
- [ ] Tạo file `src/environments/environment.prod.ts` đọc cấu hình Supabase/Sentry cho production build.
- [ ] Tạo file `src/app/app.routes.ts` khai báo route lazy-load cho dashboard, customers, quotations, orders và tasks.
- [ ] Tạo file `src/app/app.config.ts` đăng ký Angular Router, HTTP client, PrimeNG config và TanStack Query provider.
- [ ] Tạo project Supabase cho môi trường `dev` và ghi thông tin setup vào `docs/setup-supabase.md`.
- [ ] Tạo migration `supabase/migrations/001_initial_schema.sql` cho bảng `organizations` và `users`.
- [ ] Tạo migration `supabase/migrations/002_customer_schema.sql` cho bảng `customers` và index/filter cần thiết.
- [ ] Tạo migration `supabase/migrations/003_sales_schema.sql` cho bảng `quotations`, `quotation_items`, `orders` và `order_items`.
- [ ] Tạo migration `supabase/migrations/004_operations_schema.sql` cho bảng `tasks` và quan hệ với `orders`.
- [ ] Tạo migration `supabase/migrations/005_finance_audit_schema.sql` cho bảng `payments` và `audit_logs`.
- [ ] Tạo migration `supabase/migrations/006_dashboard_views.sql` cho view `dashboard_kpis` và `dashboard_revenue_by_period`.
- [ ] Tạo migration `supabase/migrations/007_rls_organization_policies.sql` cho RLS theo `organization_id`.
- [ ] Tạo migration `supabase/migrations/008_rls_role_policies.sql` cho quyền theo role Admin, Sales, Operations, Accountant và Manager.
- [ ] Tạo seed file `supabase/seed-organizations.sql` với dữ liệu demo cho 2 công ty.
- [ ] Tạo seed file `supabase/seed-customers.sql` với 5 khách hàng demo.
- [ ] Tạo seed file `supabase/seed-orders.sql` với 5 đơn hàng demo.
- [ ] Tạo seed file `supabase/seed-tasks.sql` với 10 task demo.
- [ ] Cài PrimeNG và tạo `src/app/core/layout/app-shell.component.ts` cho layout shell.
- [ ] Tạo `src/app/core/layout/sidebar.component.ts` cho sidebar navigation.
- [ ] Tạo `src/app/core/layout/topbar.component.ts` cho topbar/user menu.
- [ ] Tạo `src/app/core/layout/breadcrumb.component.ts` cho breadcrumb theo route hiện tại.
- [ ] Cài TanStack Query for Angular và tạo `src/app/core/query/query-client.provider.ts` dùng chung.
- [ ] Cài Dexie và tạo `src/app/core/cache/local-cache.service.ts` cho lookup data và draft forms.
- [ ] Tạo `src/app/core/supabase/supabase.client.ts` để khởi tạo Supabase browser client.

## 4) MVP feature delivery

### 4.1) Authentication và authorization

- [ ] Tạo `src/app/core/auth/auth.service.ts` bọc Supabase Auth: login, logout, session, current user.
- [ ] Tạo `src/app/core/auth/auth.store.ts` lưu session/user bằng signal hoặc store hiện có.
- [ ] Tạo `src/app/features/auth/login.page.ts` cho màn hình login với email/password và trạng thái loading/error.
- [ ] Tạo `src/app/features/auth/login.page.html` cho form login.
- [ ] Tạo `src/app/features/auth/auth.routes.ts` khai báo route `/login`.
- [ ] Tạo `src/app/core/guards/auth.guard.ts` chặn route private khi chưa đăng nhập.
- [ ] Tạo `src/app/core/permissions/permission.service.ts` đọc role hiện tại và trả về quyền theo `docs/user-roles-and-permissions.md`.
- [ ] Tạo `src/app/shared/directives/has-permission.directive.ts` để ẩn/hiện action theo role.
- [ ] Cập nhật `src/app/core/layout/topbar.component.ts` thêm logout action trong user menu.

### 4.2) Customer module

- [ ] Tạo `src/app/features/crm/crm.routes.ts` khai báo route `/customers` và `/customers/:id`.
- [ ] Tạo `src/app/features/crm/models/customer.model.ts` định nghĩa Customer, CustomerStatus và CustomerFilters.
- [ ] Tạo `src/app/features/crm/services/customer.service.ts` với hàm list, getById, create, update, archive.
- [ ] Tạo `src/app/features/crm/customer-list/customer-list.page.ts` cho Customer List shell.
- [ ] Tạo `src/app/features/crm/customer-list/customer-list.page.html` hiển thị table, search, filter và customer status.
- [ ] Tạo `src/app/features/crm/customer-detail/customer-detail.page.ts` cho Customer Detail shell.
- [ ] Tạo `src/app/features/crm/customer-detail/customer-detail.page.html` hiển thị summary, notes, quotations, orders và receivables.
- [ ] Tạo `src/app/features/crm/customer-form/customer-form.component.ts` cho form tạo/sửa customer với validation tối thiểu.
- [ ] Tạo `src/app/features/crm/customer-status/customer-status-badge.component.ts` để hiển thị trạng thái khách hàng dùng lại được.

### 4.3) Quotation module

- [ ] Tạo `src/app/features/quotations/quotations.routes.ts` khai báo route `/quotations`, `/quotations/new`, `/quotations/:id`.
- [ ] Tạo `src/app/features/quotations/models/quotation.model.ts` định nghĩa Quotation, QuotationItem và DiscountApprovalStatus.
- [ ] Tạo `src/app/features/quotations/services/quotation.service.ts` với hàm list, getById, create, update, convertToOrder.
- [ ] Tạo `src/app/features/quotations/quotation-list/quotation-list.page.ts` cho danh sách quotation.
- [ ] Tạo `src/app/features/quotations/quotation-form/quotation-form.page.ts` cho form tạo/sửa quotation.
- [ ] Tạo `src/app/features/quotations/quotation-line-items/quotation-line-items.component.ts` để thêm/xóa line item và tính subtotal/tax/total.
- [ ] Tạo `src/app/features/quotations/rules/discount-rule.ts` kiểm tra discount vượt ngưỡng cần Manager duyệt.
- [ ] Tạo `src/app/features/quotations/actions/convert-quotation-to-order.action.ts` xử lý chuyển quotation thành order.

### 4.4) Order module

- [ ] Tạo `src/app/features/orders/orders.routes.ts` khai báo route `/orders` và `/orders/:id`.
- [ ] Tạo `src/app/features/orders/models/order.model.ts` định nghĩa Order, OrderItem, OrderStatus và PaymentStatus.
- [ ] Tạo `src/app/features/orders/services/order.service.ts` với hàm list, getById, create, updateStatus.
- [ ] Tạo `src/app/features/orders/order-list/order-list.page.ts` cho danh sách order.
- [ ] Tạo `src/app/features/orders/order-detail/order-detail.page.ts` cho Order Detail shell.
- [ ] Tạo `src/app/features/orders/order-status-progress/order-status-progress.component.ts` hiển thị Draft → Confirmed → In Progress → Delivered → Paid.
- [ ] Tạo `src/app/features/orders/rules/order-status-rule.ts` kiểm tra điều kiện chuyển trạng thái theo `docs/business-rules.md`.
- [ ] Tạo `src/app/features/orders/services/order-task-factory.service.ts` tự động tạo operations tasks khi order được confirmed.
- [ ] Tạo `src/app/features/orders/payment-status-panel/payment-status-panel.component.ts` hiển thị paid amount, receivable amount và overdue badge.

### 4.5) Operations task module

- [ ] Tạo `src/app/features/operations/operations.routes.ts` khai báo route `/tasks`.
- [ ] Tạo `src/app/features/operations/models/task.model.ts` định nghĩa Task, TaskStatus, TaskPriority và TaskFilters.
- [ ] Tạo `src/app/features/operations/services/task.service.ts` lấy task theo organization, assignee, deadline và status.
- [ ] Tạo `src/app/features/operations/task-board/task-board.page.ts` cho Operations Task Board shell.
- [ ] Tạo `src/app/features/operations/task-board/task-board.page.html` hiển thị columns theo trạng thái task.
- [ ] Tạo `src/app/features/operations/task-card/task-card.component.ts` hiển thị customer, order, deadline, priority và trạng thái quá hạn.
- [ ] Tạo `src/app/features/operations/task-filters/task-filters.component.ts` lọc theo assignee, deadline, customer, order và overdue.
- [ ] Tạo `src/app/features/operations/actions/update-task-status.action.ts` cập nhật assignee, deadline và status của task.

### 4.6) Dashboard module

- [ ] Tạo `src/app/features/dashboard/dashboard.routes.ts` khai báo route `/dashboard`.
- [ ] Tạo `src/app/features/dashboard/models/dashboard.model.ts` định nghĩa DashboardKpis, RevenuePoint, OverdueOrder và OverdueTask.
- [ ] Tạo `src/app/features/dashboard/services/dashboard.service.ts` đọc dữ liệu từ Supabase views.
- [ ] Tạo `src/app/features/dashboard/dashboard.page.ts` làm container cho KPI cards, chart và tables.
- [ ] Tạo `src/app/features/dashboard/dashboard.page.html` bố trí grid Dashboard theo spec.
- [ ] Tạo `src/app/features/dashboard/kpi-cards/kpi-cards.component.ts` đọc dữ liệu thật từ dashboard service.
- [ ] Tạo `src/app/features/dashboard/revenue-chart/revenue-chart.component.ts` đọc dữ liệu thật từ dashboard service.
- [ ] Tạo `src/app/features/dashboard/overdue-orders-table/overdue-orders-table.component.ts` đọc dữ liệu thật từ dashboard service.
- [ ] Tạo `src/app/features/dashboard/overdue-tasks-table/overdue-tasks-table.component.ts` đọc dữ liệu thật từ dashboard service.

### 4.7) Audit, export và reporting

- [ ] Tạo `src/app/core/audit/audit-log.service.ts` ghi thay đổi customer, quotation, order và task status.
- [ ] Tạo `src/app/shared/components/activity-log/activity-log.component.ts` hiển thị actor, timestamp và event details.
- [ ] Gắn `src/app/shared/components/activity-log/activity-log.component.ts` vào Customer Detail.
- [ ] Gắn `src/app/shared/components/activity-log/activity-log.component.ts` vào Order Detail.
- [ ] Tạo `src/app/core/export/excel-export.service.ts` để export quotation list và order list.
- [ ] Tạo `src/app/core/export/pdf-export.service.ts` để export quotation detail.

## 5) Kiểm thử và chất lượng

- [ ] Tạo file `docs/test-plan.md` liệt kê test cases cho login, customer, quotation, order, task và dashboard.
- [ ] Tạo `src/app/core/permissions/permission.service.spec.ts` cho policy service.
- [ ] Tạo `src/app/shared/directives/has-permission.directive.spec.ts` cho permission directive.
- [ ] Tạo `src/app/features/quotations/rules/discount-rule.spec.ts` cho discount rule.
- [ ] Tạo `src/app/features/orders/rules/order-status-rule.spec.ts` cho order status rule.
- [ ] Tạo `src/app/features/orders/services/order-task-factory.service.spec.ts` cho auto-create operations tasks.
- [ ] Tạo `src/app/features/dashboard/services/dashboard.service.spec.ts` mock Supabase views và kiểm tra mapping dữ liệu Dashboard.
- [ ] Tạo `e2e/auth-login.spec.ts` cho flow login/logout và route guard.
- [ ] Tạo `e2e/customer-quotation.spec.ts` cho flow login → tạo customer → tạo quotation.
- [ ] Tạo `e2e/quotation-to-order.spec.ts` cho flow convert quotation thành order → confirm order → task sinh tự động.
- [ ] Tạo `e2e/manager-discount-approval.spec.ts` cho role Manager duyệt discount/exception.
- [ ] Cài axe/playwright hoặc plugin tương đương và tạo `e2e/accessibility.spec.ts` cho Dashboard, forms và Task Board.
- [ ] Chạy smoke test trên môi trường staging trước mỗi demo khách hàng và ghi kết quả vào `docs/demo-smoke-test-log.md`.

## 6) Go-to-market và bán thử

- [ ] Tạo file `docs/pricing.md` đề xuất gói Starter, Growth và Pro theo số user/chi nhánh.
- [ ] Tạo file `docs/demo-script.md` mô tả kịch bản demo 15 phút cho chủ SME.
- [ ] Tạo file `docs/sales-one-pager.md` tóm tắt vấn đề, giải pháp, lợi ích, module chính và giá trị ROI.
- [ ] Tạo file `docs/pilot-onboarding-checklist.md` mô tả các bước triển khai pilot cho 1 SME đầu tiên.
- [ ] Tạo file `docs/customer-feedback-template.md` để ghi nhận feedback sau mỗi buổi demo/pilot.
- [ ] Tạo file `docs/demo-data/distribution.md` mô tả bộ dữ liệu demo cho ngành phân phối hàng hóa.
- [ ] Tạo file `docs/demo-data/b2b-services.md` mô tả bộ dữ liệu demo cho ngành dịch vụ B2B.
- [ ] Tạo file `docs/demo-data/small-manufacturing.md` mô tả bộ dữ liệu demo cho ngành sản xuất nhỏ.
- [ ] Tạo file `docs/target-sme-list.md` ghi danh sách 5 SME mục tiêu để phỏng vấn và demo sản phẩm.
- [ ] Tạo file `docs/pilot-plan.md` mô tả kế hoạch pilot 2-4 tuần với 1 khách hàng đầu tiên.
- [ ] Tạo file `docs/pilot-blocker-log.md` để ghi lại blocker lớn nhất trong quá trình pilot.

## 7) Vận hành sau khi có khách hàng đầu tiên

- [ ] Tạo file `docs/support-process.md` mô tả cách tiếp nhận bug, phân loại mức độ nghiêm trọng và thời gian phản hồi.
- [ ] Tạo file `docs/release-checklist.md` liệt kê các bước cần làm trước khi release production.
- [ ] Tạo file `docs/backup-and-restore.md` mô tả lịch backup Supabase và cách restore dữ liệu.
- [ ] Tạo file `docs/sentry-dashboard-setup.md` mô tả các widget/cảnh báo cần có để theo dõi lỗi frontend quan trọng.
- [ ] Tạo file `docs/data-migration-checklist.md` mô tả checklist migration dữ liệu từ Excel của khách hàng sang hệ thống.
- [ ] Tạo file `docs/admin-training-plan.md` mô tả buổi training 60 phút cho Admin.
- [ ] Tạo file `docs/sales-training-plan.md` mô tả buổi training 60 phút cho Sales.
- [ ] Tạo file `docs/operations-training-plan.md` mô tả buổi training 60 phút cho Operations.
