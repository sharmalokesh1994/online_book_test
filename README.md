# online_book_test

# Used Tech Stack
1. Java 11
2. gradle-7
3. SpringBoot
4. H2-database
5. JWT Token based Authentication
6. Created Rest API Endpoints
7. Sample Unit test cases are written using JUNIT and Mockito
8. Used Validation
9. Used Exceptional Handler but need to upgrade more
10. Used Custom Exception

# Used Points-
1. Authentication - done (2 types of user admin,user role)

2.Admin is already saved (using configration) and Username = admin@admin.com, password admin
   
3. User can register himself
   
4. User can not use same email to register again

5. Admin can add more books

6. Books can search in pages (paging is used)

6. User can make order (to avoid conflict we used isolation level using @Transactional key)

7. Admin can get stastics (currently 1 month report is supported)

# Start project
1. Take Pull from API
2. go in directory and run as ./gradlew bootRun  (if not working in your system please open it in ide and build as gradle project and run it as Springboot Project)
3. Postman Collection API are shared (path - book/book.postman_collection.json)
4. Run them in the below order (1. registerUser 2.getUserToken 3.getAdminToken 4. addBooks 5. searchBooks 6. makeOrder 7. getOrderById 8. searchOrderBetweenDats 8. getReport )
5. For better reference sharing one image, follow same order like given in image (path book/bookAPI.png) 

# Some points-
1. facing some issue with Docker in local system So containerization was not possible
2. Swagger not used due to less time 
3. Logging is also need to implement
