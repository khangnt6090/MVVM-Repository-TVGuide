# TV Guide Offline — MVVM + Repository + Room

Ứng dụng Android minh họa **MVVM kết hợp Repository Pattern và Room Database** theo chủ đề Chapter 14 – *Architecture Patterns* trong sách *How to Build Android Applications with Kotlin, Third Edition*.

Ứng dụng tải danh sách TV shows từ **TVmaze API**, lưu dữ liệu vào Room và quan sát dữ liệu cục bộ bằng Kotlin Flow. Sau khi đã tải dữ liệu ít nhất một lần, ứng dụng vẫn có thể hiển thị danh sách đã cache khi mất Internet.

## Chức năng demo

- Gọi REST API công khai của TVmaze bằng Ktor (không cần API key).
- Giải mã JSON bằng Kotlin Serialization.
- Lưu tối đa 50 chương trình đầu tiên vào Room.
- ViewModel cung cấp trạng thái UI qua `StateFlow`.
- Jetpack Compose hiển thị danh sách và trạng thái tải/lỗi.
- Khi offline, dữ liệu cũ vẫn được đọc từ Room; thao tác làm mới báo lỗi nhưng không xóa cache.

## Công nghệ

| Thành phần | Công nghệ |
|---|---|
| Ngôn ngữ | Kotlin |
| Giao diện | Jetpack Compose + Material 3 |
| Kiến trúc | MVVM + Repository Pattern |
| Local database | Room / SQLite |
| Reactive stream | Kotlin Flow / StateFlow |
| HTTP client | Ktor CIO |
| JSON | Kotlin Serialization |
| Poster | Coil |

## Cấu trúc thư mục

```text
app/src/main/java/com/khangnt6090/tvguide/
├── data/
│   ├── local/       # Room Entity, DAO, Database
│   ├── remote/      # TVmaze DTO và Ktor data source
│   └── repository/  # Repository implementation + mapping
├── di/              # AppContainer: tạo database, HTTP client, repository
├── domain/
│   ├── model/       # TvShow
│   └── repository/  # Repository interface
├── presentation/    # ViewModel, UI state, ViewModel factory
├── ui/              # Compose screen và show card
├── MainActivity.kt
└── TvGuideApplication.kt
```

## Yêu cầu môi trường

- Android Studio có hỗ trợ Android Gradle Plugin 8.13.x trở lên.
- JDK 17.
- Android SDK Platform 36.
- Kết nối Internet trong lần Gradle Sync đầu tiên để tải dependencies.

Project được cấu hình với AGP 8.13.2, Gradle 8.13, Kotlin 2.4.10, KSP 2.3.12, Compose BOM 2026.09.00, Lifecycle 2.11.0, Room 2.8.5 và Ktor 3.6.1. Các phiên bản thư viện AndroidX/Compose được đối chiếu với tài liệu chính thức ngày 16/11/2026; xem liên kết ở phần Tài liệu.

## Cách chạy

1. Giải nén project.
2. Mở Android Studio → **Open** → chọn thư mục `MVVM-Repository-TVGuide`.
3. Chờ Gradle Sync hoàn tất; cài Android SDK Platform 36 nếu Android Studio yêu cầu.
4. Chọn emulator hoặc điện thoại Android đã bật USB debugging.
5. Nhấn **Run**.

Ứng dụng cần Internet để tải dữ liệu lần đầu. Không cần cấu hình API key.

## Kịch bản kiểm chứng offline

1. Mở ứng dụng khi có Internet và đợi danh sách xuất hiện.
2. Bật Airplane mode hoặc tắt Wi-Fi/dữ liệu di động.
3. Đóng rồi mở lại ứng dụng.
4. Danh sách đã lưu vẫn xuất hiện vì UI đang quan sát Room.
5. Nhấn **Làm mới**: ứng dụng hiển thị thông báo không thể cập nhật, nhưng danh sách cache không bị xóa.
6. Bật Internet và nhấn **Làm mới** để đồng bộ lại.

> Nếu chạy lần đầu khi không có Internet, database chưa có dữ liệu nên danh sách sẽ trống. Hãy tải dữ liệu online trước khi kiểm thử offline.

## Các file quan trọng để thuyết trình

- `domain/repository/TvShowRepository.kt`: hợp đồng dữ liệu của Repository.
- `data/repository/DefaultTvShowRepository.kt`: điều phối API và Room.
- `data/local/TvShowEntity.kt`, `TvShowDao.kt`, `TvGuideDatabase.kt`: lưu trữ cục bộ.
- `presentation/TvGuideViewModel.kt`: gom dữ liệu thành trạng thái UI.
- `ui/TvGuideScreen.kt`: hiển thị dữ liệu và thao tác làm mới.

## Giới hạn của bản demo

Đây là project học tập tập trung vào luồng MVVM–Repository–Room, không phải ứng dụng TV guide production. Project hiện lấy 50 chương trình đầu từ một endpoint TVmaze; chưa có phân trang, tìm kiếm, lịch phát sóng, retry/backoff, migration schema, unit test hay xử lý cache expiration. Việc dùng `fallbackToDestructiveMigration()` chỉ nhằm giảm cấu hình cho demo.

## Tài liệu tham khảo

1. Alex Forrester, Eran Boudjnah, Alexandru Dumbravan, Jomar Tigcal, *How to Build Android Applications with Kotlin*, Third Edition, Packt, 2025, Chapter 14 – Architecture Patterns, pp. 549–558.
2. TVmaze API: https://www.tvmaze.com/api
3. Android Room: https://developer.android.com/jetpack/androidx/releases/room
4. Jetpack Compose BOM: https://developer.android.com/develop/ui/compose/bom
5. Ktor releases: https://ktor.io/docs/releases.html

## License

Source code demo này được phát hành theo MIT License. Tên sách, nội dung sách và thương hiệu TVmaze thuộc chủ sở hữu tương ứng; ứng dụng chỉ dùng TVmaze API làm nguồn dữ liệu công khai cho mục đích học tập.

## Thiết lập Wrapper và GitHub

Gói source không kèm `gradle-wrapper.jar`. Hướng dẫn tạo Gradle Wrapper 8.13, chạy build trên Windows và công khai repository riêng nằm trong [`docs/SETUP_AND_GITHUB.md`](docs/SETUP_AND_GITHUB.md). Sau khi publish thành công, URL dự kiến là `https://github.com/khangnt6090/MVVM-Repository-TVGuide`.
