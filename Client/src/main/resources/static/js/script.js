document.addEventListener("DOMContentLoaded", function () {
  // Load header
  fetch("/header.html")
    .then((response) => response.text())
    .then((html) => {
      document.getElementById("header").innerHTML = html;
    })
    .catch((error) => console.error("Error loading the header:", error));

  // Load footer
  fetch("/footer.html")
    .then((response) => response.text())
    .then((html) => {
      document.getElementById("footer").innerHTML = html;
    })
    .catch((error) => console.error("Error loading the footer:", error));

    fetch("/menuaccount.html")
        .then(response => response.text())
        .then(html => {
            document.getElementById("menuContainer").innerHTML = html;
        })
        .catch(err => console.error("Error loading menu:", err));
});

function loadContent(page) {
    const contentContainer = document.getElementById('rightContent');
    console.log(page)
    fetch(page)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to load content');
            }
            return response.text();
        })
        .then(html => {
            contentContainer.innerHTML = html;
            initializeTabs();
        })
        .catch(error => {
            contentContainer.innerHTML = '<p class="text-danger">Error loading content. Please try again.</p>';
            console.error(error);
        });
}

function initializeTabs() {
    const tabs = document.querySelectorAll('.order-tabs-head');
    const contents = document.querySelectorAll('.order-tabs-content');
    tabs.forEach((tab) => {
        tab.addEventListener('click', (e) => {
            // Xóa lớp active khỏi tất cả các tab
            tabs.forEach((tab) => tab.classList.remove('order-tabs-head-active'));
            contents.forEach((content) => content.classList.remove('active'));

            // Thêm lớp active vào tab được nhấn
            e.target.classList.add('order-tabs-head-active');

            // Hiển thị nội dung tương ứng
            const targetId = e.target.getAttribute('data-id');
            document.getElementById(targetId).classList.add('active');
        });
    });
}

// Load nội dung modal từ wishlist.html
function loadWishlistModal() {
    const container = document.getElementById("wishlistContainer");
    if (container) {
        fetch("wishlist.html")
            .then((response) => response.ok ? response.text() : "")
            .then((html) => {
                if (html) container.innerHTML = html;
            });
    }
}

    // Toggle wishlist modal
    function toggleWishlistModal() {
        const modal = document.getElementById("wishlistModal");
        if (modal) modal.style.display = modal.style.display === "block" ? "none" : "block";
    }

// Close modal when clicking outside
window.onclick = function (event) {
    const modal = document.getElementById("wishlistModal");
    if (event.target === modal) closeModal();
};

// Close modal function
function closeModal() {
    const modal = document.getElementById("wishlistModal");
    if (modal) modal.style.display = "none";
}

// Expose functions globally
window.toggleWishlistModal = toggleWishlistModal;
window.closeModal = closeModal;
