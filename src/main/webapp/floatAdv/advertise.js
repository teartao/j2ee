$(function () {
    setInterval(function () {
        moveDiv();
    }, 100);


    function moveDiv() {
        var leftCss = $("#advertise").css("left");
        var leftValue = Number(leftCss.slice(0, leftCss.length - 2));

        var widthCss= $("#advertise").css("width");
        var widthValue = Number(widthCss.slice(0, widthCss.length - 2));


        var topCss = $("#advertise").css("top");
        var topValue = Number(topCss.slice(0, topCss.length - 2));

        var heightCss= $("#advertise").css("height");
        var heightValue = Number(heightCss.slice(0, heightCss.length - 2));

        var maxWidth = window.screen.width - widthValue;
        var maxHeight = window.screen.height - heightValue;

//        moveToRight(leftValue);

        $("#advertise").css("left", leftValue);
    };


    function moveToRight(leftValue){
        for(var i=leftValue;i<max;i++){

        }
    }

    function moveToLeft(leftValue){
        for(var i=leftValue;i<max;i++){

        }
    }

    function moveToTop(leftValue){
        for(var i=leftValue;i<max;i++){

        }
    }

    function moveToDown(leftValue){
        for(var i=leftValue;i<max;i++){

        }
    }

});
