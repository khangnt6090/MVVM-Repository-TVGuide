# Kịch bản thuyết trình (ước lượng 8–10 phút)

1. **Mở đầu (30s):** giới thiệu vấn đề — khi app lớn dần, đặt UI, gọi mạng và xử lý dữ liệu chung trong Activity sẽ khó bảo trì.
2. **MVVM (1 phút):** Model là dữ liệu, View là giao diện, ViewModel nhận thao tác và cung cấp trạng thái cho View.
3. **Repository (1 phút):** ViewModel không cần biết dữ liệu đến từ API hay database; Repository đứng ra điều phối.
4. **Kiến trúc project (1 phút):** đi qua `domain`, `data`, `presentation`, `ui`.
5. **Cách lưu dữ liệu (1 phút):** Entity mô tả bảng; DAO chứa truy vấn; Room quản lý SQLite.
6. **Luồng dữ liệu (1 phút):** API → Repository → Room → Flow → ViewModel → Compose.
7. **Demo online/offline (2 phút):** tải lần đầu, bật Airplane mode, mở lại app, xác nhận dữ liệu cache vẫn hiển thị.
8. **Đánh giá (1 phút):** lợi ích về phân tách trách nhiệm, kiểm thử, offline; hạn chế về cache expiration, pagination, migration và đồng bộ.
9. **Kết luận (30s):** kiến trúc tốt không chỉ làm code gọn hơn mà giúp ứng dụng dễ mở rộng và bảo trì.

## Câu hỏi có thể được hỏi

**1. Vì sao không để ViewModel gọi API trực tiếp?**  
Vì ViewModel sẽ biết chi tiết nguồn dữ liệu, khiến nó khó thay đổi và khó kiểm thử. Repository gom logic truy cập dữ liệu vào một chỗ.

**2. Room có phải là database không?**  
Room là lớp trừu tượng trên SQLite, cung cấp Entity, DAO và Database để làm việc với dữ liệu có cấu trúc.

**3. Nếu không có Internet thì điều gì xảy ra?**  
Flow tiếp tục phát dữ liệu đã lưu trong Room. Refresh thất bại thì app báo lỗi, nhưng dữ liệu cũ vẫn còn.

**4. Có phải Repository luôn cần hai nguồn dữ liệu?**  
Không. Repository là lớp trung gian quản lý truy cập dữ liệu; demo này dùng cả remote API và local database để minh họa cache/offline.

**5. Bản demo còn thiếu gì để dùng production?**  
Migration, cache expiration, pagination, retry/backoff, kiểm thử, xử lý loading/error chi tiết hơn và chính sách đồng bộ.
