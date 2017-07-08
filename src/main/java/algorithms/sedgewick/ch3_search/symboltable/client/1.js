var $jscomp = $jscomp || {};
$jscomp.scope = {};
$jscomp.defineProperty = "function" == typeof Object.defineProperties ? Object.defineProperty : function (a, b, d) {
    a != Array.prototype && a != Object.prototype && (a[b] = d.value)
};
$jscomp.getGlobal = function (a) {
    return "undefined" != typeof window && window === a ? a : "undefined" != typeof global && null != global ? global : a
};
$jscomp.global = $jscomp.getGlobal(this);
$jscomp.SYMBOL_PREFIX = "jscomp_symbol_";
$jscomp.initSymbol = function () {
    $jscomp.initSymbol = function () {
    };
    $jscomp.global.Symbol || ($jscomp.global.Symbol = $jscomp.Symbol)
};
$jscomp.symbolCounter_ = 0;
$jscomp.Symbol = function (a) {
    return $jscomp.SYMBOL_PREFIX + (a || "") + $jscomp.symbolCounter_++
};
$jscomp.initSymbolIterator = function () {
    $jscomp.initSymbol();
    var a = $jscomp.global.Symbol.iterator;
    a || (a = $jscomp.global.Symbol.iterator = $jscomp.global.Symbol("iterator"));
    "function" != typeof Array.prototype[a] && $jscomp.defineProperty(Array.prototype, a, {
        configurable: !0,
        writable: !0,
        value: function () {
            return $jscomp.arrayIterator(this)
        }
    });
    $jscomp.initSymbolIterator = function () {
    }
};
$jscomp.arrayIterator = function (a) {
    var b = 0;
    return $jscomp.iteratorPrototype(function () {
        return b < a.length ? {done: !1, value: a[b++]} : {done: !0}
    })
};
$jscomp.iteratorPrototype = function (a) {
    $jscomp.initSymbolIterator();
    a = {next: a};
    a[$jscomp.global.Symbol.iterator] = function () {
        return this
    };
    return a
};
$jscomp.iteratorFromArray = function (a, b) {
    $jscomp.initSymbolIterator();
    a instanceof String && (a += "");
    var d = 0, e = {
        next: function () {
            if (d < a.length) {
                var h = d++;
                return {value: b(h, a[h]), done: !1}
            }
            e.next = function () {
                return {done: !0, value: void 0}
            };
            return e.next()
        }
    };
    e[Symbol.iterator] = function () {
        return e
    };
    return e
};
$jscomp.polyfill = function (a, b, d, e) {
    if (b) {
        d = $jscomp.global;
        a = a.split(".");
        for (e = 0; e < a.length - 1; e++) {
            var h = a[e];
            h in d || (d[h] = {});
            d = d[h]
        }
        a = a[a.length - 1];
        e = d[a];
        b = b(e);
        b != e && null != b && $jscomp.defineProperty(d, a, {configurable: !0, writable: !0, value: b})
    }
};
$jscomp.polyfill("Array.prototype.keys", function (a) {
    return a ? a : function () {
        return $jscomp.iteratorFromArray(this, function (b) {
            return b
        })
    }
}, "es6-impl", "es3");
var OBJ_HIDDEN = -1, VERTEX_SHAPE_CIRCLE = "circle", VERTEX_SHAPE_RECT = "rect", VERTEX_DEFAULT = "default", VERTEX_NORMAL_BLUE = "normal_blue", VERTEX_NORMAL_GREEN = "normal_green", VERTEX_HIGHLIGHTED = "highlighted", VERTEX_HIGHLIGHTED_RECT = "highlighted_rect", VERTEX_TRAVERSED = "traversed", VERTEX_RESULT = "result", VERTEX_RESULT_RECT = "result_rect", VERTEX_RECT = "rect", VERTEX_BLUE_FILL = "blueFill", VERTEX_GREEN_FILL = "greenFill", VERTEX_GREY_FILL = "greyFill", VERTEX_PINK_FILL = "pinkFill", VERTEX_RED_FILL = "redFill", VERTEX_BLUE_OUTLINE =
        "blueOutline", VERTEX_GREEN_OUTLINE = "greenOutline", VERTEX_GREY_OUTLINE = "greyOutline", VERTEX_PINK_OUTLINE = "pinkOutline", VERTEX_RED_OUTLINE = "redOutline", EDGE_DEFAULT = "default", EDGE_HIGHLIGHTED = "highlighted", EDGE_TRAVERSED = "traversed", EDGE_BLUE = "blue", EDGE_GREEN = "green", EDGE_GREY = "grey", EDGE_PINK = "pink", EDGE_RED = "red", EDGE_TYPE_UDE = 0, EDGE_TYPE_DE = 1, EDGE_TYPE_BDE = 2, POLYGON_DEFAULT = "default", POLYGON_HIDDEN = "hidden", POLYGON_BLUE_FILL = "blueFill", POLYGON_GREEN_FILL = "greenFill", POLYGON_GREY_FILL = "greyFill",
    POLYGON_PINK_FILL = "pinkFill", POLYGON_RED_FILL = "redFill", POLYGON_BLUE_TRANSPARENT = "blueTransparent", POLYGON_GREEN_TRANSPARENT = "greenTransparent", POLYGON_GREY_TRANSPARENT = "greyTransparent", POLYGON_PINK_TRANSPARENT = "pinkTransparent", POLYGON_RED_TRANSPARENT = "redTransparent", NO_ITERATION = -1, NO_STATELIST = {}, ANIMATION_PLAY = 1, ANIMATION_PAUSE = 0, ANIMATION_STOP = -1, UPDATE_FORWARD = !0, UPDATE_BACKWARD = !1, MODE_GET_ALL_SUBMITTED_GRAPHS_SUMMARY = 21, MODE_SUBMIT_GRAPH = 22, MODE_GET_SUBMITTED_GRAPH_BY_ID = 23, MODE_GET_ALL_GRAPH_TOPICS =
        24, MODE_DELETE_SUBMITTED_GRAPH = 25, MODE_COMMIT_SUBMITTED_GRAPH = 26, MODE_ADD_SUBMITTED_GRAPH_RATING = 27, MODE_GET_RANDOM_SUBMITTED_GRAPH = 28, MODE_GET_ALL_COMMITTED_GRAPHS_SUMMARY = 29, MODE_DELETE_COMMITTED_GRAPH = 30;
function GraphVisu(a, b, d, e, h, m) {
    function n() {
        var b;
        l.selectAll("g").remove();
        A = l.append("svg:g").selectAll("path");
        B = l.append("svg:g").selectAll("g");
        z = l.append("svg:g").selectAll("text");
        B = B.data(f, function (a) {
            return a.id
        });
        B.selectAll("circle").style("fill", function (a) {
            return a === x ? d3.rgb(D(a.id)).brighter().toString() : D(a.id)
        });
        var a = B.enter().append("svg:g");
        a.append("svg:circle").attr("class", "node").attr("r", 16).attr("cx", function (a) {
            return a.x
        }).attr("cy", function (a) {
            return a.y
        }).style("fill",
            function (a) {
                return a === x ? d3.rgb(255, 138, 39) : d3.rgb(238, 238, 238)
            }).on("mousedown", function (a) {
            d3.event.ctrlKey || (w = a, x = w === x ? null : w, y = null, F.style("marker-end", "url(#end-arrow)").classed("hidden", !1).attr("d", "M" + w.x + "," + w.y + "L" + w.x + "," + w.y), n())
        }).on("mouseup", function (a) {
            if (w)if (F.classed("hidden", !0).style("marker-end", ""), E = a, E === w)C = E = w = null; else {
                var b = w;
                var d = E;
                a = !1 === q ? k.filter(function (a) {
                    return a.source === b && a.target === d
                })[0] : k.filter(function (a) {
                    return a.source === b && a.target === d || a.source ===
                        d && a.target === b
                })[0];
                a || (!1 === c ? (a = parseInt(Math.sqrt(Math.pow(b.x - d.x, 2) + Math.pow(b.y - d.y, 2)) / 100 + 1), a = {
                    source: b,
                    target: d,
                    weight: a
                }) : a = {source: b, target: d}, k.push(a));
                y = a;
                x = null;
                n()
            }
        });
        a.append("svg:text").attr("x", function (a) {
            return a.x
        }).attr("y", function (a) {
            return a.y + 16 / 3
        }).attr("class", "id").text(function (a) {
            return a.id
        });
        A = A.data(k);
        A.classed("selected", function (a) {
            return a === y
        });
        A.enter().append("svg:path").attr("class", "link").classed("selected", function (a) {
            return a === y
        }).style("marker-end",
            function (a) {
                if (!1 === q)return "url(#end-arrow)"
            }).attr("d", function (a) {
            var b = a.target.x - a.source.x, c = a.target.y - a.source.y, d = Math.sqrt(b * b + c * c), b = b / d, e = c / d, f = 17;
            !0 === q && (f = 12);
            var c = a.source.x + 12 * b, d = a.source.y + 12 * e, b = a.target.x - f * b, l = a.target.y - f * e;
            if (!0 !== q && k.filter(function (b) {
                    return b.source === a.target && b.target === a.source
                })[0]) {
                var g = a.source.id < a.target.id ? 1 : 2;
                var e = u(c, d, b, l, g).x, f = u(c, d, b, l, g).y, h = u(b, l, c, d, g).x, c = u(b, l, c, d, g).y;
                return "M" + h + "," + c + "L" + e + "," + f
            }
            return "M" + c + "," + d + "L" + b + "," + l
        }).on("mousedown",
            function (a) {
                d3.event.ctrlKey || (C = a, y = C === y ? null : C, x = null, n())
            });
        !1 === c && (z = l.append("svg:g").selectAll("text"), z = z.data(k), z.enter().append("svg:text").attr("class", "weight").attr("x", function (a) {
            var b = a.source.id < a.target.id ? 1 : 2;
            var c = 0;
            k.filter(function (b) {
                return b.source === a.target && b.target === a.source
            })[0] && (c = 2);
            return p(a.source.x, a.source.y, a.target.x, a.target.y, b, c).x
        }).attr("y", function (a) {
            var b = a.source.id < a.target.id ? 1 : 2;
            var c = 0;
            k.filter(function (b) {
                return b.source === a.target && b.target ===
                    a.source
            })[0] && (c = 2);
            return p(a.source.x, a.source.y, a.target.x, a.target.y, b, c).y
        }).text(function (a) {
            return a.weight
        }));
        !1 === t && (z = l.append("svg:g").selectAll("text"), z = z.data(f), z.enter().append("svg:text").attr("class", "weight").attr("x", function (a) {
            return a.x
        }).attr("y", function (a) {
            return a.y + 30
        }).text(function (a) {
            return a.weight
        }));
        var d = -1;
        for (var e = [], a = 0; a < f.length; a++)f[a].id > d && (d = f[a].id);
        d++;
        var g = Array(d);
        for (a = 0; a < d; a++)g[a] = !1;
        for (a = 0; a < f.length; a++)g[f[a].id] = !0;
        for (a = 0; a < d; a++)for (e[a] =
                                        [], b = 0; b < d; b++)e[a][b] = !0 === g[a] && !0 === g[b] ? "0" : "x";
        if (!0 === q)if (!0 === c)for (a = 0; a < k.length; a++)e[k[a].source.id][k[a].target.id] = "1", e[k[a].target.id][k[a].source.id] = "1"; else for (a = 0; a < k.length; a++)e[k[a].source.id][k[a].target.id] = k[a].weight.toString(), e[k[a].target.id][k[a].source.id] = k[a].weight.toString(); else if (!0 === c)for (a = 0; a < k.length; a++)e[k[a].source.id][k[a].target.id] = "1"; else for (a = 0; a < k.length; a++)e[k[a].source.id][k[a].target.id] = k[a].weight.toString();
        e = '{"vl":{';
        for (a = 0; a < f.length; a++)d =
            '"' + a + '":', g = {}, g.x = f[a].x, g.y = f[a].y, !1 === t && (g.w = f[a].weight), b = JSON.stringify(g), e += d + b, a !== f.length - 1 && (e += ",");
        e = e.concat('},"el":{');
        for (a = 0; a < k.length; a++) {
            d = '"' + a + '":';
            g = {};
            for (b = 0; b < f.length; b++)f[b].id == k[a].source.id && (g.u = b), f[b].id == k[a].target.id && (g.v = b);
            g.w = 1;
            !1 === c && (g.w = k[a].weight);
            b = JSON.stringify(g);
            e += d + b;
            a !== k.length - 1 && (e += ",")
        }
        JSONresult = e = e.concat("}}")
    }

    function u(a, b, c, d, k) {
        if (a === c)return 1 === k ? {x: c - 4, y: d} : {x: c + 4, y: d};
        if (b === d)return 1 === k ? {x: c, y: d - 4} : {x: c, y: d + 4};
        var e =
            -1 / ((d - b) / (c - a)), f = d - e * c;
        d = Math.sqrt(Math.pow(c - a, 2) + Math.pow(d - b, 2));
        var l = f - b;
        b = 1 + e * e;
        c = 2 * e * l - 2 * a;
        d = Math.sqrt(c * c - 4 * b * (a * a + l * l - (d * d + 16)));
        a = (-c + d) / (2 * b);
        b = (-c - d) / (2 * b);
        return 2 === k ? {x: a, y: e * a + f} : {x: b, y: e * b + f}
    }

    function p(a, b, c, d, e, k) {
        c = (a + c) / 2;
        d = (b + d) / 2;
        if (a === c)return 2 === e ? {x: c + 16, y: d} : {x: c - 16, y: d};
        if (b === d)return 2 === e ? {x: c, y: d + 16} : {x: c, y: d - 16};
        var f = -1 / ((d - b) / (c - a)), l = d - f * c;
        c = Math.sqrt(Math.pow(c - a, 2) + Math.pow(d - b, 2));
        d = 16;
        1 === k && (d = 50);
        2 === k && (d = 18);
        var g = l - b;
        b = 1 + f * f;
        k = 2 * f * g - 2 * a;
        c = Math.sqrt(k *
            k - 4 * b * (a * a + g * g - (c * c + d * d)));
        a = (-k + c) / (2 * b);
        c = (-k - c) / (2 * b);
        return 2 === e ? {x: a, y: f * a + l} : {x: c, y: f * c + l}
    }

    function r(a) {
        k.filter(function (b) {
            return b.source === a || b.target === a
        }).map(function (a) {
            k.splice(k.indexOf(a), 1)
        })
    }

    console.log(a + " " + b + " " + d + " " + e + " " + h + " " + m);
    var q = a, c = b, t = m, D = d3.scale.category10();
    d3.select("#drawgraph #viz").selectAll("svg").remove();
    var l = d3.select("#drawgraph #viz").append("svg").attr("width", 640).attr("height", 360), v = Array(100);
    for (a = v.length; 0 <= a; a--)v[a] = 0;
    var f = !0 === t ? [{
        id: 0,
        x: 100, y: 100
    }, {id: 1, x: 200, y: 200}, {id: 2, x: 300, y: 300}] : [{id: 0, x: 100, y: 100, w: 3}, {
        id: 1,
        x: 200,
        y: 200,
        w: 5
    }, {id: 2, x: 300, y: 300, w: 7}];
    var g = 3;
    var k = !0 === c ? [{source: f[0], target: f[1]}, {source: f[1], target: f[2]}] : [{
        source: f[0],
        target: f[1],
        weight: 2
    }, {source: f[1], target: f[2], weight: 2}];
    void 0 == e || void 0 == h ? (k = [], f = []) : (f = e, k = h);
    g = 0;
    g = f.length;
    for (a = 0; a < f.length; a++)v[f[a].id]++;
    for (a = 0; a < k.length; a++)for (e = 0; e < f.length; e++)f[e].id === k[a].source.id && (k[a].source = f[e]), f[e].id === k[a].target.id && (k[a].target = f[e]);
    l.append("svg:defs").append("svg:marker").attr("id",
        "end-arrow").attr("viewBox", "0 -5 10 10").attr("refX", 6).attr("markerWidth", 3).attr("markerHeight", 3).attr("orient", "auto").append("svg:path").attr("d", "M0,-5L10,0L0,5").attr("fill", "#000");
    var F = l.append("svg:path").attr("class", "link dragline hidden").attr("d", "M0,0L0,0"), A, B, z, x = null, y = null, C = null, w = null, E = null, G = d3.behavior.drag().on("drag", function (a) {
        var b, c;
        d3.select(this).select("circle").attr("cx", function () {
            return b = d3.mouse($("svg")[0])[0]
        }).attr("cy", function () {
            return c = d3.mouse($("svg")[0])[1]
        });
        a.x = b;
        a.y = c;
        a.x = parseInt(a.x) - parseInt(a.x) % 20;
        a.y = parseInt(a.y) - parseInt(a.y) % 20;
        n()
    });
    l.on("mousedown", function () {
        l.classed("active", !0);
        if (!(d3.event.ctrlKey || w || C)) {
            var a = d3.mouse(this), b = {id: g};
            v[g]++;
            for (var c = 0; 100 > c; c++)if (0 === v[c]) {
                g = c;
                break
            }
            b.x = a[0];
            b.y = a[1];
            !1 === t && (b.weight = 1);
            b.x = parseInt(b.x) - parseInt(b.x) % 20;
            b.y = parseInt(b.y) - parseInt(b.y) % 20;
            f.push(b);
            n()
        }
    }).on("mousemove", function () {
        w && (F.attr("d", "M" + w.x + "," + w.y + "L" + d3.mouse(this)[0] + "," + d3.mouse(this)[1]), n())
    }).on("mouseup", function () {
        w &&
        F.classed("hidden", !0);
        l.classed("active", !1);
        C = E = w = null
    });
    d3.select(window).on("keydown", function () {
        var a;
        17 === d3.event.keyCode && (B.call(G), l.classed("ctrl", !0));
        if (x || y)switch (console.log(d3.event.keyCode + " " + x + " " + t), d3.event.keyCode) {
            case 46:
                if (x)for (f.splice(f.indexOf(x), 1), r(x), a = v[x.id] = 0; 100 > a; a++) {
                    if (0 === v[a]) {
                        g = a;
                        break
                    }
                } else y && k.splice(k.indexOf(y), 1);
                x = y = null;
                n();
                break;
            case 13:
                if (y && !1 === c) {
                    for (; ;) {
                        var b = prompt("Enter new weight: (<= 99)");
                        if (99 >= b)break
                    }
                    a = k.indexOf(y);
                    k[a].weight = b
                } else if (x &&
                    !1 === t) {
                    for (; !(b = prompt("Enter new weight: (<= 99)"), 99 >= b););
                    a = f.indexOf(x);
                    f[a].weight = b
                }
                n()
        }
    }).on("keyup", function () {
        17 === d3.event.keyCode && (B.on("mousedown.drag", null).on("touchstart.drag", null), l.classed("ctrl", !1))
    });
    n()
}
function write(a, b, d) {
    void 0 === d && (d = "true");
    a = '\r\n  <script>var JSONresult;\x3c/script>\r\n    <div id="main">\r\n      <div id="draw-status"><p>Status</p></div>\r\n      <div id="draw-warn"><p>No Warning</p></div>\r\n      <div id="draw-err"><p>No Error</p></div>\r\n      <div id="viz">\r\n\r\n        <svg onClick = "GraphVisu(' + a + "," + b + ",null,null,null," + d + '); " width="640" height="360"><defs><marker id="end-arrow" viewBox="0 -5 10 10" refX="6" markerWidth="3" markerHeight="3" orient="auto"><path d="M0,-5L10,0L0,5" fill="#000"></path></marker></defs><path class="link dragline hidden" d="M0,0L0,0"></path><g><path class="link" d="M108.48528137423857,108.48528137423857L191.51471862576142,191.51471862576142"></path><path class="link" d="M208.48528137423858,208.48528137423858L291.5147186257614,291.5147186257614"></path></g><g><g><circle class="node" r="16" cx="100" cy="100" style="fill: rgb(238, 238, 238);"></circle><text x="100" y="105.33333333333333" class="id">0</text></g><g><circle class="node" r="16" cx="200" cy="200" style="fill: rgb(238, 238, 238);"></circle><text x="200" y="205.33333333333334" class="id">1</text></g><g><circle class="node" r="16" cx="300" cy="300" style="fill: rgb(238, 238, 238);"></circle><text x="300" y="305.3333333333333" class="id">2</text></g></g><g></g>\r\n        <text x = "250" y = "100"> &bull; Click on empty space to add vertex</text>\r\n        <text x = "250" y = "125"> &bull; Drag from vertex to vertex to add edge</text>\r\n        <text x = "250" y = "150"> &bull; Select + Delete to delete vertex/edge</text>\r\n        <text x = "250" y = "175"> &bull; Select Edge + Enter to change edge\'s weight</text>\r\n      </svg>\r\n    </div>\r\n\r\n\r\n    <div id="drawgraph-actions">\r\n      <p onclick=drawCancel()>Cancel</p>\r\n      <p onclick=GraphVisu(' +
        a + "," + b + "," + d + ')>Clear</p>\r\n      <p onclick=drawDone()>Done</p>\r\n      <form id="drawgraph-form">\r\n        \x3c!--<input type="checkbox" id="submit" name="submit" value="submit" checked="checked">Submit drawn graph to database for random graph and online quiz purposes\r\n        <br>--\x3e<input type="checkbox" id="copy" name="submit" value="submit" checked="checked">Copy JSON text to clipboard\r\n      </form>\r\n    </div>\r\n\r\n  ';
    $("#drawgraph").html(a);
    $("#copy").removeAttr("checked")
}
;var GraphEdgeWidget = function (a, b, d, e, h) {
    function m() {
        var c = a ? parseFloat(a.getAttributes().outerVertex.cx) : void 0;
        var d = a ? parseFloat(a.getAttributes().outerVertex.cy) : void 0;
        var e = a ? parseFloat(b.getAttributes().outerVertex.cx) : void 0;
        var f = a ? parseFloat(b.getAttributes().outerVertex.cy) : void 0;
        var l = a ? parseFloat(a.getAttributes().outerVertex.r) : void 0;
        l = n(c, d, e, f, l, c, d);
        var g = a ? parseFloat(b.getAttributes().outerVertex.r) : void 0;
        c = n(c, d, e, f, g, e, f);
        d = 5E3;
        f = e = 0;
        for (g = 1; 3 >= g; g += 2)for (var h = 1; 3 >= h; h += 2) {
            var m =
                Math.sqrt((l[g - 1] - c[h - 1]) * (l[g - 1] - c[h - 1]) + (l[g] - c[h]) * (l[g] - c[h]));
            m < d && (d = m, e = g, f = h)
        }
        return [{x: l[e - 1], y: l[e]}, {x: c[f - 1], y: c[f]}]
    }

    function n(a, b, c, d, e, f, l) {
        c -= a;
        d -= b;
        f -= a;
        var g = l - b, k = c * c + d * d;
        l = (c * f + d * g) / k;
        f = Math.sqrt(l * l - (f * f + g * g - e * e) / k);
        e = -l + f;
        l = -l - f;
        f = [];
        f[0] = a - c * e;
        f[1] = b - d * e;
        f[2] = a - c * l;
        f[3] = b - d * l;
        return f
    }

    function u(a) {
        if (null == a || isNaN(a))a = 250;
        0 >= a && (a = 1);
        q.attr("class", g.path["class"]);
        q.transition().duration(a).attr("d", g.path.d).attr("stroke", g.path.stroke).attr("stroke-width", g.path["stroke-width"]).style("marker-start",
            function () {
                return g.path.d == f ? null : "bde" == g.path["class"] ? "url(#backwardArrow)" : null
            }).style("marker-end", function () {
            return g.path.d == f ? null : "de" == g.path["class"] || "bde" == g.path["class"] ? "url(#arrow)" : null
        });
        c.transition().duration(a).attr("fill", g.weight.fill).attr("font-family", g.weight["font-family"]).attr("font-size", g.weight["font-size"]).attr("font-weight", g.weight["font-weight"]).attr("text-anchor", g.weight["text-anchor"]).attr("text-decoration", "underline");
        D.transition().duration(a).text(function () {
            return g.weight.text
        })
    }

    function p() {
        v = l(m());
        f = l([m()[0], m()[0]])
    }

    markerSvg.select("#arrow").empty() && markerSvg.append("marker").attr("id", "arrow").attr("viewBox", "0 -5 10 10").attr("refX", ARROW_REFX).attr("markerWidth", ARROW_MARKER_WIDTH).attr("markerHeight", ARROW_MARKER_HEIGHT).attr("orient", "auto").append("path").attr("d", "M0,-5 L10,0 L0,5").attr("fill", ARROW_FILL);
    markerSvg.select("#backwardArrow").empty() && markerSvg.append("marker").attr("id", "backwardArrow").attr("viewBox", "-10 -5 10 10").attr("refX", -1 * ARROW_REFX).attr("markerWidth",
        ARROW_MARKER_WIDTH).attr("markerHeight", ARROW_MARKER_HEIGHT).attr("orient", "auto").append("path").attr("d", "M0,-5 L-10,0 L0,5").attr("fill", ARROW_FILL);
    if (null == h || isNaN(h))h = 1;
    var r = this, q, c, t, D, l = d3.svg.line().x(function (a) {
        return a.x
    }).y(function (a) {
        return a.y
    }).interpolate("linear"), v = l(m()), f = l([m()[0], m()[0]]), g = {
        path: {id: null, "class": null, d: null, stroke: null, "stroke-width": null}, weight: {
            id: null,
            startOffset: null,
            dy: null,
            fill: null,
            "font-family": null,
            "font-weight": null,
            "font-size": null,
            "text-anchor": null,
            text: null
        }
    };
    p();
    (function () {
        g.path.id = "e" + d;
        g.path.d = f;
        g.path.stroke = graphEdgeProperties.path["default"].stroke;
        g.path["stroke-width"] = graphEdgeProperties.path["default"]["stroke-width"];
        switch (e) {
            case EDGE_TYPE_UDE:
                g.path["class"] = "ude";
                break;
            case EDGE_TYPE_DE:
                g.path["class"] = "de";
                break;
            case EDGE_TYPE_BDE:
                g.path["class"] = "bde"
        }
        g.weight.id = "ew" + d;
        g.weight.startOffset = graphEdgeProperties.weight["default"].startOffset;
        g.weight.dy = graphEdgeProperties.weight["default"].dy;
        g.weight.fill = graphEdgeProperties.weight["default"].fill;
        g.weight["font-family"] = graphEdgeProperties.weight["default"]["font-family"];
        g.weight["font-size"] = 0;
        g.weight["font-weight"] = graphEdgeProperties.weight["default"]["font-weight"];
        g.weight["text-anchor"] = graphEdgeProperties.weight["default"]["text-anchor"];
        g.weight.text = h;
        q = edgeSvg.append("path");
        q.attr("id", g.path.id).attr("class", g.path["class"]);
        try {
            "MNaN,NaNLNaN,NaN" != g.path.d && q.attr("d", g.path.d).attr("stroke", g.path.stroke).attr("stroke-width", g.path["stroke-width"])
        } catch (k) {
        }
        c = edgeWeightSvg.append("text");
        c.attr("id", g.weight.id);
        c.attr("fill", g.weight.fill).attr("font-family", g.weight["font-family"]).attr("font-size", g.weight["font-size"]).attr("font-weight", g.weight["font-weight"]).attr("text-anchor", g.weight["text-anchor"]);
        t = c.append("textPath").attr("xlink:href", function () {
            return "#" + g.path.id
        }).attr("startOffset", g.weight.startOffset);
        D = t.append("tspan").attr("dy", g.weight.dy).text(function () {
            return g.weight.text
        })
    })();
    this.redraw = function (a) {
        u(a)
    };
    this.animateHighlighted = function (a) {
        if (null ==
            a || isNaN(a))a = 250;
        0 >= a && (a = 1);
        edgeSvg.append("path").attr("id", "tempEdge" + q.attr("id")).attr("stroke", graphEdgeProperties.animateHighlightedPath.stroke).attr("stroke-width", graphEdgeProperties.animateHighlightedPath["stroke-width"]).transition().duration(a).each("start", function () {
            edgeSvg.select("#tempEdge" + q.attr("id")).attr("d", f)
        }).attr("d", v).each("end", function () {
            q.attr("stroke", graphEdgeProperties.path.highlighted.stroke).attr("stroke-width", graphEdgeProperties.path["stroke-width"]);
            edgeSvg.select("#tempEdge" +
                q.attr("id")).remove();
            u(0)
        })
    };
    this.showEdge = function () {
        g.path.d = v;
        g.path["stroke-width"] = graphEdgeProperties.path["stroke-width"]
    };
    this.hideEdge = function () {
        g.path.d = f
    };
    this.showWeight = function () {
        g.weight["font-size"] = graphEdgeProperties.weight["font-size"]
    };
    this.hideWeight = function () {
        g.weight["font-size"] = 0
    };
    this.stateEdge = function (a) {
        for (var b in graphEdgeProperties.path[a])g.path[b] = graphEdgeProperties.path[a][b];
        for (b in graphEdgeProperties.weight[a])g.weight[b] = graphEdgeProperties.weight[a][b]
    };
    this.removeEdge = function () {
        a.removeEdge(r);
        b.removeEdge(r);
        q.remove();
        c.remove()
    };
    this.refreshPath = function () {
        var a = f;
        p();
        g.path.d = g.path.d == a ? f : v
    };
    this.changeVertexA = function (b) {
        var c = !1;
        g.path.d == v && (c = !0);
        a.removeEdge(r);
        a = b;
        p();
        v = l(m());
        f = l([m()[0]]);
        g.path.d = f;
        a.addEdge(r);
        c && (g.path.d = v)
    };
    this.changeVertexB = function (a) {
        var c = !1;
        g.path.d == v && (c = !0);
        b.removeEdge(r);
        b = a;
        p();
        v = l(m());
        f = l([m()[0]]);
        g.path.d = f;
        b.addEdge(r);
        c && (g.path.d = v)
    };
    this.changeType = function (a) {
        e = a;
        switch (e) {
            case EDGE_TYPE_UDE:
                g.path["class"] =
                    "ude";
                break;
            case EDGE_TYPE_DE:
                g.path["class"] = "de";
                break;
            case EDGE_TYPE_BDE:
                g.path["class"] = "bde"
        }
    };
    this.changeWeight = function (a) {
        h = a;
        g.weight.text = h
    };
    this.getVertex = function () {
        return [a, b]
    };
    this.getAttributes = function () {
        return deepCopy(g.path)
    };
    this.getType = function () {
        return e
    }
};
var GraphPolygonWidget = function (a, b) {
    var d = null, e = null, h = {
        polygon: {
            "class": null,
            points: null,
            fill: null,
            "stroke-width": null,
            opacity: null
        }
    };
    (function () {
        d = polygonSvg.append("polygon");
        h.polygon["class"] = "p" + a;
        var e = "";
        for (key in b)e = e + b[key].x + "," + b[key].y + " ";
        h.polygon.points = e;
        h.polygon.fill = graphPolygonProperties.polygon["default"].fill;
        h.polygon["stroke-width"] = 0;
        h.polygon.opacity = 1;
        d.attr("class", h.polygon["class"]).attr("points", h.polygon.points).attr("fill", h.polygon.fill).attr("stroke-width",
            h.polygon["stroke-width"]).attr("opacity", h.polygon.opacity)
    })();
    this.redraw = function (a) {
        if (null == a || isNaN(a))a = 250;
        0 >= a && (a = 1);
        d.transition().duration(a).attr("points", h.polygon.points).attr("fill", h.polygon.fill).attr("stroke-width", h.polygon["stroke-width"]).attr("opacity", h.polygon.opacity)
    };
    this.showPolygon = function () {
        if (null == e || void 0 == e)e = POLYGON_DEFAULT;
        h.polygon["class"] = graphPolygonProperties.polygon["class"];
        h.polygon["stroke-width"] = graphPolygonProperties.polygon["stroke-width"];
        h.polygon.fill =
            graphPolygonProperties.polygon[e].fill;
        h.polygon.opacity = graphPolygonProperties.polygon[e].opacity
    };
    this.hidePolygon = function () {
        h.polygon.opacity = 0
    };
    this.removePolygon = function () {
        d.remove()
    };
    this.statePolygon = function (a) {
        e = a;
        for (var b in graphPolygonProperties.polygon[e])h.polygon[b] = graphPolygonProperties.polygon[e][b]
    };
    this.getAttributes = function () {
        return deepCopy(h)
    };
    this.getClassNumber = function () {
        return a
    }
};
var GraphVertexWidget = function (a, b, d, e, h) {
    function m(a) {
        if (null == a || isNaN(a))a = 250;
        0 >= a && (a = 1);
        n.transition().duration(a).attr("cx", c.innerVertex.cx).attr("cy", c.innerVertex.cy).attr("x", c.innerVertex.x).attr("y", c.innerVertex.y).attr("fill", c.innerVertex.fill).attr("r", c.innerVertex.r).attr("width", c.innerVertex.width).attr("height", c.innerVertex.height).attr("stroke", c.innerVertex.stroke).attr("stroke-width", c.innerVertex["stroke-width"]);
        u.transition().duration(a).attr("cx", c.outerVertex.cx).attr("cy",
            c.outerVertex.cy).attr("x", c.outerVertex.x).attr("y", c.outerVertex.y).attr("fill", c.outerVertex.fill).attr("r", c.outerVertex.r).attr("width", c.outerVertex.width).attr("height", c.outerVertex.height).attr("stroke", c.outerVertex.stroke).attr("stroke-width", c.outerVertex["stroke-width"]);
        p.transition().duration(a).attr("x", c.text.x).attr("y", c.text.y).attr("fill", c.text.fill).attr("font-family", c.text["font-family"]).attr("font-size", c.text["font-size"]).attr("font-weight", c.text["font-weight"]).attr("text-anchor",
            c.text["text-anchor"]).text(function () {
            return c.text.text
        });
        r.transition().duration(a).attr("x", c.extratext.x).attr("y", c.extratext.y).attr("fill", c.extratext.fill).attr("font-family", c.extratext["font-family"]).attr("font-size", c.extratext["font-size"]).attr("font-weight", c.extratext["font-weight"]).attr("text-anchor", c.extratext["text-anchor"]).text(function () {
            return c.extratext.text
        })
    }

    var n, u, p, r, q = graphVertexProperties.text["font-size"] / 3, c = {
        innerVertex: {
            "class": null, cx: null, cy: null, x: null, y: null,
            fill: null, r: null, width: null, height: null, stroke: null, "stroke-width": null
        },
        outerVertex: {
            "class": null,
            cx: null,
            cy: null,
            x: null,
            y: null,
            fill: null,
            r: null,
            width: null,
            height: null,
            stroke: null,
            "stroke-width": null
        },
        text: {
            "class": null,
            x: null,
            y: null,
            fill: null,
            "font-family": null,
            "font-weight": null,
            "font-size": null,
            "text-anchor": null,
            text: null
        },
        extratext: {
            "class": null,
            x: null,
            y: null,
            fill: null,
            "font-family": null,
            "font-weight": null,
            "font-size": null,
            "text-anchor": null,
            text: null
        }
    }, t = {};
    (function () {
        var t = d;
        "rect_long" ==
        d && (t = "rect");
        u = vertexSvg.append(t);
        n = vertexSvg.append(t);
        p = vertexTextSvg.append("text");
        r = vertexTextSvg.append("text");
        c.innerVertex["class"] = "v" + h;
        c.innerVertex.cx = a;
        c.innerVertex.cy = b;
        c.innerVertex.x = a - graphVertexProperties.innerVertex.width / 2;
        c.innerVertex.y = b - graphVertexProperties.innerVertex.height / 2;
        c.innerVertex.fill = graphVertexProperties.innerVertex["default"].fill;
        c.innerVertex.r = 0;
        c.innerVertex.width = 0;
        c.innerVertex.height = 0;
        c.innerVertex.stroke = graphVertexProperties.innerVertex["default"].stroke;
        c.innerVertex["stroke-width"] = 0;
        c.outerVertex["class"] = "v" + h;
        c.outerVertex.cx = a;
        c.outerVertex.cy = b;
        c.outerVertex.x = a - graphVertexProperties.outerVertex.width / 2;
        c.outerVertex.y = b - graphVertexProperties.outerVertex.height / 2;
        c.outerVertex.fill = graphVertexProperties.outerVertex["default"].fill;
        c.outerVertex.r = 0;
        c.innerVertex.width = 0;
        c.innerVertex.height = 0;
        c.outerVertex.stroke = graphVertexProperties.outerVertex["default"].stroke;
        c.outerVertex["stroke-width"] = 0;
        c.text["class"] = "v" + h;
        c.text.x = a;
        c.text.y = b +
            q;
        c.text.fill = graphVertexProperties.text["default"].fill;
        c.text["font-family"] = graphVertexProperties.text["default"]["font-family"];
        c.text["font-size"] = 0;
        c.text["font-weight"] = graphVertexProperties.text["default"]["font-weight"];
        c.text["text-anchor"] = graphVertexProperties.text["default"]["text-anchor"];
        "rect_long" == d && (c.text["text-anchor"] = "left");
        c.text.text = e;
        c.extratext["class"] = "v" + h;
        c.extratext.x = a;
        c.extratext.y = b + q + 26;
        c.extratext.fill = "red";
        c.extratext["font-family"] = graphVertexProperties.text["default"]["font-family"];
        c.extratext["font-size"] = 0;
        c.extratext["font-weight"] = graphVertexProperties.text["default"]["font-weight"];
        c.extratext["text-anchor"] = graphVertexProperties.text["default"]["text-anchor"];
        "rect_long" == d && (c.extratext["text-anchor"] = "left");
        c.extratext.text = "";
        n.attr("class", c.innerVertex["class"]);
        u.attr("class", c.outerVertex["class"]);
        p.attr("class", c.text["class"]);
        r.attr("class", c.extratext["class"]);
        n.attr("cx", c.innerVertex.cx).attr("cy", c.innerVertex.cy).attr("x", c.innerVertex.x).attr("y", c.innerVertex.y).attr("fill",
            c.innerVertex.fill).attr("r", c.innerVertex.r).attr("width", c.innerVertex.width).attr("height", c.innerVertex.height).attr("stroke", c.innerVertex.stroke).attr("stroke-width", c.innerVertex["stroke-width"]);
        u.attr("cx", c.outerVertex.cx).attr("cy", c.outerVertex.cy).attr("x", c.outerVertex.x).attr("y", c.outerVertex.y).attr("fill", c.outerVertex.fill).attr("r", c.outerVertex.r).attr("width", c.outerVertex.width).attr("height", c.outerVertex.height).attr("stroke", c.outerVertex.stroke).attr("stroke-width", c.outerVertex["stroke-width"]);
        p.attr("x", c.text.x).attr("y", c.text.y).attr("fill", c.text.fill).attr("font-family", c.text["font-family"]).attr("font-size", c.text["font-size"]).attr("font-weight", c.text["font-weight"]).attr("text-anchor", c.text["text-anchor"]).text(function () {
            return c.text.text
        });
        r.attr("x", c.extratext.x).attr("y", c.extratext.y).attr("fill", c.extratext.fill).attr("font-family", c.extratext["font-family"]).attr("font-size", c.extratext["font-size"]).attr("font-weight", c.extratext["font-weight"]).attr("text-anchor",
            c.extratext["text-anchor"]).text(function () {
            return c.extratext.text
        })
    })();
    this.redraw = function (a) {
        m(a)
    };
    this.showVertex = function () {
        c.outerVertex.r = graphVertexProperties.outerVertex.r;
        c.outerVertex.width = graphVertexProperties.outerVertex.width;
        c.outerVertex.height = graphVertexProperties.outerVertex.height;
        c.outerVertex["stroke-width"] = graphVertexProperties.outerVertex["stroke-width"];
        c.innerVertex.r = graphVertexProperties.innerVertex.r;
        c.innerVertex.width = graphVertexProperties.innerVertex.width;
        c.innerVertex.height =
            graphVertexProperties.innerVertex.height;
        c.innerVertex["stroke-width"] = graphVertexProperties.innerVertex["stroke-width"];
        c.text["font-size"] = graphVertexProperties.text["font-size"];
        c.extratext["font-size"] = graphVertexProperties.text["font-size"];
        "rect_long" == d ? (c.outerVertex.width = 200, c.innerVertex.width = 198) : "rect" == d && (c.outerVertex.width = 80, c.innerVertex.width = 78)
    };
    this.hideVertex = function () {
        c.outerVertex.r = 0;
        c.outerVertex.width = 0;
        c.outerVertex.height = 0;
        c.outerVertex["stroke-width"] = 0;
        c.innerVertex.r =
            0;
        c.innerVertex.width = 0;
        c.innerVertex.height = 0;
        c.innerVertex["stroke-width"] = 0;
        c.text["font-size"] = 0;
        c.extratext["font-size"] = 0
    };
    this.moveVertex = function (a, b) {
        c.outerVertex.cx = a;
        c.outerVertex.cy = b;
        c.outerVertex.x = a - graphVertexProperties.outerVertex.width / 2;
        c.outerVertex.y = b - graphVertexProperties.outerVertex.height / 2;
        c.innerVertex.cx = a;
        c.innerVertex.cy = b;
        c.innerVertex.x = a - graphVertexProperties.innerVertex.width / 2;
        c.innerVertex.y = b - graphVertexProperties.innerVertex.height / 2;
        c.text.x = a;
        c.text.y = b + q;
        c.extratext.x = a;
        c.extratext.y = b + q + 26;
        for (var d in t)t[d].refreshPath()
    };
    this.changeText = function (a) {
        e = a;
        c.text.text = a
    };
    this.changeExtraText = function (a) {
        c.extratext.text = a
    };
    this.changeTextFontSize = function (a) {
        null == newTextSize || isNaN(newTextSize) || (c.text["font-size"] = newTextSize, c.extratext["font-size"] = newTextSize)
    };
    this.changeRadius = function (a, b) {
        null == a || isNaN(a) || (c.innerVertex.r = a, null == b || isNaN(b) || (c.outerVertex.r = b))
    };
    this.changeWidth = function (a, b) {
        null == a || isNaN(a) || (c.innerVertex.width =
            a, null == b || isNaN(b) || (c.outerVertex.width = b))
    };
    this.changeHeight = function (a, b) {
        null == a || isNaN(a) || (c.innerVertex.height = a, null == b || isNaN(b) || (c.outerVertex.height = b))
    };
    this.changeStrokeWidth = function (a, b) {
        null == a || isNaN(a) || (c.innerVertex["stroke-width"] = a, null == b || isNaN(b) || (c.outerVertex["stroke-width"] = b))
    };
    this.removeVertex = function () {
        u.remove();
        n.remove();
        p.remove();
        r.remove()
    };
    this.stateVertex = function (a) {
        for (var b in graphVertexProperties.innerVertex[a])c.innerVertex[b] = graphVertexProperties.innerVertex[a][b];
        for (b in graphVertexProperties.outerVertex[a])c.outerVertex[b] = graphVertexProperties.outerVertex[a][b];
        for (b in graphVertexProperties.text[a])c.text[b] = graphVertexProperties.text[a][b]
    };
    this.getAttributes = function () {
        return deepCopy(c)
    };
    this.getClassNumber = function () {
        return h
    };
    this.addEdge = function (a) {
        t[a.getAttributes().id] = a
    };
    this.removeEdge = function (a) {
        null != t[a.getAttributes().id] && void 0 != t[a.getAttributes().id] && delete t[a.getAttributes().id]
    };
    this.getEdge = function () {
        var a = [], b;
        for (b in t)a.push(t[b]);
        return a
    }
};
var GraphWidget = function () {
    function a(a, c) {
        var l, t = Object.keys(r).length - 1;
        try {
            $("#progress-bar").slider("value", p), $("#status p").html(r[p].status), highlightLine(r[p].lineNo), p == t ? (pause(), (l = $("#play img").attr("src")) && $("#play img").attr("src", l.replace("/play.png", "/replay.png").replace("/pause.png", "/replay.png")), $("#play img").attr("alt", "replay").attr("title", "replay")) : ((l = $("#play img").attr("src")) && $("#play img").attr("src", l.replace("/replay.png", "/play.png").replace("/pause.png", "/play.png")),
                $("#play img").attr("alt", "play").attr("title", "play"))
        } catch (A) {
        }
        l = a.vl;
        for (var f in l)null != d[f] && void 0 != d[f] || b.addVertex(l[f].cx, l[f].cy, l[f].text, f, !1), t = d[f], t.showVertex(), l[f].state == OBJ_HIDDEN ? t.hideVertex() : null != l[f].state ? t.stateVertex(l[f].state) : t.stateVertex(VERTEX_DEFAULT), t.moveVertex(l[f].cx, l[f].cy), t.changeText(l[f].text), null != l[f]["text-font-size"] && t.changeTextFontSize(l[f]["text-font-size"]), null != l[f]["inner-r"] && null != l[f]["outer-r"] && t.changeRadius(l[f]["inner-r"], l[f]["outer-r"]),
        null != l[f]["inner-w"] && null != l[f]["outer-w"] && t.changeWidth(l[f]["inner-w"], l[f]["outer-w"]), null != l[f]["inner-h"] && null != l[f]["outer-h"] && t.changeHeight(l[f]["inner-h"], l[f]["outer-h"]), null != l[f]["inner-stroke-width"] && null != l[f]["outer-stroke-width"] && t.changeStrokeWidth(l[f]["inner-stroke-width"], l[f]["outer-stroke-width"]), null == l[f].extratext ? t.changeExtraText("") : t.changeExtraText(l[f].extratext), t.redraw(c), m[f] = !0;
        for (f in m)0 == m[f] && (d[f].hideVertex(), d[f].redraw(c), m[f] = !0);
        for (f in m)m[f] = !1;
        f = a.el;
        try {
            for (k in f) {
                null != e[k] && void 0 != e[k] || b.addEdge(f[k].vertexA, f[k].vertexB, k, f[k].type, f[k].weight, !1);
                var g = e[k];
                g.showEdge();
                f[k].state == OBJ_HIDDEN ? g.hideEdge() : null != f[k].state ? g.stateEdge(f[k].state) : g.stateEdge(EDGE_DEFAULT);
                g.hideWeight();
                f[k].state != OBJ_HIDDEN && null != f[k].displayWeight && f[k].displayWeight && g.showWeight();
                g.changeVertexA(d[f[k].vertexA]);
                g.changeVertexB(d[f[k].vertexB]);
                null == f[k].type && (f[k].type = EDGE_TYPE_UDE);
                g.changeType(f[k].type);
                null != f[k].weight && g.changeWeight(f[k].weight);
                g.refreshPath();
                null != f[k].animateHighlighted && f[k].animateHighlighted ? g.animateHighlighted(.9 * c) : g.redraw(c);
                n[k] = !0
            }
            for (k in n)0 == n[k] && (e[k].hideWeight(), e[k].hideEdge(), e[k].redraw(c), n[k] = !0);
            for (k in n)n[k] = !1
        } catch (A) {
        }
        var k = a.pl;
        for (var q in k)null != h[q] && void 0 != h[q] || b.addPolygon(q, k[q].points, !1), g = h[q], g.showPolygon(), null != k[q].state ? g.statePolygon(k[q].state) : g.statePolygon(POLYGON_DEFAULT), g.redraw(c), u[q] = !0;
        for (q in u)0 == u[q] && (h[q].hidePolygon(), h[q].redraw(c), u[q] = !0);
        for (q in u)u[q] = !1
    }

    var b = this, d = {}, e = {}, h = {}, m = {}, n = {}, u = {}, p = NO_ITERATION, r = NO_STATELIST, q = ANIMATION_STOP, c = 500;
    this.clearAll = function () {
        mainSvg.select("#polygon").empty() && (polygonSvg = mainSvg.append("g").attr("id", "polygon"));
        mainSvg.select("#edge").empty() && (edgeSvg = mainSvg.append("g").attr("id", "edge"));
        mainSvg.select("#vertex").empty() && (vertexSvg = mainSvg.append("g").attr("id", "vertex"));
        mainSvg.select("#vertexText").empty() && (vertexTextSvg = mainSvg.append("g").attr("id", "vertexText"));
        mainSvg.select("#edgeWeight").empty() &&
        (edgeWeightSvg = mainSvg.append("g").attr("id", "edgeWeight"));
        mainSvg.select("#edgeWeightPath").empty() && (edgeWeightPathSvg = mainSvg.append("g").attr("id", "edgeWeightPath"));
        mainSvg.select("#marker").empty() && (markerSvg = mainSvg.append("g").attr("id", "marker"))
    };
    b.clearAll();
    this.addVertex = function (a, b, c, e, f, g) {
        0 != f && (f = !0);
        a = new GraphVertexWidget(a, b, VERTEX_SHAPE_CIRCLE, c, e);
        "" != g && a.changeExtraText(g);
        d[e] = a;
        m[e] = !1;
        1 == f && (d[e].showVertex(), d[e].redraw());
        setTimeout(function () {
            document.body.style.zoom =
                "100.1%"
        }, 500);
        setTimeout(function () {
            document.body.style.zoom = "100%"
        }, 600)
    };
    this.addRectVertex = function (a, b, c, e, f, g) {
        0 != f && (f = !0);
        "undefined" == typeof g && (g = VERTEX_SHAPE_RECT);
        a = new GraphVertexWidget(a, b, g, c, e);
        d[e] = a;
        m[e] = !1;
        1 == f && (d[e].showVertex(), d[e].redraw());
        setTimeout(function () {
            document.body.style.zoom = "100.1%"
        }, 500);
        setTimeout(function () {
            document.body.style.zoom = "100%"
        }, 600)
    };
    this.addEdge = function (a, b, c, h, f, g, k) {
        try {
            0 != g && (g = !0);
            1 != k && (k = !1);
            if (null == h || isNaN(h))h = EDGE_TYPE_UDE;
            if (null ==
                f || isNaN(f))f = 1;
            var l = new GraphEdgeWidget(d[a], d[b], c, h, f);
            e[c] = l;
            n[c] = !1;
            d[a].addEdge(l);
            d[b].addEdge(l);
            1 == g && (e[c].showEdge(), 1 == k && e[c].showWeight(), e[c].redraw());
            setTimeout(function () {
                document.body.style.zoom = "100.1%"
            }, 500);
            setTimeout(function () {
                document.body.style.zoom = "100%"
            }, 600)
        } catch (A) {
        }
    };
    this.removeEdge = function (a) {
        null != e[a] && void 0 != e[a] && (e[a].removeEdge(), delete e[a], delete n[a], setTimeout(function () {
            document.body.style.zoom = "100.1%"
        }, 500), setTimeout(function () {
            document.body.style.zoom =
                "100%"
        }, 600))
    };
    this.removeVertex = function (a) {
        null != d[a] && void 0 != m[a] && (d[a].removeVertex(), delete d[a], delete m[a], setTimeout(function () {
            document.body.style.zoom = "100.1%"
        }, 500), setTimeout(function () {
            document.body.style.zoom = "100%"
        }, 600))
    };
    this.addPolygon = function (a, b, c) {
        0 != c && (c = !0);
        b = new GraphPolygonWidget(a, b);
        h[a] = b;
        u[a] = !1;
        1 == c && (h[a].showPolygon(), h[a].redraw())
    };
    this.removePolygon = function (a) {
        null != h[a] && void 0 != u[a] && (h[a].removePolygon(), delete h[a], delete u[a])
    };
    this.updateGraph = function (b,
                                 d) {
        if (null == d || isNaN(d))d = c;
        a(b, d);
        setTimeout(function () {
            document.body.style.zoom = "100.1%"
        }, 500);
        setTimeout(function () {
            document.body.style.zoom = "100%"
        }, 600)
    };
    this.startAnimation = function (a, c) {
        null != a && (r = a);
        p = 0;
        b.play(c)
    };
    this.animate = function (a) {
        p >= r.length && q != ANIMATION_STOP && (q = ANIMATION_PAUSE);
        p == r.length - 1 && "function" === typeof a && a();
        q != ANIMATION_PAUSE && q != ANIMATION_STOP && (b.next(c), setTimeout(function () {
            b.animate(a)
        }, c))
    };
    this.play = function (d) {
        0 > p && (p = 0);
        q == ANIMATION_STOP ? (q = ANIMATION_PLAY,
            a(r[p], c), setTimeout(function () {
            b.animate(d)
        }, c)) : (q = ANIMATION_PLAY, b.animate(d))
    };
    this.pause = function () {
        q = ANIMATION_PAUSE
    };
    this.stop = function () {
        b.jumpToIteration(r.length - 1, 0);
        p = r.length - 1;
        q = ANIMATION_STOP;
        var a = r[p].vl, c = r[p].el, d;
        for (d in c)n[d] = !0;
        for (d in n)0 == n[d] && b.removeEdge(d);
        for (d in a)m[d] = !0;
        for (d in m)0 == m[d] && b.removeVertex(d);
        for (d in n)n[d] = !1;
        for (d in m)m[d] = !1;
        r = NO_STATELIST;
        p = NO_ITERATION
    };
    this.next = function (b) {
        0 > p && (p = 0);
        p++;
        p >= r.length ? p = r.length - 1 : a(r[p], b)
    };
    this.previous =
        function (b) {
            p >= r.length && (p = r.length - 1);
            p--;
            0 > p || a(r[p], b)
        };
    this.forceNext = function (a) {
        b.pause();
        b.next(a)
    };
    this.forcePrevious = function (a) {
        b.pause();
        b.previous(a)
    };
    this.jumpToIteration = function (c, d) {
        b.pause();
        p = c;
        p >= r.length && (p = r.length - 1);
        0 > p && (p = 0);
        a(r[p], d)
    };
    this.replay = function () {
        b.jumpToIteration(0, 0);
        setTimeout(function () {
            b.play()
        }, 500)
    };
    this.getCurrentIteration = function () {
        return p
    };
    this.getTotalIteration = function () {
        return Object.keys(r).length
    };
    this.getAnimationDuration = function () {
        return c
    };
    this.getCurrentState = function () {
        return r[p]
    };
    this.setAnimationDuration = function (a) {
        c = a
    };
    this.removeAll = function () {
        for (var a in e)e[a].removeEdge();
        for (a in d)d[a].removeVertex();
        for (a in h)h[a].removePolygon();
        e = {};
        d = {};
        h = {};
        m = {};
        n = {};
        u = {}
    }
};
var ObjectPair = function (a, b) {
    this.getFirst = function () {
        return a
    };
    this.getSecond = function () {
        return b
    };
    this.setFirst = function (b) {
        a = b
    };
    this.setSecond = function (a) {
        b = a
    }
};
ObjectPair.compare = function (a, b) {
    return a.getFirst() > b.getFirst() ? 1 : a.getFirst() < b.getFirst() ? -1 : a.getSecond() > b.getSecond() ? 1 : a.getSecond() < b.getSecond() ? -1 : 0
};
var ObjectTriple = function (a, b, d) {
    this.getFirst = function () {
        return a
    };
    this.getSecond = function () {
        return b
    };
    this.getThird = function () {
        return d
    };
    this.setFirst = function (b) {
        a = b
    };
    this.setSecond = function (a) {
        b = a
    };
    this.setThird = function (a) {
        d = a
    }
};
ObjectTriple.compare = function (a, b) {
    return a.getFirst() > b.getFirst() ? 1 : a.getFirst() < b.getFirst() ? -1 : a.getSecond() > b.getSecond() ? 1 : a.getSecond() < b.getSecond() ? -1 : a.getThird() > b.getThird() ? 1 : a.getThird() < b.getThird() ? -1 : 0
};
var UfdsHelper = function () {
    var a = this, b = {};
    this.insert = function (a) {
        if (null != b[a])return !1;
        var d = {};
        d.parent = a;
        d.rank = 0;
        b[a] = d
    };
    this.findSet = function (a) {
        if (null == b[a])return !1;
        for (var d = b[a].parent, h = a; d != h;)h = d, d = b[h].parent;
        return b[a].parent = d
    };
    this.unionSet = function (d, e) {
        if (null == b[d] || null == b[e])return !1;
        if (a.isSameSet(d, e))return !0;
        var h = a.findSet(d), m = a.findSet(e);
        b[h].rank > b[m].rank ? (b[h].parent = m, b[m].rank++) : (b[m].parent = h, b[h].rank++)
    };
    this.isSameSet = function (d, e) {
        return null == b[d] || null ==
        b[e] ? !1 : a.findSet(d) == a.findSet(e)
    }
};
function IsUndirected(a, b) {
    if (0 == a.length)return !0;
    var d = [], e;
    for (e in a) {
        d[e] = [];
        for (var h in a)d[e][h] = 0
    }
    for (var m in b)d[b[m].u][b[m].v] = b[m].w;
    for (e in a)for (h in a)if (d[e][h] != d[h][e])return !1;
    return !0
}
function IsConstantWeighted(a, b) {
    if (0 == a.length)return !0;
    var d = b[0].w, e;
    for (e in b)if (b[e].w != d)return !1;
    return !0
}
function HasNegativeWeight(a, b) {
    if (0 == a.length)return !1;
    for (var d in b)if (0 > parseInt(b[d].w))return !0;
    return !1
}
function IsTree(a, b) {
    function d(a) {
        m[a] = !0;
        for (var e in b)b[e].u === a && !1 === m[b[e].v] && d(b[e].v)
    }

    if (0 == a.length)return !0;
    if (!IsUndirected(a, b))return !1;
    var e = 0, h = 0, m = [], n;
    for (n in a)e++, m[n] = !1;
    for (n in b)h++;
    if (h / 2 != e - 1)return !1;
    d(0);
    for (n in a)if (!1 === m[n])return !1;
    return !0
}
function IsDAG(a, b) {
    if (0 == a.length)return !0;
    if (IsUndirected(a, b))return !1;
    var d = 0, e;
    for (e in a)d++;
    var h = [], m;
    for (m in a) {
        h[m] = [];
        for (var n in a)h[m][n] = 0
    }
    for (e in b)h[b[e].u][b[e].v] = 1;
    for (e = 0; e < d; e++)for (m = 0; m < d; m++)for (n = 0; n < d; n++)1 == h[m][e] && 1 == h[e][n] && (h[m][n] = 1);
    for (m = 0; m < d; m++)if (1 == h[m][m])return !1;
    return !0
}
function TopoSort(a, b) {
    function d(a) {
        e[a] = !0;
        for (var m in b)b[m].u === a && !1 === e[b[m].v] && d(b[m].v);
        h.unshift(a)
    }

    if (0 == a.length || !IsDAG(a, b))return {};
    var e = [], h = [], m;
    for (m in a)e[m] = !1;
    for (m in a)e[m] || d(parseInt(m));
    return h
}
function RunBellmanFord(a, b, d) {
    function e(a) {
        u[a] = !0;
        for (var c in b)b[c].u === a && !1 === u[b[c].v] && e(b[c].v)
    }

    if (0 == a.length)return {};
    var h = 0;
    var m = 0;
    var n = {}, u = [], p;
    for (p in a)h++, n[p] = 999, u[p] = !1;
    n[parseInt(d)] = 0;
    for (p in b)m++;
    for (var r = 1; r < h; r++)for (p in b) {
        var q = b[p].u;
        m = b[p].v;
        d = b[p].w;
        999 != n[q] && 999 != d && n[q] + d < n[m] && (n[m] = n[q] + d)
    }
    h = !1;
    for (p in b)q = b[p].u, m = b[p].v, d = b[p].w, 999 != n[q] && n[q] + d < n[m] && (e(q), h = !0);
    if (h)for (p in a)u[p] && (n[p] = "??");
    return n
}
function HasNegativeWeightCycle(a, b, d) {
    if (0 == a.length)return !1;
    var e = 0;
    var h = 0;
    var m = {}, n;
    for (n in a)e++, m[n] = 999;
    m[parseInt(d)] = 0;
    for (n in b)h++;
    for (var u = 1; u < e; u++)for (n in b)h = b[n].u, d = b[n].v, a = b[n].w, 999 != m[h] && 999 != a && m[h] + a < m[d] && (m[d] = m[h] + a);
    e = !1;
    for (n in b)h = b[n].u, d = b[n].v, a = b[n].w, 999 != m[h] && m[h] + a < m[d] && (e = !0);
    return e
}
var VL = 0, EL = 1, CP3_4_1 = 0, CP3_4_3 = 1, CP3_4_4 = 2, CP3_4_9 = 3, CP3_4_10 = 4, CP3_4_14 = 5, CP3_4_17 = 6, CP3_4_18 = 7, CP3_4_19 = 8, CP3_4_24 = 9, CP3_4_26_1 = 10, CP3_4_26_2 = 11, CP3_4_26_3 = 12, CP3_4_40 = 13, K5 = 14, RAIL = 15, TESSELLATION = 16, BELLMANFORD_KILLER = 17, DIJKSTRA_KILLER = 18, DAG = 19, FORDFULKERSON_KILLER = 20, DINIC_SHOWCASE = 21, MVC_U_TWO_APPROX_KILLER = 22, EXAMPLE_VERTEX_WEIGHTED_TREE = 23, MVC_W_TWO_APPROX_KILLER = 24, INTERESTING_BIPARTITE = 25, LINEAR_CHAIN = 26;
function getExampleGraph(a, b) {
    if (a == CP3_4_1) {
        if (b == VL)return {
            0: {x: 200, y: 50},
            1: {x: 300, y: 50},
            2: {x: 300, y: 150},
            3: {x: 400, y: 50},
            4: {x: 500, y: 50},
            5: {x: 600, y: 50},
            6: {x: 500, y: 150},
            7: {x: 400, y: 150},
            8: {x: 600, y: 150}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 1},
            1: {u: 1, v: 0, w: 1},
            2: {u: 1, v: 2, w: 1},
            3: {u: 1, v: 3, w: 1},
            4: {u: 2, v: 1, w: 1},
            5: {u: 2, v: 3, w: 1},
            6: {u: 3, v: 1, w: 1},
            7: {u: 3, v: 2, w: 1},
            8: {u: 3, v: 4, w: 1},
            9: {u: 4, v: 3, w: 1},
            10: {u: 6, v: 7, w: 1},
            11: {u: 6, v: 8, w: 1},
            12: {u: 7, v: 6, w: 1},
            13: {u: 8, v: 6, w: 1}
        }
    } else if (a == CP3_4_3) {
        if (b == VL)return {
            0: {x: 200, y: 50},
            1: {
                x: 300,
                y: 50
            },
            2: {x: 400, y: 50},
            3: {x: 500, y: 50},
            4: {x: 200, y: 150},
            5: {x: 300, y: 150},
            6: {x: 400, y: 150},
            7: {x: 500, y: 150},
            8: {x: 200, y: 250},
            9: {x: 200, y: 350},
            10: {x: 300, y: 350},
            11: {x: 400, y: 350},
            12: {x: 500, y: 350}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 1},
            1: {u: 0, v: 4, w: 1},
            2: {u: 1, v: 0, w: 1},
            3: {u: 1, v: 2, w: 1},
            4: {u: 1, v: 5, w: 1},
            5: {u: 2, v: 1, w: 1},
            6: {u: 2, v: 3, w: 1},
            7: {u: 2, v: 6, w: 1},
            8: {u: 3, v: 2, w: 1},
            9: {u: 3, v: 7, w: 1},
            10: {u: 4, v: 0, w: 1},
            11: {u: 4, v: 8, w: 1},
            12: {u: 5, v: 1, w: 1},
            13: {u: 5, v: 6, w: 1},
            14: {u: 5, v: 10, w: 1},
            15: {u: 6, v: 2, w: 1},
            16: {u: 6, v: 5, w: 1},
            17: {u: 6, v: 11, w: 1},
            18: {
                u: 7,
                v: 3, w: 1
            },
            19: {u: 7, v: 12, w: 1},
            20: {u: 8, v: 4, w: 1},
            21: {u: 8, v: 9, w: 1},
            22: {u: 9, v: 8, w: 1},
            23: {u: 9, v: 10, w: 1},
            24: {u: 10, v: 5, w: 1},
            25: {u: 10, v: 9, w: 1},
            26: {u: 10, v: 11, w: 1},
            27: {u: 11, v: 6, w: 1},
            28: {u: 11, v: 10, w: 1},
            29: {u: 11, v: 12, w: 1},
            30: {u: 12, v: 7, w: 1},
            31: {u: 12, v: 11, w: 1}
        }
    } else if (a == CP3_4_4) {
        if (b == VL)return {
            0: {x: 200, y: 50},
            1: {x: 300, y: 50},
            2: {x: 300, y: 150},
            3: {x: 400, y: 50},
            4: {x: 500, y: 50},
            5: {x: 600, y: 50},
            6: {x: 400, y: 150},
            7: {x: 500, y: 150}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 1},
            1: {u: 0, v: 2, w: 1},
            2: {u: 1, v: 2, w: 1},
            3: {u: 1, v: 3, w: 1},
            4: {u: 2, v: 3, w: 1},
            5: {u: 2, v: 5, w: 1},
            6: {u: 3, v: 4, w: 1},
            7: {u: 7, v: 6, w: 1}
        }
    } else if (a == CP3_4_9) {
        if (b == VL)return {
            0: {x: 200, y: 50},
            1: {x: 300, y: 50},
            2: {x: 300, y: 150},
            3: {x: 400, y: 50},
            4: {x: 500, y: 50},
            5: {x: 600, y: 50},
            6: {x: 500, y: 150},
            7: {x: 600, y: 150}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 1},
            1: {u: 1, v: 3, w: 1},
            2: {u: 3, v: 2, w: 1},
            3: {u: 2, v: 1, w: 1},
            4: {u: 3, v: 4, w: 1},
            5: {u: 4, v: 5, w: 1},
            6: {u: 5, v: 7, w: 1},
            7: {u: 7, v: 6, w: 1},
            8: {u: 6, v: 4, w: 1}
        }
    } else if (a == CP3_4_10) {
        if (b == VL)return {
            0: {x: 200, y: 150},
            1: {x: 300, y: 50},
            2: {x: 400, y: 150},
            3: {x: 300, y: 250},
            4: {x: 200, y: 350}
        };
        if (b == EL)return {
            0: {
                u: 0,
                v: 1, w: 4
            },
            1: {u: 0, v: 2, w: 4},
            2: {u: 0, v: 3, w: 6},
            3: {u: 0, v: 4, w: 6},
            4: {u: 1, v: 2, w: 2},
            5: {u: 2, v: 3, w: 8},
            6: {u: 3, v: 4, w: 9}
        }
    } else if (a == CP3_4_14) {
        if (b == VL)return {
            0: {x: 200, y: 50},
            1: {x: 350, y: 200},
            2: {x: 350, y: 50},
            3: {x: 500, y: 200},
            4: {x: 350, y: 350}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 9},
            1: {u: 0, v: 2, w: 75},
            2: {u: 1, v: 2, w: 95},
            3: {u: 1, v: 3, w: 19},
            4: {u: 1, v: 4, w: 42},
            5: {u: 2, v: 3, w: 51},
            6: {u: 3, v: 4, w: 31}
        }
    } else if (a == CP3_4_17) {
        if (b == VL)return {
            0: {x: 315, y: 120},
            1: {x: 200, y: 50},
            2: {x: 355, y: 195},
            3: {x: 490, y: 50},
            4: {x: 370, y: 290}
        };
        if (b == EL)return {
            0: {u: 1, v: 4, w: 6},
            1: {u: 1, v: 3, w: 3},
            2: {u: 0, v: 1, w: 2},
            3: {u: 2, v: 4, w: 1},
            4: {u: 0, v: 2, w: 6},
            5: {u: 3, v: 4, w: 5},
            6: {u: 0, v: 3, w: 7}
        }
    } else if (a == CP3_4_18) {
        if (b == VL)return {
            0: {x: 200, y: 125},
            1: {x: 300, y: 50},
            2: {x: 300, y: 200},
            3: {x: 400, y: 125},
            4: {x: 500, y: 125}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 1},
            1: {u: 1, v: 3, w: 2},
            2: {u: 3, v: 4, w: 3},
            3: {u: 0, v: 2, w: 10},
            4: {u: 2, v: 3, w: -10}
        }
    } else if (a == CP3_4_19) {
        if (b == VL)return {
            0: {x: 200, y: 50},
            1: {x: 300, y: 50},
            2: {x: 400, y: 50},
            3: {x: 500, y: 50},
            4: {x: 300, y: 125}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 99}, 1: {u: 1, v: 2, w: 15}, 2: {u: 2, v: 1, w: -42}, 3: {
                u: 2,
                v: 3, w: 10
            }, 4: {u: 0, v: 4, w: -99}
        }
    } else if (a == CP3_4_24) {
        if (b == VL)return {0: {x: 200, y: 50}, 1: {x: 400, y: 50}, 2: {x: 200, y: 250}, 3: {x: 400, y: 250}};
        if (b == EL)return {
            0: {u: 0, v: 1, w: 4},
            1: {u: 1, v: 3, w: 8},
            2: {u: 0, v: 2, w: 8},
            3: {u: 2, v: 3, w: 3},
            4: {u: 2, v: 1, w: 1},
            5: {u: 1, v: 2, w: 1}
        }
    } else if (a == CP3_4_26_1) {
        if (b == VL)return {
            0: {x: 200, y: 150},
            1: {x: 400, y: 250},
            2: {x: 300, y: 50},
            3: {x: 300, y: 250},
            4: {x: 500, y: 150}
        };
        if (b == EL)return {
            0: {u: 0, v: 2, w: 5},
            1: {u: 0, v: 3, w: 3},
            2: {u: 2, v: 3, w: 3},
            3: {u: 3, v: 1, w: 5},
            4: {u: 2, v: 1, w: 3},
            5: {u: 2, v: 4, w: 3},
            6: {u: 1, v: 4, w: 7}
        }
    } else if (a ==
        CP3_4_26_2) {
        if (b == VL)return {
            0: {x: 200, y: 150},
            1: {x: 400, y: 250},
            2: {x: 300, y: 50},
            3: {x: 300, y: 250},
            4: {x: 500, y: 150}
        };
        if (b == EL)return {
            0: {u: 0, v: 2, w: 5},
            1: {u: 0, v: 3, w: 3},
            2: {u: 2, v: 3, w: 3},
            3: {u: 3, v: 1, w: 5},
            4: {u: 2, v: 1, w: 3},
            5: {u: 2, v: 4, w: 3},
            6: {u: 1, v: 4, w: 4}
        }
    } else if (a == CP3_4_26_3) {
        if (b == VL)return {
            0: {x: 200, y: 150},
            1: {x: 400, y: 250},
            2: {x: 300, y: 50},
            3: {x: 300, y: 250},
            4: {x: 500, y: 150}
        };
        if (b == EL)return {
            0: {u: 0, v: 2, w: 5},
            1: {u: 0, v: 3, w: 3},
            2: {u: 3, v: 1, w: 5},
            3: {u: 2, v: 1, w: 2},
            4: {u: 2, v: 4, w: 2},
            5: {u: 1, v: 4, w: 7}
        }
    } else if (a == CP3_4_40) {
        if (b == VL)return {
            0: {
                x: 300,
                y: 50
            }, 1: {x: 400, y: 125}, 2: {x: 400, y: 275}, 3: {x: 300, y: 200}, 4: {x: 200, y: 275}, 5: {x: 200, y: 125}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 2},
            1: {u: 0, v: 5, w: 4},
            2: {u: 1, v: 0, w: 2},
            3: {u: 1, v: 3, w: 9},
            4: {u: 2, v: 3, w: 5},
            5: {u: 3, v: 1, w: 9},
            6: {u: 3, v: 2, w: 5},
            7: {u: 3, v: 4, w: 1},
            8: {u: 4, v: 3, w: 1},
            9: {u: 5, v: 0, w: 4}
        }
    } else if (a == K5) {
        if (b == VL)return {
            0: {x: 280, y: 150},
            1: {x: 620, y: 150},
            2: {x: 350, y: 340},
            3: {x: 450, y: 50},
            4: {x: 550, y: 340}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 24},
            1: {u: 0, v: 2, w: 13},
            2: {u: 0, v: 3, w: 13},
            3: {u: 0, v: 4, w: 22},
            4: {u: 1, v: 2, w: 22},
            5: {u: 1, v: 3, w: 13},
            6: {
                u: 1, v: 4,
                w: 13
            },
            7: {u: 2, v: 3, w: 19},
            8: {u: 2, v: 4, w: 14},
            9: {u: 3, v: 4, w: 19}
        }
    } else if (a == RAIL) {
        if (b == VL)return {
            0: {x: 50, y: 50},
            1: {x: 200, y: 50},
            2: {x: 350, y: 50},
            3: {x: 500, y: 50},
            4: {x: 650, y: 50},
            5: {x: 50, y: 200},
            6: {x: 200, y: 200},
            7: {x: 350, y: 200},
            8: {x: 500, y: 200},
            9: {x: 650, y: 200}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 10},
            1: {u: 1, v: 2, w: 10},
            2: {u: 1, v: 6, w: 8},
            3: {u: 1, v: 7, w: 13},
            4: {u: 2, v: 3, w: 10},
            5: {u: 2, v: 7, w: 8},
            6: {u: 2, v: 8, w: 13},
            7: {u: 3, v: 4, w: 10},
            8: {u: 3, v: 8, w: 8},
            9: {u: 5, v: 6, w: 10},
            10: {u: 6, v: 7, w: 10},
            11: {u: 7, v: 8, w: 10},
            12: {u: 8, v: 9, w: 10}
        }
    } else if (a == TESSELLATION) {
        if (b ==
            VL)return {
            0: {x: 200, y: 50},
            1: {x: 200, y: 170},
            2: {x: 350, y: 110},
            3: {x: 500, y: 170},
            4: {x: 275, y: 290},
            5: {x: 500, y: 290},
            6: {x: 600, y: 50},
            7: {x: 640, y: 240},
            8: {x: 700, y: 120}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 8},
            1: {u: 0, v: 2, w: 12},
            2: {u: 1, v: 2, w: 13},
            3: {u: 1, v: 3, w: 25},
            4: {u: 1, v: 4, w: 9},
            5: {u: 2, v: 3, w: 14},
            6: {u: 2, v: 6, w: 21},
            7: {u: 3, v: 4, w: 20},
            8: {u: 3, v: 5, w: 8},
            9: {u: 3, v: 6, w: 12},
            10: {u: 3, v: 7, w: 12},
            11: {u: 3, v: 8, w: 16},
            12: {u: 4, v: 5, w: 19},
            13: {u: 5, v: 7, w: 11},
            14: {u: 6, v: 8, w: 11},
            15: {u: 7, v: 8, w: 9}
        }
    } else if (a == BELLMANFORD_KILLER) {
        if (b == VL)return {
            0: {x: 100, y: 50},
            1: {x: 175, y: 50},
            2: {x: 250, y: 50},
            3: {x: 325, y: 50},
            4: {x: 400, y: 50},
            5: {x: 475, y: 50},
            6: {x: 550, y: 50}
        };
        if (b == EL)return {
            0: {u: 5, v: 6, w: 1},
            1: {u: 4, v: 5, w: 2},
            2: {u: 3, v: 4, w: 3},
            3: {u: 2, v: 3, w: 4},
            4: {u: 1, v: 2, w: 5},
            5: {u: 0, v: 1, w: 6}
        }
    } else if (a == DIJKSTRA_KILLER) {
        if (b == VL)return {
            0: {x: 100, y: 150},
            1: {x: 150, y: 50},
            2: {x: 200, y: 150},
            3: {x: 250, y: 50},
            4: {x: 300, y: 150},
            5: {x: 350, y: 50},
            6: {x: 400, y: 150},
            7: {x: 450, y: 50},
            8: {x: 500, y: 150},
            9: {x: 550, y: 50},
            10: {x: 600, y: 150}
        };
        if (b == EL)return {
            0: {u: 1, v: 2, w: -32},
            1: {u: 3, v: 4, w: -16},
            2: {u: 5, v: 6, w: -8},
            3: {
                u: 7, v: 8,
                w: -4
            },
            4: {u: 9, v: 10, w: -2},
            5: {u: 0, v: 2, w: 0},
            6: {u: 2, v: 4, w: 0},
            7: {u: 4, v: 6, w: 0},
            8: {u: 6, v: 8, w: 0},
            9: {u: 8, v: 10, w: 0},
            10: {u: 8, v: 9, w: 1},
            11: {u: 6, v: 7, w: 2},
            12: {u: 4, v: 5, w: 4},
            13: {u: 2, v: 3, w: 8},
            14: {u: 0, v: 1, w: 16}
        }
    } else if (a == DAG) {
        if (b == VL)return {
            0: {x: 280, y: 110},
            1: {x: 400, y: 50},
            2: {x: 200, y: 250},
            3: {x: 500, y: 110},
            4: {x: 500, y: 250},
            5: {x: 600, y: 50}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 1},
            1: {u: 0, v: 2, w: 7},
            2: {u: 1, v: 3, w: 9},
            3: {u: 1, v: 5, w: 15},
            4: {u: 2, v: 4, w: 4},
            5: {u: 3, v: 4, w: 10},
            6: {u: 3, v: 5, w: 5},
            7: {u: 4, v: 5, w: 3}
        }
    } else if (a == FORDFULKERSON_KILLER) {
        if (b ==
            VL)return {0: {x: 200, y: 150}, 1: {x: 300, y: 250}, 2: {x: 300, y: 50}, 3: {x: 400, y: 150}};
        if (b == EL)return {
            0: {u: 0, v: 1, w: 8},
            1: {u: 0, v: 2, w: 8},
            2: {u: 1, v: 3, w: 8},
            3: {u: 2, v: 3, w: 8},
            4: {u: 2, v: 1, w: 1}
        }
    } else if (a == DINIC_SHOWCASE) {
        if (b == VL)return {
            0: {x: 100, y: 100},
            1: {x: 400, y: 50},
            2: {x: 400, y: 150},
            3: {x: 300, y: 200},
            4: {x: 250, y: 250},
            5: {x: 200, y: 300},
            6: {x: 500, y: 200},
            7: {x: 550, y: 250},
            8: {x: 600, y: 300},
            9: {x: 700, y: 100}
        };
        if (b == EL)return {
            0: {u: 0, v: 9, w: 7},
            1: {u: 0, v: 1, w: 5},
            2: {u: 1, v: 9, w: 4},
            3: {u: 0, v: 2, w: 8},
            4: {u: 2, v: 9, w: 9},
            5: {u: 0, v: 3, w: 3},
            6: {u: 3, v: 6, w: 1},
            7: {u: 6, v: 9, w: 1},
            8: {u: 0, v: 4, w: 3},
            9: {u: 4, v: 7, w: 4},
            10: {u: 7, v: 9, w: 6},
            11: {u: 0, v: 5, w: 7},
            12: {u: 5, v: 8, w: 6},
            13: {u: 8, v: 9, w: 5}
        }
    } else if (a == MVC_U_TWO_APPROX_KILLER) {
        if (b == VL)return {
            0: {x: 100, y: 100, w: 2},
            1: {x: 100, y: 200, w: 3},
            2: {x: 100, y: 300, w: 4},
            3: {x: 100, y: 400, w: 7},
            4: {x: 200, y: 100, w: 1},
            5: {x: 200, y: 200, w: 5},
            6: {x: 200, y: 300, w: 6},
            7: {x: 200, y: 400, w: 9}
        };
        if (b == EL)return {0: {u: 0, v: 4, w: 1}, 1: {u: 1, v: 5, w: 1}, 2: {u: 2, v: 6, w: 1}, 3: {u: 3, v: 7, w: 1}}
    } else if (a == EXAMPLE_VERTEX_WEIGHTED_TREE) {
        if (b == VL)return {
            0: {x: 150, y: 100, w: 2},
            1: {
                x: 100, y: 200,
                w: 9
            },
            2: {x: 150, y: 200, w: 9},
            3: {x: 200, y: 200, w: 9},
            4: {x: 50, y: 300, w: 1},
            5: {x: 100, y: 300, w: 1},
            6: {x: 150, y: 300, w: 1},
            7: {x: 200, y: 300, w: 1},
            8: {x: 50, y: 400, w: 3},
            9: {x: 100, y: 400, w: 2},
            10: {x: 150, y: 400, w: 4},
            11: {x: 150, y: 500, w: 5},
            12: {x: 200, y: 500, w: 1}
        };
        if (b == EL)return {
            0: {u: 0, v: 1},
            1: {u: 0, v: 2},
            2: {u: 0, v: 3},
            3: {u: 1, v: 4},
            4: {u: 2, v: 5},
            5: {u: 3, v: 6},
            6: {u: 3, v: 7},
            7: {u: 7, v: 8},
            8: {u: 7, v: 9},
            9: {u: 7, v: 10},
            10: {u: 10, v: 11},
            11: {u: 11, v: 12}
        }
    } else if (a == MVC_W_TWO_APPROX_KILLER) {
        if (b == VL)return {
            0: {x: 200, y: 100, w: 5}, 1: {x: 100, y: 200, w: 1}, 2: {
                x: 150, y: 200,
                w: 2
            }, 3: {x: 200, y: 200, w: 2}, 4: {x: 250, y: 200, w: 3}
        };
        if (b == EL)return {0: {u: 0, v: 1}, 1: {u: 0, v: 2}, 2: {u: 0, v: 3}, 3: {u: 0, v: 4}}
    } else if (a == INTERESTING_BIPARTITE) {
        if (b == VL)return {
            0: {x: 100, y: 100, w: 2},
            1: {x: 100, y: 200, w: 3},
            2: {x: 100, y: 300, w: 4},
            3: {x: 200, y: 100, w: 7},
            4: {x: 200, y: 200, w: 1},
            5: {x: 200, y: 300, w: 5}
        };
        if (b == EL)return {
            0: {u: 0, v: 3, w: 1},
            1: {u: 0, v: 4, w: 1},
            2: {u: 2, v: 5, w: 1},
            3: {u: 1, v: 5, w: 1},
            4: {u: 0, v: 5, w: 1}
        }
    } else if (a == LINEAR_CHAIN) {
        if (b == VL)return {
            0: {x: 100, y: 100, w: 3},
            1: {x: 200, y: 100, w: 1},
            2: {x: 300, y: 100, w: 4},
            3: {x: 400, y: 100, w: 2},
            4: {
                x: 500,
                y: 100, w: 9
            },
            5: {x: 600, y: 100, w: 1},
            6: {x: 700, y: 100, w: 2},
            7: {x: 800, y: 100, w: 9}
        };
        if (b == EL)return {
            0: {u: 0, v: 1, w: 3},
            1: {u: 1, v: 2, w: 1},
            2: {u: 2, v: 3, w: 2},
            3: {u: 3, v: 4, w: 4},
            4: {u: 4, v: 5, w: 5},
            5: {u: 5, v: 6, w: 9},
            6: {u: 6, v: 7, w: 8}
        }
    }
};function deepCopy(a) {
    if (a instanceof Array) {
        var b;
        var d = [];
        for (b = 0; b < a.length; b++)d.push(deepCopy(a[b]))
    } else if (a instanceof Object)for (keys in d = {}, a)d[keys] = deepCopy(a[keys]); else d = a;
    return d
};var MAIN_SVG_WIDTH = 1000, MAIN_SVG_HEIGHT = 600, PSEUDOCODE_SVG_WIDTH = 300, PSEUDOCODE_SVG_HEIGHT = 400, graphVertexProperties = {
    innerVertex: {
        r: 14,
        width: 30,
        height: 30,
        "stroke-width": 0,
        "default": {fill: "#eee", stroke: "#fff"},
        "leaf-default": {fill: "#ff0", stroke: "#fff"},
        lazy: {fill: "#eee", stroke: "#fff"},
        "leaf-lazy": {fill: "#ff0", stroke: "#fff"},
        normal_blue: {fill: "#2ebbd1", stroke: "#fff"},
        highlighted: {fill: "#ff8a27", stroke: "#fff"},
        highlighted_rect: {fill: "#ff8a27", stroke: "#fff"},
        traversed: {fill: "#eee", stroke: "#fff"},
        result: {
            fill: "#f7e81e",
            stroke: "#fff"
        },
        rect: {fill: "#eee", stroke: "#fff"},
        result_rect: {fill: "#52bc69", stroke: "#fff"},
        greenFill: {fill: "#52bc69", stroke: "#fff"},
        greenOutline: {fill: "#eee", stroke: "#fff"},
        pinkFill: {fill: "#ed5a7d", stroke: "#fff"},
        pinkOutline: {fill: "#eee", stroke: "#fff"},
        blueFill: {fill: "#2ebbd1", stroke: "#fff"},
        blueOutline: {fill: "#eee", stroke: "#fff"},
        redFill: {fill: "#d9513c", stroke: "#fff"},
        redOutline: {fill: "#eee", stroke: "#fff"},
        greyFill: {fill: "#cccccc", stroke: "#fff"},
        greyOutline: {fill: "#eee", stroke: "#fff"}
    }, outerVertex: {
        r: 16,
        width: 32,
        height: 32,
        "stroke-width": 2,
        "default": {fill: "#333", stroke: "#333"},
        "leaf-default": {fill: "#333", stroke: "#333"},
        lazy: {fill: "#8b00ff", stroke: "#8b00ff"},
        "leaf-lazy": {fill: "#8b00ff", stroke: "#8b00ff"},
        normal_blue: {fill: "#2ebbd1", stroke: "#333"},
        highlighted: {fill: "#ff8a27", stroke: "#ff8a27"},
        highlighted_rect: {fill: "#ff8a27", stroke: "#333"},
        traversed: {fill: "#ff8a27", stroke: "#ff8a27"},
        result: {fill: "#f7e81e", stroke: "#f7e81e"},
        rect: {fill: "#333", stroke: "#333"},
        result_rect: {fill: "#52bc69", stroke: "#333"},
        greenFill: {
            fill: "#52bc69",
            stroke: "#52bc69"
        },
        greenOutline: {fill: "#52bc69", stroke: "#52bc69"},
        pinkFill: {fill: "#ed5a7d", stroke: "#ed5a7d"},
        pinkOutline: {fill: "#ed5a7d", stroke: "#ed5a7d"},
        blueFill: {fill: "#2ebbd1", stroke: "#2ebbd1"},
        blueOutline: {fill: "#2ebbd1", stroke: "#2ebbd1"},
        redFill: {fill: "#d9513c", stroke: "#d9513c"},
        redOutline: {fill: "#d9513c", stroke: "#d9513c"},
        greyFill: {fill: "#cccccc", stroke: "#cccccc"},
        greyOutline: {fill: "#cccccc", stroke: "#cccccc"}
    }, text: {
        "font-size": 16,
        "default": {
            fill: "#333", "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold", "text-anchor": "middle"
        },
        "leaf-default": {
            fill: "#333",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        lazy: {fill: "#333", "font-family": "'PT Sans', sans-serif", "font-weight": "bold", "text-anchor": "middle"},
        "leaf-lazy": {
            fill: "#333",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        normal_blue: {
            fill: "#fff",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        highlighted: {
            fill: "#fff", "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold", "text-anchor": "middle"
        },
        highlighted_rect: {
            fill: "#fff",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "left"
        },
        traversed: {
            fill: "#ff8a27",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        result: {fill: "#fff", "font-family": "'PT Sans', sans-serif", "font-weight": "bold", "text-anchor": "middle"},
        rect: {fill: "#333", "font-family": "'PT Sans', sans-serif", "font-weight": "bold", "text-anchor": "left"},
        result_rect: {
            fill: "#fff", "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold", "text-anchor": "left"
        },
        greenFill: {
            fill: "#fff",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        greenOutline: {
            fill: "#52bc69",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        pinkFill: {
            fill: "#fff",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        pinkOutline: {
            fill: "#ed5a7d",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        blueFill: {
            fill: "#fff", "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold", "text-anchor": "middle"
        },
        blueOutline: {
            fill: "#2ebbd1",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        redFill: {fill: "#fff", "font-family": "'PT Sans', sans-serif", "font-weight": "bold", "text-anchor": "middle"},
        redOutline: {
            fill: "#d9513c",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        greyFill: {
            fill: "#fff",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        greyOutline: {
            fill: "#cccccc",
            "font-family": "'PT Sans', sans-serif", "font-weight": "bold", "text-anchor": "middle"
        }
    }, label: {
        "font-size": 16,
        "default": {
            fill: "#333",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        lazy: {fill: "#333", "font-family": "'PT Sans', sans-serif", "font-weight": "bold", "text-anchor": "middle"},
        normal_blue: {
            fill: "#fff",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        highlighted: {
            fill: "#ff8a27", "font-family": "'PT Sans', sans-serif", "font-weight": "bold",
            "text-anchor": "middle"
        },
        highlighted_rect: {
            fill: "#fff",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "left"
        },
        traversed: {
            fill: "#ff8a27",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        result: {fill: "#fff", "font-family": "'PT Sans', sans-serif", "font-weight": "bold", "text-anchor": "middle"},
        rect: {fill: "#333", "font-family": "'PT Sans', sans-serif", "font-weight": "bold", "text-anchor": "left"},
        result_rect: {
            fill: "#fff", "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold", "text-anchor": "left"
        },
        greenFill: {
            fill: "#fff",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        greenOutline: {
            fill: "#52bc69",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        pinkFill: {
            fill: "#fff",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        pinkOutline: {
            fill: "#ed5a7d",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        blueFill: {
            fill: "#fff", "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold", "text-anchor": "middle"
        },
        blueOutline: {
            fill: "#2ebbd1",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        redFill: {fill: "#fff", "font-family": "'PT Sans', sans-serif", "font-weight": "bold", "text-anchor": "middle"},
        redOutline: {
            fill: "#d9513c",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        greyFill: {
            fill: "#fff",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        greyOutline: {
            fill: "#cccccc",
            "font-family": "'PT Sans', sans-serif", "font-weight": "bold", "text-anchor": "middle"
        }
    }
}, graphEdgeProperties = {
    animateHighlightedPath: {stroke: "#ff8a27", "stroke-width": 10},
    path: {
        "stroke-width": 3,
        "default": {stroke: "#333"},
        highlighted: {stroke: "#ff8a27"},
        traversed: {stroke: "#ff8a27"},
        green: {stroke: "#52bc69"},
        pink: {stroke: "#ed5a7d"},
        blue: {stroke: "#2ebbd1"},
        red: {stroke: "#d9513c"},
        grey: {stroke: "#cccccc"}
    },
    weight: {
        "font-size": 16,
        "default": {
            startOffset: "75%", dy: -5, fill: "#333", "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold", "text-anchor": "middle"
        },
        highlighted: {
            startOffset: "75%",
            dy: -5,
            fill: "#ff8a27",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        traversed: {
            startOffset: "75%",
            dy: -5,
            fill: "#ff8a27",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        green: {
            startOffset: "75%",
            dy: -5,
            fill: "#52bc69",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        pink: {
            startOffset: "75%", dy: -5, fill: "#ed5a7d", "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold", "text-anchor": "middle"
        },
        blue: {
            startOffset: "75%",
            dy: -5,
            fill: "#2ebbd1",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        red: {
            startOffset: "75%",
            dy: -5,
            fill: "#d9513c",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        },
        grey: {
            startOffset: "75%",
            dy: -5,
            fill: "#cccccc",
            "font-family": "'PT Sans', sans-serif",
            "font-weight": "bold",
            "text-anchor": "middle"
        }
    }
}, graphPolygonProperties = {
    polygon: {
        "stroke-width": 0,
        "default": {
            fill: "#eee",
            opacity: 1
        },
        hidden: {fill: "#fff", opacity: 0},
        greenFill: {fill: "#52bc69", opacity: 1},
        greenTransparent: {fill: "#52bc69", opacity: .5},
        pinkFill: {fill: "#ed5a7d", opacity: 1},
        pinkTransparent: {fill: "#ed5a7d", opacity: .5},
        blueFill: {fill: "#2ebbd1", opacity: 1},
        blueTransparent: {fill: "#2ebbd1", opacity: .5},
        redFill: {fill: "#d9513c", opacity: 1},
        redTransparent: {fill: "#d9513c", opacity: .5},
        greyFill: {fill: "#cccccc", opacity: 1},
        greyTransparent: {fill: "#cccccc", opacity: .5}
    }
}, ARROW_MARKER_WIDTH = 3, ARROW_MARKER_HEIGHT = 3, ARROW_REFX = 9, ARROW_FILL =
    "#333";
var mainSvg = d3.select("#viz").append("svg").attr("width", MAIN_SVG_WIDTH).attr("height", MAIN_SVG_HEIGHT), pseudocodeSvg = d3.select("#pseudocode").append("svg").attr("width", PSEUDOCODE_SVG_WIDTH).attr("height", PSEUDOCODE_SVG_HEIGHT);