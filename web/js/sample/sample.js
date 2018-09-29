function deleteSample(sampleId) {
    var flag = confirm("确定删除吗？");
    if (flag == false) {
        return;
    }

    var data = {sampleId: sampleId};

    post("sample/delete", data);
    window.location.reload();
}

function save() {
    if (!isValid()) {
        return false;
    }

    var data = constructData();

    var action = "" == $("#sampleId").val() ? "sample/add" : "sample/update";
    post(action, data);
}

function constructData() {
    var data = {
        sampleId: $("#sampleId").val(),
        name: $("#name").val(),
        type: $("#type").val(),
        collectDate: $('#collectDate').val(),
        province: $('#province').val(),
        city: $('#city').val(),
        county: $('#county').val(),
        category: $("#category").val(),
        quantity: $("#quantity").val(),
        location: $("#location").val(),
        comment: $("#comment").val(),
        createPerson: $("#createPerson").val()
    };
    return data;
}

function isValid() {
    if (isEmpty("name", "请输入名称！")) {
        return false;
    }

    if (isEmpty("type", "请选择类型！")) {
        return false;
    }

    if (isEmpty("collectDate", "请选择采集日期！")) {
        return false;
    }

    if (isEmpty("category", "请选择分类！")) {
        return false;
    }

    if (isEmpty("location", "请选择位置！")) {
        return false;
    }

    return true;
}

function onDataChange(parentId, type) {
    var parent = $("#" + parentId);
    if (null == parent.val() || "" == parent.val()) {
        empty($("#" + type));
        return;
    }

    var data = {
        type: type,
        code: parent.val()
    }

    $.ajax({
        url: "code/listByType",
        type: "POST",
        data: data,
        dataType: 'text',
        success: function (result) {
            bindData(result, type);
        }
    });
}

function bindData(jsonString, domId) {
    if (null != jsonString && "" != jsonString) {
        var codes = eval(jsonString);
        var dom = $("#" + domId);
        empty(dom);

        for (var i = 0; i < codes.length; i++) {
            code = codes[i];
            dom.append("<option value='" + code.code + "'>" + code.value + "</option>");
        }
    }
}

function empty(dom) {
    dom.empty();
    dom.append("<option value=''>请选择</option>");
}