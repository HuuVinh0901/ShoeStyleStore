// script.js
document.addEventListener("DOMContentLoaded", () => {
  const addAddressBtn = document.getElementById("addAddressBtn");
  const modal = document.getElementById("modal");
  const cancelBtn = document.getElementById("cancelBtn");
  const addressForm = document.getElementById("addressForm");
  const addressList = document.querySelector(".address-list");

  // Show modal
  addAddressBtn.addEventListener("click", () => {
    modal.classList.remove("hidden");
  });

  // Hide modal
  cancelBtn.addEventListener("click", () => {
    modal.classList.add("hidden");
  });

  // Handle form submission
  addressForm.addEventListener("submit", (e) => {
    e.preventDefault();

    const city = document.getElementById("city").value;
    const district = document.getElementById("district").value;
    const ward = document.getElementById("ward").value;
    const street = document.getElementById("street").value;

    const addressCard = document.createElement("div");
    addressCard.classList.add("address-card");

    addressCard.innerHTML = `
      <div class="address-info">
        <h3>New</h3>
        <p>${city}, ${district}, ${ward}</p>
        <p>${street}</p>
      </div>
      <div class="address-actions">
        <button class="btn-edit">✏️</button>
        <button class="btn-delete">🗑️</button>
      </div>
    `;

    addressList.appendChild(addressCard);
    modal.classList.add("hidden");
    addressForm.reset();
  });
});
