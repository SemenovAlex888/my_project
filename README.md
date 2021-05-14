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
 
 ### Get the voting results for the specified date
 * `curl -s http://localhost:8080/my_project/user/restaurants/sum?date=2021-04-09 --user admin@gmail.com:admin`
 
 
 
 
