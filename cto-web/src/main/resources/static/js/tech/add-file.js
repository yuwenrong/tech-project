function saveOrUpdate() {
    var flag = $("#dataForm").valid(3);
    var data = $("#dataForm").serialize();
    if (flag) {
        layer.load(1, {shade: [0.5,'#000']});
        $.ajax({
            data: data,//提交的数据
            url: "/tech/file/saveOrUpdate",//提交连接
            type: 'post',
            dataType: 'json',
            success: function (result) {
                layer.closeAll('loading');
                if (result.code == 0) {
                    window.location.href = "/tech/files";
                }else {
                    toastr.error(result.msg,'系统通知!')
                }
            }//回调方法
        });
    }
}

function downLoad(){

}