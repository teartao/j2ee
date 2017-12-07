/**
 * Created by taolei on 2017/7/31.
 */
$(function () {
    $.ajax({
        type: "GET",
        url: "menu.json",
        success: function (menus) {
            for(var i in menus){
                var menuItem = menus[i];
                var jq = '<li class="memu-item">' +
                    '<div class="memu-title">' +
                    menuItem.menuTitle +
                    '</div>'
                    + '<div class="menus-third">';
                for(var j in menuItem.someMenu){
                    var someMenuItem = menuItem.someMenu[j];
                    jq = jq + '<span class="keyword-span">' +
                        someMenuItem +
                        '</span>'
                }
                jq = jq + '</div>'
                    + '<div class="menus-detail">';
                    for (var m in menuItem.detailMenu ) {
                        var aDetailMenu=menuItem.detailMenu[m];
                    jq = jq + '<div class="menus-d-item">'
                    +'<h3>' + aDetailMenu.itemTitle + '</h3><hr>' +
                    '<div class="menus-d-keyword">';
                        for (var j in aDetailMenu.itemDetail) {
                        jq = jq + '<span class="keyword-span">' +
                            aDetailMenu.itemDetail[j] +
                            '</span>'
                        }
                    jq = jq + '</div></div>';
                }
                jq = jq + '</div>' +
                    '</li>';
                $('#menuItem').append(jq);
            }
        }
    });
    $.ajax({
        type: "GET",
        url: "floor.json",
        context: document.body,
        success: function (floor) {

            for (i = 0; i < floor.length; i++) {
                var jq = '<div class="floor-cont">' +
                    '<div class="floor-name">' +
                        floor[i].floorTitle +
                    '</div>' +
                '<div class="floor-tab">';

                for (j = 0; j < floor[i].tabs.length; j++) {
                    jq = jq + '<div class="tabs tab1">' +
                        floor[i].tabs[j].tabTitle +
                        '</div>';
                }

                jq = jq + '</div>' +
                    '<div class="clear">' + '</div>'
                    + '<div class="floor-left-ad">' +'<img src="'+floor[i].leftPic+'">'+
                    '</div>' +
                    '<div class="floor-sm-ad">';
                for (p = 0; p < floor[i].tabs[0].tabPics.length; p++) {
                    jq = jq + '<div class="ad-cont ad-cont-1">' +
                        '<img src="'+floor[i].tabs[0].tabPics[p]+'">' +
                        '</div>';
                }
                jq = jq + '</div>' +
                    '<div class="floor-foot-brand">';
                for (m = 0; m < floor[i].brandPics.length; m++) {
                    jq = jq + '<div class="brands">' +
                        '<img src="'+floor[i].brandPics[m]+'">' +
                        '</div>';
                }
                jq = jq + '</div></div>';

                $('#floor').append(jq);
            }

        }
    })
});


function showMenuDetail() {
    $(this).find('menu-detail');
}

