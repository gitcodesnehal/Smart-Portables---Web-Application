
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Google Chart - Servlet 3</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    // Load the Visualization API and the piechart package.
    google.load('visualization', '1.0', {
        'packages' : [ 'corechart' ]
    });
 
    // Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(drawChart);
 
    // Callback that creates and populates a data table,
    // instantiates the pie chart, passes in the data and
    // draws it.
    function drawChart() {
 
               
        var data = google.visualization.arrayToDataTable([
                                                              ['Product Name', 'No of available items',{ role: 'style' }],
                                                              <c:forEach items="${barDataList}" var="entry">
                                                                  [ '${entry.key}', ${entry.value} , 'stroke-width: 40'],
                                                              </c:forEach>
                                                        ]);
 
        // Set chart options
        var options = {
        title: 'Product names and total numbers of available items',
        //chartArea: {height: 90000},
        width: 1000,
        height: 800,
        hAxis: {
          title: 'Number of available items',
          minValue: 0
        },
        vAxis: {
          title: 'Product names'
        }
      };
 
        var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }
</script>
</head>
<body>
    <div style="width: 600px;">
        <div id="chart_div"></div>
    </div>
</body>
</html>