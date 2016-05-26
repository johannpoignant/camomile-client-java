POST/login username=_________&password=___________

POST/user username=_________&password=___________&description=_________&role=___________
==>{"error":"Invalid role."} role = admin or user
==>{"error":"Invalid password."} min 9 characters
==>{"error":"Invalid username (duplicate)."} déjà existant




>>> Sending request : sun.net.www.protocol.http.HttpURLConnection:http://localhost:3000/user
	POST : username=user2&password=123456789&description={}&role=user
>> Response Code : 200
>> Response : {"username":"user2","role":"user","_id":"574572121eb602010014c904","description":"{}"}

>>> Sending request : sun.net.www.protocol.http.HttpURLConnection:http://localhost:3000/user
	POST : {"password":"123456789","role":"user","name":"user2","description":{}}




	POST : {"username":"root","password":"admin"}

	POST : {"password":"123456789","role":"user","name":"user2","description":{}}