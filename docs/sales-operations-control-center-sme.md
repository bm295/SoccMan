# Sales & Operations Control Center cho SME

## 1) Tổng quan sản phẩm

**Sales & Operations Control Center** là một web application theo hướng **all-in-one mini ERP** dành cho doanh nghiệp nhỏ và vừa (SME), tập trung giải quyết bài toán:

- Quản lý khách hàng (CRM mini)
- Quản lý báo giá và đơn hàng
- Điều phối công việc vận hành sau bán
- Theo dõi dòng tiền cơ bản
- Cung cấp dashboard quản trị cho chủ doanh nghiệp

Mục tiêu của phiên bản đầu (MVP) là ưu tiên mạnh vào **Sales + Operations**, sau đó mở rộng dần sang báo cáo và cashflow nâng cao.

---

## 2) Vấn đề business cần giải quyết

Trong nhiều SME, dữ liệu vận hành đang bị phân mảnh:

- Đơn hàng lưu ở Excel
- Khách hàng trao đổi qua Zalo/chat
- Việc vận hành theo dõi trong group chat
- Thu chi ở file riêng
- Báo cáo tổng hợp thủ công

### Hệ quả thường gặp

- Không biết đơn nào đang chậm xử lý
- Không rõ nhân sự nào đang nghẽn việc
- Doanh thu có nhưng dòng tiền thực tế xấu
- Chủ doanh nghiệp không có dashboard nhìn nhanh toàn cục

Ứng dụng này giải quyết trực diện các điểm đau trên bằng một luồng dữ liệu thống nhất từ khách hàng → báo giá → đơn hàng → task vận hành → trạng thái thanh toán.

---

## 3) Vì sao phù hợp với Angular-first

Angular rất phù hợp với bài toán nội bộ doanh nghiệp nhờ các điểm mạnh:

- Form lớn, nhiều business rule
- Dashboard nhiều màn hình
- Role-based UI
- Workflow nhiều trạng thái
- Component tái sử dụng cao
- Phù hợp xây SPA/PWA cho nghiệp vụ nội bộ

### Quyết định tech stack

**Chọn stack chính: Angular 22 + Supabase + PrimeNG + TanStack Query + IndexedDB.**

Lý do chọn stack này cho sản phẩm SME:

- **Angular 22 + TypeScript strict mode + Signals**: phù hợp ứng dụng nghiệp vụ nhiều form, workflow, route guard, permission và dashboard; dùng phiên bản Angular đang được hỗ trợ thay vì Angular 19 đã hết support.
- **Supabase Postgres**: dữ liệu CRM/order/task/cashflow có quan hệ rõ ràng, cần join, filter, báo cáo, phân quyền theo tenant; SQL dễ mở rộng hơn Firestore khi sản phẩm tiến tới commercial SaaS.
- **Supabase Auth + Row Level Security (RLS)**: đáp ứng nhu cầu multi-tenant SME, phân quyền theo công ty/role và giảm rủi ro leak dữ liệu giữa khách hàng.
- **Supabase Storage**: lưu file báo giá, chứng từ, biên bản bàn giao, hóa đơn đính kèm.
- **PrimeNG**: ưu tiên component enterprise như table, filter, dialog, calendar, stepper, badge, menu và data-heavy UI; phù hợp dashboard/quản trị hơn UI marketing.
- **TanStack Query for Angular**: quản lý server state, cache, refetch, optimistic update cho các màn hình CRUD/workflow; tránh tự viết quá nhiều state service thủ công.
- **IndexedDB qua Dexie**: cache local cho lookup data, draft form và khả năng offline/PWA ở phase sau.
- **ApexCharts**: dashboard KPI, revenue chart, overdue chart và workload chart.
- **Playwright + Vitest/Jest**: E2E cho workflow quan trọng và unit test cho policy/rule engine.
- **Vercel hoặc Netlify cho frontend preview; Supabase Cloud cho backend MVP**: triển khai nhanh, dễ demo, dễ tách môi trường dev/staging/prod.

### Stack không chọn làm chính

- **Firebase/Firestore**: rất nhanh cho demo realtime nhưng dữ liệu order/cashflow/reporting dạng quan hệ sẽ phức tạp hơn khi cần join, aggregate, audit và báo cáo quản trị.
- **Angular local-first thuần IndexedDB**: tốt cho prototype/offline demo nhưng không phù hợp làm sản phẩm SME nhiều user, nhiều role, nhiều chi nhánh ngay từ MVP.
- **Angular Material**: ổn định và nhẹ hơn, nhưng PrimeNG có nhiều component data-heavy sẵn hơn cho ERP/CRM mini.

### Stack đề xuất cuối cùng

- **Frontend:** Angular 22, TypeScript strict mode, Signals, Angular Router, Reactive Forms
- **UI:** PrimeNG, PrimeFlex hoặc Tailwind CSS utility layer tối thiểu
- **Backend-as-a-Service:** Supabase Auth, Postgres, RLS, Storage, Realtime selective
- **Server/database logic:** Postgres schema, views/materialized views cho báo cáo, Edge Functions khi cần business action bảo mật
- **Client server-state:** TanStack Query for Angular
- **Client local cache/offline:** Dexie + IndexedDB
- **Charts:** ApexCharts
- **Testing:** Vitest/Jest cho unit tests, Playwright cho E2E
- **Deployment:** Supabase Cloud + Vercel/Netlify preview deployments
- **Monitoring giai đoạn sau:** Sentry cho frontend error tracking

---

## 4) Core modules (phiên bản business)

## 4.1 CRM Mini

Quản lý thông tin khách hàng:

- Thông tin công ty/cá nhân
- Lịch sử giao dịch
- Trạng thái tiềm năng
- Ghi chú chăm sóc
- Lịch follow-up

**Pipeline mẫu:** `Lead → Contacted → Quoted → Won / Lost`

## 4.2 Sales Order Management

Quản lý báo giá và đơn hàng:

- Tạo quotation
- Chuyển quotation thành order
- Theo dõi trạng thái xử lý
- Theo dõi công nợ cơ bản
- Theo dõi deadline giao hàng/bàn giao

**Workflow mẫu:** `Draft → Confirmed → In Progress → Delivered → Paid`

## 4.3 Operations Task Board

Khi đơn hàng được xác nhận, hệ thống tự sinh task vận hành:

- Chuẩn bị hàng
- Xử lý hồ sơ
- Kiểm tra chất lượng
- Bàn giao
- Thu tiền

Ví dụ cho `SO-001`:

1. Call customer
2. Prepare package
3. Confirm delivery
4. Issue invoice

## 4.4 Cashflow Snapshot

Không làm kế toán đầy đủ ngay từ đầu, chỉ cần góc nhìn quản trị nhanh:

- Expected incoming
- Expected outgoing
- Overdue receivables
- Upcoming payables

## 4.5 Management Dashboard

Màn hình điều hành cho chủ doanh nghiệp:

- Doanh thu tháng này
- Số đơn đang xử lý
- Đơn trễ hạn
- Khách hàng mới
- Nhân sự quá tải
- Công nợ đến hạn

---

## 5) Kiến trúc triển khai đề xuất

## Cách 1: Angular + Supabase (khuyến nghị cho sản phẩm thật)

- Auth: Supabase Auth
- Data: Supabase Postgres
- Authorization: Row Level Security theo tenant/role
- File: Supabase Storage
- Realtime: chỉ bật cho task board/notification cần thiết
- Reporting: SQL views/materialized views

**Phù hợp:** MVP có định hướng thương mại, dữ liệu quan hệ rõ ràng, cần báo cáo, audit và multi-tenant.

## Cách 2: Angular + Firebase (ưu tiên MVP demo nhanh)

- Auth: Firebase Authentication
- Data: Firestore
- File: Firebase Storage
- Hosting: Firebase Hosting

**Phù hợp:** demo, MVP nhanh, startup nhỏ, thời gian ra thị trường ngắn.

## Cách 3: Angular local-first (pure Angular)

- Dữ liệu chính lưu IndexedDB
- Export/import JSON hoặc Excel
- Bổ sung sync server ở giai đoạn sau

**Phù hợp:** bản nội bộ desktop, prototype, portfolio, triển khai sớm cho SME nhỏ.

---

## 6) Tính năng tạo cảm giác “enterprise-grade”

## 6.1 Role-based UI

Vai trò tiêu chuẩn:

- Admin
- Sales
- Operations
- Accountant
- Manager

Mỗi role có menu, quyền thao tác, và action khác nhau.

## 6.2 Rule engine nhẹ

Ví dụ rule:

- Đơn > 50 triệu cần duyệt
- Khách overdue thì chặn tạo đơn mới
- Task trễ > 2 ngày hiển thị cảnh báo đỏ

Có thể implement ở Angular qua:

- Policy service
- Route guard
- Action-level permission directive

## 6.3 Audit trail

Lưu lịch sử thay đổi quan trọng:

- Ai sửa đơn
- Sửa lúc nào
- Đổi trạng thái từ gì sang gì

## 6.4 Reusable form engine

Thiết kế form theo schema/config để tái sử dụng:

- Customer form
- Quotation form
- Order form
- Task form

---

## 7) Cấu trúc thư mục Angular 22 gợi ý

```text
src/app
├── core
│   ├── auth
│   ├── layout
│   ├── guards
│   ├── interceptors
│   └── services
├── shared
│   ├── ui
│   ├── components
│   ├── pipes
│   ├── directives
│   └── utils
├── features
│   ├── dashboard
│   ├── crm
│   ├── quotations
│   ├── orders
│   ├── operations
│   ├── cashflow
│   ├── reports
│   └── settings
└── state
    ├── app.store.ts
    ├── auth.store.ts
    └── filters.store.ts
```

---

## 8) Các màn hình trọng tâm nên ưu tiên

### Dashboard

- KPI cards
- Revenue chart
- Đơn gần trễ hạn
- Task quá hạn
- Top khách hàng

### Customer Detail

- Timeline
- Quotations
- Orders
- Notes
- Receivables

### Order Detail

- Thông tin đơn hàng
- Progress bar theo trạng thái
- Linked tasks
- Payment status
- Activity log

### Manager Console

- Duyệt đơn
- Duyệt giảm giá
- Duyệt ngoại lệ

---

## 9) MVP roadmap đề xuất

## Phase 1 (MVP lõi)

- Login
- Customer CRUD
- Quotation CRUD
- Order CRUD
- Dashboard cơ bản

## Phase 2

- Operations Task Board
- Approval flow
- Audit log

## Phase 3

- Cashflow snapshot
- Reports
- Export Excel/PDF

## Phase 4

- PWA
- Offline-first
- Realtime notifications

---

## 10) Giá trị portfolio và khả năng thương mại

Dự án này vượt xa CRUD thông thường vì có đầy đủ:

- Workflow trạng thái
- Permission theo vai trò
- Dashboard/KPI
- Business rule
- Auditability
- Khả năng offline/realtime

Điều này giúp sản phẩm trông giống một hệ thống thực tế có thể triển khai cho SME, đồng thời tạo portfolio mạnh theo hướng enterprise Angular.

---

## 11) To-do list triển khai ra thị trường

Danh sách việc cần làm để đưa sản phẩm tới MVP demo, pilot và bán thử đã được tách sang file riêng: [`docs/market-readiness-todo.md`](market-readiness-todo.md).
