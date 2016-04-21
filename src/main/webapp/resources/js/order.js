
document.getElementById('save').onclick = function () {
    if (document.getElementById('phone_input').value.trim() == '') {
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

