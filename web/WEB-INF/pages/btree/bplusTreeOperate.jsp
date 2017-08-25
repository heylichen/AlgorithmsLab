<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Algorithms Lab - btree</title>
    <script type="text/javascript" src="https://d3js.org/d3.v4.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.js"></script>
    <script type="text/javascript" src="../resources/js/d3Tree.js"></script>
    <link rel="stylesheet" type="text/css" href="../resources/css/styles.css"/>
    <style type="text/css">
        .node circle {
            cursor: pointer;
            fill: #fff;
            stroke: steelblue;
            stroke-width: 1px;
        }

        .node text {
            font-size: 9px;
            font-family: 微软雅黑
        }

        path.link {
            fill: none;
            stroke: #ccc;
            stroke-width: 1px;
        }

        input {
            width: 50px;
        }
    </style>
</head>
<body>
<div id="operate">
    width <input name="width" value="400"/>
    height <input name="height" value="280"/>
    margins top <input name="top"/>
    bottom <input name="bottom"/>
    <button id="showTextBtn" type="button">show text</button>
    <br>
    t <input name="t" value="2" class="init"/>
    node count <input name="nodeCount" value="10" class="init"/>
    <button class="newTree" action="new" type="button">init btree</button>
    <button class="newTree" action="random" type="button">random btree</button>

    key <input name="key" value="4" class="update"/>
    <button id="addBtn" action="addKey" type="button" class="update">add key</button>
    <button id="deleteBtn" action="deleteKey" type="button" class="update">delete key</button>
</div>


<div id="graphContainer">

</div>
<script type="text/javascript">
    $(function () {
        var chart;
        var lastTreeData;

        function resize(chart) {
            var chartWidth = parseInt($("[name='width']").val());
            var chartHeight = parseInt($("[name='height']").val());
            var top = parseInt($("[name='top']").val());
            var bottom = parseInt($("[name='bottom']").val());
            var t = $("input[name='t']").val();
            var topBottom = 16 * (2 * t - 1);
            if (!top) {
                top = topBottom;
            }
            if (!bottom) {
                bottom = topBottom;
            }

            if (chartWidth) {
                chart.margins({top: top, left: 20, right: 20, bottom: bottom});
                chart.width(chartWidth);
            }
            if (chartHeight) {
                chart.margins({top: top, left: 20, right: 20, bottom: bottom});
                chart.height(chartHeight);
            }
            console.log(chart.margins());
        }

        $("button.newTree").click(function () {
            var action = $(this).attr('action');
            var form = {};
            $("input.init").each(function (d) {
                var ele = $(this);
                var name = ele.attr("name");
                var value = ele.val();
                form[name] = value;
            })
            $("#graphContainer").html('');
            $.get(action, form, function (data) {
                lastTreeData = data;
                console.log(data);
                chart = tree();
                resize(chart);
                chart.nodes(data).render("#graphContainer");
            }, "json");
        });

        $("button.update").click(function () {
            var ele = $(this);
            var action = ele.attr("action");
            var key = $("#operate [name='key']").val();
            var formData = {
                "json": JSON.stringify(lastTreeData),
                "key": key
            };

            $.post(action, formData, function (data) {
                console.log(data);
                lastTreeData = data;
                chart = tree();
                resize(chart);
                chart.nodes(data).render("#graphContainer");
            }, "json");
        });

        $("#showTextBtn").click(function () {
            chart.showText();
        });
    });
</script>
</body>
</html>
