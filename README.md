<div id="top"></div>
<br />
<div align="center">
<h3 align="center">Rest API application for Blind auction</h3>
  <p align="center">
    Restfull app created using spring boot .
  </p>
</div>

## Built With
* [JDK 21](https://openjdk.org/projects/jdk/21/)
* [Spring Boot](https://spring.io/projects/spring-boot) 
* [Lombok](https://www.projectlombok.org/features/all)
* [Hibernate](https://hibernate.org/orm/documentation/)
* [HSQLDB](https://hsqldb.org/)
* [Maven](https://maven.apache.org/)

## Instruction to run the application
### Prerequisites
- Ensure Java, Maven, Git bash are installed on machine.
- Ensure all paths/classpaths for Java, Maven are set.
### Steps
- git clone https://github.com/sachy76/blind-auction.git
- cd blind-auction
- mvn install
- java -jar ./target/blind-auction-0.0.1-SNAPSHOT.jar
- run this command to check if application has started - curl http://localhost:8081/auctions
- execute operational functional endpoints via REST client like Postman

## Operational Functionalities
### User can 
- Create new auction
- Bid against auction
- List all auctions in the system, including bids against each auction

## Endpoints
### Get Greeting message from Aution team
```
GET http://localhost:8081/auctions

RESPONSE: Greetings from Auctions team!
```

### Create new auction
```
POST http://localhost:8081/auctions/create-auction
Content-Type: application/json

{
"description": "hat",
"startingPrice": 70
}

RESPONSE:
{
    "auctionId": 2,
    "auctionType": "bidding",
    "description": "hat",
    "startingPrice": 70,
    "currentPrice": null,
    "bids": []
}
```

### Bid against auction
```
POST http://localhost:8081/auctions/{auctionId}/place-bid
Content-Type: application/json

{
    "bidPrice": 110
}

RESPONSE:
{
    "bidId": 6,
    "bidPrice": 110
}
```

### List all auctions
```
GET http://localhost:8081/auctions/get-all-auctions

RESPONSE:
[
    {
        "auctionId": 2,
        "auctionType": null,
        "description": "hat",
        "startingPrice": 70.00,
        "currentPrice": 150.00,
        "bids": [
            {
                "bidId": 6,
                "bidPrice": 110.00
            },
            {
                "bidId": 7,
                "bidPrice": 150.00
            }
        ]
    }
]

```
### Conclude OPEN Auction by letting higest bidden win.
```
POST http://localhost:8081/auctions/conclude-auction
Content-Type: application/json

{
    "auctionId": 2
}

RESPONSE:
{
    "bidId": 6,
    "bidPrice": 110
}
```

