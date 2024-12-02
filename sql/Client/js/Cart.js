document.querySelectorAll(".cart-item__quantity-btn").forEach((button) => {
  button.addEventListener("click", (e) => {
      const isIncrement = e.target.classList.contains("cart-item__quantity-btn--increase");
      const inputElement = e.target.parentNode.querySelector(".cart-item__quantity-input");
      let quantity = parseInt(inputElement.value);

      // Tăng hoặc giảm số lượng
      if (isIncrement) {
          quantity += 1;
      } else if (quantity > 1) {
          quantity -= 1;
      }

      // Cập nhật giá trị input
      inputElement.value = quantity;
  });
});

fetch("breadcrumb.html")
  .then((response) => response.text())
  .then((data) => {
    document.getElementById("breadcrumb-container").innerHTML = data;

    // Cập nhật tên trang sau khi breadcrumb được tải
    document.getElementById("current-page").textContent = "Cart";
  });
