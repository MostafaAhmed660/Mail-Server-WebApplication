# Mail-Server-WebApplication

this is the final version and my task was front-end using Vue.js

Front-end with Vue.js and Back-end with spring-boot

to run this project you need to run 

-the Front-end Folder by Vue CLI(should be in localhost:8081)port:8081

-the Back-end folder by any java IDE(should be in localhost:8080)port:8080


## class diagram
<br/><br/><br/>
![image](https://user-images.githubusercontent.com/59110598/126705687-bb625ee2-a0ec-491b-9cbb-4ff27a046c09.png)
<br/>
<br/>
<br/>
## design pattern used

&nbsp;-proto type : when we need to copy an email and send it to user or if we need to add it to another folder of user’s folder
<br/>
<br/>

&nbsp;1-adapter : used to convert get the user the suitable page of emails where the user enters the page number and the adapter converts it into start and end index and get them from required folder
<br/>
<br/>
&nbsp;2-iterator : when we need to iterate over a list of users , emails , Folders so we get an iterator over them and iterate as we want
<br/>
<br/>
&nbsp;3-Proxy : when we need to deal with emails by sending or deleting or editing and contacts
by crud and also with users with crud operations
<br/>
<br/>
&nbsp;4-filter : to filter emails according to subject and sender and importance and emailAddress
and other attributes
<br/>
<br/>
&nbsp;5-facade : after we end our classes we need to collect them to the facade to ease the user’s
control to the program where he can call only one function that calls many functions
