# Thiết lập project và công khai GitHub

## 1. Mở project trong Android Studio

1. Giải nén folder project.
2. Android Studio → Open → chọn folder gốc `MVVM-Repository-TVGuide`.
3. Kiểm tra JDK 17 và Android SDK Platform 36.
4. Sync Gradle để IDE tải các plugin và thư viện.

### Nếu project cần Gradle Wrapper

Gói source này không kèm `gradle-wrapper.jar` vì môi trường tạo file không có bản phân phối Gradle cục bộ. Nếu Android Studio yêu cầu wrapper, cài Gradle 8.13 rồi mở terminal tại thư mục gốc và chạy:

```powershell
gradle wrapper --gradle-version 8.13
.\gradlew.bat --version
.\gradlew.bat :app:assembleDebug
```

Hoặc tạo một project Empty Activity mới bằng Android Studio, sau đó chép `app/src`, `app/build.gradle.kts`, `settings.gradle.kts`, `build.gradle.kts` và `gradle/libs.versions.toml` của project này vào project mới, giữ lại Gradle Wrapper do Android Studio tạo.

## 2. Công khai repository riêng

Tên repository dự kiến: `MVVM-Repository-TVGuide`.

### Cách nhanh bằng GitHub CLI

1. Cài Git for Windows và GitHub CLI: https://cli.github.com/.
2. Mở PowerShell trong thư mục project.
3. Chạy `gh auth login` và đăng nhập tài khoản GitHub `khangnt6090`.
4. Đảm bảo Git đã có username/email (`git config --global user.name` và `git config --global user.email`).
5. Chạy:

```powershell
.\publish-to-github.ps1
```

Script sẽ tạo repository **public** mới và push source lên GitHub.

### Cách làm thủ công

Vào GitHub → New repository → đặt tên `MVVM-Repository-TVGuide` → chọn Public → Create repository. Sau đó chạy tại thư mục project:

```powershell
git init
git add .
git commit -m "Add Android MVVM Repository Room demo"
git branch -M main
git remote add origin https://github.com/khangnt6090/MVVM-Repository-TVGuide.git
git push -u origin main
```

Sau khi push thành công, link repo sẽ là:

`https://github.com/khangnt6090/MVVM-Repository-TVGuide`

**Chỉ nộp link này sau khi bạn đã tạo repository và thấy các file xuất hiện trên GitHub.**
