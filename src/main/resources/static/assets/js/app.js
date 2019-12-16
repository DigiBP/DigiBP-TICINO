$(document).ready(function () {
    $("#formPlaceOrder").submit(function (event) {
        event.preventDefault();
        if( formValid() )
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
    });
});

function formValid()
{
    var returnValue = true;
    if( $('#projectType').val() == 0 ) 
    {
        $('#projectType').css( "border-color", "red" );
        returnValue = false;
    }
    else
    {
        $('#projectType').css( "border-color", "" );
    } 
    if ( $('#background').val() == 0 ) 
    {
        $('#background').css( "border-color", "red" );
        returnValue = false;
    }
    else
    {
        $('#background').css( "border-color", "" );
    }
    if( $('#reagent').val() == 0 ) 
    {
        $('#reagent').css( "border-color", "red" );
        returnValue = false;
    }
    else
    {
        $('#reagent').css( "border-color", "" );
    }

    if( isNaN( $('#zip').val() ) )
    {
        $('#zip').css( "border-color", "red" );
        returnValue = false;
    }
    else
    {
        $('#zip').css( "border-color", "" );
    }

    return returnValue;
}