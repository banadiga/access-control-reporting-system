(function () {
    window.onload = function () {
        d3.json("api/reports/general-working-time-report", function (error, data) {
            if (error) {
                return console.warn(error);
            }
            console.log("Loaded data : ", data);
            timeseries('report', data.data);
        });
    }
})();

