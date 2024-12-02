// $(document).ready(function() {
//     var today = new Date().toISOString().split('T')[0];
//     $('#startDate').val(today);
//     $('#endDate').val(today);
//     console.log("heheh")
//     $.ajax({
//         url: '/admin/orders/thong-ke',
//         method: 'GET',
//         data: {
//             startDate: today,
//             endDate: today
//         },
//         success: function(response) {
//             console.log(response);
//             var revenueData = {
//                 dates: response.dates,
//                 revenues: response.revenues
//             };
//             drawChart(revenueData);
//         },
//         error: function(xhr, status, error) {
//             alert("Có lỗi xảy ra khi tải dữ liệu.");
//         }
//     });
// });
//
// function drawChart(revenueData) {
//     var ctx = document.getElementById('revenueChart').getContext('2d');
//     var chart = new Chart(ctx, {
//         type: 'line',
//         data: {
//             labels: revenueData.dates,
//             datasets: [{
//                 label: 'Doanh thu',
//                 data: revenueData.revenues,
//                 borderColor: 'rgba(75, 192, 192, 1)',
//                 fill: false,
//             }]
//         },
//         options: {
//             responsive: true,
//             maintainAspectRatio: false,
//         }
//     });
// }
//
//
// // Xử lý khi form được submit
// $('#revenueForm').submit(function(event) {
//     event.preventDefault();
//     var startDate = $('#startDate').val();
//     var endDate = $('#endDate').val();
//
//     // Gửi yêu cầu AJAX tới server
//     $.ajax({
//         url: '/admin/orders/thong-ke',
//         method: 'GET',
//         data: {
//             startDate: startDate,
//             endDate: endDate
//         },
//         success: function(response) {
//             console.log(response);
//
//             var revenueData = {
//                 dates: response.dates,
//                 revenues: response.revenues
//             };
//             drawChart(revenueData);
//         },
//         error: function(xhr, status, error) {
//             alert("Có lỗi xảy ra khi tải dữ liệu.");
//         }
//     });
// });
//
