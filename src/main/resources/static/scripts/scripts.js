function onClick(args) {

    $.ajax({
        type: "POST",
        url: "http://localhost:9090/ordermanager/order",
        data: args,
        dataType: "text",
        success: function(response) {
            location.reload();
        }
    });
}