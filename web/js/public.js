function isEmpty(id, prompt) {
	var dom = $("#" + id);
	if (null == dom.val() || "" == dom.val()) {
        alert(prompt);
		dom.focus();
		return true;
	}
	return false;
}
function isRadioEmpty(name, prompt) {
	var rd = $('input[name=' + name + ']:checked');
	if (rd.length == 0) {
        alert(prompt);
        $('input[name="' + name + '"]')[0].focus();
        return true;
	}
	return false;
}

function post(url, data) {
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        dataType: 'text',
        success: function (result) {
            if (result != 0) {
                alert("成功");
                window.opener.location.reload();
                window.close();
            } else {
                alert("失败");
            }
        }
    });
}