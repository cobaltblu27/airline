java -cp ./bin MainAir ./public/Hawaiian-airports.txt ./public/Hawaiian-flights.txt ./public/Hawaiian-customers10.txt > ./output/tickets01.out
java -cp ./bin MainAir ./public/Hawaiian-airports.txt ./public/Hawaiian-flights.txt ./public/Hawaiian-customers100.txt > ./output/tickets02.out
java -cp ./bin MainAir ./public/Hawaiian-airports.txt ./public/Hawaiian-flights.txt ./public/Hawaiian-customers-bogus.txt > ./output/tickets03.out
java -cp ./bin MainAir ./public/Hawaiian-airports.txt ./public/Hawaiian-flights-singles.txt ./public/Hawaiian-customers100.txt > ./output/tickets04.out
java -cp ./bin MainAir ./public/Alaska-airports.txt ./public/Alaska-flights.txt ./public/Alaska-customers200.txt > ./output/tickets05.out
java -cp ./bin MainAir ./public/Alaska-airports.txt ./public/Alaska-flights.txt ./public/Alaska-customers2000.txt > ./output/tickets06.out
java -cp ./bin MainAir ./public/Alaska-airports.txt ./public/Alaska-flights.txt ./public/Alaska-customers20000.txt > ./output/tickets07.out

# Enter "sh readme.txt" at the Linux command prompt to execute
# the following test cases in batch. Read .out files for the detailed
# results from executions.

