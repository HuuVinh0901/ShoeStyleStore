// Hàm xóa voucher
function deleteVoucher(voucherID, row) {
    // Gửi yêu cầu DELETE tới server để xóa voucher
    fetch(`/admin/vouchers/delete/${voucherID}`, {
        method: "DELETE",
    })
        .then((response) => {
            if (response.ok) {
                alert("Xóa voucher thành công!");
                // Xóa dòng tương ứng trong bảng HTML
                row.remove();  // Xóa dòng trong bảng sau khi xóa thành công
            } else {
                throw new Error("Không thể xóa voucher. Vui lòng thử lại!");
            }
        })
        .catch((error) => {
            console.error(error);
            alert("Lỗi: " + error.message);
        });
}

// Hàm render danh sách voucher
function renderVoucherList(vouchers) {
    const voucherListElement = document.getElementById("voucher-list");
    voucherListElement.innerHTML = ""; // Xóa danh sách hiện tại

    vouchers.forEach((voucher) => {
        const row = document.createElement("tr");
        row.innerHTML += `<td>${voucher.voucherID}</td>`;
        row.innerHTML += `<td>${voucher.name}</td>`;
        row.innerHTML += `<td>
            <span ${voucher.discountType === "Flat" ? 'class="currency"' : ''}>${voucher.discountType === "Flat" ? formatCurrency(voucher.discountValue) : voucher.discountValue + " %"}</span>
        </td>`;
        row.innerHTML += `<td>${voucher.startDate}</td>`;
        row.innerHTML += `<td>${voucher.endDate}</td>`;
        row.innerHTML += `<td>${voucher.status}</td>`;
        row.innerHTML += `<td class="currency">${voucher.minValueOrder}</td>`;
        row.innerHTML += `
            <td>
                <button class="btn btn-primary btn-sm edit-btn">Edit</button>
                <button class="btn btn-danger btn-sm delete-btn" data-id="${voucher.voucherID}">Delete</button>
            </td>
        `;
        voucherListElement.appendChild(row);

        // Thêm sự kiện cho nút Edit
        const editButton = row.querySelector(".edit-btn");
        editButton.addEventListener("click", function () {
            handleEditVoucher(this);
        });

        // Thêm sự kiện cho nút Delete
        const deleteButton = row.querySelector(".delete-btn");
        deleteButton.addEventListener("click", function () {
            const voucherID = this.getAttribute("data-id");
            if (confirm("Bạn có chắc chắn muốn xóa voucher này không?")) {
                deleteVoucher(voucherID, row);
            }
        });
    });
}
document.getElementById("voucher-form").addEventListener("submit", function (event) {
    // Ngăn hành vi mặc định của form (submit)
    event.preventDefault();

    const name = document.getElementById("tenPhieuGiamGia").value.trim();
    const description = document.getElementById("moTa").value.trim();
    const discountType = document.querySelector('input[name="discount-type"]:checked')?.value;
    const discountValue = parseFloat(document.getElementById("phanTramGiam").value);
    const minValueOrder = parseFloat(document.getElementById("giaTriDonToiThieu").value);
    const startDate = document.getElementById("ngayBatDau").value;
    const endDate = document.getElementById("ngayKetThuc").value;

    const errors = [];

    // Kiểm tra các điều kiện
    if (!name) {
        errors.push("Vui lòng nhập tên phiếu giảm giá.");
    }
    if (!description) {
        errors.push("Vui lòng nhập mô tả.");
    }
    if (!discountType) {
        errors.push("Vui lòng chọn kiểu giảm giá.");
    }
    if (!discountValue || isNaN(discountValue) || discountValue <= 0) {
        errors.push("Vui lòng nhập giá trị giảm giá hợp lệ.");
    }
    if (!minValueOrder || isNaN(minValueOrder) || minValueOrder < 0) {
        errors.push("Vui lòng nhập giá trị đơn tối thiểu hợp lệ.");
    }
    if (!startDate) {
        errors.push("Vui lòng chọn ngày bắt đầu.");
    }
    if (!endDate) {
        errors.push("Vui lòng chọn ngày kết thúc.");
    }

    // Kiểm tra logic ngày
    const today = new Date();
    const start = new Date(startDate);
    const end = new Date(endDate);

    if (startDate && endDate) {
        if (end < start) {
            errors.push("Ngày kết thúc không được nhỏ hơn ngày bắt đầu.");
        }
        if (end < today) {
            errors.push("Ngày kết thúc không được nhỏ hơn ngày hiện tại.");
        }
    }

    // Nếu có lỗi, hiển thị và dừng xử lý
    if (errors.length > 0) {
        alert(errors.join("\n"));
        return; // Ngừng xử lý tiếp theo
    }

    // Nếu không có lỗi, tiếp tục xử lý
    fetch("http://localhost:8080/vouchers/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            name,
            description,
            discountType,
            discountValue,
            minValueOrder,
            startDate,
            endDate,
        }),
    })
        .then(response => {
            if (response.ok) {
                alert("Voucher added successfully!");
                location.reload();
                return response.json();
            } else {
                throw new Error("Có lỗi xảy ra khi thêm voucher.");
            }
        })
        .catch(error => {
            alert("Error: " + error.message);
        });
});

document.getElementById("voucher-form-edit").addEventListener("submit", function (event) {
    event.preventDefault();
    const voucherId = this.dataset.voucherId;
    const name = document.getElementById("tenPhieuGiamGiaEdit").value.trim();
    const description = document.getElementById("moTa-edit").value.trim();
    const discountType = document.querySelector('input[name="discount-type-edit"]:checked')?.value;
    const discountValue = parseFloat(document.getElementById("phanTramGiam-edit").value);
    const minValueOrder = parseFloat(document.getElementById("giaTriDonToiThieu-edit").value);
    const startDate = document.getElementById("ngayBatDau-edit").value;
    const endDate = document.getElementById("ngayKetThuc-edit").value;
    console.log(discountType)
    const errors = [];
    if (!name) {
        errors.push("Vui lòng nhập tên phiếu giảm giá.");
    }
    if (!description) {
        errors.push("Vui lòng nhập mô tả.");
    }
    if (!discountType) {
        errors.push("Vui lòng chọn kiểu giảm giá.");
    }
    if (!discountValue || isNaN(discountValue) || discountValue <= 0) {
        errors.push("Vui lòng nhập giá trị giảm giá hợp lệ.");
    }
    if (!minValueOrder || isNaN(minValueOrder) || minValueOrder < 0) {
        errors.push("Vui lòng nhập giá trị đơn tối thiểu hợp lệ.");
    }
    if (!startDate) {
        errors.push("Vui lòng chọn ngày bắt đầu.");
    }
    if (!endDate) {
        errors.push("Vui lòng chọn ngày kết thúc.");
    }
    const today = new Date();
    const start = new Date(startDate);
    const end = new Date(endDate);

    if (startDate && endDate) {
        if (end < start) {
            errors.push("Ngày kết thúc không được nhỏ hơn ngày bắt đầu.");
        }
        if (end < today) {
            errors.push("Ngày kết thúc không được nhỏ hơn ngày hiện tại.");
        }
    }
    if (errors.length > 0) {
        alert(errors.join("\n"));
        return;
    }
    const updatedVoucher = {
        name: name,
        description: description,
        discountType: discountType,
        discountValue: discountValue,
        minValueOrder: minValueOrder,
        startDate: startDate,
        endDate: endDate,
    };
    fetch(`/admin/vouchers/${voucherId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(updatedVoucher),
    })
        .then((response) => {
            if (response.ok) {
                return response.json();
            }
            throw new Error("Cập nhật không thành công");
        })
        .then((data) => {
            alert(`Voucher ${data.name} đã được cập nhật thành công!`);
            location.reload();
        })
        .catch((error) => {
            alert(error.message);
            console.error(error);
        });
});


function formatCurrency(value) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
}

function handleSearch() {
    // Lấy giá trị từ các input date
    const startDate = document.getElementById("searchStartDate").value;
    const endDate = document.getElementById("searchEndDate").value;

    // Kiểm tra nếu các giá trị ngày hợp lệ
    if (!startDate || !endDate) {
        alert("Vui lòng chọn cả ngày bắt đầu và ngày kết thúc.");
        return;
    }

    // Gọi API để tìm kiếm các voucher theo ngày
    fetch(`/admin/vouchers/search?startDate=${startDate}&endDate=${endDate}`)
        .then(response => response.json())
        .then(data => {
            // Xử lý dữ liệu trả về từ API
            renderVoucherList(data);
        })
        .catch(error => {
            console.error("Lỗi khi tìm kiếm:", error);
        });
}

document.querySelectorAll('.currency').forEach((element) => {
    const value = parseFloat(element.textContent.trim());
    if (!isNaN(value)) {
        element.textContent = formatCurrency(value);
    }
});
document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".edit-btn").forEach((button) => {
        button.addEventListener("click", function () {
            console.log("check")
            handleEditVoucher(this);

        });
    });

    document.querySelectorAll(".delete-btn").forEach((button) => {
        button.addEventListener("click", function () {
            const voucherID = this.getAttribute("data-id");
            if (confirm("Bạn có chắc chắn muốn xóa voucher này không?")) {
                const row = this.closest("tr");
                deleteVoucher(voucherID, row);
            }
        });
    });
});

function handleEditVoucher(button) {
    const row = button.closest("tr");
    const voucherId = row.querySelector("td:first-child").textContent;

    // Gọi API để lấy thông tin voucher
    fetch(`/admin/vouchers/${voucherId}`)
        .then((response) => {
            if (response.ok) {
                return response.json();
            }
            throw new Error("Không thể lấy thông tin voucher");
        })
        .then((voucher) => {
            // Hiển thị thông tin lên form chỉnh sửa
            document.getElementById("tenPhieuGiamGiaEdit").value = voucher.name;
            document.getElementById("moTa-edit").value = voucher.description;
            document.getElementById("phanTramGiam-edit").value = voucher.discountValue;
            document.getElementById("giaTriDonToiThieu-edit").value = voucher.minValueOrder;
            document.getElementById("ngayBatDau-edit").value = voucher.startDate;
            document.getElementById("ngayKetThuc-edit").value = voucher.endDate;

            // Set radio button
            document.getElementById(
                voucher.discountType === "Percentage"
                    ? "discount-percentage-edit"
                    : "discount-flat-edit"
            ).checked = true;

            // Lưu voucherID vào form chỉnh sửa
            document.getElementById("voucher-form-edit").dataset.voucherId = voucherId;
        })
        .catch((error) => {
            alert(error.message);
            console.error(error);
        });
}

// // Khi DOM đã tải xong
// document.addEventListener("DOMContentLoaded", function () {
//     const deleteButtons = document.querySelectorAll(".delete-btn");
//
//     // Duyệt qua tất cả các nút delete trong bảng hiện tại và gắn sự kiện
//     deleteButtons.forEach((button) => {
//         button.addEventListener("click", function () {
//             const voucherID = this.getAttribute("data-id");
//             if (confirm("Bạn có chắc chắn muốn xóa voucher này không?")) {
//                 // Gọi hàm deleteVoucher khi click vào nút xóa
//                 const row = this.closest("tr");  // Tìm dòng tr chứa nút delete
//                 deleteVoucher(voucherID, row);
//             }
//         });
//     });
// });




//
//
// function renderVoucherList(vouchers) {
//     const voucherListElement = document.getElementById("voucher-list");
//     voucherListElement.innerHTML = ""; // Xóa danh sách hiện tại
//
//     // Render lại danh sách voucher sau khi nhận dữ liệu từ API
//     vouchers.forEach(voucher => {
//         const row = document.createElement("tr");
//         row.innerHTML += `<td>${voucher.voucherID}</td>`;
//         row.innerHTML += `<td>${voucher.name}</td>`;
//         row.innerHTML += `<td>
//             <span ${voucher.discountType === 'Flat' ? 'class="currency"' : ''}>${voucher.discountType === 'Flat' ? formatCurrency(voucher.discountValue) : voucher.discountValue + ' %'}</span>
//         </td>`;
//         row.innerHTML += `<td>${voucher.startDate}</td>`;
//         row.innerHTML += `<td>${voucher.endDate}</td>`;
//         row.innerHTML += `<td>${voucher.status}</td>`;
//         row.innerHTML += `<td class="currency">${voucher.minValueOrder}</td>`;
//         row.innerHTML += `
//             <td>
//                 <button class="btn btn-primary btn-sm">Edit</button>
//                 <button class="btn btn-danger btn-sm delete-btn" data-id="${voucher.voucherID}">Delete</button>
//             </td>
//         `;
//         voucherListElement.appendChild(row);
//     });
// }



// document.addEventListener("DOMContentLoaded", function () {
//     const deleteButtons = document.querySelectorAll(".delete-btn");
//
//     deleteButtons.forEach((button) => {
//         button.addEventListener("click", function () {
//             const voucherID = this.getAttribute("data-id");
//             console.log(voucherID)
//             if (confirm("Bạn có chắc chắn muốn xóa voucher này không?")) {
//                 fetch(`/admin/vouchers/delete/${voucherID}`, {
//                     method: "DELETE",
//                 })
//                     .then((response) => {
//                         if (response.ok) {
//                             alert("Xóa voucher thành công!");
//                             // Xóa dòng tương ứng trong bảng HTML
//                             this.closest("tr").remove();
//                         } else {
//                             throw new Error("Không thể xóa voucher. Vui lòng thử lại!");
//                         }
//                     })
//                     .catch((error) => {
//                         console.error(error);
//                         alert("Lỗi: " + error.message);
//                     });
//             }
//         });
//     });
// });

// document.addEventListener("DOMContentLoaded", () => {
//     document.querySelectorAll(".btn-primary").forEach((button) => {
//         button.addEventListener("click", (event) => {
//             const row = event.target.closest("tr");
//             const voucherId = row.querySelector("td:first-child").textContent;
//
//             // Gọi API để lấy thông tin voucher
//             fetch(`/admin/vouchers/${voucherId}`)
//                 .then((response) => {
//                     if (response.ok) {
//                         return response.json();
//                     }
//                     throw new Error("Không thể lấy thông tin voucher");
//                 })
//                 .then((voucher) => {
//                     // Hiển thị thông tin lên form
//                     document.getElementById("tenPhieuGiamGiaEdit").value = voucher.name;
//                     document.getElementById("moTa-edit").value = voucher.description;
//                     document.getElementById("phanTramGiam-edit").value = voucher.discountValue;
//                     document.getElementById("giaTriDonToiThieu-edit").value = voucher.minValueOrder;
//                     document.getElementById("ngayBatDau-edit").value = voucher.startDate;
//                     document.getElementById("ngayKetThuc-edit").value = voucher.endDate;
//
//                     // Set radio button
//                     document.getElementById(
//                         voucher.discountType === "Percentage"
//                             ? "discount-percentage-edit"
//                             : "discount-flat-edit"
//                     ).checked = true;
//                     document.getElementById("voucher-form-edit").dataset.voucherId = voucherId;
//                 })
//                 .catch((error) => {
//                     alert(error.message);
//                     console.error(error);
//                 });
//         });
//     });
// });



// function updateVoucherTable(voucher) {
//     const voucherList = document.getElementById("voucher-list");
//
//     // Tạo một dòng mới
//     const row = document.createElement("tr");
//
//     // Thêm nội dung vào dòng mới
//     row.innerHTML = `
//         <td>${voucher.voucherID}</td>
//         <td>${voucher.name}</td>
//         <td>${voucher.discountValue}</td>
//         <td>${new Date(voucher.startDate).toLocaleString()}</td>
//         <td>${new Date(voucher.endDate).toLocaleString()}</td>
//         <td>${voucher.status}</td>
//         <td>${voucher.minValueOrder}</td>
//         <td>
//             <button class="btn btn-primary btn-sm">Edit</button>
//             <button class="btn btn-danger btn-sm delete-btn" data-id="${voucher.voucherID}">Delete</button>
//         </td>
//     `;
//
//     // Thêm dòng mới vào cuối bảng
//     voucherList.appendChild(row);
// }



// document.getElementById("voucher-form-edit").addEventListener("submit", function (event) {
//     event.preventDefault();
//
//     const voucherId = this.dataset.voucherId;
//     const updatedVoucher = {
//         name: document.getElementById("tenPhieuGiamGiaEdit").value.trim(),
//         description: document.getElementById("moTa-edit").value.trim(),
//         discountType: document.querySelector('input[name="discount-type"]:checked')?.value,
//         discountValue: parseFloat(document.getElementById("phanTramGiam-edit").value),
//         minValueOrder: parseFloat(document.getElementById("giaTriDonToiThieu-edit").value),
//         startDate: document.getElementById("ngayBatDau-edit").value,
//         endDate: document.getElementById("ngayKetThuc-edit").value,
//     };
//
//     fetch(`/admin/vouchers/${voucherId}`, {
//         method: "PUT",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify(updatedVoucher),
//     })
//         .then((response) => {
//             if (response.ok) {
//                 return response.json();
//             }
//             throw new Error("Cập nhật không thành công");
//         })
//         .then((data) => {
//             alert(`Voucher ${data.name} đã được cập nhật thành công!`);
//             // Reload lại danh sách sau khi cập nhật
//             location.reload();
//         })
//         .catch((error) => {
//             alert(error.message);
//             console.error(error);
//         });
// });


