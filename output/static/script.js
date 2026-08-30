document.addEventListener("DOMContentLoaded", function () {
    var deleteForms = document.querySelectorAll(".inline-form");
    deleteForms.forEach(function (form) {
        form.addEventListener("submit", function (event) {
            var confirmed = confirm("Are you sure you want to delete this product?");
            if (!confirmed) {
                event.preventDefault();
            }
        });
    });
});
