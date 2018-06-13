# Airports:
#     column 1: airport name
#     column 2: connection time
# 
# Flights:
#     column 1: departure airport name
#     column 2: arrival airport name
#     column 3: departure time
#     column 4: arrival time
# 
# Customers:
#     column 1: departure airport name
#     column 2: arrival airport name
#     column 3: earliest departure time

# Enter "sh readme.txt" at the Linux command prompt to execute
# the following test cases in batch. Read .out files for the detailed
# results from executions.

java MainAir Lufthansa-airports.txt Lufthansa-flights.txt Lufthansa-customers200.txt > tickets-200.out

diff tickets-200.out tickets-200answer.out


