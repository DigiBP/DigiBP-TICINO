$(document).ready(function () {
    $("#formPlaceOrder").submit(function (event) {
        event.preventDefault();

        if( jQuery('#formPlaceOrder input[type=checkbox][name=reagents]:checked').length > 0 )
        {
            $.ajax({
                type: "POST",
                processData: false,
                contentType: false,
                url: "/order",
                data: new FormData(this),
                success: function (data, textStatus, response) {
                    $('#modal-title').html('Order Successful');
                    $('#modal-content').html('You will be notified about the further steps!');
                    $('#order-response').modal();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#modal-title').html('Something went wrong');
                    $('#modal-content').html('I\'m Sorry!');
                    $('#order-response').modal();
                }
            });
        }
        else
        {
            alert('Nope');
        }
    });
});

$(document).ready(function () {
    $("#formMicePlanned").submit(function (event) {
        event.preventDefault();
        
        var url = new URL(window.location.href);
        var id = url.searchParams.get("id");
        $('#taskId').val(id);
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