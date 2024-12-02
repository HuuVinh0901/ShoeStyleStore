fetch("/breadcrumb.html")
  .then((response) => response.text())
  .then((data) => {
    document.getElementById("breadcrumb-container").innerHTML = data;

    // Cập nhật tên trang sau khi breadcrumb được tải
    document.getElementById("current-page").textContent = "MyAccount";
  });