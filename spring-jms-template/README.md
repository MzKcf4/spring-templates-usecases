Message Queue : 

An abstract layer that allows multiple process ( likely on different machines ) to communicate via various models ( point-to-point , publish / subscribe , etc.)

It can be used when resilience is required and late response is fine.

Use cases :  (Ref: https://www.cloudamqp.com/blog/rabbitmq-use-cases-explaining-message-queues-and-when-to-use-them.html)

you have “timeout errors” due to too many requests at the same time.
you need a decoupled way to communicate between or within your application.
you are polling a data store too often and you want this data store to be available to answer qualified queries instead
you need to scale up and down during peak hours
you have long-running processes and background jobs
Act as the middleman in between microservices