$(function () {
    $('td').on('mousedown',function () {
        $(this).css('background-color','gray');
    });
    $('td').on('mouseup',function () {
        $(this).css('background-color','#cccccc');
    });

});