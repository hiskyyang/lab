function save() {
    if(!isValid()){
        return false;
    }

    var data = constructData();
    var action = "" == $("#userId").val() ? "user/add" : "user/update";
    post(action, data);
}

function constructData(){
    return {
        userId: $("#userId").val(),
        name: $("#name").val(),
        password: $("#password").val(),
        type: $('input[name="type"]:checked').val()
    };
}

function deleteUser(userId){
    var flag = confirm("确定删除吗？");
    if (flag == false) {
        return;
    }

    data = {userId: userId};

    post("user/delete", data);
    window.location.reload();
}

function isValid() {
    if (isEmpty("name", "请输入账号！")) {
        return false;
    }

    if (isEmpty("password", "请输入密码！")) {
        return false;
    }

    if (isRadioEmpty("type", "请选择类型！")) {
        return false;
    }

    return true;
}