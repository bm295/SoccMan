# Data Model MVP

## 1) Nguyên tắc thiết kế

- Tất cả bảng nghiệp vụ chính phải có `organization_id` để hỗ trợ multi-tenant.
- Các bảng cần `created_at`, `created_by`, `updated_at`, `updated_by` khi phù hợp.
- Không hard delete dữ liệu quan trọng; ưu tiên `archived_at` hoặc status.
- Dữ liệu thay đổi trạng thái quan trọng phải ghi vào `audit_logs`.

## 2) organizations

Lưu thông tin tenant/doanh nghiệp sử dụng hệ thống.

| Field | Type | Ghi chú |
|---|---|---|
| id | uuid PK | Định danh organization |
| name | text | Tên doanh nghiệp |
| tax_code | text nullable | Mã số thuế |
| industry | text nullable | Ngành kinh doanh |
| status | text | `active`, `suspended`, `cancelled` |
| settings | jsonb | Ngưỡng discount, overdue, task template |
| created_at | timestamptz | Ngày tạo |
| updated_at | timestamptz | Ngày cập nhật |

## 3) users

Lưu profile người dùng nội bộ, liên kết Supabase Auth user nếu dùng Supabase.

| Field | Type | Ghi chú |
|---|---|---|
| id | uuid PK | Định danh user |
| organization_id | uuid FK | Thuộc organization |
| auth_user_id | uuid nullable | ID từ auth provider |
| full_name | text | Họ tên |
| email | text | Email đăng nhập |
| phone | text nullable | Số điện thoại |
| role | text | `admin`, `manager`, `sales`, `operations`, `accountant` |
| status | text | `active`, `invited`, `disabled` |
| last_login_at | timestamptz nullable | Lần đăng nhập gần nhất |
| created_at | timestamptz | Ngày tạo |
| updated_at | timestamptz | Ngày cập nhật |

## 4) customers

Quản lý khách hàng/lead.

| Field | Type | Ghi chú |
|---|---|---|
| id | uuid PK | Định danh customer |
| organization_id | uuid FK | Thuộc organization |
| owner_id | uuid FK users | Sales phụ trách |
| name | text | Tên công ty/cá nhân |
| contact_name | text nullable | Người liên hệ chính |
| email | text nullable | Email liên hệ |
| phone | text nullable | Số điện thoại |
| address | text nullable | Địa chỉ |
| status | text | `lead`, `active`, `overdue`, `blocked`, `inactive` |
| source | text nullable | Nguồn lead |
| payment_terms_days | int | Số ngày công nợ mặc định |
| overdue_amount | numeric | Tổng nợ quá hạn cache/reporting |
| notes | text nullable | Ghi chú |
| created_at | timestamptz | Ngày tạo |
| updated_at | timestamptz | Ngày cập nhật |

## 5) quotations

Lưu báo giá và thông tin thương mại trước khi thành order.

| Field | Type | Ghi chú |
|---|---|---|
| id | uuid PK | Định danh quotation |
| organization_id | uuid FK | Thuộc organization |
| customer_id | uuid FK customers | Khách hàng |
| owner_id | uuid FK users | Sales phụ trách |
| quote_number | text | Mã báo giá duy nhất trong organization |
| status | text | `draft`, `pending_approval`, `approved`, `sent`, `won`, `lost`, `converted`, `expired` |
| valid_until | date nullable | Ngày hết hiệu lực |
| currency | text | Mặc định `VND` |
| subtotal | numeric | Tổng trước giảm giá |
| discount_amount | numeric | Số tiền giảm |
| discount_percent | numeric | Phần trăm giảm |
| tax_amount | numeric | Thuế nếu có |
| total_amount | numeric | Tổng sau giảm/thuế |
| line_items | jsonb | Danh sách sản phẩm/dịch vụ |
| approval_status | text | `not_required`, `pending`, `approved`, `rejected` |
| approved_by | uuid nullable | Manager/Admin duyệt |
| approved_at | timestamptz nullable | Thời điểm duyệt |
| created_at | timestamptz | Ngày tạo |
| updated_at | timestamptz | Ngày cập nhật |

## 6) orders

Lưu đơn hàng đã hoặc sắp xác nhận.

| Field | Type | Ghi chú |
|---|---|---|
| id | uuid PK | Định danh order |
| organization_id | uuid FK | Thuộc organization |
| quotation_id | uuid nullable FK quotations | Báo giá nguồn |
| customer_id | uuid FK customers | Khách hàng |
| owner_id | uuid FK users | Sales phụ trách |
| order_number | text | Mã đơn hàng duy nhất trong organization |
| status | text | `draft`, `pending_approval`, `confirmed`, `in_progress`, `delivered`, `closed`, `cancelled` |
| payment_status | text | `pending`, `partially_paid`, `paid`, `overdue` |
| order_date | date | Ngày tạo đơn |
| delivery_due_date | date nullable | Hạn giao/bàn giao |
| delivered_at | timestamptz nullable | Thời điểm delivered |
| currency | text | Mặc định `VND` |
| total_amount | numeric | Giá trị đơn |
| paid_amount | numeric | Đã thu |
| outstanding_amount | numeric | Còn phải thu |
| line_items | jsonb | Copy từ quotation hoặc nhập trực tiếp |
| approved_by | uuid nullable | Người duyệt exception |
| approved_at | timestamptz nullable | Thời điểm duyệt |
| created_at | timestamptz | Ngày tạo |
| updated_at | timestamptz | Ngày cập nhật |

## 7) tasks

Quản lý công việc vận hành phát sinh từ order.

| Field | Type | Ghi chú |
|---|---|---|
| id | uuid PK | Định danh task |
| organization_id | uuid FK | Thuộc organization |
| order_id | uuid FK orders | Đơn hàng liên quan |
| assignee_id | uuid nullable FK users | Người phụ trách |
| created_by | uuid FK users | Người/hệ thống tạo |
| title | text | Tên task |
| description | text nullable | Mô tả |
| status | text | `todo`, `in_progress`, `blocked`, `done`, `cancelled` |
| priority | text | `low`, `normal`, `high`, `urgent` |
| due_date | timestamptz nullable | Deadline |
| completed_at | timestamptz nullable | Thời điểm hoàn thành |
| blocked_reason | text nullable | Lý do blocked |
| source | text | `manual`, `order_template`, `system` |
| created_at | timestamptz | Ngày tạo |
| updated_at | timestamptz | Ngày cập nhật |

## 8) payments

Theo dõi khoản phải thu và thanh toán theo order.

| Field | Type | Ghi chú |
|---|---|---|
| id | uuid PK | Định danh payment |
| organization_id | uuid FK | Thuộc organization |
| order_id | uuid FK orders | Đơn hàng liên quan |
| customer_id | uuid FK customers | Khách hàng để query công nợ nhanh |
| recorded_by | uuid FK users | Accountant ghi nhận |
| amount | numeric | Số tiền |
| due_date | date nullable | Hạn thanh toán |
| paid_at | timestamptz nullable | Thời điểm thanh toán |
| method | text nullable | Chuyển khoản, tiền mặt, khác |
| status | text | `pending`, `confirmed`, `overdue`, `voided` |
| reference_code | text nullable | Mã giao dịch/chứng từ |
| notes | text nullable | Ghi chú |
| created_at | timestamptz | Ngày tạo |
| updated_at | timestamptz | Ngày cập nhật |

## 9) audit_logs

Ghi lại hành động quan trọng để truy vết.

| Field | Type | Ghi chú |
|---|---|---|
| id | uuid PK | Định danh log |
| organization_id | uuid FK | Thuộc organization |
| actor_id | uuid nullable FK users | Người thực hiện, null nếu system |
| entity_type | text | `customer`, `quotation`, `order`, `task`, `payment`, `user` |
| entity_id | uuid | ID bản ghi bị tác động |
| action | text | `created`, `updated`, `status_changed`, `approved`, `rejected`, `deleted`, `archived` |
| before_data | jsonb nullable | Snapshot trước thay đổi |
| after_data | jsonb nullable | Snapshot sau thay đổi |
| reason | text nullable | Lý do thay đổi/duyệt |
| ip_address | inet nullable | IP nếu có |
| user_agent | text nullable | User agent nếu có |
| created_at | timestamptz | Thời điểm ghi log |

## 10) Index và constraint đề xuất

- Unique `(organization_id, email)` cho users.
- Unique `(organization_id, quote_number)` cho quotations.
- Unique `(organization_id, order_number)` cho orders.
- Index `(organization_id, status)` trên customers, quotations, orders, tasks và payments.
- Index `(organization_id, due_date)` trên tasks và payments.
- Index `(organization_id, customer_id)` trên quotations, orders và payments.
- RLS bắt buộc lọc theo `organization_id`.
