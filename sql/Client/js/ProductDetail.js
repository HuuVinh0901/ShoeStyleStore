// Change the main product image based on thumbnail click
function changeImage(image) {
  document.getElementById("mainProductImage").src = image;
  const thumbnails = document.querySelectorAll(".thumbnail-images img");
  thumbnails.forEach((img) => img.classList.remove("active"));
  event.currentTarget.classList.add("active");
}

// Decrease quantity with a minimum of 1
function decreaseQuantity() {
  let quantity = parseInt(document.getElementById("quantity").textContent);
  if (quantity > 1) {
    document.getElementById("quantity").textContent = quantity - 1;
  }
}

// Increase quantity
function increaseQuantity() {
  let quantity = parseInt(document.getElementById("quantity").textContent);
  document.getElementById("quantity").textContent = quantity + 1;
}

// Show the selected tab content
function showTab(tab) {
  document.getElementById("descriptionContent").style.display =
    tab === "description" ? "block" : "none";
  document.getElementById("reviewContent").style.display =
    tab === "review" ? "block" : "none";

  // Update active tab style
  const tabs = document.querySelectorAll(".tabs .tab");
  tabs.forEach((t) => t.classList.remove("active"));
  document
    .querySelector(`.tabs .tab[onclick="showTab('${tab}')"]`)
    .classList.add("active");
}

function scrollProducts(direction) {
    const productList = document.querySelector('.related-products__list');
    const productCard = document.querySelector('.product-card');
    const productWidth = productCard.offsetWidth + 15; // including gap between cards

    if (direction === 'next') {
        productList.scrollBy({ left: productWidth, behavior: 'smooth' });
    } else if (direction === 'prev') {
        productList.scrollBy({ left: -productWidth, behavior: 'smooth' });
    }
}

fetch("breadcrumb.html")
  .then((response) => response.text())
  .then((data) => {
    document.getElementById("breadcrumb-container").innerHTML = data;

    // Cập nhật tên trang sau khi breadcrumb được tải
    document.getElementById("current-page").textContent = "ProductDetail";
  });
