(function () {
    window.onload = function () {

        function randomTime(now, r) {
            var d = new Date(now.getTime());
            return d.getHours() + ":" + d.getSeconds();
        }

        function getRandomData() {
            var data = [];

            for (var i = 8; i < 15; i++) {
                for (var j = 14; j < 22; j++) {

                    data.push({
                        'start_time': randomTime(new Date(2000,0,1,i,30), 10),
                        'end_time': randomTime(new Date(2000,0,1, Math.max(i,j),30), 10000),
                        'amount': Math.round(Math.min(Math.abs(i-j), 4)<4?0:Math.random() * 20)
                    });
                }
            }
            return data;
        }

        console.log("RandomData : ", getRandomData());
        timeseries('report', getRandomData());
    }
})();

