(function () {
    var margin = {
        top: 10,
        right: 25,
        bottom: 15,
        left: 35
    };
    var format = "%H:%M";
    var xTicks =6;
    var yTicks =12;

    var width = window.innerWidth - 200,
        height = (500 - margin.top - margin.bottom);

    var xStart = new Date(2000, 1, 1, 7, 0, 0, 0).getTime(),
        xStop = new Date(2000, 1, 1, 14, 59, 59, 59).getTime();

    var yStart = new Date(2000, 1, 1, 12, 0, 0, 0).getTime(),
        yStop = new Date(2000, 1, 1, 21, 59, 59, 59).getTime();

    this.timeseries = function render(classd, data) {
        var x = d3.time.scale()
                .range([margin.right, width - margin.left])
                .domain(d3.extent([xStart, xStop])),
            y = d3.time.scale()
                .range([height - margin.bottom - margin.top, margin.top])
                .domain(d3.extent([yStart, yStop]));

        var xAxis = d3.svg.axis().scale(x).orient("bottom")
            .ticks(xTicks)
            .tickSize(-height, 0)
            .tickFormat(d3.time.format(format));

        var yAxis = d3.svg.axis().scale(y).orient("left")
            .ticks(yTicks)
            .tickSize(-width + margin.right, margin.left)
            .tickFormat(d3.time.format(format));

        var svg = d3.select("." + classd).append("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom);

        var context = svg.append("g")
            .attr("class", "context")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        context.append("g")
            .attr("class", "x axis")
            .attr("transform",
                  "translate(" + margin.left + "," + (margin.top + (height - margin.bottom)) + ")")
            .call(xAxis);

        context.append("g")
            .attr("class", "y axis")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")")
            .call(yAxis);

        var circles = context.append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        circles.selectAll(".circ")
            .data(transformData(data))
            .enter().append("circle")
            .attr("class", "circ")
            .attr("cx", function (d) {
                      return (x(getTime(d.start_time)));
                  })
            .attr("cy", function (d) {
                      return (y(getTime(d.end_time)));
                  })
            .attr("r", function (d) {
                      return d.amount;
                  });

    };

    function transformData(data) {
        data.forEach(function(d) {
            d.start_time = Date.parse("2000/1/1 " + d.start_time);
            d.end_time = Date.parse("2000/1/1 " + d.end_time);
            return d;
        })
        return data
    }

    function getTime(d) {
        var date = moment(d);
        date.date(1);
        date.month(1);
        date.year(2000);
        return date.valueOf();
    }

})();
