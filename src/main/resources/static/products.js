var stomp = null;

window.onload = function() {
    connect();
};

function connect() {
    var socket = new SockJS('/socket');
    stomp = Stomp.over(socket);
    stomp.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stomp.subscribe('/topic/products', function(product) {
            renderItem(product);
        });
    });
}

//Хук на интерфес
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendContent(); });
 });

//Отправка сообщений на сервер
function sendContent() {
    stomp.send("/app/products", {}, JSON.stringify({
        'title': $("#title").val(),
        'price': $("#price").val()
        }));
}

//Рендер сообщения полученного от сервера
function renderItem(productJSON) {
    var product = JSON.parse(productJSON.body);
    $("#table").append("<tr>") +
        "<td>" + product.title + "<td>" +
        "<td>" + product.price + "<td>" +
        "<td><a href='/products'" + product.id + "/bucket'>Add to bucket</a></td>" +
        "</tr>");
}