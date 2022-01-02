When should use AOP : 
	- Monitor / Alert service
		�� Logging
		�� Exception Handling
		�� Instrumentation
			�� Size of Rest request
			�� Process time used by Rest API

Why use AOP in above cases : 
	- Keep instrumentation code outside of actual business logic
		�� Keep code clean
	- Keep these Cross-cutting concerns at one single place ( class / package )
Easy to declaratively apply / remove them whenever needed