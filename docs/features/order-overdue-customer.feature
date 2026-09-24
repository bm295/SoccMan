Feature: Duyệt đơn hàng cho khách hàng quá hạn
  Để kiểm soát rủi ro công nợ
  Với vai trò Sales
  Tôi muốn đơn hàng của khách hàng quá hạn phải được cấp quản lý phê duyệt

  Scenario: Sales xác nhận đơn hàng mới của khách hàng đang quá hạn
    Given khách hàng "Công ty An Phát" đang có trạng thái "overdue"
    And đơn hàng có customer, line items, tổng tiền, owner và payment terms hợp lệ
    And người dùng hiện tại có vai trò "Sales"
    When người dùng xác nhận đơn hàng
    Then hệ thống không chuyển đơn hàng sang trạng thái "confirmed"
    And hệ thống chuyển đơn hàng sang trạng thái "pending_approval"
    And hệ thống yêu cầu người có vai trò "Manager" hoặc "Admin" duyệt exception
    And người duyệt phải nhập lý do exception
