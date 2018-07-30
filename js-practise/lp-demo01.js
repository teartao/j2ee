Number.prototype.floor = function (scale) {
    scale = Number(scale);
    if (isNaN(scale) || scale !== Number(scale.toFixed(0))) {
        return ;
    }
    var ratio = Math.pow(10,scale);
    return Math.floor(this * ratio) / ratio;
};