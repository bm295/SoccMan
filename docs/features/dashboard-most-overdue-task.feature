Feature: Hiển thị task trễ nhất trên dashboard
  Để Manager ưu tiên xử lý công việc có rủi ro cao nhất
  Với vai trò Manager
  Tôi muốn dashboard hiển thị task chưa hoàn thành có số ngày quá hạn lớn nhất

  Scenario: Tìm task trễ nhất và mức vượt ngưỡng escalation
    Given ngưỡng escalation của task là 1 ngày quá hạn
    And task "Prepare package" chưa hoàn thành và đã quá hạn 3 ngày
    And task "Confirm delivery" chưa hoàn thành và đã quá hạn 1 ngày
    And task "Call customer" đã hoàn thành
    When Manager mở dashboard
    Then dashboard hiển thị "Prepare package" là task trễ nhất
    And dashboard hiển thị task đã quá hạn 3 ngày
    And dashboard hiển thị task đã vượt ngưỡng escalation 2 ngày

