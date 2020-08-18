function onClick(args) {

    $.ajax({
        type: "POST",
        url: "http://localhost:9090/ordermanager/order",
        data: args,
        dataType: "text",
        success: function (response) {
            location.reload();
        }
    });
}

function send() {
    var item = {};
    var pusto = ""
    let itemList = JSON.parse(sessionStorage.getItem("itemList"));
    if (itemList === null) {
        itemList = [];
    }
    if (document.getElementById("name").value != "" && document.getElementById("surname").value != "" && document.getElementById("card_number").value != "" && document.getElementById("expiration_date").value != "" && document.getElementById("cvv").value != "" && document.getElementById("date_of_birth").value != "" && document.getElementById("phone_number").value != "") {
        item.name = document.getElementById("name").value;
        item.surname = document.getElementById("surname").value;
        item.card_number = document.getElementById("card_number").value;
        item.expiration_date = document.getElementById("expiration_date").value;
        item.cvv = document.getElementById("cvv").value;
        item.date_of_birth = document.getElementById("date_of_birth").value;
        item.phone_number = document.getElementById("phone_number").value;

        itemList.push(item);
        sessionStorage.setItem('itemList', JSON.stringify(itemList));
        console.log(item);
    } else
        alert("Proszę nie zostawiać pustych pól");
}

function showAll() {
    var itemList = JSON.parse(sessionStorage.getItem('itemList'));
    var el = document.getElementById("list");
    var string = "";

    if (itemList != null) {
        for (i = 0; i < itemList.length; i++) {

            string += "<br/> WYSŁANA WIADOMOŚĆ: <br/> Imię: " + itemList[i].name + "<br/> Nazwisko: " + itemList[i].surname + "<br/> Numer karty: " + itemList[i].card_number + "<br/> Data ważności: " + itemList[i].expiration_date + "</br> Kod CVV: " + itemList[i].cvv + "</br>Data_urodzenia: " + itemList[i].date_of_birth + "</br>Numer telefonu: " + itemList[i].phone_number;

            string += "<br/><button class='btn btn-primary btn-lg' onclick='edit(" + i + ")' >Edytuj</button>";
        }
        el.innerHTML = string;
    } else {
        el.innerHTML = string + " Żadna wiadomość nie została wysłana! ";
    }
}

function clearStorage() {
    sessionStorage.clear();
    showAll();
}

function edit(i) {
    var itemList = JSON.parse(sessionStorage.getItem('itemList'));
    document.getElementById("name").value = itemList[i].name;
    document.getElementById("surname").value = itemList[i].surname;
    document.getElementById("card_number").value = itemList[i].card_number;
    document.getElementById("expiration_date").value = itemList[i].expiration_date;
    document.getElementById("cvv").value = itemList[i].cvv;
    document.getElementById("date_of_birth").value = itemList[i].date_of_birth;
    document.getElementById("phone_number").value = itemList[i].phone_number;
    itemList.splice(i, 1);
    sessionStorage.setItem('itemList', JSON.stringify(itemList));

}