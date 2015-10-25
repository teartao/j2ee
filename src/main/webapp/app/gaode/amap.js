(function (config) {
    var e = navigator.userAgent.toLowerCase(), y = window, C = document;

    function D(a) {
        return -1 !== e.indexOf(a)
    }

    var aa = D("ucbrowser"), ba = D("micromessenger"), ca = D("mqqbrowser"), F = "ActiveXObject"in y, da = F && !y.XMLHttpRequest, ea = F && !C.querySelector, fa = F && !C.addEventListener, ga = F && D("ie 9"), ia = F && D("rv:11"), ja = y.navigator && y.navigator.msPointerEnabled && !!y.navigator.msMaxTouchPoints, ka = ja || D("touch") || "ontouchstart"in C, la = D("webkit"), chrome = D("chrome"), ma = D("firefox"), na = D("android"), oa = -1 !== e.search(/android( |\/)4\./), pa = -1 !== e.search(/android [23]/), qa = D("windows phone"), ra = "devicePixelRatio"in y && 1 < y.devicePixelRatio ||
        "matchMedia"in y && y.matchMedia("(min-resolution:144dpi)") && y.matchMedia("(min-resolution:144dpi)").matches, sa = D("ipad;"), ta = sa && ra, ua = D("ipod touch;"), va = D("iphone;"), wa = va || sa || ua, xa = D("safari"), ya = (D(" os 7") || D(" os 8")) && wa, G = na || wa || qa || D("mobile") || "undefined" !== typeof orientation, za = C.documentElement, Aa = F && "transition"in za.style, Ba = !!C.createElementNS && !!C.createElementNS("http://www.w3.org/2000/svg", "svg").createSVGRect, Ca = !!C.createElement("canvas").getContext, Da = "WebKitCSSMatrix"in y &&
        "m11"in new window.WebKitCSSMatrix, Ea = "MozPerspective"in za.style, Fa = "OTransition"in za.style, Ga = Aa || Da || Ea || Fa, Ha = D("windows nt"), Ia = !G && Ha && -1 === e.search(/nt [67]\.[1-9]/), Ja;
    if (!(Ja = !Ca)) {
        var Ka;
        if (!(Ka = na && !(oa && (-1 !== e.search(/m351|firefox/) ? 0 : ba && ca ? -1 === e.search(/hm note|gt-|m1 note/) : D("gt-n710") && -1 !== e.search(/android( |\/)4\.1/) ? 0 : -1 !== e.search(/ucbrowser\/((9\.[0-5]\.)|(10\.))/) ? -1 === e.search(/huawei( p6|h30)/) : D("baidubrowser") ? -1 === e.search(/hm201|sm-g900/) : -1 !== e.search(/lbbrowser|360|liebao|oupeng|mqqbrowser|sogou|micromessenger|chrome/) || !D("ucbrowser") && -1 !== e.search(/sm-n900|(gt-(n710|i95|p[67]))|(mi( [1-4]|-one))|(huawei( p6|_c8813|h30| g750))|lenovo k900|coolpad_9150/))))) {
            var La;
            if (La = va) {
                var Ma = screen.width;
                La = !(ya && (aa || ba ? !(375 < Ma) : xa))
            }
            Ka = La || ua || ta || qa || F && !ia || G && ma || Ha && D("version")
        }
        Ja = Ka
    }
    var Na = Ja, Oa = !1;
    try {
        Oa = "undefined" !== typeof y.localStorage
    } catch (Pa) {
    }
    config.f = {
        size: 100,
        eq: D("macintosh"),
        Yo: D("baidubrowser"),
        LJ: ca,
        Ye: F,
        Yc: da,
        Kk: ea,
        zc: fa,
        dE: F && !ia,
        hH: la,
        Bm: Oa,
        pe: na,
        dI: pa,
        $q: aa,
        chrome: chrome,
        Tu: ma,
        gJ: Aa,
        iH: Da,
        FI: Ea,
        EJ: Fa,
        RB: Ga,
        Y: G,
        BJ: G && la,
        NE: G && Da,
        AJ: G && y.opera,
        nE: wa,
        fc: ka,
        Ov: ja,
        hJ: ga,
        ka: ra,
        bg: Ba,
        wv: Ca,
        qq: Na,
        Jh: !Na && !Ia,
        pq: !Ba && G && Ca
    };
    var y = window, H = "http map anip layers overlay0 brender mrender".split(" ");
    config.oc = "main";
    config.f.fc && (H += ",touch", config.oc += "t");
    config.f.Y || (H += ",mouse", config.oc += "m");
    config.f.qq && !config.f.pq ? (config.oc += "d", H += ",drender") : (H += ",crender", config.oc += "c", config.f.Jh && (config.oc += "v", H += ",vectorlayer,overlay", H += ",vp", config.oc += "p"), config.f.Jh && (config.oc += "vt", H += ",vt"));
    config[7] && (H += "," + config[7], config.oc += config[7].replace(",", "").replace(eval("/AMap./gi"), ""));
    H += ",sync";
    config.Bx = H.split(",");
    window.AMap = window.AMap || {};
    window.AMap.Gf = "1.3.8.5";
    var Qa = window.AMap.gr = {}, Ra = config[2].split(",")[0], Sa = Ra + "/theme/v" + config[4] + "/style1.3.8.5.css", Ta = document.head || document.getElementsByTagName("head")[0];
    if (Ta) {
        var Ua = document.createElement("link");
        Ua.setAttribute("rel", "stylesheet");
        Ua.setAttribute("type", "text/css");
        Ua.setAttribute("href", Sa);
        Ta.insertBefore(Ua, Ta.firstChild)
    } else document.write("<link rel='stylesheet' href='" + Sa + "'/>");
    function Va() {
        var a = Wa, b = document, c = b.createElement("script");
        c.charset = "utf-8";
        c.src = a;
        return (a = b.body || Ta) ? (a.appendChild(c), !0) : !1
    }

    var Xa = (new Date).getTime();
    Qa.__load__ = function (a) {
        a(config, Xa);
        delete Qa.__load__
    };
    try {
        if (window.localStorage) {
            var J = window.localStorage["_AMap_" + config.oc], Ya = !1;
            J ? (J = JSON.parse(J), J.version === window.AMap.Gf ? (eval(J.script), Qa.loaded = !0) : Ya = !0) : Ya = !0;
            if (Ya)for (var Za in window.localStorage)window.localStorage.hasOwnProperty(Za) && 0 === Za.indexOf("_AMap_") && window.localStorage.removeItem(Za)
        }
    } catch ($a) {
    }
    if (!Qa.loaded) {
        var Wa = Ra + "/maps/main?v=" + config[4] + "&key=" + config[0] + "&m=" + config.Bx.join(",") + "&vrs=1.3.8.5";
        config[5] && Va() || (document.write('<script id="a$m@p&j^s_" src=\'' + Wa + "' type='text/javascript'>\x3c/script>"), document.getElementById("a$m@p&j^s_") || Va());
        delete config.Bx
    }
    ;
})(["bbe7af0ce6da737946eea74915275953", [118.363373, 31.228098, 119.241663, 32.614363, 118.767413, 32.041544], "http://js.webapi.amap.com", 1, "1.3", null, "320100", ""])