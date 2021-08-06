 there is a difference between auto-generated key and
auto generated Key. For example, when doing insert process, auto-generated key will
automatically hit to the database.
[https://www.youtube.com/watch?v=dNLZceo9ufo&list=PLGTrAf5-F1YLNgq_0TXd9Xu245dJxqJMr&index=24]
In Hibernate, We use two level cache. All the process, fist stored in cache. Only 
when We commit the transaction, then all the changes will in the database.
---
MergeTestMain.java:   
Only refer to what does persist and Merge do? Also there is one method demo the diff between   
Merge and persist. Persist is for saving to the database. Merge is for merge and update the difference.
Detached Object can not remove again.   

FirstLevelCacheMain:    
surrogate key, when new objects creates, it will hit to database immediately. While natural key will not.
Before Transaction Commit, It's possible to retrieval object attributes.   
getReference, find both OK.   

Before Transaction Commit, use setter to update attributes value.      
For the remove, first, if already persisted, remove will hit twice to database, one when inserting, one when remove.

_Clear_ method invalid the cache.
_flush_ Synchronize the persistence context to the underlying database. -> what does flush do.






