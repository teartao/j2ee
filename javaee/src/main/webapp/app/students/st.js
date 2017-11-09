$(function () {
    $.ajax({
        type: "GET",
        url: "/jee/student/list",
        dataType: 'json',
        success: function (stu) {
            for (var i in stu) {
                var studentHtml =  '<tr>' +
                    '<td>' + stu[i].id + '</td>' +
                    '<td>' + stu[i].name + '</td>' +
                    '<td>' + stu[i].birth + '</td>' +
                    '<td>' + (stu[i].sex=='1'?'男':'女') + '</td>' +
                    '<td><img class="view-pic" src="' + stu[i].photo + '"></td>'+
                    '<td><button onclick="deleteStudent('+stu[i].id +') ">删除</button></td>';
            }
            $('#student').append(studentHtml);
        }
    });
})

function deleteStudent(studentId){
    $.ajax({
        type: "post",
        url: "/jee/student/delete",
        dataType: 'json',
        data:{id:studentId},
        success: function (data) {
            if(data=='success'){
                alert("删除成功");
            }else{
                alert("删除失败");

            }
        }
    });
}

