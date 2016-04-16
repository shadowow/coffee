
document.getElementById('save').onclick = function () {
    var inputs = document.getElementsByTagName('input');
    inputs[inputs.length] = document.getElementById('note');
    for(var i = 0; i < inputs.length; i++) {
        if (inputs[i].value.trim() == '') {
            alert('Все поля обязательны для заполнения!');
            return false;
        }
    }
    return true;
};

