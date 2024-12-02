// Trigger animation on load
window.addEventListener("load", () => {
  document
    .querySelector(".header__boot-image")
    .classList.add("header__boot-image--show");
});

// Navbar color change on scroll
window.addEventListener("scroll", () => {
  const navbar = document.querySelector(".navbar");
  if (window.scrollY > 50) {
    navbar.classList.add("navbar--scrolled");
  } else {
    navbar.classList.remove("navbar--scrolled");
  }
});

document.addEventListener("DOMContentLoaded", function () {
  const navbar = document.querySelector(".navbar");
  if (document.body.classList.contains("page-home")) {
    window.addEventListener("scroll", function () {
      if (window.scrollY > 50) {
        navbar.classList.add("navbar--scrolled");
      } else {
        navbar.classList.remove("navbar--scrolled");
      }
    });
  } else {
    // Apply dark background on other pages immediately
    navbar.classList.add("navbar--scrolled");
  }
});

function showTab(tabId) {
  // Hide all tab content
  const tabs = document.querySelectorAll(".tab-products__list");
  tabs.forEach(tab => {
    tab.style.display = "none";
  });

  // Show the selected tab content
  document.getElementById(tabId).style.display = "flex";

  // Update active tab menu
  const tabButtons = document.querySelectorAll(".tab-menu__item");
  tabButtons.forEach(button => {
    button.classList.remove("tab-menu__item--active");
  });
  event.target.classList.add("tab-menu__item--active");
}

function scrollProducts(direction) {
  const container = document.querySelector(".tab-products__list");
  const itemWidth = container.querySelector(".tab-product-card").offsetWidth; // Get the width of a single card
  const gap = 20; // Match the gap defined in your CSS
  const scrollAmount = itemWidth + gap; // Calculate the scroll amount (card width + gap)

  if (direction === "next") {
    container.scrollBy({ left: scrollAmount, behavior: "smooth" });
  } else if (direction === "prev") {
    container.scrollBy({ left: -scrollAmount, behavior: "smooth" });
  }
}

