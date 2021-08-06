
First level cache, there is an difference between auto-generated key and
auto generated Key. For example, when doing insert process, auto-generated key will
automatically hit to the database. 

[https://www.youtube.com/watch?v=dNLZceo9ufo&list=PLGTrAf5-F1YLNgq_0TXd9Xu245dJxqJMr&index=24]

In Hibernate, We use two level cache.All the process, fist stored in cache. Only 
when We commit the transaction, then all the changes will in the database.
