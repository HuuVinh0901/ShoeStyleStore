document.addEventListener('DOMContentLoaded', function () {
  const tabs = document.querySelectorAll('.order-tabs-head');
  const contents = document.querySelectorAll('.order-tabs-content');

  console.log('Tabs:', tabs); // Kiểm tra các tab được tìm thấy
  console.log('Contents:', contents); // Kiểm tra các tab content được tìm thấy
  document.querySelectorAll('.order-tabs-head').forEach((tab) => {
    tab.addEventListener('click', (e) => {
      // Remove active class from all tab heads
      document.querySelectorAll('.order-tabs-head').forEach((tab) =>
        tab.classList.remove('order-tabs-head-active')
      );

      // Remove active class from all tab contents
      document.querySelectorAll('.order-tabs-content').forEach((content) =>
        content.classList.remove('active')
      );

      // Add active class to the clicked tab
      e.target.classList.add('order-tabs-head-active');

      // Show the corresponding tab content
      const targetId = e.target.getAttribute('data-id');
      document.getElementById(targetId).classList.add('active');
    });
  });
});
