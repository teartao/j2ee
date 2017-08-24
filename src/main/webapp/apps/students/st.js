$(function () {
    $.ajax({
        type: "GET",
        url: "/cm/student/list",
        dataType: 'json',
        success: function (response) {
            var stu = [];
            if (response.success == true) {
                stu = response.data;
            }

            for (var i in stu) {
                var studentHtml = '<tr>' +
                    '<td>' + stu[i].id + '</td>' +
                    '<td>' + stu[i].name + '</td>' +
                    '<td>' + stu[i].birth + '</td>' +
                    '<td>' + (stu[i].sex == '1' ? '男' : '女') + '</td>' +
                    '<td><img class="view-pic" src="' + stu[i].photo + '"></td>' +
                    '<td><button onclick="deleteStudent(' + stu[i].id + ') ">删除</button></td>';
            }
            $('#student').append(studentHtml);
        }
    });
});

function deleteStudent(studentId) {
    $.ajax({
        type: "POST",
        url: "/cm/student/delete",
        contentType: "application/json;charset=UTF-8",
        data: {
            age: 23,
            id: 1,
            name: "张三",
            sex: "男"
        },
        success: function (response) {
            if (response.success == true) {
                alert("删除成功");
            } else {
                alert("删除失败");

            }
        }
    });
}

