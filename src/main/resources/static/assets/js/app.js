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

$(document).ready(function () {
    $("#formMicePlanned").submit(function (event) {
        event.preventDefault();
        
        var url = new URL(window.location.href);
        var id = url.searchParams.get("id");
        $('#taskId').val(id);
        //alert(id);
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "/mice-planned",
            data: new FormData(this),
            success: function (data, textStatus, response) {
                //$('#orderSuccess').removeClass("d-none");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert('meh');
            }
        });
    });
});

$(document).ready(function () {
    $("#formMiceOrdered").submit(function (event) {
        event.preventDefault();
        
        var url = new URL(window.location.href);
        var id = url.searchParams.get("id");
        $('#taskId').val(id);
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "/mice-ordered",
            data: new FormData(this),
            success: function (data, textStatus, response) {
                //$('#orderSuccess').removeClass("d-none");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert('meh');
            }
        });
    });
});

$(document).ready(function () {
    $("#formInjectionData").submit(function (event) {
        event.preventDefault();
        
        var url = new URL(window.location.href);
        var id = url.searchParams.get("id");
        var method = url.searchParams.get("method");
        $('#taskId').val(id);
        $.ajax({
            type: method,
            processData: false,
            contentType: false,
            url: "/injection-data",
            data: new FormData(this),
            success: function (data, textStatus, response) {
                //$('#orderSuccess').removeClass("d-none");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert('meh');
            }
        });
    });
});

$(document).ready(function () {
    $('#birthPositive').click(function() {
        $('#birth').val(true);
    });

    $('#birthNegative').click(function() {
        $('#birth').val(false);
    });

    $("#birthCheckForm").submit(function (event) {
        
        event.preventDefault();
        var url = new URL(window.location.href);
        var id = url.searchParams.get("id");
        $('#taskId').val(id);
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "/birth-check",
            data: new FormData(this),
            success: function (data, textStatus, response) {
                //$('#orderSuccess').removeClass("d-none");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert('meh');
            }
        });
    });

    $("#formBirthRecord").submit(function (event) {
        
        event.preventDefault();
        var url = new URL(window.location.href);
        var id = url.searchParams.get("id");
        $('#taskId').val(id);
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "/record-birth",
            data: new FormData(this),
            success: function (data, textStatus, response) {
                //$('#orderSuccess').removeClass("d-none");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert('meh');
            }
        });
    });

    $("#formSampleRecord").submit(function (event) {
        
        event.preventDefault();
        var url = new URL(window.location.href);
        var id = url.searchParams.get("id");
        $('#taskId').val(id);
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "/record-samples",
            data: new FormData(this),
            success: function (data, textStatus, response) {
                //$('#orderSuccess').removeClass("d-none");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert('meh');
            }
        });
    });

    $('#samplePositive').click(function() {
        $('#sampleResult').val(true);
    });

    $('#sampleNegative').click(function() {
        $('#sampleResult').val(false);
    });

    $("#sampleConfirmForm").submit(function (event) {
        
        event.preventDefault();
        var url = new URL(window.location.href);
        var id = url.searchParams.get("id");
        $('#taskId').val(id);
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "/confirm-samples",
            data: new FormData(this),
            success: function (data, textStatus, response) {
                //$('#orderSuccess').removeClass("d-none");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //alert('meh');
            }
        });
    });
});