const apiBaseUrl = 'http://localhost:8080/auth/users'; // Cơ sở URL API
const apiUrl = `${apiBaseUrl}/list`; // Endpoint cho danh sách người dùng
const apiAddUrl = `${apiBaseUrl}/add`; // Endpoint để thêm user
const apiSearchUrl = `${apiBaseUrl}/search`; // Endpoint cho tìm kiếm
const apiEditUrl = `${apiBaseUrl}/edit`; // Endpoint để chỉnh sửa user

// Load toàn bộ danh sách người dùng
async function loadUserList() {
    try {
        const response = await fetch(apiUrl, {
            method: 'GET',
            headers: { 'Accept': 'application/json' },
        });

        if (response.ok) {
            const users = await response.json(); // Chuyển đổi JSON
            renderUserList(users); // Hiển thị danh sách người dùng
        } else {
            console.error(`Lỗi tải danh sách: ${response.status}`);
            alert('Không thể tải danh sách người dùng từ server.');
        }
    } catch (error) {
        console.error('Lỗi khi tải danh sách người dùng:', error);
        alert('Có lỗi xảy ra khi tải danh sách người dùng.');
    }
}

// Render danh sách người dùng ra giao diện
function renderUserList(users) {
    const userListContainer = document.getElementById('user-list');

    // Kiểm tra nếu không tìm thấy container
    if (!userListContainer) {
        console.error("Phần tử với ID 'user-list' không tồn tại trong DOM.");
        return;
    }

    // Xóa danh sách cũ
    userListContainer.innerHTML = '';

    // Kiểm tra nếu không có người dùng
    if (!users || users.length === 0) {
        userListContainer.innerHTML = `
            <tr>
                <td colspan="8" class="text-center text-muted">Không có người dùng nào được tìm thấy.</td>
            </tr>
        `;
        return;
    }

//     // Duyệt danh sách người dùng và thêm vào giao diện
//     users.forEach(user => {
//         const userElement = document.createElement('tr');
//
//         userElement.innerHTML = `
//             <td>${user.userID}</td>
//             <td>${user.name}</td>
//             <td>${user.userName}</td>
//             <td>${user.password ? '********' : 'N/A'}</td>
//             <td>${user.ci}</td>
//             <td>${user.role?.name}</td>
//             <td>${user.status}</td>
//             <td>
//                 <button onclick="confirmEdit(${user.userID})" class="btn btn-warning btn-sm">Edit</button>
//                 <button onclick="deleteUser(${user.userID})" class="btn btn-danger btn-sm">Delete</button>
//             </td>
//         `;
//         // Gắn vào danh sách người dùng
//         userListContainer.appendChild(userElement);
//     });
}

// Chỉnh sửa user
// document.addEventListener('DOMContentLoaded', function () {
//     const userId = getUserIdFromUrl(); // Extract user ID from the URL
//     const form = document.getElementById('update-user-form');
//
//     // Fetch existing user data when the page loads
//     fetch(`${apiBaseUrl}/${userId}`)
//         .then(response => response.json())
//         .then(data => {
//             if (data) {
//                 populateForm(data); // Populate the form with the fetched user data
//             } else {
//                 alert('User not found.');
//             }
//         })
//         .catch(error => {
//             console.error('Error fetching user data:', error);
//             alert('Error fetching user data.');
//         });
//
//     // Function to populate the form with user data
//     function populateForm(user) {
//         document.getElementById('userId').value = user.userID;
//         document.getElementById('ci').value = user.ci;
//         document.getElementById('name').value = user.name;
//         document.getElementById('username').value = user.userName;
//         document.getElementById('password').value = user.password;
//         document.getElementById('email').value = user.email;
//         document.getElementById('phone').value = user.phoneNumber;
//         document.getElementById('status').value = user.status;
//         // document.getElementById('role').value = user.role ? user.role.roleID : ''; // Handle missing role
//         document.getElementById('role').value = user.role?.roleID;
//     }
//
//     // Handle form submission to update user
//     form.addEventListener('submit', function (e) {
//         e.preventDefault();
//         const editUser = {
//             ci: document.getElementById('ci').value.trim(),
//             name: document.getElementById('name').value.trim(),
//             userName: document.getElementById('username').value.trim(),
//             password: document.getElementById('password').value.trim(),
//             email: document.getElementById('email').value.trim(),
//             phoneNumber: document.getElementById('phone').value.trim(),
//             status: document.getElementById('status').value.trim(),
//             // roleID: parseInt(document.getElementById('role').value.trim()),
//             role: {
//                 roleID: parseInt(document.getElementById('role').value.trim()), // Nếu cần gửi toàn bộ đối tượng
//                 roleName: role[parseInt(document.getElementById('role').value.trim())],
//             },
//         };
//         console.log("ci:", editUser.ci);
//         console.log("name:", editUser.name);
//         console.log("userName:", editUser.userName);
//         console.log("password:", editUser.password);
//         console.log("email:", editUser.email);
//         console.log("phoneNumber:", editUser.phoneNumber);
//         console.log("status:", editUser.status);
//         console.log("roleID:", editUser.role.roleID);
//         console.log("roleName:", editUser.role.roleName);
//
//
//         // Send a PUT request to update the user data
//         fetch(`${apiBaseUrl}/${userId}`, {
//             method: 'PUT',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify(editUser),
//         })
//             .then(response => response.json())
//             .then(data => {
//                 if (data) {
//                     alert('User updated successfully');
//                     window.location.href = 'http://localhost:3000/admin/users/list'; // Redirect to the user list page
//                 } else {
//                     alert('Failed to update user');
//                 }
//             })
//             .catch(error => {
//                 console.error('Error updating user:', error);
//                 alert('An error occurred while updating the user.');
//             });
//     });
//
//     // Helper function to extract user ID from the URL (for example, /users/edit/1)
//     function getUserIdFromUrl() {
//         const pathParts = window.location.pathname.split('/');
//         return pathParts[pathParts.length - 1]; // Return the last part of the URL (user ID)
//     }
// });
document.addEventListener('DOMContentLoaded', function () {
    const userId = getUserIdFromUrl(); // Extract user ID from the URL
    const form = document.getElementById('update-user-form');

    // Fetch existing user data when the page loads
    fetch(`${apiBaseUrl}/${userId}`)
        .then(response => response.json())
        .then(data => {
            if (data) {
                populateForm(data); // Populate the form with the fetched user data
            } else {
                alert('User not found.');
            }
        })
        .catch(error => {
            console.error('Error fetching user data:', error);
            alert('Error fetching user data.');
        });

    // Function to populate the form with user data
    function populateForm(user) {
        console.log(user)
        document.getElementById('userId').value = user.userID;
        document.getElementById('ci').value = user.ci;
        document.getElementById('name').value = user.name;
        document.getElementById('username').value = user.userName;
        document.getElementById('password').value = user.password;
        document.getElementById('email').value = user.email;
        document.getElementById('phone').value = user.phoneNumber;
        document.getElementById('status').value = user.status;
        document.getElementById('role').value = user.role ? user.role.roleID : ''; // Ensure to set the role ID
    }

    // Handle form submission to update user
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        // Extract role information
        const roleElement = document.getElementById('role');
        const roleID = parseInt(roleElement.value.trim());

        // Prepare the user object for update
        const editUser = {
            ci: document.getElementById('ci').value.trim(),
            name: document.getElementById('name').value.trim(),
            userName: document.getElementById('username').value.trim(),
            password: document.getElementById('password').value.trim(),
            email: document.getElementById('email').value.trim(),
            phoneNumber: document.getElementById('phone').value.trim(),
            status: document.getElementById('status').value.trim(),
            role: {
                roleID: parseInt(document.getElementById('role').value.trim()),
                roleName: document.getElementById('role').options[document.getElementById('role').selectedIndex].text.trim()
            }
        };

// Gửi yêu cầu PUT để cập nhật người dùng
        fetch(`${apiBaseUrl}/${userId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(editUser)
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(err => {
                        throw new Error(`HTTP error! status: ${response.status}, details: ${err}`);
                    });
                }
                return response.json();
            })
            .then(data => {
                alert('User updated successfully');
                window.location.href = 'http://localhost:3000/admin/users/list';
            })
            .catch(error => {
                console.error('Error updating user:', error);
                alert('An error occurred while updating the user: ' + error.message);
            });
    });

    // Helper function to extract user ID from the URL
    function getUserIdFromUrl() {
        const pathParts = window.location.pathname.split('/');
        return pathParts[pathParts.length - 1]; // Return the last part of the URL (user ID)
    }
});

// Confirmation for editing a user
function confirmEdit(userId) {
    const isConfirmed = confirm("Are you sure you want to edit this user?");
    if (isConfirmed) {
        window.location.href = `http://localhost:8080/auth/users/edit/${userId}`;
    }
}

// Load user list when the page is loaded
document.addEventListener('DOMContentLoaded', loadUserList);
