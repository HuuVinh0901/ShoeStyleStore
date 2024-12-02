// function printInvoice() {
//     const invoiceContainer = document.getElementById('invoice'); // Đúng với id trong HTML
//     if (!invoiceContainer) {
//         console.error('Invoice container not found!');
//         return;
//     }
//
//     const invoiceContent = invoiceContainer.innerHTML;
//
//     const printWindow = window.open('', '_blank');
//     printWindow.document.write(`
//     <html>
//       <head>
//         <title>Hóa Đơn</title>
//         <style>
//           body {
//             font-family: Arial, sans-serif;
//             padding: 20px;
//           }
//           table {
//             width: 100%;
//             border-collapse: collapse;
//           }
//           table, th, td {
//             border: 1px solid black;
//           }
//           th, td {
//             padding: 10px;
//             text-align: center;
//           }
//         </style>
//       </head>
//       <body>
//         ${invoiceContent}
//       </body>
//     </html>
//   `);
//     printWindow.document.close();
//     printWindow.print();
// }
//
//
// document.addEventListener("DOMContentLoaded", function () {
//     const today = new Date();
//     const formattedDate = today.toLocaleDateString('vi-VN', {
//         year: 'numeric',
//         month: 'long',
//         day: 'numeric',
//     });
//     document.getElementById('invoiceDate').innerText = formattedDate;
// });