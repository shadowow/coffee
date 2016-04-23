
document.getElementById('save').onclick = function () {
    if (document.getElementById('phone_input').value.trim() == '') {
        alert('Пожалуйста, введите номер телефона!');
        return false;
    }
    var reg = '^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$';
    if(!reg.test(document.getElementById('phone_input').value)) {
        alert('Пожалуйста, введите номер телефона!');
        return false;
    }

    if (document.getElementById('street_input').value.trim() == '') {
        alert('Пожалуйста, введите улицу!');
        return false;
    }
    if (document.getElementById('building_input').value.trim() == '') {
        alert('Пожалуйста, введите номер дома!');
        return false;
    }
    return true;
};

