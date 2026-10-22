document.addEventListener("DOMContentLoaded", () => {
  const search = document.querySelector('input[type="search"]');
  if (search) {
    search.addEventListener("keydown", (event) => {
      if (event.key === "Escape") {
        search.value = "";
        search.form?.requestSubmit();
      }
    });
  }
  document.querySelectorAll(".booking-form").forEach((form) => {
    form.addEventListener("submit", () => {
      const button = form.querySelector('button[type="submit"]');
      if (form.checkValidity() && button) {
        button.disabled = true;
        button.textContent = "Preparing enquiry…";
      }
    });
  });
});
