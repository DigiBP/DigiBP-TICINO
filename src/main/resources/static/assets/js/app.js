$(document).ready(function () {
    $("#form").submit(function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "/order",
            data: new FormData(this),
            success: function (data, textStatus, response) {
                $('#orderSuccess').removeClass("d-none");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('meh');
            }
        });
    });
});