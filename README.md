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
- mvn install -DskipTests
- java -jar ./target/blind-auction-0.0.1-SNAPSHOT.jar
- execute operational functional endpoints via REST client like Postman or any other tool of your choice

## Operational Functionalities
- Use Postman to execute below use cases.
- Before executing each of below function, tocken must be generated. Follow these steps to generate tocken for each user.
  - Open Postman
  - POST http://localhost:8090/oauth2/token
  - Authorization - Type: Basic Auth, Username: seller-1, Password: secret1
  - Body - 
    - x-www-form-urlencoded
    - Parameter - grant_type : client_credentials
    - Parameter - scope : user.write
  - Hit Send button
  - Copy the access_tocken value
- Repeat above step for Username: buyer-1, Password: secret2
- Repeat above step for Username: buyer-2, Password: secret3
- Use these tocken in below Authorization tab.
 

<table>
  <tr>
    <th>REST endpoints</th>
    <th>Usage</th>
    <th>Request</th>
    <th>Allowed Users</th>
    <th>Parameters</th>
    <th>Authorization</th>
    <th>Response</th>
  </tr>
  <tr>
    <td>http://localhost:8081/auctions</td>
    <td>Entry point for application</td>
    <td>GET</td>
    <td>seller-1, buyer-1, buyer-2</td>
    <td></td>
    <td>Bearer Tocken</td>
    <th>Greetings from Auctions team!</th>
  </tr>
  <tr>
    <td>http://localhost:8081/auctions/create-auction</td>
    <td>Create new auction</td>
    <td>POST</td>
    <td>seller-1</td>
    <td>
        {
            "description": "hat",
            "startingPrice": 70
        }
    </td>
    <td>Bearer Tocken</td>
    <td>
        {
            "auctionId": 2,
            "auctionType": "bidding",
            "description": "hat",
            "startingPrice": 70,
            "currentPrice": null,
            "bids": []
        }
    </td>
  </tr>
  <tr>
    <td>http://localhost:8081/auctions/{auctionId}/place-bid</td>
    <td>Bid against auction</td>
    <td>POST</td>
    <td>buyer-1, buyer-2</td>
    <td>
        {
            "bidPrice": 140
        }
    </td>
    <td>Bearer Tocken</td>
    <td>
        {
            "bidId": 6,
            "bidPrice": 110
        }
    </td>
  </tr>
  <tr>
    <td>http://localhost:8081/auctions/conclude-auction</td>
    <td>Conclude the auction by selecting first highest bidder</td>
    <td>POST</td>
    <td>buyer-1, buyer-2</td>
    <td>
        {
            "auctionId": 2
        }
    </td>
    <td>Bearer Tocken</td>
    <td>
        [
            {
                "auctionId": 2,
                "auctionType": null,
                "description": "hat",
                "startingPrice": 70.00,
                "auctionStatus": "SOLD",
                "currentPrice": 140.00,
                "bids": [
                    {
                        "bidId": 6,
                        "bidPrice": 130.00,
                        "bidWinner": false
                    },
                    {
                        "bidId": 7,
                        "bidPrice": 140.00,
                        "bidWinner": true
                    }
                ]
            }
        ]
    </td>
  </tr>
    <tr>
    <td>http://localhost:8081/auctions/get-all-auctions</td>
    <td>List all auctions in the system, including bids against each auction</td>
    <td>GET</td>
    <td>seller-1, buyer-1, buyer-2</td>
    <td></td>
    <td>Bearer Tocken</td>
    <td>
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
    </td>
  </tr>
</table>

