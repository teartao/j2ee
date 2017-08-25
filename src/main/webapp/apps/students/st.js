$(function () {
    $.ajax({
        type: "GET",
        url: "/lp/student/list",
        dataType: 'json',
        success: function (response) {
            var stu = response.data;
            var studentHtml = '';
            for (var i in stu) {
                studentHtml = studentHtml + '<tr>' +
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
        url: "/lp/student/delete",
        contentType: "application/json;charset=UTF-8",
        data: {
            id: studentId+''
        },
        success: function (response) {
            if (response.status == 'success') {
                alert("删除成功");
            } else {
                alert("删除失败");

            }
        }
    });
}

function addStudent() {
    $.ajax({
        type:"POST",
        url:"/lp/student/add",
        contentType: "application/json;charset=UTF-8",
        data:{
            name:$('#studentName').val()
        }
    })
}