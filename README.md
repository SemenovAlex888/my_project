# Restaurant voting app

A system that allows you to determine in which restaurant to dine based on a vote.

 * 2 types of users: admin and regular users
 * The administrator can enter the restaurant and its lunch menu of the day (usually 2-5 items, only the name of the dish and the price)
 * The menu changes every day (updates are made by admins)
 * Users can vote in which restaurant they want to dine.
 * Only one vote counts for each user
 * If the user votes again on the same day:
 * If it's before 11:00, we're guessing he changed his mind.
 * If after 11:00 it is too late, the vote cannot be changed.
 * Each restaurant offers a new menu every day.
 
 cURL commands:
 
 
 ## Get the voting results for the specified date
 
 * `curl -s http://localhost:8080/my_project/user/restaurants/sum?date=2021-04-09 --user user1@yandex.ru:password`
 
 
 ### Administrator actions
 
 #### Work with Users
 
 - get All Users:

> `curl -s http://localhost:8080/my_project/admin/users --user admin@gmail.com:admin`

- get User 100002:
    
> `curl -s http://localhost:8080/my_project/admin/users/100002 --user admin@gmail.com:admin`

-  get User by email:

> `curl -s http://localhost:8080/my_project/admin/users/by?email=user2@yandex.ru --user admin@gmail.com:admin`

 - create User: 

> `curl -s -X POST -d '{"name": "User5",
      "email": "user5@yandex.ru",
      "password": "password",
      "enabled": true,
      "registered": "2021-05-12T13:05:27.357+00:00",
      "roles": ["USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/my_project/admin/users --user admin@gmail.com:admin`

-  update User 100003:

> `curl -s -X PUT -d '{"id": 100003,
      "name": "User3",
      "email": "user333@yandex.ru",
       "password": "password",
      "enabled": true,
      "registered": "2021-05-12T13:05:27.357+00:00",
      "roles": ["USER"]}' -H 'Content-Type: application/json' http://localhost:8080/my_project/admin/users/100003 --user admin@gmail.com:admin`

- delete User 100004:

> `curl -s -X DELETE http://localhost:8080/my_project/admin/users/100004 --user admin@gmail.com:admin`


 #### Working with restaurants

 - get All Restaurants with dishes:

> `curl -s http://localhost:8080/my_project/admin/restaurants/ --user admin@gmail.com:admin`

- get Restaurant 100006:
    
> `curl -s http://localhost:8080/my_project/admin/restaurants/100006 --user admin@gmail.com:admin`

 - create Restaurant: 

> `curl -s -X POST -d '{"name": "Mansarda2",
   "address": "Pochtamtskaya st., 7",
   "dishes": null}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/my_project/admin/restaurants/ --user admin@gmail.com:admin`

-  update Restaurant 100006:

> `curl -s -X PUT -d '{"id": 100006,
      "name": "Rome",
      "address": "Moskovsky prospect, 97A"}' -H 'Content-Type: application/json' http://localhost:8080/my_project/admin/restaurants/100006 --user admin@gmail.com:admin`

- delete Restaurant 100005:

> `curl -s -X DELETE http://localhost:8080/my_project/admin/restaurants/100005 --user admin@gmail.com:admin`

 - get the voting results for the specified date:

> `curl -s http://localhost:8080/my_project/admin/restaurants/sum?date=2021-04-09 --user admin@gmail.com:admin`


 #### Working with dishes

 - get all Dishes for Restaurant 100005:

> `curl -s http://localhost:8080/my_project/admin/restaurant/100005/dishes --user admin@gmail.com:admin`

- get Dish 100011 for Restaurant 100005:
    
> `curl -s http://localhost:8080/my_project/admin/restaurant/100005/dishes/100011 --user admin@gmail.com:admin`

 - create Dish: 

> `curl -s -X POST -d '{"name": "New Mozzarella",
   "price": 750}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/my_project/admin/restaurant/100005/dishes --user admin@gmail.com:admin`

-  update Dish 100012 for Restaurant 100005:

> `curl -s -X PUT -d '{"name": "Beef Pho Bo",
            "price": 1500}' -H 'Content-Type: application/json' http://localhost:8080/my_project/admin/restaurant/100005/dishes/100012 --user admin@gmail.com:admin`

- delete Dish 100010 for Restaurant 100005:

> `curl -s -X DELETE http://localhost:8080/my_project/admin/restaurant/100005/dishes/100010 --user admin@gmail.com:admin`


 ### User actions
 
 #### Working with a profile

- get User:
    
> `curl -s http://localhost:8080/my_project/user --user user2@yandex.ru:password`

- User registration:

> `curl -s -X POST -d '{"name": "User5",
   "email": "user5@yandex.ru",
   "password": "password",
   "enabled": true,
   "registered": "2021-05-12T13:05:27.357+00:00",
   "roles": ["USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/my_project/user/register`

-  update User 100002:

> `curl -s -X PUT -d '{"id": 100002,
   "name": "User2222",
   "email": "user2@yandex.ru",
   "password": "password",
   "enabled": true,
   "registered": "2021-05-12T13:05:27.357+00:00",
   "roles": ["USER"]}' -H 'Content-Type: application/json' http://localhost:8080/my_project/user --user user2@yandex.ru:password`

- delete User 100000:

> `curl -s -X DELETE http://localhost:8080/my_project/user --user user1@yandex.ru:password`



 #### Working with restaurants

 - get All Restaurants with dishes:

> `curl -s http://localhost:8080/my_project/user/restaurants --user user1@yandex.ru:password`

 - get the voting results for the specified date:

> `curl -s http://localhost:8080/my_project/user/restaurants/sum?date=2021-04-09 --user user1@yandex.ru:password`


 #### Voting for a restaurant

 - voting for the selected restaurant:

> `curl -s -X POST http://localhost:8080/my_project/votes/100008 --user user1@yandex.ru:password`
