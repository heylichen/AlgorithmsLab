function tree() {
    var _chart = {};
    var invisiblePlaceholderNode = {fake: true};
    var _width = 1200, _height = 700,
        _margins = {top: 200, left: 20, right: 20, bottom: 220},
        _svg,
        _nodes,
        _i = 0,
        _showText = true,
        _duration = 300,
        _bodyG,
        _root;

    _chart.render = function (ele) {
        if (!_svg) {
            var sel = "body";
            if (ele) {
                sel = ele;
            }
            _svg = d3.select(sel).append("svg")
                .attr("height", _height)
                .attr("width", _width);
        }

        renderBody(_svg);
    };

    function renderBody(svg) {
        if (!_bodyG) {
            _bodyG = svg.append("g")
                .attr("class", "body")
                .attr("transform", function (d) {
                    return "translate(" + _margins.left
                        + "," + _margins.top + ")";
                });
        }

        _root = d3.hierarchy(_nodes, function (d) {
            var arr = [];
            if (d.childrenSize == 0) {
                return arr;
            }
            for (var i = 0; i < d.childrenSize; i++) {
                arr.push(d.children[i]);
            }
            return arr;
        }); // <-A

        render(_root);
    }

    function render(root) {
        var tree = d3.tree() // <-B
            .size([(_width - _margins.left - _margins.right),
                (_height - _margins.top - _margins.bottom)
            ]);

        tree(root); // <-C


        console.log(tree.size());

        renderNodes(root); // <-D

        renderLinks(root); // <-E
    }

    var leftAdd = (_width - _margins.left - _margins.right) / 2;

    function renderNodes(root) {

        var nodes = root.descendants();

        var nodeElements = _bodyG.selectAll("g.node")
            .data(nodes, function (d) {
                return d.id || (d.id = ++_i);
            });

        var nodeEnter = nodeElements.enter().append("g")
            .attr("class", "node")
            .attr("transform", function (d) {  // <-F
                return "translate(" + (d.x)
                    + "," + d.y + ")";
            })
            .on("click", function (d) { // <-G
                toggle(d);
                render(_root);
            });

        nodeEnter.append("circle") // <-H
            .attr("r", function (d) {
                if (d.data.fake) {
                    return 0;
                } else {
                    return 2.5;
                }
            }).append("title")
            .text(function (d) {
                var data = d.data;
                var text = "";
                for (var i = 0; i < data.size; i++) {
                    text = text + data.keys[i] + " ";
                }
                var depth = d.depth;
                return "key:" + text + ", depth:" + depth;
            });

        var nodeUpdate = nodeEnter.merge(nodeElements)
            .transition().duration(_duration)
            .attr("transform", function (d) {
                return "translate(" + (d.x) + "," + d.y + ")"; // <-I
            });

        nodeUpdate.select('circle')
            .style("fill", function (d) {
                return d._children ? "lightsteelblue" : "#fff"; // <-J
            });

        var nodeExit = nodeElements.exit()
            .transition().duration(_duration)
            .attr("transform", function (d) {
                return "translate(" + d.x
                    + "," + d.y + ")";
            })
            .remove();

        nodeExit.select("circle")
            .attr("r", 1e-6)
            .remove();

        renderLabels(nodeEnter, nodeUpdate, nodeExit);
    }

    function renderLabels(nodeEnter, nodeUpdate, nodeExit) {
        nodeEnter.append("text")
            .attr("x", function (d) {
                return d.children || d._children ? -10 : 10; // <-K
            })
            .attr("transform", "rotate(90)")
            .attr("dy", ".35em")
            .attr("text-anchor", function (d) {
                return d.children || d._children ? "end" : "start"; // <-L
            })
            .text(function (d) {

                var data = d.data;
                var text = "";
                for (var i = 0; i < data.size; i++) {
                    text = text + data.keys[i] + " ";
                }
                return text;
            })
            .style("fill-opacity", 1e-6);

        nodeUpdate.select("text")
            .style("fill-opacity", 1);

        nodeExit.select("text")
            .style("fill-opacity", 1e-6)
            .remove();
    }

    function renderLinks(root) {
        var nodes = root.descendants().slice(1);

        var link = _bodyG.selectAll("path.link")
            .data(nodes, function (d) {
                return d.id || (d.id = ++_i);
            });

        link.enter().insert("path", "g") // <-M
            .attr("class", "link")
            .merge(link)
            .transition().duration(_duration)
            .attr("d", function (d) {
                if (d.data.fake || d.parent.data.fake) {
                    return "";
                }
                return generateLinkPath(d, d.parent); // <-N
            });

        link.exit().remove();
    }

    function generateLinkPath(target, source) {
        var path = d3.path();
        path.moveTo(target.x, target.y);

        path.bezierCurveTo(target.x, (target.y + source.y) / 2, source.x,
            (target.y + source.y) / 2, source.x, source.y);
        return path.toString();
    }

    function toggle(d) {
        if (d.children) {
            d._children = d.children;
            d.children = null;
        } else {
            d.children = d._children;
            d._children = null;
        }
    }

    _chart.width = function (w) {
        if (!arguments.length) return _width;
        _width = w;
        return _chart;
    };

    _chart.height = function (h) {
        if (!arguments.length) return _height;
        _height = h;
        return _chart;
    };

    _chart.margins = function (m) {
        if (!arguments.length) return _margins;
        _margins = m;
        return _chart;
    };

    _chart.nodes = function (n) {
        if (!arguments.length) return _nodes;
        _nodes = n;
        return _chart;
    };

    _chart.showText = function () {
        var value = _showText ? "hidden" : "visible ";
        d3.selectAll(".node text").attr("visibility", value);
        _showText = !_showText;

    }
    return _chart;
}