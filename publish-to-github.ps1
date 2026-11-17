$ErrorActionPreference = "Stop"
Set-Location $PSScriptRoot
$repo = "khangnt6090/MVVM-Repository-TVGuide"

if (-not (Get-Command git -ErrorAction SilentlyContinue)) {
    throw "Chua cai Git. Cai Git for Windows truoc: https://git-scm.com/download/win"
}
if (-not (Get-Command gh -ErrorAction SilentlyContinue)) {
    throw "Chua cai GitHub CLI. Cai tai https://cli.github.com/ roi dang nhap bang lenh: gh auth login"
}

gh auth status | Out-Null
if (-not (Test-Path ".git")) {
    git init
}
$pending = git status --porcelain
if ($pending) {
    git add .
    git commit -m "Add Android MVVM Repository Room demo"
} else {
    Write-Host "Working tree da sach; bo qua commit moi."
}
git branch -M main

# Creates a new PUBLIC repository under the authenticated GitHub account and pushes this project.
gh repo create $repo --public --source . --remote origin --push
Write-Host "Da tao repository: https://github.com/$repo"
