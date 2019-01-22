# TempCheckr
An android application that displays a 7-day forecast for any given location. 



*Want to try the app out? Download the test version here: https://tsfr.io/5x3cjg* 

**Features**

* Tempcheckr extracts weather data using this API: https://developer.here.com/api-explorer/rest/auto_weather/weather-forecast-7days-simple. 

* The user can look up the weather based on the city or zip code. 

* When running the app locally, each search gets saved to a database via a servlet using a GET request. Once that information is saved, the user can browse their search history with a POST request. 


![Image of front page](https://github.com/SumaitaH/TempCheckr/blob/master/Images/Screen%20Shot%202018-12-21%20at%204.01.25%20AM.png) ![7-day forecast](https://github.com/SumaitaH/TempCheckr/blob/master/Images/Screen%20Shot%202018-12-21%20at%204.01.49%20AM.png) 

![Servlet](https://github.com/SumaitaH/TempCheckr/blob/master/Images/Screen%20Shot%202018-12-21%20at%204.06.09%20AM.png) ![Search History](https://github.com/SumaitaH/TempCheckr/blob/master/Images/Screen%20Shot%202018-12-21%20at%204.06.43%20AM.png)
